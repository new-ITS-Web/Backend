package com.itsweb.backend.post.repository;

import com.itsweb.backend.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {

}
