package com.example.socialnetwork.service;

import com.example.socialnetwork.entity.Photo;
import com.example.socialnetwork.entity.Publication;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {
    Photo createPhoto(MultipartFile file, Publication publication);
}
