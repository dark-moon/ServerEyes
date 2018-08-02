package model;

import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author kashwaa
 */
public class SecurityGroup {

    private Collection<Area> allowedAreas;
    
    public SecurityGroup(){
        this.allowedAreas = new HashSet<>();
    }

    public Collection<Area> getAllowedAreas() {
        return allowedAreas;
    }
    
}
