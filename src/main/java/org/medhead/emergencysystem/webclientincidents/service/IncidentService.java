package org.medhead.emergencysystem.webclientincidents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.medhead.emergencysystem.webclientincidents.repository.IncidentProxy;
import org.medhead.emergencysystem.webclientincidents.model.Incident;

import lombok.Data;

@Data
@Service
public class IncidentService {

    @Autowired
    private IncidentProxy incidentProxy;

    public Incident getIncident(final int id) { return  incidentProxy.getIncident(id); }

    public Iterable<Incident> getIncidents() { return incidentProxy.getIncidents(); }

    public void deleteIncident(final int id) { incidentProxy.deleteIncident(id); }

    public Incident saveIncident(Incident incident) {
        Incident savedIncident;

        if(incident.getId() == null) {
            //if id is null then it's a new incident
            savedIncident = incidentProxy.createIncident(incident);
        } else {
            savedIncident = incidentProxy.updateIncident(incident);
        }

        return savedIncident;
    }
}
