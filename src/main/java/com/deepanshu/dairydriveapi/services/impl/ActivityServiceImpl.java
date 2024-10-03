package com.deepanshu.dairydriveapi.services.impl;

import com.deepanshu.dairydriveapi.entities.Activity;
import com.deepanshu.dairydriveapi.repositories.ActivityRepo;
import com.deepanshu.dairydriveapi.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityRepo activityRepo;


    @Override
    public void logActivity(Activity activity) {
        this.activityRepo.save(activity);
    }
}
