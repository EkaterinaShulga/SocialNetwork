package com.example.socialnetwork.service;

import com.example.socialnetwork.entity.Publication;
import com.example.socialnetwork.exeption.UserAlreadyCreatedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PublicationService {
    Publication createPublication(String userLogin, Publication publication, MultipartFile image) throws UserAlreadyCreatedException;

    Publication createPublicationNoPhoto(String userLogin, Publication publication) throws UserAlreadyCreatedException;

    Publication getPublicationById(long id);

    List<Publication> getAllPublicationsByUserEmail(String userEmail);

    Publication getPublicationByTitle(String title);

    void removePublication(Publication publication, Authentication authentication);

    Publication updatePublication(String userLogin, String title, Publication updatePublication, Authentication authentication);

    List<Publication> getAllMyPublication(String userName);


}
