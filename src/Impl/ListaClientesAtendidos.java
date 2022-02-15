/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Impl;

import Estructuras.LinkedList;
import Estructuras.NodoSimple;
import Modelos.Cliente;
/**
 *
 * @author Xhunik_Local
 */
public class ListaClientesAtendidos extends LinkedList<Cliente> {
    public String graph(){
        StringBuilder str = new StringBuilder();
        if(!this.isVoid()){
            NodoSimple<Cliente> auxc = this.head;
            while(auxc != null){
                str.append(auxc.dato.id).append("[label=\"").append(auxc.dato.nombre)
                    .append("\n Color: ").append(auxc.dato.imgColor)
                    .append("\n Blanco y Negro: ").append(auxc.dato.imgBlancoNegro)
                        .append("\n Pasos: ").append(auxc.dato.pasos)
                        .append("\"];\n");

                if (auxc.siguiente != null)
                    str.append(auxc.dato.id).append("->").append(auxc.siguiente.dato.id).append(";\n");

                auxc = auxc.siguiente;
            }        
        }
            
        return str.toString();
    }
    
    public void printFive(){
        int count = 0;
        if(!this.isVoid()){
            NodoSimple<Cliente> auxc = this.head;
            while(auxc != null){
                System.out.println("Cliente: " + auxc.dato.nombre);
                if(count >= 4)
                    return;
                count++;
                auxc = auxc.siguiente;
            }        
        }
    }
    
    public void printOne(){
        if(!this.isVoid())
            System.out.println("Cliente: " + this.head.dato.nombre);
    }
    
    public void sortBySteps(){
        int n = this.getSize();
        for (int i = 0; i < n-1; i++){
            for (int j = 0; j < n-i-1; j++){
                if (getByIndex(j).pasos < getByIndex(j+1).pasos)
                {
                    // swap arr[j+1] and arr[j]
                    Cliente temp = getByIndex(j);
                    setByIndex(j, getByIndex(j+1));
                    setByIndex(j+1, temp);
                }
            }
        }
    }
    
    public void sortByColorAsc(){
        int n = this.getSize();
        for (int i = 0; i < n-1; i++){
            for (int j = 0; j < n-i-1; j++){
                if (getByIndex(j).imgColorConst > getByIndex(j+1).imgBlancoNegroConst)
                {
                    // swap arr[j+1] and arr[j]
                    Cliente temp = getByIndex(j);
                    setByIndex(j, getByIndex(j+1));
                    setByIndex(j+1, temp);
                }
            }
        }
    }
    
    public void sortByBlancoNegroDesc(){
        int n = this.getSize();
        for (int i = 0; i < n-1; i++){
            for (int j = 0; j < n-i-1; j++){
                if (getByIndex(j).imgBlancoNegroConst < getByIndex(j+1).imgBlancoNegroConst)
                {
                    // swap arr[j+1] and arr[j]
                    Cliente temp = getByIndex(j);
                    setByIndex(j, getByIndex(j+1));
                    setByIndex(j+1, temp);
                }
            }
        }
    }
    
    private Cliente getByIndex(int index){
        int count = 0;
        NodoSimple<Cliente> nodo = this.head;
        while (nodo != null){
            if (index == count)
                return nodo.dato;
            count++;
            nodo = nodo.siguiente;
        }
        return null;
    }
    
    private void setByIndex(int index, Cliente data){
        int count = 0;
        NodoSimple<Cliente> nodo = this.head;
        while (nodo != null){
            if (index == count){
                nodo.dato = data;
                return;
            }
            count++;
            nodo = nodo.siguiente;
        }
    }
    
    private int getSize(){
        int count = 0;
        NodoSimple<Cliente> nodo = this.head;
        while(nodo != null){
            count++;
            nodo = nodo.siguiente;
        }
        return count;
    }
//    
//        def get_size(self):
//        count = 0
//        node = self.head
//        while node != None:
//            node = node.next
//            count = count + 1
//        return count
    
//        def get_by_index(self, index: int):
//        count = 0
//        node = self.head
//        while node != None:
//            if index == count:
//                return node.data
//            count = count + 1
//            node = node.next
//        return None
//
//    # Añade un elemento en el indice
//    def set_by_index(self, index: int, data):
//        count = 0
//        node = self.head
//        while node != None:
//            if index == count:
//                node.data = data
//                return
//            count = count + 1
//            node = node.next
//        return
}
