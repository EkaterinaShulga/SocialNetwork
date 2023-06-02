package com.example.socialnetwork.controller;

import com.example.socialnetwork.entity.Password;
import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.exeption.UserAlreadyCreatedException;
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
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/set_password")
    @Operation(
            summary = "setPassword",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "201", content = @Content())
            }
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> setPassword(@RequestBody Password password, Authentication authentication) {
        log.info("userController - setPassword()");
        User user = userService.changePassword(password, authentication.getName());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @Operation(
            summary = "my information ",
            responses = {
                    @ApiResponse(responseCode = "200"),
            }
    )
    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> getUser(Authentication authentication) throws UserAlreadyCreatedException {
        log.info("userController - getUser()");
        return ResponseEntity.ok(userService.getUser(authentication.getName()));
    }
}
