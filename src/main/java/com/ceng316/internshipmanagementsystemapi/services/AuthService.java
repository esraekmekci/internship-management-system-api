package com.ceng316.internshipmanagementsystemapi.services;

import com.ceng316.internshipmanagementsystemapi.controllers.UBYSController;
import com.ceng316.internshipmanagementsystemapi.entities.*;
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
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;
    private final CompanyRepRepository companyRepRepository;
    private final CoordinatorRepository coordinatorRepository;
    private final SecretaryRepository secretaryRepository;
    private final UBYSController UBYSController;

    public AuthResponse authenticateStudent(LoginRequest request) {
        try {

//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                            request.getEmail(), //student id
//                            request.getPassword()
//                    )
//            );
            Student std = UBYSController.getStudent(Long.parseLong(request.getEmail()));
            if (!std.getPassword().equals(request.getPassword())){
                return null;
            }
            Student student = null;
            if (studentRepository.findById(std.getStudentID()).isEmpty()){
                student = studentRepository.save(std);
            }
            else {
                student = studentRepository.findById(std.getStudentID()).get();
            }
            if (student != null){
                Authentication auth = new UsernamePasswordAuthenticationToken(student, student.getPassword());
                String jwtToken = jwtTokenProvider.generateJwtToken(auth);
                return AuthResponse.builder()
                        .token(jwtToken)
                        .name(student.getName())
                        .authorities(student.getRole())
                        .id(student.getStudentID().toString())
                        .build();
            }
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public AuthResponse authenticate(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            User user = switch (request.getRole()) {
                case "STUDENT" -> studentRepository.findByEmail(request.getEmail());
                case "COMPANY" -> companyRepRepository.findByEmail(request.getEmail());
                case "COORDINATOR" -> coordinatorRepository.findByEmail(request.getEmail());
                case "SECRETARY" -> secretaryRepository.findByEmail(request.getEmail());
                default -> null;
            };
            if (user != null){
                Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword());
                String jwtToken = jwtTokenProvider.generateJwtToken(auth);
                return AuthResponse.builder()
                        .token(jwtToken)
                        .name(user.getName())
                        .authorities(user.getRole())
                        .id(user.getSubclassId().toString())
                        .build();
            }
            return null;
        }
        catch (Exception e) {
            return null;
        }
    }

    public AuthResponse register(RegisterRequest request) {
        User user;
        if (request.getRole().equalsIgnoreCase("student")) {
            Student student = Student.studentBuilder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .studentID(null)
                    .grade(null)
                    .internshipStatus(null)
                    .build();
            user = studentRepository.save(student);
        } else if (request.getRole().equalsIgnoreCase("coordinator")) {
            Coordinator coordinator = Coordinator.coordinatorBuilder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .build();
            user = coordinatorRepository.save(coordinator);
        } else if (request.getRole().equalsIgnoreCase("company")) {
            CompanyRep companyRep = CompanyRep.companyRepBuilder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .companyName(null)
                    .companyAddress(null)
                    .foundationYear(null)
                    .employeeSize(null)
                    .internshipType(null)
                    .build();
            user = companyRepRepository.save(companyRep);
        } else if (request.getRole().equalsIgnoreCase("secretary")) {
            Secretary secretary = Secretary.secretaryBuilder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .build();
            user = secretaryRepository.save(secretary);
        }
        else {
            // Handle unknown roles or throw an exception
            throw new IllegalArgumentException("Unknown role: " + request.getRole());
        }

        Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword());
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        return AuthResponse.builder()
                .token(jwtToken)
                .name(user.getName())
                .authorities(user.getRole())
                .id(user.getSubclassId().toString())
                .build();
    }
}
