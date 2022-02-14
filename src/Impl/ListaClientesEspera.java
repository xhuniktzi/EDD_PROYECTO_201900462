/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Impl;

import Estructuras.DoubleCircularList;
import Estructuras.NodoDoble;
import Modelos.Cliente;
import Modelos.Imagen;

/**
 *
 * @author Xhunik_Local
 */
public class ListaClientesEspera extends DoubleCircularList<Cliente>{
    public String graph(){
        StringBuilder str = new StringBuilder();
        if(!isVoid()){
            NodoDoble<Cliente> aux = this.head;
            do {
                str.append(aux.dato.listaImages.graph(aux.dato.id));
                str.append(aux.dato.id).append("[label=\"").append(aux.dato.nombre)
                        .append("\n Color: ").append(aux.dato.imgColorConst)
                        .append("\n Blanco y Negro: ").append(aux.dato.imgBlancoNegroConst)
                        .append("\"];\n");
                if (aux.anterior != null)
                    str.append(aux.dato.id).append("->")
                            .append(aux.anterior.dato.id).append(";\n");

                if (aux.siguiente != null)
                    str.append(aux.dato.id).append("->")
                            .append(aux.siguiente.dato.id).append(";\n");

            aux = aux.siguiente;
            } while (aux != this.head);
        }
        return str.toString();
    }
    
    public void appendImageToCliente(String id, Imagen img){
        if(!isVoid()){
            NodoDoble<Cliente> aux = this.head;
            do {
                if (aux.dato.id.equals(id)){
                    switch (img.tipo){
                        case BLANCONEGRO:
                            aux.dato.imgBlancoNegro++;
                            break;
                        case COLOR:
                            aux.dato.imgColor++;
                            break;
                    }
                    aux.dato.listaImages.addToEnd(img);
                }
                aux = aux.siguiente;
            } while (aux != this.head);
        }
    }
    
    public void popClientesCompletados(ListaClientesAtendidos atendidos){
        if(!isVoid()){
            NodoDoble<Cliente> aux = this.head;
            do {
                if (aux.dato.imgBlancoNegro == aux.dato.imgBlancoNegroConst && aux.dato.imgColor == aux.dato.imgColorConst){
                    atendidos.addToEnd(aux.dato);
                    this.deleteById(aux.dato.id);
                    return;
                }
                    
                aux = aux.siguiente;
            } while (aux != this.head);
        }
    }
    
    private void deleteById(String id){
        NodoDoble<Cliente> aux = this.head;
        NodoDoble<Cliente> ant = null;
        boolean flag = false;
        if (!isVoid()){
            do {
                if(aux.dato.id.equals(id)){
                    if(aux == this.head){
                        this.head = this.head.siguiente;
                        this.head.anterior = this.last;
                        this.last.siguiente = this.head;
                    }
                    else if (aux==this.last){
                        this.last = ant;
                        this.last.siguiente = this.head;
                        this.head.anterior = this.last;
                    } else {
                        ant.siguiente = aux.siguiente;
                        aux.siguiente.anterior = ant;
                    }
                    flag = true;
                }
                ant = aux;
                aux = aux.siguiente;
            }while(aux != this.head && !flag);
        }        
   }
}
