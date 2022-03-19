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
public class ListaEnlazadaSimple<T> {
    public NodoSimple<T> head;
    public final int size;
    public ListaEnlazadaSimple(int size){
        this.size = size;
        int i = 1;
        this.head = new NodoSimple(null);
        NodoSimple<T> aux = this.head;
        while (i < size) {
            aux.next = new NodoSimple<>();
            aux = aux.next;
            i++;
        }
    }
    
    public void setByIndex(int index, T value){
        int i = 0;
        NodoSimple aux = this.head;
        while (aux != null){
            if (i == index){
                aux.value = value;
                return;
            }
            
            aux = aux.next;
            i++;
        }
    }
    
    public T getByIndex(int index){
        int i = 0;
        NodoSimple<T> aux = this.head;
        while (aux != null){
            if (i == index){
                return aux.value;
            }
            aux = aux.next;
            i++;
        }
        return null;
    }
//    public void recorrer(){
//        NodoSimple aux = this.head;
//        while (aux != null){
//            System.out.print("Valor: " + aux);
//            if (aux.next != null){
//                System.out.print(" -> " + aux.next);
//            }
//            System.out.println();
//            aux = aux.next;
//        }
//    }
}
