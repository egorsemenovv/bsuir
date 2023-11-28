package org.example.database;

import org.example.enums.Employee;
import org.example.enums.ServiceStatus;
import org.example.service.Service;
import org.example.worker.Electrician;
import org.example.worker.Plumber;
import org.example.worker.Worker;

import java.sql.*;
import java.util.Optional;

public class WorkerDatabase {
    private static final ServiceDatabase serviceDatabase = new ServiceDatabase();

    /**
     * gets worker from database
     *
     * @param username        worker`s username
     * @param encodedPassword workers`s password (encoded)
     * @return worker if such exists
     */
    public Optional<Worker> getWorkerFromDatabase(String username, String encodedPassword) {
        Optional<Worker> worker = Optional.empty();
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
                if (resultSet.getString(6).equals(Employee.PLUMBER.name())) {
                    worker = Optional.of(new Plumber(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getBigDecimal(4),
                            resultSet.getBigDecimal(5)));
                } else if (resultSet.getString(6).equals(Employee.ELECTRICIAN.name())) {
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

    /**
     * adds worker to database
     *
     * @param username        worker`s username
     * @param encodedPassword worker`s password (encoded)
     * @param employee        worker`s position
     * @return true if successful, false if not
     */
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
                preparedStatement2.setString(4, employee.name());
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

    /**
     * sets status for worker (busy or not)
     *
     * @param workerId worker`s id
     * @param status   true or false (busy or not)
     * @return true if successful, false if not
     */
    public boolean setStatusForWorker(int workerId, boolean status) {
        String sql = """
                UPDATE worker
                SET busy = ?
                WHERE id = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(2, workerId);
            preparedStatement.setBoolean(1, status);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * takes work for a worker
     *
     * @param workerId worker`s id
     * @param type     worker`s position
     * @return requested service
     */
    public Optional<Service> takeWork(int workerId, Employee type) {
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
             var preparedStatement2 = connection.prepareStatement(sql2)) {
            preparedStatement2.setInt(1, workerId);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            resultSet2.next();
            if (resultSet2.getBoolean(1)) {
                return Optional.empty();
            }
            int availableServiceId = serviceDatabase.getAvailableServiceId(type);
            if (availableServiceId == 0) {
                return Optional.empty();
            }
            setStatusForWorker(workerId, true);
            serviceDatabase.setStatusAndWorkerIdForService(availableServiceId, workerId, ServiceStatus.WORKING);
            preparedStatement1.setInt(1, availableServiceId);
            ResultSet resultSet = preparedStatement1.executeQuery();
            resultSet.next();
            return Optional.of(new Service(resultSet.getLong(1),
                    resultSet.getInt(2),
                    Employee.valueOf(resultSet.getString(3)),
                    resultSet.getInt(4),
                    ServiceStatus.valueOf(resultSet.getString(5))));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * gets service on which employee is working on
     *
     * @param workerId worker`s id
     * @return service the employee is working on
     */
    public Optional<Service> getWorkerOrder(int workerId) {
        String sql = """
                SELECT *
                FROM service
                WHERE worker_id = ? AND status = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, workerId);
            preparedStatement.setString(2, ServiceStatus.WORKING.name());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new Service(resultSet.getLong(1),
                        resultSet.getInt(2),
                        Employee.valueOf(resultSet.getString(3)),
                        resultSet.getInt(4),
                        ServiceStatus.valueOf(resultSet.getString(5))));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    /**
     * updates worker`s balance in database
     *
     * @param workerId worker`s id
     * @param balance  worker`s balance
     * @return true if successful, false if not
     */
    public boolean updateBalanceForWorker(int workerId, java.math.BigDecimal balance) {
        String sql = """
                UPDATE worker
                SET balance = ?
                WHERE id = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setBigDecimal(1, balance);
            preparedStatement.setInt(2, workerId);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * deletes worker from database by worker`s login
     *
     * @param username worker`s login
     * @return true if successful, false if not
     */
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
