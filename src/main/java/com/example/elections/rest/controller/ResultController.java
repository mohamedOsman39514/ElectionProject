package com.example.elections.rest.controller;

import com.example.elections.rest.exception.Response;
import com.example.elections.service.VoteCandidateService;
import com.example.elections.service.VoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.Logger;


@RequiredArgsConstructor
@RestController
@RequestMapping("/result")
//@Slf4j
public class ResultController {
    @Autowired
    private VoteCandidateService voteCandidateService;

    @Autowired
    private VoteService voteService;

    Logger logger = LogManager.getLogger(ResultController.class);

    @GetMapping("/votes")
    public ResponseEntity<List<?>> getAllVotes() {
        logger.debug("message");
        List<?> votes = voteCandidateService.getVotes();
        return ResponseEntity.ok(votes);
    }

    @GetMapping("/voters")
    public ResponseEntity<?> getNumberOfVoters() {
            List<?> voters = voteCandidateService.getAllVoters();
            return ResponseEntity.status(200).body(voters);
    }

    @GetMapping("/revocations")
    public ResponseEntity<List<?>> getNumberOfRevocation() {
        List<?> revocations = voteCandidateService.getAllRevocations();
        return ResponseEntity.ok(revocations);
    }

//    @GetMapping("/voter")
//    public ResponseEntity<List<?>> findVoterInStation() {
//        List<?> revocations = voteService.findVoterInStation();
//        return ResponseEntity.ok(revocations);
//    }

}
