package com.example.elections.repository;

import com.example.elections.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface VoterRepository extends JpaRepository<Voter, Long> {
    @Query(value = "SELECT * FROM Voter u WHERE u.email= :email", nativeQuery = true)
    Voter findByEmail(String email);
}
