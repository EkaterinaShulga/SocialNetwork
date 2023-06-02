package com.example.socialnetwork.controller;

import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.exeption.SubscriptionException;
import com.example.socialnetwork.repository.UserRepository;
import com.example.socialnetwork.service.SubscriptionService;
import com.example.socialnetwork.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/friend")
public class SubscriptionController {
    private final UserRepository userRepository;
    private final SubscriptionService service;

    @PostMapping("/{email}")
    @Operation(
            summary = "add new friend",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "201", content = @Content()),
                    @ApiResponse(responseCode = "401", content = @Content()),
                    @ApiResponse(responseCode = "403", content = @Content()),
                    @ApiResponse(responseCode = "404", content = @Content())
            }
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> addSubscription(@PathVariable("email") String userNameFriend,
                                                  Authentication authentication) throws SubscriptionException {
        log.info("subscriptionController -  addSubscription");
        User friend = userRepository.getUserByEmail(userNameFriend);
        User user = userRepository.getUserByEmail(authentication.getName());
        log.info("user " + user);
        if (user != null && friend != null) {
            service.addFriend(authentication, userNameFriend);
            return ResponseEntity.ok("You add user " + userNameFriend + " in your friends");
        } else {
            return ResponseEntity.ok("Not found user " + userNameFriend);
        }
    }
}
