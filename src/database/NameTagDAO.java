package database;

import java.util.Collection;
import model.NameTag;

/**
 *
 * @author kashwaa
 */
public interface NameTagDAO {
    public Collection<NameTag> getAllNameTags();
    public NameTag findNameTag(NameTag criteria);
    public Collection<NameTag> findNameTags(NameTag criteria);
    public int insertNameTag(NameTag tag);
    public boolean updateNameTag(NameTag tag);
    public boolean deleteNameTag(NameTag tag);
    public Collection<NameTag> getUnusedNameTags();
    public Collection<NameTag> getTempDisabledNameTags();
    public Collection<NameTag> getTempActiveNameTags();
}
