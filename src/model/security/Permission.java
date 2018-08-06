package model.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author kashwaa
 */
public class Permission {

    public final static int EFFECT_GRANT = 0;
    public final static int EFFECT_DENY = -1;
    
    private SecurityResource resource;
    private int effect;
    private Set<ResourceAction> actions;

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Permission(SecurityResource resource) {
        this(resource, new HashSet<ResourceAction>(), EFFECT_GRANT);
    }

    public Permission(SecurityResource resource, Set<ResourceAction> actions) {
        this(resource, actions, EFFECT_GRANT);
    }
    
    public Permission(SecurityResource resource, Set<ResourceAction> actions, int effect) {
        this.resource = resource;
        this.actions = actions;
        this.effect = effect;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    public SecurityResource getResource() {
        return resource;
    }

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }

    public Set<ResourceAction> getActions() {
        return actions;
    }
    
    public List<ResourceAction> getActionsAsList(){
        List<ResourceAction> result = new ArrayList<>();
        this.actions.forEach(result::add);
        return result;
    }
    //</editor-fold>
    
    public boolean addAction(ResourceAction action){
        return this.actions.add(action);
    }
    
    public boolean addAllActions(Collection<ResourceAction> actions){
        return this.actions.addAll(actions);
    }
    
    public ResourceAction getActionByName(String actionName){
        for (ResourceAction next : this.actions) {
            if (next.getName().equals(actionName)) {
                return next;
            }
        }
        return null;
    }
    
    public List<ResourceAction> getActionsMatches(String regex){
        List<ResourceAction> result = new ArrayList<>();
        this.actions.stream().map((ResourceAction next) -> {
            if (next.getName().matches(regex)) {
                result.add(next);
            }
            return next;
        }).forEachOrdered(result::add);
        return result;
    }
    
    public boolean removeActionByName(String actionName){
        for (Iterator<ResourceAction> iterator = actions.iterator(); iterator.hasNext();) {
            ResourceAction next = iterator.next();
            if (next.getName().equals(actionName)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
    
    public boolean removeActionMatches(String regex){
        for (Iterator<ResourceAction> iterator = actions.iterator(); iterator.hasNext();) {
            ResourceAction next = iterator.next();
            if (next.getName().matches(regex)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Permission other = (Permission) obj;
        if(!this.resource.equals(other.resource) && this.effect != other.effect){
            return false;
        }
        
        return (this.actions.isEmpty()? 
                other.actions.isEmpty() : 
                this.actions.containsAll(other.actions));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.resource);
        hash = 53 * hash + this.effect;
        hash = 53 * hash + Objects.hashCode(this.actions);
        return hash;
    }
}
