/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *
 * @author Xhunik_Local
 */
public class DoubleLinkedList<T> {
    NodoDoble<T> head;
    NodoDoble<T> last;
    
    public boolean isVoid(){
        return this.head == null;
    }
    
    
    public void addToEnd(T dato) {
        NodoDoble<T> nodo = new NodoDoble();
        nodo.dato = dato;
        
        if (this.isVoid()){
            head = last = nodo;
        } else if(head.siguiente == null){
            nodo.anterior = this.head;
            head.siguiente = nodo;
            last = nodo;
        } else {
            NodoDoble<T> aux = this.head;
            while(aux.siguiente != null){
                aux = aux.siguiente;
            }
            nodo.anterior = aux;
            aux.siguiente = nodo;
        }
    }
    
    // TEST
    public void recorrer(){
        NodoDoble<T> aux = this.head;
            while(aux != null){
                StringBuilder str = new StringBuilder();
                if (aux.anterior != null)
                    str.append(aux.anterior.dato + " <- ");
                
                str.append(aux.dato);

                if (aux.siguiente != null)
                    str.append("->" + aux.siguiente.dato);
                
                System.out.println(str.toString());
                //System.out.println(aux.anterior.dato + " <- " + aux.dato+" -> "+aux.siguiente.dato);
                aux = aux.siguiente;
            }
 
    }
}
