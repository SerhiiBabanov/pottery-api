package com.pottery.support;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class SupportValidator {

    private static final String EMAIL_REGEX = "^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private static final Pattern pattern= Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
    public void validate(SupportRequestDto request) {
        if (!pattern.matcher(request.getEmail()).matches()) {
            throw new AppException("Invalid email", HttpStatus.BAD_REQUEST);
        }
        if (request.getName() == null || request.getName().isEmpty()) {
            throw new AppException("Name is required", HttpStatus.BAD_REQUEST);
        }
        if (request.getPhone() == null || request.getPhone().isEmpty()) {
            throw new AppException("Phone is required", HttpStatus.BAD_REQUEST);
        }
        if (request.getMessage() == null || request.getMessage().isEmpty()) {
            throw new AppException("Message is required", HttpStatus.BAD_REQUEST);
        }
    }
}
