package model.security;

import java.util.List;
import model.User;

/**
 *
 * @author kashwaa
 */
public interface SecurityResource {
    public List<ResourceAction> getAllowedActions();
    public boolean autherize(User user, ResourceAction action);
}
