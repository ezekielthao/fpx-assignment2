package com.zeke.fpx.service;

import com.zeke.fpx.domain.Opportunity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Zeke on 6/20/2016.
 */

@Service
public class OpportunityServiceImpl implements OpportunityService {
    //private Map<String, Opportunity> opportunityMap;

    private final Byte[] YESBYTE = {1};
    private final Byte[] NOBYTE = {0};

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
        Opportunity opportunity = em.find(Opportunity.class, id);
        opportunity = processOpportunity(opportunity);
        return opportunity;
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
        if (opportunity.isClosed()) {
            opportunity.setIsClosed(YESBYTE);
        } else {
            opportunity.setIsClosed(NOBYTE);
        }
        if (opportunity.isWon()) {
            opportunity.setIsWon(YESBYTE);
        } else {
            opportunity.setIsWon(NOBYTE);
        }
        if (opportunity.isDeleted()) {
            opportunity.setIsDeleted(YESBYTE);
        } else {
            opportunity.setIsDeleted(NOBYTE);
        }

        return opportunity;
    }

    public Opportunity processOpportunity(Opportunity opportunity) {
        if (Arrays.equals(opportunity.getIsClosed(), NOBYTE)) {
            opportunity.setClosed(false);
        } else {
            opportunity.setClosed(true);
        }
        if (Arrays.equals(opportunity.getIsDeleted(), NOBYTE)) {
            opportunity.setDeleted(false);
        } else {
            opportunity.setDeleted(true);
        }
        if (Arrays.equals(opportunity.getIsWon(), NOBYTE)) {
            opportunity.setWon(false);
        } else {
            opportunity.setWon(true);
        }

        return opportunity;
    }

}
