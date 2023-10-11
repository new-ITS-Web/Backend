package com.itsweb.backend.post.domain;

import com.itsweb.backend.post.dto.PostEditDTO;
import com.itsweb.backend.post.dto.PostWriteDTO;
import com.itsweb.backend.member.Member;
import com.itsweb.backend.post.TimeStamp;
import com.itsweb.backend.post.image.Image;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Post extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "post")
    private List<LikeEntity> likeEntity = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Image> images;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void writePost(PostWriteDTO postWriteDTO){
        this.title = postWriteDTO.getTitle();
        this.content = postWriteDTO.getContent();
    }
    public void updatePost(PostEditDTO postEditDTO){
        this.title = postEditDTO.getTitle();
        this.content = postEditDTO.getContent();
    }
}
