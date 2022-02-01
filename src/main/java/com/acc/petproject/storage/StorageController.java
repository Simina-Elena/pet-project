package com.acc.petproject.storage;

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/file")
@CrossOrigin
public class StorageController {

    @Autowired
    private StorageService service;

    @GetMapping("/list")
    public ResponseEntity<List<S3ObjectSummary>> getFile() {
        return new ResponseEntity<>(service.getFiles(), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<S3ObjectInputStream> getOneFile(@RequestParam(value = "filename") String filename) {
        return new ResponseEntity<>(service.getOneFile(filename), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "id") Long id) {
        System.out.println(file);
        return new ResponseEntity<>(service.uploadFile(file, id), HttpStatus.OK);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = service.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @DeleteMapping("/delete-for-shelter/{fileName}")
    public ResponseEntity<String> deleteFileForShelter(@PathVariable String fileName) {
        return new ResponseEntity<>(service.deleteFileForShelter(fileName), HttpStatus.OK);
    }

    @DeleteMapping("/delete-for-pet/{fileName}")
    public ResponseEntity<String> deleteFileForPet(@PathVariable String fileName) {
        return new ResponseEntity<>(service.deleteFileForPet(fileName), HttpStatus.OK);
    }
}
