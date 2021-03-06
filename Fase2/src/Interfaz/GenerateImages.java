/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Estructuras.binario.BinaryTree;
import Estructuras.linkedlist.LinkedList;
import Estructuras.linkedlist.NodoSimple;
import Estructuras.matriz.Matriz;
import Fase2.Globals;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import procedures.loaders;
import procedures.traverseTrees;

/**
 *
 * @author Xhunik_Local
 */
public class GenerateImages extends javax.swing.JFrame {

    /**
     * Creates new form GenerateImages
     */
    public GenerateImages() {
        initComponents();
        DefaultTableModel dataModel = (DefaultTableModel) tableImages.getModel();
        
        LinkedList<BinaryTree> avl = Globals.images.inorden();
        NodoSimple<BinaryTree> aux = avl.head;
        
        while (aux != null){  
            dataModel.addRow(new Object[] {aux.dato.id});
            aux = aux.siguiente;
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboRecorrido = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableImages = new javax.swing.JTable();
        btnGenerateImage = new javax.swing.JButton();
        traverse = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        comboRecorrido.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "preorden", "inorden", "posorden" }));

        jLabel1.setText("Tipo de recorrido");

        tableImages.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableImages);
        if (tableImages.getColumnModel().getColumnCount() > 0) {
            tableImages.getColumnModel().getColumn(0).setResizable(false);
        }

        btnGenerateImage.setText("Generar Imagen");
        btnGenerateImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(traverse, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(comboRecorrido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGenerateImage))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboRecorrido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerateImage))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(traverse, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerateImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateImageActionPerformed
        // TODO add your handling code here:
        int typeSelected = comboRecorrido.getSelectedIndex();
        int row = tableImages.getSelectedRow();
        int id = (int) tableImages.getValueAt(row, 0);
        BinaryTree img = Globals.images.search(id);
        StringBuilder str = new StringBuilder();
        Matriz m = null;
        switch(typeSelected){
            case 0:
                LinkedList<Matriz> lm1 = img.preorden();
                
                NodoSimple<Matriz> aux1 = lm1.head;
                while (aux1 != null){
                    str.append(aux1.dato.id).append(";");
                    aux1 = aux1.siguiente;
                }
                traverse.setText(str.toString());
                
                m = traverseTrees.generateImagePreorden(id);
                break;
            case 1:
                LinkedList<Matriz> lm2 = img.inorden();
                
                NodoSimple<Matriz> aux2 = lm2.head;
                while (aux2 != null){
                    str.append(aux2.dato.id).append(";");
                    aux2 = aux2.siguiente;
                }
                traverse.setText(str.toString());
                
                m = traverseTrees.generateImageInorden(id);
                break;
            case 2:
                LinkedList<Matriz> lm3 = img.posorden();
                
                NodoSimple<Matriz> aux3 = lm3.head;
                while (aux3 != null){
                    str.append(aux3.dato.id).append(";");
                    aux3 = aux3.siguiente;
                }
                traverse.setText(str.toString());
                
                m = traverseTrees.generateImagePosorden(id);
                break;
        }
        if (m != null){
            try {
                loaders.generarDot("ImageGenerated", m.graph());
                Thread.sleep(3500);
                ImageIcon imgicon = new ImageIcon("ImageGenerated.png");
                JLabel image = new JLabel();
                image.setBounds(0, 0, imgicon.getIconWidth(), imgicon.getIconHeight());
                image.setIcon(imgicon);
                jScrollPane2.setViewportView(image);
            } catch (InterruptedException ex) {
                Logger.getLogger(GenerateImages.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnGenerateImageActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(GenerateImages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GenerateImages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GenerateImages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GenerateImages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new GenerateImages().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerateImage;
    private javax.swing.JComboBox<String> comboRecorrido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableImages;
    private javax.swing.JLabel traverse;
    // End of variables declaration//GEN-END:variables
}
