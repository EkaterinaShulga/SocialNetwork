package com.example.socialnetwork.service;


import com.example.socialnetwork.entity.Publication;
import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.exeption.UserAlreadyCreatedException;
import com.example.socialnetwork.repository.PublicationRepository;
import com.example.socialnetwork.repository.UserRepository;
import com.example.socialnetwork.service.serviceImpl.PublicationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PublicationServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PublicationRepository publicationRepository;


    @Mock
    private UserService userService;


    @InjectMocks
    private PublicationServiceImpl publicationService;
    private List<Publication> publicationList;
    private User defaultUser;
    private Authentication authentication;
    private Publication publication;
    private Publication publication2;

    @BeforeEach
    void init() {
        defaultUser = new User();
        defaultUser.setId(1L);
        defaultUser.setUsername("username@gmail.com");
        authentication = new UsernamePasswordAuthenticationToken(defaultUser, null);


        publication = new Publication();
        publication.setId(3L);
        publication.setTitle("title");
        publication.setText("text");
        publication.setUser(defaultUser);

        publication2 = new Publication();
        publication2.setId(3L);
        publication2.setTitle("title2");
        publication2.setText("text");
        publication2.setUser(defaultUser);

        publicationList = new ArrayList<>();

        publicationList.add(publication);
        publicationList.add(publication2);

    }

    @Test
    void createPublicationNoPhotoTest() throws UserAlreadyCreatedException {

        when(userRepository.save(defaultUser)).thenReturn(defaultUser);
        when(publicationRepository.countByTitleAndUserId(anyString(), anyLong())).thenReturn(0);
        when(publicationRepository.save(any())).thenReturn(publication);
        when(userService.getUser(anyString())).thenReturn(defaultUser);
        Publication result = publicationService.createPublicationNoPhoto(defaultUser.getUsername(), publication);
        assertNotNull(result);
    }

    @Test
    void getPublicationById() {
        when(publicationRepository.findById(anyLong())).thenReturn(Optional.of(publication));
        Publication result = publicationService.getPublicationById(publication.getId());


        assertNotNull(result);
    }

}
