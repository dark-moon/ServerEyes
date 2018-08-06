
package model;

import database.DAOFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import model.security.ResourceAction;
import model.security.SecurityResource;
/**
 *
 * @author kashwaa
 */
public class Area implements SecurityResource{
    
    public static final String ACTION_MANAGE = "Manage area access";
    public static final String ACTION_ACCESS = "Access area";
    public static final String ACTION_ALTER = "Alter area";
    
    private static final DAOFactory sqlDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MY_SQL);
    
    private final List<ResourceAction> allowedActions;
    private int area_id = 0;
    private String areaName;
    private String areaDescription;
    
    public Area(){
//        this.allowedActions = new ArrayList<>();
        allowedActions = sqlDAOFactory.getAreaDAO().getAllowedActions();
    }
    
    public static Collection<Area> getAllAreas(){
        return sqlDAOFactory.getAreaDAO().getAllAreas();
    }
    
    @Override
    public boolean autherize(User user, ResourceAction action){
        return AutherizationTable.getInstance().Autherize(user, this, action);
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaDescription() {
        return areaDescription;
    }

    public void setAreaDescription(String areaDescription) {
        this.areaDescription = areaDescription;
    }

    @Override
    public List<ResourceAction> getAllowedActions() {
        return allowedActions;
    }
}
