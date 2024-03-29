package com.example.exam.dao;

import com.example.exam.model.Employee;
import com.example.exam.model.Project;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.List;

public class ProjectDaoImpl implements ProjectDao{

    private static final String JPA_UNIT_NAME = "myPersistance";
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManager = Persistence.createEntityManagerFactory(
                    JPA_UNIT_NAME).createEntityManager();
        }
        return entityManager;
    }
    @Override
    public List<Project> selectAll() {
        List<Project> Projects = getEntityManager().createQuery(
                "select p from Project p").getResultList();
        return Projects;
    }

    @Override
    public Project insert(Project u) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(u);
        getEntityManager().getTransaction().commit();
        return u;
    }

    @Override
    public void delete(Project u) {
        getEntityManager().getTransaction().begin();
        u = getEntityManager().merge(u);
        getEntityManager().remove(u);
        getEntityManager().getTransaction().commit();
    }

    @Override
    public Project update(Project u) {
        if (u != null) {
            getEntityManager().getTransaction().begin();
            u = getEntityManager().merge(u);
            getEntityManager().getTransaction().commit();
        } else {
            System.out.println("vide");
        }
        return u;
    }

    @Override
    public Project findById(int id) {
        return entityManager.find(Project.class, id);
    }
}
