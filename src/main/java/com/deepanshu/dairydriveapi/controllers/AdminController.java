package com.deepanshu.dairydriveapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @GetMapping("/")
    public ResponseEntity<String> dummmy(){
        return new ResponseEntity<>("Admin GET req....", HttpStatus.OK);
    }
}
