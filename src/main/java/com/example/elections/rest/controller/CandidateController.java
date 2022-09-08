package com.example.elections.rest.controller;

import com.example.elections.handle.Response;
import com.example.elections.model.Candidate;
import com.example.elections.rest.dtos.CandidateDto;
import com.example.elections.rest.exception.ResourceNotFound;
import com.example.elections.rest.mapper.CandidateMapper;
import com.example.elections.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateMapper candidateMapper;

    @Autowired
    private CandidateService candidateService;


    @GetMapping
    public ResponseEntity<List<CandidateDto>> getAllCandidates() {
        List<CandidateDto> candidateDtoList = candidateMapper.toCandidateDtos(candidateService.getAllCandidates());
        return ResponseEntity.ok(candidateDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateDto> getCandidateById(@PathVariable(value = "id") Long id)
            throws ResourceNotFound {
        Candidate candidate = candidateService.getCandidate(id)
                .orElseThrow(()->new ResourceNotFound("The Candidate of id "+ id +" Not Found"));
        CandidateDto candidateDto = candidateMapper.toCandidateDto(candidate);
        return ResponseEntity.ok(candidateDto);
    }

    @PostMapping
    public ResponseEntity<?> createCandidate(@RequestBody CandidateDto candidateDto) {
        Candidate candidate = candidateMapper.toCandidate(candidateDto);
        candidate.setDate(LocalDate.now());
        candidateService.save(candidate);
        return ResponseEntity.status(HttpStatus.CREATED).body(candidate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,	@RequestBody CandidateDto candidateDto)
            throws ResourceNotFound {
        Candidate candidate = candidateMapper.toCandidate(candidateDto);
        Candidate candidateId = candidateService.getCandidate(id)
                .orElseThrow(()-> new ResourceNotFound("Vote Candidate of id "+ id +" Not Found"));

        candidateId.setName(candidate.getName()!=null ? candidate.getName() : candidateId.getName());
        candidateId.setNickname(candidate.getNickname()!=null ? candidate.getNickname()
                : candidateId.getNickname());
        candidateId.setElectionProcess(candidate.getElectionProcess()!=null ? candidate.getElectionProcess()
                : candidateId.getElectionProcess());
        candidateId.setNational_id(candidate.getNational_id()!=null ? candidate.getNational_id()
                : candidateId.getNational_id());
        candidateId.setPosition(candidate.getPosition()!=null ? candidate.getPosition() : candidateId.getPosition());
        candidateId.setNumber(candidate.getNumber()!=null ? candidate.getNumber() : candidateId.getNumber());
        candidateService.save(candidateId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(candidateId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws ResourceNotFound {
        Candidate candidateId = candidateService.getCandidate(id)
                .orElseThrow(()-> new ResourceNotFound("Vote Candidate of id "+ id +" Not Found"));
        candidateService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("deleted"));
    }

}
