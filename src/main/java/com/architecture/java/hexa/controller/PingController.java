package com.architecture.java.hexa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/ping")
public class PingController {

    @GetMapping
    @ResponseStatus(OK)
    public String getPing() {
        return "Pong";
    }
}
