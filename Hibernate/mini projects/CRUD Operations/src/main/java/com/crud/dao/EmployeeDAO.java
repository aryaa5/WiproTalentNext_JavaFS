package com.crud.dao;

import com.crud.model.Employee;
import com.crud.util.HibernateUtil; // Assuming a utility class for SessionFactory
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class EmployeeDAO {

    // (Note: HibernateUtil class needs to be implemented to provide SessionFactory)

    // A mock method for login validation, assuming ID is used as username for simplicity
    public Employee getEmployeeById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Employee.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // --- CRUD Methods ---
    
    public void addEmployee(Employee emp) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(emp);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e; // Re-throw to be handled by servlet
        }
    }

    public void updateEmployee(Employee emp) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(emp);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            if (employee != null) {
                session.remove(employee);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Employee> selectAllEmployees() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Using HQL (or JPAQL) to retrieve all employees
            return session.createQuery("from Employee", Employee.class).list();
        }
    }
}
