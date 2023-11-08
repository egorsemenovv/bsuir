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

    /**
     * @return id of a flight
     */
    public long getFlightId() {
        return flightId;
    }

    /**
     * @return seat number of this ticket
     */
    public String getSeatNo() {
        return seatNo;
    }
}
