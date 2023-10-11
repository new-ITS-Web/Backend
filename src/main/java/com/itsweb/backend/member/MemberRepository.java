package com.itsweb.backend.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUserId(String userId);
    Member findByUsername(String username);
}
