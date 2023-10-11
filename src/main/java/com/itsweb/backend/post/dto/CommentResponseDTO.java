package com.itsweb.backend.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itsweb.backend.post.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDTO {
    private Long commentId;
    private Long postId;
    private String content;
    private String writer;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdDate;

    public CommentResponseDTO(Comment comment){
        this.commentId = comment.getId();
        this.postId = comment.getPost().getId();
        this.content = comment.getContent();
        this.writer = comment.getMember().getUsername();
        this.createdDate = comment.getRegDate();
    }
}
