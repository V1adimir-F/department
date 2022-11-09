package com.department.service;

import com.department.dto.request.RankRequestDTO;
import com.department.dto.response.RankResponseDTO;

import java.util.List;

/**
 * @author Vladimir F. 05.11.2022 22:36
 */

public interface RankService {

    List<RankResponseDTO> getAllRanks();

    RankResponseDTO createRank(RankRequestDTO rankRequestDTO);

    RankResponseDTO updateRank(RankRequestDTO rankRequestDTO, Long rankId);

    RankResponseDTO deleteRank(Long rankId);
}
