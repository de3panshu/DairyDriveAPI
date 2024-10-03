package com.deepanshu.dairydriveapi.controllers;

import com.deepanshu.dairydriveapi.payloads.DistributorDto;
import com.deepanshu.dairydriveapi.services.DistributorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private DistributorService distService;


    @PostMapping("/signup")
    public ResponseEntity<DistributorDto> signingUp(@Valid @RequestBody DistributorDto distDto){
        //Setting default values for fields
        //distributor.setDaysRemaining(this.appConfig.getTrialDays());
        distDto.setDaysRemaining(0);
        distDto.setDeleted(false);

        distDto = this.distService.create(distDto);
        return new ResponseEntity<>(distDto, HttpStatus.CREATED);
    }

    @PostMapping("/email-verification")
    public ResponseEntity<Boolean> verifyAccountViaEmail(@RequestParam ("token") String emailToken){
        DistributorDto distDto = this.distService.verifyByEmailToken(emailToken);
        if(distDto != null)
            return ResponseEntity.ok(true);
        return ResponseEntity.internalServerError().build();
    }
}
