package repository;

import model.*;
import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository {

    public void addEmployee(Employee emp) {
        String sql = """
                INSERT INTO employees (id, name, department, role, salary) VALUES (?, ?, ?, ?, ?)
                """;
        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, emp.getId());
            statement.setString(2, emp.getName());
            statement.setString(3, emp.getDepartment().name());
            statement.setString(4, emp.getRole().name());
            statement.setInt(5, emp.getSalary());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to add employee", e);
        }
    }

    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Department department = Department.valueOf(resultSet.getString("department"));
                Role role = Role.valueOf(resultSet.getString("role"));
                int salary = resultSet.getInt("salary");

                Employee employee = new Employee(id, name, department, role, salary);

                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve Employees", e);
        }

        return employees;
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM employees WHERE id=?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete Employee", e);
        }
    }

    public Optional<Employee> findById(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Department department = Department.valueOf(resultSet.getString("department"));
                    Role role = Role.valueOf(resultSet.getString("role"));
                    Employee employee = new Employee(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            department,
                            role,
                            resultSet.getInt("salary"));
                    return Optional.of(employee);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find Employee", e);
        }

        return Optional.empty();
    }

}
