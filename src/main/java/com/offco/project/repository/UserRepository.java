package com.offco.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.offco.project.domain.User;

public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username); // user 존재 여부 확인
}
