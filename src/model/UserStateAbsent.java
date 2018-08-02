
package model;

import java.util.Date;

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
        return false;
    }

    @Override
    public boolean accessArea(Area area, Date time) {
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
        return UserState.ABSENT;
    }
}
