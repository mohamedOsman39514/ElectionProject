package com.example.elections.service;

import com.example.elections.model.VoteCandidate;
import com.example.elections.repository.VoteCandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VoteCandidateService {

    @Autowired
    private VoteCandidateRepository voteCandidateRepository;

    public List<VoteCandidate> getAllVoteCandidates() {
        return voteCandidateRepository.findAll();
    }

    public Optional<VoteCandidate> getVoteCandidate(Long id) {
        return voteCandidateRepository.findById(id);
    }

    public void delete(Long id) {
        voteCandidateRepository.deleteById(id);
    }

    public VoteCandidate save(VoteCandidate voteCandidate) {
        return voteCandidateRepository.save(voteCandidate);
    }

    public List<?> getVotes() {
        return voteCandidateRepository.findVotes();
    }

    public List<?> getAllVoters() {
        return voteCandidateRepository.findAllVoters();
    }

    public List<?> getAllRevocations() {
        return voteCandidateRepository.findAllRevocation();
    }

    public List<?> getVoteId(Long id) {
        return voteCandidateRepository.findVoteId(id);
    }

}
