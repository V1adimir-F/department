package com.department.service;

import com.department.dto.request.PostRequestDTO;
import com.department.dto.response.PostResponseDTO;

import java.util.List;

/**
 * @author Vladimir F. 05.11.2022 23:23
 */

public interface PostService {

    List<PostResponseDTO> getAllPosts();

    PostResponseDTO createPost(PostRequestDTO postRequestDTO);

    PostResponseDTO updatePost(PostRequestDTO postRequestDTO, Long postId);

    PostResponseDTO deletePost(Long postId);
}
