package com.example.elections.rest.controller;

import com.example.elections.service.VoteCandidateService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/result")
public class ResultController {
    @Autowired
    private VoteCandidateService voteCandidateService;

    @GetMapping("/votes")
    public ResponseEntity<List<?>> getAllVotes() {
        List<?> votes = voteCandidateService.getVotes();
        return ResponseEntity.ok(votes);
    }

    @GetMapping("/voters")
    public ResponseEntity<?> getNumberOfVoters() {
        List<?> voters = Collections.singletonList(voteCandidateService.getAllVoters());
        return ResponseEntity.ok(voters);
    }

    @GetMapping("/revocations")
    public ResponseEntity<List<?>> getNumberOfRevocation() {
        List<?> revocations = voteCandidateService.getAllRevocations();
        return ResponseEntity.ok(revocations);
    }

}
