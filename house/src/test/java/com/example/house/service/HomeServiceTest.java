package com.example.house.service;

import com.example.house.model.Home;
import com.example.house.model.HomeType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class HomeServiceTest {

    @Autowired
    MockMvc mockMvc;

    //resolves
    @MockBean
    HomeServiceImpl homeService;
    
    Home homeObject1, homeObject2, homeObject3, homeObject4, homeObject5;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        homeObject1 = new Home("jj","JoeyHome","Mumbai", 1500, HomeType.Villa);
        homeObject2 = new Home("cc","ChandlerHome", "Mumbai", 2000, HomeType.PentHouse);
        homeObject3 = new Home("rr","RossHome", "Mumbai", 4000, HomeType.Villa);
        homeObject4 = new Home("mm","MonicaHome", "Mumbai", 5000, HomeType.Apartment);
        homeObject5 = new Home("nn","RachelHome", "Mumbai", 9000, HomeType.PentHouse);
    }


    @Test
    void testFindAll_thenHomeListShouldBeReturned() {
        when(homeService.findAll()).thenReturn(
                Arrays.asList(
                    homeObject1, homeObject2
                )
        );

        List<Home> result = homeService.findAll();

        for(Home x : result) {
            System.out.println(x.getId());
            System.out.println(x.getHomeName());
        }

        Assertions.assertEquals(homeObject1.getHomeName(), result.get(0).getHomeName());
        Assertions.assertEquals(homeObject2.getHomeName(), result.get(1).getHomeName());
        Assertions.assertEquals(2, result.size());

    }

    @Test
    public void testSaveOrUpdateHome_thenHomeShouldBeReturned() {
        Home testHome = new Home("jj","JoeyHome","Mumbai", 1500, HomeType.Villa);

        Mockito.when(homeService.saveOrUpdateHome(testHome))
                .thenReturn(testHome);

        Home found = homeService.saveOrUpdateHome(testHome);

        Assertions.assertNotNull(found);

        // Value based checking
        Assertions.assertEquals(testHome.getHomeName(), found.getHomeName());
        Assertions.assertEquals(testHome.getId(), found.getId());
    }

    @Test
    public void testDeleteHomeById() {
        Home testHome = new Home("jj","JoeyHome","Mumbai", 1500, HomeType.Villa);

        homeService.deleteHomeById(testHome.getId());

        Mockito.verify(homeService, Mockito.times(1))
                .deleteHomeById(testHome.getId());
    }

    @Test
    public void testFindAllByOrderByHomeAreaDesc_thenHomesShouldBeReturned_byHomeAreaDesc() {

        List<Home> homeList = Arrays.asList(homeObject1, homeObject2, homeObject3, homeObject4, homeObject5);

        Mockito.when(homeService.findAllByOrderByHomeAreaDesc())
                .thenReturn(
                        homeList
                            .stream()
                            .sorted(
                                Comparator.comparing(Home::getHomeArea).reversed()
                            )
                            .collect(
                                    Collectors.toList()
                            )
                        );

        List<Home> foundHomesList = homeService.findAllByOrderByHomeAreaDesc();

        Assertions.assertNotNull(foundHomesList);
        int last = foundHomesList.size() - 1;
        Assertions.assertEquals(5, foundHomesList.size());
        Assertions.assertEquals(homeList.get(last).getHomeName(), foundHomesList.get(0).getHomeName());
        Assertions.assertEquals(homeList.get(last).getId(), foundHomesList.get(0).getId());

        System.out.println("__________________________");
        for(Home x : homeList) {
            System.out.println(x.getId());
            System.out.println(x.getHomeName());
            System.out.println(x.getHomeArea());
        }

        System.out.println("__________________________");
        for(Home x : foundHomesList) {
            System.out.println(x.getId());
            System.out.println(x.getHomeName());
            System.out.println(x.getHomeArea());
        }
    }

}
