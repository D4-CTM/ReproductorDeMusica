package Logic;

import java.io.File;

public class SongManager {
    protected CancionBase Inicio;
    protected int Size;
    
    public SongManager(){
        Inicio = null;
        Size = 0;
    }
    
    public boolean isEmpty(){
        return Inicio == null;
    }
    
    public boolean AddSong(String SongName, File ImagePath, File Location){
        return AddSong(new CancionBase(SongName, ImagePath, Location), Inicio);
    }
    
    public boolean AddSong(CancionBase newSong){
        return AddSong(newSong, Inicio);
    }
    
    private boolean AddSong(CancionBase newSong, CancionBase ActualSong){
        if (newSong == null) return false;
        
        if (Inicio == null){
            Inicio = newSong;
            Size++;
            return true;
        } else if (ActualSong != null) {

            if (ActualSong.NextSong == null){
                
                ActualSong.NextSong = newSong;
                Size++;
                return true;
                
            } else return AddSong(newSong, ActualSong.NextSong);
        }
        return false;
    }
    
    public boolean RemoveSong(int Index){
        if (Inicio != null){
            CancionBase ActualSong = Inicio;
            for (int i = -1; i < Size; i++){
                if (i + 1 == Index){
                    ActualSong.NextSong = ActualSong.NextSong.NextSong;
                    Size--;
                    return true;
                }
                ActualSong = ActualSong.NextSong;
            }
        }
        return false;
    }
    
    public CancionBase getSong(int Index){
        if (Inicio != null){
        CancionBase ActualSong = Inicio;
            for (int i = 0; i < Size; i++){
                if (i == Index){
                    return ActualSong;
                }
                ActualSong = ActualSong.NextSong;
            }
        }
        return null;
    }
    
    public String[] getSongList(){
        if (!isEmpty()){
            String[] Songs = new String[Size];
            CancionBase Cancion = Inicio;

            for (int i = 0; i < Size; i++){
                Songs[i] = Cancion.getSongName();
                Cancion = Cancion.NextSong;
            }

            return Songs;
        }
        return null;
    }
    
}
