/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Edd.Proyecto.Fase2;

import Estructuras.matriz.Matriz;

/**
 *
 * @author Xhunik_Local
 */
public class Fase2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Matriz m = new Matriz("Matriz1");
        m.insert(1,1,"ffff");
        m.insert(2,1,"ffff");
        m.insert(3,2,"ffff");
        m.insert(1,4,"ffff");
        m.insert(2,3,"ffff");
        m.insert(2,3,"ffff");
        m.insert(2,3,"ffff");
        m.insert(2,3,"ddd");
        m.insert(2,3,"dddddd");
        
        m.insert(1,3,"ffff");
        m.insert(5,5,"ffff");
//        m.printCols();
//        m.printRows();
        System.out.println(m.graph()); 
//        System.out.println(m.graph());
    }
    
}
