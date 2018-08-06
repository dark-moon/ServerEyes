package model.incident;

import java.util.Date;

/**
 *
 * @author kashwaa
 */
public class Incident {
    
    private int id;
    private String decription;
    private Date incidentTime;
    
    private final IncidentReporter reporter;
    
    private IncidentResponse defaultResponse;
    private final IncidentDetails details;
    
    private Incident(IncidentDetails details){
        this.details = details;
        this.reporter = details.getReporter();
        this.defaultResponse = details.getReporter().getDefaultResponse();
        this.decription = details.getIncidentDescription();
        this.incidentTime = details.getIncidentTime();
    }
    
    public static Incident createIncident(IncidentDetails details){
        return new Incident(details);
    }
    
    public void changeResopnse(IncidentResponse desiredResponse){
        this.defaultResponse = desiredResponse;
    }
    
    public void responde(){
        this.defaultResponse.respond(details);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public Date getIncidentTime() {
        return incidentTime;
    }

    public void setIncidentTime(Date incidentTime) {
        this.incidentTime = incidentTime;
    }

    public IncidentReporter getReporter() {
        return reporter;
    }
    
}
