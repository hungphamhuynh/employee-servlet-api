package data.repository;

import data.entity.Degree;
import data.entity.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import util.HibernateUtil;

public class DegreeRepository {
    private final EntityManagerFactory emf;

    public DegreeRepository() {
        emf = HibernateUtil.getEntityManagerFactory();
    }

    public Degree getDegree(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery(
                            "SELECT d FROM Degree d where d.id=:id", Degree.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new EntityNotFoundException("Degree with id " + id + " not found");
        }
    }
}
