package com.graduation.project.repository;

import com.graduation.project.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

}
