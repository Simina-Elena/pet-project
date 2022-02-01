package com.acc.petproject.storage;

import com.acc.petproject.pet.Pet;
import com.acc.petproject.pet.PetRepository;
import com.acc.petproject.shelter.Shelter;
import com.acc.petproject.shelter.ShelterRepository;
import com.acc.petproject.storage.image.Image;
import com.acc.petproject.storage.image.ImageRepository;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StorageService {

    @Value("${amazon.s3.bucket-name}")
    private String bucketName;

    @Value("${amazon.s3.pet-bucket-name}")
    private String petBucketName;

    @Autowired
    private AmazonS3 s3Client;

    private final ImageRepository imageRepository;
    private final ShelterRepository shelterRepository;
    private final PetRepository petRepository;

    @Autowired
    public StorageService(ImageRepository imageRepository, ShelterRepository shelterRepository, PetRepository petRepository) {
        this.imageRepository = imageRepository;
        this.shelterRepository = shelterRepository;
        this.petRepository = petRepository;
    }

    public String uploadFile(MultipartFile file, Long id) {
        String fileName = "";
        Optional<Shelter> shelter = shelterRepository.findById(id);
        if (shelter.isPresent()) {
            File fileObj = convertMultiPartFileToFile(file);
            fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
            fileObj.delete();
            Image image = new Image();
            image.setName(fileName);
            image.setShelter(shelter.get());
            imageRepository.save(image);
        } else {
            Optional<Pet> pet = petRepository.findById(id);
            File fileObj = convertMultiPartFileToFile(file);
            fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            s3Client.putObject(new PutObjectRequest(petBucketName, fileName, fileObj));
            fileObj.delete();
            Image image = new Image();
            image.setName(fileName);
            image.setPet(pet.get());
            imageRepository.save(image);
        }
        return fileName;
    }

    public List<S3ObjectSummary> getFiles() {
        ListObjectsV2Result result = s3Client.listObjectsV2(bucketName);
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        for (S3ObjectSummary os : objects) {
            System.out.println("* " + os.getKey());
        }
        return objects;
    }


    public S3ObjectInputStream getOneFile(String filename) {
        S3Object s3Object = s3Client.getObject(bucketName, filename);
        return s3Object.getObjectContent();
    }

    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Transactional
    public String deleteFile(String fileName) {
        Image image = imageRepository.findByName(fileName);
        if(image.getShelter().getId() != null) {
            imageRepository.delete(image);
            s3Client.deleteObject(bucketName, fileName);
        } else {
            imageRepository.delete(image);
            s3Client.deleteObject(petBucketName, fileName);
        }
        return fileName + " removed ...";
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
}
