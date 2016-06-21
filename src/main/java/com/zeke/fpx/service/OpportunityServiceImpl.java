package com.zeke.fpx.service;

import com.zeke.fpx.domain.Opportunity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * Created by Zeke on 6/20/2016.
 */

@Service
public class OpportunityServiceImpl implements OpportunityService {

    private EntityManagerFactory emf;

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Opportunity> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Customer", Opportunity.class).getResultList();
    }

    @Override
    public Opportunity getById(Long id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Opportunity.class, id);
    }

    @Override
    public Opportunity saveOrUpdate(Opportunity opportunity) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Opportunity savedOpportunity = em.merge(opportunity);
        em.getTransaction().commit();

        return savedOpportunity;
    }

    @Override
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Opportunity.class, id));
        em.getTransaction().commit();
    }
}
