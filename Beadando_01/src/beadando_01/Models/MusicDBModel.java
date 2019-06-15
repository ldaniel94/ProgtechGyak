package beadando_01.Models;

import beadando_01.Builder.Song;
import beadando_01.Builder.SongBuilder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;

public class MusicDBModel {
    
    Connection conn = null;
    ResultSet rs = null;
    Statement st;
    
    /**
     * Adds a song into the database.
     * @param title The song's title
     * @param artist The song's artist
     * @param album The album which has the song
     * @return
     * @throws SQLException 
     */
    public Song AddSong(String title, String artist, String album) throws SQLException{
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/users");
            st = (Statement)conn.createStatement();
            SongBuilder builder = new SongBuilder();
            Song song = builder.SetTitle(title).SetArtist(artist).SetAlbum(album).Build();
            String addString = String.format
                        ("INSERT INTO APP.SONGS (\"title\", \"artist\", \"album\") VALUES ('%s', '%s', '%s')", 
                        title, artist, album);
            st.executeUpdate(addString);
            return song;
        }
        catch(SQLException ex){
            throw ex;
        }
    }
    
    /**
     * Refreshes the contents of a list from the database.
     * @param list The list you want to refresh.
     * @throws SQLException 
     */
    public void RefreshList(DefaultListModel list) throws SQLException{
        try {
            list.clear();
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/users");
            st = (Statement)conn.createStatement();
            rs = st.executeQuery("SELECT * FROM APP.SONGS");
            while (rs.next()) {                
                list.addElement(rs.getString(3) + " - " + rs.getString(2) + " (" + rs.getString(4) + ")");
            }            
        } catch (SQLException ex) {
            throw ex;
        }
    }
}
