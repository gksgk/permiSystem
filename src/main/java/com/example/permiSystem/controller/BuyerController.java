package com.example.permiSystem.controller;

import com.example.permiSystem.dto.AuthResponse;
import com.example.permiSystem.dto.LoginRequest;
import com.example.permiSystem.entity.User;
import com.example.permiSystem.security.CustomUserDetails;
import com.example.permiSystem.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buyer")
public class BuyerController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Authenticate buyer
            System.out.println("11");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(), loginRequest.getPassword()
                    )
            );
            System.out.println("12");

            // Check if user has ROLE_BUYER
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = userDetails.getUser(); // Extract the User object from CustomUserDetails
            System.out.println("13");
            boolean isBuyer = user.getUserRoles().stream()
                    .anyMatch(userRole -> "BUYER".equals(userRole.getRole().getRoleName().toUpperCase()));

            System.out.println("14");
            if (!isBuyer) {
                throw new Exception("User is not a buyer");
            }

            System.out.println("15");

            // Generate JWT token
            String token = jwtTokenProvider.generateToken(authentication);

            System.out.println("16");

            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials or role mismatch");
        }
    }

    @GetMapping("/allOrders")
    public ResponseEntity<?> allOrders(){
        return ResponseEntity.ok("all order details");
    }

    @GetMapping("/cart")
    public ResponseEntity<?> cart(){
        return ResponseEntity.ok("this is cart details");
    }

}
