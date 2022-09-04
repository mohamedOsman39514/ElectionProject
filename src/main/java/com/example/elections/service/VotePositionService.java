package com.example.elections.service;

import com.example.elections.model.Vote;
import com.example.elections.model.VotePosition;
import com.example.elections.repository.VotePositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VotePositionService {

    @Autowired
    private VotePositionRepository votePositionRepository;

    public List<VotePosition> getAllVotePositions() {
        return votePositionRepository.findAll();
    }

    public Optional<VotePosition> getVotePosition(Long id) {
        return votePositionRepository.findById(id);
    }

    public void delete(Long id) {
        votePositionRepository.deleteById(id);
    }

    public VotePosition save(VotePosition votePosition) {
        return votePositionRepository.save(votePosition);
    }
}
