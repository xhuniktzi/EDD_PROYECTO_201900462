/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Impl;

import Enums.TipoImagen;
import Estructuras.NodoDoble;
import Estructuras.Queue;
import Modelos.Imagen;

/**
 *
 * @author Xhunik_Local
 */
public class ColaImpresoras extends Queue<Imagen>{
    public String graph(TipoImagen tipo){
        String tipoString = null;
        String tipoId = null;
        switch (tipo){
            case BLANCONEGRO:
                tipoString = "Blanco y Negro";
                tipoId = "BN";
                break;
            case COLOR:
                tipoString = "Color";
                tipoId = "C";
                break;
        }
        StringBuilder str = new StringBuilder();
        
        if(!this.isVoid()){
            NodoDoble<Imagen> auxc = this.head;
            while(auxc != null){
                str.append(auxc.dato.id).append("[label=\"").append(auxc.dato.tipo.toString()).append("\"];\n");

                if (auxc.siguiente != null)
                    str.append(auxc.dato.id).append("->").append(auxc.siguiente.dato.id).append(";\n");

                auxc = auxc.siguiente;
            }
            str.append(tipoId).append("[label=\"").append(tipoString).append("\"];\n");
            if (this.last != null)
                str.append(this.last.dato.id).append("-> ").append(tipoId).append(";\n");
        }
        return str.toString();
    }
}
