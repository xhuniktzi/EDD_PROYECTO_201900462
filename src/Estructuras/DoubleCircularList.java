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
public class DoubleCircularList<T> {
    NodoDoble<T> head;
    
    public boolean isVoid(){
        return head == null;
    }
    
    public void addToHead(T dato){
        NodoDoble<T> nodo = new NodoDoble<>();
        nodo.dato = dato;
        if(this.isVoid()){
            this.head = nodo;
            nodo.anterior = nodo.siguiente = this.head;
        } else {
            NodoDoble<T> last = this.head.anterior;
            nodo.siguiente = this.head;
            nodo.anterior = last;
            this.head.anterior = nodo;
            last.siguiente = nodo;
            this.head = nodo;
        }
    }
    
    public void addToEnd(T dato) {
        NodoDoble<T> nodo=new NodoDoble<T>();
        nodo.dato = dato;
        if (isVoid()) {
            nodo.anterior = nodo.siguiente = nodo;             
            this.head = nodo;
        } else {
            NodoDoble<T> last = this.head.anterior;
            nodo.siguiente = this.head;
            nodo.anterior = last;
            this.head.anterior = nodo;
            last.siguiente = nodo;
        }
    }    
    
    public int cantidad()
    {
        int cant = 0;
        if (!isVoid()) {
            NodoDoble<T> aux=this.head;
            do {
                cant++;
                aux = aux.siguiente;                
            } while (aux!=this.head);
        }    
        return cant;
    }
    
    public void borrar(int pos)
    {
        pos += 1;
        if (pos <= cantidad()) {
            if (pos == 1) {
                if (cantidad()==1) {
                    this.head=null;
                } else {
                    NodoDoble<T> last = this.head.anterior;
                    this.head = this.head.siguiente;
                    last.siguiente=this.head;
                    this.head.anterior=last;
                } 
            } else {
                NodoDoble<T> aux = this.head;
                for (int f = 1 ; f <= pos - 1 ; f++)
                    aux = aux.siguiente;
                NodoDoble<T> anterior = aux.anterior;
                aux=aux.siguiente;
                anterior.siguiente=aux;
                aux.anterior = anterior;
            }
        }
    }
    
    // TEST
    public void recorrer(){
        if(!isVoid()){
            if (this.head != null)
                System.out.println("head: " + this.head.dato);
            
            NodoDoble<T> aux = this.head;
            do {
                StringBuilder str = new StringBuilder();
                if (aux.anterior != null)
                    str.append(aux.anterior.dato + " <- ");

                str.append(aux.dato);

                if (aux.siguiente != null)
                    str.append("->" + aux.siguiente.dato);

                System.out.println(str.toString());
                aux = aux.siguiente;
            } while (aux != this.head);
        }
    }
    
    // TEST
//    public void recorrer(){
//        NodoDoble<T> aux = this.head;

//        
//        if (aux != null){
//            while(aux.siguiente != this.head){

//                aux = aux.siguiente;
//            }
//        }
//
//    }
}
