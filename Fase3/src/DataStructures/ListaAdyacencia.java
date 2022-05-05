/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataStructures;

import Models.Lugar;
import Models.Mensajero;
import Models.Ruta;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Xhunik
 */
public class ListaAdyacencia {
    LinkedList<Lugar> lsLugares = new LinkedList<>();
    LinkedList<Ruta> lsRutas = new LinkedList<>();
    
    public void addLugar(Lugar l){
        lsLugares.addToEnd(l);
    }
    
    public void addRuta(Ruta r){
        lsRutas.addToEnd(r);
    }
    
    public void loadFromFileLugares(File f) throws FileNotFoundException, IOException, ParseException{
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj = (JSONObject) jsonParser.parse(new FileReader(f));
        JSONArray Lugares = (JSONArray) jsonObj.get("Lugares");
        Iterator<JSONObject> iterator = Lugares.iterator();
        while(iterator.hasNext()) {
            JSONObject l = iterator.next();
            Lugar lugar = new Lugar();
            lugar.id = (long) l.get("id");
            lugar.depto = (String) l.get("departamento");
            lugar.nombre = (String) l.get("nombre");
            lugar.sn_sucursal = (String) l.get("sn_sucursal");
            addLugar(lugar);
        }
    }
    
        public void loadFromFileRutas(File f) throws FileNotFoundException, IOException, ParseException{
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj = (JSONObject) jsonParser.parse(new FileReader(f));
        JSONArray Rutas = (JSONArray) jsonObj.get("Grafo");
        Iterator<JSONObject> iterator = Rutas.iterator();
        while(iterator.hasNext()) {
            JSONObject r = iterator.next();
            Ruta ruta = new Ruta();
            ruta.start = (long) r.get("inicio");
            ruta.end = (long) r.get("final");
            ruta.peso = (long) r.get("peso");
            addRuta(ruta);
        }
    }
    
    public String graphLugares(){
        StringBuilder str = new StringBuilder();
        str.append("digraph G{");
        if(!lsLugares.isVoid()){
            NodoSimple<Lugar> auxc = lsLugares.head;
            while(auxc != null){
                str.append(auxc.dato.id).append("[label=\"").append(auxc.dato.nombre)
                    .append("\n Departamento: ").append(auxc.dato.depto)
                        .append("\"];\n");

                if (auxc.siguiente != null)
                    str.append(auxc.dato.id).append("->").append(auxc.siguiente.dato.id).append(";\n");

                auxc = auxc.siguiente;
            }        
        }
        str.append("}");
        return str.toString();
    }
    
    public String graphRutas(){
        StringBuilder str = new StringBuilder();
        str.append("digraph G{\n");
        str.append(" node [shape=record];\n");
        if(!lsRutas.isVoid()){
            NodoSimple<Ruta> auxc = lsRutas.head;
            while(auxc != null){
                str.append("n").append(auxc.dato.start).append("c").append(auxc.dato.end).append("[label=\"{Peso: ")
                        .append(auxc.dato.peso).append("|Inicio: ").append(auxc.dato.start)
                        .append("| Fin: ").append(auxc.dato.end)
                        .append("|}\"];\n");

                if (auxc.siguiente != null)
                    str.append("n").append(auxc.dato.start).append("c").append(auxc.dato.end)
                            .append("->").append("n").append(auxc.siguiente.dato.start)
                            .append("c").append(auxc.siguiente.dato.end).append(";\n");

                auxc = auxc.siguiente;
            }        
        }
        str.append("}");
        return str.toString();
    }
    
}
