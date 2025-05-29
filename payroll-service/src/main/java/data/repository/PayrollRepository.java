package data.repository;

import data.entity.Employee;
import data.entity.Payroll;
import jakarta.persistence.*;
import util.HibernateUtil;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public class PayrollRepository {
    private final EntityManagerFactory emf;

    public PayrollRepository() {
        emf = HibernateUtil.getEntityManagerFactory();
    }

    public Payroll getPayroll(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery(
                            "SELECT p FROM Payroll p LEFT JOIN FETCH p.employee e LEFT JOIN FETCH e.degree WHERE p.id = :id", Payroll.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new EntityNotFoundException("Payroll with id " + id + " not found");
        }
    }

    public List<Payroll> getAllPayrolls() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery(
                            "SELECT p FROM Payroll p left join fetch p.employee e left join fetch e.degree", Payroll.class)
                    .getResultList();
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to get payrolls", e);
        }
    }

    public Payroll add(Payroll payroll) {
        EntityTransaction tx = null;
        try (EntityManager em = emf.createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            em.persist(payroll);
            tx.commit();
            return payroll;
        } catch (RuntimeException e) {
            rollbackTransaction(tx);
            throw new RuntimeException("Database error while adding payroll", e);
        }
    }

    public void delete(int id) {
        EntityTransaction tx = null;
        try (EntityManager em = emf.createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            Payroll payroll = em.createQuery(
                            "SELECT p FROM Payroll p LEFT JOIN FETCH p.employee e LEFT JOIN FETCH e.degree WHERE p.id = :id", Payroll.class)
                    .setParameter("id", id)
                    .getSingleResult();
            em.remove(payroll);
            tx.commit();
        } catch (NoResultException e) {
            throw new EntityNotFoundException("Payroll with ID " + id + " not found for deletion.");
        } catch (RuntimeException e) {
            rollbackTransaction(tx);
            throw new RuntimeException("Database error while deleting payroll", e);
        }
    }

    public Payroll update(Payroll payroll) {
        EntityTransaction tx = null;
        try (EntityManager em = emf.createEntityManager()) {
            tx = em.getTransaction();
            tx.begin();
            em.merge(payroll);
            tx.commit();
            return payroll;
        } catch (RuntimeException e) {
            rollbackTransaction(tx);
            throw new RuntimeException("Database error while updating payroll", e);
        }
    }

    public boolean existsByEmployeeIdAndMonth(int employeeId, Instant month) {
        try (EntityManager em = emf.createEntityManager()) {
            int targetMonth = month.atZone(ZoneId.systemDefault()).getMonthValue();
            int targetYear = month.atZone(ZoneId.systemDefault()).getYear();

            System.out.println("targetMonth: " + targetMonth);
            System.out.println("targetYear: " + targetYear);

            String jpql = "SELECT p FROM Payroll p " +
                    "WHERE p.employee.id = :employeeId " +
                    "AND FUNCTION('MONTH', p.payrollDate) = :targetMonth " +
                    "AND FUNCTION('YEAR', p.payrollDate) = :targetYear";

            try {
                em.createQuery(jpql, Payroll.class)
                        .setParameter("employeeId", employeeId)
                        .setParameter("targetMonth", targetMonth)
                        .setParameter("targetYear", targetYear)
                        .setMaxResults(1)
                        .getSingleResult();
                return true;
            } catch (NoResultException e) {
                return false;
            }
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