package com.example.socialnetwork.service.serviceImpl;

import com.example.socialnetwork.entity.Subscription;
import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.exeption.SubscriptionException;
import com.example.socialnetwork.repository.SubscriptionRepository;
import com.example.socialnetwork.repository.UserRepository;
import com.example.socialnetwork.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public void addFriend(Authentication authentication, String newFriend) throws SubscriptionException {
        log.info("subscriptionServiceImpl - addFriend())");
        User user = userRepository.getUserByEmail(authentication.getName());
        List<String> allFriends = new ArrayList<>();
        List<Subscription> allSubscription = subscriptionRepository.findSubscriptionsByUserId(user.getId());
        for (Subscription s : allSubscription) {
            String emailFriend = s.getFriend();
            allFriends.add(emailFriend);
        }
        if (!allFriends.contains(newFriend) && !user.getUsername().contains(newFriend)) {
            Subscription subscription1 = new Subscription();
            subscription1.setFriend(newFriend);
            subscription1.setUser(user);
            subscriptionRepository.save(subscription1);
            log.info("add " + subscription1);
        } else {
            throw new SubscriptionException();

        }


    }
}


