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

    public String getFlightNo() {
        return flightNo;
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

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public void setDepartureDate(java.sql.Timestamp departureDate) {
        this.departureDate = departureDate;
    }

    public void setArrivalDate(java.sql.Timestamp arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public void setAircraftId(int aircraftId) {
        this.aircraftId = aircraftId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
