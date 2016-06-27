package com.zeke.fpx.controllers;

import com.zeke.fpx.domain.Opportunity;
import com.zeke.fpx.service.OpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping({"/list", "/"})
    public String listOpportunities(Model model) {
        model.addAttribute("opportunities", opportunityService.listAll());
        return "opportunities";
    }

    @RequestMapping("/show/{id}")
    public String showOpportunity(@PathVariable String id, Model model) {
        model.addAttribute("opportunity", opportunityService.getById(id));
        return "opportunity";
    }

    @RequestMapping("/new")
    public String newOpportunity(Model model){

        model.addAttribute("opportunity", new Opportunity());
        return "opportunityform";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("opportunity", opportunityService.getById(id));
        return "opportunityform";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveOrUpdate(Opportunity opportunity) {
        Opportunity newOpportunity = opportunityService.saveOrUpdate(opportunity);
        return "redirect:/opportunity/show/" + newOpportunity.getId();
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        opportunityService.delete(id);
        return "redirect:/opportunity/list";
    }

}
