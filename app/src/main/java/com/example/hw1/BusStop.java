package com.example.hw1;

public class BusStop {
    private double latitude, longitude;
    private String name, plusCode;

    public BusStop(String name, double latitude, double longitude, String plusCode){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.plusCode = plusCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public String getPlusCode() {
        return plusCode;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlusCode(String plusCode) {
        this.plusCode = plusCode;
    }

    @Override
    public String toString() {
        return "BusStop {" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", name='" + name + '\'' +
                ", plusCode='" + plusCode + '\'' +
                '}';
    }
}
