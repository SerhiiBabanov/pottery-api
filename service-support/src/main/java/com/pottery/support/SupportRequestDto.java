package com.pottery.support;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link SupportRequest}
 */
@Data
@RequiredArgsConstructor
public class SupportRequestDto implements Serializable {
    String name;
    String email;
    String phone;
    String message;
}
