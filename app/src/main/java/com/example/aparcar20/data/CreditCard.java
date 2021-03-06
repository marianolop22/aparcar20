package com.example.aparcar20.data;


import com.google.gson.Gson;

public class CreditCard {

    private String card_number;
    private String card_expiration_month;
    private String card_expiration_year;
    private String security_code;
    private String card_holder_name;
    private CardHolderIdentification card_holder_identification;

    public CreditCard() {
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getCard_expiration_month() {
        return card_expiration_month;
    }

    public void setCard_expiration_month(String card_expiration_month) {
        this.card_expiration_month = card_expiration_month;
    }

    public String getCard_expiration_year() {
        return card_expiration_year;
    }

    public void setCard_expiration_year(String card_expiration_year) {
        this.card_expiration_year = card_expiration_year;
    }

    public String getSecurity_code() {
        return security_code;
    }

    public void setSecurity_code(String security_code) {
        this.security_code = security_code;
    }

    public String getCard_holder_name() {
        return card_holder_name;
    }

    public void setCard_holder_name(String card_holder_name) {
        this.card_holder_name = card_holder_name;
    }

    public CardHolderIdentification getCard_holder_identification() {
        return card_holder_identification;
    }

    public void setCard_holder_identification(CardHolderIdentification card_holder_identification) {
        this.card_holder_identification = card_holder_identification;
    }

//    public String getCard_holder_identification () {
//        return card_holder_identification.toJson();
//    }


    public String toJson () {
        Gson gson = new Gson();
        return gson.toJson( this );
    }
}
