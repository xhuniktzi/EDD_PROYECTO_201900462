/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto.fase1;

import Estructuras.DoubleLinkedList;

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
        DoubleLinkedList<String> lista = new DoubleLinkedList();
        lista.addToEnd("A");
        lista.addToEnd("B");
        lista.addToEnd("C");
        lista.addToEnd("D");
        lista.addToEnd("E");
        System.out.println("Tirada 1");
        lista.recorrer();
        lista.deleteEnd();
        lista.deleteEnd();
        System.out.println("Tirada 2");
        lista.recorrer();
        lista.deleteEnd();
        lista.deleteEnd();
        System.out.println("Tirada 3");
        lista.recorrer();
        lista.deleteEnd();
        System.out.println("Tirada 4");
        lista.recorrer();
        //lista.deleteEnd();
        //lista.recorrer();
    }
    
}
