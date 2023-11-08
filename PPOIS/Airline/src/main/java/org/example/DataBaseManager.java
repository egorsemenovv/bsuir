package org.example;

import java.sql.*;
import java.util.*;

public class DataBaseManager {
    public static final String USERNAME_KEY = "db.username";
    public static final String PASSWORD_KEY = "db.password";
    public static final String URL_KEY = "db.url";

    /**
     * opens new connection to database
     * @return connection to database
     */
    public static Connection open() {
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param username person`s username
     * @param password person`s password (encoded)
     * @return user from database by username and password
     */
    public Person getUserFromDataBase(String username, String password) {
        Person person = null;
        String sql = """
                SELECT *
                FROM person
                WHERE username = ? AND password = ?;
                """;
        try (var connection = DataBaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getString(5).equals("USER")) {
                    person = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                } else {
                    person = new Admin(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;
    }

    /**
     * adds new user to database
     * @param username person`s username
     * @param password person`s password
     * @param email person`s email
     * @return true if successful, false if not
     */
    public boolean addUser(String username, String password, String email) {
        Person person = null;
        String sql1 = """
                SELECT
                EXISTS( SELECT * FROM person WHERE username = ? OR email = ?);
                """;
        String sql2 = """
                INSERT INTO person (username, password, email, person_status)
                VALUES (?, ?, ?, 'USER')
                """;

        try (var connection = DataBaseManager.open();
             var preparedStatement1 = connection.prepareStatement(sql1)) {
            preparedStatement1.setString(1, username);
            preparedStatement1.setString(2, email);
            ResultSet resultSet = preparedStatement1.executeQuery();
            resultSet.next();
            if (!resultSet.getBoolean(1)) {
                PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
                preparedStatement2.setString(1, username);
                preparedStatement2.setString(2, password);
                preparedStatement2.setString(3, email);
                preparedStatement2.execute();
            } else {
                System.out.println("User with such username or email is already exists");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    /**
     * adds flight to database
     * @param flightNo flight number
     * @param departureTimestamp departure date
     * @param departureCode departure airport code
     * @param arrivalTimestamp arrival date
     * @param arrivalCode arrival airport code
     * @param aircraftId aircraft id
     * @param status status of a flight
     */
    public void addFlight(String flightNo, java.sql.Timestamp departureTimestamp, String departureCode, java.sql.Timestamp arrivalTimestamp, String arrivalCode, int aircraftId, String status) {
        String sql = """
                INSERT INTO flight(flight_no,departure_date, departure_airport_code, arrival_date, arrival_airport_code, aircraft_id, status)
                VALUES (?, ?, ?, ?, ?, ?, ?);
                """;
        try (var connection = DataBaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, flightNo);
            preparedStatement.setTimestamp(2, departureTimestamp);
            preparedStatement.setString(3, departureCode);
            preparedStatement.setTimestamp(4, arrivalTimestamp);
            preparedStatement.setString(5, arrivalCode);
            preparedStatement.setInt(6, aircraftId);
            preparedStatement.setString(7, status);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return list of flights from database
     */
    public List<Flight> getFlights(){
        String sql = """
                SELECT *
                FROM flight;
                """;
        List<Flight> flights = new ArrayList<>();
        try (var connection = DataBaseManager.open();
        var preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                flights.add(new Flight(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getTimestamp(3),
                        resultSet.getTimestamp(5),
                        resultSet.getString(4),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getString(8)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flights;
    }

    /**
     * deletes flight by flight number
     * @param flightNo flight number
     * @return true if successful, false if not
     */
    public boolean deleteFlightByFlightNo(String flightNo) {
        String sql = """
                DELETE FROM flight
                WHERE flight_no = ?;
                """;
        try(var connection = DataBaseManager.open();
            var preparedStatemnet = connection.prepareStatement(sql)){
            preparedStatemnet.setString(1, flightNo);
            int rowsUpdated = preparedStatemnet.executeUpdate();
            return rowsUpdated !=0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * checks if airport code of such city exists
     * @param code airport code
     * @return true if exists, false if not
     */
    public boolean isAirportCodeExists(String code) {
        String sql = """
                SELECT
                EXISTS (SELECT * FROM airport WHERE code = ?);
                """;
        try (var connection = DataBaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getBoolean(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * checks if aircraft with such id exists
     * @param aircraftId id of aircraft
     * @return true if exists, false if not
     */
    public boolean isAircraftIdExists(int aircraftId) {
            String sql = """
                SELECT
                EXISTS (SELECT * FROM aircraft WHERE id = ?);
                """;
            try (var connection = DataBaseManager.open();
                 var preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, aircraftId);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                return resultSet.getBoolean(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    /**
     * checks if such city exists
     * @param city city name
     * @return true if exists, false if not
     */
    private boolean isCityExists(String city){
        String sql = """
                SELECT
                EXISTS(SELECT * FROM airport WHERE city = ?);
                """;
        try (var connection = DataBaseManager.open();
            var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, city);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getBoolean(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param city city name
     * @return airport code by city
     */
    public String getAirportCodeFromCity(String city){
        if(!isCityExists(city)){
            return "";
        }
        String sql = """
                SELECT code
                FROM airport
                WHERE city = ?;
                """;
        try (var connection = DataBaseManager.open();
        var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, city);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param airportCode airport code
     * @return city of this airport by code
     */
    public  String getCityFromAirportCode(String airportCode){
        String sql = """
                SELECT city
                FROM airport
                WHERE code = ?;
                """;
        try (var connection = DataBaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, airportCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param status status to set for user
     * @param userId id of user
     * @return true if successful, false if not
     */
    public boolean setStatusForUSer(String status, int userId){
        String sql = """
                UPDATE person
                SET person_status = ?
                WHERE id = ?;
                """;
        try (var connection = DataBaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,status);
            preparedStatement.setInt(2,userId);
            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * deletes user
     * @param userId id of a user
     * @return true if successful, false if not
     */
    public boolean deleteUser(int userId){
        String sql = """
                DELETE FROM person
                WHERE id = ?;
                """;
        try (var connection = DataBaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, userId);
            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return list of users
     */
    public List<Person> getUsers(){
        String sql = """
                SELECT *
                FROM person
                WHERE person_status = 'USER';
                """;
        List<Person> users = new ArrayList<>();
        try (var connection = DataBaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                users.add(new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    /**
     * add ticket to database
     * @param userId user id
     * @param passengerName passenger name
     * @param flightId flight id
     * @param seatNo seat number
     * @return true if successful, false if not
     */
    public boolean addTicket(int userId, String passengerName, long flightId, String seatNo) {
        String sql1 = """
                SELECT
                EXISTS( SELECT * FROM ticket WHERE user_id = ? AND passenger_name = ? AND flight_id = ? AND seat_no = ?);
                """;
        String sql2 = """
                INSERT INTO ticket(user_id, passenger_name, flight_id, seat_no)
                VALUES (?, ?, ?, ?);
                """;
        try (var connection = DataBaseManager.open();
             var preparedStatement1 = connection.prepareStatement(sql1)) {
            preparedStatement1.setInt(1, userId);
            preparedStatement1.setString(2, passengerName);
            preparedStatement1.setLong(3, flightId);
            preparedStatement1.setString(4, seatNo);
            ResultSet resultSet = preparedStatement1.executeQuery();
            resultSet.next();
            if (!resultSet.getBoolean(1)) {
                PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
                preparedStatement2.setInt(1, userId);
                preparedStatement2.setString(2, passengerName);
                preparedStatement2.setLong(3, flightId);
                preparedStatement2.setString(4, seatNo);
                preparedStatement2.execute();
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * deletes ticket from database
     * @param userId user id
     * @param passengerName passenger name
     * @param flightId flight id
     * @param seatNo seat number
     * @return true if successful, false if not
     */
        public boolean deleteTicket(int userId, String passengerName, long flightId, String seatNo){
        String sql = """
                DELETE FROM ticket
                WHERE user_id = ? AND passenger_name = ? AND flight_id = ? AND seat_no = ?;
                """;
        try (var connection = DataBaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, passengerName);
            preparedStatement.setLong(3, flightId);
            preparedStatement.setString(4, seatNo);
            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param departureAirportCode code of departure airport
     * @param arrivalAirportCode code arrival airport
     * @return list of flights by airport codes
     */
    public List<Flight> getFlightsByAirportCodesForUsers(String departureAirportCode, String arrivalAirportCode) {
        if(!isAirportCodeExists(departureAirportCode) || !isAirportCodeExists(arrivalAirportCode)){
            return null;
        }
        java.sql.Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        String sql = """
                SELECT *
                FROM flight
                WHERE departure_airport_code = ? AND arrival_airport_code = ? AND departure_date > ? AND status = 'SCHEDULED';
                """;

        List<Flight> flights = new ArrayList<>();
        try (var connection = DataBaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, departureAirportCode);
            preparedStatement.setString(2, arrivalAirportCode);
            preparedStatement.setTimestamp(3, currentTime);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                flights.add(new Flight(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getTimestamp(3),
                        resultSet.getTimestamp(5),
                        resultSet.getString(4),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getString(8)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flights;
    }

    /**
     * checks if such seat exists
     * @param aircraftId aircraft id
     * @param seatNo seat number
     * @return true if exists, false if not
     */
    public boolean isSeatExists(int aircraftId, String seatNo){
        String sql = """
                SELECT
                EXISTS( SELECT * FROM seat WHERE aircraft_id = ? and seat_no = ?);
                """;
        List<String> seats = new ArrayList<>();
        try (var connection = DataBaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, aircraftId);
            preparedStatement.setString(2, seatNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getBoolean(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param aircraftId aircraft id
     * @param flightId flight id
     * @return set of available seats on flight
     */
    public Set<String> getAvailableSeats(int aircraftId, long flightId){
        String sql1 = """
                SELECT seat_no
                FROM seat
                WHERE aircraft_id = ?;
                """;
        String sql2 = """
                SELECT seat_no
                FROM ticket
                WHERE flight_id = ?;
                """;
        Set<String> availableSeats = new HashSet<>();
        try (var connection = DataBaseManager.open();
        var preparedStatement1 = connection.prepareStatement(sql1);
        var preparedStatement2 = connection.prepareStatement(sql2)) {
            preparedStatement1.setInt(1, aircraftId);
            preparedStatement2.setLong(1, flightId);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            while (resultSet1.next()){
                availableSeats.add(resultSet1.getString(1));
            }
            while (resultSet2.next()){
                availableSeats.remove(resultSet2.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return availableSeats;
    }

    /**
     * @param userId id of a user
     * @return list of user tickets
     */
    public List<Ticket> getUserTickets(int userId){
        String sql = """
                SELECT *
                FROM ticket
                WHERE user_id = ?;
                """;
        List<Ticket> tickets = new ArrayList<>();
        try(var connection = DataBaseManager.open();
        var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                tickets.add(new Ticket(resultSet.getLong(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getLong(4),
                        resultSet.getString(5)));
            }
            if (tickets.isEmpty()){
                return null;
            }
            return tickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param id flight id
     * @return Flight by id
     */
    public Flight getFlightById(long id){
        String sql = """
                SELECT *
                FROM flight
                WHERE id = ?;
                """;
        try(var connection = DataBaseManager.open();
        var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Flight(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getTimestamp(3),
                    resultSet.getTimestamp(5),
                    resultSet.getString(4),
                    resultSet.getString(6),
                    resultSet.getInt(7),
                    resultSet.getString(8));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
