package org.example;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class User extends Person {
    private static final String USER_STATUS = "USER";

    private final DataBaseManager db = new DataBaseManager();

    public User(int id, String username, String password, String email) {
        super(id, username, password, email, USER_STATUS);
    }

    private void showFlights(List<Flight> flights) {

        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.format("%8s %16s %16s %30s %30s\n", "Number:", "Departure city:", "Arrival city:", "Departure date:", "Arrival date:");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        int i = 1;
        for (Flight flight : flights) {
            System.out.format("%8d %16s %16s %30s %30s\n",
                    i++,
                    db.getCityFromAirportCode(flight.getDepartureAirportCode()),
                    db.getCityFromAirportCode(flight.getArrivalAirportCode()),
                    flight.getDepartureDate(),
                    flight.getArrivalDate());
        }
        System.out.println("--------------------------------------------------------------------------------------------------------");
    }

    public boolean buyTicket(String departureCity, String arrivalCity, int flightNumber, String seat) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input departure city: ");
//        String departureCity = scanner.nextLine();
        System.out.println("Input arrival city: ");
//        String arrivalCity = scanner.nextLine();
        List<Flight> flights = db.getFlightsByAirportCodesForUsers(db.getAirportCodeFromCity(departureCity), db.getAirportCodeFromCity(arrivalCity));
        if (flights == null) {
            System.out.println("No such flights");
            return false;
        }
        showFlights(flights);
        System.out.println("Input flight number: ");
//        int flightNumber = scanner.nextInt();
        if (flightNumber > flights.size() || flightNumber < 1) {
            return false;
        }
        int aircraftId = flights.get(flightNumber - 1).getAircraftId(); // to show aircraft later
        System.out.println("Available seats: ");
        Set<String> availableSeats = db.getAvailableSeats(flights.get(flightNumber - 1).getAircraftId(), flights.get(flightNumber - 1).getId());
        for (String availableSeat :
                availableSeats) {
            System.out.print(availableSeat + " ");
        }
        System.out.println();
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Input seat: ");
//        String seat = scanner1.nextLine();
        if (!availableSeats.contains(seat) || !db.isSeatExists(flights.get(flightNumber - 1).getAircraftId(), seat)) {
            System.out.println("The seat is taken or doesn't exist");
            return false;
        }
        return db.addTicket(getId(), getUsername(), flights.get(flightNumber - 1).getId(), seat);
    }

    public boolean cancelTicket(int ticketNumber) {
        List<Ticket> tickets = db.getUserTickets(this.getId());
        if (tickets == null) {
            System.out.println("This user have no tickets");
            return false;
        }
        showUserTickets(tickets);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input ticket number: ");
//        int ticketNumber = scanner.nextInt();
        if (ticketNumber > tickets.size() || ticketNumber < 1) {
            return false;
        }
        db.deleteTicket(this.getId(), this.getUsername(), tickets.get(ticketNumber - 1).getFlightId(), tickets.get(ticketNumber - 1).getSeatNo());
        return true;
    }

    private void showUserTickets(List<Ticket> tickets) {
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.format("%8s %16s %16s %30s %30s %8s\n", "Number:", "Departure city:", "Arrival city:", "Departure date:", "Arrival date:", "Seat: ");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        int i = 1;
        Flight flight;
        for (Ticket ticket : tickets) {
            flight = db.getFlightById(ticket.getFlightId());
            System.out.format("%8d %16s %16s %30s %30s %8s\n",
                    i++,
                    db.getCityFromAirportCode(flight.getDepartureAirportCode()),
                    db.getCityFromAirportCode(flight.getArrivalAirportCode()),
                    flight.getDepartureDate(),
                    flight.getArrivalDate(),
                    ticket.getSeatNo());
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
    }
}
