package com.example.elections.repository;

import com.example.elections.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query(value = "select email e from vote v" +
            "    join stations t on v.election_process_id = t.election_process_id" +
            "    join stations_voters s on s.voters_id =:voterId" +
            "    join voter e on e.id=s.voters_id" +
            "    Where s.station_id =:stationId " +
            "    group by email", nativeQuery = true)
    List<?> findVoterInStation(Long stationId, Long voterId);
}
