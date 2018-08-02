package database;

import java.util.Collection;
import java.util.Set;
import model.Area;
import model.User;

/**
 *
 * @author kashwaa
 */
public interface AreaDAO {
    public Collection<Area> getAllAreas();
    public Area findArea(Area criteria);
    public Collection<Area> findAreas(Area criteria);
    public int insertArea(Area area);
    public boolean updateArea(Area area);
    public boolean deleteArea(Area area);
    public Collection<User> getAllowedUsers(Area area);
    public boolean autherize(Area area, User user);
}
