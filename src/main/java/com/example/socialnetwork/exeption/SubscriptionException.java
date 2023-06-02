package com.example.socialnetwork.exeption;

import java.io.IOException;

public class SubscriptionException extends IOException {
    public SubscriptionException() {
        super("You added this user earlier and you can not add yourself");
    }
}
