package com.example.offco.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.offco.domain.Invite;

public interface InviteRepository extends JpaRepository<Invite, Long> {
    // @Override
    Optional<Invite> findByToken(String inviteUrl);
}