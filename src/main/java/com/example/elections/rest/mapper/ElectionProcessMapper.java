package com.example.elections.rest.mapper;

import com.example.elections.model.ElectionProcess;
import com.example.elections.rest.dtos.ElectionProcessDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ElectionProcessMapper {

    List<ElectionProcessDto> toElectionProcessDtos(List<ElectionProcess> electionProcesses);

    ElectionProcess toElectionProcess(ElectionProcessDto electionProcessDto);

    ElectionProcessDto toElectionProcessDto(ElectionProcess electionProcess);
}
