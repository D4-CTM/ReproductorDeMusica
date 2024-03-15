package GUI;

import Logic.CancionBase;
import Logic.FileManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javazoom.jl.player.Player;

/**
 *
 * @author josue
 */
public class Panel extends JPanel implements Runnable{
    //Clase encargada de conseguir los archivos
    private final FileManager FM;
    //Variables usadas en los hilos
    private boolean PonerMusica;
    //Variables usadas en el swing
    private final int W = 800;
    private final int H = 600;
    public Panel(FileManager FM){
        this.FM = FM;
        
        setPreferredSize(new Dimension(W,H));
        setBackground(Color.LIGHT_GRAY);
        setLayout(null);
        
        setElements();
        new Thread(this).start();
    }
    
    // <editor-fold desc="Swing elements">                          
    private void setElements(){
        JButton Back = GoBackBTN();
        add(Back);
        
        JButton Pause = Pause(Back.getX() + Back.getWidth() + 75);
        add(Pause);
        
        JButton Next = NextSong(Pause.getX() + Pause.getWidth() + 75);
        add(Next);
        
        setSongBox();
        add(Rolas);
        
        JLabel RolasTXT = SongTXT();
        add(RolasTXT);
        
        setSongImage();
        add(SongImage);
    }
    
    private JButton GoBackBTN(){
        JButton Back = new JButton();
        
        Back.setBounds(25, 425, 200, 100);
        Back.setOpaque(false);
        Back.setIcon(ScaledImage(new File("Icons\\NextSong.png").getAbsolutePath(), Back.getWidth(), Back.getHeight()));
        Back.addActionListener((ActionEvt)->{
            try {
                Rolas.setSelectedIndex(Rolas.getSelectedIndex() - 1);
            } catch (Exception Ex){
                Rolas.setSelectedIndex(FM.PlayList.getSize() - 1);
            }
        });
        
        return Back;
    }
    
    private JButton Pause(int X){
        JButton Pause = new JButton();
        
        Pause.setBounds(X, 425, 200, 100);
        Pause.setOpaque(false);
        Pause.setIcon(ScaledImage(new File("Icons\\PP.png").getAbsolutePath(), Pause.getWidth(), Pause.getHeight()));
        Pause.addActionListener((ActionEvt)->{
            PonerMusica = !PonerMusica;
            if (!PonerMusica){
                Vinilo.close();
            }
        });
        
        return Pause;
    }
    
    private JButton NextSong(int X){
        JButton Next = new JButton();
        
        Next.setBounds(X, 425, 200, 100);
        Next.setOpaque(false);
        Next.setIcon(ScaledImage(new File("Icons\\NextSong.png").getAbsolutePath(), Next.getWidth(), Next.getHeight()));
        Next.addActionListener((ActionEvt)->{
            try {
                Rolas.setSelectedIndex(Rolas.getSelectedIndex() + 1);
            } catch (Exception Ex){
                Rolas.setSelectedIndex(0);
            }
        });
        
        return Next;
    }
    
    private JLabel SongTXT(){
        JLabel SongTXT = new JLabel("Canciones: ");
        
        SongTXT.setBounds(25, 25, 250, 25);
        
        return SongTXT;
    }
    
    private void setSongBox(){
        Rolas = new JComboBox();
        
        Rolas.setBounds(25, 50, 250, 25);
        Rolas.setModel(new javax.swing.DefaultComboBoxModel(FM.PlayList.getSongList()));
        Rolas.addActionListener((ActionEvent) -> {
            ChangeSongImage();
            PonerMusica = false;
            if (Vinilo != null){
                Vinilo.close();
            }
        });
    }
    
    private void setSongImage(){
        SongImage = new JLabel();
        
        SongImage.setBounds(W/2 - 150, 100, 300, 300);
        ChangeSongImage();
    }
    //</editor-fold>
    
    private void ChangeSongImage(){
        CancionBase Cancion = FM.PlayList.getSong(Rolas.getSelectedIndex());
        
        SongImage.setIcon(ScaledImage(Cancion.getImageUrl().getAbsolutePath(), SongImage.getWidth(), SongImage.getHeight()));
    }
    
    private ImageIcon ScaledImage(String Url, int Width, int Height) {
        ImageIcon neoIcon = new ImageIcon(Url);
        Image scaledCard = neoIcon.getImage().getScaledInstance(Width, Height, Image.SCALE_SMOOTH);
        neoIcon = new ImageIcon(scaledCard);
        return neoIcon;
    }
        
    private void PlaySong(){
        try {
            if (PonerMusica){
                CancionBase Cancion = FM.PlayList.getSong(Rolas.getSelectedIndex());

                FileInputStream FileI = new FileInputStream(Cancion.getLocation());
                BufferedInputStream Input = new BufferedInputStream(FileI);
                Vinilo = new Player(Input);
                Vinilo.play();
            }
        } catch (Exception Ex){
            Ex.printStackTrace();
        }
    }
    
    @Override
    public void run(){
        while (true){
            PlaySong();
        }
    }
    
    //-- SWING ELEMENTS --
    private JLabel SongImage;
    private JComboBox Rolas;
    private Player Vinilo;
    //-- SWING ELEMENTS --
}
