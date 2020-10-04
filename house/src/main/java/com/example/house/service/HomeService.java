package com.example.house.service;

import com.example.house.model.Home;

import java.util.List;
import java.util.Optional;

//@Service
public interface HomeService {

    /////////////////// Methods here ///////////////////////

    Home saveOrUpdateHome(Home home);

    Optional<Home> findByHomeName(String name);

    Optional<Home> findById(String id);

    List<Home> findAll();

    void deleteHomeById(String id);

    List<Home> findAllByOrderByHomeAreaDesc();
}
