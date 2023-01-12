/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UCPPWS.ucppws.model.jpa;

import UCPPWS.ucppws.exceptions.NonexistentEntityException;
import UCPPWS.ucppws.exceptions.PreexistingEntityException;
import UCPPWS.ucppws.model.entity.BukanManusia;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author meuti
 */
public class BukanManusiaJpaController implements Serializable {

    public BukanManusiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("UCPPWS_ucppws_jar_0.0.1-SNAPSHOTPU");

    public BukanManusiaJpaController() {
       
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BukanManusia bukanManusia) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(bukanManusia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBukanManusia(bukanManusia.getId()) != null) {
                throw new PreexistingEntityException("BukanManusia " + bukanManusia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BukanManusia bukanManusia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            bukanManusia = em.merge(bukanManusia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bukanManusia.getId();
                if (findBukanManusia(id) == null) {
                    throw new NonexistentEntityException("The bukanManusia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BukanManusia bukanManusia;
            try {
                bukanManusia = em.getReference(BukanManusia.class, id);
                bukanManusia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bukanManusia with id " + id + " no longer exists.", enfe);
            }
            em.remove(bukanManusia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BukanManusia> findBukanManusiaEntities() {
        return findBukanManusiaEntities(true, -1, -1);
    }

    public List<BukanManusia> findBukanManusiaEntities(int maxResults, int firstResult) {
        return findBukanManusiaEntities(false, maxResults, firstResult);
    }

    private List<BukanManusia> findBukanManusiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BukanManusia.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public BukanManusia findBukanManusia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BukanManusia.class, id);
        } finally {
            em.close();
        }
    }

    public int getBukanManusiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BukanManusia> rt = cq.from(BukanManusia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}