/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.binario;

import Estructuras.matriz.Matriz;

/**
 *
 * @author Xhunik_Local
 */
public class BinaryTree {
    public int id;
    private BinaryNode root;
    
    public BinaryTree(){
        this.root = null;
    }
    
    public void insert(Matriz m){
        if (root == null)
            root = new BinaryNode(m);
        else
            root.insert(m);
    }
}
