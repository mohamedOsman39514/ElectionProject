package com.example.elections.repository;

import com.example.elections.model.VoteCandidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteCandidateRepository extends JpaRepository<VoteCandidate, Long> {
}
