package data.repository;

import data.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import util.HibernateUtil;

public class EmployeeRepository {
    private final EntityManagerFactory emf;

    public EmployeeRepository() {
        emf = HibernateUtil.getEntityManagerFactory();
    }

    public Employee getEmployee(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery(
                            "SELECT e FROM Employee e LEFT JOIN FETCH e.degree WHERE e.id = :id", Employee.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new EntityNotFoundException("Employee with id " + id + " not found");
        }
    }
}
