package com.example.T2507e.config;

import com.example.T2507e.enums.Role;
import com.example.T2507e.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class JwtAuthenicationFilter extends OncePerRequestFilter {
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        try {
            final String jwt = authHeader.substring(7);
//            System.out.println(jwt);
            final String userEmail =jwtService.extractUsername(jwt);
//            System.out.println(userEmail);
            Authentication authentication = SecurityContextHolder.getContext()
                    .getAuthentication();
            if(userEmail != null && authentication == null){
                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
                if(jwtService.isTokenValid(jwt,userDetails)){
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    Claims claims = jwtService.extractAllClaims(jwt);
                    int roleValue = claims.get("role",Integer.class);
//                    System.out.println(roleValue);
                    Role role = Role.fromValue(roleValue);
                    authorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,null,authorities
                            );
                    authToken.setDetails(new WebAuthenticationDetailsSource()
                            .buildDetails(request)
                    );
                    System.out.println(authToken);
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        filterChain.doFilter(request,response);
    }
}
