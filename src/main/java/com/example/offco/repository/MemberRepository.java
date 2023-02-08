package com.example.offco.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.offco.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByMemberId(String username);

    Boolean existsByMemberId(String memberId);
}