package com.itsweb.backend.post.service;

import com.itsweb.backend.member.Member;
import com.itsweb.backend.member.MemberRepository;
import com.itsweb.backend.post.domain.Comment;
import com.itsweb.backend.post.domain.Post;
import com.itsweb.backend.post.dto.CommentRequestDTO;
import com.itsweb.backend.post.dto.CommentResponseDTO;
import com.itsweb.backend.post.repository.CommentRepository;
import com.itsweb.backend.post.repository.PostRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    public CommentResponseDTO saveComment(Long postId, CommentRequestDTO commentRequestDTO){
        Member member= memberRepository.findByUsername(commentRequestDTO.getWriter());
        Post post = postRepository.findById(postId).orElseThrow(() -> new NoSuchElementException("게시글이 존재하지 않습니다."));
        Comment comment = new Comment();
        comment.writeComment(commentRequestDTO,post,member);
        commentRepository.save(comment);
        CommentResponseDTO dto = new CommentResponseDTO(comment);
        return dto;
    }

    public List<CommentResponseDTO> findAllComment(Long postId) {
        List<CommentResponseDTO> tmp = new ArrayList<>();
        for (Comment comment : commentRepository.findAll()) {
            tmp.add(new CommentResponseDTO(comment));
        };
        return tmp;
    }
}
