/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author kashwaa
 */
public class UserStateInside implements UserState{

    User user;
    
    public UserStateInside(User user) {
        this.user = user;
    }

    @Override
    public boolean clockIn(Date time) {
        return false;
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
        //TODO: ensure that the area requested to be left is the same area the 
        //user is inside.
        this.user.setState(new UserStateOutside(user));
        return true;
    }

    @Override
    public boolean autheticate() {
        //TODO: Authenticate the user before returning true.
        return true;
    }
    
    @Override
    public String toString(){
        return UserState.INSIDE;
    }
}
