package org.example;

public class Ticket {
    long id;
    int user_id;
    String passengerName;
    long flightId;
    String seatNo;

    public Ticket(long id, int user_id, String passengerName, long flightId, String seatNo  ) {
        this.id = id;
        this.user_id = user_id;
        this.passengerName = passengerName;
        this.flightId = flightId;
        this.seatNo = seatNo;
    }

    public long getFlightId() {
        return flightId;
    }

    public String getSeatNo() {
        return seatNo;
    }
}