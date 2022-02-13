/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Estructuras.Stack;
import Impl.PilaImpresiones;
import edd.proyecto.fase1.EddProyectoFase1;

/**
 *
 * @author Xhunik_Local
 */
public class Ventanilla {
    public String id;
    public String nombre;
    public Cliente cliente;
    public PilaImpresiones pilaImagenes;

    public Ventanilla(String nombre) {
        this.id = EddProyectoFase1.generateGuid();
        this.nombre = nombre;
        this.pilaImagenes = new PilaImpresiones();
    }
    
}
