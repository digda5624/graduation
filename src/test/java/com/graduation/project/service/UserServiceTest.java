package com.graduation.project.service;

import com.graduation.project.domain.User;
import com.graduation.project.dto.UserGetResponse;
import com.graduation.project.dto.UserInfoResponse;
import com.graduation.project.error.UserErrorResult;
import com.graduation.project.error.UserException;
import com.graduation.project.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

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

    private User user() {
        return User.builder()
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

    @Test
    void User삭제실패_DB에없음() {
        // given
        doReturn(Optional.empty()).when(userRepository).findById(userId);
        // when
        UserException result = assertThrows(UserException.class, () -> target.removeUser(userId));
        // then
        assertThat(result.getErrorResult()).isEqualTo(UserErrorResult.USER_NOT_FOUND);
    }

    @Test
    void User삭제성공() {
        // given
        doReturn(Optional.of(user())).when(userRepository).findById(userId);
        // when
        target.removeUser(userId);
        // then
    }

    @Nested
    @DisplayName("사용자 정보 조회")
    class getUserInfo {
        @Test
        public void 정보조회실패() throws Exception {
            //given
            User user = user();
            doReturn(Optional.empty())
                    .when(userRepository)
                    .findById(userId);

            //when
            UserException result = assertThrows(UserException.class, () -> {
                target.getUserInfo(user.getId());
            });
            //then
            assertThat(result.getErrorResult()).isEqualTo(UserErrorResult.USER_NOT_FOUND);
        }

        @Test
        public void 정보조회성공() throws Exception {
            //given
            User user = user();
            doReturn(Optional.of(user))
                    .when(userRepository)
                    .findById(userId);
            //when
            UserInfoResponse result = target.getUserInfo(user.getId());
            //then
            assertThat(result.getNickname()).isEqualTo(user.getNickname());
        }
    }

    @Nested
    @DisplayName("사용자 비밀번호 찾기용 힌트 조회")
    class getHint {

        @Test
        public void 힌트조회실패() throws Exception {
            //given
            User user = user();
            doReturn(Optional.empty())
                    .when(userRepository)
                    .findByLoginId(loginId);
            //when
            UserException result = assertThrows(UserException.class, () -> {
                target.getHint(user.getLoginId());
            });
            //then
            assertThat(result.getErrorResult()).isEqualTo(UserErrorResult.USER_NOT_FOUND);
        }

        @Test
        public void 힌트조회성공() throws Exception {
            //given
            User user = user();
            doReturn(Optional.of(user))
                    .when(userRepository)
                    .findByLoginId(loginId);
            //when
            String result = target.getHint(user.getLoginId());
            //then
            assertThat(result).isEqualTo(user.getHint());
        }
    }
}
