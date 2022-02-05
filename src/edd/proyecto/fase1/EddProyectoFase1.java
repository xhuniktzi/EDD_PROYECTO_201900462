/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto.fase1;

import Estructuras.DoubleCircularList;

/**
 *
 * @author Xhunik_Local
 */
public class EddProyectoFase1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DoubleCircularList<String> lista = new DoubleCircularList();
        lista.addToEnd("A");
        lista.addToEnd("B");
        lista.addToEnd("C");
        lista.addToEnd("D");
        lista.addToEnd("E");
        lista.recorrer();
    }
    
}
