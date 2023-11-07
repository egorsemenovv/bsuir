package org.example;

import java.util.Date;

public class Flight {
    private final long id;
    private String flightNo;
    private java.sql.Timestamp departureDate;
    private java.sql.Timestamp arrivalDate;
    private String departureAirportCode;
    private String arrivalAirportCode;
    private int aircraftId;
    private String status;

    public Flight(long id, String flightNo, java.sql.Timestamp departureDate, java.sql.Timestamp arrivalDate, String departureAirportCode, String arrivalAirportCode, int aircraftId, String status) {
        this.id = id;
        this.flightNo = flightNo;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureAirportCode = departureAirportCode;
        this.arrivalAirportCode = arrivalAirportCode;
        this.aircraftId = aircraftId;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public int getAircraftId() {
        return aircraftId;
    }

    public String getStatus() {
        return status;
    }

}
