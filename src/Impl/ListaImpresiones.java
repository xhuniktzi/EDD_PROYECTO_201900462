/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Impl;

import Estructuras.LinkedList;
import Estructuras.NodoSimple;
import Modelos.Cliente;
import Modelos.Imagen;

/**
 *
 * @author Xhunik_Local
 */
public class ListaImpresiones extends LinkedList<Imagen> {
     public String graph(String headId, ListaClientesEspera espera){
        StringBuilder str = new StringBuilder();
        if(!this.isVoid()){
            NodoSimple<Imagen> auxc = this.head;
            str.append(headId).append("->").append(auxc.dato.id).append(";\n");
                while(auxc != null){
                    Cliente cl = espera.getById(auxc.dato.clientId);
                    if (cl != null){
                        str.append(auxc.dato.id).append("[label=\"").append(auxc.dato.tipo.toString())
                                .append("\n").append("Cliente: ").append(cl.nombre)
                                .append("\"];\n");
                    if (auxc.siguiente != null)
                        str.append(auxc.dato.id).append("->").append(auxc.siguiente.dato.id).append(";\n");
                    }


                    auxc = auxc.siguiente;
                }        
        }
            
        return str.toString();
    }
}
