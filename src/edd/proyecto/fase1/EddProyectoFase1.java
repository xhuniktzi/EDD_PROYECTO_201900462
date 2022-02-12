/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto.fase1;

import Enums.TipoImagen;
import Impl.ColaRecepcion;
import Modelos.Cliente;
import Modelos.Imagen;
import Modelos.Impresora;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 *
 * @author Xhunik_Local
 */
public class EddProyectoFase1 {
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        Logic l = new Logic();
        l.exec();
        l.graph();
    }
    public static String generateGuid(){
        return UUID.randomUUID().toString().replaceAll("-", "").replaceFirst("[0-9]+", "");
    }
    
}

class Logic {
    Impresora blancoNegroImpresora;
    Impresora colorImpresora;
    ColaRecepcion colaRecepcion;

    public Logic() {
        blancoNegroImpresora = new Impresora(TipoImagen.BLANCONEGRO);
        colorImpresora = new Impresora(TipoImagen.COLOR);
        colaRecepcion = new ColaRecepcion();
    }
    
    public void exec(){
        Imagen img1 = new Imagen(TipoImagen.BLANCONEGRO);
        blancoNegroImpresora.addImage(img1);
        Imagen img2 = new Imagen(TipoImagen.BLANCONEGRO);
        blancoNegroImpresora.addImage(img2);
        Imagen img3 = new Imagen(TipoImagen.BLANCONEGRO);
        blancoNegroImpresora.addImage(img3);
        
        Imagen img4 = new Imagen(TipoImagen.COLOR);
        colorImpresora.addImage(img4);
        Imagen img5 = new Imagen(TipoImagen.COLOR);
        colorImpresora.addImage(img5);
        Imagen img6 = new Imagen(TipoImagen.COLOR);
        colorImpresora.addImage(img6);
        
        Cliente c1 = new Cliente("Cliente 1", 2, 4);
        colaRecepcion.encolar(c1);
        Cliente c2 = new Cliente("Cliente 2", 3, 5);
        colaRecepcion.encolar(c2);
        Cliente c3 = new Cliente("Cliente 3", 6, 2);
        colaRecepcion.encolar(c3);
        Cliente c4 = new Cliente("Cliente 4", 7, 3);
        colaRecepcion.encolar(c4);
    }
    
    public void graph(){
        // Ingresar a generar reporte
        try (FileOutputStream out = new FileOutputStream("./report.dot")) {
            StringBuilder str = new StringBuilder();
            
            // Head del archivo
            str.append("digraph G{\n");
            str.append("rankdir=LR;\n");
            
            // Cola impresora blanco y negro
            str.append("subgraph clusterPrintBN {\n");
            str.append(blancoNegroImpresora.queue.graph(TipoImagen.BLANCONEGRO));
            str.append("}");
            
            // Cola impresora a color
            str.append("subgraph clusterPrintColor {\n");
            str.append(colorImpresora.queue.graph(TipoImagen.COLOR));
            str.append("}");
            
            // Cola recepcion
            str.append("subgraph clusterColaRecepcion {\n");
            str.append(colaRecepcion.graph());
            str.append("}");
            
            // End del archivo
            str.append("}");

            // Cerrar archivo
            out.write(str.toString().getBytes());
            out.close();
            System.out.println(str.toString());
            Runtime.getRuntime().exec("dot -Tjpg report.dot -o report.jpg");
            
        } catch (Exception ex){}   
    }

}