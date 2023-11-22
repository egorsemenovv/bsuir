package org.example.database;

import org.example.Building;
import org.example.Service;
import org.example.user.Property;
import org.example.user.User;
import org.example.worker.Electrician;
import org.example.worker.Employee;
import org.example.worker.Plumber;
import org.example.worker.Worker;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class DatabaseManager {
    public static final String USERNAME_KEY = "db.username";
    public static final String PASSWORD_KEY = "db.password";
    public static final String URL_KEY = "db.url";

    /**
     * opens new connection to database
     *
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

    public Optional<User> getUserFromDatabase(String username, String encodedPassword) {

        Optional<User> user = Optional.empty();
        String sql = """
                SELECT *
                FROM person
                WHERE username = ? and password = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, encodedPassword);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = Optional.of(new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getBigDecimal(6)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    public boolean addUserToDatabase(String username, String encodedPassword, String phoneNUmber, String email) {
        String sql1 = """
                SELECT
                EXISTS( SELECT * FROM person WHERE username = ? OR email = ?);
                """;
        String sql2 = """
                INSERT INTO person (username, password, phone_number, email, balance)
                VALUES (?, ?, ?, ?, 0.00);
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement1 = connection.prepareStatement(sql1)) {
            preparedStatement1.setString(1, username);
            preparedStatement1.setString(2, email);
            ResultSet resultSet = preparedStatement1.executeQuery();
            resultSet.next();
            if (!resultSet.getBoolean(1)) {
                PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
                preparedStatement2.setString(1, username);
                preparedStatement2.setString(2, encodedPassword);
                preparedStatement2.setString(3, phoneNUmber);
                preparedStatement2.setString(4, email);
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

    public boolean addPropertyToDatabase(int personId, int square, int floors, String address, String description, java.math.BigDecimal price, Building buildingType){
        String sql1 = """
                SELECT
                EXISTS( SELECT * FROM property WHERE address = ?);
                """;
        String sql2 = """
                INSERT INTO property (person_id, square, floors, address, description, price, type)
                VALUES (?, ?, ?, ?, ?, ?, ?);
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement1 = connection.prepareStatement(sql1)) {
            preparedStatement1.setString(1, address);
            ResultSet resultSet = preparedStatement1.executeQuery();
            resultSet.next();
            if (!resultSet.getBoolean(1)) {
                PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
                preparedStatement2.setInt(1, personId);
                preparedStatement2.setInt(2, square);
                preparedStatement2.setInt(3, floors);
                preparedStatement2.setString(4, address);
                preparedStatement2.setString(5, description);
                preparedStatement2.setBigDecimal(6, price);
                preparedStatement2.setString(7, buildingType.getType());
                preparedStatement2.execute();
            } else {
                System.out.println("Such property is already exists");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean deletePropertyFromDatabase(String address){
        String sql = """
                DELETE FROM property
                where address = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, address);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Property> getPropertiesByType(Building buildingType){
        List<Property> propertiesList = new LinkedList<>();
        String sql = """
                SELECT *
                FROM property
                WHERE type = ?;
                """;
        try (var connection = DatabaseManager.open();
        var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, buildingType.getType());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                propertiesList.add(new Property(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getBigDecimal(7),
                        Building.valueOf(resultSet.getString(8))
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return propertiesList;
    }

    public Optional<Worker> getWorkerFromDatabase(String username, String encodedPassword) {
        Optional<Worker> worker=Optional.empty();
        String sql = """
                SELECT *
                FROM worker
                WHERE username = ? and password = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, encodedPassword);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getString(6).equals(Employee.PLUMBER.getType())){
                    worker = Optional.of(new Plumber(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getBigDecimal(4),
                            resultSet.getBigDecimal(5)));
                } else if (resultSet.getString(6).equals(Employee.ELECTRICIAN.getType())) {
                    worker = Optional.of(new Electrician(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getBigDecimal(4),
                            resultSet.getBigDecimal(5)));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return worker;
    }

    public boolean addWorkerToDatabase(String username, String encodedPassword, Employee employee) {
        String sql1 = """
                SELECT
                EXISTS( SELECT * FROM worker WHERE username = ? );
                """;
        String sql2 = """
                INSERT INTO worker (username, password, payment, worker_type)
                VALUES (?, ?, ?, ?);
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement1 = connection.prepareStatement(sql1)) {
            preparedStatement1.setString(1, username);
            ResultSet resultSet = preparedStatement1.executeQuery();
            resultSet.next();
            if (!resultSet.getBoolean(1)) {
                PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
                preparedStatement2.setString(1, username);
                preparedStatement2.setString(2, encodedPassword);
                preparedStatement2.setBigDecimal(3, employee.getPayment());
                preparedStatement2.setString(4, employee.getType());
                preparedStatement2.execute();
            } else {
                System.out.println("Employee with such username is already exists");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean addRequestForService(int propertyId, Employee employee){
        String sql = """
                INSERT INTO service(property_id, worker_type, worker_id, status)
                VALUES (?,?, null,'REQUESTED')
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, propertyId);
            preparedStatement.setString(2, employee.getType());
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean setStatusAndWorkerIdForService(long serviceId, int workerId, String status){
        String sql = """
                UPDATE service
                SET status = ?, worker_id = ?
                WHERE id = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(3, serviceId);
            preparedStatement.setInt(2, workerId);
            preparedStatement.setString(1, status);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean setStatusForWorker(int workerId, boolean status){
        String sql = """
                UPDATE worker
                SET busy = ?
                WHERE id = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(2, workerId);
            preparedStatement.setBoolean(1, status);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private int getAvailableServiceId(String workerType){
        String sql = """
                SELECT DISTINCT id
                FROM service
                WHERE status = 'REQUESTED';
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    public Optional<Service> takeWork(int workerId, String workerType){
        String sql1 = """
                SELECT *
                FROM service
                WHERE id = ?;
                """;
        String sql2 = """
                SELECT busy
                FROM worker
                WHERE id = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement1 = connection.prepareStatement(sql1);
             var preparedStatement2 = connection.prepareStatement(sql2)){
            preparedStatement2.setInt(1, workerId);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            resultSet2.next();
            if(resultSet2.getBoolean(1)){
                return Optional.empty();
            }
            int availableServiceId = getAvailableServiceId(workerType);
            if(availableServiceId==0){
                return Optional.empty();
            }
            setStatusForWorker(workerId, true);
            setStatusAndWorkerIdForService(availableServiceId, workerId,"WORKING");
            preparedStatement1.setInt(1, availableServiceId);
            ResultSet resultSet = preparedStatement1.executeQuery();
            resultSet.next();
            return Optional.of(new Service(resultSet.getLong(1),
                    resultSet.getInt(2),
                    Employee.valueOf(resultSet.getString(3)),
                    resultSet.getInt(4),
                    resultSet.getString(5)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getAddressByPropertyId(int propertyId){
        String sql = """
                SELECT address
                FROM property
                WHERE id = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, propertyId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    public Optional<Service> getWorkerOrder(int workerId){
        String sql = """
                SELECT *
                FROM service
                WHERE worker_id = ? AND status = 'WORKING';
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, workerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
               return Optional.of(new Service(resultSet.getLong(1),
                        resultSet.getInt(2),
                        Employee.valueOf(resultSet.getString(3)),
                        resultSet.getInt(4),
                        resultSet.getString(5)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public boolean updateBalanceForUser(int userId, java.math.BigDecimal balance){
        String sql = """
                UPDATE person
                SET balance = ?
                WHERE id = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setBigDecimal(1, balance);
            preparedStatement.setInt(2, userId);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateBalanceForWorker(int workerId, java.math.BigDecimal balance){
        String sql = """
                UPDATE worker
                SET balance = ?
                WHERE id = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setBigDecimal(1, balance);
            preparedStatement.setInt(2, workerId);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean setStatusForProperty(int userId, int propertyId , boolean status){
        String sql = """
                UPDATE property
                SET for_sale = ?
                WHERE id = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(2, propertyId);
            preparedStatement.setBoolean(1, status);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public java.math.BigDecimal getPropertyPrice(int propertyId) {
        String sql = """
                SELECT price
                FROM property
                WHERE id = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, propertyId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getBigDecimal(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean setUserIdForProperty(int userId, int propertyId){
        String sql = """
                UPDATE property
                SET person_id = ?
                WHERE id = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, propertyId);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getUserIdFromProperty(int propertyId){
        String sql = """
                SELECT person_id
                FROM property
                WHERE id = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, propertyId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public boolean clearAllRequestedService(){
        String sql = """
                DELETE FROM service
                where status = 'REQUESTED';
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)) {
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteUserByUsername(String username){
        String sql = """
                DELETE FROM person
                where username = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteWorkerByUsername(String username) {
        String sql = """
                DELETE FROM worker
                where username = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}