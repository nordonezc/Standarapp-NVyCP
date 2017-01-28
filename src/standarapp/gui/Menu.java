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
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Niki
 */
public class Menu extends javax.swing.JFrame {

    int xMouse;
    int yMouse;

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("/images/SPiconDesktop.png")).getImage());
        this.setTitle("StandarApp");
    }

    public Menu(int x, int y) {
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
        menuButton = new javax.swing.JButton();
        fixCharacters = new javax.swing.JRadioButton();
        readRegistry = new javax.swing.JRadioButton();
        search = new javax.swing.JRadioButton();
        labelNumber3 = new javax.swing.JLabel();
        labelNumber2 = new javax.swing.JLabel();
        labelNumber1 = new javax.swing.JLabel();
        labelTittle = new javax.swing.JLabel();
        exitButton = new javax.swing.JButton();
        minimizeButton = new javax.swing.JButton();
        iconLabel = new javax.swing.JLabel();
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

        menuButton.setBackground(new java.awt.Color(0, 153, 153));
        menuButton.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        menuButton.setForeground(new java.awt.Color(255, 255, 255));
        menuButton.setText("Seleccionar");
        menuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuButtonActionPerformed(evt);
            }
        });
        getContentPane().add(menuButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 110, -1));

        menuButtonGroup.add(fixCharacters);
        fixCharacters.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        fixCharacters.setSelected(true);
        fixCharacters.setText("Arreglar Caracteres");
        fixCharacters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fixCharactersActionPerformed(evt);
            }
        });
        getContentPane().add(fixCharacters, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 180, -1));

        menuButtonGroup.add(readRegistry);
        readRegistry.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        readRegistry.setText("Lectura de Casos");
        readRegistry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readRegistryActionPerformed(evt);
            }
        });
        getContentPane().add(readRegistry, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 160, -1));

        menuButtonGroup.add(search);
        search.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        search.setText("Búsqueda personalizada");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        getContentPane().add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 210, -1));

        labelNumber3.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        labelNumber3.setText("3.");
        getContentPane().add(labelNumber3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 40, 20));

        labelNumber2.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        labelNumber2.setText("2.");
        getContentPane().add(labelNumber2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 40, 20));

        labelNumber1.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        labelNumber1.setText("1.");
        getContentPane().add(labelNumber1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 40, 20));

        labelTittle.setBackground(new java.awt.Color(79, 152, 43));
        labelTittle.setFont(new java.awt.Font("Gill Sans MT", 1, 28)); // NOI18N
        labelTittle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTittle.setText("StandarApp 1.0");
        getContentPane().add(labelTittle, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 220, 40));

        exitButton.setBackground(new java.awt.Color(204, 51, 0));
        exitButton.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        exitButton.setText("Salir");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(exitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 70, -1));

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
        getContentPane().add(minimizeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 20, 20));

        iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SPicon.png"))); // NOI18N
        getContentPane().add(iconLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 22, -1, 40));

        derechosLabel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        derechosLabel.setForeground(new java.awt.Color(153, 153, 153));
        derechosLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        derechosLabel.setText("Designed by Nicolas Ordoñez Chala, 2017");
        getContentPane().add(derechosLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 260, 20));

        derechosEmailLabel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        derechosEmailLabel.setForeground(new java.awt.Color(153, 153, 153));
        derechosEmailLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        derechosEmailLabel.setText("info: nordonezc@unal.edu.co");
        getContentPane().add(derechosEmailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 260, 20));

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
        getContentPane().add(dragLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -6, 290, 260));

        backgroundLabel.setFont(new java.awt.Font("Gill Sans MT", 0, 11)); // NOI18N
        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/greenPolygons.jpg"))); // NOI18N
        getContentPane().add(backgroundLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 250));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButtonActionPerformed
        // TODO add your handling code here:
        if (fixCharacters.isSelected()) {
            this.setVisible(false);
            FixChar windowTwo = new FixChar(this.getX(), this.getY());
            windowTwo.setVisible(true);
        }
        if (readRegistry.isSelected()) {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setToolTipText("Seleccione el archivo xlsx con la base de datos");
            chooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xlsx"));
            chooser.setAcceptAllFileFilterUsed(false);

            int option = chooser.showOpenDialog(this);
            if (JFileChooser.CANCEL_OPTION == option) {
                //this.setVisible(true);
            }
            else if (JFileChooser.APPROVE_OPTION == option) {
                try {
                    ReadCases windowTwo;
                    windowTwo = new ReadCases(this.getX(), this.getY(), chooser.getSelectedFile().getAbsolutePath());
                    windowTwo.setVisible(true);
                    this.setVisible(false);
                } catch (IOException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (search.isSelected()) {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setToolTipText("Seleccione el archivo xlsx con la base de datos");
            chooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xlsx"));
            chooser.setAcceptAllFileFilterUsed(false);

            int option = chooser.showOpenDialog(this);
            if (JFileChooser.CANCEL_OPTION == option) {
                //this.setVisible(true);
            }
            else if (JFileChooser.APPROVE_OPTION == option) {
                try {
                ParticularSearch windowTwo;
                windowTwo = new ParticularSearch(this.getX(), this.getY(), chooser.getSelectedFile().getAbsolutePath());
                windowTwo.setVisible(true);
                this.setVisible(false);
            } catch (IOException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }//GEN-LAST:event_menuButtonActionPerformed

    private void fixCharactersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fixCharactersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fixCharactersActionPerformed

    private void readRegistryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readRegistryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_readRegistryActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundLabel;
    private javax.swing.JLabel derechosEmailLabel;
    private javax.swing.JLabel derechosLabel;
    private javax.swing.JLabel dragLabel;
    private javax.swing.JButton exitButton;
    private javax.swing.JRadioButton fixCharacters;
    private javax.swing.JLabel iconLabel;
    private javax.swing.JLabel labelNumber1;
    private javax.swing.JLabel labelNumber2;
    private javax.swing.JLabel labelNumber3;
    private javax.swing.JLabel labelTittle;
    private javax.swing.JButton menuButton;
    private javax.swing.ButtonGroup menuButtonGroup;
    private javax.swing.JButton minimizeButton;
    private javax.swing.JRadioButton readRegistry;
    private javax.swing.JRadioButton search;
    // End of variables declaration//GEN-END:variables
}
