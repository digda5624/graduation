package com.graduation.project.repository;

import com.graduation.project.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void UserRepository가Null아님() {
        assertThat(userRepository).isNotNull();
    }

    @Test
    void User등록() {
        // given
        User user = User.builder()
                .id(1L)
                .loginId("testID")
                .nickname("test")
                .name("test")
                .password("1234")
                .build();
        // when
        User savedUser = userRepository.save(user);
        // then
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getName()).isEqualTo("test");
        assertThat(savedUser.getLoginId()).isEqualTo("testID");

    }
    @Test
    void User조회_로그인아이디() {
        // given
        User user = User.builder()
                .id(1L)
                .loginId("testID")
                .nickname("test")
                .name("test")
                .password("1234")
                .build();
        // when
        User savedUser = userRepository.save(user);
        User findUser = userRepository.findByLoginId("testID").orElse(null);
        // then
        assertThat(findUser).isNotNull();
        assertThat(findUser.getId()).isNotNull();
        assertThat(findUser.getLoginId()).isEqualTo(savedUser.getLoginId());
        assertThat(findUser).isEqualTo(savedUser);
    }

    @Test
    void User삭제() {
        // given
        User user = User.builder().build();
        User savedUser = userRepository.save(user);
        // when
        userRepository.deleteById(savedUser.getId());
        List<User> users = userRepository.findAll();
        // then
        assertThat(users.size()).isEqualTo(0);
    }

    @Nested
    @DisplayName("findByLoginIdOrNickname")
    class findByLoginIdOrNickname {
        @Test
        public void findByLoginIdOrNickname() throws Exception {
            //given
            User user1 = User.builder()
                    .nickname("nickname").build();
            User user2 = User.builder()
                    .loginId("loginId").build();
            userRepository.save(user1);
            userRepository.save(user2);
            //when
            Optional<User> findByLoginIdUser = userRepository.findByLoginIdOrNickname("loginId", "asd");
            Optional<User> findByNicknameUser = userRepository.findByLoginIdOrNickname("asd", "nickname");
            Optional<User> notingUser = userRepository.findByLoginIdOrNickname("asd", "asd");
            //then
            assertThat(findByLoginIdUser).isNotEmpty();
            assertThat(findByLoginIdUser.get().getLoginId()).isEqualTo(user2.getLoginId());
            assertThat(findByNicknameUser).isNotEmpty();
            assertThat(findByNicknameUser.get().getNickname()).isEqualTo(user1.getNickname());
            assertThat(notingUser).isEmpty();
        }

    }

}
