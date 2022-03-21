/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Edd.Proyecto.Fase2;

import Estructuras.btree.BTreeClients;
import Estructuras.matriz.Matriz;
import Modelos.Cliente;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Xhunik_Local
 */
public class Fase2 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws org.json.simple.parser.ParseException
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {
        // TODO code application logic here
        cargaMasivaCapa();
        cargaMasivaClientes();

    }
    
    private static void cargaMasivaClientes() throws FileNotFoundException, IOException, ParseException{
        BTreeClients b = new BTreeClients(2);
        
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./Clientes.json"));
        Iterator<JSONObject> iterator = jsonArray.iterator();
        while(iterator.hasNext()){
            JSONObject cliente = iterator.next();
            String dpi = (String) cliente.get("dpi");
            String nombre_cliente = (String) cliente.get("nombre_cliente");
            String password = (String) cliente.get("password");
            b.insert(new Cliente(dpi, nombre_cliente, password));
        }
        generarDot("BTreeClients", b.graph());
    }
    
    private static void cargaMasivaCapa() throws FileNotFoundException, IOException, ParseException{
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./ImagenMario.json"));
        Iterator<JSONObject> iterator = jsonArray.iterator();
        while(iterator.hasNext()) {
            JSONObject capa = iterator.next();
            long id = (long) capa.get("id_capa");
            JSONArray pixeles = (JSONArray) capa.get("pixeles");
            Iterator<JSONObject> iterator2 = pixeles.iterator();
            
            Matriz m = new Matriz((int) id, "Capa");
            while (iterator2.hasNext()){
                JSONObject obj = iterator2.next();
                long fila = (long) obj.get("fila");
                long columna = (long) obj.get("columna");
                String color = (String) obj.get("color");
//                System.out.println("F: " + fila + " C: " + columna + " D: " + color);
                m.insert((int) columna, (int) fila, color);
            }
            
            generarDot("capa" + id, m.graph());
        }
    }
    private static void generarDot(String name, String capa){
        try (FileOutputStream out = new FileOutputStream(name + ".dot")) {
            out.write(capa.getBytes());
            out.close();
            Runtime.getRuntime().exec("dot -Tjpg " + name + ".dot -o " + name + ".jpg");
        } catch (Exception ex){}
    }
}
