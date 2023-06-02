package com.example.socialnetwork.service;

import com.example.socialnetwork.exeption.SubscriptionException;
import org.springframework.security.core.Authentication;

public interface SubscriptionService {
    void addFriend(Authentication authentication, String friend) throws SubscriptionException;
}
