package com.example.elections.rest.controller;

import com.example.elections.model.Station;
import com.example.elections.rest.dtos.StationDto;
import com.example.elections.rest.exception.ResourceNotFound;
import com.example.elections.rest.mapper.StationMapper;
import com.example.elections.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/station")
public class StationController {

    @Autowired
    private StationMapper stationMapper;

    @Autowired
    private StationService stationService;

    @GetMapping
    public ResponseEntity<List<StationDto>> getAllStations() {
        List<StationDto> stationDtoList = stationMapper.toStationDtos(stationService.findAll());
        return ResponseEntity.ok(stationDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StationDto> getStationDto(@PathVariable Long id)
            throws ResourceNotFound {
        Station station = stationService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Station of id "+ id +" Not Found"));
        StationDto stationDto = stationMapper.toStationDto(station);
        return ResponseEntity.ok(stationDto);
    }

    @PostMapping
    public ResponseEntity<StationDto> createStation(@RequestBody StationDto stationDto) {
        Station station = stationMapper.toStation(stationDto);
        stationService.save(station);
        return ResponseEntity.status(HttpStatus.CREATED).body(stationDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,	@RequestBody StationDto stationDto)
            throws ResourceNotFound {
        Station station = stationMapper.toStation(stationDto);
        Station stationId = stationService.findById(id)
                .orElseThrow(()-> new ResourceNotFound("Station of id "+ id +" Not Found"));
        stationId.setNumber(station.getNumber()!=null ? station.getNumber() : stationId.getNumber());
        stationService.save(stationId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(stationId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        stationService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
