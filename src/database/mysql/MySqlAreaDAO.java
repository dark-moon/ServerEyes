package database.mysql;

import database.AreaDAO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import model.Area;
import model.User;
import model.security.ResourceAction;

/**
 *
 * @author kashwaa
 */
public class MySqlAreaDAO implements AreaDAO{

    @Override
    public Collection<Area> getAllAreas() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int insertArea(Area area) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateArea(Area area) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteArea(Area area) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Area findArea(Area criteria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Area> findAreas(Area criteria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<User> getAllowedUsers(Area area) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    @Override
    public boolean autherize(Area area, User user) {
        return true;
    }

    @Override
    public List<ResourceAction> getAllowedActions() {
        //TODO: replace temp implementation with actual data retrival.
        List<ResourceAction> result = new ArrayList<>();
        result.add(new ResourceAction("Manage area access"));
        result.add(new ResourceAction("Access area"));
        result.add(new ResourceAction("Alter area"));
        return result;
    }
    
}
