package com.pottery.support;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(MockitoExtension.class)
class SupportValidatorTest {

    private final SupportValidator supportValidator = new SupportValidator();

    @Test
    void validate_success() {
        //given
        SupportRequestDto request = new SupportRequestDto();
        String correctEmail = "test@example.com";
        request.setEmail(correctEmail);
        request.setName("test");
        request.setPhone("123456789");
        request.setMessage("test");
        //when
        try {
            supportValidator.validate(request);
        } catch (Exception e) {
            //then
            fail("Should not throw exception");
        }

    }

    @Test
    void validate_email_fail() {
        //given
        SupportRequestDto request = new SupportRequestDto();
        String incorrectEmail = "test";
        request.setEmail(incorrectEmail);
        request.setName("test");
        request.setPhone("123456789");
        request.setMessage("test");
        //when
        try {
            supportValidator.validate(request);
            fail("Should throw exception");
        } catch (AppException e) {
            //then
            assert(e.getMessage().equals("Invalid email"));
        }
    }

    @Test
    void validate_name_fail() {
        //given
        SupportRequestDto request = new SupportRequestDto();
        String incorrectName = "";
        request.setEmail("test@example.com");
        request.setName(incorrectName);
        request.setPhone("123456789");
        request.setMessage("test");
        //when
        try {
            supportValidator.validate(request);
            fail("Should throw exception");
        } catch (AppException e) {
            //then
            assert(e.getMessage().equals("Name is required"));
        }
    }

    @Test
    void validate_phone_fail() {
        //given
        SupportRequestDto request = new SupportRequestDto();
        String incorrectPhone = "";
        request.setEmail("test@example.com");
        request.setName("test");
        request.setPhone(incorrectPhone);
        request.setMessage("test");
        //when
        try {
            supportValidator.validate(request);
            fail("Should throw exception");
        } catch (AppException e) {
            //then
            assert(e.getMessage().equals("Phone is required"));
        }
    }

    @Test
    void validate_message_fail() {
        //given
        SupportRequestDto request = new SupportRequestDto();
        String incorrectMessage = "";
        request.setEmail("test@example.com");
        request.setName("test");
        request.setPhone("123456789");
        request.setMessage(incorrectMessage);
        //when
        try {
            supportValidator.validate(request);
            fail("Should throw exception");
        } catch (AppException e) {
            //then
            assert(e.getMessage().equals("Message is required"));
        }
    }
}
