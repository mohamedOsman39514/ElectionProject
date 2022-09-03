package com.example.elections.service;

import com.example.elections.model.Position;
import com.example.elections.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    public List<Position> findAll()
    {
        return positionRepository.findAll();
    }

    public Optional<Position> findById(Long id)
    {
        return positionRepository.findById(id);
    }

    public Position save(Position position)
    {
        return positionRepository.save(position);
    }

    public void deleteById(Long id)
    {
        positionRepository.deleteById(id);
    }

}
