package model;

import java.util.Scanner;

import service.EmployeeService;

public class Employee {
    private int id;
    private String name;
    private Department department;
    private Role role;
    private int salary;

    public Employee(int id, String name, Department department, Role role, int salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.role = role;
        this.salary = salary;
    }

    public static void CreateEmployee(Scanner sc, EmployeeService service) {
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    public Role getRole() {
        return role;
    }

    public int getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + department +
                ", role=" + role +
                ", salary=" + salary +
                '}';
    }
}
