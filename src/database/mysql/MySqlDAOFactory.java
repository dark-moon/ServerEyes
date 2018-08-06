package database.mysql;

import database.AreaDAO;
import database.DAOFactory;
import database.NameTagDAO;
import database.UserDAO;

/**
 *
 * @author kashwaa
 */
public class MySqlDAOFactory extends DAOFactory{

    @Override
    public AreaDAO getAreaDAO() {
        return new MySqlAreaDAO();
    }

    @Override
    public UserDAO getUserDAO() {
        return new MySqlUserDAO();
    }

    @Override
    public NameTagDAO getNameTagDAO() {
        return new MySqlNameTagDAO();
    }
    
}
