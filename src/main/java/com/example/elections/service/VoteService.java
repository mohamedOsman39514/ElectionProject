package com.example.elections.service;

import com.example.elections.model.Vote;
import com.example.elections.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VoteService {


    @Autowired
    private VoteRepository voteRepository;

    public List<Vote> getAllVotes() {
        return voteRepository.findAll();
    }

    public Optional<Vote> getVote(Long id) {
        return voteRepository.findById(id);
    }

    public void delete(Long id) {
        voteRepository.deleteById(id);
    }

    public Vote save(Vote vote) {
        return voteRepository.save(vote);
    }

    public List<?> findVoterInStation(Long stationId, Long voterId) {
        return voteRepository.findVoterInStation(stationId, voterId);
    }


}
