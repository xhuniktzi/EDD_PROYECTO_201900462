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

        BTreeClients t = new BTreeClients(2);
        t.insert(new Cliente("12345678", "Persona 12", "clave"));
        t.insert(new Cliente("546694856", "Persona 13", "clave"));
        t.insert(new Cliente("84612151", "Persona 14", "clave"));
        t.insert(new Cliente("811616185", "Persona 15", "clave rara"));
        t.insert(new Cliente("9166112", "Persona 15", "clave"));
        t.insert(new Cliente("812116515", "Persona 15", "clave"));
        t.insert(new Cliente("2299131", "Persona 15", "clave"));
        t.insert(new Cliente("1642915151", "Persona 15", "clave"));
        t.insert(new Cliente("963119123", "Persona 15", "clave inicio"));
        t.insert(new Cliente("965332221566", "Persona 15", "clave inicio"));
        t.insert(new Cliente("02256889", "Persona 15", "clave inicio"));
        
        System.out.println();
       t.graph();
    }
    
}
