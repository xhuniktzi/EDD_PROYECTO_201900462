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
    
    public void addToHead(T dato){
        NodoDoble<T> nodo = new NodoDoble();
        nodo.dato = dato;
        
        if (this.isVoid()){
            head = last = nodo;
        }
        else {
            this.head.anterior = nodo;
            nodo.siguiente = this.head;
            this.head = nodo;
        }
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
            nodo.anterior = last;
            last = last.siguiente = nodo;
        }
    }
    
    public T getHead(){
        return this.head.dato;
    }
    
    public T getEnd(){
        return this.last.dato;
    }
    
    public void deleteHead(){
        if(!this.isVoid()){
            this.head = this.head.siguiente;
            if (this.head != null)
                this.head.anterior = null;
            else 
                this.last = null;
        }
    }
   
    public void deleteEnd(){
        if(!this.isVoid()){
            this.last = this.last.anterior;
            if(this.last != null)
                this.last.siguiente = null;
            else
                this.head = null;
        }
    }

    // TEST
    public void recorrer(){
        NodoDoble<T> aux = this.head;
        if (this.head != null)
            System.out.println("head: " + this.head.dato);
        
        if (this.last != null)
            System.out.println("last: " + this.last.dato);
        
            while(aux != null){
                StringBuilder str = new StringBuilder();
                if (aux.anterior != null)
                    str.append(aux.anterior.dato + " <- ");
                
                str.append(aux.dato);

                if (aux.siguiente != null)
                    str.append("->" + aux.siguiente.dato);
                
                System.out.println(str.toString());
                aux = aux.siguiente;
            }
 
    }
}
