package org.example;

public class Airport {

    private final String code;
    private final String country;
    private final String city;

    public Airport(String code, String country, String city) {
        this.code = code;
        this.country = country;
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }
}
