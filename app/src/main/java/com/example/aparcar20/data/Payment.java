package com.example.aparcar20.data;

import com.example.aparcar20.interfaces.Basic;
import com.google.gson.Gson;

public class Payment implements Basic {

    private String site_transaction_id;
    private String token;
    private int payment_method_id;
    private String bin;
    private double amount;
    private String currency;
    private int installments;
    private String description;
    private String payment_type;
    //private array sub_payments;
    private String establishment_name;
    private String email;

    public Payment() {
    }

    public Payment(String site_transaction_id, String token, int payment_method_id, String bin, double amount, String currency, int installments, String description, String payment_type, String establishment_name, String email) {
        this.site_transaction_id = site_transaction_id;
        this.token = token;
        this.payment_method_id = payment_method_id;
        this.bin = bin;
        this.amount = amount;
        this.currency = currency;
        this.installments = installments;
        this.description = description;
        this.payment_type = payment_type;
        this.establishment_name = establishment_name;
        this.email = email;
    }


    public String getSite_transaction_id() {
        return site_transaction_id;
    }

    public void setSite_transaction_id(String site_transaction_id) {
        this.site_transaction_id = site_transaction_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getPayment_method_id() {
        return payment_method_id;
    }

    public void setPayment_method_id(int payment_method_id) {
        this.payment_method_id = payment_method_id;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getInstallments() {
        return installments;
    }

    public void setInstallments(int installments) {
        this.installments = installments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getEstablishment_name() {
        return establishment_name;
    }

    public void setEstablishment_name(String establishment_name) {
        this.establishment_name = establishment_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
