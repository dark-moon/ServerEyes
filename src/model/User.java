
package model;

import database.DAOFactory;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

/**
 *
 * @author kashwaa
 */
public class User {
    private static final DAOFactory sqlDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MY_SQL);
    
    private String userName, firstName, lastName, address, email;
    private NameTag tag;
    private Collection<SecurityGroup> groups;
    private UserState state;

    public static Collection<User> getAllUsers(){
        return sqlDAOFactory.getUserDAO().getAllUsers();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public User() {
        this.state = new UserStateAbsent(this);
        this.groups = new HashSet<>();
    }

    public User(String userName, String firstName, String lastName, String address, String email) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
    }  
    //</editor-fold>
    
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
    
//<editor-fold defaultstate="collapsed" desc="getters and setters">
    
    public UserState getState(){
        return state;
    }
    
    public void setState(UserState state){
        this.state = state;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public NameTag getTag() {
        return tag;
    }

    public void setTag(NameTag tag) {
        this.tag = tag;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    //</editor-fold>

    //TODO: change the implementation form User-tickets to ACL Matrix.
    public Collection<SecurityGroup> getGroups() {
        return groups;
    }

}
