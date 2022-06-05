package com.celestabank.celestabankapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/")
@RestController
public class DefaultController {
    @GetMapping
    public ResponseEntity<String> defaultAction() {
        return new ResponseEntity<>("<h1>CELESTA BANK API</h1>", HttpStatus.OK);
    }
}
