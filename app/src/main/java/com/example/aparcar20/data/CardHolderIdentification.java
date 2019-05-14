package com.example.aparcar20.data;

import com.google.gson.Gson;

public class CardHolderIdentification {

    private String type;
    private String number;

    public CardHolderIdentification() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String toJson () {
        Gson gson = new Gson();
        return gson.toJson( this );
    }



}