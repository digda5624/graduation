package com.graduation.project.service;

import com.graduation.project.domain.Users;
import com.graduation.project.dto.UserGetResponse;
import com.graduation.project.error.UserErrorResult;
import com.graduation.project.error.UserException;
import com.graduation.project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private final String name = "test";
    private final String nickname = "test";
    private final String loginId = "testId";
    private final Long userId = 1L;

    @InjectMocks
    private UserService target;
    @Mock
    private UserRepository userRepository;

    private Users user() {
        return Users.builder()
                .id(userId)
                .name(name)
                .nickname(nickname)
                .loginId(loginId)
                .build();
    }
    @Test
    void User조회실패_존재하지않음() {
        // given
        doReturn(Optional.empty()).when(userRepository).findById(userId);
        // when
        UserException result = assertThrows(UserException.class, () -> target.getUser(userId));
        // then
        assertThat(result.getErrorResult()).isEqualTo(UserErrorResult.USER_NOT_FOUND);
    }

    @Test
    void User조회성공() {
        // given
        doReturn(Optional.of(user())).when(userRepository).findById(userId);
        // when
        UserGetResponse result = target.getUser(userId);
        // then
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getNickname()).isEqualTo(nickname);
        assertThat(result.getLoginId()).isEqualTo(loginId);

    }
}
