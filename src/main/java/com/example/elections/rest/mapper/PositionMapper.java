package com.example.elections.rest.mapper;

import com.example.elections.model.Position;
import com.example.elections.rest.dtos.PositionDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PositionMapper {

    List<PositionDto> toPositionDtos(List<Position> positions);

    Position toPosition(PositionDto positionDto);

    PositionDto toPositionDto(Position position);
}
