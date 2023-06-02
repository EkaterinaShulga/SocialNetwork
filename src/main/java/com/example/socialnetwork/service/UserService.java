package com.example.socialnetwork.service;

import com.example.socialnetwork.entity.Password;
import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.exeption.UserAlreadyCreatedException;


public interface UserService {
    User changePassword(Password password, String userLogin);

    User getUserByLogin(String userLogin) throws UserAlreadyCreatedException;

    User getUser(String UserPassword) throws UserAlreadyCreatedException;


}
