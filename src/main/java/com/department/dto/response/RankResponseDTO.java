package com.department.dto.response;

import com.department.model.Rank;

import java.io.Serializable;

/**
 * @author Vladimir F. 05.11.2022 22:37
 */

public record RankResponseDTO(Long id, String rankName) implements Serializable {

    public static RankResponseDTO fromRank(Rank rank) {
        return new RankResponseDTO(
                rank.getId(),
                rank.getRankName()
        );
    }
}
