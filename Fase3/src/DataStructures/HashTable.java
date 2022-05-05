/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataStructures;

import Models.Mensajero;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Xhunik
 */
public class HashTable {
    private int count = 0;
    private Mensajero[] arrayMensajeros = new Mensajero[37];
    
    
    private long sparseFunctionPrimary(String key){
        BigInteger dpi = new BigInteger(key);
        return dpi.mod(BigInteger.valueOf(arrayMensajeros.length)).longValue();
    }
    
    private long sparseFunctionSecondary(String key, int i){
        BigInteger dpi = new BigInteger(key);
        return ((dpi.mod(BigInteger.valueOf(7)).longValue()) + 1) * i;
    }
    
    public Mensajero get(String dpi){
        int pos = (int) sparseFunctionPrimary(dpi);
        Mensajero element = arrayMensajeros[pos];
        
        if (element != null && element.DPI.equals(dpi)){
            return element;
        }
        else{
            int i = 1;
            do {
                int posAux = (int) sparseFunctionSecondary(dpi, i);
                pos = pos + posAux;
                if (pos >= arrayMensajeros.length){
                    pos = pos % arrayMensajeros.length;
                }
                
                Mensajero elementAux = arrayMensajeros[pos];
                if (elementAux != null && elementAux.DPI.equals(dpi)){
                    return elementAux;
                }
                i++;
            } while (true);
        }
        
    }
    
    public void insert(Mensajero obj){
        int pos = (int) sparseFunctionPrimary(obj.DPI);
        Mensajero element = arrayMensajeros[pos];
        if (element == null){
            arrayMensajeros[pos] = obj;            
        } else {
            int i = 1;

            do {
                int posAux = (int) sparseFunctionSecondary(obj.DPI, i);
                pos = pos + posAux;
                if (pos >= arrayMensajeros.length){
                    pos = pos % arrayMensajeros.length;
                }
                
                Mensajero elementAux = arrayMensajeros[pos];
                if (elementAux == null){
                    arrayMensajeros[pos] = obj;
                    break;
                }
                i++;
            } while (true);
        }
        
        count++;
        if ( ocupacion() > 0.75){
            reSizeHashTable();
        }
    }
    
    private double ocupacion(){
        return ((double) count) / ((double) arrayMensajeros.length);
    }
    
    public void printArray(){
        for (int i = 0; i < this.arrayMensajeros.length; i++) {
            if (this.arrayMensajeros[i] != null)
                System.out.println("Posición: "+ i + ", value: " + this.arrayMensajeros[i].DPI);
            else
                System.out.println("Posición: "+ i + ", value: null");
        }
        System.out.println("Cantidad: " + count + "/" + arrayMensajeros.length + ", porcentaje de ocupación: " + ocupacion());
    }
    
    private void reSizeHashTable(){
        Mensajero[] aux = this.arrayMensajeros.clone();
        
        this.arrayMensajeros = new Mensajero[nextPrimo(arrayMensajeros.length)];
        this.count = 0;
        
        for (Mensajero mensajero : aux) {
            if (mensajero != null)
                this.insert(mensajero);
        }
    }
    
    private int nextPrimo(int n){
        n++;
        while(!esPrimo(n)){
            n++;
        }
        return n;
    }
    
    private boolean esPrimo(int n){
        for (int i = 2; i < n; i++) {
            if (n % i == 0)
                return false;
        }
        return n != 1;
    }
    
    public void loadFromFile(File f) throws FileNotFoundException, IOException, ParseException{
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(f));
        Iterator<JSONObject> iterator = jsonArray.iterator();
        while(iterator.hasNext()) {
            JSONObject m = iterator.next();
            Mensajero newMensajero = new Mensajero();
            newMensajero.DPI = (String) m.get("dpi");
            newMensajero.Nombres = (String) m.get("nombres");
            newMensajero.Apellidos = (String) m.get("apellidos");
            newMensajero.typeLiscense = (String) m.get("tipo_licencia");
            newMensajero.genero = (String) m.get("genero");
            newMensajero.direccion = (String) m.get("direccion");
            newMensajero.telefono = (String) m.get("telefono");
            this.insert(newMensajero);
        }
    }
    
    public String graph() {
        StringBuilder str = new StringBuilder();
        str.append("""
                   digraph G{ nodesep=.05;
                   rankdir=LR;
                   node [shape=record,width=.1,height=.1];
                   node [width = 1.5];""");
        
        for (int i = 0; i < arrayMensajeros.length; i++) {
            Mensajero m = arrayMensajeros[i];
            str.append("node").append(i).append(" [label = \"{")
                    .append("n").append(i);
            
                    if (m != null){
                        str.append("|").append(m.DPI)
                        .append("|Nombre: ").append(m.Nombres)
                        .append("|Apellido: ").append(m.Apellidos);
                    } else {
                        str.append("|null")
                        .append("|null ")
                        .append("|null");
                    }
                    
                    str.append("| }\"];\n");
        }
        
        str.append("}");
        return str.toString();
    }
}
