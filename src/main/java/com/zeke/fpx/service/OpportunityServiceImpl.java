package com.zeke.fpx.service;

import com.zeke.fpx.domain.Opportunity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;
import java.util.Map;

/**
 * Created by Zeke on 6/20/2016.
 */

@Service
public class OpportunityServiceImpl implements OpportunityService {
    //private Map<String, Opportunity> opportunityMap;

    private EntityManagerFactory emf;

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Opportunity> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Opportunity", Opportunity.class).getResultList();
    }

    @Override
    public Opportunity getById(String id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Opportunity.class, id);
    }

    @Override
    public Opportunity saveOrUpdate(Opportunity opportunity) {
        EntityManager em = emf.createEntityManager();
        opportunity = prePersistData(opportunity);

        em.getTransaction().begin();
        Opportunity savedOpportunity = em.merge(opportunity);
        em.getTransaction().commit();

        return savedOpportunity;
    }

    @Override
    public void delete(String id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Opportunity.class, id));
        em.getTransaction().commit();
    }

    public Opportunity prePersistData(Opportunity opportunity) {
        Byte[] yesByte = {1};
        Byte[] noByte = {0};
        if (opportunity.isClosedBool()) {
            opportunity.setIsClosed(yesByte);
        } else {
            opportunity.setIsClosed(noByte);
        }
        if (opportunity.isWonBool()) {
            opportunity.setIsWon(yesByte);
        } else {
            opportunity.setIsWon(noByte);
        }
        if (opportunity.isDeletedBool()) {
            opportunity.setIsDeleted(yesByte);
        } else {
            opportunity.setIsDeleted(noByte);
        }

        return opportunity;
    }

}
