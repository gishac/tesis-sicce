/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.hibernate.encryptor.HibernatePBEEncryptorRegistry;

/**
 *
 * @author gish@c
 */
public class EncryptionProvider {

    private static String password = "sicce";
    
    private static StandardPBEStringEncryptor getEncryptor(){
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(password);
        return encryptor;
    }
    
    public static void RegisterHibernateEncryptor() {
        
        HibernatePBEEncryptorRegistry registry = HibernatePBEEncryptorRegistry.getInstance();
        registry.registerPBEStringEncryptor("configurationHibernateEncryptor",
                getEncryptor());
    }
    
    public static String Encrypt(String value){ 
        String result = getEncryptor().encrypt(value);
        return result;
    }
    
    public static String Decrypt(String value){
        return getEncryptor().decrypt(value);
    }
}
