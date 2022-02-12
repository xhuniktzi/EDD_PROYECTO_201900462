/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import edd.proyecto.fase1.EddProyectoFase1;

/**
 *
 * @author Xhunik_Local
 */
public class Cliente {
    public String id;
    public String nombre;
    public int imgColor;
    public int imgBlancoNegro;

    public Cliente(String nombre) {
        this.id = EddProyectoFase1.generateGuid();
        this.nombre = nombre;
    }

    public Cliente(String nombre, int imgColor, int imgBlancoNegro) {
        this(nombre);
        this.imgColor = imgColor;
        this.imgBlancoNegro = imgBlancoNegro;
    }
    
    
    
}
