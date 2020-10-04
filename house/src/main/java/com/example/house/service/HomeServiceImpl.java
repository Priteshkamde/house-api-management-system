package com.example.house.service;

import com.example.house.model.Home;
import com.example.house.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HomeServiceImpl implements HomeService{

    @Autowired
    HomeRepository homeRepository;

    @Override
    public List<Home> findAll() {
        return homeRepository.findAll();
    }

    @Override
    public Optional<Home> findById(String id) {
        return homeRepository.findById(id);
    }

    @Override
    public Optional<Home> findByHomeName(String name) {
        return homeRepository.findByHomeNameIgnoreCase(name);
    }

    @Override
    public Home saveOrUpdateHome(Home home) {
        return homeRepository.save(home);
    }

    @Override
    public void deleteHomeById(String id) {
        homeRepository.deleteById(id);
    }

    @Override
    public List<Home> findAllByOrderByHomeAreaDesc() {
        List<Home> homeList = homeRepository.findAllByOrderByHomeAreaDesc();
        return homeList;
    }

}
