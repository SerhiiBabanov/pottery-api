package com.pottery.support;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SupportController {

    private final SupportService supportService;
    private final SupportValidator validator;
    private final SupportRequestMapper supportRequestMapper;

    @PostMapping("/support")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSupportRequest(@RequestBody SupportRequestDto supportRequestDto) {
        validator.validate(supportRequestDto);
        supportService.create(supportRequestMapper.toEntity(supportRequestDto));
    }

}
