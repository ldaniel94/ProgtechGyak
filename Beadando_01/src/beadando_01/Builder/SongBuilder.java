package beadando_01.Builder;

import beadando_01.Builder.Song;

public class SongBuilder {
    
    private Song song;
    
    public SongBuilder SetTitle(String title){
        this.song.setTitle(title);
        return this;
    }
    
    public SongBuilder SetArtist(String artist){
        this.song.setArtist(artist);
        return this;
    }
    
    public SongBuilder SetAlbum(String album){
        this.song.setAlbum(album);
        return this;
    }
    
    public Song Build(){
        return song;
    }
    
    public SongBuilder(){
       
        this.song = new Song();
    }
}
