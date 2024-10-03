package com.deepanshu.dairydriveapi.services;

import com.deepanshu.dairydriveapi.payloads.DistributorDto;

import java.util.List;

public interface DistributorService {
    DistributorDto create(DistributorDto distributor);
    DistributorDto update(DistributorDto oldDistributor);
    DistributorDto getById(String distributorId);
    DistributorDto delete(String distributorId);
    List<DistributorDto> getAll();
    DistributorDto verifyByEmailToken(String emailToken);
}
