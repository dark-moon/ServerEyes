package model.incident;

import java.util.Date;
import model.User;

/**
 *
 * @author kashwaa
 */
public class ResponseLogInDB implements IncidentResponse {

    @Override
    public <T extends IncidentDetails> void respond(T details) {
        //TODO:Code to log the incident details in the database.
        Date time = details.getIncidentTime();
        String userName = ((User)details.getReporter()).getUserName();
        String areaName = "Unknown";
        if (details instanceof IncidentDetailsPhysicalAccess) {
            areaName = 
            ((IncidentDetailsPhysicalAccess) details).getReportedArea().getAreaName();
        }
        System.out.println(time.toString() + ": User: " 
                + userName + " tried to enter the unautherized area: " + areaName);
    }

}
