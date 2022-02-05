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
public class LinkedList<T> {
    public NodoSimple<T> head;
    
    public void addToHead(T dato){
        NodoSimple<T> nodo  = new NodoSimple();
        nodo.dato = dato;
        nodo.siguiente = this.head;
        this.head.siguiente = nodo;
    }
    
    public boolean isVoid(){
        return this.head == null;
    }
    
    public T getHead(){
        return this.head.dato;
    }
    
    
    public void deleteHead(){
        if (!this.isVoid()){
            this.head = this.head.siguiente;
        }
    }
    
    public void clear(){
        this.head = null;
    }
}
