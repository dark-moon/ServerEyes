package model;

import java.util.Date;
import model.incident.Incident;
import model.incident.IncidentDetailsPhysicalAccess;
import model.incident.IncidentDetailsStateTransition;
import model.security.ResourceAction;

/**
 *
 * @author kashwaa
 */
public class UserStateOutside implements UserState{
    private final User user;

    
    public UserStateOutside(User user) {
        this.user = user;
    }

    @Override
    public boolean clockIn(Date time) {
        Incident.createIncident(
                new IncidentDetailsStateTransition(OUTSIDE, OUTSIDE, "clockIn", time,
                        "Outside user trying to clock in", user)).responde();
        return false;
    }

    @Override
    public boolean clockOut(Date time) {
        this.user.setState(new UserStateAbsent(user));
        return true;
    }

    @Override
    public boolean accessArea(Area area, Date time) {
        if(area.autherize(user, new ResourceAction(Area.ACTION_ACCESS))){
            this.user.setState(new UserStateInside(this.user, area));
            return true;
        }
        Incident.createIncident(new IncidentDetailsPhysicalAccess(time, 
                "User tried to Enter unautherized Area", user, area, true)).responde();
        return false;
    }

    @Override
    public boolean leaveArea(Area area, Date time) {
        Incident.createIncident(new IncidentDetailsStateTransition(OUTSIDE, OUTSIDE,
                "leaveArea", time, 
                "Outside user tried to leave area: " + area.getAreaName(), user)).responde();
        return false;
    }

    @Override
    public boolean autheticate() {
        return false;
    }
    
    @Override
    public String toString(){
        return UserState.OUTSIDE;
    }
}
