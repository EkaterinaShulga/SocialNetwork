package com.example.socialnetwork.repository;

import com.example.socialnetwork.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findSubscriptionsByUserId(long userId);
}
