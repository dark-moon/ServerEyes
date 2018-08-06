package model;

import database.DAOFactory;
import database.NameTagDAO;

/**
 *
 * @author kashwaa
 */
public class NameTag {
    
    private int id;
    private final long assignedSignal;
    
    public static NameTagDAO getDAO(int daoFactoryType){
        return DAOFactory.getDAOFactory(daoFactoryType).getNameTagDAO();
    }
    
    public NameTag(long signal){
        this.assignedSignal = signal;
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
    
    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        NameTag other = (NameTag) obj;
        return (this.assignedSignal == other.assignedSignal && this.id == other.id);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + (int) (this.assignedSignal ^ (this.assignedSignal >>> 32));
        return hash;
    }
}
