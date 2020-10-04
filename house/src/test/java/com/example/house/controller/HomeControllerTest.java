package com.example.house.controller;

import com.example.house.HouseApplication;
import com.example.house.model.Home;
import com.example.house.model.HomeType;
import com.example.house.service.HomeService;
import com.example.house.service.HomeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {

    @Autowired
    MockMvc mockMvc;

    //resolved by MockBean
    @MockBean
    HomeServiceImpl homeService;

    @InjectMocks
    HomeController homeController;

    Home homeObject1;
    Home homeObject2;
    List<Home> homeList;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        homeObject1 = new Home("jj","JoeyHome","Mumbai", 1500, HomeType.Villa);
        homeObject2 = new Home("cc","ChandlerHome", "Mumbai", 1500, HomeType.Villa);
    }

    @Test
    void testGetAllHomes() throws Exception{

        List<Home> homeList = Arrays.asList(homeObject1, homeObject2);

        when(homeService.findAll()).thenReturn(homeList);

        mockMvc.perform(get("/house/all")).andDo(print())

                .andExpect(status().isOk())
                // json response capture
                .andExpect(jsonPath("$", hasSize(2)));

    }

    @Test
    void test_givenHome_whenFindByHomeName_thenReturnJsonArray() throws Exception{

        given(homeService.findByHomeName(homeObject1.getHomeName())).willReturn(Optional.of(homeObject1));

        mockMvc.perform(get("/house/homename/{name}", homeObject1.getHomeName())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                // json response capture
                .andExpect(
                        jsonPath(
                                "$.homeName", is(homeObject1.getHomeName())
                        )
                );
    }

    @Test
    public void test_saveHome_itShouldReturnStatusOk() throws Exception {
        given(homeService.saveOrUpdateHome(any(Home.class))).willReturn(homeObject1);

        String jsonString = objectMapper.writeValueAsString(homeObject1);

        mockMvc.perform(post("/house/save")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isOk());
    }

    @Test
    public void test_deleteHome_itShouldReturnStatusOk() throws Exception {
        given(homeService.findById(homeObject2.getId())).willReturn(Optional.of(homeObject2));
        doNothing().when(homeService).deleteHomeById(any(String.class));

        mockMvc.perform(delete("/house/delete/{homeID}", homeObject2.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
