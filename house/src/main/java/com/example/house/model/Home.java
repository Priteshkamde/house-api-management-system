package com.example.house.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "Houses")
public class Home {

    @Id
    private String id;

    private String homeName;
    private String homeLocation;
    private int homeArea;
    private HomeType homeType;

    public Home() { }

    public Home(String id, String homeName, String homeLocation, int homeArea, HomeType homeType) {
        this.id = id;
        this.homeName = homeName;
        this.homeLocation = homeLocation;
        this.homeArea = homeArea;
        this.homeType = homeType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public String getHomeLocation() {
        return homeLocation;
    }

    public void setHomeLocation(String homeLocation) {
        this.homeLocation = homeLocation;
    }

    public int getHomeArea() {
        return homeArea;
    }

    public void setHomeArea(int homeArea) {
        this.homeArea = homeArea;
    }

    public HomeType getHomeType() {
        return homeType;
    }

    public void setHomeType(HomeType homeType) {
        this.homeType = homeType;
    }
}
