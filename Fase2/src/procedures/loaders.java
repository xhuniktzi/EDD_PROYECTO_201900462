/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procedures;

import Estructuras.binario.BinaryTree;
import Estructuras.matriz.Matriz;
import Fase2.Globals;
import java.io.File;
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
public class loaders {
    public static void cargaMasivaCapa(File f) throws FileNotFoundException, IOException, ParseException{
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(f));
        Iterator<JSONObject> iterator = jsonArray.iterator();
        while(iterator.hasNext()) {
            JSONObject capa = iterator.next();
            long id_capa = (long) capa.get("id_capa");
            JSONArray pixeles = (JSONArray) capa.get("pixeles");
            Iterator<JSONObject> iterator2 = pixeles.iterator();
            Matriz m = new Matriz((int) id_capa, "Capa");
            while (iterator2.hasNext()){
                JSONObject obj = iterator2.next();
                long fila = (long) obj.get("fila");
                long columna = (long) obj.get("columna");
                String color = (String) obj.get("color");
                m.insert((int) columna, (int) fila, color);
            }
            Globals.capas.insert(m);
        }
    }
    
    public static void cargaMasivaImages(File f) throws FileNotFoundException, IOException, ParseException{
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(f));
        Iterator<JSONObject> iterator = jsonArray.iterator();
        while(iterator.hasNext()) {
            JSONObject image = iterator.next();
            long id = (long) image.get("id");
            JSONArray capas = (JSONArray) image.get("capas");
            BinaryTree internalTree = new BinaryTree();
            internalTree.id = (int) id;
            capas.forEach(t -> {
                Long idCapa = (Long) t;
                Matriz m = Globals.capas.search(idCapa.intValue());
                internalTree.insert(m);
            });
            Globals.images.insert(internalTree);
        }
    }
    
    public static void generarDot(String name, String capa){
        try (FileOutputStream out = new FileOutputStream(name + ".dot")) {
            out.write(capa.getBytes());
            out.close();
            Runtime.getRuntime().exec("dot -Tpng " + name + ".dot -o " + name + ".png");
        } catch (Exception ex){}
    }
}
