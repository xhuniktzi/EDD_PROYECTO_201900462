/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.avl;

import Estructuras.binario.BinaryTree;

/**
 *
 * @author Xhunik_Local
 */
public class AvlNode {
    public BinaryTree value;
    public AvlNode left;
    public AvlNode right;
    
    public int height;   
    
    public AvlNode(BinaryTree value){
        this.value = value;
        this.left = this.right = null;
    }
    
    public void insert(BinaryTree b){
        if (b.id < value.id){
            if (this.left == null)
                this.left = new AvlNode(b);
            else
                this.left.insert(b);
        }
        else if (b.id > value.id){
            if (this.right == null)
                this.right = new AvlNode(b);
            else
                this.right.insert(b);
        }
        else {
            value = b;
        }
            
    }
}
