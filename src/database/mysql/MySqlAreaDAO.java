package database.mysql;

import database.AreaDAO;
import java.util.Collection;
import java.util.Set;
import model.Area;
import model.User;

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
    
}
