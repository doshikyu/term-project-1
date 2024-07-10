package employee.management;

public class EmployeeBook {
    private final Employee[] employees;

    public EmployeeBook(int size) {
        employees = new Employee[size];
    }

    public boolean addEmployee(Employee employee) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = employee; // What happens to the ID when we delete an employee and then add one?
                return true;
            }
        }
        return false;
    }

    public void removeEmployee(int id) {
        employees[--id] = null;
    }

    public Employee findEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee != null && employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    public String toString() {
        StringBuilder employeesStr = new StringBuilder();
        for (Employee employee : employees) {
            if (employee != null) {
                employeesStr.append(employee.getId()).append(",")
                        .append(employee.getFullName()).append(",")
                        .append(employee.getDepartment()).append(",")
                        .append(employee.getSalary()).append("\n");
            }
        }
        return "ID,ФИО,Департамент,Зарплата\n" + employeesStr;
    }

    public float calculateTotalSalaries() {
        float totalSalaries = 0;
        for (Employee employee : employees) {
            if (employee != null) {
                totalSalaries += employee.getSalary();
            }
        }
        return totalSalaries;
    }

    public Employee findMinSalaryEmployee() {
        if (employees == null || employees.length == 0) {
            throw new IllegalArgumentException("The employees array is null or empty.");
        }
        Employee minSalaryEmployee = null;
        for (Employee employee : employees) {
            if (employee != null && (minSalaryEmployee == null || employee.getSalary() < minSalaryEmployee.getSalary()))
                minSalaryEmployee = employee;
        }
        if (minSalaryEmployee == null) throw new IllegalArgumentException("All employees in the array are null.");
        return minSalaryEmployee;
    }

    public Employee findMaxSalaryEmployee() {
        if (employees == null || employees.length == 0) {
            throw new IllegalArgumentException("The employees array is null or empty.");
        }
        Employee maxSalaryEmployee = null;
        for (Employee employee : employees) {
            if (employee != null && (maxSalaryEmployee == null || employee.getSalary() > maxSalaryEmployee.getSalary()))
                maxSalaryEmployee = employee;
        }
        if (maxSalaryEmployee == null) throw new IllegalArgumentException("All employees in the array are null.");
        return maxSalaryEmployee;
    }

    public float calculateAverageSalary() {
        float totalSalaries = calculateTotalSalaries();
        return totalSalaries / employees.length;
    }

    public void printEmployeesFullNames() {
        System.out.println("Все сотрудники компании: ");
        for (Employee employee : employees) {
            if (employee != null) {
                System.out.println(employee.getFullName());
            }
        }
    }

    public void increaseEmployeesWages(float raiseRate) {
        for (Employee employee : employees) {
            if (employee != null) {
                employee.setSalary(employee.getSalary() * (1 + raiseRate / 100));
            }
        }
    }

    /* Methods by Departments */
    public Employee[] getDepartmentEmployees(int departmentId) {
        if (employees == null || employees.length == 0) {
            return null;
        }
        Employee[] deptEmployeesTmp = new Employee[10];
        int cnt = 0;
        for (Employee employee : employees) {
            if (employee != null && employee.getDepartment() == departmentId) {
                deptEmployeesTmp[cnt++] = employee;
            }
        }
        if (cnt > 0) {
            Employee[] deptEmployees = new Employee[cnt];
            System.arraycopy(deptEmployeesTmp, 0, deptEmployees, 0, cnt);

            return deptEmployees;
        } else return null;
    }

    public Employee findEmployeeWithMinSalaryInDept(int departmentId) {
        Employee[] deptEmployees = getDepartmentEmployees(departmentId);
        if (deptEmployees == null || deptEmployees.length == 0) {
            throw new IllegalArgumentException("The employees array is null or empty.");
        }
        Employee minSalaryEmployee = null;
        for (Employee employee : deptEmployees) {
            if (employee != null && (minSalaryEmployee == null || employee.getSalary() < minSalaryEmployee.getSalary()))
                minSalaryEmployee = employee;
        }
        if (minSalaryEmployee == null) throw new IllegalArgumentException("All employees in the array are null.");
        return minSalaryEmployee;
    }

    public Employee findEmployeeWithMaxSalaryInDept(int departmentId) {
        Employee[] deptEmployees = getDepartmentEmployees(departmentId);
        if (deptEmployees == null || deptEmployees.length == 0) {
            throw new IllegalArgumentException("The employees array is null or empty.");
        }
        Employee maxSalaryEmployee = null;
        for (Employee employee : deptEmployees) {
            if (employee != null && (maxSalaryEmployee == null || employee.getSalary() > maxSalaryEmployee.getSalary()))
                maxSalaryEmployee = employee;
        }
        if (maxSalaryEmployee == null) throw new IllegalArgumentException("All employees in the array are null.");
        return maxSalaryEmployee;
    }

    public float calculateTotalDepartmentSalaries(int departmentId) {
        Employee[] deptEmployees = getDepartmentEmployees(departmentId);
        if (deptEmployees == null || deptEmployees.length == 0) {
            throw new IllegalArgumentException("The employees array is null or empty.");
        }
        float totalSalaries = 0;
        for (Employee employee : deptEmployees) {
            if (employee != null) {
                totalSalaries += employee.getSalary();
            }
        }
        return totalSalaries;
    }

    public float calculateAverageDepartmentSalary(int departmentId) {
        Employee[] deptEmployees = getDepartmentEmployees(departmentId);
        if (deptEmployees == null || deptEmployees.length == 0) {
            throw new IllegalArgumentException("The employees array is null or empty.");
        }
        float totalSalaries = calculateTotalDepartmentSalaries(departmentId);
        return totalSalaries / deptEmployees.length;
    }

    public void printDepartmentEmployeesFullNames(int departmentId) {
        System.out.println("Все сотрудники отдела " + departmentId);
        Employee[] deptEmployees = getDepartmentEmployees(departmentId);
        if (deptEmployees == null || deptEmployees.length == 0) {
            throw new IllegalArgumentException("The employees array is null or empty.");
        }
        for (Employee employee : deptEmployees) {
            if (employee != null) {
                System.out.println(employee.getFullName());
            }
        }
    }

    public void increaseDepartmentEmployeesSalaries(int departmentId, float raisePercentage) {
        Employee[] deptEmployees = getDepartmentEmployees(departmentId);
        if (deptEmployees == null || deptEmployees.length == 0) {
            throw new IllegalArgumentException("The employees array is null or empty.");
        }
        for (Employee employee : deptEmployees) {
            if (employee != null) {
                employee.setSalary(employee.getSalary() * (1 + raisePercentage / 100));
            }
        }
    }

    public void printEmployeesWithSalaryBelowThreshold(int maxSalaryThreshold) {
        System.out.println("Сотрудники с заработной платой меньше " + maxSalaryThreshold + ":");
        boolean foundEmployees = false;
        StringBuilder output = new StringBuilder();

        for (Employee employee : employees) {
            if (employee != null && employee.getSalary() < maxSalaryThreshold) {
                output.append(employee.getId()).append(" ")
                        .append(employee.getFullName()).append(" ")
                        .append(employee.getSalary()).append("\n");
                foundEmployees = true;
            }
        }

        if (foundEmployees) {
            System.out.print(output);
        } else {
            System.out.println("Не найдено");
        }
    }


    public void printEmployeesWithSalaryAboveThreshold(int minSalaryThreshold) {
        System.out.println("Сотрудники с заработной платой больше или равной " + minSalaryThreshold + ":");
        boolean foundEmployees = false;
        StringBuilder output = new StringBuilder();

        for (Employee employee : employees) {
            if (employee != null && employee.getSalary() >= minSalaryThreshold) {
                output.append(employee.getId()).append(" ")
                        .append(employee.getFullName()).append(" ")
                        .append(employee.getSalary()).append("\n");
                foundEmployees = true;
            }
        }

        if (foundEmployees) {
            System.out.print(output);
        } else {
            System.out.println("Не найдены");
        }
    }
}
