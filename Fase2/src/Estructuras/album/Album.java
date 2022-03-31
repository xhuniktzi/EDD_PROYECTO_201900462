/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.album;

import Estructuras.binario.BinaryTree;


/**
 *
 * @author Xhunik_Local
 */
public class Album {
    public String nombre_album;
    public ListaImagenes imgs;
    
    public Album(){
        this.imgs = new ListaImagenes();
    }
    
    public void addImage(BinaryTree img){
        imgs.addToEnd(img);
    }
}
