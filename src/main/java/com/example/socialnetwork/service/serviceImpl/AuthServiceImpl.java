package com.example.socialnetwork.service.serviceImpl;

import com.example.socialnetwork.entity.RegisterReq;
import com.example.socialnetwork.entity.Role;
import com.example.socialnetwork.repository.UserRepository;
import com.example.socialnetwork.service.AuthService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service

public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    public AuthServiceImpl(UserDetailsManager manager,
                           UserRepository userRepository) {
        this.manager = manager;
        this.userRepository = userRepository;
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        String encryptedPassword = userDetails.getPassword();
        String encryptedPasswordWithoutEncryptionType = encryptedPassword.substring(8);
        return encoder.matches(password, encryptedPasswordWithoutEncryptionType);
    }

    @Override
    public boolean register(RegisterReq registerReq, Role role) {
        if (manager.userExists(registerReq.getUsername())) {
            return false;
        }
        manager.createUser(
                User.withDefaultPasswordEncoder()
                        .password(registerReq.getPassword())
                        .username(registerReq.getUsername())
                        .roles(role.name())
                        .build()
        );
        LocalDateTime time = LocalDateTime.now();
        Optional<com.example.socialnetwork.entity.User> user = userRepository.findByEmail(registerReq.getUsername());
        com.example.socialnetwork.entity.User user1 = user.get();
        user1.setFirstName(registerReq.getFirstName());
        user1.setLastName(registerReq.getLastName());
        user1.setPhone(registerReq.getPhone());
        user1.setEmail(registerReq.getUsername());
        user1.setRegDate(time.toString());

        userRepository.save(user1);

        return true;
    }
}

