package com.schedule.auth.service;

import com.schedule.auth.dto.LoginAuthRequest;
import com.schedule.auth.dto.RegisterAuthRequest;
import com.schedule.auth.dto.SessionAuth;
import com.schedule.config.PasswordEncoder;
import com.schedule.user.entity.User;
import com.schedule.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public SessionAuth login(LoginAuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다.")
        );

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }

        return new SessionAuth(
                user.getId(),
                user.getEmail()
        );
    }

    @Transactional
    public SessionAuth register(RegisterAuthRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = new User(request.getUsername(), request.getEmail(), encodedPassword);
        User savedUser = userRepository.save(user);

        return new SessionAuth(
                savedUser.getId(),
                savedUser.getEmail()
        );
    }
}