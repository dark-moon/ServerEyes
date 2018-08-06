package model.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author kashwaa
 */
public class Policy{

    private String name;
    private Set<Permission> permissions;

    public Policy(String name) {
        this.name = name;
        this.permissions = new HashSet<>();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
    
    public List<Permission> getPermissionsAsList(){
        List<Permission> result = new ArrayList<>();
        this.permissions.forEach(result::add);
        return result;
    }
    
    public boolean addPermission(Permission permission){
        
        if (permissions.contains(permission)) {
            throw new IllegalArgumentException("A permission with the same name already exists");
        }
        this.permissions.add(permission);
        return true;
    }
    
    public boolean removePermission(Permission permission){
        return this.permissions.remove(permission);
    }
    
    public boolean isAllowedAction(SecurityResource resource, ResourceAction action){
        return permissions.stream().anyMatch((Permission permission) ->
                (permission.getResource().equals(resource) && 
                permission.getActions().contains(action) &&
                permission.getEffect() == Permission.EFFECT_GRANT));
    }
    
    @Override
    public boolean equals(Object object){
        if (object == null) {
            return false;
        }
        if (getClass() != object.getClass()) {
            return false;
        }
        Policy other = (Policy) object;
        return this.name.equals(other.getName());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }
}
