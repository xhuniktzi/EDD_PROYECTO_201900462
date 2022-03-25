/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fase2;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import procedures.loaders;
import procedures.traverseTrees;

/**
 *
 * @author Xhunik_Local
 */
public class App {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws org.json.simple.parser.ParseException
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {
        // TODO code application logic here
        loaders.cargaMasivaCapa("./Capas-AuxEDD.json");
        loaders.cargaMasivaImages("./Imagenes-AuxEDD.json");
//        traverseTrees.generateImagePosorden(3);
//        System.out.println(Globals.images.search(3).graph());
    }
    
}
