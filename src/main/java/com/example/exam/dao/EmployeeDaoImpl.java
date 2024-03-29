package com.example.exam.dao;

import com.example.exam.model.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao{
    private static final String JPA_UNIT_NAME = "myPersistance";
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManager = Persistence.createEntityManagerFactory(
                    JPA_UNIT_NAME).createEntityManager();
        }
        return entityManager;
    }

    public List<Employee> selectAll() {
        List<Employee> employees = getEntityManager().createQuery(
                "select e from Employee e").getResultList();
        return employees;
    }

    public Employee insert(Employee u) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(u);
        getEntityManager().getTransaction().commit();
        return u;
    }

    public void delete(Employee u) {
        getEntityManager().getTransaction().begin();
        u = getEntityManager().merge(u);
        getEntityManager().remove(u);
        getEntityManager().getTransaction().commit();
    }

    public Employee update(Employee u) {
        if (u != null) {
            getEntityManager().getTransaction().begin();
            u = getEntityManager().merge(u);
            getEntityManager().getTransaction().commit();
        } else {
            System.out.println("vide");
        }
        return u;
    }

    public Employee findById(int id) {
        return entityManager.find(Employee.class, id);
    }
}
