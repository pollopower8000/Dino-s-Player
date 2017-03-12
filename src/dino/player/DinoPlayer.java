/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dino.player;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Kevin
 */
public class DinoPlayer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        jfrmMainGUI Main = new jfrmMainGUI();
        Main.setTitle("Dino's Player");
        Main.setSize(883, 450);
        Main.setVisible(true);
        Main.setResizable(false);
        Main.setLocationRelativeTo(Main);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        } catch (InstantiationException ex) {
            Logger.getLogger(DinoPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DinoPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(DinoPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DinoPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
