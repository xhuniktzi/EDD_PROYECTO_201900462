/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Edd.Proyecto.Fase2;
//
//import Estructuras.btree.BTreeClients;

/**
 *
 * @author Xhunik_Local
 */
public class Fase2 {
//    public static BTreeClients clients = new BTreeClients(2);
//    public static BinaryTree capas = new BinaryTree();
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws org.json.simple.parser.ParseException
     */
//    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {
//        // TODO code application logic here
////        System.out.println(cargaMasivaCapa().search(1).graph());
////        System.out.println(cargaMasivaCapa().graph());
////        System.out.println(cargaMasivaImages(cargaMasivaCapa()).graph());
//    }
//    
//    private static void cargaMasivaClientes() throws FileNotFoundException, IOException, ParseException{
//        BTreeClients b = new BTreeClients(2);
//        
//        JSONParser jsonParser = new JSONParser();
//        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./Clientes.json"));
//        Iterator<JSONObject> iterator = jsonArray.iterator();
//        while(iterator.hasNext()){
//            JSONObject cliente = iterator.next();
//            String dpi = (String) cliente.get("dpi");
//            String nombre_cliente = (String) cliente.get("nombre_cliente");
//            String password = (String) cliente.get("password");
//            b.insert(new Cliente(dpi, nombre_cliente, password));
//        }
//    }
//    
//    private static BinaryTree cargaMasivaCapa() throws FileNotFoundException, IOException, ParseException{
//        BinaryTree capas = new BinaryTree();
//        JSONParser jsonParser = new JSONParser();
//        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./Capas-AuxEDD.json"));
//        Iterator<JSONObject> iterator = jsonArray.iterator();
//        while(iterator.hasNext()) {
//            JSONObject capa = iterator.next();
//            long id_capa = (long) capa.get("id_capa");
//            JSONArray pixeles = (JSONArray) capa.get("pixeles");
//            Iterator<JSONObject> iterator2 = pixeles.iterator();
//            Matriz m = new Matriz((int) id_capa, "Capa");
//            while (iterator2.hasNext()){
//                JSONObject obj = iterator2.next();
//                long fila = (long) obj.get("fila");
//                long columna = (long) obj.get("columna");
//                String color = (String) obj.get("color");
//                m.insert((int) columna, (int) fila, color);
//            }
//            capas.insert(m);
//        }
//        return capas;
//    }
//    
//    private static AvlTree cargaMasivaImages(BinaryTree b) throws FileNotFoundException, IOException, ParseException {
//        AvlTree images = new AvlTree();
//        JSONParser jsonParser = new JSONParser();
//        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./Imagenes-AuxEDD.json"));
//        Iterator<JSONObject> iterator = jsonArray.iterator();
//        while(iterator.hasNext()) {
//            JSONObject image = iterator.next();
//            long id = (long) image.get("id");
//            JSONArray capas = (JSONArray) image.get("capas");
//            BinaryTree internalTree = new BinaryTree();
//            internalTree.id = (int) id;
//            capas.forEach(t -> {
//                Long idCapa = (Long) t;
//                Matriz m = b.search(idCapa.intValue());
//                internalTree.insert(m);
//            });
//            images.insert(internalTree);
//        }
//        return images;
//    }
//    
//    private static void generarDot(String name, String capa){
//        try (FileOutputStream out = new FileOutputStream(name + ".dot")) {
//            out.write(capa.getBytes());
//            out.close();
//            Runtime.getRuntime().exec("dot -Tpng " + name + ".dot -o " + name + ".png");
//        } catch (Exception ex){}
//    }
}
