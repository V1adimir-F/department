package com.department.service.impl;

import com.department.dto.request.PostRequestDTO;
import com.department.dto.response.PostResponseDTO;
import com.department.exception.BadRequestException;
import com.department.model.Post;
import com.department.model.Status;
import com.department.repository.PostRepository;
import com.department.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Vladimir F. 05.11.2022 23:25
 */

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostResponseDTO> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(PostResponseDTO::fromPost)
                .toList();
    }

    @Override
    public PostResponseDTO createPost(PostRequestDTO postRequestDTO) throws BadRequestException {
        if (postRequestDTO.postName() == null || postRequestDTO.postName().isEmpty()) {
            throw new BadRequestException("Not enough data for creating post");
        }

        if (postRepository.existsByPostName(postRequestDTO.postName())) {
            throw new BadRequestException("Post " + postRequestDTO.postName() + " already exists");
        }

        Post post = new Post();
        post.setPostName(postRequestDTO.postName());
        post.setCreated(LocalDateTime.now());
        post.setUpdated(LocalDateTime.now());
        post.setStatus(Status.ACTIVE);
        postRepository.save(post);
        return PostResponseDTO.fromPost(post);
    }

    @Override
    public PostResponseDTO updatePost(PostRequestDTO postRequestDTO, Long postId) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new BadRequestException("Incorrect data for updating post"));

        Optional<Post> samePost = postRepository.findByPostName(postRequestDTO.postName());
        if (samePost.isPresent() && !samePost.get().equals(post)) {
            throw new BadRequestException("Post with post name " + postRequestDTO.postName() + " already exists");
        }

        post.setPostName(postRequestDTO.postName());
        post.setUpdated(LocalDateTime.now());
        postRepository.save(post);
        return PostResponseDTO.fromPost(post);
    }

    @Override
    public PostResponseDTO deletePost(Long postId) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new BadRequestException("Incorrect data for deleting post"));
        postRepository.delete(post);
        return PostResponseDTO.fromPost(post);
    }
}
