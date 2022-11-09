package com.department.controller;

import com.department.dto.request.RankRequestDTO;
import com.department.dto.response.RankResponseDTO;
import com.department.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Vladimir F. 05.11.2022 23:01
 */

@RestController
@RequestMapping(value = "/department/rank")
@CrossOrigin(origins = "${client.host}")
public class RankController {

    private final RankService rankService;

    @Autowired
    public RankController(RankService rankService) {
        this.rankService = rankService;
    }

    @GetMapping()
    public ResponseEntity<?> getAll() {
        List<RankResponseDTO> rankResponseDTOList = rankService.getAllRanks();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(rankResponseDTOList);
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody RankRequestDTO rankRequestDTO) {
        RankResponseDTO rankResponseDTO = rankService.createRank(rankRequestDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(rankResponseDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long rankId,
                                    @RequestBody RankRequestDTO rankRequestDTO) {
        RankResponseDTO rankResponseDTO = rankService.updateRank(rankRequestDTO, rankId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(rankResponseDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long rankId) {
        RankResponseDTO rankResponseDTO = rankService.deleteRank(rankId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(rankResponseDTO);
    }
}
