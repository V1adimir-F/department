package com.department.controller;

import com.department.dto.request.PostRequestDTO;
import com.department.dto.response.PostResponseDTO;
import com.department.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Vladimir F. 06.11.2022 13:17
 */

@RestController
@RequestMapping(value = "/department/post")
@CrossOrigin(origins = "${client.host}")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping()
    public ResponseEntity<?> getAll() {
        List<PostResponseDTO> postResponseDTOList = postService.getAllPosts();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postResponseDTOList);
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody PostRequestDTO postRequestDTO) {
        PostResponseDTO postResponseDTO = postService.createPost(postRequestDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postResponseDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long postId,
                                    @RequestBody PostRequestDTO postRequestDTO) {
        PostResponseDTO postResponseDTO = postService.updatePost(postRequestDTO, postId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postResponseDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long postId) {
        PostResponseDTO postResponseDTO = postService.deletePost(postId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postResponseDTO);
    }
}
