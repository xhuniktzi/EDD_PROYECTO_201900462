/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.album;

import Estructuras.binario.BinaryTree;
import Estructuras.linkedlist.LinkedList;
import Estructuras.linkedlist.NodoSimple;

/**
 *
 * @author Xhunik_Local
 */
public class ListaAlbumes extends LinkedList<Album> {
    public Album search(String nombre_album){
        NodoSimple<Album> aux = this.head;
        while (aux != null){
            if (aux.dato.nombre_album.equals(nombre_album))
                return aux.dato;
            
            aux = aux.siguiente;
        }
        return null;
    }
    
    public String graph(){
        StringBuilder str = new StringBuilder();
        str.append("digraph G {\n");
        NodoSimple<Album> aux = this.head;
        while (aux != null){
            str.append("A").append(aux.dato.nombre_album).append("[label = \"")
                    .append(aux.dato.nombre_album).append("\"];\n");
            
            NodoSimple<BinaryTree> auximg = aux.dato.imgs.head;
            
            str.append("A").append(aux.dato.nombre_album).append(" -> ")
                    .append("I").append(auximg.dato.id).append(";\n");
            
            while(auximg != null){
                str.append("I").append(auximg.dato.id).append("[label = \"")
                    .append(auximg.dato.id).append("\"];\n");
                
                if (auximg.siguiente != null)
                    str.append("I").append(auximg.dato.id)
                            .append(" -> ").append("I").append(auximg.siguiente.dato.id).append(";\n");
                
                auximg = auximg.siguiente;
            }
            
            
            
            aux = aux.siguiente;
        }
        str.append("}");
        return str.toString();
    }
}
