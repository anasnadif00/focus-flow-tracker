package com.focusflow.boot.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        
        // Extract Authorization header
        String authHeader = request.getHeader("Authorization");
        
        // If no token or not Bearer token, continue without authentication
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        
        try {
            // Extract token (remove "Bearer " prefix)
            String token = authHeader.substring(7);
            
            // Validate token
            if (!jwtService.isValid(token)) {
                filterChain.doFilter(request, response);
                return;
            }
            
            // Extract user ID from token
            String userId = jwtService.extractUserId(token);
            
            // Extract role from token (we stored it as a claim)
            String role = jwtService.extractRole(token);
            
            // Create authentication object
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userId,
                    null,
                    List.of(new SimpleGrantedAuthority("ROLE_" + role))
            );
            
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            
            // Set authentication in security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
        } catch (Exception e) {
            // If any error occurs, just continue without authentication
            // The security config will handle unauthorized access
        }
        
        filterChain.doFilter(request, response);
    }
}
