package com.example.house.dbseeds;

import com.example.house.model.Home;
import com.example.house.model.HomeType;
import com.example.house.repository.HomeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
public class DBSeeder implements CommandLineRunner {

    public HomeRepository homeRepository;

    public DBSeeder(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    @Override
    public void run(String... args) {

        homeRepository.deleteAll();

        Home home1 = new Home("1","TomHome", "Mumbai", 1500, HomeType.Villa);
        Home home2 = new Home("2","JerryHome", "Pune", 1700, HomeType.Apartment);
        Home home3 = new Home("3","BatmanHome", "Mumbai", 20000, HomeType.PentHouse);

        List<Home> homeList = Arrays.asList(home1,home2,home3);
        homeRepository.saveAll(homeList);

        System.out.println("\t \t DB Initialization Done ");

    }
}
