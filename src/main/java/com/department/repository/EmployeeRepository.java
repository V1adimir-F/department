package com.department.repository;

import com.department.model.Employee;
import com.department.model.Post;
import com.department.model.Rank;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Vladimir F. 13.09.2022 13:35
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @NotNull
    Page<Employee> findAll(@NotNull Pageable pageable);

    @NotNull
    Page<Employee> findAllByRank(@NotNull Pageable pageable, Rank rank);

    @NotNull
    Page<Employee> findAllByPost(@NotNull Pageable pageable, Post post);

    @NotNull
    Page<Employee> findAllByRankAndPost(@NotNull Pageable pageable, Rank rank, Post post);

    Boolean existsByPersonalNumber(String personalNumber);

    Optional<Employee> findByPersonalNumber(String personalNumber);

    long countAllByRankAndPost(Rank rank, Post post);

    long countAllByRank(Rank rank);

    long countAllByPost(Post post);
}
