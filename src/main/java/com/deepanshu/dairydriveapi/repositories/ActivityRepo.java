package com.deepanshu.dairydriveapi.repositories;

import com.deepanshu.dairydriveapi.entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepo extends JpaRepository<Activity,Integer> {
}
