package model.incident;

import java.util.Date;
import model.Area;

/**
 *
 * @author kashwaa
 */
public class IncidentDetailsPhysicalAccess extends IncidentDetails{
    
    private final Area reportedArea;
    
    public IncidentDetailsPhysicalAccess(Date time, 
            String description, IncidentReporter reporter, Area reportedArea) {
        super(time, description, reporter);
        this.reportedArea = reportedArea;
    }

    public Area getReportedArea() {
        return reportedArea;
    }
    
}
