package com.offco.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.offco.project.domain.Invite;

public interface InviteRepository extends JpaRepository<Invite, Long> {
    // @Override
    Optional<Invite> findByInviteUrl(String inviteUrl);
}