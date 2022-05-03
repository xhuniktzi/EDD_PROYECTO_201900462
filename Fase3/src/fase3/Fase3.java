/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fase3;

import DataStructures.HashTable;
import Models.Mensajero;
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
        HashTable hs = new HashTable();
        
//        Mensajero m1 = new Mensajero("213213132");
//        hs.insert(m1);
//        Mensajero m2 = new Mensajero("213213132");
//        hs.insert(m2);
//        Mensajero m3 = new Mensajero("66933832437229");
//        hs.insert(m3);
//        Mensajero m4 = new Mensajero("65927339454337");
//        hs.insert(m4);
//        Mensajero m5 = new Mensajero("55932733055687");
//        hs.insert(m5);
//        Mensajero m6 = new Mensajero("4493372233733");
//        hs.insert(m6);
//        Mensajero m7 = new Mensajero("5466033833");
//        hs.insert(m7);
//        Mensajero m8 = new Mensajero("66534354");
//        hs.insert(m8);


        JFileChooser jfc = new JFileChooser();
        int result = jfc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION){
            try {
                File f = jfc.getSelectedFile();
                hs.loadFromFile(f);
            } catch (IOException | ParseException ex) {
                Logger.getLogger(Fase3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        hs.printArray();
        
    }
    
}
