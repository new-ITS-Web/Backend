package com.itsweb.backend.post.controller;

import com.itsweb.backend.post.dto.CommentRequestDTO;
import com.itsweb.backend.post.dto.CommentResponseDTO;
import com.itsweb.backend.post.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    //특정 게시글 댓글목록 조회
    @GetMapping("/post/{postId}/comment")
    public List<CommentResponseDTO> getComment(@PathVariable("postId") Long postId){
        return commentService.findAllComment(postId);
    }

    // 댓글 작성
    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<CommentResponseDTO> createComment(@PathVariable("postId") Long postId, @RequestBody CommentRequestDTO commentRequestDTO){
        CommentResponseDTO commentResponseDTO = commentService.saveComment(postId, commentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponseDTO);
    }

    // 댓글 삭제
    @DeleteMapping("/post/{postId}/comment")
    public void removeComment(@PathVariable("postId") Long postId){

    }
}
