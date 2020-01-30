package by.it.academy.project.dao;

import by.it.academy.project.entity.Department;
import by.it.academy.project.entity.Employee;
import by.it.academy.project.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.*;
import java.util.List;

@Slf4j
public class EmployeeDaoImpl implements EmployeeDao {

    private static final EmployeeDaoImpl INSTANCE = new EmployeeDaoImpl();

    private SessionFactory sf = HibernateUtil.getSessionFactory();
    private CriteriaBuilder cb = sf.getCriteriaBuilder();

    public static EmployeeDaoImpl getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public void createDepartment(Department department) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(department);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("error while creating department: " + e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void create(Employee employee) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("error while creating person: " + e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Employee> getAll() {
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        criteria.from(Employee.class);
        Session session = sf.openSession();
        List<Employee> employees = session.createQuery(criteria).getResultList();
        session.close();
        return employees;
    }

    @Override
    public List<Employee> getByName(String name) {
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> employee = criteria.from(Employee.class);
        criteria.select(employee).where(cb.equal(employee.get("name"), name));
        Session session = sf.openSession();
        List<Employee> employees = session.createQuery(criteria).getResultList();
        session.close();
        return employees;
    }

    @Override
    public List<Employee> getAllWithNameNotNull() {
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> employee = criteria.from(Employee.class);
        criteria.select(employee).where(cb.isNotNull(employee.get("name")));
        Session session = sf.openSession();
        List<Employee> employees = session.createQuery(criteria).getResultList();
        session.close();
        return employees;
    }

    @Override
    public List<Employee> getSalaryGraterThan(Long salary) {
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> employee = criteria.from(Employee.class);
        criteria.select(employee).where(cb.gt(employee.get("salary"), salary));
        Session session = sf.openSession();
        List<Employee> employees = session.createQuery(criteria).getResultList();
        session.close();
        return employees;
    }

    @Override
    public List<Employee> getSalaryGraterThanOrderDesc(Long salary) {
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> employee = criteria.from(Employee.class);
        criteria.orderBy(cb.desc(employee.get("salary"))).where(cb.gt(employee.get("salary"), salary));
        Session session = sf.openSession();
        List<Employee> employees = session.createQuery(criteria).getResultList();
        session.close();
        return employees;
    }

    @Override
    public List<Employee> getSalaryLessOrEqual(Long salary) {
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> employee = criteria.from(Employee.class);
        criteria.select(employee).where(cb.lessThanOrEqualTo(employee.get("salary"), salary));
        Session session = sf.openSession();
        List<Employee> employees = session.createQuery(criteria).getResultList();
        session.close();
        return employees;
    }

    @Override
    public List<Employee> getByAgeBetween(Integer from, Integer to) {
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> employee = criteria.from(Employee.class);
        criteria.select(employee).where(cb.between(employee.get("age"), from, to));
        Session session = sf.openSession();
        List<Employee> employees = session.createQuery(criteria).getResultList();
        session.close();
        return employees;
    }

    @Override
    public List<Employee> getByAgeAndName(String name, Integer age) {
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> employee = criteria.from(Employee.class);
        criteria.select(employee).where(cb.and(cb.equal(employee.get("name"), name)), cb.equal(employee.get("age"), age));
        Session session = sf.openSession();
        List<Employee> employees = session.createQuery(criteria).getResultList();
        session.close();
        return employees;
    }

    @Override
    public List<Employee> getByAgeOrName(String name, Integer age) {
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> employee = criteria.from(Employee.class);
        criteria.select(employee).where(cb.or(cb.equal(employee.get("age"), age), cb.equal(employee.get("name"), name)));
        Session session = sf.openSession();
        List<Employee> employees = session.createQuery(criteria).getResultList();
        session.close();
        return employees;
    }

    @Override
    public long getEmployeeCount() {
        CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
        criteria.select(cb.count(criteria.from(Employee.class)));
        Session session = sf.openSession();
        long count = session.createQuery(criteria).getSingleResult();
        session.close();
        return count;
    }

    @Override
    public Double getAverageSalary() {
        CriteriaQuery<Double> criteria = cb.createQuery(Double.class);
        criteria.select(cb.avg(criteria.from(Employee.class).get("salary")));
        Session session = sf.openSession();
        Double result = session.createQuery(criteria).getSingleResult();
        session.close();
        return result;
    }

    @Override
    public Double getMaxSalary() {
        CriteriaQuery<Double> criteria = cb.createQuery(Double.class);
        criteria.select(cb.max(criteria.from(Employee.class).get("salary")));
        Session session = sf.openSession();
        Double result = session.createQuery(criteria).getSingleResult();
        session.close();
        return result;
    }

    @Override
    public long getMinAge() {
        CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
        criteria.select(cb.min(criteria.from(Employee.class).get("age")));
        Session session = sf.openSession();
        long result = session.createQuery(criteria).getSingleResult();
        session.close();
        return result;
    }

    @Override
    public Double getAverageSalaryByDep(Long depId) {
        CriteriaQuery<Double> criteria = cb.createQuery(Double.class);
        Root<Employee> employee = criteria.from(Employee.class);
        Join<Employee, Department> join = employee.join("department", JoinType.INNER);
        criteria.select(cb.avg(employee.get("salary"))).where(cb.equal(join.get("depId"), depId));
        Session session = sf.openSession();
        Double result = session.createQuery(criteria).getSingleResult();
        session.close();
        return result;
    }
}
