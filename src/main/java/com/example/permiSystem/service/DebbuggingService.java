package com.example.permiSystem.service;

import com.example.permiSystem.entity.User;
import com.example.permiSystem.security.CustomUserDetails;
import com.example.permiSystem.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class DebbuggingService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public void testBuyerLogin(String username, String password) {
        try {
            // Authenticate buyer
            System.out.println("11");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            System.out.println("12");

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = userDetails.getUser(); // Extract the User object from CustomUserDetails
            System.out.println("13");
            boolean isBuyer = user.getUserRoles().stream()
                    .anyMatch(userRole -> "BUYER".equals(userRole.getRole().getRoleName().toUpperCase()));

            System.out.println("14");
            if (!isBuyer) {
                System.out.println("User is not a buyer");
                return;
            }
            System.out.println("15");

            // Generate JWT token
            String token = jwtTokenProvider.generateToken(authentication);
            System.out.println("16");
            // Log token or use it for further debugging
            System.out.println("Login successful! Token: " + token);
        } catch (Exception e) {
            System.err.println("Error during login: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
