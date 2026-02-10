package com.example.fule_delivery_app;

public class PetrolRequest {


    String name, number, carNumber, type;
    int quantity;
    double latitude, longitude;

    public PetrolRequest() {
    }

    public PetrolRequest(String name, String number, String carNumber, String type, int quantity, double latitude, double longitude) {
        this.name = name;
        this.number = number;
        this.carNumber = carNumber;
        this.type = type;
        this.quantity = quantity;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
