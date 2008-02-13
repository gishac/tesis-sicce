package sicce.api.info;

import java.util.List;
import org.apache.cayenne.ObjectId;
import sicce.api.info.auto._Role;
import sicce.api.info.interfaces.IOptionRole;
import sicce.api.info.interfaces.IRole;

public class Role extends _Role implements IRole {

    public static final String ROLE_ID = "ID_ROLE";

    public int getID() {
        return (getObjectId() != null) ? Integer.parseInt(getObjectId().getIdSnapshot().get(ROLE_ID).toString()) : 0;
    }

    public List<IOptionRole> getPermissions() {
        return getOptionRoleArray();
    }

    public void generateID() {
        this.setObjectId(new ObjectId(Role.class.getName()));
    }

    @Override
    public Object clone() {
        Role role = new Role();
        role.setDescription(this.getDescription());
        role.setID(this.getObjectId());
        if (this.getPermissions() != null) {
            for (IOptionRole permission : this.getPermissions()) {
                role.addToOptionRoleArray((OptionRole) permission);
            }
        }
        return role;
    }

    public void setID(Object id) {
        this.setObjectId((ObjectId) id);
    }
}



