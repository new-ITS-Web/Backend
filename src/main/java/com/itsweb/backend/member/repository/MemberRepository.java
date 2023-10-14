package com.itsweb.backend.member.repository;

import com.itsweb.backend.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUserId(String userId);

    boolean existsByUserId(String userId);

    boolean existsByUsername(String username);
}
