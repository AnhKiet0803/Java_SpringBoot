package com.example.T2507e.service;

import com.example.T2507e.dto.req.LoginUser;
import com.example.T2507e.dto.req.RegisterUser;
import com.example.T2507e.dto.res.LoginRes;
import com.example.T2507e.entity.User;
import com.example.T2507e.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public boolean register(RegisterUser input){
        User user = new User();
        user.setEmail(input.getEmail());
        user.setFullName(input.getFullName());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        userRepository.save(user);
        return true;
    }

    public LoginRes authenticate(LoginUser input){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        User user = userRepository.findByEmail(input.getEmail());
        if (user == null)
            throw new UsernameNotFoundException("Email or password is not correct");
        String jwtToken = jwtService.generateToken(user);
        LoginRes loginRes = new LoginRes();
        loginRes.setToken(jwtToken);
        return loginRes;
    }
}
