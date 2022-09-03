package com.example.elections.rest.controller;

import com.example.elections.model.Voter;
import com.example.elections.rest.dtos.VoterDto;
import com.example.elections.rest.exception.ResourceNotFound;
import com.example.elections.rest.mapper.VoterMapper;
import com.example.elections.service.VoterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class VoterController {

    @Autowired
    private VoterMapper voterMapper;

    @Autowired
    private VoterService voterService;


    @GetMapping
    public ResponseEntity<List<VoterDto>> getAllVoters() {
        List<VoterDto> voterDtoList = voterMapper.toVoterDtos(voterService.getAllVoters());
        return ResponseEntity.ok(voterDtoList);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByRut(@PathVariable String email){
        Voter voter = voterService.findByEmail(email);
        if (voter == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        return ResponseEntity.ok().body(voter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoterDto> getVoter(@PathVariable Long id)
            throws ResourceNotFound {
        Voter voter = voterService.getVoter(id)
                .orElseThrow(() -> new ResourceNotFound("Voter Not Found"));
        VoterDto voterDto = voterMapper.toVoterDto(voter);
        return ResponseEntity.ok(voterDto);
    }

    @PostMapping
    public ResponseEntity<VoterDto> createVoter(@RequestBody VoterDto voterDto) {
        Voter voter = voterMapper.toVoter(voterDto);
        voterService.save(voter);
        return ResponseEntity.status(HttpStatus.CREATED).body(voterDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,	@RequestBody VoterDto voterDto)
            throws ResourceNotFound {
        Voter voter = voterMapper.toVoter(voterDto);
        Voter voterId = voterService.getVoter(id)
                .orElseThrow(()->new ResourceNotFound("Voter Not Found"));
        voterId.setName(voter.getName()!=null ? voter.getName() : voterId.getName());
        voterService.save(voterId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(voterId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        voterService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
