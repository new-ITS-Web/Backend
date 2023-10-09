package com.itsweb.backend.post.dto;

import com.itsweb.backend.post.domain.Post;
import com.itsweb.backend.post.like.LikeEntity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostResponseDTO {
    private Long id;
    private String title;
    private String content;
    private Long likes;
    private List<Long> likeList = new ArrayList<>();

    public PostResponseDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.likes = (long) post.getLikeEntity().size();
        for (LikeEntity likeEntity : post.getLikeEntity()) {
            this.likeList.add(likeEntity.getMember().getId());
        }
    }
}
