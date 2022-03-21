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
public class BinaryNode {
    public Matriz value;
    public BinaryNode left;
    public BinaryNode right;
    
    public BinaryNode(Matriz value){
        this.value = value;
        this.left = this.right = null;
    }
    
    public void insert(Matriz m){
        if (m.id < value.id){
            if (left == null)
                left = new BinaryNode(m);
            else
                left.insert(m);
        } else if (m.id > value.id){
            if (right == null)
                right = new BinaryNode(m);
            else
                right.insert(m);
        } else {
            value = m;
        }
    }
}
