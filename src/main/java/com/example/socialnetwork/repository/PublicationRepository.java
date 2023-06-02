package com.example.socialnetwork.repository;

import com.example.socialnetwork.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Integer> {


    List<Publication> getAllPublicationsByUserId(long userId);

    int countByTitleAndUserId(String title, Long userId);

    Optional<Publication> findById(long id);

    Optional<Publication> getByTitle(String title);

    List<Publication> getPublicationByUserId(long userId);
}
