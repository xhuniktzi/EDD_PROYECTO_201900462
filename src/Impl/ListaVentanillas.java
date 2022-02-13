/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Impl;

import Enums.TipoImagen;
import Estructuras.LinkedList;
import Estructuras.NodoSimple;
import Modelos.Cliente;
import Modelos.Imagen;
import Modelos.Ventanilla;

/**
 *
 * @author Xhunik_Local
 */
public class ListaVentanillas extends LinkedList<Ventanilla> {
//    public void insertarClienteVentanilla(Cliente cliente){
//        NodoSimple<Ventanilla> auxc = this.head;
//            while(auxc != null){
//                if (auxc.dato.cliente == null){
//                    auxc.dato.cliente = cliente;
//                    return;
//                }
//                auxc = auxc.siguiente;
//            }
//    }
//    
//    public void ingresarUnElementoPila(){
//        NodoSimple<Ventanilla> auxc = this.head;
//            while(auxc != null){
//                if (auxc.dato.cliente != null){
//                    if (auxc.dato.cliente.imgColor > 0){
//                        auxc.dato.pilaImagenes.push(new Imagen(TipoImagen.COLOR, auxc.dato.id));
//                        auxc.dato.cliente.imgColor--;
//                    }else if (auxc.dato.cliente.imgBlancoNegro > 0){
//                        auxc.dato.pilaImagenes.push(new Imagen(TipoImagen.BLANCONEGRO, auxc.dato.id));
//                        auxc.dato.cliente.imgBlancoNegro--;
//                    }
//                    
//                }
//                auxc = auxc.siguiente;
//            }
//    }
    
    public String graph(){
        StringBuilder str = new StringBuilder();
        if(!this.isVoid()){
            NodoSimple<Ventanilla> auxc = this.head;
                while(auxc != null){
                    str.append(auxc.dato.id).append("[label=\" ").append(auxc.dato.nombre).append(" \"];\n");

                    if (auxc.siguiente != null)
                        str.append(auxc.dato.id).append("->").append(auxc.siguiente.dato.id).append(";\n");

                    str.append(auxc.dato.pilaImagenes.graph(auxc.dato.id));
                    if(auxc.dato.cliente != null){
                        str.append(auxc.dato.cliente.id).append("[label=\"")
                                .append(auxc.dato.cliente.nombre)
                                .append("\n Color: ").append(auxc.dato.cliente.imgColor)
                                .append("\n Blanco y Negro: ").append(auxc.dato.cliente.imgBlancoNegro).append("\"];\n");

                        str.append(auxc.dato.id).append("->").append(auxc.dato.cliente.id)
                                .append(";\n");
                    }
                    auxc = auxc.siguiente;
                }
        }
        return str.toString();
    }
}
