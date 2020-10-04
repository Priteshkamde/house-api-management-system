package com.example.house;

import com.example.house.repository.HomeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = HomeRepository.class)
@ComponentScan("com.example.house.*")
public class HouseApplication {
	public static void main(String[] args) {
		SpringApplication.run(HouseApplication.class, args);
		System.out.println("\t\t ###################");
		System.out.println("\t\t #### House App ####");
		System.out.println("\t\t ###################");
	}

}
