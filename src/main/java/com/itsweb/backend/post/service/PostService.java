package com.itsweb.backend.post.service;

import com.itsweb.backend.member.Member;
import com.itsweb.backend.member.MemberRepository;
import com.itsweb.backend.post.domain.Post;
import com.itsweb.backend.post.dto.PostAllResponseDTO;
import com.itsweb.backend.post.dto.PostResponseDTO;
import com.itsweb.backend.post.domain.LikeEntity;
import com.itsweb.backend.post.repository.LikeRepository;
import com.itsweb.backend.post.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final LikeRepository likeRepository;
    public List<PostAllResponseDTO> findAllPost(){
        List<Post> all = postRepository.findAll();
        List<PostAllResponseDTO> dto = new ArrayList<>();
        for (Post post : all) {
            PostAllResponseDTO tmp = new PostAllResponseDTO(post);
            dto.add(tmp);
        }
        return dto;
    }

    public Post findPostDetail(Long id){
        return postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 포스트가 없습니다"));
    }
    public PostResponseDTO findPostDetailReturnDTO(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 포스트가 없습니다"));
        PostResponseDTO dto = new PostResponseDTO(post);
        return dto;
    }

    public void save(Post post){
        postRepository.save(post);
    }
    public void deleteById(Long id){
        postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 포스트가 없습니다"));
        postRepository.deleteById(id);
    }
    public long addLikeToPost(Long postId, Long memberId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다."));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("유저가 없습니다."));
        LikeEntity like = new LikeEntity();
        like.setPost(post);
        like.setMember(member);

        likeRepository.save(like);
        return likeRepository.count();

    }

    public long removeLikeFromPost(Long postId, Long memberId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다."));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("유저가 없습니다."));

        LikeEntity like = likeRepository.findByPostAndMember(post, member);
        if (like != null) {
            likeRepository.delete(like);
        }
        long sum = likeRepository.count();
        return sum;
    }
    public boolean isDuplicateLikes(Long memberId) {
       return likeRepository.existsLikeEntityByMemberId(memberId);
    }
}
