package com.graduation.project.repository;

import com.graduation.project.domain.TokenPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TokenPairRepository extends JpaRepository<TokenPair, Long> {

    @Query("select t " +
            "from TokenPair t " +
            "where t.user.id = :userId")
    Optional<TokenPair> findByUserId(@Param("userId") Long userId);

    @Query("select t " +
            "from TokenPair t " +
            "join fetch t.user u " +
            "where u.id = :userId")
    Optional<TokenPair> findByUserIdWithUser(Long userId);
}
