package com.example.socialnetwork.service;

import com.example.socialnetwork.entity.RegisterReq;
import com.example.socialnetwork.entity.Role;

public interface AuthService {

    boolean login(String userName, String password);

    boolean register(RegisterReq registerReq, Role role);
}
