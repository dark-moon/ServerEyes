package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import model.security.ResourceAction;
import model.security.Role;
import model.security.SecurityResource;

/**
 *
 * @author kashwaa
 */
public class AutherizationTable {

    //<editor-fold defaultstate="collapsed" desc="Singleton implementation">
    public static AutherizationTable singletone;
    public static Object lock = new Object();

    public static AutherizationTable getInstance() {
        if (singletone == null) {
            synchronized (lock) {
                if (singletone == null) {
                    singletone = new AutherizationTable();
                }
            }
        }
        return singletone;
    }
    //</editor-fold>

//    private Collection<Role> roles;
    private final Map<User, Collection<Role>> autherizationTable;

    private AutherizationTable() {
        autherizationTable = new HashMap<>();
        //TODO: load the table from the DB.
    }

    public void assignRole(User user, Role role) {
        if (autherizationTable.get(user) != null) {
            autherizationTable.get(user).add(role);
            return;
        }
        Collection<Role> userRoles = new ArrayList<>();
        userRoles.add(role);
        autherizationTable.put(user, userRoles);

    }

    public synchronized boolean unassignRole(User user, Role role) {
        if (autherizationTable.get(user) == null || !autherizationTable.get(user).contains(role)) {
            return false;
        }
        return autherizationTable.get(user).remove(role);
    }

    public boolean hasRole(User user, Role role) {
        return autherizationTable.get(user) != null
                ? autherizationTable.get(user).contains(role) : false;
    }

    public boolean Autherize(User user, SecurityResource resource, ResourceAction action) {
        if (autherizationTable.get(user) == null) {
            return false;
        }
        return autherizationTable.get(user).stream().anyMatch((Role role) -> 
                role.isAllowedAction(resource, action));
    }

}
