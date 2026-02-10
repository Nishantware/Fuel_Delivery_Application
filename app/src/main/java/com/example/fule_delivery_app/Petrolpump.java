package com.example.fule_delivery_app;

public class Petrolpump {

    String name,address,number,time,charges,imageuri;
    private double latitude;
    private double longitude;

    public Petrolpump() {
    }

    public Petrolpump(String name, String address, String number, String time, String charges, String imageuri, double latitude, double longitude) {
        this.name = name;
        this.address = address;
        this.number = number;
        this.time = time;
        this.charges = charges;
        this.imageuri = imageuri;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCharges() {
        return charges;
    }

    public void setCharges(String charges) {
        this.charges = charges;
    }

    public String getImageuri() {
        return imageuri;
    }

    public void setImageuri(String imageuri) {
        this.imageuri = imageuri;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
