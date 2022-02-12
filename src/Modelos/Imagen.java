/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Enums.TipoImagen;
import edd.proyecto.fase1.EddProyectoFase1;
import java.util.UUID;

/**
 *
 * @author Xhunik_Local
 */
public class Imagen {
    public String id;
    public TipoImagen tipo;
    public String clientId;

    public Imagen(TipoImagen tipo) {
        this.id = EddProyectoFase1.generateGuid();
        this.tipo = tipo;
    }
    
    public Imagen(TipoImagen tipo, String clientId) {
        this(tipo);
        this.clientId = clientId;
    }
}
