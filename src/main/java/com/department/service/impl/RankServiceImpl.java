package com.department.service.impl;

import com.department.dto.request.RankRequestDTO;
import com.department.dto.response.RankResponseDTO;
import com.department.exception.BadRequestException;
import com.department.model.Rank;
import com.department.model.Status;
import com.department.repository.RankRepository;
import com.department.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Vladimir F. 05.11.2022 22:46
 */

@Service
public class RankServiceImpl implements RankService {

    private final RankRepository rankRepository;

    @Autowired
    public RankServiceImpl(RankRepository rankRepository) {
        this.rankRepository = rankRepository;
    }

    @Override
    public List<RankResponseDTO> getAllRanks() {
        return rankRepository.findAll()
                .stream()
                .map(RankResponseDTO::fromRank)
                .toList();
    }

    @Override
    public RankResponseDTO createRank(RankRequestDTO rankRequestDTO) throws BadRequestException {
        if (rankRequestDTO.rankName() == null || rankRequestDTO.rankName().isEmpty()) {
            throw new BadRequestException("Not enough data for creating rank");
        }

        if (rankRepository.existsByRankName(rankRequestDTO.rankName())) {
            throw new BadRequestException("Rank " + rankRequestDTO.rankName() + " already exists");
        }

        Rank rank = new Rank();
        rank.setRankName(rankRequestDTO.rankName());
        rank.setCreated(LocalDateTime.now());
        rank.setUpdated(LocalDateTime.now());
        rank.setStatus(Status.ACTIVE);
        rankRepository.save(rank);
        return RankResponseDTO.fromRank(rank);
    }

    @Override
    public RankResponseDTO updateRank(RankRequestDTO rankRequestDTO, Long rankId) throws BadRequestException {
        Rank rank = rankRepository
                .findById(rankId)
                .orElseThrow(() -> new BadRequestException("Incorrect data for updating rank"));

        Optional<Rank> sameRank = rankRepository.findByRankName(rankRequestDTO.rankName());
        if (sameRank.isPresent() && !sameRank.get().equals(rank)) {
            throw new BadRequestException("Rank with rank name " + rankRequestDTO.rankName() + " already exists");
        }

        rank.setRankName(rankRequestDTO.rankName());
        rank.setUpdated(LocalDateTime.now());
        rankRepository.save(rank);
        return RankResponseDTO.fromRank(rank);
    }

    @Override
    public RankResponseDTO deleteRank(Long rankId) throws BadRequestException {
        Rank rank = rankRepository
                .findById(rankId)
                .orElseThrow(() -> new BadRequestException("Incorrect data for deleting rank"));
        rankRepository.delete(rank);
        return RankResponseDTO.fromRank(rank);
    }
}
