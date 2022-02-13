/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Impl;

import Estructuras.DoubleCircularList;
import Estructuras.NodoDoble;
import Modelos.Cliente;

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
                        .append("\n Color: ").append(aux.dato.imgColor)
                        .append("\n Blanco y Negro: ").append(aux.dato.imgBlancoNegro)
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
    
    public Cliente findClientById(String id){
            if(!isVoid()){

            NodoDoble<Cliente> aux = this.head;
            do {
                if (aux.dato.id.equals(id))
                    return aux.dato;
                aux = aux.siguiente;
            } while (aux != this.head);
        }
        return null;
    }
}
