package Logic;

import java.io.File;

public class CancionBase {
    protected CancionBase NextSong;
    protected File Location;
    protected File ImageUrl;
    protected String Nombre;
    
    public CancionBase(String Nombre, File ImageUrl, File Location){
        this.ImageUrl = ImageUrl;
        this.Location = Location;
        this.Nombre = Nombre;
        NextSong = null;
    }
    
    public void setNextSong(CancionBase NextSong){
        this.NextSong = NextSong;
    }
    
    public CancionBase getNextSong(){
        return NextSong;
    }
    
    public String getSongName(){
        return Nombre;
    }
    
    public File getLocation() {
        return Location;
    }
    
    public File getImageUrl(){
        return ImageUrl;
    }
    
}
