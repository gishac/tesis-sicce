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

    public static void RegisterHibernateEncryptor() {
        StandardPBEStringEncryptor hibernateEncryptor = new StandardPBEStringEncryptor();
        hibernateEncryptor.setPassword("sicce");
        HibernatePBEEncryptorRegistry registry = HibernatePBEEncryptorRegistry.getInstance();
        registry.registerPBEStringEncryptor("configurationHibernateEncryptor",
                hibernateEncryptor);
    }
}
