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
public class Stack<T> extends LinkedList {
    public void push(T dato){
        this.addToHead(dato);
    }
    
    public T pop(T dato){
        T value = (T) this.getHead();
        this.deleteHead();
        return value;
    }
}
