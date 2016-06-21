package com.zeke.fpx.controllers;

import com.zeke.fpx.domain.Opportunity;
import com.zeke.fpx.service.OpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Zeke on 6/21/2016.
 */
@Controller
@RequestMapping("/opportunity")
public class OpportunityCtrl {
    private OpportunityService opportunityService;

    @Autowired
    public void setOpportunityService(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }

//    @RequestMapping({"/list", "/"})
//    public String listCustomers(Model model){
//        model.addAttribute("customers", customerService.listAll());
//        return "customer/list";
//    }

    @RequestMapping({"/list", "/"})
    public String listOpportunities(Model model) {
        model.addAttribute("opportunities", opportunityService.listAll());
        return "opportunities";
    }

    @RequestMapping("show/{id}")
    public String showOpportunity(@PathVariable Long id, Model model) {
        model.addAttribute("opportunity", opportunityService.getById(id));
        return "opportunity";
    }



}
