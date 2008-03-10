/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

/**
 *
 * @author gish@c
 */
public interface IUserSicce {

    IRole getRole();
    void setRole(IRole role);
    void setName(String name);
    String getName();
    void setPasswordSicce(String passwordSicce);
    String getPasswordSicce();
    void setUsernameSicce(String usernameSicce);
    String getUsernameSicce();
    void setLastname(String lastnameSicce);
    String getLastname();
    Integer getID();
    
}
