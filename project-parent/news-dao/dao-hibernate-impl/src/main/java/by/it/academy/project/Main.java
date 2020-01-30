package by.it.academy.project;

import by.it.academy.project.dao.EmployeeDao;
import by.it.academy.project.dao.EmployeeDaoImpl;
import by.it.academy.project.entity.Department;
import by.it.academy.project.entity.Employee;
import by.it.academy.project.util.HibernateUtil;

public class Main {

    private static final EmployeeDao employeeDao = EmployeeDaoImpl.getINSTANCE();

    public static void main(String[] args) {

        Department department = new Department("Dev");
        Department department1 = new Department("HR");
        Department department2 = new Department("PR");

        employeeDao.createDepartment(department);
        employeeDao.createDepartment(department1);
        employeeDao.createDepartment(department2);

        employeeDao.create(new Employee("Daniil", "Kastylenka", 500.0, 20L, department));
        employeeDao.create(new Employee(null, "Kastylenka", 1000.0, 24L, department));
        employeeDao.create(new Employee("Alexey", "Alexeev", 1250.0, 23L, department));
        employeeDao.create(new Employee("Sergey", "Sergeev", 800.0, 26L, department1));
        employeeDao.create(new Employee("Ivan", "Ivanov", 250.0, 19L, department2));
        employeeDao.create(new Employee("Alexandr", "Petrov", 1200.0, 19L, department2));

        System.err.println(employeeDao.getAll() + "\n"
        + employeeDao.getByName("Daniil") + "\n"
        + employeeDao.getAllWithNameNotNull() + "\n"
        + employeeDao.getSalaryGraterThan(1000L) + "\n"
        + employeeDao.getSalaryGraterThanOrderDesc(800L) + "\n"
        + employeeDao.getSalaryLessOrEqual(500L) + "\n"
        + employeeDao.getByAgeBetween(20,23) + "\n"
        + employeeDao.getByAgeAndName("Ivan", 19) + "\n"
        + employeeDao.getByAgeOrName("Ivan", 19) + "\n"
        + employeeDao.getEmployeeCount() + "\n"
        + employeeDao.getAverageSalary() + "\n"
        + employeeDao.getMaxSalary() + "\n"
        + employeeDao.getMinAge() + "\n"
        + employeeDao.getAverageSalaryByDep(1L));

        HibernateUtil.shutdown();
    }

}
