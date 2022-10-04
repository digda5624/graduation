package com.graduation.project.repository;

import com.graduation.project.domain.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

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
    void User조회() {
        // given
        Users user = Users.builder()
                .id(1L)
                .loginId("testID")
                .nickname("test")
                .name("test")
                .password("1234")
                .build();
        // when
        Users savedUser = userRepository.save(user);
        Optional<Users> findUser = userRepository.findById(1L);
        // then
        assertTrue(findUser.isPresent());
        assertThat(findUser.get().getId()).isNotNull();
        assertThat(findUser.get()).isEqualTo(savedUser);
    }

}
