package com.example.socialnetwork.controller;

import com.example.socialnetwork.entity.Publication;
import com.example.socialnetwork.service.PhotoService;
import com.example.socialnetwork.service.PublicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/publication")
public class PublicationController {

    private final PublicationService publicationService;
    private final PhotoService photoService;


  /* @PostMapping
           (
           consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
           produces = MediaType.APPLICATION_JSON_VALUE
   )
   @Operation(
           summary = "addPublication with photo",
           responses = {
                   @ApiResponse(responseCode = "200"),
                   @ApiResponse(responseCode = "401", content = @Content()),
                   @ApiResponse(responseCode = "403", content = @Content()),
                   @ApiResponse(responseCode = "404", content = @Content())
           }
   )
   @PreAuthorize("isAuthenticated()")
   public ResponseEntity<Publication> addPublication(@RequestPart(value = "properties") Publication publication,
                                                    @RequestPart(value = "image") MultipartFile image,
                                        Authentication authentication) throws IOException {
      log.info("publicationController -  addPublication()");
      Publication publication1 = publicationService.createPublication(authentication.getName(), publication, image);
      if (publication1 == null) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }

      return ResponseEntity.ok(publication);
   }
*/

    @PostMapping
    @Operation(
            summary = "user addPublication without photo",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "401", content = @Content()),
                    @ApiResponse(responseCode = "403", content = @Content()),
                    @ApiResponse(responseCode = "404", content = @Content())
            }
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<HttpStatus> addPublication(@RequestBody Publication publication,
                                                     Authentication authentication) throws IOException {
        log.info("publicationController -  addPublication()");
        Publication publication1 = publicationService.createPublicationNoPhoto(authentication.getName(), publication);
        if (publication1 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "/{id}")
    @Operation(
            summary = "getPublication by id",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "401", content = @Content()),
                    @ApiResponse(responseCode = "403", content = @Content()),
                    @ApiResponse(responseCode = "404", content = @Content())
            }
    )
    public ResponseEntity<Publication> getPublicationById(@PathVariable("id") long id) {
        log.info("publicationController - getPublicationById()");
        Publication publication = publicationService.getPublicationById(id);
        if (null == publication) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(publication);
    }

    @GetMapping(value = "/email/{userEmail}")
    @Operation(
            summary = "user getPublication at userName of another user",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "401", content = @Content()),
                    @ApiResponse(responseCode = "403", content = @Content()),
                    @ApiResponse(responseCode = "404", content = @Content())
            }
    )
    public ResponseEntity<List<Publication>> getAllPublicationsByUserEmail(@PathVariable("userEmail") String userEmail) {
        log.info("publicationController - getAllPublicationsByUserEmail()");
        List<Publication> allPublications = publicationService.getAllPublicationsByUserEmail(userEmail);
        if (allPublications.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(allPublications);
    }

    @DeleteMapping(value = "/{title}")
    @Operation(
            summary = "user removePublication by title publication",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content()),
                    @ApiResponse(responseCode = "401", content = @Content()),
                    @ApiResponse(responseCode = "403", content = @Content())
            }
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<HttpStatus> removePublication(@PathVariable("title") String title, Authentication authentication) {
        log.info("publicationController -  removePublication()");
        Publication publicationForDelete = publicationService.getPublicationByTitle(title);
        if (publicationForDelete == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        publicationService.removePublication(publicationForDelete, authentication);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping(value = "/{title}")
    @Operation(
            summary = "user can updatePublication by title",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "publication"),
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "401", content = @Content()),
                    @ApiResponse(responseCode = "403", content = @Content()),
                    @ApiResponse(responseCode = "404", content = @Content())
            }
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Publication> updatePublication(@PathVariable("title") String title,
                                                         @RequestBody Publication updatedPublication,
                                                         Authentication authentication) {
        log.info("publicationController -  updatePublication()");
        Publication newPublication = publicationService.updatePublication(authentication.getName(), title, updatedPublication, authentication);

        if (newPublication == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(newPublication);
    }

    @GetMapping("/me")
    @Operation(
            summary = "get all publications of user by userName",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "401", content = @Content()),
                    @ApiResponse(responseCode = "403", content = @Content()),
                    @ApiResponse(responseCode = "404", content = @Content())
            }
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Publication>> getAdsMe(Authentication authentication) {
        log.info("publicationController -  updatePublication()");
        List<Publication> allMyPublications = publicationService.getAllMyPublication(authentication.getName());
        return ResponseEntity.ok(allMyPublications);
    }


}
