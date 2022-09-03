package com.example.elections.rest.mapper;

import com.example.elections.model.Station;
import com.example.elections.rest.dtos.StationDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StationMapper {

    List<StationDto> toStationDtos(List<Station> stations);

    Station toStation(StationDto stationDto);

    StationDto toStationDto(Station station);

}
