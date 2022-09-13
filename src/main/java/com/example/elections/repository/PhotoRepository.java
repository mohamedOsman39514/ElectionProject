package com.example.elections.repository;

import com.example.elections.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    @Query(value = "SELECT u FROM Photo u WHERE u.name =:name")
    Photo findImageByName(String name);

    @Query(value = "SELECT u FROM Photo u WHERE candidate_id =:id")
    Photo getCandidateImage(Long id);
}
