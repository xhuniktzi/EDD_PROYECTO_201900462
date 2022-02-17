/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto.fase1;

import Enums.TipoImagen;
import Estructuras.Stack;
import Impl.ColaRecepcion;
import Impl.ListaClientesAtendidos;
import Impl.ListaClientesEspera;
import Impl.ListaVentanillas;
import Modelos.Cliente;
import Modelos.Impresora;
import Modelos.Ventanilla;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Xhunik_Local
 */
public class EddProyectoFase1 {
    /**
     * @param args the command line arguments
     */
    
    static Scanner sc;
    
    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {
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
                case 4:
                    l.printReports();
                    break;
                case 5:
                    System.out.println("-------------------------------------");
                    System.out.println("Proyecto Fase 1 - EDD");
                    System.out.println("Carnet: 201900462");
                    System.out.println("Nombre: Xhunik Miguel");
                    System.out.println("-------------------------------------");
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
    
    private int remaingColorSteps;
    private int remaingBlancoNegroSteps;
    
    Scanner sc;
    
    public Logic() {
        blancoNegroImpresora = new Impresora(TipoImagen.BLANCONEGRO);
        colorImpresora = new Impresora(TipoImagen.COLOR);
        colaRecepcion = new ColaRecepcion();
        listaVentanillas = new ListaVentanillas();
        listaEspera = new ListaClientesEspera();
        listaAtendidos = new ListaClientesAtendidos();
        
        this.remaingBlancoNegroSteps = 1;
        this.remaingColorSteps = 2;
        
        sc = new Scanner(System.in);
    }
    
    public void printReports(){
        System.out.println("Imprimir");
        System.out.println("5 clientes con mas impresiones a color:");
        listaAtendidos.sortByColorAsc();
        listaAtendidos.printFive();
        System.out.println("5 clientes con menos impresiones a blanco y negro:");
        listaAtendidos.sortByBlancoNegroDesc();
        listaAtendidos.printFive();
        System.out.println("Cliente con mas pasos en el sistema");
        listaAtendidos.sortBySteps();
        listaAtendidos.printOne();
        System.out.println("Clientes actualmente en el sistema");
        listaAtendidos.printAll();
        System.out.println("ingrese un numero: ");
        int value = sc.nextInt();
        
        Cliente cl = listaAtendidos.getByIndex(value - 1);
        System.out.println("Cliente:" + cl.nombre);
        System.out.println("Imagenes a blanco y negro: " + cl.imgBlancoNegroConst);
        System.out.println("Imagenes a color: " + cl.imgColorConst);
        System.out.println("Pasos: " + cl.pasos);
    }
    
    public void cargarVentanillas(int cant){
        for (int i = 0; i < cant; i++) {
            Ventanilla v = new Ventanilla("Ventanilla " + i);
            listaVentanillas.addToEnd(v);
        }
    }
    
    public void cargarClientes() throws FileNotFoundException, IOException, ParseException{
        Stack<Cliente> pilaClientes = new Stack<>();
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Archivos JSON", "json"));
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            JSONParser parser = new JSONParser();
            File f = chooser.getSelectedFile();
            Object obj = parser.parse(new FileReader(f));
            JSONObject objJson = (JSONObject) obj;
            
            objJson.keySet().forEach(keyStr -> {
//                System.out.println("Key: " + keyStr);
                if (((JSONObject) objJson.get(keyStr)).keySet() != null){
                    Cliente cl = new Cliente();
                    ((JSONObject) objJson.get(keyStr)).keySet().forEach(keyStr2 -> {
                        Object keyValue = ((JSONObject) objJson.get(keyStr)).get(keyStr2);
                        if(keyStr2.equals("nombre_cliente"))
                            cl.nombre = keyValue.toString();
                        if(keyStr2.equals("img_color"))
                            cl.imgColorConst = cl.imgColor = Integer.valueOf(keyValue.toString());
                        if(keyStr2.equals("img_bw"))
                            cl.imgBlancoNegroConst = cl.imgBlancoNegro = Integer.valueOf(keyValue.toString());
                    });
                    pilaClientes.push(cl);
                }
            }); 
        }
        
        while (!pilaClientes.isVoid()){
            colaRecepcion.enqueue(pilaClientes.pop());
        }
    }
    
    public void execStep(){
        randomClientes();
        listaVentanillas.insertarClienteVentanilla(colaRecepcion.dequeue());
        listaVentanillas.ingresarUnElementoPila();
        listaVentanillas.encolarImpresiones(colorImpresora.queue, blancoNegroImpresora.queue, listaEspera);
        
        if (this.remaingColorSteps > 0){
            remaingColorSteps--;
        } else {
            colorImpresora.printImage(listaEspera);
            remaingColorSteps = 2;
        }
        
        if (this.remaingBlancoNegroSteps > 0){
            remaingBlancoNegroSteps--;
        } else {
            blancoNegroImpresora.printImage(listaEspera);
            remaingBlancoNegroSteps = 1;
        }
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
            str.append("/* Cola blanco y negro */\n");
            str.append("label=\"Cola Impresora Blanco y Negro\";\n");
            str.append(blancoNegroImpresora.queue.graph(TipoImagen.BLANCONEGRO, listaEspera));
            str.append("}");
            
            // Cola impresora a color
            str.append("subgraph clusterPrintColor {\n");
            str.append("/* Cola color */\n");
            str.append("label=\"Cola Impresora Color\";\n");
            str.append(colorImpresora.queue.graph(TipoImagen.COLOR, listaEspera));
            str.append("}");
            
            // Cola recepcion
            str.append("subgraph clusterColaRecepcion {\n");
            str.append("/* Cola recepcion */\n");
            str.append("label=\"Cola Recepcion\";\n");
            str.append(colaRecepcion.graph());
            str.append("}");
            
            // Lista de ventanillas
            str.append("subgraph clusterListaVentanillas {\n");
            str.append("/* Ventanillas */\n");
            str.append("label=\"Ventanillas\";\n");
            str.append(listaVentanillas.graph());
            str.append("}");
            
            // Lista atendidos
            str.append("subgraph clusterListaAtendidos {\n");
            str.append("/* Atendidos */\n");
            str.append("label=\"Atendidos\";\n");
            str.append(listaAtendidos.graph());
            str.append("}");
            
            // Lista espera
            str.append("subgraph clusterListaEspera {\n");
            str.append("/* Lista Espera */\n");
            str.append("label=\"Lista de espera\";\n");
            str.append(listaEspera.graph());
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