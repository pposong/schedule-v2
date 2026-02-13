package com.schedule.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginAuthResponse {

    private final Long id;
    private final String email;
}
