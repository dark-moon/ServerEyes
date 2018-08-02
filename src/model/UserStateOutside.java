package model;

import java.util.Date;

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
        if(area.autherize(user)){
            this.user.setState(new UserStateInside(this.user));
            return true;
        }
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
