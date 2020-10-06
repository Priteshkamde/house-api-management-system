package com.example.house.repository;

import com.example.house.model.Home;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface HomeRepository extends MongoRepository<Home, String> {

    Optional<Home> findByHomeNameIgnoreCase(String name);

    List<Home> findAllByOrderByHomeAreaDesc();
}
