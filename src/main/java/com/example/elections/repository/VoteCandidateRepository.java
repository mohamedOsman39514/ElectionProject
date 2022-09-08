package com.example.elections.repository;

import com.example.elections.handle.Data;
import com.example.elections.model.VoteCandidate;
import com.example.elections.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoteCandidateRepository extends JpaRepository<VoteCandidate, Long> {

    @Query(value = "SELECT  c.name, count(u) FROM Vote_Candidate u JOIN" +
            " Candidates c ON c.id= u.candidate_id" +
            " GROUP BY c.name", nativeQuery = true)
    List<?> findVotes();

    @Query(value = "SELECT u.name FROM Voter u WHERE u.voter_vote = true")
    List<?> findAllVoters();

//    @Query(value = "SELECT json_build_object( 'name', u.name )" +
//            "FROM Voter u WHERE u.voter_vote = true",nativeQuery = true)
//    List<?> findAllVoters();

    @Query(value = "SELECT count(u) FROM Vote u WHERE u.revocation = true")
    List<?> findAllRevocation();

    @Query(value = "SELECT u.vote_id FROM Vote_Candidate u WHERE u.vote_id= :vote_id", nativeQuery = true)
    List<?> findVoteId(Long vote_id);

}
