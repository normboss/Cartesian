/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartesian3;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author Norm
 */
public class MP3PlayerClass 
{
    FileInputStream FIS;
    BufferedInputStream BIS;
    
    public Player player;
    public long pauseLocation;
    public long songTotalLength;
    public String fileLocation;
    
   
    public void Play(String path) 
    {
        try {
            FIS = new FileInputStream(path);
            BIS = new BufferedInputStream(FIS);
            
            player = new Player(BIS);
            songTotalLength = FIS.available();
            fileLocation = path + "";
            
        } catch (FileNotFoundException | JavaLayerException ex ) {
            
        } catch (IOException ex) {
            Logger.getLogger(MP3PlayerClass.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        new Thread(){
            @Override
            public void run(){
                try {
                    player.play();
                    
//                    if(player.isComplete() && MP3PlayerGUI.count == 1){
//                        Play(fileLocation);
//                    }
//                    if(player.isComplete()){
//                        MP3PlayerGUI.Display.setText("");
//                    }
                } catch (JavaLayerException ex) {
                    
                }
            }
        }.start();
        
    }
   
}
