package com.example.elections.rest.mapper;

import com.example.elections.model.VoteCandidate;
import com.example.elections.rest.dtos.VoteCandidateDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VoteCandidateMapper {

    List<VoteCandidateDto> toVoteCandidateDtos(List<VoteCandidate> voteCandidates);

    VoteCandidate toCandidate(VoteCandidateDto voteCandidateDto);

    VoteCandidateDto toVoteCandidateDto(VoteCandidate voteCandidate);

}
