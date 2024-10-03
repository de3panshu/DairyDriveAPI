package com.deepanshu.dairydriveapi.controllers;

import com.deepanshu.dairydriveapi.payloads.DistributorDto;
import com.deepanshu.dairydriveapi.services.DistributorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/distributor")
public class DistributorController {
    @Autowired
    private DistributorService distService;

    @PostMapping("/")
    public ResponseEntity<DistributorDto> create(@Valid @RequestBody DistributorDto distDto){
        distDto = this.distService.create(distDto);
        return new ResponseEntity<>(distDto, HttpStatus.CREATED);
    }
}
