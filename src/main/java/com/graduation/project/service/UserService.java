package com.graduation.project.service;

import com.graduation.project.domain.User;
import com.graduation.project.dto.UserGetResponse;
import com.graduation.project.error.UserErrorResult;
import com.graduation.project.error.UserException;
import com.graduation.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void removeUser(Long userId){
        Optional<User> optionalUsers = userRepository.findById(userId);
        optionalUsers.orElseThrow(() -> new UserException(UserErrorResult.USER_NOT_FOUND));
        userRepository.deleteById(userId);
    }
}
