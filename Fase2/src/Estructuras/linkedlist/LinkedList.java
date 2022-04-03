/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.linkedlist;

/**
 *
 * @author Xhunik_Local
 * @param <T>
 */
public class LinkedList<T> {
    public NodoSimple<T> head;
    
    public void addToHead(T dato){
        NodoSimple<T> nodo  = new NodoSimple();
        nodo.dato = dato;
        nodo.siguiente = this.head;
        this.head = nodo;
    }
    
    public boolean isVoid(){
        return this.head == null;
    }
    
    public T getByIndex(int index){
        int i = 0;
        if(isVoid()){
            return null;
        }
        else {
            NodoSimple<T> aux = this.head;
            while (aux != null){
                if (i == index)
                    return aux.dato;
                i++;
                aux = aux.siguiente;
            }
            return null;
        }
    }
    
    public T getHead(){
        if(!isVoid())
            return this.head.dato;
        return null;
    }
    
    
    public void deleteHead(){
        if (!this.isVoid()){
            this.head = this.head.siguiente;
        }
    }
    
    public void clear(){
        this.head = null;
    }
    
    public void addToEnd(T dato){
        NodoSimple<T> nodo = new NodoSimple();
        nodo.dato = dato;
        if (this.isVoid()){
            this.head = nodo;
            return;
        }
        
        NodoSimple<T> aux = this.head;
        while (aux.siguiente != null){
            aux = aux.siguiente;
        }
        aux.siguiente = nodo;
    }
    
    public int size(){
        int i = 0;
        NodoSimple<T> aux = this.head;
        while (aux != null){
            i++;
            aux = aux.siguiente;
        }
        return i;
    }
} 