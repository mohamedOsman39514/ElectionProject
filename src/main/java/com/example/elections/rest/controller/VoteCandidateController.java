package com.example.elections.rest.controller;

import com.example.elections.model.VoteCandidate;
import com.example.elections.model.Voter;
import com.example.elections.rest.dtos.VoteCandidateDto;
import com.example.elections.rest.exception.ResourceNotFound;
import com.example.elections.rest.mapper.VoteCandidateMapper;
import com.example.elections.service.VoteCandidateService;
import com.example.elections.service.VoterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/voteCandidate")
public class VoteCandidateController {
    @Autowired
    private VoteCandidateMapper voteCandidateMapper;

    @Autowired
    private VoteCandidateService voteCandidateService;

    @Autowired
    private VoterService voterService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping
    public ResponseEntity<List<VoteCandidateDto>> getAllVoteCandidates() {
        List<VoteCandidateDto> voteCandidateDtoList = voteCandidateMapper
                .toVoteCandidateDtos(voteCandidateService.getAllVoteCandidates());
        return ResponseEntity.ok(voteCandidateDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVoteCandidateDto(@PathVariable Long id)
            throws ResourceNotFound {
        VoteCandidate voteCandidate = voteCandidateService.getVoteCandidate(id)
                .orElseThrow(() -> new ResourceNotFound("Vote Candidate Not Found"));
        VoteCandidateDto voteCandidateDto = voteCandidateMapper.toVoteCandidateDto(voteCandidate);
        return ResponseEntity.status(HttpStatus.FOUND).body(voteCandidateDto);
    }

    @PostMapping
    public ResponseEntity<?> createVoteCandidateDto(@RequestBody VoteCandidateDto voteCandidateDto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Voter voterEmail = voterService.findByEmail(email);
        String isRevocation = request.getHeader("revocation");

        if (!voterEmail.getVoter_vote() && isRevocation == null && !voterEmail.getRevocation()) {
            voterEmail.setVoter_vote(true);
            voterService.save(voterEmail);
            VoteCandidate voteCandidate = voteCandidateMapper.toCandidate(voteCandidateDto);
            voteCandidateService.save(voteCandidate);
            return ResponseEntity.status(HttpStatus.CREATED).body(voteCandidateDto);
        } else if (isRevocation!= null && isRevocation.equals("true")) {
            voterEmail.setRevocation(true);
            voterService.save(voterEmail);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("You Are Revocation");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are voted before");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody VoteCandidateDto voteCandidateDto)
            throws ResourceNotFound {
        VoteCandidate voteCandidate = voteCandidateMapper.toCandidate(voteCandidateDto);
        VoteCandidate voteCandidateId = voteCandidateService.getVoteCandidate(id)
                .orElseThrow(() -> new ResourceNotFound("Vote Candidate Not Found"));
        voteCandidateId.setPosition(voteCandidate.getPosition() != null ? voteCandidate.getPosition()
                : voteCandidateId.getPosition());
        voteCandidateId.setCandidate(voteCandidate.getCandidate() != null ? voteCandidate.getCandidate()
                : voteCandidateId.getCandidate());
        voteCandidateId.setVote(voteCandidate.getVote() != null ? voteCandidate.getVote()
                : voteCandidateId.getVote());
        voteCandidateService.save(voteCandidateId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(voteCandidateId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        voteCandidateService.delete(id);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Voter voterEmail = voterService.findByEmail(email);
        voterEmail.setVoter_vote(false);
        voterEmail.setRevocation(false);
        voterService.save(voterEmail);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
