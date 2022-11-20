package com.graduation.project.service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.graduation.project.argumentResolver.Login;
import com.graduation.project.domain.TokenPair;
import com.graduation.project.domain.User;
import com.graduation.project.domain.enumtype.Auth;
import com.graduation.project.dto.UpdateUserRequest;
import com.graduation.project.dto.*;
import com.graduation.project.error.UserErrorResult;
import com.graduation.project.error.UserException;
import com.graduation.project.repository.TokenPairRepository;
import com.graduation.project.repository.UserRepository;
import com.graduation.project.security.JwtUtils;
import com.graduation.project.security.userdetails.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TokenPairRepository tokenPairRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserGetResponse getUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElseThrow(() -> new UserException(UserErrorResult.USER_NOT_FOUND));

        return UserGetResponse.builder()
                .name(user.getName())
                .nickname(user.getNickname())
                .loginId(user.getLoginId())
                .build();
    }

    public void removeUser(Long userId) {
        Optional<User> optionalUsers = userRepository.findById(userId);
        optionalUsers.orElseThrow(() -> new UserException(UserErrorResult.USER_NOT_FOUND));
        userRepository.deleteById(userId);
    }
    
    public void updateUser(Long userId, UpdateUserRequest request) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorResult.USER_NOT_FOUND));
        userRepository.findByLoginId(request.getLoginId())
                .ifPresent((user) -> {
                    throw new UserException(UserErrorResult.ALREADY_LOGINID_OR_NICKNAME_EXIST);
                });
        String password = findUser.getPassword();
        if (request.getPassword() != null) {
            password = passwordEncoder.encode(request.getPassword());
        }
        findUser.updateInfo(request, password);
    }

    public void signup(SignupRequest request) {
        if (!Objects.equals(request.getPassword(), request.getPasswordCheck()))
            throw new UserException(UserErrorResult.NOT_MATCH_PASSWORD_CHECK);

        userRepository.findByLoginId(request.getLoginId())
                .ifPresent((user) -> {
                    throw new UserException(UserErrorResult.ALREADY_LOGINID_OR_NICKNAME_EXIST);
                });

        String hashedPwd = passwordEncoder.encode(request.getPassword());
        User user = User.createUser(request.getLoginId(), request.getNickname(), hashedPwd,
                request.getHint(), request.getName(), request.getAnswer(), Auth.USER);
        userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLoginId(), request.getPassword()));

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User loginUser = principal.getUser();

        String jwt = jwtUtils.createAccessToken(authentication);
        String refresh = jwtUtils.createRefreshToken(authentication);
        TokenPair tokenPair = TokenPair.createTokenPair(jwt, refresh, loginUser);

        tokenPairRepository.findByUserId(loginUser.getId())
                .ifPresentOrElse(
                        (findTokenPair) -> findTokenPair.updateToken(jwt, refresh),
                        () -> tokenPairRepository.save(tokenPair)
                );

        LoginResponse response = new LoginResponse(loginUser);
        response.setAccessToken(jwtUtils.addPrefix(jwt));
        response.setRefreshToken(jwtUtils.addPrefix(refresh));

        return response;
    }

    public LoginResponse refresh(TokenRefreshRequest request) {

        String requestRefreshTokenToken = request.getRefreshToken().replace("Bearer ", "");

        // 요청으로 받은 refreshToken 유효한지 확인
        jwtUtils.validateToken(requestRefreshTokenToken);

        // 이전에 받았던 refreshToken과 일치하는지 확인(tokenPair 유저당 하나로 유지)
        Long userId = jwtUtils.getUserIdFromToken(requestRefreshTokenToken);
        TokenPair findTokenPair = tokenPairRepository.findByUserIdWithUser(userId)
                .orElseThrow(() -> new JWTVerificationException("유효하지 않은 토큰입니다."));

        if (!requestRefreshTokenToken.equals(findTokenPair.getRefreshToken())) {
            throw new JWTVerificationException("중복 로그인 되었습니다.");
        }

        // 이전에 발급했던 AccessToken 만료되지 않았다면 refreshToken 탈취로 판단
        // TokenPair 삭제 -> 다시 로그인 해야됨
        if (jwtUtils.isExpired(findTokenPair.getAccessToken())) {
            // refreshToken 유효하고, AccessToken 정상적으로 Expired 상태일때
            PrincipalDetails principal = new PrincipalDetails(findTokenPair.getUser());
            Authentication authentication = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());

            String jwt = jwtUtils.createAccessToken(authentication);
            String refresh = jwtUtils.createRefreshToken(authentication);
            findTokenPair.updateToken(jwt, refresh);

            LoginResponse response = new LoginResponse(principal.getUser());
            response.setAccessToken(jwtUtils.addPrefix(jwt));
            response.setRefreshToken(jwtUtils.addPrefix(refresh));
            return response;

        } else {
            // accessToken이 아직 만료되지 않은 상태 -> 토큰 탈취로 판단 -> delete tokenPair
            tokenPairRepository.delete(findTokenPair);
            return null;
        }
    }

    public UserInfoResponse getUserInfo(Long userId) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorResult.USER_NOT_FOUND));
        return new UserInfoResponse(findUser);
    }

    public String getHint(String loginId) {
        User findUser = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UserException(UserErrorResult.USER_NOT_FOUND));
        return findUser.getAnswer();
    }

    public String answerToHint(AnswerToHintRequest request) {
        User findUser = userRepository.findByLoginIdAndAnswer(request.getLoginId(), request.getAnswer())
                .orElseThrow(() -> new UserException(UserErrorResult.USER_NOT_FOUND));
        String tempToken = jwtUtils.createAccessToken(findUser.getId());
        return jwtUtils.addPrefix(tempToken);
    }

    public void findPassword(Long userId, FindPasswordRequest request) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorResult.USER_NOT_FOUND));
        if (!Objects.equals(request.getPassword(), request.getPasswordCheck())) {
            throw new UserException(UserErrorResult.NOT_MATCH_PASSWORD_CHECK);
        }
        String password = passwordEncoder.encode(request.getPassword());
        findUser.changePassword(password, request.getNewHint(), request.getNewAnswer());
    }
}
