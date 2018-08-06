package model.security;

import java.util.Objects;

/**
 *
 * @author kashwaa
 */
public class ResourceAction {
    
    private final String name;
    private String description;
    
    public ResourceAction(String name){
        this.name = name;
    }
    
//    public abstract void execute();
    
    public String getName(){
        return name;
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ResourceAction other = (ResourceAction) obj;
        return this.getName().equals(other.getName());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.name);
        return hash;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
