package modelTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import model.User;
import model.security.Permission;
import model.security.Policy;
import model.security.ResourceAction;
import model.security.SecurityManager;
import model.security.Role;
import model.security.SecurityResource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kashwaa
 */
public class SecurityUnitTest {

    SecurityManager securityManager;
    static ResourceAction acRemoveArea;
    static SecurityResource resourceArea;

    public SecurityUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        acRemoveArea = new ActionRemoveArea("remove area");

        resourceArea = new SecurityResource() {
            @Override
            public List<ResourceAction> getAllowedActions() {

                List<ResourceAction> areaAllowedActions = setupAreaActions();
                return areaAllowedActions;
            }

            @Override
            public boolean autherize(User user, ResourceAction action) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getSecurityManagerInstanceTest() {
        assertNull(securityManager);

        assertNotNull("Couldn't create a security manager instance",
                securityManager = SecurityManager.getInstance());

        assertSame(SecurityManager.getInstance(), securityManager);
    }

    @Test(expected = IllegalArgumentException.class)
    public void duplicateRoleTest() {
        Role role = SecurityManager.getInstance().createRole("Admin");
        SecurityManager.getInstance().createRole("Admin");
    }

    @Test(expected = IllegalArgumentException.class)
    public void duplicatePolicyTest() {
        Policy policy = SecurityManager.getInstance().createPolicy("ManageAccess");
        SecurityManager.getInstance().createPolicy("ManageAccess");
    }

    @Test
    public void addPermissionTest() {
        Policy policy = SecurityManager.getInstance().createPolicy("ManageAccess");

        SecurityManager.getInstance().AddPermission(policy,
                new Permission(resourceArea, new HashSet<>(resourceArea.getAllowedActions())));

        Permission permission = policy.getPermissions().iterator().next();

        assertTrue(permission.getResource().equals(resourceArea));
        assertTrue(permission.getEffect() == Permission.EFFECT_GRANT);
        assertTrue(permission.getActions().contains(acRemoveArea));
    }
    
    @Test
    public void removeActionTest(){
        Policy policy = SecurityManager.getInstance().createPolicy("PolicyForDelete");

        SecurityManager.getInstance().AddPermission(policy,
                new Permission(resourceArea, new HashSet<>(resourceArea.getAllowedActions())));

        Permission permission = policy.getPermissions().iterator().next();

        permission.removeActionMatches("remove[\\s\\w]+");
        assertFalse(permission.getActions().contains(acRemoveArea));
    }

    @Test(expected = IllegalArgumentException.class)
    public void duplicatePermissionTest(){
        Policy policy = SecurityManager.getInstance().createPolicy("GrantAccess");
        
        SecurityManager.getInstance().AddPermission(policy, 
                new Permission(resourceArea, new HashSet<>(resourceArea.getAllowedActions())));
        
        //Trying to duplicate a permission int a policy should throw 
        //an IllegalArgumentExeption.
        SecurityManager.getInstance().AddPermission(policy, 
                new Permission(resourceArea, new HashSet<>(resourceArea.getAllowedActions())));
        
    }
    private static List<ResourceAction> setupAreaActions() {
        List<ResourceAction> actions = new ArrayList<>();

//        actions.add(new ResourceAction("create area") {
//            @Override
//            public void execute() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        });
        actions.add(new ActionRemoveArea("remove area"));
        actions.add(new ResourceAction("manage area") {
//            @Override
//            public void execute() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
        });

        return actions;
    }
    static class ActionRemoveArea extends ResourceAction{

        public ActionRemoveArea(String name) {
            super(name);
        }

       
        
    }
}
