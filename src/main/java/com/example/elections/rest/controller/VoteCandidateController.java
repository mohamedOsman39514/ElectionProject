package com.example.elections.rest.controller;

import com.example.elections.model.VoteCandidate;
import com.example.elections.rest.dtos.VoteCandidateDto;
import com.example.elections.rest.exception.ResourceNotFound;
import com.example.elections.rest.mapper.VoteCandidateMapper;
import com.example.elections.service.VoteCandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/voteCandidate")
public class VoteCandidateController {

    @Autowired
    private VoteCandidateMapper voteCandidateMapper;

    @Autowired
    private VoteCandidateService voteCandidateService;

    @GetMapping
    public ResponseEntity<List<VoteCandidateDto>> getAllVoteCandidates() {
        List<VoteCandidateDto> voteCandidateDtoList = voteCandidateMapper
                .toVoteCandidateDtos(voteCandidateService.getAllVoteCandidates());
        return ResponseEntity.ok(voteCandidateDtoList);
    }

    @GetMapping("/results")
    public ResponseEntity<List<?>> getAllVotes() {
        List<?> voteCandidateDtoList = voteCandidateService.getVotes();
        return ResponseEntity.ok(voteCandidateDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoteCandidateDto> getVoteCandidateDto(@PathVariable Long id)
            throws ResourceNotFound {
        VoteCandidate voteCandidate = voteCandidateService.getVoteCandidate(id)
                .orElseThrow(() -> new ResourceNotFound("Vote Candidate Not Found"));
        VoteCandidateDto voteCandidateDto = voteCandidateMapper.toVoteCandidateDto(voteCandidate);
        return ResponseEntity.ok(voteCandidateDto);
    }

    @PostMapping
    public ResponseEntity<VoteCandidateDto> createVoteCandidateDto(@RequestBody VoteCandidateDto voteCandidateDto) {
        VoteCandidate voteCandidate = voteCandidateMapper.toCandidate(voteCandidateDto);
        voteCandidateService.save(voteCandidate);
        return ResponseEntity.status(HttpStatus.CREATED).body(voteCandidateDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,	@RequestBody VoteCandidateDto voteCandidateDto)
            throws ResourceNotFound {
        VoteCandidate voteCandidate = voteCandidateMapper.toCandidate(voteCandidateDto);
        VoteCandidate voteCandidateId = voteCandidateService.getVoteCandidate(id)
                .orElseThrow(()->new ResourceNotFound("Vote Candidate Not Found"));
        voteCandidateId.setPosition(voteCandidate.getPosition()!=null ? voteCandidate.getPosition()
                : voteCandidateId.getPosition());
        voteCandidateId.setCandidate(voteCandidate.getCandidate()!=null ? voteCandidate.getCandidate()
                : voteCandidateId.getCandidate());
        voteCandidateId.setVote(voteCandidate.getVote()!=null ? voteCandidate.getVote()
                : voteCandidateId.getVote());
        voteCandidateService.save(voteCandidateId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(voteCandidateId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        voteCandidateService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
