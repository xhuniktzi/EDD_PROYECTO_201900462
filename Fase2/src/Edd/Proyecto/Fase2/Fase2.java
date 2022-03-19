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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        // public API
        // insert, remove, findClienteByDpi, graph
        
        BTreeClients t = new BTreeClients(2);
        t.insert(new Cliente("0", "Persona 12", "clave"));
        t.insert(new Cliente("1", "Persona 13", "clave"));
        t.insert(new Cliente("2", "Persona 14", "clave"));
        t.insert(new Cliente("3", "Persona 15", "clave rara"));
        t.insert(new Cliente("4", "Persona 15", "clave"));
        t.insert(new Cliente("5", "Persona 15", "clave"));
        t.insert(new Cliente("6", "Persona 15", "clave"));
        t.insert(new Cliente("7", "Persona 15", "clave"));
        t.insert(new Cliente("8", "Persona 15", "clave inicio"));
        t.insert(new Cliente("9", "Persona 15", "clave inicio"));
        t.insert(new Cliente("10", "Persona 15", "clave inicio"));
        
        t.remove("0");
        t.remove("1");
        t.remove("2");
        t.graph();
    }
    
}
