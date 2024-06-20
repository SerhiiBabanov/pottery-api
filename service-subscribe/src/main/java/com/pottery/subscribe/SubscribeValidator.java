package com.pottery.subscribe;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class SubscribeValidator {

    private static final String EMAIL_REGEX = "^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private static final Pattern pattern= Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
    public void validate(String email) {
        if (!pattern.matcher(email).matches()) {
            throw new AppException("Invalid email", HttpStatus.BAD_REQUEST);
        }
    }
}
