package model.security;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author kashwaa
 */
public class Role implements Comparable<Role> {

    private String name;
    private String description;
    private Set<Policy> policies;

    public Role(String name) {
        this(name, "");
    }

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
        this.policies = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Policy> getPolicies() {
        return policies;
    }

    public boolean hasPermission(Permission permission) {
        return policies.stream().anyMatch((Policy policy)-> {
            return policy.getPermissions().contains(permission);
        });
    }
    
    public boolean isAllowedAction(SecurityResource resource, ResourceAction action){
        return policies.stream().anyMatch((Policy policy) -> 
                (policy.isAllowedAction(resource, action)));
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(Role o) {
        if (this.name.equals(o.getName())) {
            return 0;
        }
        return -1;
    }

    @Override
    public boolean equals(Object role) {
        if (role == null) {
            return false;
        }
        if (getClass() != role.getClass()) {
            return false;
        }
        final Role other = (Role) role;
        return this.name.equals(other.getName());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.name);
        return hash;
    }
}
