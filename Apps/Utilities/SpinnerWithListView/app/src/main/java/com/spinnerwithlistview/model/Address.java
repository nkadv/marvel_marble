package com.spinnerwithlistview.model;

/**
 * Created by Technovibe on 07-04-2015.
 */
public class Address {
    private long id;
    private long cityId;
    private String buildingName;
    private String street;
    private String area;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
