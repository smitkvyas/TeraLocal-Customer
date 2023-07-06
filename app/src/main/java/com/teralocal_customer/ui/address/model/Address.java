package com.teralocal_customer.ui.address.model;

public class Address {

    private String line1;
    private String line2;
    private String line3;
    private String city;
    private String state;
    private Integer pincode;

    public String getLine1() {
        return line1;
    }

    public Address setLine1(String line1) {
        this.line1 = line1;
        return this;
    }

    public String getLine2() {
        return line2;
    }

    public Address setLine2(String line2) {
        this.line2 = line2;
        return this;
    }

    public String getLine3() {
        return line3;
    }

    public Address setLine3(String line3) {
        this.line3 = line3;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public Address setState(String state) {
        this.state = state;
        return this;
    }

    public Integer getPincode() {
        return pincode;
    }

    public Address setPincode(Integer pincode) {
        this.pincode = pincode;
        return this;
    }
}
