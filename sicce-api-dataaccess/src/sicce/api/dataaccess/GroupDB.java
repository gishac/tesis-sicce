/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.dataaccess;

import java.util.List;
import sicce.api.info.interfaces.IGroup;

/**
 *
 * @author gish@c
 */
public class GroupDB {

    public static List<IGroup> GetAllGroups(){
        return DataAccessManager.getInstance().getGroupDB().findAll();
    }
    
}
