package com.itsweb.backend.post.controller;

import com.itsweb.backend.post.domain.Post;
import com.itsweb.backend.post.dto.PostEditDTO;
import com.itsweb.backend.post.dto.PostLikeDTO;
import com.itsweb.backend.post.dto.PostResponseDTO;
import com.itsweb.backend.post.service.PostService;
import com.itsweb.backend.post.dto.PostWriteDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public List<PostResponseDTO> getAllPost(){
        return postService.findAllPost();

    }
    // 게시글 상세조회
    @GetMapping("/post/{id}")
    public PostResponseDTO getDetailPost(@PathVariable("id") Long id) throws NoSuchElementException {
          return postService.findPostDetailReturnDTO(id);
    }
    // 게시글 작성
    @PostMapping("/post")
    public ResponseEntity<?> writePost(@Valid @RequestBody PostWriteDTO postWriteDTO){
        Post post = new Post();
        post.writePost(postWriteDTO);
        postService.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post.getId());
    }
    //게시글 삭제
    @DeleteMapping("/post/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        postService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
    //게시글 수정
    @PutMapping("/post/{id}")
    public ResponseEntity<?> editPost(@Valid @RequestBody PostEditDTO postEditDTO, @PathVariable Long id)  throws NoSuchElementException{
        Post post = postService.findPostDetail(id);
        post.updatePost(postEditDTO);
        postService.save(post);
        return ResponseEntity.ok().body(id);
    }
    //좋아요
    @PostMapping("/post/{postId}/like")
    public ResponseEntity<?> addLike(@PathVariable Long postId, @RequestBody PostLikeDTO postLikeDTO) {
        if(postService.isDuplicateLikes(postLikeDTO.getMemberId())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 좋아요를 누른 사용자입니다.");
        }
        long totalLikes = postService.addLikeToPost(postId, postLikeDTO.getMemberId());
        return ResponseEntity.ok(totalLikes);
    }
    //좋아요 취소
    @DeleteMapping("/post/{postId}/like")
    public ResponseEntity<Long> removeLike(@PathVariable Long postId, @RequestBody PostLikeDTO postLikeDTO) {
       long totalLikes = postService.removeLikeFromPost(postId, postLikeDTO.getMemberId());
        return ResponseEntity.ok(totalLikes);
    }
}
