package data.repository;

import data.entity.Department;
import data.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import util.HibernateUtil;

public class DepartmentRepository {
    private final EntityManagerFactory emf;

    public DepartmentRepository() {
        emf = HibernateUtil.getEntityManagerFactory();
    }

    public Department getDepartment(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery(
                            "SELECT d FROM Department d", Department.class)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new EntityNotFoundException("Department with id " + id + " not found");
        }
    }
}
