package com.example.elections.rest.controller;

import com.example.elections.service.VoteCandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/result")
public class ResultController {
    @Autowired
    private VoteCandidateService voteCandidateService;

    @GetMapping("/votes")
    public ResponseEntity<List<?>> getAllVotes() {
        List<?> voteCandidateDtoList = voteCandidateService.getVotes();
        return ResponseEntity.ok(voteCandidateDtoList);
    }

    @GetMapping("/voters")
    public ResponseEntity<List<?>> getNumberOfVoters() {
        List<?> voteCandidateDtoList = voteCandidateService.getAllVoters();
        return ResponseEntity.ok(voteCandidateDtoList);
    }

    @GetMapping("/revocations")
    public ResponseEntity<List<?>> getNumberOfRevocation() {
        List<?> voteCandidateDtoList = voteCandidateService.getAllRevocations();
        return ResponseEntity.ok(voteCandidateDtoList);
    }

}
