package com.example.elections.repository;

import com.example.elections.model.ElectionProcess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectionProcessRepository extends JpaRepository<ElectionProcess, Long> {
}
