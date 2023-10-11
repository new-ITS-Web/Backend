package com.itsweb.backend.post.dto;

import com.itsweb.backend.post.domain.Comment;
import com.itsweb.backend.post.domain.Post;
import com.itsweb.backend.post.domain.LikeEntity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostResponseDTO {
    private Long post_id;
    private String title;
    private String content;
    private Long likes;
    private List<Long> likeList = new ArrayList<>();
    private int comments;
    private List<CommentResponseDTO> commentList= new ArrayList<>();

    public PostResponseDTO(Post post) {
        this.post_id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.likes = (long) post.getLikeEntity().size();
        for (LikeEntity likeEntity : post.getLikeEntity()) {
            this.likeList.add(likeEntity.getMember().getId());
        }
        this.comments = post.getComments().size();
        for (Comment comment : post.getComments()) {
            commentList.add(new CommentResponseDTO(comment));
        }
    }
}
