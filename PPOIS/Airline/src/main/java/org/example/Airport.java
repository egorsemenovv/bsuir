package org.example;

public class Airport {

    private final String code;
    private final String country;
    private final String city;

    /**
     * @param code code of airport
     * @param country country of airport location
     * @param city city of airport location
     */
    public Airport(String code, String country, String city) {
        this.code = code;
        this.country = country;
        this.city = city;
    }

    /**
     * @return code of airport
     */
    public String getCode() {
        return code;
    }

    /**
     * @return country of airport
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return city of airport
     */
    public String getCity() {
        return city;
    }
}
