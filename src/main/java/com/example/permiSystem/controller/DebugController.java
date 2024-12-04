package com.example.permiSystem.controller;

import com.example.permiSystem.service.DebbuggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/debug")
public class DebugController {
    @Autowired
    DebbuggingService debbuggingService;

    @GetMapping("/test-login")
    public ResponseEntity<?> testLogin() {
        debbuggingService.testBuyerLogin("ankit", "abc@123");
        return ResponseEntity.ok("Test completed! Check logs for details.");
    }
}
