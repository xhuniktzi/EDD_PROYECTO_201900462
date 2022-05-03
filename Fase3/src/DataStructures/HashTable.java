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
    private int size = 37;
    private int count = 0;
    private Mensajero[] arrayMensajeros = new Mensajero[size];
    
    private long sparseFunctionPrimary(long key){
        return key % size;
    }
    
    private long sparseFunctionSecondary(long key, int i){
        return ((key % 7) + 1) * i;
    }
    
    public void insert(Mensajero obj){
        int pos = (int) sparseFunctionPrimary(Long.valueOf(obj.DPI));
        Mensajero element = arrayMensajeros[pos];
        if (element == null){
            arrayMensajeros[pos] = obj;
            
        } else {
            int i = 1;
            int newPos = pos;
            do {
                int posAux = (int) sparseFunctionSecondary(Long.valueOf(obj.DPI), i);
                newPos = pos + posAux;
                if (newPos >= size){
                    newPos = newPos - size;
                }
                
                Mensajero elementAux = arrayMensajeros[newPos];
                if (elementAux == null){
                    arrayMensajeros[newPos] = obj;
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
        return ((double) count) / ((double) size);
    }
    
    public void printArray(){
        for (int i = 0; i < this.arrayMensajeros.length; i++) {
            if (this.arrayMensajeros[i] != null)
                System.out.println("Posición: "+ i + ", value: " + this.arrayMensajeros[i].DPI);
            else
                System.out.println("Posición: "+ i + ", value: null");
        }
        System.out.println("Cantidad: " + count + "/" + size + ", porcentaje de ocupación: " + ocupacion());
    }
    
    private void reSizeHashTable(){
        Mensajero[] aux = this.arrayMensajeros.clone();
        
        this.size = nextPrimo(size);
        this.arrayMensajeros = new Mensajero[this.size];
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
}
