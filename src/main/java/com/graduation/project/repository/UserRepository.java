package com.graduation.project.repository;


import com.graduation.project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String loginId);

    Optional<User> findByLoginIdOrNickname(String loginId, String nickname);

    Optional<User> findByLoginIdAndAnswer(String loginId, String answer);
}
