package Main_Reproductor;

import Logic.FileManager;
import GUI.MainFrame;

/**
 *
 * @author josue
 */
public class Exe {
    
    public static void main(String[] args) {
        new MainFrame(new FileManager()).setVisible(true);
    }
    
}
