package com.example.socialnetwork.service.serviceImpl;

import com.example.socialnetwork.entity.Password;
import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.exeption.UserAlreadyCreatedException;
import com.example.socialnetwork.repository.UserRepository;
import com.example.socialnetwork.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User changePassword(Password passwordDto, String userLogin) {
        log.info("Was invoked method for change password of user");
        if (passwordDto != null) {
            User user = userRepository.getUserByPassword(passwordDto.getCurrentPassword());
            if (user != null) {
                user.setPassword(passwordDto.getNewPassword());
            }
            return user;
        }
        return null;
    }

    @Override
    public User getUserByLogin(String userLogin) throws UserAlreadyCreatedException {
        return userRepository.findByEmail(userLogin)
                .orElseThrow(UserAlreadyCreatedException::new);
    }

    @Override
    public User getUser(String userLogin) throws UserAlreadyCreatedException {
        log.info("Was invoked method for get current user");
        return getUserByLogin(userLogin);
    }


}
