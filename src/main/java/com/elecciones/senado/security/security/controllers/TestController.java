package com.elecciones.senado.security.security.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/security/test")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TestController {

    @GetMapping
    public String welcome() {
        return "Welcome to a secure endpoint";
    }

}
