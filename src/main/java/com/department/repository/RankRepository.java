package com.department.repository;

import com.department.model.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Vladimir F. 05.11.2022 22:35
 */

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {

    Boolean existsByRankName(String rankName);

    Optional<Rank> findByRankName(String rankName);
}
