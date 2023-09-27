package com.itsweb.backend.post;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    //게시글 전체조회
    @GetMapping("/post")
    public List<Post> getAllPost(){
        List<Post> posts = postService.findAllPost();
        return posts;
    }
    //게시글 상세조회
    @GetMapping("/post/{id}")
    public Post getDetailPost(@PathVariable("id") Long id) throws NoSuchElementException {
        Post post = postService.findPostDetail(id);
        return post;
    }
//    //게시글 작성
    @PostMapping("/post")
    public ResponseEntity<?> writePost(@Validated @RequestBody PostWriteDTO postWriteDTO){
        Post post = new Post();
        post.writePost(postWriteDTO);
        postService.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post.getId());
    }
//    //게시글 삭제
    @DeleteMapping("/post/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id){
        postService.deleteById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }
//    //게시글 수정
    @PutMapping("/post/{id}")
    public ResponseEntity<?> editPost(@Validated @RequestBody PostEditDTO postEditDTO, @PathVariable Long id){
        Post post = postService.findPostDetail(id);
        post.updatePost(postEditDTO);
        postService.save(post);
        return ResponseEntity.ok().body(id);
    }
}
