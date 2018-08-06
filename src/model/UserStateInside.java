/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import static model.UserState.OUTSIDE;
import model.incident.Incident;
import model.incident.IncidentDetailsPhysicalAccess;
import model.incident.IncidentDetailsStateTransition;

/**
 *
 * @author kashwaa
 */
public class UserStateInside implements UserState {

    User user;
    Area currentArea = null;

    public UserStateInside(User user, Area area) {
        this.user = user;
        this.currentArea = area;
    }

    @Override
    public boolean clockIn(Date time) {
        Incident.createIncident(
                new IncidentDetailsStateTransition(INSIDE, OUTSIDE, "clockIn", time,
                        "Inside user trying to clock in", user)).responde();
        return false;
    }

    @Override
    public boolean clockOut(Date time) {
        Incident.createIncident(
                new IncidentDetailsStateTransition(INSIDE, ABSENT, "clockOut", time,
                        "Inside user trying to clock out", user)).responde();
        return false;
    }

    @Override
    public boolean accessArea(Area area, Date time) {
        Incident.createIncident(
                new IncidentDetailsStateTransition(INSIDE, INSIDE, "accessArea", time,
                        "Inside user trying to accessArea", user)).responde();
        Incident.createIncident(new IncidentDetailsPhysicalAccess(time, 
                "Inside user tried to Enter an Area", user, area, true)).responde();
        return false;
    }

    @Override
    public boolean leaveArea(Area area, Date time) {
        //ensure that the area requested to be left is the same area the 
        //user is inside.
        if (area.getArea_id() == this.currentArea.getArea_id()) {
            this.user.setState(new UserStateOutside(user));
            return true;
        }
        Incident.createIncident(
                new IncidentDetailsStateTransition(INSIDE, OUTSIDE, "leaveArea", time,
                        "Inside user trying to leave another area", user)).responde();
        Incident.createIncident(new IncidentDetailsPhysicalAccess(time, 
                "Inside user tried to leave another Area", user, area, true)).responde();
        return false;
    }

    @Override
    public boolean autheticate() {
        //TODO: Authenticate the user before returning true.
        return true;
    }

    @Override
    public String toString() {
        return UserState.INSIDE;
    }
}
