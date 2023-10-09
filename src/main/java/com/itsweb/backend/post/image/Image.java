package com.itsweb.backend.post.image;

import com.itsweb.backend.exception.UnsupportedImageFormatException;
import com.itsweb.backend.post.domain.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Arrays;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Column(nullable = false)
    private String uniqueName;

    @Column(nullable = false)
    private String originName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id",nullable = false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Post post;

    public Image(String originName, Post post){
        this.originName= originName;
        this.uniqueName = generateUniqueName(extractExtension(originName));
        this.post=post;
    }

    private final static String supportedExtension[] = {"jpg", "jpeg", "gif", "bmp", "png"};
    public Image(String originName) {
        // 세팅
        this.originName = originName;
        this.uniqueName = generateUniqueName(extractExtension(originName));
    }

    public void initPost(Post post) {
        if(this.post == null) {
            this.post = post;
        }
    }

    private String generateUniqueName(String extension) {
        return UUID.randomUUID().toString() + "." + extension;
    }

    private String extractExtension(String originName) {
        try {
            String ext = originName.substring(originName.lastIndexOf(".") + 1);
            if(isSupportedFormat(ext)) return ext;
        } catch (StringIndexOutOfBoundsException e) { }
        throw new UnsupportedImageFormatException();
    }

    private boolean isSupportedFormat(String ext) {
        return Arrays.stream(supportedExtension).anyMatch(e -> e.equalsIgnoreCase(ext));
    }

}
