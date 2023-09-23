package com.itsweb.backend.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> findAllPost(){
        List<Post> all = postRepository.findAll();
        return all;
    }

    public Post findPostDetail(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 포스트가 없습니다"));
        return post;
    }
    public void save(Post post){
        postRepository.save(post);
    }
    public void deleteById(Long id){
        postRepository.deleteById(id);
    }
}
