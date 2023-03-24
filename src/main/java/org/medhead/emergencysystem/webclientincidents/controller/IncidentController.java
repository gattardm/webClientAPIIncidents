package org.medhead.emergencysystem.webclientincidents.controller;

import lombok.Data;
import org.medhead.emergencysystem.webclientincidents.model.Incident;
import org.medhead.emergencysystem.webclientincidents.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Data
@Controller
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Incident> listIncident = incidentService.getIncidents();
        model.addAttribute("incidents", listIncident);
        return "homeIncidents";
    }

    @GetMapping("/createIncident")
    public String createIncident(Model model) {
        Incident i = new Incident();
        model.addAttribute("incident", i);
        return "formNewIncident";
    }

    @GetMapping("/updateIncident/{id}")
    public String updateIncident(@PathVariable("id") final int id, Model model) {
        Incident i = incidentService.getIncident(id);
        model.addAttribute("incident", i);
        return "formUpdateIncident";
    }

    @GetMapping("/deleteIncident/{id}")
    public ModelAndView deleteIncident(@PathVariable("id") final int id) {
        incidentService.deleteIncident(id);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/saveIncident")
    public ModelAndView saveIncident(@ModelAttribute Incident incident) {
        if(incident.getId() != null) { Incident current = incidentService.getIncident(incident.getId()); }
        incidentService.saveIncident((incident));
        return new ModelAndView("redirect:/");
    }
}
