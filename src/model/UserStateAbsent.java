
package model;

import java.util.Date;
import static model.UserState.*;
import model.incident.Incident;
import model.incident.IncidentDetailsPhysicalAccess;
import model.incident.IncidentDetailsStateTransition;

/**
 *
 * @author kashwaa
 */
public class UserStateAbsent implements UserState{
    User user;
    
    public UserStateAbsent(User user){
        this.user = user;
    }

    @Override
    public boolean clockIn(Date time) {
        this.user.setState(new UserStateOutside(this.user));
        return true;
    }

    @Override
    public boolean clockOut(Date time) {
        Incident.createIncident(
                new IncidentDetailsStateTransition(ABSENT, ABSENT, "clockOut", time,
                        "Absent user trying to clock out", user)).responde();
        return false;
    }

    @Override
    public boolean accessArea(Area area, Date time) {
        Incident.createIncident(
                new IncidentDetailsStateTransition(ABSENT, INSIDE, "accessArea", time,
                        "Absent user trying to access area", user)).responde();
        Incident.createIncident(new IncidentDetailsPhysicalAccess(time, 
                "Absent user tried to access area", user, area, true)).responde();
        return false;
    }

    @Override
    public boolean leaveArea(Area area, Date time) {
        Incident.createIncident(
                new IncidentDetailsStateTransition(ABSENT, OUTSIDE, "leaveArea", time,
                        "Absent user trying to leave area", user)).responde();
        Incident.createIncident(new IncidentDetailsPhysicalAccess(time, 
                "Absent user tried to leave area", user, area, false)).responde();
        return false;
    }

    @Override
    public boolean autheticate() {
        return false;
    }
    
    @Override
    public String toString(){
        return UserState.ABSENT;
    }
}
