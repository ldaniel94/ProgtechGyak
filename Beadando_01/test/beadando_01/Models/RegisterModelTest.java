/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadando_01.Models;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ldope
 */
public class RegisterModelTest {
    
    public RegisterModelTest() {
    }
    
    /**
     * Register test: helyes adatok -> true
     * (A rekord az adatbázisba kerül)
     */
    @Test
    public void testRegister() throws Exception {        
        System.out.println("Register");
        String name = "név";
        String passwd = "jelszó";
        String confPasswd = "jelszó";
        RegisterModel instance = new RegisterModel();
        Boolean expResult = true;
        Boolean result = instance.Register(name, passwd, confPasswd);
        assertEquals(expResult, result);        
    }
    
    /**
     * Register test2: Nem egyező jelszó -> false
     */
    @Test
    public void testRegister2() throws Exception {        
        System.out.println("Register");
        String name = "név2";
        String passwd = "jelszó";
        String confPasswd = "jelszo";
        RegisterModel instance = new RegisterModel();
        Boolean expResult = false;
        Boolean result = instance.Register(name, passwd, confPasswd);
        assertEquals(expResult, result);        
    }
    
    /**
     * Register test3: Létező felhasználó -> false
     */
    @Test
    public void testRegister3() throws Exception {        
        System.out.println("Register");
        String name = "asd";
        String passwd = "jelszó";
        String confPasswd = "jelszó";
        RegisterModel instance = new RegisterModel();
        Boolean expResult = false;
        Boolean result = instance.Register(name, passwd, confPasswd);
        assertEquals(expResult, result);        
    }
}
