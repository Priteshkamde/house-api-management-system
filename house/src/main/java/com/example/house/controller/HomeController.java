package com.example.house.controller;


import com.example.house.model.Home;
import com.example.house.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/house")
public class HomeController {

    @Autowired
    HomeService homeService;

    @GetMapping("/all")
    public List<Home> getAllHomes() {
        List<Home> homes = homeService.findAll();
        return homes;
    }

    @GetMapping("/home/{id}")
    public ResponseEntity<Home> getByHomeId(@PathVariable("id") String id) {
        Optional<Home> home = homeService.findById(id);

        if(home.isPresent()){
            return new ResponseEntity<Home>(home.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/homename/{name}")
    public ResponseEntity<Home> getByHomeName(@PathVariable("name") String name) {
        Optional<Home> home = homeService.findByHomeName(name);

        if(home.isPresent()){
            return new ResponseEntity<Home>(home.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveOrUpdateHome(@RequestBody Home home) {
        homeService.saveOrUpdateHome(home);
        return new ResponseEntity("Home added successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{homeID}")
    public ResponseEntity<?> deleteHomeById(@PathVariable String homeID) {
        homeService.deleteHomeById(homeID);
        return new ResponseEntity("Home deleted successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/maxarea")
    public List<Home> getByHomeArea(){
        List<Home> homeList = homeService.findAllByOrderByHomeAreaDesc();
        return homeList;
    }

}
