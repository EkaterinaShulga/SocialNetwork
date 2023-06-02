package com.example.socialnetwork.service.serviceImpl;

import com.example.socialnetwork.entity.Photo;
import com.example.socialnetwork.entity.Publication;
import com.example.socialnetwork.exeption.FileEmptyException;
import com.example.socialnetwork.exeption.SomeProblemWithFileException;
import com.example.socialnetwork.repository.PhotoRepository;
import com.example.socialnetwork.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;

    @Override
    public Photo createPhoto(MultipartFile file, Publication publication) {
        log.info("imageServiceImpl - createPhoto())");
        Photo photo = new Photo();
        getInformationFromFile(file, photo);
        return photoRepository.save(photo);

    }

    public void getInformationFromFile(MultipartFile file, Photo photo) {
        log.info("photoServiceImpl - getInformationFromFile()");
        byte[] photo1;
        if (file.isEmpty()) {
            throw new FileEmptyException();
        }
        try {
            photo1 = file.getBytes();
        } catch (IOException e) {
            log.warn("Some problem with file" + file.getName());
            throw new SomeProblemWithFileException();
        }
        photo.setPhoto(photo1);
        photo.setMediaType(file.getContentType());
        photo.setFileSize(file.getSize());
    }

}
