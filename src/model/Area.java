
package model;

import database.AreaDAO;
import database.DAOFactory;
import database.MySqlAreaDAO;
import database.MySqlDAOFactory;

/**
 *
 * @author kashwaa
 */
public class Area {
    private static DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MY_SQL);
    
    public static Area[] getAllAreas(){
        return daoFactory.getAreaDAO().getAllAreas();
    }
    
    public boolean autherize(User user){
        //TODO: code to autherize user.
        
        
        return true;
    }
}
