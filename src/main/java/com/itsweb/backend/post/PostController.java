package com.itsweb.backend.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    //게시글 전체조회
    @GetMapping("/post")
    public List<Post> getAllPost(){
        List<Post> list = postService.findAllPost();
        return list;
    }
    //게시글 상세조회
    @GetMapping("/post/{id}")
    public Post getDetailPost(@PathVariable("id") Long id) throws NoSuchElementException {
        Post post = postService.findPostDetail(id);
        return post;
    }
//    //게시글 작성
    @PostMapping("/post")
    public ResponseEntity<?> writePost(@RequestBody PostWriteDTO postWriteDTO){
        Post post = new Post();
        post.updatePost(postWriteDTO.getTitle(), postWriteDTO.getContent());
        postService.save(post);
        return ResponseEntity.ok().body(post.getId());
    }
//    //게시글 삭제
    @DeleteMapping("/post/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id){
        postService.deleteById(id);
        return ResponseEntity.ok().body(id);
    }
//    //게시글 수정
    @PutMapping("/post/{id}")
    public ResponseEntity<?> editPost(@RequestBody PostEditDTO postEditDTO, @PathVariable Long id){
        Post post = postService.findPostDetail(id);
        post.updatePost(postEditDTO.getTitle(), postEditDTO.getContent());
        postService.save(post);
        return ResponseEntity.ok().body(id);
    }
}
