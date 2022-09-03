package com.example.elections.rest.mapper;

import com.example.elections.model.Voter;
import com.example.elections.rest.dtos.VoterDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VoterMapper {

    List<VoterDto> toVoterDtos(List<Voter> voters);

    Voter toVoter(VoterDto voterDto);

    VoterDto toVoterDto(Voter voter);

}
