package com.example.restTemplate.practice.controllers;


import com.example.restTemplate.practice.services.RestTemplateImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/app")
public class RestController {

    private final RestTemplateImpl restT;

    public RestController(RestTemplateImpl restT) {
        this.restT = restT;
    }



    @GetMapping("/getUserList")

    public ResponseEntity<?> getUserList(){


    return new ResponseEntity<>(restT.getUserList(), HttpStatus.OK);

    }



}
