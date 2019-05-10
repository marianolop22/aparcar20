package com.example.aparcar20.data;

import com.google.gson.Gson;

public class ParkingItem {

    private String title;
    private String email;
    private int quantity;
    private double unit_price;

    public ParkingItem(String title, String email, int quantity, double unit_price) {
        this.title = title;
        this.email = email;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(float unit_price) {
        this.unit_price = unit_price;
    }


    public String toJson() {

        Gson gson = new Gson();
        return gson.toJson( this );

    }




}
