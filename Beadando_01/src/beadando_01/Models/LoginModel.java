package beadando_01.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    
    Connection conn = null;
    ResultSet rs = null;
    Statement st;
    /**
     * Checks if a user is in the database and returns a logical value.
     * @param name Username of the registered user
     * @param passwd Password of the registered user
     * @return Boolean value (True if successful login, False if unsuccessful) 
     * @throws SQLException 
     */
    public Boolean Login(String name, String passwd) throws SQLException{
        Boolean log = false;
        
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/users");
            st = (Statement)conn.createStatement();
            rs = st.executeQuery("SELECT * FROM APP.USERS");
            
            while (rs.next()) {                
                
                if(rs.getString(2).equals(name) && rs.getString(3).equals(passwd)){
                    log = true;
                    break;
                }
            }            
        } catch (SQLException ex) {
            throw ex;
        }
        return log;
    }
}
