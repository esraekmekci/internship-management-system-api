package com.ceng316.internshipmanagementsystemapi.services;

import com.ceng316.internshipmanagementsystemapi.entities.User;
import com.ceng316.internshipmanagementsystemapi.repos.*;
import com.ceng316.internshipmanagementsystemapi.security.JwtUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final StudentRepository studentRepository;
    private final CompanyRepRepository companyRepRepository;
    private final CoordinatorRepository coordinatorRepository;
    private final SecretaryRepository secretaryRepository;

    public UserDetailsServiceImpl(StudentRepository studentRepository, CompanyRepRepository companyRepRepository, CoordinatorRepository coordinatorRepository, SecretaryRepository secretaryRepository) {
        this.studentRepository = studentRepository;
        this.companyRepRepository = companyRepRepository;
        this.coordinatorRepository = coordinatorRepository;
        this.secretaryRepository = secretaryRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            User user = null;
            if (studentRepository.findByEmail(email) != null){
                user = studentRepository.findByEmail(email);
            } else if (companyRepRepository.findByEmail(email) != null){
                user = companyRepRepository.findByEmail(email);
            } else if (coordinatorRepository.findByEmail(email) != null) {
                user = coordinatorRepository.findByEmail(email);
            } else if (secretaryRepository.findByEmail(email) != null) {
                user = secretaryRepository.findByEmail(email);
            }
            if (user == null){
                throw new UsernameNotFoundException("User not found with email: " + email);
            }
            return JwtUserDetails.create(user);
        }
        catch (Exception e) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }

    public UserDetails loadUserById(Long id) {
        try {
            User user = null;
            if (studentRepository.findById(id).isPresent()){
                user = studentRepository.findById(id).get();
            } else if (companyRepRepository.findById(id).isPresent()){
                user = companyRepRepository.findById(id).get();
            } else if (coordinatorRepository.findById(id).isPresent()) {
                user = coordinatorRepository.findById(id).get();
            } else if (secretaryRepository.findById(id).isPresent()) {
                user = secretaryRepository.findById(id).get();
            }
            if (user == null){
                throw new UsernameNotFoundException("User not found with id: " + id);
            }
            return JwtUserDetails.create(user);
        }
        catch (Exception e) {
            throw new UsernameNotFoundException("User not found with id: " + id);
        }
    }

}