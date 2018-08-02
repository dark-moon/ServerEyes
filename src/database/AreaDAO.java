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
    public Area[] getAllAreas();
    public Area findArea(Area criteria);
    public Collection<Area> findAreas(Area criteria);
    public int insertArea(Area area);
    public boolean updateArea(Area area);
    public boolean deleteArea(Area area);
    public Set<User> getAllowedUsers(Area area);
    public boolean autherize(Area area, User user);
}
