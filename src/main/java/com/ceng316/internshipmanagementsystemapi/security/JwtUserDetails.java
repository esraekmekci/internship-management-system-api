package com.ceng316.internshipmanagementsystemapi.security;

import com.ceng316.internshipmanagementsystemapi.entities.Role;
import com.ceng316.internshipmanagementsystemapi.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class JwtUserDetails implements UserDetails {

    public Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private JwtUserDetails(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static JwtUserDetails create(User user) {
        List<GrantedAuthority> authoritiesList = new ArrayList<>();
        if (user.getRole().equals(Role.STUDENT)) {
            authoritiesList.add(new SimpleGrantedAuthority("STUDENT"));
        }
        if (user.getRole().equals(Role.SECRETARY)) {
            authoritiesList.add(new SimpleGrantedAuthority("SECRETARY"));
        }
        if (user.getRole().equals(Role.COORDINATOR)) {
            authoritiesList.add(new SimpleGrantedAuthority("COORDINATOR"));
        }
        if (user.getRole().equals(Role.COMPANYREP)) {
            authoritiesList.add(new SimpleGrantedAuthority("COMPANYREP"));
        }
        authoritiesList.add(new SimpleGrantedAuthority("USER"));
        return new JwtUserDetails(user.getId(), user.getName(), user.getPassword(), authoritiesList);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}