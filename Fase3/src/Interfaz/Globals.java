/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

import DataStructures.ListaAdyacencia;
import DataStructures.HashTable;
import DataStructures.ListClients;
import java.io.FileOutputStream;

/**
 *
 * @author Xhunik
 */
public class Globals {
    public static ListClients lsClients = new ListClients();
    public static HashTable tableMensajeros = new HashTable();
    public static ListaAdyacencia lsAdyacencia = new ListaAdyacencia();
    
     public static void generarDot(String name, String img){
        try (FileOutputStream out = new FileOutputStream(name + ".dot")) {
            out.write(img.getBytes());
            out.close();
            
            Runtime.getRuntime().exec("dot -Tpng " + name + ".dot -o " + name + ".png");
        } catch (Exception ex){}
    }
}
