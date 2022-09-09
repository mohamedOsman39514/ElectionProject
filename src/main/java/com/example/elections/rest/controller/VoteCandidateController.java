package com.example.elections.rest.controller;

import com.example.elections.rest.exception.Response;
import com.example.elections.model.Vote;
import com.example.elections.model.VoteCandidate;
import com.example.elections.model.Voter;
import com.example.elections.rest.dtos.VoteCandidateDto;
import com.example.elections.rest.exception.ResourceNotFound;
import com.example.elections.rest.mapper.VoteCandidateMapper;
import com.example.elections.service.VoteCandidateService;
import com.example.elections.service.VoteService;
import com.example.elections.service.VoterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private VoteService voteService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping
    public ResponseEntity<List<?>> getAllVoteCandidates() {
        Map<String, String> body = new HashMap<>();
        List<VoteCandidateDto> voteCandidateDtoList = voteCandidateMapper
                .toVoteCandidateDtos(voteCandidateService.getAllVoteCandidates());
        return ResponseEntity.status(200).body(voteCandidateDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVoteCandidateDto(@PathVariable Long id)
            throws ResourceNotFound {
        VoteCandidate voteCandidate = voteCandidateService.getVoteCandidate(id)
                .orElseThrow(() -> new ResourceNotFound("Vote Candidate of id " + id + " Not Found"));
        VoteCandidateDto voteCandidateDto = voteCandidateMapper.toVoteCandidateDto(voteCandidate);
        return ResponseEntity.status(HttpStatus.FOUND).body(voteCandidateDto);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> createVoteCandidateDto(@Valid @RequestBody VoteCandidateDto voteCandidateDto,
                                                    @PathVariable Long id) throws ResourceNotFound {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Voter voterEmail = voterService.findByEmail(email);
        List<?> c = voteCandidateService.getVoteId(id);
        Vote vote = voteService.getVote(id).get();

        if (!c.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Response("Not Allowed"));
        }

        if (!voterEmail.getVoterVote()) {
            if (voteCandidateDto.getRevocation()) {
                vote.setRevocation(true);
                voterEmail.setVoterVote(true);
                voteService.save(vote);
                voterService.save(voterEmail);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("You Are Revocation"));
            }
            voteCandidateDto.setVote(vote);
            VoteCandidate voteCandidate = voteCandidateMapper.toCandidate(voteCandidateDto);
            voteCandidateService.save(voteCandidate);
            voterEmail.setVoterVote(true);
            voterService.save(voterEmail);
            return ResponseEntity.status(HttpStatus.CREATED).body(voteCandidateDto);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Response("You Are Voted Before"));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody VoteCandidateDto voteCandidateDto)
//            throws ResourceNotFound {
//        VoteCandidate voteCandidate = voteCandidateMapper.toCandidate(voteCandidateDto);
//        VoteCandidate voteCandidateId = voteCandidateService.getVoteCandidate(id)
//                .orElseThrow(() -> new ResourceNotFound("Vote Candidate of id " + id + " Not Found"));
//        voteCandidateId.setPosition(voteCandidate.getPosition() != null ? voteCandidate.getPosition()
//                : voteCandidateId.getPosition());
//        voteCandidateId.setCandidate(voteCandidate.getCandidate() != null ? voteCandidate.getCandidate()
//                : voteCandidateId.getCandidate());
//        voteCandidateId.setVote(voteCandidate.getVote() != null ? voteCandidate.getVote()
//                : voteCandidateId.getVote());
//        voteCandidateService.save(voteCandidateId);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(voteCandidateId);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id) throws ResourceNotFound {
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//        Voter voterEmail = voterService.findByEmail(email);
//        VoteCandidate voteCandidateId = voteCandidateService.getVoteCandidate(id)
//                .orElseThrow(() -> new ResourceNotFound("Vote Candidate of id " + id + " Not Found"));
//        voteCandidateService.delete(id);
//        voterEmail.setVoter_vote(false);
//        voterService.save(voterEmail);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("deleted"));
//    }

}
