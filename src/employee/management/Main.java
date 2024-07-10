package employee.management;

public class Main {
    static Employee[] employees = new Employee[10];

    public static void main(String[] args) {
        employees[0] = new Employee("Анна Сергеевна Каренина", 1, 33000);
        employees[1] = new Employee("Дмитрий Дмитриевич Гуров", 1, 62000);
        employees[2] = new Employee("Иван Иванович Чимша-Гималайский", 2, 72000);
        employees[3] = new Employee("Дмитрий Ионыч Старцев", 2, 64000);
        employees[4] = new Employee("Сергей Сергеевич Толстой", 3, 87000);
        employees[5] = new Employee("Михаил Васильевич Тонкий", 3, 78000);
        employees[6] = new Employee("Иван Дмитриевич Лаевский", 4, 59000);
        employees[7] = new Employee("Надежда Федоровна Михайлова", 4, 37000);
        employees[8] = new Employee("Андрей Ефимыч Рагин", 5, 97000);
        employees[9] = new Employee("Николай Васильевич Подгорин", 5, 72000);

        System.out.println("Информация о сотрудниках:\n" + toString(employees));
        System.out.println("Сумма затрат на ЗП в месяц: " + calculateTotalSalaries());
        System.out.println("Сотрудник с минимальной ЗП: " + findMinSalaryEmployee().getSalary());
        System.out.println("Сотрудник с максимальной ЗП: " + findMaxSalaryEmployee().getSalary());
        System.out.println("Среднее значение зарплат: " + calculateAverageSalary());
        System.out.println();
        printEmployeesFullNames();

        increaseEmployeesWages(4.6F);

        System.out.println("------------------------------");
        System.out.println("Проверка функций по департаментам");
        int deptId = 2;
        System.out.println("Сотрудник с минимальной зп в отделе " + deptId + ": " + findEmployeeWithMinSalaryInDept(deptId).getFullName());
        System.out.println("Сотрудник с максимальной зп в отделе " + deptId + ": " + findEmployeeWithMaxSalaryInDept(deptId).getFullName());
        System.out.println("Сумма затрат на ЗП в месяц в отделе " + deptId + ": " + calculateTotalDepartmentSalaries(deptId));
        System.out.println("Средняя ЗП по отделу " + deptId + ": " + calculateAverageDepartmentSalary(deptId));
        printDepartmentEmployeesFullNames(deptId);
        increaseDepartmentEmployeesSalaries(deptId, 10);
        printEmployeesWithSalaryBelowThreshold(60000);
        printEmployeesWithSalaryAboveThreshold(110000);
    }

    public static String toString(Employee[] employees) {
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

    public static float calculateTotalSalaries() {
        float totalSalaries = 0;
        for (Employee employee : employees) {
            if (employee != null) {
                totalSalaries += employee.getSalary();
            }
        }
        return totalSalaries;
    }

    public static Employee findMinSalaryEmployee() {
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

    public static Employee findMaxSalaryEmployee() {
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

    public static float calculateAverageSalary() {
        float totalSalaries = calculateTotalSalaries();
        return totalSalaries / employees.length;
    }

    public static void printEmployeesFullNames() {
        System.out.println("Все сотрудники компании: ");
        for (Employee employee : employees) {
            if (employee != null) {
                System.out.println(employee.getFullName());
            }
        }
    }

    public static void increaseEmployeesWages(float raiseRate) {
        for (Employee employee : employees) {
            if (employee != null) {
                employee.setSalary(employee.getSalary() * (1 + raiseRate / 100));
            }
        }
    }

    /* Methods by Departments */
    public static Employee[] getDepartmentEmployees(int departmentId) {
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

    public static Employee findEmployeeWithMinSalaryInDept(int departmentId) {
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

    public static Employee findEmployeeWithMaxSalaryInDept(int departmentId) {
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

    public static float calculateTotalDepartmentSalaries(int departmentId) {
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

    public static float calculateAverageDepartmentSalary(int departmentId) {
        Employee[] deptEmployees = getDepartmentEmployees(departmentId);
        if (deptEmployees == null || deptEmployees.length == 0) {
            throw new IllegalArgumentException("The employees array is null or empty.");
        }
        float totalSalaries = calculateTotalDepartmentSalaries(departmentId);
        return totalSalaries / deptEmployees.length;
    }

    public static void printDepartmentEmployeesFullNames(int departmentId) {
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

    public static void increaseDepartmentEmployeesSalaries(int departmentId, float raisePercentage) {
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

    public static void printEmployeesWithSalaryBelowThreshold(int maxSalaryThreshold) {
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


    public static void printEmployeesWithSalaryAboveThreshold(int minSalaryThreshold) {
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