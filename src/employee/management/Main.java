package employee.management;

public class Main {
    public static void main(String[] args) {
        EmployeeBook employeeBook = new EmployeeBook(10);
        employeeBook.addEmployee(new Employee("Анна Сергеевна Каренина", 1, 33000));
        employeeBook.addEmployee(new Employee("Дмитрий Дмитриевич Гуров", 1, 62000));
        employeeBook.addEmployee(new Employee("Иван Иванович Чимша-Гималайский", 2, 72000));
        employeeBook.addEmployee(new Employee("Дмитрий Ионыч Старцев", 2, 64000));
        employeeBook.addEmployee(new Employee("Сергей Сергеевич Толстой", 3, 87000));
        employeeBook.addEmployee(new Employee("Михаил Васильевич Тонкий", 3, 78000));
        employeeBook.addEmployee(new Employee("Иван Дмитриевич Лаевский", 4, 59000));
        employeeBook.addEmployee(new Employee("Надежда Федоровна Михайлова", 4, 37000));
        employeeBook.addEmployee(new Employee("Андрей Ефимыч Рагин", 5, 97000));
        employeeBook.addEmployee(new Employee("Николай Васильевич Подгорин", 5, 72000));
        System.out.println(employeeBook.addEmployee(new Employee("Сотрудник Номер Одиннадцать", 120, 1000000)));

        System.out.println("Информация о сотрудниках:\n" + employeeBook);
        employeeBook.removeEmployee(1);
        System.out.println("Информация о сотрудниках:\n" + employeeBook);
        employeeBook.addEmployee(new Employee("Анна Сергеевна Каренина", 1, 33000));
        System.out.println("Информация о сотрудниках:\n" + employeeBook);

        System.out.println("Получить сотрудника по id = 5: " + employeeBook.findEmployeeById(5));
        System.out.println("Получить сотрудника по id = 15: " + employeeBook.findEmployeeById(15));


        System.out.println("Сумма затрат на ЗП в месяц: " + employeeBook.calculateTotalSalaries());
        System.out.println("Сотрудник с минимальной ЗП: " + employeeBook.findMinSalaryEmployee().getSalary());
        System.out.println("Сотрудник с максимальной ЗП: " + employeeBook.findMaxSalaryEmployee().getSalary());
        System.out.println("Среднее значение зарплат: " + employeeBook.calculateAverageSalary());
        System.out.println();
        employeeBook.printEmployeesFullNames();

        employeeBook.increaseEmployeesWages(4.6F);

        employeeBook.printEmployeesWithSalaryBelowThreshold(60000);
        employeeBook.printEmployeesWithSalaryAboveThreshold(110000);

        System.out.println("------------------------------");
        System.out.println("Проверка функций по департаментам");
        int deptId = 2;
        System.out.println("Сотрудник с минимальной зп в отделе " + deptId + ": " + employeeBook.findEmployeeWithMinSalaryInDept(deptId).getFullName());
        System.out.println("Сотрудник с максимальной зп в отделе " + deptId + ": " + employeeBook.findEmployeeWithMaxSalaryInDept(deptId).getFullName());
        System.out.println("Сумма затрат на ЗП в месяц в отделе " + deptId + ": " + employeeBook.calculateTotalDepartmentSalaries(deptId));
        System.out.println("Средняя ЗП по отделу " + deptId + ": " + employeeBook.calculateAverageDepartmentSalary(deptId));
        employeeBook.printDepartmentEmployeesFullNames(deptId);
        employeeBook.increaseDepartmentEmployeesSalaries(deptId, 10);
    }
}