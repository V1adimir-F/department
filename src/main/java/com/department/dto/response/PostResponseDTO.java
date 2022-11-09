package com.department.dto.response;

import com.department.model.Post;

import java.io.Serializable;

/**
 * @author Vladimir F. 05.11.2022 23:21
 */

public record PostResponseDTO(Long id, String postName) implements Serializable {

    public static PostResponseDTO fromPost(Post post) {
        return new PostResponseDTO(
                post.getId(),
                post.getPostName()
        );
    }
}
