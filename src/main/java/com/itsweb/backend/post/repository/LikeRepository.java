package com.itsweb.backend.post.repository;

import com.itsweb.backend.member.Member;
import com.itsweb.backend.post.domain.Post;
import com.itsweb.backend.post.domain.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity,Long> {
    LikeEntity findByPostAndMember(Post post, Member member);
    boolean existsLikeEntityByMemberId(Long memberId);
}
