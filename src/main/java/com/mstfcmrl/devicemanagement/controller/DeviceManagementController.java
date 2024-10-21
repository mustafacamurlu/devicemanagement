package com.mstfcmrl.devicemanagement.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devicemanagement")
public class DeviceManagementController {

    @GetMapping("/devices")
    public String publicHello() {
        return "Hello, this is a public endpoint!";
    }

    @GetMapping("/devices/{id}")
    public String getDeviceWithId(@PathVariable String id) {
        return "Hello, this is a public endpoint! Your id: " + id;
    }

    @PostMapping("/devices")
    @PreAuthorize("hasScope('create:device')")
    public String createDevice(@RequestParam("name") String name) {
        // Proceed with device creation logic
        return "Hello, this is a public endpoint! Your name: " + name;
    }

    @GetMapping("/secure/hello")
    public String secureHello(@RequestHeader(value = "Authorization", required = true) String authorizationHeader) {
        if (authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            return "Hello, this is a secure endpoint! Your token: " + token;
        }
        return "Hello, this is a secure endpoint, but no valid token was provided.";
    }
}