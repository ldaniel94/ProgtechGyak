package beadando_01.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterModel {
    
    Connection conn = null;
    ResultSet rs = null;
    Statement st;
    /**
     * Registers a new user into database with a name and password, and checks if the password is the same as confPasswd.
     * @param name Username for the new user
     * @param passwd Password for the new user
     * @param confPasswd Confirm password
     * @return Boolean value (True if successful registration, False if unsuccessful) 
     * @throws SQLException 
     */
    public Boolean Register(String name, String passwd, String confPasswd) throws SQLException{
        Boolean reg = false;
        
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/users");
            st = (Statement)conn.createStatement();
            rs = st.executeQuery("SELECT * FROM APP.USERS");
            
            while (rs.next()) {                
                
                if(rs.getString(2).equals(name)){
                    return false;
                }
            }
            if (passwd.equals(confPasswd)) {
                String regString = String.format("INSERT INTO APP.USERS(\"NAME\", PASSWORD) VALUES ('%s', '%s')", name, passwd);
                st.executeUpdate(regString);
                reg = true;
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return reg;
    }
}
