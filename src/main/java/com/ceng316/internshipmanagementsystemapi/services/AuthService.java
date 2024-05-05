package com.ceng316.internshipmanagementsystemapi.services;

import com.ceng316.internshipmanagementsystemapi.entities.Role;
import com.ceng316.internshipmanagementsystemapi.entities.User;
import com.ceng316.internshipmanagementsystemapi.repos.*;
import com.ceng316.internshipmanagementsystemapi.requests.LoginRequest;
import com.ceng316.internshipmanagementsystemapi.requests.RegisterRequest;
import com.ceng316.internshipmanagementsystemapi.responses.AuthResponse;
import com.ceng316.internshipmanagementsystemapi.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    public AuthResponse authenticate(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            User user = userRepository.findByEmail(request.getEmail());
            Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword());
            String jwtToken = jwtTokenProvider.generateJwtToken(auth);
            return AuthResponse.builder()
                    .token(jwtToken)
                    .name(user.getName())
                    .authorities(user.getRole())
                    .id(user.getId().toString())
                    .build();
        }
        catch (Exception e) {
            return null;
        }
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepository.save(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword());
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        return AuthResponse.builder()
                .token(jwtToken)
                .name(user.getName())
                .authorities(user.getRole())
                .id(user.getId().toString())
                .build();
    }
}
