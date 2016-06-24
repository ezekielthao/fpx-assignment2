package com.zeke.fpx.controllers;

import com.zeke.fpx.domain.Opportunity;
import com.zeke.fpx.service.OpportunityService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by Zeke on 6/21/2016.
 */
public class OpportunityCtrlTest {
    @Mock
    private OpportunityService opportunityService;

    @InjectMocks
    private OpportunityCtrl opportunityCtrl;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(opportunityCtrl).build();
    }

    @Test
    public void testListOpportunities() throws Exception {
        List<Opportunity> opportunities = new ArrayList<>();
        opportunities.add(new Opportunity());
        opportunities.add(new Opportunity());
        when(opportunityService.listAll()).thenReturn((List) opportunities);

        mockMvc.perform(get("/opportunity/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("opportunities"))
                .andExpect(model().attribute("opportunities", hasSize(2)));

    }

    @Test
    public void testShowOpportunity() throws Exception {
        Opportunity opportunity = new Opportunity();
        opportunity.setId("1");
        when(opportunityService.getById("1")).thenReturn(opportunity);

        mockMvc.perform(get("/opportunity/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("opportunity"))
                .andExpect(model().attribute("opportunity", instanceOf(Opportunity.class)));
    }

    @Test
    public void testEdit() throws Exception {
        when(opportunityService.getById("1")).thenReturn(new Opportunity());

        mockMvc.perform(get("/opportunity/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("opportunityform"))
                .andExpect(model().attribute("opportunity", instanceOf(Opportunity.class)));
    }

    @Test
    public void testNewOpportunity() throws Exception {
        //Important will make sure no other interactions have happened with opportunityService
        verifyZeroInteractions(opportunityService);

        mockMvc.perform(get("/opportunity/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("opportunityform"))
                .andExpect(model().attribute("opportunity", instanceOf(Opportunity.class)));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        String name = "Java Developer";
        String description = "fpx Java Developer";
        BigDecimal amount = new BigDecimal("80000.00");

        Opportunity opportunity = new Opportunity();
        opportunity.setId("1");
        opportunity.setDescription(description);
        opportunity.setAmount(amount);
        opportunity.setName(name);

        when(opportunityService.saveOrUpdate(Matchers.any())).thenReturn(opportunity);

        mockMvc.perform(post("/opportunity")
                .param("id", "1")
                .param("description", description)
                .param("name", name)
                .param("amount", "80000.00"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/opportunity/show/1"))
                .andExpect(model().attribute("opportunity", instanceOf(Opportunity.class)))
                .andExpect(model().attribute("opportunity", hasProperty("id", is("1"))))
                .andExpect(model().attribute("opportunity", hasProperty("name", is(name))))
                .andExpect(model().attribute("opportunity", hasProperty("description", is(description))))
                .andExpect(model().attribute("opportunity", hasProperty("amount", is(amount))));

        ArgumentCaptor<Opportunity> boundOpportunity = ArgumentCaptor.forClass(Opportunity.class);
        verify(opportunityService).saveOrUpdate(boundOpportunity.capture());

        assertEquals("1", boundOpportunity.getValue().getId());
        assertEquals(name, boundOpportunity.getValue().getName());
        assertEquals(description, boundOpportunity.getValue().getDescription());
        assertEquals(amount, boundOpportunity.getValue().getAmount());
    }

    @Test
    public void testDelete() throws Exception {
        String id = "1";
        mockMvc.perform(get("/opportunity/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/opportunity/list"));

        verify(opportunityService, times(1)).delete(id);
    }

}
