package Logic;

import java.io.File;

public class FileManager {
    public SongManager PlayList;
    
    public FileManager(){
        this.PlayList = new SongManager();
        
        File PlayList = new File("General");
        PlayList.mkdir();
        
        CreateDefaultPlayList(PlayList);
    }
    
    private void CreateDefaultPlayList(File AllSongs){
        for (String SongName : AllSongs.list()){
            PlayList.AddSong(Cancion(SongName));
        }
    }
    
    private CancionBase Cancion(String SongName){
        File Song = new File("General", SongName);
        File ImageURL = null;
        File Location = null;
        for (File Info : Song.listFiles()){
            if (Info.getName().contains(".mp3")){
                Location = Info;
            } else ImageURL = Info;
        }
        return (ImageURL != null && Location != null)?new CancionBase(SongName, ImageURL, Location):null;
    }
    
}
