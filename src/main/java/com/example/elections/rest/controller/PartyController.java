package com.example.elections.rest.controller;

import com.example.elections.handle.Response;
import com.example.elections.model.Party;
import com.example.elections.rest.dtos.PartyDto;
import com.example.elections.rest.exception.ResourceNotFound;
import com.example.elections.rest.mapper.PartyMapper;
import com.example.elections.service.PartyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping(value = "/party")
public class PartyController {
    @Autowired
    private PartyMapper partyMapper;

    @Autowired
    private PartyService partyService;

    @GetMapping
    public ResponseEntity<List<PartyDto>> getAllParties() {
        List<PartyDto> partyDtoList = partyMapper.toPartyDtos(partyService.getAllParties());
        return ResponseEntity.ok(partyDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartyDto> getPartyDto(@PathVariable Long id)
            throws ResourceNotFound {
        Party party = partyService.getParty(id)
                .orElseThrow(() -> new ResourceNotFound("Party of id "+ id +" Not Found"));
        PartyDto partyDto = partyMapper.toPartyDto(party);
        return ResponseEntity.ok(partyDto);
    }

    @PostMapping
    public ResponseEntity<PartyDto> createParty(@RequestBody PartyDto partyDto) {
        Party party = partyMapper.toParty(partyDto);
        partyService.save(party);
        return ResponseEntity.status(HttpStatus.CREATED).body(partyDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,	@RequestBody PartyDto partyDto)
            throws ResourceNotFound {
        Party party = partyMapper.toParty(partyDto);
        Party partyId = partyService.getParty(id)
                .orElseThrow(()->new ResourceNotFound("Party of id "+ id +" Not Found"));
        partyId.setName(party.getName()!=null ? party.getName() : partyId.getName());
//        party.setUpdatedBy(party.getUpdatedBy()!=null ? party.getUpdatedBy() : partyId.getUpdatedBy());
//        party.setCreatedBy(party.getCreatedBy()!=null ? party.getCreatedBy() : partyId.getCreatedBy());
        partyService.save(partyId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(partyId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws ResourceNotFound {
        Party partyId = partyService.getParty(id)
                .orElseThrow(()->new ResourceNotFound("Party of id "+ id +" Not Found"));
        partyService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("deleted"));
    }
}
