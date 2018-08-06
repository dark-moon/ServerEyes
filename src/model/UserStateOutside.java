package model;

import java.util.Date;
import model.incident.Incident;
import model.incident.IncidentDetails;
import model.incident.IncidentDetailsPhysicalAccess;
import model.incident.IncidentReporter;
import model.security.ResourceAction;

/**
 *
 * @author kashwaa
 */
public class UserStateOutside implements UserState{
    private User user;

    
    public UserStateOutside(User user) {
        this.user = user;
    }

    @Override
    public boolean clockIn(Date time) {
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
                "User tried to Enter unautherized Area", user, area)).responde();
        return false;
    }

    @Override
    public boolean leaveArea(Area area, Date time) {
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
