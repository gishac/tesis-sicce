/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.businesslogic;

import sicce.api.info.OptionSicce;
import sicce.api.info.Role;
import sicce.api.info.interfaces.IOptionSicce;
import sicce.api.info.interfaces.IRole;

/**
 *
 * @author gish@c
 */
public class ClassFactory {

    public static IRole getRoleInstance()
    {
        return new Role();
    }
    
    public static IOptionSicce getOptionInstance()
    {
        return new OptionSicce();
    }
    
}
