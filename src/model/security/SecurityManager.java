package model.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author kashwaa
 */
public class SecurityManager {
    private static SecurityManager singletone;
    private static Object lock = new Object();
    
    private Set<Role> roles;
    private List<Policy> policies;
    
    private SecurityManager(){
        this.roles = new HashSet<>();
        this.policies = new ArrayList<>();
    }
    
    public static SecurityManager getInstance(){
        if (singletone == null) {
            synchronized (lock) {
                if (singletone == null) {
                    singletone = new SecurityManager();
                }
            }
        } 
        return singletone;
    }
    
    public Role createRole(String name){
        Role role = new Role(name);
        if (roles.contains(role)) {
            throw new IllegalArgumentException("A Role with the same name already exists");
        }
        roles.add(role);
        return role;
    }
    
    public Policy createPolicy(String name){
        Policy policy = new Policy(name);
        if (policies.contains(policy)) {
            throw new IllegalArgumentException("A Policy with the same name already exists");
        }
        this.policies.add(policy);
        return policy;
    }
    
    public boolean AddPermission(Policy policy, Permission permission){
        if (policy.getPermissions().contains(permission)) {
            throw new IllegalArgumentException("The defined policy already contains this permission");
        }
        return policy.addPermission(permission);
    }
    
    public boolean checkPermission(SecurityResource resource, Permission permission){
        throw new UnsupportedOperationException("Not supported yet");
    }
}
