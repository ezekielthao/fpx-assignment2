package com.zeke.fpx.service;

import com.zeke.fpx.config.IntegrationConfig;
import com.zeke.fpx.domain.Opportunity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Zeke on 6/21/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(IntegrationConfig.class)
public class OpportunityServiceImplTest {
    private OpportunityService opportunityService;

    @Autowired
    public void setOpportunityService(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }

    @Before
    public void setup() {
        Byte[] noBinary = {0,0};
        Opportunity opportunity1 = new Opportunity();
        opportunity1.setId("1");
        opportunity1.setAmount(new BigDecimal("15000.00"));
        opportunity1.setCloseDate(new Date());
        opportunity1.setCurrencyIsoCode("USD");
        opportunity1.setDescription("Java Contractor");
        opportunity1.setIsClosed(noBinary);
        opportunity1.setIsDeleted(noBinary);
        opportunity1.setIsWon(noBinary);
        opportunityService.saveOrUpdate(opportunity1);

        Opportunity opportunity2 = new Opportunity();
        opportunity2.setId("2");
        opportunity2.setAmount(new BigDecimal("15000.00"));
        opportunity2.setCloseDate(new Date());
        opportunity2.setCurrencyIsoCode("USD");
        opportunity2.setDescription(".NET Contractor");
        opportunity2.setIsClosed(noBinary);
        opportunity2.setIsDeleted(noBinary);
        opportunity2.setIsWon(noBinary);
        opportunityService.saveOrUpdate(opportunity2);

        Opportunity opportunity3 = new Opportunity();
        opportunity3.setId("3");
        opportunity3.setAmount(new BigDecimal("15000.00"));
        opportunity3.setCloseDate(new Date());
        opportunity3.setCurrencyIsoCode("USD");
        opportunity3.setDescription("DBA Contractor");
        opportunity3.setIsClosed(noBinary);
        opportunity3.setIsDeleted(noBinary);
        opportunity3.setIsWon(noBinary);
        opportunityService.saveOrUpdate(opportunity3);

        Opportunity opportunity4 = new Opportunity();
        opportunity4.setId("4");
        opportunity4.setAmount(new BigDecimal("15000.00"));
        opportunity4.setCloseDate(new Date());
        opportunity4.setCurrencyIsoCode("USD");
        opportunity4.setDescription("JavaScript Contractor");
        opportunity4.setIsClosed(noBinary);
        opportunity4.setIsDeleted(noBinary);
        opportunity4.setIsWon(noBinary);
        opportunityService.saveOrUpdate(opportunity4);

        Opportunity opportunity5 = new Opportunity();
        opportunity5.setId("5");
        opportunity5.setAmount(new BigDecimal("15000.00"));
        opportunity5.setCloseDate(new Date());
        opportunity5.setCurrencyIsoCode("USD");
        opportunity5.setDescription("AWS Contractor");
        opportunity5.setIsClosed(noBinary);
        opportunity5.setIsDeleted(noBinary);
        opportunity5.setIsWon(noBinary);
        opportunityService.saveOrUpdate(opportunity5);
    }

    @Test
    public void testListAll() throws Exception {
        List<Opportunity> opportunities = (List<Opportunity>) opportunityService.listAll();
        assert opportunities.size() == 5;
    }

    @Test
    public void testPrePersistData() throws Exception {
        Opportunity opportunity = new Opportunity();

    }




}
