package org.example;

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
                        resultSet.getString(5)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    public boolean addUser(String username, String password, String phoneNUmber,String email) {
        String sql1 = """
                SELECT
                EXISTS( SELECT * FROM person WHERE username = ? OR email = ?);
                """;
        String sql2 = """
                INSERT INTO person (username, password, phone_number, email, balance)
                VALUES (?, ?, ?, ?, 0);
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
                preparedStatement2.setString(2, password);
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

    public boolean addProperty(int personId, int square, int floors, String address, String description, java.math.BigDecimal price, Building buildingType){
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
}