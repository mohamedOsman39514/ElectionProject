package com.example.elections.repository;

import com.example.elections.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VoterRepository extends JpaRepository<Voter, Long> {
    @Query(value = "SELECT u FROM Voter u WHERE u.email= :email")
    Voter findByEmail(String email);
}
