package com.example.elections.rest.mapper;

import com.example.elections.model.Vote;
import com.example.elections.rest.dtos.VoteDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VoteMapper {

    List<VoteDto> toVoteDtos(List<Vote> votes);

    Vote toVote(VoteDto voteDto);

    VoteDto toVoteDto(Vote vote);

}
