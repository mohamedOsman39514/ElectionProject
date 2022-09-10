package com.example.elections.service;

import com.example.elections.model.ElectionProcess;
import com.example.elections.repository.ElectionProcessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ElectionProcessService {

    @Autowired
    private ElectionProcessRepository electionProcessRepository;

    public List<ElectionProcess> getAllElectionProcesses() {
        return electionProcessRepository.findAll();
    }

    public Optional<ElectionProcess> getElectionProcess(Long id) {
        return electionProcessRepository.findById(id);
    }

    public void delete(Long id) {
        electionProcessRepository.deleteById(id);
    }

    public ElectionProcess save(ElectionProcess electionProcess) {
        return electionProcessRepository.save(electionProcess);
    }

}
