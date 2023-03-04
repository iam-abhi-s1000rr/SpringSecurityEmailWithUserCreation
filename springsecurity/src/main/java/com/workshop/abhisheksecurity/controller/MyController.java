package com.workshop.abhisheksecurity.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/home")
public class MyController {
     @GetMapping("/public")
    public ResponseEntity<String> public_(){
        return ResponseEntity.ok(" Welome  People!");
    }

    @PreAuthorize("hasRole('ADMIN_USER')")
    @GetMapping("/admin")
   public ResponseEntity<String> admin(){
        return ResponseEntity.ok("Hello Admin !");
    }

    @PreAuthorize("hasRole('GENERAL_USER')")
    @GetMapping("/user")
   public ResponseEntity<String> user(){
         return ResponseEntity.ok("Hello user !");
    }

}
