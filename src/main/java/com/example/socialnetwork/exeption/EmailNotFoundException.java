package com.example.socialnetwork.exeption;

import java.io.IOException;

public class EmailNotFoundException extends IOException {
    public EmailNotFoundException() {
        super("This email not found");
    }
}
