package com.example.elections.rest.controller;

import com.example.elections.rest.exception.Response;
import com.example.elections.model.ElectionProcess;
import com.example.elections.rest.dtos.ElectionProcessDto;
import com.example.elections.rest.exception.ResourceNotFound;
import com.example.elections.rest.mapper.ElectionProcessMapper;
import com.example.elections.service.ElectionProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/electionProcess")
public class ElectionProcessController {

    @Autowired
    private ElectionProcessService electionProcessService;

    @Autowired
    private ElectionProcessMapper electionProcessMapper;


    @GetMapping
    public ResponseEntity<List<ElectionProcessDto>> getAllElectionsProcess() {
        List<ElectionProcessDto> electionProcessDtoList = electionProcessMapper
                .toElectionProcessDtos(electionProcessService.getAllElectionProcesses());
        return ResponseEntity.ok(electionProcessDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElectionProcessDto> getElectionProcessDto(@PathVariable Long id)
            throws ResourceNotFound {
        ElectionProcess electionProcess = electionProcessService.getElectionProcess(id)
                .orElseThrow(() -> new ResourceNotFound("Election Process of id "+ id +" Not Found"));
        ElectionProcessDto electionProcessDto = electionProcessMapper.toElectionProcessDto(electionProcess);
        return ResponseEntity.ok(electionProcessDto);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ElectionProcessDto electionProcessDto) {
        ElectionProcess electionProcess = electionProcessMapper.toElectionProcess(electionProcessDto);
        electionProcessService.save(electionProcess);
        return ResponseEntity.status(HttpStatus.CREATED).body(electionProcess);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody ElectionProcessDto electionProcessDto)
            throws ResourceNotFound {

        ElectionProcess electionProcess = electionProcessMapper.toElectionProcess(electionProcessDto);
        ElectionProcess electionProcessId = electionProcessService.getElectionProcess(id)
                .orElseThrow(()->new ResourceNotFound("Election Process of id "+ id +" Not Found"));
        electionProcess.setId(id);
        electionProcessId.setName(electionProcess.getName()!=null ? electionProcess.getName()
                : electionProcessId.getName());
        electionProcessId.setStartAt(electionProcess.getStartAt()!= null? electionProcess.getStartAt()
                : electionProcessId.getStartAt());
        electionProcessId.setEndAt(electionProcess.getEndAt()!= null? electionProcess.getEndAt()
                : electionProcessId.getEndAt());
        electionProcessId.setParty(electionProcess.getParty()!= null? electionProcess.getParty()
                : electionProcessId.getParty());
        electionProcessService.save(electionProcessId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(electionProcessId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws ResourceNotFound {
        ElectionProcess electionProcessId = electionProcessService.getElectionProcess(id)
                .orElseThrow(()->new ResourceNotFound("Election Process of id "+ id +" Not Found"));
        electionProcessService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("deleted"));
    }


}
