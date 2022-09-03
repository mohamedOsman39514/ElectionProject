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
import java.util.Optional;

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
    public ResponseEntity<PositionDto> getPositionById(@PathVariable(value = "id") Long Id)
            throws ResourceNotFound {
        Optional<Position> position = Optional.ofNullable(positionService.findById(Id)
                .orElseThrow(() -> new ResourceNotFound("Position Not Found")));
        PositionDto positionDto = positionMapper.toPositionDto(position.get());
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
                .orElseThrow(()->new ResourceNotFound("Position Not Found"));
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
