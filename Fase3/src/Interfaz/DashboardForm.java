/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Xhunik
 */
public class DashboardForm extends javax.swing.JFrame {

    /**
     * Creates new form dashboardForm
     */
    public DashboardForm() {
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

        cmdCargarMensajeros = new javax.swing.JButton();
        cmdGraficarMensajero = new javax.swing.JButton();
        cmdGraficarClientes = new javax.swing.JButton();
        cmdEditClients = new javax.swing.JButton();
        cmdCargarLugares = new javax.swing.JButton();
        cmdGraficarLugares = new javax.swing.JButton();
        cmdCargarRutas = new javax.swing.JButton();
        cmdGraficarRutas = new javax.swing.JButton();
        cmdGrafo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cmdCargarMensajeros.setText("Cargar Mensajeros");
        cmdCargarMensajeros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCargarMensajerosActionPerformed(evt);
            }
        });

        cmdGraficarMensajero.setText("Graficar Mensajeros");
        cmdGraficarMensajero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGraficarMensajeroActionPerformed(evt);
            }
        });

        cmdGraficarClientes.setText("Graficar Clientes");
        cmdGraficarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGraficarClientesActionPerformed(evt);
            }
        });

        cmdEditClients.setText("Editar Clientes");
        cmdEditClients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditClientsActionPerformed(evt);
            }
        });

        cmdCargarLugares.setText("Cargar Lugares");
        cmdCargarLugares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCargarLugaresActionPerformed(evt);
            }
        });

        cmdGraficarLugares.setText("Graficar Lugares");
        cmdGraficarLugares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGraficarLugaresActionPerformed(evt);
            }
        });

        cmdCargarRutas.setText("Cargar Rutas");
        cmdCargarRutas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCargarRutasActionPerformed(evt);
            }
        });

        cmdGraficarRutas.setText("Graficar Rutas");
        cmdGraficarRutas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGraficarRutasActionPerformed(evt);
            }
        });

        cmdGrafo.setText("Mostrar Grafo");
        cmdGrafo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGrafoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cmdCargarRutas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdCargarLugares, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdCargarMensajeros, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdEditClients, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(126, 126, 126)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdGraficarClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdGraficarMensajero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdGraficarLugares, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdGraficarRutas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdGrafo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdCargarMensajeros)
                    .addComponent(cmdGraficarMensajero))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdEditClients)
                    .addComponent(cmdGraficarClientes))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdCargarLugares)
                    .addComponent(cmdGraficarLugares))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdCargarRutas)
                    .addComponent(cmdGraficarRutas))
                .addGap(18, 18, 18)
                .addComponent(cmdGrafo)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdCargarMensajerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCargarMensajerosActionPerformed
        // TODO add your handling code here:
        JFileChooser jfc = new JFileChooser();
        int result = jfc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            try {
                File f = jfc.getSelectedFile();
                Globals.tableMensajeros.loadFromFile(f);
            } catch (IOException | ParseException ex) {
//                Logger.getLogger(Fase3.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
    }//GEN-LAST:event_cmdCargarMensajerosActionPerformed

    private void cmdGraficarMensajeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGraficarMensajeroActionPerformed
        try {
            // TODO add your handling code here:
            String value = Globals.tableMensajeros.graph();
            System.out.println(value);
            Globals.generarDot("mensajeros", value);
            Thread.sleep(5000);
            showImage frm = new showImage(new ImageIcon("mensajeros.png"));
            frm.setVisible(true);
            frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } catch (InterruptedException ex) {
            Logger.getLogger(DashboardForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_cmdGraficarMensajeroActionPerformed

    private void cmdGraficarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGraficarClientesActionPerformed
        try {
            // TODO add your handling code here:
            String value = Globals.lsClients.graph();
            System.out.println(value);
            Globals.generarDot("clientes", value);
            Thread.sleep(5000);
            showImage frm = new showImage(new ImageIcon("clientes.png"));
            frm.setVisible(true);
            frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } catch (InterruptedException ex) {
            Logger.getLogger(DashboardForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmdGraficarClientesActionPerformed

    private void cmdEditClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditClientsActionPerformed
        // TODO add your handling code here:
        FormEditClients frm = new FormEditClients();
        frm.setVisible(true);
        frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_cmdEditClientsActionPerformed

    private void cmdCargarLugaresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCargarLugaresActionPerformed
        // TODO add your handling code here:
        JFileChooser jfc = new JFileChooser();
        int result = jfc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            try {
                File f = jfc.getSelectedFile();
                Globals.lsAdyacencia.loadFromFileLugares(f);
            } catch (IOException | ParseException ex) {
//                Logger.getLogger(Fase3.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
    }//GEN-LAST:event_cmdCargarLugaresActionPerformed

    private void cmdGraficarLugaresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGraficarLugaresActionPerformed
        try {
            // TODO add your handling code here:
            String value = Globals.lsAdyacencia.graphLugares();
            System.out.println(value);
            Globals.generarDot("lugares", value);
            Thread.sleep(5000);
            showImage frm = new showImage(new ImageIcon("lugares.png"));
            frm.setVisible(true);
            frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } catch (InterruptedException ex) {
            Logger.getLogger(DashboardForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmdGraficarLugaresActionPerformed

    private void cmdCargarRutasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCargarRutasActionPerformed
        // TODO add your handling code here:
        JFileChooser jfc = new JFileChooser();
        int result = jfc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            try {
                File f = jfc.getSelectedFile();
                Globals.lsAdyacencia.loadFromFileRutas(f);
            } catch (IOException | ParseException ex) {
//                Logger.getLogger(Fase3.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
    }//GEN-LAST:event_cmdCargarRutasActionPerformed

    private void cmdGraficarRutasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGraficarRutasActionPerformed
        try {
            // TODO add your handling code here:
            String value = Globals.lsAdyacencia.graphRutas();
            System.out.println(value);
            Globals.generarDot("rutas", value);
            Thread.sleep(5000);
            showImage frm = new showImage(new ImageIcon("rutas.png"));
            frm.setVisible(true);
            frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } catch (InterruptedException ex) {
            Logger.getLogger(DashboardForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmdGraficarRutasActionPerformed

    private void cmdGrafoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGrafoActionPerformed
        // TODO add your handling code here:
                try {
            // TODO add your handling code here:
            String value = Globals.lsAdyacencia.graphSchema();
            System.out.println(value);
            Globals.generarDot("grafo", value);
            Thread.sleep(5000);
            showImage frm = new showImage(new ImageIcon("grafo.png"));
            frm.setVisible(true);
            frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } catch (InterruptedException ex) {
            Logger.getLogger(DashboardForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmdGrafoActionPerformed

    
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
            java.util.logging.Logger.getLogger(DashboardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new DashboardForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdCargarLugares;
    private javax.swing.JButton cmdCargarMensajeros;
    private javax.swing.JButton cmdCargarRutas;
    private javax.swing.JButton cmdEditClients;
    private javax.swing.JButton cmdGraficarClientes;
    private javax.swing.JButton cmdGraficarLugares;
    private javax.swing.JButton cmdGraficarMensajero;
    private javax.swing.JButton cmdGraficarRutas;
    private javax.swing.JButton cmdGrafo;
    // End of variables declaration//GEN-END:variables
}
