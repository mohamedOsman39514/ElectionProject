package com.example.elections.rest.controller;

import com.example.elections.handle.Response;
import com.example.elections.model.Vote;
import com.example.elections.rest.dtos.VoteDto;
import com.example.elections.rest.exception.ResourceNotFound;
import com.example.elections.rest.mapper.VoteMapper;
import com.example.elections.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteMapper voteMapper;

    @Autowired
    private VoteService voteService;

    @GetMapping
    public ResponseEntity<List<VoteDto>> getAllVotes() {
        List<VoteDto> voteDtoList = voteMapper.toVoteDtos(voteService.getAllVotes());
        return ResponseEntity.ok(voteDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoteDto> getVoteDto(@PathVariable Long id)
            throws ResourceNotFound {
        Vote vote = voteService.getVote(id)
                .orElseThrow(() -> new ResourceNotFound("Vote of id "+ id +" Not Found"));
        VoteDto voteDto = voteMapper.toVoteDto(vote);
        return ResponseEntity.ok(voteDto);
    }

    @PostMapping
    public ResponseEntity<?> createVote(@RequestBody VoteDto voteDto) {
        Vote vote = voteMapper.toVote(voteDto);
        voteService.save(vote);
        return ResponseEntity.status(HttpStatus.CREATED).body(vote);
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
