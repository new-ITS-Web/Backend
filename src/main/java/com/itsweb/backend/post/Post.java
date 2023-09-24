package com.itsweb.backend.post;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Post extends TimeStamp{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    public void writePost(PostWriteDTO postWriteDTO){
        this.title = postWriteDTO.getTitle();
        this.content = postWriteDTO.getContent();
    }
    public void updatePost(PostEditDTO postEditDTO){
        this.title = postEditDTO.getTitle();
        this.content = postEditDTO.getContent();
    }
}
