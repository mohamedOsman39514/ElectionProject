package com.example.elections.rest.controller;

import com.example.elections.model.Vote;
import com.example.elections.model.Voter;
import com.example.elections.rest.dtos.VoteDto;
import com.example.elections.rest.exception.ResourceNotFound;
import com.example.elections.rest.exception.Response;
import com.example.elections.rest.mapper.VoteMapper;
import com.example.elections.service.VoteService;
import com.example.elections.service.VoterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteMapper voteMapper;

    @Autowired
    private VoteService voteService;

    @Autowired
    private VoterService voterService;

    @GetMapping
    public ResponseEntity<List<VoteDto>> getAllVotes() {
        List<VoteDto> voteDtoList = voteMapper.toVoteDtos(voteService.getAllVotes());
        return ResponseEntity.ok(voteDtoList);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("# authentication.principal.id==1")
    public ResponseEntity<VoteDto> getVoteDto(@PathVariable Long id)
            throws ResourceNotFound {
        Vote vote = voteService.getVote(id)
                .orElseThrow(() -> new ResourceNotFound("Vote of id " + id + " Not Found"));
        VoteDto voteDto = voteMapper.toVoteDto(vote);
        return ResponseEntity.ok(voteDto);
    }

    @PostMapping
    public ResponseEntity<?> createVote(@Valid @RequestBody VoteDto voteDto) {
        try {
            Vote vote = voteMapper.toVote(voteDto);
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Voter voterEmail = voterService.findByEmail(email);
            List<?> e = voteService.findVoterInStation(voteDto.getStation().getId(), voterEmail.getId());
            System.out.println(e + " \n" + email);
            if (!e.contains(email)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    new Response("Sorry " + voterEmail.getName() + ", you are not in this station"));
            else if (voterEmail.getVoterVote()) return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    new Response("You are voted before "));
            voteService.save(vote);
            return ResponseEntity.status(HttpStatus.CREATED).body(vote);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> update(@PathVariable Long id,	@RequestBody VoteDto voteDto)
//            throws ResourceNotFound {
//        Vote vote = voteMapper.toVote(voteDto);
//        Vote voteId = voteService.getVote(id)
//                .orElseThrow(()->new ResourceNotFound("Vote of id "+ id +" Not Found"));
//        voteId.setStation(vote.getStation()!=null ? vote.getStation() : voteId.getStation());
//        voteId.setElectionProcess(vote.getElectionProcess()!=null ? vote.getElectionProcess()
//                : voteId.getElectionProcess());
//        voteService.save(voteId);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(voteId);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id) throws ResourceNotFound {
//        Vote voteId = voteService.getVote(id)
//                .orElseThrow(()->new ResourceNotFound("Vote of id "+ id +" Not Found"));
//        voteService.delete(id);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("deleted"));
//    }
}
