package employee.management;

import java.util.Objects;

public class Employee {
    static int idCnt;
    private final int id;
    private String fullName;
    private int department;
    private float salary;

    public Employee(String fullName, int department, float salary) {
        if (fullName.isEmpty() || department < 1 || department > 5 || salary < 0)
            throw new IllegalArgumentException("Wrong arguments passed to the constructor");
        this.id = ++idCnt;
        this.fullName = fullName;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (fullName.isEmpty())
            throw new IllegalArgumentException("Cannot set the name to an empty string");
        this.fullName = fullName;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        if (department < 1 || department > 5)
            throw new IllegalArgumentException("Department must be an integer 1-5");
        this.department = department;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        if (salary < 0)
            throw new IllegalArgumentException("Salary cannot be negative. This company does not support slavery");
        this.salary = salary;
    }

    @Override
    public boolean equals(Object employeeToCheck) {
        if (this == employeeToCheck) return true;
        if (employeeToCheck == null || getClass() != employeeToCheck.getClass()) return false;
        Employee employee = (Employee) employeeToCheck;
        return id == employee.id && Objects.equals(fullName, employee.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName);
    }
}
