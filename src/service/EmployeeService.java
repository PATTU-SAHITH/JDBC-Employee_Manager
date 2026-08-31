package service;

import java.util.Optional;
import java.util.Comparator;
import java.util.List;

import repository.*;
import exception.*;
import model.*;

public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public void addEmployee(int id, String name, Department department, Role role, int salary) {
        if (salary <= 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }

        if (repository.findById(id).isPresent()) {
            throw new DuplicateEmployeeException("Employee with " + id + " already exists");
        }

        Employee employee = new Employee(id, name, department, role, salary);
        repository.addEmployee(employee);
    }

    public void removeEmployee(int id) {
        repository.deleteById(id);
    }

    public Employee getEmployee(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Optional<Employee> findHigheshPaid() {
        return repository.findAll()
                .stream()
                .max(Comparator.comparing(Employee::getSalary));
    }

    public Optional<Employee> findLowestPaid() {
        return repository.findAll()
                .stream()
                .min(Comparator.comparing(Employee::getSalary));
    }

    public double getAverageSalary() {
        return repository.findAll()
                .stream()
                .mapToInt(Employee::getSalary)
                .average()
                .orElse(0.0);
    }

    public List<Employee> getEmployeesByDepartment(Department department) {
        return repository.findAll()
                .stream()
                .filter(emp -> emp.getDepartment().equals(department))
                .toList();
    }

    public List<Employee> getEmployeesByRole(Role role) {
        return repository.findAll()
                .stream()
                .filter(emp -> emp.getRole().equals(role))
                .toList();
    }

    public List<Employee> getEmployeesAboveSalary(int salary) {
        return repository.findAll()
                .stream()
                .filter(emp -> emp.getSalary() >= salary)
                .toList();
    }

    public List<Employee> sortEmployeesBySalary() {
        return repository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(Employee::getSalary))
                .toList();
    }

    public List<Employee> getTopPaidEmployees(int n) {
        return repository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                .limit(n)
                .toList();
    }
}