package by.it.academy.project;

import by.it.academy.project.entity.Department;
import by.it.academy.project.entity.Employee;
import by.it.academy.project.entity.EmployeeAddress;
import by.it.academy.project.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.mapping.Array;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class HQL {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Employee employee = new Employee
                (null, "Daniil", "Kastylenka", "+375000000000", null, null);
        EmployeeAddress employeeAddress = new EmployeeAddress(null, "Minsk", "Pushkina", "220047", null);
        employee.setEmployeeAddress(employeeAddress);
        employeeAddress.setEmployee(employee);
        Department department = new Department("dev");
        employee.setDepartment(department);

        Employee employee1 = new Employee
                (null, "Roman", "Kupelev", "+375111111111", null, null);
        EmployeeAddress employeeAddress1 = new EmployeeAddress(null, "Minsk", "Ivanova", "220487", null);
        employee1.setEmployeeAddress(employeeAddress1);
        employeeAddress1.setEmployee(employee1);
        Department department1 = new Department("HR");
        employee1.setDepartment(department1);

        Employee employee2 = new Employee
                (null, "Dema", "Ivanov", "+375222222222", null, null);
        EmployeeAddress employeeAddress2 = new EmployeeAddress(null, "Brest", "Andreevskaya", "221454", null);
        employee2.setEmployeeAddress(employeeAddress2);
        employeeAddress2.setEmployee(employee2);
        Department department2 = new Department("PR");
        employee2.setDepartment(department1);

        session.save(employee);
        session.save(department);
        session.save(employee1);
        session.save(department1);
        session.save(employee2);
        session.save(department2);
        session.getTransaction().commit();
        session.close();


        //HQL запросы
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Query<Employee> selectFromEmployee = session.createQuery("from Employee", Employee.class);
        List<Employee> employees = selectFromEmployee.list();

        Query selectByCity = session.createQuery("select e.surname from Employee as e where e.employeeAddress.city = 'Minsk'");
        List names = selectByCity.list();

        Query selectCountOfEmployeeInDepartment = session.createQuery("select count(e.name) from Employee e where department.name = :name");
        selectCountOfEmployeeInDepartment.setParameter("name","hr");
        List count = selectCountOfEmployeeInDepartment.list();

        Query<Employee> queryMaxResult = session.createQuery("from Employee e", Employee.class);
        queryMaxResult.setFirstResult(0);
        queryMaxResult.setMaxResults(2);
        List<Employee> employees2 = queryMaxResult.list();

        employees2.forEach(employee3 -> log.info("empl id: {}, empl.name: {}", employee3.getEmployeeId(), employee3.getName()));
        queryMaxResult.setFirstResult(2);
        employees2 = queryMaxResult.list();
        employees2.forEach(employee3 -> log.info("empl id: {}, empl.name: {}", employee3.getEmployeeId(), employee3.getName()));

        Query updateBySurname = session.createQuery("update Employee e set e.phone =: newPhone where e.surname =: surname");
        updateBySurname.setParameter("newPhone", "+375123456789");
        updateBySurname.setParameter("surname", "kastylenka");
        updateBySurname.executeUpdate();

        Query deleteBySurname = session.createQuery("delete from Employee where surname=:surname");
        deleteBySurname.setParameter("surname", "ivanov");
        deleteBySurname.executeUpdate();

        session.getTransaction().commit();
        session.close();

        System.err.println(employees.toString());
        System.err.println(names.toString());
        System.err.println(count.toString());


        HibernateUtil.shutdown();

    }

}
