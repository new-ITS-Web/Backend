package com.itsweb.backend.post;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostValidationService postValidationService;
    //게시글 전체조회
    @GetMapping("/post")
    public Page<Post> getAllPost(Pageable pageable){
        Page<Post> posts = postService.findAllPost(pageable);
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
    public ResponseEntity<?> writePost(@Validated @RequestBody PostWriteDTO postWriteDTO,BindingResult bindingResult){
        String validationErrors = postValidationService.getValidationErrors(bindingResult);
        if(validationErrors!=null){
            return ResponseEntity.badRequest().body(validationErrors);
        }
        Post post = new Post();
        post.writePost(postWriteDTO);
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
    public ResponseEntity<?> editPost(@Validated @RequestBody PostEditDTO postEditDTO, @PathVariable Long id){
        Post post = postService.findPostDetail(id);
        post.updatePost(postEditDTO);
        postService.save(post);
        return ResponseEntity.ok().body(id);
    }
}
