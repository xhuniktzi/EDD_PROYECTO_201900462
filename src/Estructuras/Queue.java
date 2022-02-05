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
public class Queue<T> extends DoubleLinkedList {
    public void enqueue(T dato){
        this.addToHead(dato);
    }
    
    public T dequeue(){
        T value = (T) this.getEnd();
        this.deleteEnd();
        return value;
    }
    
}
