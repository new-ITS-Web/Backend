package com.itsweb.backend.post.repository;

import com.itsweb.backend.post.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
