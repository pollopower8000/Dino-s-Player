/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dino.player;

import java.io.File;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;

import javax.swing.JTextField;

import javax.swing.UIManager;

/**
 *
 * @author Kevin
 */
public class jfrmMainGUI extends javax.swing.JFrame {

    //Main Variables
    int iPlay;
    int iChangeSize;
    Clip audioClipComp;
    JTextField tName;
    DefaultListModel addSongtoList = new DefaultListModel();
    DefaultListModel addPathtoList = new DefaultListModel();
    File fFichero;

    /**
     * Creates new form jfrmMainGUI
     */
    public jfrmMainGUI() {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnPlay = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jList1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 88, 298, 300));

        jList2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jScrollPane2.setViewportView(jList2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(315, 88, 350, 300));

        jButton1.setText("Song");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 58, 298, -1));

        jButton2.setText("Path");
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(315, 58, 350, -1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Play");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 40, 37));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Stop");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 12, 40, 37));

        btnPlay.setText("PlayButton");
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });
        getContentPane().add(btnPlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 60, 190, -1));

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Open...");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //Opens a File and add the File to a List
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        JFileChooser Fc = new JFileChooser();
        int iSeleccion = Fc.showOpenDialog(jList1);
        if (iSeleccion == JFileChooser.APPROVE_OPTION) {//Si nuestro archivo es txt y lo seleccionamos el programa hara las instrucciones a dar
            fFichero = Fc.getSelectedFile();//Se Obtiene el archivo que se selecciono en nuestro file chooser
            String sFile = fFichero.getName();
            if (sFile.contains("mp3")) {
                try {
                    if (iPlay > 0) {
                        audioClipComp.close();

                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
            } else if (sFile.contains(".wav")) {
                playandAddListofWAV16(fFichero);
            }

            /*
            
             */
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    //Opens a File From the list
    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        //IposSong == Position of the song
        //PathName == Name of the file from a path
        //getPath == The path of the song to open the song
        try {
            int iPosSong = jList1.getSelectedIndex();
            String PathName = (String) addPathtoList.getElementAt(iPosSong);

            Path pathSong = Paths.get(PathName);
            File getPath = pathSong.toFile();

            if (PathName.contains(".mp3")) {
                try {
                    if (iPlay > 0) {
                        audioClipComp.close();

                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
            } else if (PathName.contains(".wav")) {
                playFromaPositionofaListWAV16(getPath, iPosSong);

            }

        } catch (Exception e) {
        }


    }//GEN-LAST:event_jList1MouseClicked

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        // TODO add your handling code here:
        playandAddListofWAV16(fFichero);
    }//GEN-LAST:event_btnPlayActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */

//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(jfrmMainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jfrmMainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jfrmMainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jfrmMainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jfrmMainGUI().setVisible(true);
            }
        });
    }

    public void playMP3() {
    }

    //Play a file from the list (the file has to e added to the list)
    public void playFromaPositionofaListWAV16(File fPath, int iPosition) {
        try {
            if (iPlay > 0) {
                audioClipComp.close();

            }

            AudioInputStream audioStream;
            audioStream = AudioSystem.getAudioInputStream(fPath);
            AudioFormat format = audioStream.getFormat();

            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClipComp = audioClip;

            setTitle("Dino's Player Current Playing (" + addSongtoList.get(iPosition) + ")");

            audioClipComp.open(audioStream);
            audioClipComp.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(jfrmMainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Plays the file and add it to the list (File/File Path)
    public void playandAddListofWAV16(File fPath) {
        try {
            if (iPlay > 0) {
                audioClipComp.close();

            }
            addSongtoList.addElement(fPath.getName());
            addPathtoList.addElement(fPath.getAbsolutePath());
            addSongtoList.setElementAt(fPath.getName(), iPlay);
            addPathtoList.setElementAt(fPath.getAbsolutePath(), iPlay);
            jList1.setModel(addSongtoList);
            jList2.setModel(addPathtoList);

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(fPath.getAbsoluteFile());
            AudioFormat format = audioStream.getFormat();

            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClipComp = audioClip;

            setTitle("Dino's Player Current Playing (" + fFichero.getName() + ")");

            audioClipComp.open(audioStream);
            audioClipComp.start();

            iPlay++;

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(jfrmMainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void playFLAC() {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
