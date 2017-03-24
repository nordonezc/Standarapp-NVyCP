/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package standarapp.gui;

import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import standarapp.algorithm.CodeAssign;

/**
 *
 * @author Niki
 */
public class ParticularSearch extends javax.swing.JFrame {

    private int xMouse;
    int yMouse;
    private CodeAssign ca;

    /**
     * Creates new form Menu
     */
    public ParticularSearch(int x, int y, String nameExcel) throws IOException {
        initComponents();
        this.setLocation(x, y);
        this.setIconImage(new ImageIcon(getClass().getResource("/images/SPicon.png")).getImage());
        this.setTitle("StandarApp");
        ca = new CodeAssign(nameExcel);
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
        iconLabel = new javax.swing.JLabel();
        labelTittle = new javax.swing.JLabel();
        derechosLabel = new javax.swing.JLabel();
        derechosEmailLabel = new javax.swing.JLabel();
        municipioLabel = new javax.swing.JLabel();
        municipioTextField = new javax.swing.JTextField();
        departamentoLabel = new javax.swing.JLabel();
        departamentoTextField = new javax.swing.JTextField();
        localidadLabel = new javax.swing.JLabel();
        localidadTextField = new javax.swing.JTextField();
        answerPanel = new javax.swing.JScrollPane();
        answerTextArea = new javax.swing.JTextArea();
        doButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
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
        getContentPane().add(minimizeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, 20, 20));

        iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SPicon.png"))); // NOI18N
        getContentPane().add(iconLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, 40));

        labelTittle.setBackground(new java.awt.Color(79, 152, 43));
        labelTittle.setFont(new java.awt.Font("Castellar", 1, 24)); // NOI18N
        labelTittle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTittle.setText("StandarApp 3.0");
        getContentPane().add(labelTittle, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 280, 40));

        derechosLabel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        derechosLabel.setForeground(new java.awt.Color(153, 153, 153));
        derechosLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        derechosLabel.setText("Designed by Nicolas Ordoñez Chala, 2017");
        getContentPane().add(derechosLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 440, 260, 20));

        derechosEmailLabel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        derechosEmailLabel.setForeground(new java.awt.Color(153, 153, 153));
        derechosEmailLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        derechosEmailLabel.setText("info: nordonezc@unal.edu.co");
        getContentPane().add(derechosEmailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, 260, 20));

        municipioLabel.setFont(new java.awt.Font("Lao UI", 0, 14)); // NOI18N
        municipioLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        municipioLabel.setText("Municipio:");
        getContentPane().add(municipioLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 70, 30));

        municipioTextField.setFont(new java.awt.Font("Lao UI", 0, 14)); // NOI18N
        municipioTextField.setText("Nombre o código");
        municipioTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                municipioTextFieldActionPerformed(evt);
            }
        });
        getContentPane().add(municipioTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 120, 30));

        departamentoLabel.setFont(new java.awt.Font("Lao UI", 0, 14)); // NOI18N
        departamentoLabel.setText("D/mento:");
        getContentPane().add(departamentoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 90, 30));

        departamentoTextField.setFont(new java.awt.Font("Lao UI", 0, 14)); // NOI18N
        departamentoTextField.setText("Nombre o código");
        departamentoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departamentoTextFieldActionPerformed(evt);
            }
        });
        getContentPane().add(departamentoTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 120, 30));

        localidadLabel.setFont(new java.awt.Font("Lao UI", 0, 14)); // NOI18N
        localidadLabel.setText("Localidad:");
        getContentPane().add(localidadLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 70, 30));

        localidadTextField.setFont(new java.awt.Font("Lao UI", 0, 14)); // NOI18N
        localidadTextField.setText("Nombre o código");
        localidadTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                localidadTextFieldActionPerformed(evt);
            }
        });
        getContentPane().add(localidadTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 120, 30));

        answerPanel.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        answerPanel.setFocusable(false);

        answerTextArea.setColumns(20);
        answerTextArea.setFont(new java.awt.Font("Lao UI", 0, 10)); // NOI18N
        answerTextArea.setLineWrap(true);
        answerTextArea.setRows(5);
        answerTextArea.setText("Aquí se mostrará el resultado de su búsqueda personalizada donde se mostrara:\n\t-Centroides\n\t-Posibles nombres oficiales\n\nLos criterios para realizar la búsqueda son:\n\t-Departamento: Puede ingresar un nombre o un código si lo conoce\n\t-Municipio: Puede ingresar un nombre o un código si lo conoce\n\t-Localidad: Puede ingresar un nombre o un código si lo conoce\n\nEn caso de tener algún problema al realizar la búsqueda, presione el botón reiniciar busqueda,\nsi persiste el problema comunicarse con el encargado o envie correo al desarrollador");
        answerTextArea.setWrapStyleWord(true);
        answerTextArea.setDragEnabled(true);
        answerPanel.setViewportView(answerTextArea);

        getContentPane().add(answerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 570, 300));

        doButton.setBackground(new java.awt.Color(0, 153, 153));
        doButton.setFont(new java.awt.Font("Lao UI", 0, 14)); // NOI18N
        doButton.setForeground(new java.awt.Color(255, 255, 255));
        doButton.setText("Buscar");
        doButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doButtonActionPerformed(evt);
            }
        });
        getContentPane().add(doButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 570, -1));

        resetButton.setBackground(new java.awt.Color(255, 255, 255));
        resetButton.setFont(new java.awt.Font("Lao UI", 0, 14)); // NOI18N
        resetButton.setText("Reiniciar búsqueda");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });
        getContentPane().add(resetButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, 160, -1));

        backButton.setBackground(new java.awt.Color(0, 153, 153));
        backButton.setFont(new java.awt.Font("Lao UI", 0, 14)); // NOI18N
        backButton.setForeground(new java.awt.Color(255, 255, 255));
        backButton.setText("Regresar");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        getContentPane().add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 110, -1));

        exitButton.setBackground(new java.awt.Color(204, 51, 0));
        exitButton.setFont(new java.awt.Font("Lao UI", 0, 14)); // NOI18N
        exitButton.setText("Salir");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(exitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 430, 70, -1));

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
        getContentPane().add(dragLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -6, 640, 480));

        backgroundLabel.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/greenPolygons.jpg"))); // NOI18N
        getContentPane().add(backgroundLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void doButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doButtonActionPerformed
        // TODO add your handling code here:
        boolean dptoFilled = false;
        boolean mncpFilled = false;
        boolean localFilled = false;

        String dpto = departamentoTextField.getText();
        String mncp = municipioTextField.getText();
        String local = localidadTextField.getText();

        dpto = dpto.toUpperCase();
        mncp = mncp.toUpperCase();
        local = local.toUpperCase();

        if (!dpto.contains("Ó")) {
            dptoFilled = true;
        }

        if (!mncp.contains("Ó")) {
            mncpFilled = true;
        }

        if (!local.contains("Ó")) {
            localFilled = true;
        }

        try {
            if (dptoFilled == true) {
                if (mncpFilled == true) {
                    if (localFilled == true) {
                        try {
                            double localCode = Double.valueOf(local);
                            System.out.println("codigo");
                            answerTextArea.setText(ca.findByLocalidadesCode(localCode));
                        } catch (Exception e) {
                            try {
                                int dptoCode = Integer.valueOf(dpto);
                                int mncpCode = Integer.valueOf(mncp);
                                System.out.println("codigo");
                                answerTextArea.setText(ca.findByDptoMncpCodeAndLocalidadString(dptoCode, mncpCode, local));
                            } catch (Exception ex) {
                                try {
                                    int mncpCode = Integer.valueOf(mncp);
                                    System.out.println("codigo");
                                    answerTextArea.setText(ca.findByMncpCodeAndLocalidadString(mncpCode, local));
                                } catch (Exception exc) {
                                    try {
                                        int dptoCode = Integer.valueOf(dpto);
                                        System.out.println("codigo");
                                        answerTextArea.setText(ca.findByDptoCodeAndLocalidadString(dptoCode, local));
                                    } catch (Exception exce) {
                                        System.err.println("texto");
                                        answerTextArea.setText(ca.findByAllString(dpto, mncp, local));
                                    }
                                }
                            }
                        }
                    } else {
                        try {
                            int mncpCode = Integer.valueOf(mncp);
                            System.out.println("codigo");
                            answerTextArea.setText(ca.findByMunicipioCode(mncpCode));
                        } catch (Exception e) {
                            try {
                            int dptoCode = Integer.valueOf(dpto);
                            System.out.println("codigo");
                            answerTextArea.setText(ca.finbByDepartamentoCodeAndMunicipioString(dptoCode, mncp));
                        } catch (Exception ex) {
                            System.err.println("texto");
                            answerTextArea.setText(ca.findByMunicipioAndDepartamentoString(mncp, dpto));
                        }
                        }
                    }
                } else if (localFilled == true) {
                    try {
                        double localCode = Double.valueOf(local);
                        System.out.println("codigo");
                        answerTextArea.setText(ca.findByLocalidadesCode(localCode));
                    } catch (Exception e) {
                        try {
                            int dptoCode = Integer.valueOf(dpto);
                            System.out.println("codigo");
                            answerTextArea.setText(ca.findByDptoCodeAndLocalidadString(dptoCode, local));
                        } catch (Exception excpt) {
                            System.err.println("texto");
                            answerTextArea.setText(ca.finbByLocalidadAndDepartamento(local, dpto));
                        }
                    }
                } else {
                    try {
                        int dptoCode = Integer.valueOf(dpto);
                        System.out.println("codigo");
                        answerTextArea.setText(ca.findByDepartamentoCode(dptoCode));
                    } catch (Exception e) {
                        System.err.println("texto");
                        answerTextArea.setText(ca.findByDepartamentoString(dpto));
                    }

                }
                
            } else if (mncpFilled == true) {
                if (localFilled == true) {
                    try {
                        double localCode = Double.valueOf(local);
                        System.out.println("codigo");
                        answerTextArea.setText(ca.findByLocalidadesCode(localCode));
                    } catch (Exception e) {
                        try {
                            System.err.println("texto");
                            int mncpCode = Integer.valueOf(mncp);
                            answerTextArea.setText(ca.findByMncpCodeAndLocalidadString(mncpCode, local));
                        } catch (Exception ex) {
                            answerTextArea.setText(ca.findByLocalidadAndMunicipioString(local, mncp));
                        }
                    }
                } else {
                    try {
                        int mncpCode = Integer.valueOf(mncp);
                        System.out.println("codigo");
                        answerTextArea.setText(ca.findByMunicipioCode(mncpCode));
                    } catch (Exception e) {

                        System.err.println("texto");
                        answerTextArea.setText(ca.findByMunicipioString(mncp));
                    }
                }
            } else if (localFilled == true) {
                try {
                    double localCode = Double.valueOf(local);
                    System.out.println("codigo: " + localCode);
                    answerTextArea.setText(ca.findByLocalidadesCode(localCode));
                } catch (NumberFormatException e) {
                    System.err.println("texto: " + local);
                    answerTextArea.setText(ca.findByLocalidadString(local));
                }
            } else {
                answerTextArea.setText("*VERIFIQUE QUE SI COMPLETO ALGUNO DE LOS CAMPOS CORRECTAMENTE" + "\n"
                        + "DE SER NECESARIO REINICIE LA BÚSQUEDA.");
            }
        } catch (Exception e) {
            answerTextArea.setText("*VERIFIQUE QUE SI COMPLETO ALGUNO DE LOS CAMPOS CORRECTAMENTE" + "\n"
                    + "DE SER NECESARIO REINICIE LA BÚSQUEDA.");
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

    private void localidadTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_localidadTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_localidadTextFieldActionPerformed

    private void departamentoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departamentoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_departamentoTextFieldActionPerformed

    private void minimizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeButtonActionPerformed
        // TODO add your handling code here:
        setState(this.ICONIFIED);
    }//GEN-LAST:event_minimizeButtonActionPerformed

    private void municipioTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_municipioTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_municipioTextFieldActionPerformed

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

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        // TODO add your handling code here:
        departamentoTextField.setText("Nombre o código");
        municipioTextField.setText("Nombre o código");
        localidadTextField.setText("Nombre o código");
        answerTextArea.setText(
                "Aquí se mostrará el resultado de su búsqueda personalizada donde se mostrara:\n"
                + "	-Centroides\n"
                + "	-Posibles nombres oficiales\n"
                + "\n"
                + "Los criterios para realizar la búsqueda son:\n"
                + "	-Departamento: Puede ingresar un nombre o un codigo si lo conoce\n"
                + "	-Municipio: Puede ingresar un nombre o un codigo si lo conoce\n"
                + "	-Localidad: Puede ingresar un nombre o un codigo si lo conoce\n"
                + "\n"
                + "En caso de tener algún problema al realizar la búsqueda, presione el botón reiniciar busqueda,\n"
                + "si persiste el problema comunicarse con el encargado o envie correo al desarrollador");
    }//GEN-LAST:event_resetButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane answerPanel;
    private javax.swing.JTextArea answerTextArea;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel backgroundLabel;
    private javax.swing.JLabel departamentoLabel;
    private javax.swing.JTextField departamentoTextField;
    private javax.swing.JLabel derechosEmailLabel;
    private javax.swing.JLabel derechosLabel;
    private javax.swing.JButton doButton;
    private javax.swing.JLabel dragLabel;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel iconLabel;
    private javax.swing.JLabel labelTittle;
    private javax.swing.JLabel localidadLabel;
    private javax.swing.JTextField localidadTextField;
    private javax.swing.JButton minimizeButton;
    private javax.swing.JLabel municipioLabel;
    private javax.swing.JTextField municipioTextField;
    private javax.swing.JButton resetButton;
    // End of variables declaration//GEN-END:variables
}
