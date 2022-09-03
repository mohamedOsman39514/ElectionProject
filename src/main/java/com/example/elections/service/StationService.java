package com.example.elections.service;

import com.example.elections.model.Station;
import com.example.elections.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StationService {

    @Autowired
    private StationRepository stationRepository;

    public List<Station> findAll()
    {
        return stationRepository.findAll();
    }

    public Optional<Station> findById(Long id)
    {
        return stationRepository.findById(id);
    }

    public Station save(Station station)
    {
        return stationRepository.save(station);
    }

    public void deleteById(Long id)
    {
        stationRepository.deleteById(id);
    }
}
