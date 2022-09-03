package com.example.elections.rest.mapper;

import com.example.elections.model.VotePosition;
import com.example.elections.rest.dtos.VotePositionDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VotePositionMapper {

    List<VotePositionDto> toVotePositionDtos(List<VotePosition> votePositions);

    VotePosition toVotePosition(VotePositionDto votePositionDto);

    VotePositionDto toVotePositionDto(VotePosition votePosition);

}
