package org.example.database;

import org.example.enums.Employee;
import org.example.enums.ServiceStatus;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceDatabase {

    /**
     * adds request for a service
     *
     * @param propertyId property`s id
     * @param employee   needed worker`s position
     * @return true if successful, false if not
     */
    public boolean addRequestForService(int propertyId, Employee employee) {
        String sql = """
                INSERT INTO service(property_id, worker_type, worker_id, status)
                VALUES (?, ?, null, ?)
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, propertyId);
            preparedStatement.setString(2, employee.name());
            preparedStatement.setString(3, ServiceStatus.REQUESTED.name());
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * sets status for worker by id in service table
     *
     * @param serviceId service`s id
     * @param workerId  worker`s id
     * @param status    status of a service (REQUESTED || WORKING || FINISHED)
     * @return true if successful, false if not
     */
    public boolean setStatusAndWorkerIdForService(long serviceId, int workerId, ServiceStatus status) {
        String sql = """
                UPDATE service
                SET status = ?, worker_id = ?
                WHERE id = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(3, serviceId);
            preparedStatement.setInt(2, workerId);
            preparedStatement.setString(1, status.name());
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * gets id of a requested service
     *
     * @param type worker`s position
     * @return first found requested service (if not - 0)
     */
    public int getAvailableServiceId(Employee type) {
        String sql = """
                SELECT DISTINCT id
                FROM service
                WHERE status = ? and worker_type = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, ServiceStatus.REQUESTED.name());
            preparedStatement.setString(2, type.name());
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
     * gets address by property id
     *
     * @param propertyId property`s id
     * @return address of a property
     */
    public String getAddressByPropertyId(int propertyId) {
        String sql = """
                SELECT address
                FROM property
                WHERE id = ?;
                """;
        try (var connection = DatabaseManager.open();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, propertyId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    /**
     * clears all REQUESTED services
     *
     * @return true if successful, false if not
     */
    public boolean clearAllRequestedService() {
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
}
