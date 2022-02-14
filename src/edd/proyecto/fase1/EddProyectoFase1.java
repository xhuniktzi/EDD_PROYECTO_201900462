/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto.fase1;

import Enums.TipoImagen;
import Impl.ColaRecepcion;
import Impl.ListaClientesAtendidos;
import Impl.ListaClientesEspera;
import Impl.ListaVentanillas;
import Modelos.Cliente;
import Modelos.Impresora;
import Modelos.Ventanilla;
import java.io.FileOutputStream;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

/**
 *
 * @author Xhunik_Local
 */
public class EddProyectoFase1 {
    /**
     * @param args the command line arguments
     */
    
    static Scanner sc;
    
    public static void main(String[] args) {
        // TODO code application logic here
        sc = new Scanner(System.in);
        Logic l = new Logic();
        OUTER:
        while (true) {
            System.out.println("----    Menú Principal      ----");
            System.out.println("1.  Parametros iniciales");
            System.out.println("2.  Ejecutar Paso");
            System.out.println("3.  Estado de la aplicación");
            System.out.println("4.  Reportes");
            System.out.println("5.  Acerca de");
            System.out.println("6.  Salir");
            switch (sc.nextInt()) {
                case 1:
                    l.cargarClientes();
                    System.out.println("Ingrese numero de ventanillas");
                    l.cargarVentanillas(sc.nextInt());
                    break;
                case 2:
                    l.execStep();
                    break;
                case 3:
                    l.graph();
                    break;
                case 6:
                    break OUTER;
                default:
                    System.out.println("Ingrese un valor valido");
                    break;
            }
        }
    }
    public static String generateGuid(){
        return UUID.randomUUID().toString().replaceAll("-", "").replaceFirst("[0-9]+", "");
    }
    
    
    public static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.ints(min, (max + 1)).findFirst().getAsInt();
    }
    
    public static String randomNames(){
        String[] arr = new String[10];
        arr[0] = "Leslie";
        arr[1] = "Juan";
        arr[2] = "Pedro";
        arr[3] = "Jorge";
        arr[4] = "Dulce";
        arr[5] = "Celeste";
        arr[6] = "Miguel";
        arr[7] = "Diego";
        arr[8] = "Sofia";
        arr[9] = "Ruben";
        return arr[getRandomNumberInRange(0, 9)];
    }
    
    public static String randomLastNames(){
        String[] arr = new String[10];
        arr[0] = "Muñoz";
        arr[1] = "Rodriguez";
        arr[2] = "Lopez";
        arr[3] = "Rivera";
        arr[4] = "Hernandez";
        arr[5] = "Velasquez";
        arr[6] = "Molina";
        arr[7] = "Suarez";
        arr[8] = "Escobar";
        arr[9] = "Aguirre";
        return arr[getRandomNumberInRange(0, 9)];
    }
}

class Logic {
    Impresora blancoNegroImpresora;
    Impresora colorImpresora;
    ColaRecepcion colaRecepcion;
    ListaVentanillas listaVentanillas;
    ListaClientesEspera listaEspera;
    ListaClientesAtendidos listaAtendidos;
    
    public Logic() {
        blancoNegroImpresora = new Impresora(TipoImagen.BLANCONEGRO);
        colorImpresora = new Impresora(TipoImagen.COLOR);
        colaRecepcion = new ColaRecepcion();
        listaVentanillas = new ListaVentanillas();
        listaEspera = new ListaClientesEspera();
        listaAtendidos = new ListaClientesAtendidos();
    }
//    
//    public void testInit(){
//        cargarClientes();
//        cargarVentanillas(6);
//    }
    
    public void cargarVentanillas(int cant){
        for (int i = 0; i < cant; i++) {
            Ventanilla v = new Ventanilla("Ventanilla "+ i);
            listaVentanillas.addToEnd(v);
        }
    }
    
    public void cargarClientes(){
        randomClientes();
    }
    
    public void execStep(){
        randomClientes();
        listaVentanillas.insertarClienteVentanilla(colaRecepcion.dequeue());
        listaVentanillas.ingresarUnElementoPila();
        listaVentanillas.encolarImpresiones(colorImpresora.queue, blancoNegroImpresora.queue, listaEspera);
        colorImpresora.printImage(listaEspera);
        blancoNegroImpresora.printImage(listaEspera);
        listaEspera.popClientesCompletados(listaAtendidos);
    }
    
    private void randomClientes(){
        int numClientes = EddProyectoFase1.getRandomNumberInRange(0, 3);
        for (int i = 0; i < numClientes; i++) {
            int color = EddProyectoFase1.getRandomNumberInRange(0, 4);
            int bn = EddProyectoFase1.getRandomNumberInRange(0, 4);
            colaRecepcion.enqueue(new Cliente(
                    EddProyectoFase1.randomNames() + " " + EddProyectoFase1.randomLastNames()
                    , color, bn));
        }
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
            str.append("label=\"Cola Impresora Blanco y Negro\"");
            str.append(blancoNegroImpresora.queue.graph(TipoImagen.BLANCONEGRO));
            str.append("}");
            
            // Cola impresora a color
            str.append("subgraph clusterPrintColor {\n");
            str.append("label=\"Cola Impresora Color\"");
            str.append(colorImpresora.queue.graph(TipoImagen.COLOR));
            str.append("}");
            
            // Cola recepcion
            str.append("subgraph clusterColaRecepcion {\n");
            str.append("label=\"Cola Recepcion\"");
            str.append(colaRecepcion.graph());
            str.append("}");
            
            // Lista de ventanillas
            str.append("subgraph clusterListaVentanillas {\n");
            str.append("label=\"Ventanillas\"");
            str.append(listaVentanillas.graph());
            str.append("}");
            
            // Lista espera
            str.append("subgraph clusterListaEspera {\n");
            str.append("label=\"Lista de espera\"");
            str.append(listaEspera.graph());
            str.append("}");
            
            // Lista atendidos
            str.append("subgraph clusterListaAtendidos {\n");
            str.append("label=\"Atendidos\"");
            str.append(listaAtendidos.graph());
            str.append("}");
            
            // End del archivo
            str.append("}");

            // Cerrar archivo
            out.write(str.toString().getBytes());
            out.close();
//            System.out.println(str.toString());
            Runtime.getRuntime().exec("dot -Tjpg report.dot -o report.jpg");
            
        } catch (Exception ex){
        ex.printStackTrace();}   
    }

}