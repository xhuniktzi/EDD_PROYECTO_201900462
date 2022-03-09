/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *xx
 * @author Xhunik_Local
 */
public class DoubleLinkedList<T> {
    public NodoDoble<T> head;
    public NodoDoble<T> last;
    
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
        if (!this.isVoid())
            return this.head.dato;
        return null;
    }
    
    public T getEnd(){
    if (!this.isVoid())
        return this.last.dato;
    return null;
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
    
    public void clear(){
        this.head = this.last = null;
    }
}
