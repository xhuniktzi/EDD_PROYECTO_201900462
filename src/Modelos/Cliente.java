/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Estructuras.LinkedList;
import Impl.ListaImpresiones;
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
//    private int imgColorToProcess;
//    private int imgColorProcessed;
//    private int imgBlancoNegroToProcess;
//    private int imgBlancoNegroProcessed;
    public ListaImpresiones listaImages;

    public Cliente(String nombre) {
        this.id = EddProyectoFase1.generateGuid();
        this.nombre = nombre;
        this.listaImages = new ListaImpresiones();
    }

    public Cliente(String nombre, int imgColor, int imgBlancoNegro) {
        this(nombre);
        this.imgColor = imgColor;
        this.imgBlancoNegro = imgBlancoNegro;
    }
    
//    public boolean processColor(){
//        imgColorProcessed++;
//        return this.imgColorProcessed < this.imgColorToProcess;
//    }
//    
//    public boolean processBlancoNegro(){
//        imgBlancoNegroProcessed++;
//        return this.imgBlancoNegroProcessed < this.imgBlancoNegroToProcess;
//    }
}
