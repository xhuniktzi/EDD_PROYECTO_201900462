/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Enums.TipoImagen;
import Estructuras.Queue;
import Impl.ColaImpresoras;
import edd.proyecto.fase1.EddProyectoFase1;

/**
 *
 * @author Xhunik_Local
 */
public class Impresora {
    public String id;
    public TipoImagen tipo;
    public ColaImpresoras queue;
    
    public Impresora(TipoImagen tipo){
        this.id = EddProyectoFase1.generateGuid();
        this.tipo = tipo;
        this.queue = new ColaImpresoras();
    }
    
    public void addImage(Imagen img){
        this.queue.enqueue(img);
    }
}
