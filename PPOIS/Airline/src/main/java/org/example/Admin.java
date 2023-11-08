package org.example;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Admin extends Person {
    private static final String ADMIN_STATUS = "ADMIN";
    private final DataBaseManager db = new DataBaseManager();

    /**
     *  create object from database
     * @param id admin id from database
     * @param username admin username from database
     * @param password admin password from database
     * @param email admin email from database
     */
    public Admin(int id, String username, String password, String email) {
        super(id, username, password, email, ADMIN_STATUS);
    }

    /**
     * display all users on console
     */
    public void showUsers(){
        List<Person> users = db.getUsers();
        System.out.println("--------------------------------------------------------------------------");
        System.out.format("%8s %32s %32s\n", "Id:", "Username:", "Email:");
        System.out.println("--------------------------------------------------------------------------");
        for (Person user : users) {
            System.out.format("%8d %32s %32s\n", user.getId(), user.getUsername(), user.getEmail());
        }
        System.out.println("--------------------------------------------------------------------------");
    }

    /**
     * sets ADMIN status to user
     * @param userID id of user from database
     * @return true if successful, false if not
     */
    public boolean makeUserAdmin(int userID){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input user id: ");
//        int userID = scanner.nextInt();
        return db.setStatusForUSer(ADMIN_STATUS, userID);
    }

    /**
     * shows all flights
     */
    public void showFlights(){
        List<Flight> flights = db.getFlights();
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.format("%8s %16s %16s %30s %30s %16s\n","Id:", "Departure city:", "Arrival city:", "Departure date:", "Arrival date:", "Status:");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        for(Flight flight:flights){
            System.out.format("%8d %16s %16s %30s %30s %16s\n",
                    flight.getId(),
                    db.getCityFromAirportCode(flight.getDepartureAirportCode()),
                    db.getCityFromAirportCode(flight.getArrivalAirportCode()),
                    flight.getDepartureDate(),
                    flight.getArrivalDate(),
                    flight.getStatus());
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * adds new flight to database
     * @param flightNo number of flight
     * @param departure departure date
     * @param arrival arrival date
     * @param departureCode code of departure airport
     * @param arrivalCode code of arrival airport
     * @param status status of a flight
     * @param aircraftId id of aircraft
     * @return true if successful, false if not
     */
    public boolean addFlight(String flightNo, String departure, String arrival, String departureCode, String arrivalCode, String status, int aircraftId){
        Pattern pattern = Pattern.compile("^(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)\\s((0[1-9])|([1-2][0-9])|(3[0-1]))\\s(([0-1][0-9])|(2[0-4]))\\:([0-5][0-9])\\:([0-5][0-9])\\s(\\d{4})$");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input flight number: ");
//        String flightNo = scanner.nextLine();
        System.out.println("Input departure date: ");
//        String departure = scanner.nextLine();
        Matcher matcher = pattern.matcher(departure);
        if (!matcher.matches()){
            System.out.println("Wrong date format; Month Day hh:mm:ss Year");
            return false;
        }
        Date departureDate = new Date(departure);
        java.sql.Timestamp departureTimestamp = new java.sql.Timestamp(departureDate.getTime());
        System.out.println("Input arrival date: ");
//        String arrival = scanner.nextLine();
        matcher = pattern.matcher(arrival);
        if (!matcher.matches()){
            System.out.println("Wrong date format; Month Day hh:mm:ss Year");
            return false;
        }
        Date arrivalDate = new Date(arrival);
        java.sql.Timestamp arrivalTimestamp = new java.sql.Timestamp(arrivalDate.getTime());
        System.out.println("Input departure airport code: ");
//        String departureCode = scanner.nextLine();
        if (!db.isAirportCodeExists(departureCode)){
            System.out.println("Wrong departure code");
            return false;
        }
        System.out.println("Input arrival airport code: ");
//        String arrivalCode = scanner.nextLine();
        if (!db.isAirportCodeExists(arrivalCode)){
            System.out.println("Wrong arrival code");
            return false;
        }
        System.out.println("Input status of the flight: ");
//        String status = scanner.nextLine();
        System.out.println("Input aircraft id: ");
//        int aircraftId = scanner.nextInt();
        if (!db.isAircraftIdExists(aircraftId)){
            System.out.println("Wrong aircraft id");
            return false;
        }
        db.addFlight(flightNo, departureTimestamp, departureCode, arrivalTimestamp, arrivalCode, aircraftId, status);
        return true;
    }

    /**
     * deletes flight from database
     * @param flightNo number of flight
     * @return true if successful, false if not
     */
    public boolean deleteFlight(String flightNo){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input flight number: ");
//        String flightNo = scanner.nextInt();
        return db.deleteFlightByFlightNo(flightNo);
    }

}
