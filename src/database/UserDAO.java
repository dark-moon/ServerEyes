package database;

import java.util.Collection;
import model.User;

/**
 *
 * @author kashwaa
 */
public interface UserDAO {
    
    public Collection<User> getAllUsers();
    public User findUser(User user);
    public Collection<User> findUsers(User criteria);
    public int insertUser(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(User user);

}
