/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

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
    public int pasos;
    public String idVentanilla;
    public int imgBlancoNegroConst;
    public int imgColorConst;
//    private int imgColorToProcess;
//    private int imgColorProcessed;
//    private int imgBlancoNegroToProcess;
//    private int imgBlancoNegroProcessed;
    public ListaImpresiones listaImages;

    public Cliente(){
        this.id = EddProyectoFase1.generateGuid();
        this.listaImages = new ListaImpresiones();
        this.pasos = 0;
    }
    
    public Cliente(String nombre) {
        this();
        this.nombre = nombre;
    }

    public Cliente(String nombre, int imgColor, int imgBlancoNegro) {
        this(nombre);
        this.imgColorConst = this.imgColor = imgColor;
        this.imgBlancoNegroConst = this.imgBlancoNegro = imgBlancoNegro;
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
