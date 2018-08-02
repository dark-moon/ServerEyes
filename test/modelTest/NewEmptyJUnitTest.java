package modelTest;

import java.util.Date;
import model.Area;
import model.SecurityGroup;
import model.User;
import model.UserState;
import model.UserStateInside;
import model.UserStateOutside;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kashwaa
 */
public class NewEmptyJUnitTest {

    private static User absentUser;
    private static User insideUser;
    private static User outsideUser;
    private static Area allowedArea;
    private static Area restrictedArea;
    private static SecurityGroup group;

    public NewEmptyJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        allowedArea = new Area();
        restrictedArea = new Area();
        absentUser = new User();
        outsideUser = new User();
        outsideUser.setState(new UserStateOutside(outsideUser));
        insideUser = new User();
        insideUser.setState(new UserStateInside(insideUser, allowedArea));
        group = new SecurityGroup();
        group.getAllowedAreas().add(allowedArea);
        outsideUser.getGroups().add(group);
        insideUser.getGroups().add(group);
        absentUser.getGroups().add(group);
    }

    @AfterClass
    public static void tearDownClass() {
        absentUser = null;
        outsideUser = null;
        insideUser = null;
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    //<editor-fold defaultstate="collapsed" desc="clock-in attempts">
    @Test
    public void absentUserClockIn() {
        //Ensure that absent users can clock-in normally.
        assertTrue("Absent user can't clock-in", absentUser.clockIn(new Date()));

        //Ensure that user's state changed correctly to outside after clocking-in.
        assertEquals("user state didn't transition correctly to \"outside\""
                + " instead to \"" + absentUser.getState().toString(),
                UserState.OUTSIDE, absentUser.getState().toString());
        
        //clock out the absent user again to stay absent in the subsequent tests.
        absentUser.clockOut(new Date());
    }

    @Test
    public void outsideUserClockIn() {
        //Ensure that outside users can't clock-in again.
        assertFalse("Attended (outside) user clocked in while he shouldn't be allowed to!",
                outsideUser.clockIn(new Date()));

        //Ensure that trying to clock-in an already clocked-in user doesn't change
        //his state.
        assertEquals("user state transitioned to "
                + outsideUser.getState().toString() + " instead of staying the same.",
                UserState.OUTSIDE, outsideUser.getState().toString());
    }

    @Test
    public void insideUserClockIn() {
        //Ensure that inside users can't clock-in again.
        assertFalse("Attended (inside) user clocked in while he shouldn't be allowd to!",
                insideUser.clockIn(new Date()));

        //Ensure that trying to clock-in an already clocked-in user doesn't change
        //his state.
        assertEquals("user state transitioned to "
                + insideUser.getState().toString() + " instead of staying the same.",
                UserState.INSIDE, insideUser.getState().toString());
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="clock-out attempts">
    @Test
    public void absentUserClockOut() {
        //Ensure that absent users can't clock-out.
        assertFalse("Absent user clocked-out!", absentUser.clockOut(new Date()));
        
        //Ensure that trying to clock-out an already clocked-out user doesn't change
        //his state.
        assertEquals("user state transitioned to "
                + absentUser.getState().toString() + " instead of staying the same.",
                UserState.ABSENT, absentUser.getState().toString());
    }

    @Test
    public void outsideUserClockOut() {
        //Ensure that outside users can clock-out normally.
        assertTrue("Outside user can't clock-out", outsideUser.clockOut(new Date()));

        //Ensure that user's state changed correctly to outside after clocking-in.
        assertEquals("user state didn't transition correctly to \"absent\""
                + " instead to \"" + outsideUser.getState().toString(),
                UserState.ABSENT, outsideUser.getState().toString());
        
        //Clock outside user in again to stay outside in the subsequent tests.
        outsideUser.clockIn(new Date());
    }
    
    @Test
    public void insideUserClockOut(){
        //Ensure that inside users can't clock-out.
        assertFalse("Inside user clocked-out!", insideUser.clockOut(new Date()));
        
        //Ensure that trying to clock-out an inside user doesn't change
        //his state.
        assertEquals("user state transitioned to "
                + insideUser.getState().toString() + "instead of staying the same.",
                UserState.INSIDE, insideUser.getState().toString());
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="access allowed area attempts">
    @Test
    public void absentUserAccessArea(){
        assertFalse("Absent user accessed area!", absentUser.accessArea(allowedArea,
                new Date()));
        
        assertEquals("Absent user state changed to \"" + absentUser.getState() 
                + "\" instead of staying the same", UserState.ABSENT, absentUser.getState().toString());
    }
    
    @Test
    public void outsideUserAccessArea(){
        assertTrue("Outside user can't access allowed area!", 
                outsideUser.accessArea(allowedArea, new Date()));
        
        assertEquals("Outside user state didn't transition correctly to \"INSIDE\""
                + " instead to \"" + outsideUser.getState().toString(),
                UserState.INSIDE, outsideUser.getState().toString());
        
        outsideUser.leaveArea(allowedArea, new Date());
    }
    
    @Test
    public void insideUserAccessArea(){
        assertFalse("Inside user accessed area!", insideUser.accessArea(restrictedArea,
                new Date()));
        
        assertEquals("Inside user state changed to \"" + insideUser.getState() 
                + "\" instead of staying the same", UserState.INSIDE, insideUser.getState().toString());
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="access restricted area attempts">
    @Test
    public void absentUserAccessRestrictedArea(){
        assertFalse("Absent user accessed area!", absentUser.accessArea(restrictedArea,
                new Date()));
        
        assertEquals("Absent user state changed to \"" + absentUser.getState() 
                + "\" instead of staying the same", UserState.ABSENT, absentUser.getState().toString());
    }
    
    @Test
    public void outsideUserAccessRestrictedArea(){
        assertFalse("Outside user can access restricted area!", 
                outsideUser.accessArea(restrictedArea, new Date()));
        
        assertEquals("Outside user state changed to " + outsideUser.getState().toString()
                + " instead of staying the same",
                UserState.OUTSIDE, outsideUser.getState().toString());
        
        outsideUser.leaveArea(allowedArea, new Date());
    }
    
    @Test
    public void insideUserAccessRestrictedArea(){
        assertFalse("Inside user accessed area!", insideUser.accessArea(allowedArea,
                new Date()));
        
        assertEquals("Inside user state changed to \"" + insideUser.getState() 
                + "\" instead of staying the same", UserState.INSIDE, insideUser.getState().toString());
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="leave area attempts">
    @Test
    public void absentUserLeaveArea(){
        assertFalse("Absent user leaved area!", absentUser.leaveArea(allowedArea,
                new Date()));
        
        assertEquals("Absent user state changed to \"" + absentUser.getState() 
                + "\" instead of staying the same", UserState.ABSENT, absentUser.getState().toString());
    }
    
    @Test
    public void outsideUserLeaveArea(){
        assertFalse("Outside user leaved area!", outsideUser.leaveArea(allowedArea,
                new Date()));
        
        assertEquals("Outside user state changed to \"" + outsideUser.getState() 
                + "\" instead of staying the same", UserState.OUTSIDE, outsideUser.getState().toString());
        
    }
    
    @Test
    public void insideUserLeaveArea(){
        assertTrue("Inside user can't leave his area!", 
                insideUser.leaveArea(allowedArea, new Date()));
        
        assertEquals("Inside user state didn't transition correctly to \"OUTSIDE\""
                + " instead to \"" + insideUser.getState().toString(),
                UserState.OUTSIDE, insideUser.getState().toString());
        
        insideUser.accessArea(allowedArea, new Date());
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Normal transitions test">
    @Test
    public void testNormalTransitioins(){
        assertEquals(UserState.ABSENT, absentUser.getState().toString());
        absentUser.clockIn(new Date());
        assertEquals(UserState.OUTSIDE, absentUser.getState().toString());
        absentUser.accessArea(allowedArea, new Date());
        assertEquals(UserState.INSIDE, absentUser.getState().toString());
        absentUser.leaveArea(allowedArea, new Date());
        assertEquals(UserState.OUTSIDE, absentUser.getState().toString());
        absentUser.accessArea(restrictedArea, new Date());
        assertEquals(UserState.OUTSIDE, absentUser.getState().toString());
        absentUser.clockOut(new Date());
        assertEquals(UserState.ABSENT, absentUser.getState().toString());
    }
    //</editor-fold>

    @Test
    public void loggedInUserClockIn() {
        //TODO: implement the logged in user first.
    }

}
