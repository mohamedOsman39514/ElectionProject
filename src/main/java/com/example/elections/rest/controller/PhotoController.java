package com.example.elections.rest.controller;

import com.example.elections.model.Candidate;
import com.example.elections.model.Photo;
import com.example.elections.rest.exception.ResourceNotFound;
import com.example.elections.rest.exception.Response;
import com.example.elections.service.CandidateService;
import com.example.elections.utils.image.ImageUtility;
import com.example.elections.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping(value = "/photo")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private CandidateService candidateService;


    @PostMapping("/{id}")
    public ResponseEntity<?> uplaodImage(@PathVariable Long id,@RequestParam("image") MultipartFile file)
            throws IOException, ResourceNotFound {

        Candidate candidate = candidateService.getCandidate(id)
                .orElseThrow(() -> new ResourceNotFound("The Candidate of id " + id + " Not Found"));
        Photo photo = new Photo();
        photo.setName(file.getOriginalFilename());
        photo.setType(file.getContentType());
        photo.setImage(ImageUtility.compressImage(file.getBytes()));
        photo.setCandidate(candidate);
        photoService.save(photo);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response("Image uploaded successfully: " + file.getOriginalFilename()));
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getImageByName(@PathVariable("name") String name) throws IOException {
        Photo dbImage = photoService.getPhotoByName(name);
        if (dbImage == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.getType()))
                .body(ImageUtility.decompressImage(dbImage.getImage()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCandidateImage(@PathVariable Long id,@RequestParam("image") MultipartFile file)
            throws IOException {
        Photo photo = photoService.findCandidateImage(id);
        if (photo == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        photo.setName(file.getOriginalFilename());
        photo.setType(file.getContentType());
        photo.setImage(ImageUtility.compressImage(file.getBytes()));
        photoService.save(photo);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response("Image updated successfully: " + file.getOriginalFilename()));
    }

}
