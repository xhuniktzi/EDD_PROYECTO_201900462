/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procedures;

import Estructuras.binario.BinaryTree;
import Estructuras.linkedlist.LinkedList;
import Estructuras.matriz.Matriz;
import Fase2.Globals;

/**
 *
 * @author Xhunik_Local
 */
public class traverseTrees {
    public static Matriz generateImagePreorden(int id){
        BinaryTree img = Globals.images.search(id);
        Matriz nm = new Matriz(id, "image");
        
        LinkedList<Matriz> lm = img.preorden();
        var aux = lm.head;
        while(aux != null){
            nm.insertMatriz(aux.dato);
            aux = aux.siguiente;
        }
        return nm;
    }
    
    public static Matriz generateImageInorden(int id){
        BinaryTree img = Globals.images.search(id);
        Matriz nm = new Matriz(id, "image");
        
        LinkedList<Matriz> lm = img.inorden();
        var aux = lm.head;
        while(aux != null){
            nm.insertMatriz(aux.dato);
            aux = aux.siguiente;
        }
        return nm;
    }
    
    public static Matriz generateImagePosorden(int id){
        BinaryTree img = Globals.images.search(id);
        Matriz nm = new Matriz(id, "image");
        
        LinkedList<Matriz> lm = img.posorden();
        var aux = lm.head;
        while(aux != null){
            nm.insertMatriz(aux.dato);
            aux = aux.siguiente;
        }
        return nm;
    }
}
