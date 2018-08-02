
package model;

import java.util.Date;

/**
 *
 * @author kashwaa
 */
public class User {
    UserState state;

    public User() {
        this.state = new UserStateAbsent(this);
    }
    
    public boolean clockIn(Date time){
        return state.clockIn(time);
    }
    
    public boolean clockOut(Date time){
        return state.clockOut(time);
    }
    
    public boolean accessArea(Area area, Date time){
        return state.accessArea(area, time);
    }
    
    public boolean leaveArea(Area area, Date time){
        return state.leaveArea(area, time);
    }
    
    public UserState getState(){
        return state;
    }
    
    public void setState(UserState state){
        this.state = state;
    }
}
