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

    /**
     * @param id flight id
     * @param flightNo flight number
     * @param departureDate departure date
     * @param arrivalDate arrival date
     * @param departureAirportCode code of departure airport
     * @param arrivalAirportCode code of arrival airport
     * @param aircraftId aircraft id
     * @param status status of a flight
     */
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

    /**
     * @return flight id
     */
    public long getId() {
        return id;
    }

    /**
     * @return departure date
     */
    public Date getDepartureDate() {
        return departureDate;
    }

    /**
     * @return arrival date
     */
    public Date getArrivalDate() {
        return arrivalDate;
    }

    /**
     * @return code of departure airport
     */
    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    /**
     * @return code of arrival airport
     */
    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    /**
     * @return id of aircraft
     */
    public int getAircraftId() {
        return aircraftId;
    }

    /**
     * @return status of user (Admin or not)
     */
    public String getStatus() {
        return status;
    }

}
