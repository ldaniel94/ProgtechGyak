package beadando_01.Builder;

public class Song {
    
    private String title;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String artist;
    public String getArtist() {
        return artist;
    }

    
    public void setArtist(String artist) {
        this.artist = artist;
    }
    
    private String album;
    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
    
    public String ToString(){
        
        return artist + " - " + title + " (" + album + ")";
    }
    
    public Song(){
        
        this.title = "";
        this.artist = "";
        this.album = "";
    }
    
    public Song(String title, String artist, String album){
        
        this.title = title;
        this.artist = artist;
        this.album = album;
    }
}
