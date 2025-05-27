package data.repository;

import data.entity.Employee;
import jakarta.persistence.*;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class EmployeeRepository {
    private final EntityManagerFactory emf;

    public EmployeeRepository() {
        emf = HibernateUtil.getEntityManagerFactory();
    }

    public Employee getEmployee(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery(
                            "SELECT e FROM Employee e LEFT JOIN FETCH e.degree LEFT JOIN FETCH e.department WHERE e.id = :id", Employee.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new EntityNotFoundException("Employee with id " + id + " not found");
        }
    }

    public List<Employee> getAllEmployees() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery(
                    "SELECT e FROM Employee e left join fetch e.department left join fetch e.degree", Employee.class)
                    .getResultList();
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to get employees", e);
        }
    }

    public Employee add(Employee employee) {
        EntityTransaction tx = null;
        try (EntityManager em = emf.createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            em.persist(employee);
            tx.commit();
            return employee;
        } catch (RuntimeException e) {
            rollbackTransaction(tx);
            throw new RuntimeException("Database error while adding employee", e);
        }
    }

    public void delete(int id) {
        EntityTransaction tx = null;
        try (EntityManager em = emf.createEntityManager()) {
            Employee employee = getEmployee(id);

            tx = em.getTransaction();
            tx.begin();
            em.remove(employee);
            tx.commit();
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Employee with ID " + id + " not found for deletion.");
        } catch (RuntimeException e) {
            rollbackTransaction(tx);
            throw new RuntimeException("Database error while deleting employee", e);
        }
    }

    public Employee update(Employee employee) {
        EntityTransaction tx = null;
        try (EntityManager em = emf.createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            em.merge(employee);
            tx.commit();
            return employee;
        } catch (RuntimeException e) {
            rollbackTransaction(tx);
            throw new RuntimeException("Database error while updating employee", e);
        }
    }

    private void rollbackTransaction(EntityTransaction tx) {
        if (tx != null && tx.isActive()) {
            try {
                tx.rollback();
            } catch (PersistenceException rollbackEx) {
                // Log lỗi rollback nếu có, nhưng không ném lại để không che giấu lỗi gốc.
                System.err.println("Error during transaction rollback: " + rollbackEx.getMessage());
                rollbackEx.printStackTrace();
            }
        }
    }
}
