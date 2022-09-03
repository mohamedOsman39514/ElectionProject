package com.example.elections.service;

import com.example.elections.model.Party;
import com.example.elections.repository.PartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PartyService {

    @Autowired
    private PartyRepository partyRepository;

    public List<Party> getAllParties() {
        return partyRepository.findAll();
    }

    public Optional<Party> getParty(Long id) {
        return partyRepository.findById(id);
    }

    public void delete(Long id) {
        partyRepository.deleteById(id);
    }

    public Party save(Party party) {
        return partyRepository.save(party);
    }

}
