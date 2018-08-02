
package model;

import java.util.Date;

/**
 *
 * @author kashwaa
 */
public interface UserState {
    public static String ABSENT = "absent";
    public static String OUTSIDE = "outside";
    public static String INSIDE = "inside";
    public static String LOGGED_IN = "loggedIn";
    public boolean clockIn(Date time);
    public boolean clockOut(Date time);
    public boolean accessArea(Area area, Date time);
    public boolean leaveArea(Area area, Date time);
    public boolean autheticate();
}
