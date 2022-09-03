package com.example.elections.service;

import com.example.elections.model.Voter;
import com.example.elections.repository.VoterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VoterService {

    @Autowired
    private VoterRepository voterRepository;

    public List<Voter> getAllVoters() {
        return voterRepository.findAll();
    }

    public Optional<Voter> getVoter(Long id) {
        return voterRepository.findById(id);
    }

    public void delete(Long id) {
        voterRepository.deleteById(id);
    }

    public Voter save(Voter voter) {
        return voterRepository.save(voter);
    }

    public Voter findByEmail(String email) {
        return voterRepository.findByEmail(email);
    }

}
