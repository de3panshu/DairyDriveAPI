package com.deepanshu.dairydriveapi.services.impl;

import com.deepanshu.dairydriveapi.configs.AppConfigurations;
import com.deepanshu.dairydriveapi.entities.Distributor;
import com.deepanshu.dairydriveapi.exceptions.ResourceNotFoundException;
import com.deepanshu.dairydriveapi.payloads.DistributorDto;
import com.deepanshu.dairydriveapi.repositories.DistributorRepo;
import com.deepanshu.dairydriveapi.services.DistributorService;
import com.deepanshu.dairydriveapi.services.EmailService;
import com.deepanshu.dairydriveapi.utilities.Helper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DistributorServiceImpl implements DistributorService {
    @Autowired
    private DistributorRepo distRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AppConfigurations appConfig;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public DistributorDto create(DistributorDto distributorDto) {
        String emailToken = UUID.randomUUID().toString();
        String encodedPassword = passwordEncoder.encode(distributorDto.getPassword());
        distributorDto.setDeleted(false);
        distributorDto.setVerified(false);
        distributorDto.setPassword(encodedPassword);
        Distributor dist = DistributorDto.dto2Model(distributorDto,modelMapper);
        dist.setEmailToken(emailToken);

        DistributorDto savedDistDto =  DistributorDto.model2Dto(this.distRepo.save(dist),modelMapper);
        //sending the email for account verification.
        emailService.sendEmailWithHtmlContent(savedDistDto.getEmail(),"DairyDrive: Email Verification", Helper.getBodyForEmailVerification(savedDistDto.getName(),emailToken));
        return savedDistDto;
    }

    @Override
    public DistributorDto update(DistributorDto oldDistributor) {
        Distributor distributor = this.distRepo
                .findById(oldDistributor.getId())
                .orElseThrow( ()-> new ResourceNotFoundException("Distributor","Id",oldDistributor.getId()));
        distributor.setEmail(oldDistributor.getEmail());
        distributor.setPassword(oldDistributor.getPassword());
        distributor.setContact(oldDistributor.getContact());
        distributor.setName(oldDistributor.getName());
        return DistributorDto.model2Dto(this.distRepo.save(distributor),modelMapper);
    }

    @Override
    public DistributorDto getById(String distributorId) {
        return DistributorDto.model2Dto(this.distRepo.findById(distributorId).orElseThrow(() -> new ResourceNotFoundException("Distributor","Id",distributorId)),modelMapper);
    }

    @Override
    public DistributorDto delete(String distributorId) {
        DistributorDto result = DistributorDto.model2Dto(this.distRepo.findById(distributorId).orElseThrow(()->new ResourceNotFoundException("Distributor","Id",distributorId)),modelMapper);
        this.distRepo.deleteById(distributorId);
        return result;
    }

    @Override
    public List<DistributorDto> getAll() {
        return distRepo.findAll().stream().map(distributor -> DistributorDto.model2Dto(distributor,this.modelMapper)).toList();
    }

    @Override
    public DistributorDto verifyByEmailToken(String emailToken) {
        Distributor distributor = distRepo.findByEmailToken(emailToken).orElseThrow(() -> new ResourceNotFoundException("Invalid Email Token."));
        boolean isAlreadyVerified = distributor.isVerified();
        if(!isAlreadyVerified){
            distributor.setVerified(true);
            distributor.setDaysRemaining(appConfig.getTrialDays());
            distributor = distRepo.save(distributor);
        }
        return DistributorDto.model2Dto(distributor,modelMapper);
    }


}
