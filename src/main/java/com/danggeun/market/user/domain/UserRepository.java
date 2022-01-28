package com.danggeun.market.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT U FROM User U JOIN FETCH U.authorities WHERE U.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);
}
