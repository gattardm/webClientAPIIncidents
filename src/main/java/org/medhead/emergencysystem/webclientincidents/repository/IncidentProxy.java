package org.medhead.emergencysystem.webclientincidents.repository;

import lombok.extern.slf4j.Slf4j;
import org.medhead.emergencysystem.webclientincidents.CustomProperties;
import org.medhead.emergencysystem.webclientincidents.model.Incident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class IncidentProxy {

    @Autowired
    private CustomProperties props;

    /**
     * Get all incidents
     * @return An iterable of all incidents
     */
    public Iterable<Incident> getIncidents() {

        String baseApiUrl = props.getApiUrl();
        String getIncidentsUrl = baseApiUrl + "/incidents";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Incident>> response = restTemplate.exchange(
                getIncidentsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Incident>>() {}
        );

        log.debug(("Get Incidents call " + response.getStatusCode().toString()));

        return response.getBody();
    }

    /**
     * Get an incident by the id
     * @param id The id of the incident
     * @return The incident which matches the id
     */
    public Incident getIncident(int id) {
        String baseApiUrl = props.getApiUrl();
        String getIncidentUrl = baseApiUrl + "/incident/" +id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Incident> response = restTemplate.exchange(
                getIncidentUrl,
                HttpMethod.GET,
                null,
                Incident.class
        );

        log.debug("Get Incident call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Add a new incident
     * @param i A new incident (without an id)
     * @return The employee fulfilled (with an id)
     */
    public Incident createIncident(Incident i) {
        String baseApiUrl = props.getApiUrl();
        String createIncidentUrl = baseApiUrl + "/incident";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Incident> request = new HttpEntity<Incident>(i);
        ResponseEntity<Incident> response = restTemplate.exchange(
                createIncidentUrl,
                HttpMethod.POST,
                request,
                Incident.class
        );

        log.debug("Create Incident call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Update an incident - using the PUT HTTP Method.
     * @param i Existing incident to update
     */
    public Incident updateIncident(Incident i) {
        String baseApiUrl = props.getApiUrl();
        String updateIncidentUrl = baseApiUrl + "/incident/" + i.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Incident> request = new HttpEntity<Incident>(i);
        ResponseEntity<Incident> response = restTemplate.exchange(
                updateIncidentUrl,
                HttpMethod.PUT,
                request,
                Incident.class
        );

        log.debug("Update Incident call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Delete an incident using exchange method of RestTemplate
     * instead of delete method in order to log the response status code.
     * @param id The incident to delete
     */
    public void deleteIncident(int id) {
        String baseApiUrl = props.getApiUrl();
        String deleteIncidentUrl = baseApiUrl + "/incident/" + id;

        RestTemplate restTemplate =new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteIncidentUrl,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        log.debug("Delete Incident call " + response.getStatusCode().toString());
    }

}
