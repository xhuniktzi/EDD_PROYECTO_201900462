/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Impl;

import Estructuras.NodoDoble;
import Estructuras.Queue;
import Modelos.Cliente;

/**
 *
 * @author Xhunik_Local
 */
public class ColaRecepcion  extends Queue<Cliente>{
    public String graph(){
        StringBuilder str = new StringBuilder();
        NodoDoble<Cliente> auxc = this.head;
            while(auxc != null){
                str.append(auxc.dato.id).append("[label=\" ").append(auxc.dato.nombre)
                        .append("\n Color: ").append(auxc.dato.imgColor)
                        .append("\n Blanco y Negro: ").append(auxc.dato.imgBlancoNegro).append(" \"];\n");

                if (auxc.siguiente != null)
                    str.append(auxc.dato.id).append("->").append(auxc.siguiente.dato.id).append(";\n");

                auxc = auxc.siguiente;
            }
            
        return str.toString();
    }
    
    public void encolar(Cliente cliente){
        this.enqueue(cliente);
    }
}
