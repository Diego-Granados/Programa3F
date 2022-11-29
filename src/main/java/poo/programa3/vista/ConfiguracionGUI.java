/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package poo.programa3.vista;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.HashMap;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import poo.programa3.modelo.Configuracion;
import poo.programa3.modelo.Reloj;
import poo.programa3.modelo.RangeException;

/**
 * Esta ventana implementa la funcionalidad de la configuración
 * @author Daniel Granados Retana y Diego Granados Retana
 */
public class ConfiguracionGUI extends javax.swing.JFrame {
    private String nivel;
    private String reloj;
    private boolean lado;
    private HashMap<String, javax.swing.JRadioButton> niveles = new HashMap<>();
    private HashMap<String, javax.swing.JRadioButton> relojes = new HashMap<>();
    private HashMap<Boolean, javax.swing.JRadioButton> lados = new HashMap<>();
    private HashMap<Integer, javax.swing.JRadioButton> tamaños = new HashMap<>();
    
    /**
     * Creates new form Configuracion
     */
    public ConfiguracionGUI() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nivelGroup = new javax.swing.ButtonGroup();
        relojGroup = new javax.swing.ButtonGroup();
        posicionGroup = new javax.swing.ButtonGroup();
        SizeButton = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        facilButton = new javax.swing.JRadioButton();
        intermedioButton = new javax.swing.JRadioButton();
        dificilButton = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        siButton = new javax.swing.JRadioButton();
        timerButton = new javax.swing.JRadioButton();
        noButton = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        izquierdaButton = new javax.swing.JRadioButton();
        derechaButton = new javax.swing.JRadioButton();
        relojFrame = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        horasField = new javax.swing.JTextField();
        minutosField = new javax.swing.JTextField();
        segundosField = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        multinivelButton = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        CincoButton = new javax.swing.JRadioButton();
        SeisButton = new javax.swing.JRadioButton();
        SieteButton = new javax.swing.JRadioButton();
        OchoButton = new javax.swing.JRadioButton();
        NueveButton = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel1.setText("Configuración");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(206, 19, 174, -1));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel2.setText("1. Nivel:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 73, -1));

            nivelGroup.add(facilButton);
            facilButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
            facilButton.setSelected(true);
            facilButton.setText("Fácil");
            facilButton.setToolTipText("");
            getContentPane().add(facilButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 98, -1));

            nivelGroup.add(intermedioButton);
            intermedioButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
            intermedioButton.setText("Intermedio");
            intermedioButton.setToolTipText("");
            getContentPane().add(intermedioButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 116, -1));

            nivelGroup.add(dificilButton);
            dificilButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
            dificilButton.setText("Difícil");
            dificilButton.setToolTipText("");
            getContentPane().add(dificilButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 116, -1));

            jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            jLabel3.setText("3. Posición de la ventana del panel de dígitos:");
            getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 393, -1));

            relojGroup.add(siButton);
            siButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
            siButton.setSelected(true);
            siButton.setText("Sí");
            siButton.setToolTipText("");
            getContentPane().add(siButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 98, -1));

            relojGroup.add(timerButton);
            timerButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
            timerButton.setText("Timer");
            timerButton.setToolTipText("");
            getContentPane().add(timerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 116, -1));

            relojGroup.add(noButton);
            noButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
            noButton.setText("No");
            noButton.setToolTipText("");
            getContentPane().add(noButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, 116, -1));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel4.setText("2. Reloj:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 73, -1));

            posicionGroup.add(izquierdaButton);
            izquierdaButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
            izquierdaButton.setText("Izquierda");
            izquierdaButton.setToolTipText("");
            getContentPane().add(izquierdaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 370, 98, -1));

            posicionGroup.add(derechaButton);
            derechaButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
            derechaButton.setSelected(true);
            derechaButton.setText("Derecha");
            derechaButton.setToolTipText("");
            getContentPane().add(derechaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 370, 116, -1));

        relojFrame.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        relojFrame.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Horas");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        relojFrame.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, 67, 24));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Segundos");
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        relojFrame.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 0, 69, 24));

            jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
            jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel6.setText("Minutos");
            jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            relojFrame.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 0, 65, 24));
            relojFrame.add(horasField, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 65, 71, -1));
            relojFrame.add(minutosField, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 65, 71, -1));
            relojFrame.add(segundosField, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 65, 71, -1));

        getContentPane().add(relojFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, -1, -1));

            saveButton.setBackground(new java.awt.Color(204, 255, 204));
            saveButton.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
            saveButton.setText("Guardar");
            getContentPane().add(saveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(418, 79, -1, -1));

            cancelButton.setBackground(new java.awt.Color(255, 102, 102));
            cancelButton.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
            cancelButton.setText("Cancelar");
            getContentPane().add(cancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(418, 142, -1, -1));

            nivelGroup.add(multinivelButton);
            multinivelButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
            multinivelButton.setText("Multinivel");
            multinivelButton.setToolTipText("");
            getContentPane().add(multinivelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 116, -1));

            jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            jLabel8.setText("4. Tamaño de la cuadrícula:");
            getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, -1, -1));

        SizeButton.add(CincoButton);
        CincoButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        CincoButton.setText("5");
        getContentPane().add(CincoButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 440, -1, -1));

        SizeButton.add(SeisButton);
        SeisButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        SeisButton.setText("6");
        getContentPane().add(SeisButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 470, -1, -1));

        SizeButton.add(SieteButton);
        SieteButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        SieteButton.setText("7");
        getContentPane().add(SieteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 500, -1, -1));

        SizeButton.add(OchoButton);
        OchoButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        OchoButton.setText("8");
        getContentPane().add(OchoButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 530, -1, -1));

        SizeButton.add(NueveButton);
        NueveButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        NueveButton.setText("9");
        getContentPane().add(NueveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 560, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

      public void addFacilActionListener(ActionListener a){
                             facilButton.addActionListener(a);
    }
    public void addIntermedioActionListener(ActionListener a){
                             intermedioButton.addActionListener(a);
    }
      public void addDificilActionListener(ActionListener a){
                             dificilButton.addActionListener(a);
    }
     public void addSiActionListener(ActionListener a){
                             siButton.addActionListener(a);
    }
     
    public void addTimerActionListener(ActionListener a){
                             timerButton.addActionListener(a);
    }
    public void addNoActionListener(ActionListener a){
                             noButton.addActionListener(a);
    }
    
    public void addIzquierdaActionListener(ActionListener a){
                             izquierdaButton.addActionListener(a);
    }
    public void addDerechaActionListener(ActionListener a){
                             derechaButton.addActionListener(a);
    }

    public void addSaveActionListener(ActionListener a){
                             saveButton.addActionListener(a);
    }
    public void addCancelActionListener(ActionListener a){
                             cancelButton.addActionListener(a);
    }
      public void addMultinivelActionListener(ActionListener a){
                             multinivelButton.addActionListener(a);
    }
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
            java.util.logging.Logger.getLogger(ConfiguracionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfiguracionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfiguracionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfiguracionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConfiguracionGUI().setVisible(true);
            }
        });
    }

    public javax.swing.ButtonGroup obtainSizeButton(){
          return SizeButton;
    }
    
      // Variables declaration - do not modify//GEN-BEGIN:variables
      private javax.swing.JRadioButton CincoButton;
      private javax.swing.JRadioButton NueveButton;
      private javax.swing.JRadioButton OchoButton;
      private javax.swing.JRadioButton SeisButton;
      private javax.swing.JRadioButton SieteButton;
      private javax.swing.ButtonGroup SizeButton;
      private javax.swing.JButton cancelButton;
      private javax.swing.JRadioButton derechaButton;
      private javax.swing.JRadioButton dificilButton;
      private javax.swing.JRadioButton facilButton;
      private javax.swing.JTextField horasField;
      private javax.swing.JRadioButton intermedioButton;
      private javax.swing.JRadioButton izquierdaButton;
      private javax.swing.JLabel jLabel1;
      private javax.swing.JLabel jLabel2;
      private javax.swing.JLabel jLabel3;
      private javax.swing.JLabel jLabel4;
      private javax.swing.JLabel jLabel5;
      private javax.swing.JLabel jLabel6;
      private javax.swing.JLabel jLabel7;
      private javax.swing.JLabel jLabel8;
      private javax.swing.JTextField minutosField;
      private javax.swing.JRadioButton multinivelButton;
      private javax.swing.ButtonGroup nivelGroup;
      private javax.swing.JRadioButton noButton;
      private javax.swing.ButtonGroup posicionGroup;
      private javax.swing.JPanel relojFrame;
      private javax.swing.ButtonGroup relojGroup;
      private javax.swing.JButton saveButton;
      private javax.swing.JTextField segundosField;
      private javax.swing.JRadioButton siButton;
      private javax.swing.JRadioButton timerButton;
      // End of variables declaration//GEN-END:variables

      public String getNivel() {
            return nivel;
      }

      public String getReloj() {
            return reloj;
      }

      public boolean isLado() {
            return lado;
      }

      public HashMap<String, javax.swing.JRadioButton> getNiveles() {
            return niveles;
      }

      public HashMap<String, javax.swing.JRadioButton> getRelojes() {
            return relojes;
      }

      public HashMap<Boolean, javax.swing.JRadioButton> getLados() {
            return lados;
      }

      public HashMap<Integer, javax.swing.JRadioButton> getTamaños() {
            return tamaños;
      }

      public javax.swing.JRadioButton getCincoButton() {
            return CincoButton;
      }

      public javax.swing.JRadioButton getNueveButton() {
            return NueveButton;
      }

      public javax.swing.JRadioButton getOchoButton() {
            return OchoButton;
      }

      public javax.swing.JRadioButton getSeisButton() {
            return SeisButton;
      }

      public javax.swing.JRadioButton getSieteButton() {
            return SieteButton;
      }

      public javax.swing.JButton getCancelButton() {
            return cancelButton;
      }

      public javax.swing.JRadioButton getDerechaButton() {
            return derechaButton;
      }

      public javax.swing.JRadioButton getDificilButton() {
            return dificilButton;
      }

      public javax.swing.JRadioButton getFacilButton() {
            return facilButton;
      }

      public javax.swing.JTextField getHorasField() {
            return horasField;
      }

      public javax.swing.JRadioButton getIntermedioButton() {
            return intermedioButton;
      }

      public javax.swing.JRadioButton getIzquierdaButton() {
            return izquierdaButton;
      }

      public javax.swing.JLabel getjLabel1() {
            return jLabel1;
      }

      public javax.swing.JLabel getjLabel2() {
            return jLabel2;
      }

      public javax.swing.JLabel getjLabel3() {
            return jLabel3;
      }

      public javax.swing.JLabel getjLabel4() {
            return jLabel4;
      }

      public javax.swing.JLabel getjLabel5() {
            return jLabel5;
      }

      public javax.swing.JLabel getjLabel6() {
            return jLabel6;
      }

      public javax.swing.JLabel getjLabel7() {
            return jLabel7;
      }

      public javax.swing.JLabel getjLabel8() {
            return jLabel8;
      }

      public javax.swing.JTextField getMinutosField() {
            return minutosField;
      }

      public javax.swing.JRadioButton getMultinivelButton() {
            return multinivelButton;
      }

      public javax.swing.ButtonGroup getNivelGroup() {
            return nivelGroup;
      }

      public javax.swing.JRadioButton getNoButton() {
            return noButton;
      }

      public javax.swing.ButtonGroup getPosicionGroup() {
            return posicionGroup;
      }

      public javax.swing.JPanel getRelojFrame() {
            return relojFrame;
      }

      public javax.swing.ButtonGroup getRelojGroup() {
            return relojGroup;
      }

      public javax.swing.JButton getSaveButton() {
            return saveButton;
      }

      public javax.swing.JTextField getSegundosField() {
            return segundosField;
      }

      public javax.swing.JRadioButton getSiButton() {
            return siButton;
      }

      public javax.swing.JRadioButton getTimerButton() {
            return timerButton;
      }
}
