package model;

import database.DAOFactory;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author kashwaa
 */
public class RFID_Reader {
    
    private int id;
    private final boolean isOutside;
    private Set<User> proppers;
    private RFID_Control parent;

    public RFID_Reader(int id, boolean isOutside){
        this(id, isOutside, null);
    }
    
    public RFID_Reader(int id, boolean isOutside, RFID_Control parent){
        this.id = id;
        this.isOutside = isOutside;
        this.parent = parent;
        this.proppers = new HashSet<>();
    }
    
    //method to be called by the JNI API connected to the external RFID system.
    public void handleSignal(long signal, int id){
        User criteria = new User();
        criteria.setTag(new NameTag(id, signal));
        this.parent.handleSignal(this, User.getUserDAO(DAOFactory.MY_SQL).findUser(criteria));
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOutside() {
        return isOutside;
    }

    public RFID_Control getParent() {
        return parent;
    }

    public void setParent(RFID_Control parent) {
        this.parent = parent;
    }

    public boolean isProppedBy(User user) {
        return proppers.contains(user);
    }

    public void addPropper(User user){
        this.proppers.add(user);
    }
    
}
