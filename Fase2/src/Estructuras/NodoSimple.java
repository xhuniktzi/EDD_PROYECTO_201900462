/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *
 * @author Xhunik_Local
 * @param <T>
 */
public class NodoSimple <T> {
    public NodoSimple<T> next;
    public T value;
    
    public NodoSimple(){}
    
    public NodoSimple(T value){
        this.value = value;        
    }
    
    public NodoSimple(T value, NodoSimple<T> next){
        this(value);
        this.next = next;
    }
}
