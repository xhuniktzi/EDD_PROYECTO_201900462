/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Edd.Proyecto.Fase2;

import Estructuras.btree.BTreeClients;
import Modelos.Cliente;

/**
 *
 * @author Xhunik_Local
 */
public class Fase2 {

    public static int grado;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BTreeClients t = new BTreeClients(3);
        Cliente c1 = new Cliente();
        c1.dpi = "12345678";
        t.insert(c1);
        
        Cliente c2 = new Cliente();
        c2.dpi = "546694856";
        t.insert(c2);
        
        Cliente c3 = new Cliente();
        c3.dpi = "84612151";
        t.insert(c3);
        
        Cliente c4 = new Cliente();
        c4.dpi = "811616185";
        t.insert(c4);
        
        Cliente c5 = new Cliente();
        c5.dpi = "9166112";
        t.insert(c5);
        
        Cliente c6 = new Cliente();
        c6.dpi = "812116515";
        t.insert(c6);
        
        Cliente c7 = new Cliente();
        c7.dpi = "2299131";
        t.insert(c7);
        
        Cliente c8 = new Cliente();
        c8.dpi = "1642915151";
        t.insert(c8);
        
        Cliente c9 = new Cliente();
        c9.dpi = "963119123";
        t.insert(c9);
        
        t.traverse();
        System.out.println();
       t.graph();
    }
    
}
