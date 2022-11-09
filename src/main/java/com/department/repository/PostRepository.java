package com.department.repository;

import com.department.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Vladimir F. 05.11.2022 23:18
 */

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Boolean existsByPostName(String postName);

    Optional<Post> findByPostName(String postName);
}
