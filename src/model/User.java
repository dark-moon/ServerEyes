
package model;

import model.incident.IncidentResponse;
import model.incident.IncidentReporter;
import database.DAOFactory;
import database.UserDAO;
import java.util.Collection;
import java.util.Date;
import model.incident.ResponseLogInDB;

/**
 *
 * @author kashwaa
 */
public class User implements IncidentReporter{
    private static DAOFactory daoFactory;
    
    private int id;
    private String userName, firstName, lastName, address, email;
    private NameTag tag;
    private UserState state;
    
    private IncidentResponse defaultResponse = new ResponseLogInDB();

    public static UserDAO getUserDAO(int daoFactoryType){
        return DAOFactory.getDAOFactory(daoFactoryType).getUserDAO();
    }
    
//    public static Collection<User> getAllUsers(int daoFactoryType){
//        daoFactory = DAOFactory.getDAOFactory(daoFactoryType);
//        return daoFactory.getUserDAO().getAllUsers();
//    }
//    
//    public static Collection<User> getAllUsersMySql(){
//        daoFactory = DAOFactory.getDAOFactory(DAOFactory.MY_SQL);
//        return daoFactory.getUserDAO().getAllUsers();
//    }
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public User() {
        this.state = new UserStateAbsent(this);
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
    
        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
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

    private void setDefaultResponse(IncidentResponse newResponse){
        this.defaultResponse = newResponse;
    }
    
    @Override
    public IncidentResponse getDefaultResponse() {
        return this.defaultResponse;
    }


}
