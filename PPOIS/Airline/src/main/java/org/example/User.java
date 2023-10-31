package org.example;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class User extends Person {
    private static final String USER_STATUS = "USER";

    private static final DataBaseManager db = new DataBaseManager();

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
                    DataBaseManager.getCityFromAirportCode(flight.getDepartureAirportCode()),
                    DataBaseManager.getCityFromAirportCode(flight.getArrivalAirportCode()),
                    flight.getDepartureDate(),
                    flight.getArrivalDate());
        }
        System.out.println("--------------------------------------------------------------------------------------------------------");
    }

    public boolean buyTicket() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input departure city: ");
        String departureCity = scanner.nextLine();
        System.out.println("Input arrival city: ");
        String arrivalCity = scanner.nextLine();
        List<Flight> flights = db.getFlightsByAirportCodesForUsers(db.getAirportCodeFromCity(departureCity), db.getAirportCodeFromCity(arrivalCity));
        if (flights == null) {
            System.out.println("No such flights");
            return false;
        }
        showFlights(flights);
        System.out.println("Input flight number: ");
        int flightNumber = scanner.nextInt();
        if (flightNumber > flights.size()) {
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
        String seat = scanner1.nextLine();
        if (!availableSeats.contains(seat) || !db.isSeatExists(flights.get(flightNumber - 1).getAircraftId(), seat)) {
            System.out.println("The seat is taken or doesn't exist");
            return false;
        }
        return db.addTicket(getId(), getUsername(), flights.get(flightNumber - 1).getId(), seat);
    }

    public boolean cancelTicket() {
        List<Ticket> tickets = db.getUserTickets(this.getId());
        if (tickets == null) {
            System.out.println("No such flights");
            return false;
        }
        showUserTickets(tickets);
        showUserTickets(tickets);
        return true;
    }

    public void showUserTickets(List<Ticket> tickets) {

    }

    @Override
    public void hello() {
        System.out.println("I`m user of this service");
    }

    @Override
    public void startService() {

    }
}
