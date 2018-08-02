
package model;

import database.DAOFactory;
import java.util.Collection;

/**
 *
 * @author kashwaa
 */
public class Area {
    private static final DAOFactory sqlDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MY_SQL);
    
    private int area_id = 0;
    private String areaName;
    private String areaDescription;
    
    public static Collection<Area> getAllAreas(){
        return sqlDAOFactory.getAreaDAO().getAllAreas();
    }
    
    public boolean autherize(User user){
        //TODO: code to autherize user.
        for (SecurityGroup group : user.getGroups()) {
            if (group.getAllowedAreas().contains(this)) {
                return true;
            }
        }
        return false;
//        return sqlDAOFactory.getAreaDAO().autherize(this, user);
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
}
