package com.graduation.project.service;

import com.graduation.project.domain.User;
import com.graduation.project.domain.enumtype.Auth;
import com.graduation.project.dto.SignupRequest;
import com.graduation.project.dto.UserGetResponse;
import com.graduation.project.error.UserErrorResult;
import com.graduation.project.error.UserException;
import com.graduation.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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

    public void signup(SignupRequest request) {
        if (!Objects.equals(request.getPassword(), request.getPasswordCheck()))
            throw new UserException(UserErrorResult.NOT_MATCH_PASSWORD_CHECK);

        userRepository.findByLoginId(request.getLoginId())
                .ifPresent((user) -> {
                    throw new UserException(UserErrorResult.ALREADY_LOGINID_OR_NICKNAME_EXIST);
                });

        User user = User.createUser(request.getLoginId(), request.getNickname(), request.getPassword(),
                request.getHint(), request.getName(), request.getAnswer(), Auth.USER);
        userRepository.save(user);
    }
}
