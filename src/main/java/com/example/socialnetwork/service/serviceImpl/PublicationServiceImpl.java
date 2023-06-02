package com.example.socialnetwork.service.serviceImpl;

import com.example.socialnetwork.entity.Photo;
import com.example.socialnetwork.entity.Publication;
import com.example.socialnetwork.exeption.PublicationsNotFoundException;
import com.example.socialnetwork.exeption.UserAlreadyCreatedException;
import com.example.socialnetwork.exeption.UserNotFoundException;
import com.example.socialnetwork.repository.PublicationRepository;
import com.example.socialnetwork.repository.UserRepository;
import com.example.socialnetwork.service.PhotoService;
import com.example.socialnetwork.service.PublicationService;
import com.example.socialnetwork.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.example.socialnetwork.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PhotoService photoService;
    private final PublicationRepository publicationRepository;

    /*@Override
    public Publication createPublication(Publication publication, String userEmail) {
        log.info("publicationServiceImpl - save publication");
        User user = userRepository.getUserByEmail(userEmail);
        if (user == null) {
            throw new UserNotFoundException("Пользователь с userEmail " + userEmail + " не найден!");
        } else {
            publicationRepository.save(publication);
        }

        return publication;
    }*/


    @Override
    public Publication createPublication(String userLogin, Publication publication, MultipartFile image) throws UserAlreadyCreatedException {
        log.info("publicationServiceImpl - createPublication");
        log.info("user login - " + userLogin);
        log.info("publication - " + publication.getTitle());
        User user = userService.getUserByLogin(userLogin);
        int exist = publicationRepository.countByTitleAndUserId(publication.getTitle(), user.getId());

        if (exist > 0) {
            return null;
        }

        publicationRepository.save(publication);
        Photo photoForPublication = photoService.createPhoto(image, publication);
        List<Photo> allPhoto = new ArrayList<>();
        allPhoto.add(photoForPublication);
        return publication;
    }

    @Override
    public Publication createPublicationNoPhoto(String userLogin, Publication publication) throws UserAlreadyCreatedException {
        log.info("publicationServiceImpl - createPublication");
        log.info("user login - " + userLogin);
        log.info("publication - " + publication.getTitle());
        User user = userService.getUserByLogin(userLogin);
        int exist = publicationRepository.countByTitleAndUserId(publication.getTitle(), user.getId());

        if (exist > 0) {
            return null;
        }
        publication.setUser(user);
        publication.setId(publication.getId());
        publicationRepository.save(publication);

        return publication;
    }

    @Override
    public Publication getPublicationById(long id) {
        return publicationRepository.findById(id)
                .orElseThrow(() -> new PublicationsNotFoundException("Публикация с id " + id + " не найдена!"));
    }

    @Override
    public List<Publication> getAllPublicationsByUserEmail(String email) {
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("Пользователь с userEmail " + email + " не найден!");

        }
        log.info("user.getId " + user.getId());
        return publicationRepository.getAllPublicationsByUserId(user.getId());

    }

    @Override
    public Publication getPublicationByTitle(String title) {
        return publicationRepository.getByTitle(title)
                .orElseThrow(() -> new PublicationsNotFoundException("Публикация с title " + title + " не найдена!"));
    }

    @Override
    public void removePublication(Publication publication, Authentication authentication) {
        log.info("publicationServiceImpl - removePublication()");
        publicationRepository.delete(publication);
    }

    @Override
    public Publication updatePublication(String userLogin, String title, Publication updatePublication, Authentication authentication) {
        log.info("publicationServiceImpl - updatePublication()");
        Optional<Publication> optionalPublication = publicationRepository.getByTitle(title);

        optionalPublication.ifPresent(publication -> {
            publication.setTitle(updatePublication.getTitle());
            publication.setText(updatePublication.getText());

            publicationRepository.save(publication);

        });

        return optionalPublication
                .orElse(null);
    }

    @Override
    public List<Publication> getAllMyPublication(String userName) {
        log.info("publicationServiceImpl - getAllMyPublication()");
        User user = userRepository.getUserByEmail(userName);
        Long userId = user.getId();
        List<Publication> allPublication = publicationRepository.getAllPublicationsByUserId(userId);

        return allPublication;
    }
}
