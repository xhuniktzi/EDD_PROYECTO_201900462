/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto.fase1;

import Enums.TipoImagen;
import Impl.ColaRecepcion;
import Impl.ListaClientesEspera;
import Impl.ListaVentanillas;
import Modelos.Cliente;
import Modelos.Imagen;
import Modelos.Impresora;
import Modelos.Ventanilla;
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
    ListaVentanillas listaVentanillas;
    ListaClientesEspera listaEspera;
    
    public Logic() {
        blancoNegroImpresora = new Impresora(TipoImagen.BLANCONEGRO);
        colorImpresora = new Impresora(TipoImagen.COLOR);
        colaRecepcion = new ColaRecepcion();
        listaVentanillas = new ListaVentanillas();
        listaEspera = new ListaClientesEspera();
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
        
        Cliente c1 = new Cliente("Cliente 1", 1, 1);
        colaRecepcion.enqueue(c1);
        Cliente c2 = new Cliente("Cliente 2", 2, 2);
        colaRecepcion.enqueue(c2);
        Cliente c3 = new Cliente("Cliente 3", 3, 3);
        colaRecepcion.enqueue(c3);
        Cliente c4 = new Cliente("Cliente 4", 4, 4);
        colaRecepcion.enqueue(c4);
        
        Ventanilla v1 = new Ventanilla("Ventanilla 1");
        listaVentanillas.addToEnd(v1);
        Ventanilla v2 = new Ventanilla("Ventanilla 2");
        listaVentanillas.addToEnd(v2);
        Ventanilla v3 = new Ventanilla("Ventanilla 3");
        listaVentanillas.addToEnd(v3);
        
        Cliente c5 = new Cliente("Cliente 5", 1, 1);
        Cliente c6 = new Cliente("Cliente 6", 2, 2);
        Cliente c7 = new Cliente("Cliente 7", 3, 3);
        Cliente c8 = new Cliente("Cliente 8", 4, 4);

        listaVentanillas.insertarClienteVentanilla(c5);
        listaVentanillas.insertarClienteVentanilla(c6);
        listaVentanillas.insertarClienteVentanilla(c7);
        listaVentanillas.insertarClienteVentanilla(c8);
        
        listaVentanillas.ingresarUnElementoPila();
        listaVentanillas.ingresarUnElementoPila();
//        listaVentanillas.ingresarUnElementoPila();
//        listaVentanillas.ingresarUnElementoPila();
//        listaVentanillas.ingresarUnElementoPila();
//        listaVentanillas.ingresarUnElementoPila();
//        listaVentanillas.ingresarUnElementoPila();
//        listaVentanillas.ingresarUnElementoPila();

        Cliente c9 = new Cliente("Cliente 9", 1, 1);
        c9.listaImages.addToEnd(new Imagen(TipoImagen.COLOR, c9.id));
        c9.listaImages.addToEnd(new Imagen(TipoImagen.BLANCONEGRO, c9.id));
        c9.listaImages.addToEnd(new Imagen(TipoImagen.COLOR, c9.id));
        c9.listaImages.addToEnd(new Imagen(TipoImagen.COLOR, c9.id));
        Cliente c10 = new Cliente("Cliente 10", 2, 2);
        c10.listaImages.addToEnd(new Imagen(TipoImagen.BLANCONEGRO, c10.id));
        c10.listaImages.addToEnd(new Imagen(TipoImagen.COLOR, c10.id));
        Cliente c11 = new Cliente("Cliente 11", 3, 3);
        listaEspera.addToEnd(c9);
        listaEspera.addToEnd(c10);
        listaEspera.addToEnd(c11);
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
            
            // Lista de ventanillas
            str.append("subgraph clusterListaVentanillas {\n");
            str.append(listaVentanillas.graph());
            str.append("}");
            
            // Lista espera
            str.append("subgraph clusterListaEspera {\n");
            str.append(listaEspera.graph());
            str.append("}");
            
            // End del archivo
            str.append("}");

            // Cerrar archivo
            out.write(str.toString().getBytes());
            out.close();
            System.out.println(str.toString());
            Runtime.getRuntime().exec("dot -Tjpg report.dot -o report.jpg");
            
        } catch (Exception ex){
        ex.printStackTrace();}   
    }

}