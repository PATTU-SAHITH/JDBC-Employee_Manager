import repository.*;
import service.EmployeeService;
import model.*;
import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try (Connection connection = DatabaseConnection.getConnection()) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            e.printStackTrace();
            sc.close();
            return;
        }

        EmployeeRepository repository = new EmployeeRepository();
        EmployeeService service = new EmployeeService(repository);

        Employee.CreateEmployee(sc, service);
        sc.close();
    }

}
