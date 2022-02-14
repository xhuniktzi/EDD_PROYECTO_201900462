/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Enums.TipoImagen;
import Impl.ColaImpresoras;
import Impl.ListaClientesEspera;
import edd.proyecto.fase1.EddProyectoFase1;

/**
 *
 * @author Xhunik_Local
 */
public class Impresora {
    public String id;
    public TipoImagen tipo;
    public ColaImpresoras queue;
//    public int turnos;
    
    public Impresora(TipoImagen tipo){
        this.id = EddProyectoFase1.generateGuid();
        this.tipo = tipo;
        this.queue = new ColaImpresoras();
//        this.turnos = 0;
    }
    
    public void addImage(Imagen img){
        this.queue.enqueue(img);
    }
    
    public void printImage(ListaClientesEspera espera){
        Imagen img = this.queue.dequeue();
        if (img != null){
            espera.appendImageToCliente(img.clientId, img);
        
//        turnos++;
//        if(tipo == TipoImagen.COLOR && turnos == 2){
//            Imagen img = this.queue.dequeue();
//            if (img != null){
//                espera.appendImageToCliente(img.clientId, img);
//                turnos = 0;
//                return;
//            }
//        } 
//        if (tipo == TipoImagen.BLANCONEGRO && turnos == 1){
//            Imagen img = this.queue.dequeue();
//            if (img != null){
//                espera.appendImageToCliente(img.clientId, img);
//                turnos = 0;
//                return;
//            }
//        }
        
        }
    }
}
