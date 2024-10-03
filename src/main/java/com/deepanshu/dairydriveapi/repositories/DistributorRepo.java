package com.deepanshu.dairydriveapi.repositories;

import com.deepanshu.dairydriveapi.entities.Distributor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DistributorRepo extends JpaRepository<Distributor,String> {
    Optional<Distributor> findByEmailToken(String token);
    Optional<Distributor> findByEmail(String email);
}
