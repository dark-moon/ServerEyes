package model.incident;

import java.util.Date;

/**
 *
 * @author kashwaa
 */
public class IncidentDetails {
    
    private final Date incidentTime;
    private final String description;
    private final IncidentReporter reporter;
    
    public IncidentDetails(Date time, String description, IncidentReporter reporter){
        this.incidentTime = time;
        this.description = description;
        this.reporter = reporter;
    }
    
    public Date getIncidentTime(){
        return this.incidentTime;
    }
    public String getIncidentDescription(){
        return this.description;
    }
    public IncidentReporter getReporter(){
        return this.reporter;
    }
}
