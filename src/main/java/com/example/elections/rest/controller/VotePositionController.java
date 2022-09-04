package com.example.elections.rest.controller;

import com.example.elections.model.VotePosition;
import com.example.elections.rest.dtos.VotePositionDto;
import com.example.elections.rest.exception.ResourceNotFound;
import com.example.elections.rest.mapper.VotePositionMapper;
import com.example.elections.service.VotePositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class VotePositionController {


    @Autowired
    private VotePositionMapper votePositionMapper;

    @Autowired
    private VotePositionService votePositionService;

    @GetMapping
    public ResponseEntity<List<VotePositionDto>> getAllVotePositions() {
        List<VotePositionDto> votePositionDtoList = votePositionMapper.
                toVotePositionDtos(votePositionService.getAllVotePositions());
        return ResponseEntity.ok(votePositionDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VotePositionDto> getVotePositionDtoDto(@PathVariable Long id)
            throws ResourceNotFound {
        VotePosition votePosition = votePositionService.getVotePosition(id)
                .orElseThrow(() -> new ResourceNotFound("Vote PositionDto Not Found"));
        VotePositionDto votePositionDto = votePositionMapper.toVotePositionDto(votePosition);
        return ResponseEntity.ok(votePositionDto);
    }

    @PostMapping
    public ResponseEntity<VotePositionDto> createVotePositionDto(@RequestBody VotePositionDto votePositionDto) {
        VotePosition votePosition = votePositionMapper.toVotePosition(votePositionDto);
        votePositionService.save(votePosition);
        return ResponseEntity.status(HttpStatus.CREATED).body(votePositionDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,	@RequestBody VotePositionDto votePositionDto)
            throws ResourceNotFound {
        VotePosition votePosition = votePositionMapper.toVotePosition(votePositionDto);
        VotePosition votePositionId = votePositionService.getVotePosition(id)
                .orElseThrow(()->new ResourceNotFound("Vote Position Not Found"));
        votePositionId.setPosition(votePosition.getPosition()!=null ? votePosition.getPosition()
                : votePositionId.getPosition());
        votePositionId.setVote(votePosition.getVote()!=null ? votePosition.getVote()
                : votePositionId.getVote());
        votePositionService.save(votePositionId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(votePositionId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        votePositionService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
