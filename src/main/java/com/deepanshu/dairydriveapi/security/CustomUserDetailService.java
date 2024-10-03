package com.deepanshu.dairydriveapi.security;

import com.deepanshu.dairydriveapi.entities.Distributor;
import com.deepanshu.dairydriveapi.exceptions.LoginAttemptFailedException;
import com.deepanshu.dairydriveapi.repositories.DistributorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private DistributorRepo distRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Distributor dist = this.distRepo.findByEmail(username).orElseThrow(()->new LoginAttemptFailedException());
        return dist;
    }
}
