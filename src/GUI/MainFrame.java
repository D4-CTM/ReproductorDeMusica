package GUI;

import Logic.FileManager;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
    private final FileManager FM;
    
    public MainFrame(FileManager FM){
        this.FM = FM;
        
        add(new Panel(FM));
        pack();
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
    }
    
    
}
