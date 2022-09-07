package com.example.elections.rest.controller;

import com.example.elections.model.Position;
import com.example.elections.rest.dtos.PositionDto;
import com.example.elections.rest.exception.ResourceNotFound;
import com.example.elections.rest.mapper.PositionMapper;
import com.example.elections.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @Autowired
    private PositionMapper positionMapper;

    @GetMapping
    public ResponseEntity<List<PositionDto>> getAllPositions() {
        List<PositionDto> positionDtoList = positionMapper.toPositionDtos(positionService.findAll());
        return ResponseEntity.ok(positionDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PositionDto> getPositionById(@PathVariable(value = "id") Long id)
            throws ResourceNotFound {
        Position position = positionService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Position of id "+ id +" Not Found"));
        PositionDto positionDto = positionMapper.toPositionDto(position);
        return ResponseEntity.ok(positionDto);
    }

    @PostMapping
    public ResponseEntity<PositionDto> create(@RequestBody PositionDto positionDto) {
        Position position = positionMapper.toPosition(positionDto);
        positionService.save(position);
        return ResponseEntity.status(HttpStatus.CREATED).body(positionDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PositionDto> update(@PathVariable Long id,	@RequestBody PositionDto positionDto)
            throws ResourceNotFound {
        Position position = positionMapper.toPosition(positionDto);
        Position positionId = positionService.findById(id)
                .orElseThrow(()->new ResourceNotFound("Position of id "+ id +" Not Found"));
        position.setId(id);
        position.setName(position.getName()!=null ? position.getName() : positionId.getName());
        position.setSets(position.getSets()!=null ? position.getSets() : positionId.getSets());
        position.setElectionProcess(position.getElectionProcess()!=null ? position.getElectionProcess()
                : positionId.getElectionProcess());
        positionService.save(position);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(positionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        positionService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
