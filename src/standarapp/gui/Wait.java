/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package standarapp.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Niki
 */
public class Wait extends javax.swing.JFrame {

    int xMouse;
    int yMouse;
    /**
     * Creates new form Menu
     */
    public Wait() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("/images/SPiconDesktop.png")).getImage());
        this.setTitle("StandarApp");
    }
    
    public Wait(int x, int y) {
        initComponents();
        this.setLocation(x, y);
        this.setIconImage(new ImageIcon(getClass().getResource("/images/SPicon.png")).getImage());
        this.setTitle("StandarApp");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuButtonGroup = new javax.swing.ButtonGroup();
        exitButton = new javax.swing.JButton();
        minimizeButton = new javax.swing.JButton();
        iconLabel = new javax.swing.JLabel();
        waitGif = new javax.swing.JLabel();
        derechosLabel = new javax.swing.JLabel();
        derechosEmailLabel = new javax.swing.JLabel();
        dragLabel = new javax.swing.JLabel();
        backgroundLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exitButton.setBackground(new java.awt.Color(204, 51, 0));
        exitButton.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        exitButton.setText("Salir");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(exitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 70, -1));

        minimizeButton.setBackground(new java.awt.Color(0, 153, 153));
        minimizeButton.setFont(new java.awt.Font("Adobe Arabic", 0, 3)); // NOI18N
        minimizeButton.setForeground(new java.awt.Color(255, 255, 255));
        minimizeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/minimize.png"))); // NOI18N
        minimizeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minimizeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimizeButtonActionPerformed(evt);
            }
        });
        getContentPane().add(minimizeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 20, 20));

        iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SPicon.png"))); // NOI18N
        getContentPane().add(iconLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 40));

        waitGif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/wait.gif"))); // NOI18N
        getContentPane().add(waitGif, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 300, 280));

        derechosLabel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        derechosLabel.setForeground(new java.awt.Color(153, 153, 153));
        derechosLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        derechosLabel.setText("Designed by Nicolas Ordoñez Chala, 2017");
        getContentPane().add(derechosLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 260, 20));

        derechosEmailLabel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        derechosEmailLabel.setForeground(new java.awt.Color(153, 153, 153));
        derechosEmailLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        derechosEmailLabel.setText("info: nordonezc@unal.edu.co");
        getContentPane().add(derechosEmailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 260, 20));

        dragLabel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                dragLabelMouseDragged(evt);
            }
        });
        dragLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dragLabelMousePressed(evt);
            }
        });
        getContentPane().add(dragLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -6, 390, 320));

        backgroundLabel.setFont(new java.awt.Font("Gill Sans MT", 0, 11)); // NOI18N
        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/greenPolygons.jpg"))); // NOI18N
        getContentPane().add(backgroundLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 310));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void minimizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeButtonActionPerformed
        // TODO add your handling code here:
        setState(this.ICONIFIED);
    }//GEN-LAST:event_minimizeButtonActionPerformed

    private void dragLabelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dragLabelMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_dragLabelMouseDragged

    private void dragLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dragLabelMousePressed
        // TODO add your handling code here:
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_dragLabelMousePressed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Wait.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Wait.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Wait.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Wait.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Wait().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundLabel;
    private javax.swing.JLabel derechosEmailLabel;
    private javax.swing.JLabel derechosLabel;
    private javax.swing.JLabel dragLabel;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel iconLabel;
    private javax.swing.ButtonGroup menuButtonGroup;
    private javax.swing.JButton minimizeButton;
    private javax.swing.JLabel waitGif;
    // End of variables declaration//GEN-END:variables
}
