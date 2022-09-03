package com.example.elections.repository;

import com.example.elections.model.VotePosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotePositionRepository extends JpaRepository<VotePosition, Long> {
}
