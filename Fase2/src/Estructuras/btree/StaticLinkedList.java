/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.btree;

/**
 *
 * @author Xhunik_Local
 * @param <T>
 */
public class StaticLinkedList<T> {
    public StaticSimpleNode<T> head;
    
    public StaticLinkedList(int size){
        if (size > 0){
            int i = 0;
            this.head = new StaticSimpleNode<>();
            i++;
            
            StaticSimpleNode<T> aux = this.head;
            while(aux != null && i < size){
                aux.next = new StaticSimpleNode<T>();

                i++;
                aux = aux.next;
            }
        }
    }
    
    public void set(int index, T value){
        int i = 0;
        StaticSimpleNode<T> aux = this.head;
        while(aux != null){
            if (i == index){
                aux.value = value;
                return;
            }
            
            i++;
            aux = aux.next;
        }
    }
    
    public T get(int index){
        int i = 0;
        
        StaticSimpleNode<T> aux = this.head;
        while(aux != null){
            if (i == index){
                return aux.value;
            }
            
            i++;
            aux = aux.next;
        }
        return null;
    }
    
//    public void print(){
//        StaticSimpleNode<T> aux = this.head;
//        while(aux != null){
//            System.out.print(aux.value);
//            if (aux.next != null){
//                System.out.print(" -> " + aux.next.value);
//            }
//            System.out.println();
//            aux = aux.next;
//        }
//    }
}
