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
public class LoginModelTest {
    
    public LoginModelTest() {
    }

    /**
     * Login test: Üres mezők -> false
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println("Login");
        String name = "";
        String passwd = "";
        LoginModel instance = new LoginModel();
        Boolean expResult = false;
        Boolean result = instance.Login(name, passwd);
        assertEquals(expResult, result);
    }
    
    /**
     * Login test: Üres felhasználónév mező  -> false
     */
    @Test
    public void testLogin2() throws Exception {
        System.out.println("Login");
        String name = "";
        String passwd = "asd";
        LoginModel instance = new LoginModel();
        Boolean expResult = false;
        Boolean result = instance.Login(name, passwd);
        assertEquals(expResult, result);
    }
    
    /**
     * Login test: Üres jelszó mező  -> false
     */
    @Test
    public void testLogin3() throws Exception {
        System.out.println("Login");
        String name = "asd";
        String passwd = "";
        LoginModel instance = new LoginModel();
        Boolean expResult = false;
        Boolean result = instance.Login(name, passwd);
        assertEquals(expResult, result);
    }
    
    /**
     * Login test: Létező felhasználó -> true
     */
    @Test
    public void testLogin4() throws Exception {
        System.out.println("Login");
        String name = "asd";
        String passwd = "asd";
        LoginModel instance = new LoginModel();
        Boolean expResult = true;
        Boolean result = instance.Login(name, passwd);
        assertEquals(expResult, result);
    }
}
