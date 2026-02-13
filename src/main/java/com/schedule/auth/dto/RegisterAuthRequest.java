package com.schedule.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegisterAuthRequest {

    @NotBlank(message = "유저 닉네임은 필수입니다.")
    @Size(min = 2, max = 20, message = "유저 네임은 {min}자 이상 {max}자 이하이어야 합니다.")
    private String username;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, message = "비밀번호는 최소 {min}자 이상이어야 합니다.")
    private String password;
}
