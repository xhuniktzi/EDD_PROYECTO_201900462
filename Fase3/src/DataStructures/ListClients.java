/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataStructures;

import Models.Cliente;
import at.favre.lib.crypto.bcrypt.BCrypt;
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
public class ListClients extends LinkedList<Cliente> {
    public void insertClient(Cliente c){
        Cliente findCliente = findByUsername(c.username);
        if (findCliente != null)
            return;
        
        String password = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(6, c.password.toCharArray());
        c.password = password;
        this.addToEnd(c);
    }
    
    public Cliente findByUsername(String username){
        NodoSimple<Cliente> aux = this.head;
        
        while (aux != null){
            if (aux.dato.username.equals(username))
                return aux.dato;
            
            aux = aux.siguiente;
        }
        
        return null;
    }
    
    public boolean checkIfClientOk(String username, String password){
        Cliente findCliente = findByUsername(username);
        
        BCrypt.Result resultStrict = BCrypt.verifyer(BCrypt.Version.VERSION_2Y).verifyStrict(password.toCharArray(),findCliente.password.toCharArray());
        return resultStrict.verified;
    }
    
    public void loadFromFile(File f) throws FileNotFoundException, IOException, ParseException{
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(f));
        Iterator<JSONObject> iterator = jsonArray.iterator();
        while(iterator.hasNext()) {
            JSONObject c = iterator.next();
            Cliente newCliente = new Cliente();
            newCliente.DPI = (String) c.get("dpi");
            newCliente.NombreCompleto = (String) c.get("nombre_completo");
            newCliente.username = (String) c.get("nombre_usuario");
            newCliente.mail = (String) c.get("correo");
            newCliente.password = (String) c.get("contrasenia");
            newCliente.phone = (String) c.get("telefono");
            newCliente.direction = (String) c.get("direccion");
            newCliente.id_municipio = (long) c.get("id_municipio");
            this.insertClient(newCliente);
        }
    }
}
