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
        }

        EmployeeRepository repository = new EmployeeRepository();
        EmployeeService service = new EmployeeService(repository);

        System.out.println(service.getTopPaidEmployees(2));
        sc.close();
    }

    public static void newEmployee(Scanner sc, EmployeeService service) {
        System.out.print("Enter Id: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("""
                Choose Department
                1. ENGINEERING
                2. SALES
                Enter option: """);
        int deptOption = sc.nextInt();
        sc.nextLine();

        Department department;
        Role role;

        switch (deptOption) {
            case 1 -> {
                department = Department.ENGINEERING;
                System.out.print("""
                        Choose Role
                        1. SOFTWARE_ENGINEER
                        2. BACKEND_DEVELOPER
                        3. FRONTEND_DEVELOPER
                        Enter option: """);
                int roleOption = sc.nextInt();
                sc.nextLine();

                switch (roleOption) {
                    case 1:
                        role = Role.SOFTWARE_ENGINEER;
                        break;
                    case 2:
                        role = Role.BACKEND_DEVELOPER;
                        break;
                    case 3:
                        role = Role.FRONTEND_DEVELOPER;
                        break;
                    default:
                        System.out.println("Invalid role option.");
                        return;
                }
            }
            case 2 -> {
                department = Department.SALES;
                System.out.print("""
                        Choose Role
                        1. SALES_EXECUTIVE
                        2. SALES_MANAGER
                        3. ACCOUNT_MANAGER
                        Enter option: """);
                int roleOption = sc.nextInt();
                sc.nextLine();

                switch (roleOption) {
                    case 1:
                        role = Role.SALES_EXECUTIVE;
                        break;
                    case 2:
                        role = Role.SALES_MANAGER;
                        break;
                    case 3:
                        role = Role.ACCOUNT_MANAGER;
                        break;
                    default:
                        System.out.println("Invalid role option.");
                        return;
                }
            }
            default -> {
                System.out.println("Wrong department option.");
                return;
            }
        }

        System.out.print("Enter Salary: ");
        int salary = sc.nextInt();
        sc.nextLine();

        service.addEmployee(id, name, department, role, salary);
        System.out.println("Employee Added");
        sc.close();
    }
}
