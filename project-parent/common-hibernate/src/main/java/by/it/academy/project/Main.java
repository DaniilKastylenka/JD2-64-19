package by.it.academy.project;

import by.it.academy.project.entity.Employee;
import by.it.academy.project.entity.EmployeeAddress;
import by.it.academy.project.util.HibernateUtil;
import org.hibernate.Session;

public class Main {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Employee employee = new Employee(null, "Daniil", "Kastylenka", "+375487456985", null);
        EmployeeAddress employeeAddress = new EmployeeAddress(null, "Minsk", "Pushkina", "214547", null);
        employee.setEmployeeAddress(employeeAddress);
        employeeAddress.setEmployee(employee);
        session.save(employee);
        session.getTransaction().commit();
        session.close();
        HibernateUtil.shutdown();


    }

}
