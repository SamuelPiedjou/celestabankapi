package com.celestabank.celestabankapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/")
@RestController
public class DefaultController {
    @GetMapping
    public String defaultAction() {
        return ("<h1>CELESTA BANK API</h1>");
    }
}
