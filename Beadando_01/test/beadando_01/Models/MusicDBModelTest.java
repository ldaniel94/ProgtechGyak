/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadando_01.Models;

import beadando_01.Builder.SongBuilder;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ldope
 */
public class MusicDBModelTest {
    
    public MusicDBModelTest() {
    }
    
    /**
     * AddSong teszt: Helyes adatok -> true
     */
    @Test
    public void testAddSong() throws Exception {
        System.out.println("AddSong");
        String title = "cím";
        String artist = "előadó";
        String album = "album";
        MusicDBModel instance = new MusicDBModel();
        SongBuilder builder = new SongBuilder();
        String expResult = builder.SetTitle("cím").SetArtist("előadó").SetAlbum("album").Build().ToString();
        String result = instance.AddSong(title, artist, album).ToString();
        assertEquals(expResult, result);
    }
    
}
