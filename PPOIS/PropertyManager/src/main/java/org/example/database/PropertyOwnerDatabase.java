package org.example.database;

import org.example.enums.Building;
import org.example.propertyowner.Property;
import org.example.propertyowner.PropertyOwner;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class PropertyOwnerDatabase {

    /**
     * gets user from database
     *
     * @param username        person`s username
     * @param encodedPassword person`s password (encoded)
     * @return user if such exists
     */
    public Optional<PropertyOwner> getUserFromDatabase(String username, String encodedPassword) {

        Optional<PropertyOwner> user = Optional.empty();
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
                user = Optional.of(new PropertyOwner(
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


    /**
     * adds user to database
     *
     * @param username        person`s username
     * @param encodedPassword person`s password (encoded)
     * @param phoneNUmber     person`s phone number
     * @param email           person`s email
     * @return true if successful, false if not
     */
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


    /**
     * adds property to database
     *
     * @param personId     property`s id
     * @param square       property`s square
     * @param floors       property`s number of floors
     * @param address      property`s address
     * @param description  property`s description
     * @param price        property`s price
     * @param buildingType property`s type
     * @return true if successful, false if not
     */
    public boolean addPropertyToDatabase(int personId, int square, int floors, String address, String description, java.math.BigDecimal price, Building buildingType) {
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
                preparedStatement2.setString(7, buildingType.name());
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


    /**
     * deletes property from database by address
     *
     * @param address property`s address
     * @return true if successful false if not
     */
    public boolean deletePropertyFromDatabase(String address) {
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


    /**
     * gets properties by their type
     *
     * @param buildingType property`s type
     * @return linked list of properties with such type
     */
    public List<Property> getPropertiesByType(Building buildingType) {
        List<Property> propertiesList = new LinkedList<>();
        String sql = """
                SELECT *
                FROM property
                WHERE type = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, buildingType.name());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
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

    /**
     * updates user`s balance in database
     *
     * @param userId  user`s id
     * @param balance user`s balance
     * @return true if successful, false if not
     */
    public boolean updateBalanceForUser(int userId, java.math.BigDecimal balance) {
        String sql = """
                UPDATE person
                SET balance = ?
                WHERE id = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setBigDecimal(1, balance);
            preparedStatement.setInt(2, userId);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * sets status for property (for sale or not)
     *
     * @param userId     user`s id
     * @param propertyId property`s id
     * @param status     status (boolean, for sale or not)
     * @return true if successful, false if not
     */
    public boolean setStatusForProperty(int userId, int propertyId, boolean status) {
        String sql = """
                UPDATE property
                SET for_sale = ?
                WHERE id = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(2, propertyId);
            preparedStatement.setBoolean(1, status);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * gets property`s price by id
     *
     * @param propertyId property`s id
     * @return price
     */
    public java.math.BigDecimal getPropertyPrice(int propertyId) {
        String sql = """
                SELECT price
                FROM property
                WHERE id = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, propertyId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getBigDecimal(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * sets id for property
     *
     * @param userId     user`s id
     * @param propertyId property`s id
     * @return true if successful, false if not
     */
    public boolean setUserIdForProperty(int userId, int propertyId) {
        String sql = """
                UPDATE property
                SET person_id = ?
                WHERE id = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, propertyId);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * gets owner`s id from properties table by property`s id
     *
     * @param propertyId property`s id
     * @return user`s id
     */
    public int getUserIdFromProperty(int propertyId) {
        String sql = """
                SELECT person_id
                FROM property
                WHERE id = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, propertyId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    /**
     * deletes user from database by user`s login
     *
     * @param username user`s login
     * @return true if successful, false if not
     */
    public boolean deleteUserByUsername(String username) {
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
}
