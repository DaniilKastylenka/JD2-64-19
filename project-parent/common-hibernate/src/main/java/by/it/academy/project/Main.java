package by.it.academy.project;

import by.it.academy.project.entity.*;
import by.it.academy.project.util.HibernateUtil;
import org.hibernate.Session;

import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        //One-to-one and One-to-many
        Employee employee = new Employee(null, "Petr", "Petrov", "+375487456985", null, null);
        EmployeeAddress employeeAddress = new EmployeeAddress(null, "Minsk", "Pushkina", "214547", null);
        Department department = new Department("dev");
        session.save(department);
        employee.setDepartment(department);
        employee.setEmployeeAddress(employeeAddress);
        employeeAddress.setEmployee(employee);
        session.save(employee);
        session.getTransaction().commit();

        //Many-to-many
        Student student = new Student(null, "Ivan", "Ivanov", new HashSet<>());
        Course course = new Course(null, "IT", new HashSet<>());
        student.getCourses().add(course);
        course.getStudents().add(student);
        session.save(student);
        session.save(course);
        session.getTransaction().commit();

        session.close();
        HibernateUtil.shutdown();


    }

}
