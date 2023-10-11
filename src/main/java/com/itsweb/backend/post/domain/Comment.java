package com.itsweb.backend.post.domain;

import com.itsweb.backend.member.Member;
import com.itsweb.backend.post.TimeStamp;
import com.itsweb.backend.post.dto.CommentRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void writeComment(CommentRequestDTO commentRequestDTO,Post post,Member member) {
        this.content = commentRequestDTO.getContent();
        this.post = post;
        this.member = member;
    }
}
