package com.example.elections.rest.mapper;

import com.example.elections.model.Candidate;
import com.example.elections.rest.dtos.CandidateDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateMapper {

    List<CandidateDto> toCandidateDtos(List<Candidate> candidates);

    Candidate toCandidate(CandidateDto candidateDto);

    CandidateDto toCandidateDto(Candidate candidate);
}
