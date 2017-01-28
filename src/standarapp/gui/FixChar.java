/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package standarapp.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import standarapp.algorithm.Lecture;

/**
 *
 * @author Niki
 */
public class FixChar extends javax.swing.JFrame {

    int xMouse;
    int yMouse;

    /**
     * Creates new form Menu
     */
    public FixChar(int x, int y) {
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

        minimizeButton = new javax.swing.JButton();
        labelTittle = new javax.swing.JLabel();
        instructionOne = new javax.swing.JLabel();
        inFileTextField = new javax.swing.JTextField();
        inFileButton = new javax.swing.JButton();
        instructionTwo = new javax.swing.JLabel();
        collumnsTextField = new javax.swing.JTextField();
        omitLabelOne = new javax.swing.JLabel();
        numberPageLabel = new javax.swing.JLabel();
        numberPageTextField = new javax.swing.JTextField();
        omitLabelTwo = new javax.swing.JLabel();
        instructionFour = new javax.swing.JLabel();
        outFileTextField = new javax.swing.JTextField();
        outFileButton = new javax.swing.JButton();
        omitLabelThree = new javax.swing.JLabel();
        answerLabel = new javax.swing.JLabel();
        doButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        derechosLabel = new javax.swing.JLabel();
        derechosEmailLabel = new javax.swing.JLabel();
        iconLabel = new javax.swing.JLabel();
        dragLabel = new javax.swing.JLabel();
        backgroundLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        minimizeButton.setBackground(new java.awt.Color(0, 153, 153));
        minimizeButton.setFont(new java.awt.Font("Adobe Arabic", 0, 3)); // NOI18N
        minimizeButton.setForeground(new java.awt.Color(255, 255, 255));
        minimizeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/minimize.png"))); // NOI18N
        minimizeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimizeButtonActionPerformed(evt);
            }
        });
        getContentPane().add(minimizeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 20, 20));

        labelTittle.setBackground(new java.awt.Color(79, 152, 43));
        labelTittle.setFont(new java.awt.Font("Gill Sans MT", 0, 28)); // NOI18N
        labelTittle.setText("StandarApp 1.0");
        getContentPane().add(labelTittle, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 220, 40));

        instructionOne.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        instructionOne.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        instructionOne.setText("Indique la ruta del archivo de entrada");
        getContentPane().add(instructionOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, 20));

        inFileTextField.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        inFileTextField.setText("Dirección del archivo de entrada...");
        getContentPane().add(inFileTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 390, 30));

        inFileButton.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        inFileButton.setText("jButton1");
        inFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inFileButtonActionPerformed(evt);
            }
        });
        getContentPane().add(inFileButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 30, 30));

        instructionTwo.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        instructionTwo.setText("Digite los numeros de las columnas:");
        getContentPane().add(instructionTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 240, 30));

        collumnsTextField.setFont(new java.awt.Font("Gill Sans MT", 0, 12)); // NOI18N
        collumnsTextField.setText("0,1,2,...");
        collumnsTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                collumnsTextFieldActionPerformed(evt);
            }
        });
        getContentPane().add(collumnsTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, 210, 30));

        omitLabelOne.setFont(new java.awt.Font("Gill Sans MT", 0, 10)); // NOI18N
        omitLabelOne.setForeground(new java.awt.Color(51, 51, 51));
        omitLabelOne.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        omitLabelOne.setText("(Si desea corregir todo el archivo, ignore este paso)");
        getContentPane().add(omitLabelOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, -1, -1));

        numberPageLabel.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        numberPageLabel.setText("Digite el numero de la pagina:");
        getContentPane().add(numberPageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 200, 30));

        numberPageTextField.setFont(new java.awt.Font("Gill Sans MT", 0, 12)); // NOI18N
        numberPageTextField.setText("0");
        numberPageTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberPageTextFieldActionPerformed(evt);
            }
        });
        getContentPane().add(numberPageTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 80, 30));

        omitLabelTwo.setFont(new java.awt.Font("Gill Sans MT", 0, 10)); // NOI18N
        omitLabelTwo.setForeground(new java.awt.Color(51, 51, 51));
        omitLabelTwo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        omitLabelTwo.setText("(Omitir, en caso de no conocer)");
        getContentPane().add(omitLabelTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, 150, 30));

        instructionFour.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        instructionFour.setText("Indique la ruta del archivo de salida");
        getContentPane().add(instructionFour, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 230, 20));

        outFileTextField.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        outFileTextField.setText("Ubicacion para el archivo de salida...");
        getContentPane().add(outFileTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 390, 30));

        outFileButton.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        outFileButton.setText("jButton1");
        outFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outFileButtonActionPerformed(evt);
            }
        });
        getContentPane().add(outFileButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 250, 30, 30));

        omitLabelThree.setFont(new java.awt.Font("Gill Sans MT", 0, 10)); // NOI18N
        omitLabelThree.setForeground(new java.awt.Color(51, 51, 51));
        omitLabelThree.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        omitLabelThree.setText("(Si desea sobreescribir el archivo de entrada,  ignore este paso, y presione arreglar)");
        getContentPane().add(omitLabelThree, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, -1, -1));

        answerLabel.setFont(new java.awt.Font("Perpetua Titling MT", 1, 11)); // NOI18N
        answerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        answerLabel.setText("Presione arreglar para continuar");
        getContentPane().add(answerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 430, -1));

        doButton.setBackground(new java.awt.Color(0, 153, 153));
        doButton.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        doButton.setForeground(new java.awt.Color(255, 255, 255));
        doButton.setText("Arreglar");
        doButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doButtonActionPerformed(evt);
            }
        });
        getContentPane().add(doButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 330, 110, -1));

        backButton.setBackground(new java.awt.Color(0, 153, 153));
        backButton.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        backButton.setForeground(new java.awt.Color(255, 255, 255));
        backButton.setText("Regresar");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        getContentPane().add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, 110, -1));

        exitButton.setBackground(new java.awt.Color(204, 51, 0));
        exitButton.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        exitButton.setText("Salir");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(exitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 360, 70, -1));

        derechosLabel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        derechosLabel.setForeground(new java.awt.Color(153, 153, 153));
        derechosLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        derechosLabel.setText("Designed by Nicolas Ordoñez Chala, 2017");
        getContentPane().add(derechosLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 260, 20));

        derechosEmailLabel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        derechosEmailLabel.setForeground(new java.awt.Color(153, 153, 153));
        derechosEmailLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        derechosEmailLabel.setText("info: nordonezc@unal.edu.co");
        getContentPane().add(derechosEmailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 390, 260, 20));

        iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SPicon.png"))); // NOI18N
        getContentPane().add(iconLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, 40));

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
        getContentPane().add(dragLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -6, 530, 420));

        backgroundLabel.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/greenPolygons.jpg"))); // NOI18N
        getContentPane().add(backgroundLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void doButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doButtonActionPerformed
        // TODO add your handling code here:
        Lecture lec = new Lecture();
        String nameIn = inFileTextField.getText();
        String nameOut = outFileTextField.getText();
        String collumns = collumnsTextField.getText();
        int[] col = {};

        double pages = Double.valueOf(numberPageTextField.getText());

        if (!collumns.equals("0,1,2,...")) {
            String[] temporal = collumns.split(",");
            col = new int[temporal.length];
            for (int i = 0; i < temporal.length; i++) {
                double tmp = Double.valueOf(temporal[i]);
                col[i] = (int) tmp;
            }
        }
        try {
            //C:\Users\Niki\Downloads\municipio de cada casco urbano.xls
            if (!nameIn.contains("...")) {
                if (nameOut.contains("...")) {
                    lec.fixFile(nameIn, (int) pages, col);
                    answerLabel.setForeground(Color.BLUE);
                    answerLabel.setText("Archivo corregido en: " + nameIn);

                } else if (Lecture.determineExtensionFile(nameIn)) {
                    lec.fixFile(nameIn, nameOut + "\\fixedFile.xlsx", (int) pages, col);
                    answerLabel.setForeground(Color.BLUE);
                    answerLabel.setText("Archivo corregido en: " + nameOut + "\\fixedFile.xlsx");
                } else {
                    lec.fixFile(nameIn, nameOut + "\\fixedFile.xls", (int) pages, col);
                    answerLabel.setForeground(Color.BLUE);
                    answerLabel.setText("Archivo corregido en: " + nameOut + "\\fixedFile.xls");
                }
            } else {
                answerLabel.setForeground(Color.red);
                answerLabel.setText("No ha seleccionado ningun archivo");
            }
        } catch (Exception e) {
            answerLabel.setForeground(Color.red);
            answerLabel.setText("Revisar los campos de seleccion de archivos");
        }


    }//GEN-LAST:event_doButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Menu windowTwo = new Menu(this.getX(), this.getY());
        windowTwo.setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

    private void numberPageTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberPageTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numberPageTextFieldActionPerformed

    private void collumnsTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_collumnsTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_collumnsTextFieldActionPerformed

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

    private void inFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inFileButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xls", "xlsx"));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("xls Files", "xls"));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("xlsx Files", "xlsx"));
        chooser.setAcceptAllFileFilterUsed(false);
        int option = chooser.showOpenDialog(this);

        if (JFileChooser.CANCEL_OPTION == option) 
            inFileTextField.setText("No ha seleccionado ningun archivo...");
        
        if (JFileChooser.APPROVE_OPTION == option) 
            inFileTextField.setText(chooser.getSelectedFile().getAbsolutePath());
        
    }//GEN-LAST:event_inFileButtonActionPerformed

    private void outFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outFileButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        int option = chooser.showSaveDialog(this);
        if (JFileChooser.APPROVE_OPTION == option) 
            outFileTextField.setText("No ha seleccionado ninguna ubicación...");
        
        if (JFileChooser.APPROVE_OPTION == option) 
            outFileTextField.setText(chooser.getSelectedFile().getAbsolutePath());
        
    }//GEN-LAST:event_outFileButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel answerLabel;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel backgroundLabel;
    private javax.swing.JTextField collumnsTextField;
    private javax.swing.JLabel derechosEmailLabel;
    private javax.swing.JLabel derechosLabel;
    private javax.swing.JButton doButton;
    private javax.swing.JLabel dragLabel;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel iconLabel;
    private javax.swing.JButton inFileButton;
    private javax.swing.JTextField inFileTextField;
    private javax.swing.JLabel instructionFour;
    private javax.swing.JLabel instructionOne;
    private javax.swing.JLabel instructionTwo;
    private javax.swing.JLabel labelTittle;
    private javax.swing.JButton minimizeButton;
    private javax.swing.JLabel numberPageLabel;
    private javax.swing.JTextField numberPageTextField;
    private javax.swing.JLabel omitLabelOne;
    private javax.swing.JLabel omitLabelThree;
    private javax.swing.JLabel omitLabelTwo;
    private javax.swing.JButton outFileButton;
    private javax.swing.JTextField outFileTextField;
    // End of variables declaration//GEN-END:variables
}
