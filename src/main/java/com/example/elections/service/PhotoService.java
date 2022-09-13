package com.example.elections.service;

import com.example.elections.model.Photo;
import com.example.elections.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;


    public Photo save(Photo photo) {
        return photoRepository.save(photo);
    }

    public Optional<Photo> getPhoto(Long id) {
        return photoRepository.findById(id);
    }

    public Photo findCandidateImage(Long id) {
        return photoRepository.getCandidateImage(id);
    }

    public Photo getPhotoByName(String name) {
        return photoRepository.findImageByName(name);
    }


}
