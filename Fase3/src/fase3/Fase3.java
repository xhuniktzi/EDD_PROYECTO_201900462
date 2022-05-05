/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fase3;

import DataStructures.HashTable;
import DataStructures.ListClients;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Xhunik
 */
public class Fase3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        HashTable hs = new HashTable();

        JFileChooser jfc = new JFileChooser();
        int result = jfc.showOpenDialog(null);
//        if (result == JFileChooser.APPROVE_OPTION){
//            try {
//                File f = jfc.getSelectedFile();
//                hs.loadFromFile(f);
//            } catch (IOException | ParseException ex) {
//                Logger.getLogger(Fase3.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//        hs.printArray();
        
        ListClients ls = new ListClients();
//        result = jfc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION){
            try {
                File f = jfc.getSelectedFile();
                ls.loadFromFile(f);
            } catch (IOException | ParseException ex) {
                Logger.getLogger(Fase3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println(ls.checkIfClientOk("Winnie", "Lorem"));
    }
    
}
