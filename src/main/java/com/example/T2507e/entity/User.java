package com.example.T2507e.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.T2507e.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name",length = 100)
    private String fullName;

    @Column(length = 100)
    private String password;

    private String email;
    private Long role = 0L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return new ArrayList<>();
        Role userRole = Role.fromValue(role.intValue());

        return List.of(
                new SimpleGrantedAuthority("ROLE_" + userRole.name())
        );
    }

    public String getUsername() {
        return getEmail();
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
