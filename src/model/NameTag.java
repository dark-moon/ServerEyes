package model;

import database.DAOFactory;
import database.NameTagDAO;

/**
 *
 * @author kashwaa
 */
public class NameTag {
    
    private int id;
    
    public static NameTagDAO getDAO(int daoFactoryType){
        return DAOFactory.getDAOFactory(daoFactoryType).getNameTagDAO();
    }
    
    public void enable(){
        throw new UnsupportedOperationException("Not supported yet");
    }
    
    public void disable(){
        throw new UnsupportedOperationException("Not supported yet");
    }
    
    public boolean isEnabled(){
        throw new UnsupportedOperationException("Not supported yet");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
