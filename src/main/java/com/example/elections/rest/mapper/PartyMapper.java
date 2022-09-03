package com.example.elections.rest.mapper;

import com.example.elections.model.Party;
import com.example.elections.rest.dtos.PartyDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PartyMapper {

    List<PartyDto> toPartyDtos(List<Party> parties);

    Party toParty(PartyDto partyDto);

    PartyDto toPartyDto(Party party);

}
