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
public class AvlTree {
    public AvlNode root;
    
    public AvlTree(){
        this.root = null;
    }
    
    public void insert(BinaryTree b){
        this.root = insert(b, this.root);
    }
    
    public String graph(){
        return root.graph();
    }
    
    private AvlNode insert(BinaryTree b, AvlNode r){
        if (r == null) {
            r = new AvlNode(b);
        } else if (b.id < r.value.id){
            r.left = insert(b, r.left);
            if (height(r.right) - height(r.left) == -2)
                if (b.id < r.left.value.id)
                    r = rotateLeftLeft(r);
                else
                    r  =rotateLeftRight(r);
        } else if (b.id > r.value.id){
            r.right = insert(b, r.right);
            if(height(r.right) - height(r.left) == 2)
                if (b.id < r.right.value.id)
                    r = rotateRightRight(r);
                else
                    r  = rotateRightLeft(r);
        } else {
            r.value = b;
        }
        
        r.height = major(height(r.left), height(r.right)) + 1;
        return r;
    }
    
    private int height( AvlNode nodo )
    {
        if(nodo==null)
            return -1;
        else
            return nodo.height;
    }
    
    private int major(int node1, int node2)
    {
        if(node1 > node2)
            return node1;
        return node2;
    }
    
    
    private AvlNode rotateLeftLeft(AvlNode nodeInput){
        AvlNode nodeAux = nodeInput.left;
        nodeInput.left = nodeAux.right;
        nodeAux.right = nodeInput;
        nodeInput.height = major(height(nodeInput.left), height(nodeInput.right)) + 1;  
        nodeAux.height = major(height(nodeAux.left), nodeInput.height) + 1;
        return nodeAux;
    }
    
    private AvlNode rotateRightRight(AvlNode nodeInput){
        AvlNode nodeAux = nodeInput.right;
        nodeInput.right = nodeAux.left;
        nodeAux.left = nodeInput;
        nodeInput.height = major(height(nodeInput.left), height(nodeInput.right)) + 1;  
        nodeAux.height = major(height(nodeAux.right), nodeInput.height) + 1;
        return nodeAux;
    }
    
    private AvlNode rotateLeftRight(AvlNode nodeInput){
        nodeInput.left = rotateRightRight(nodeInput.left);
        return rotateLeftLeft(nodeInput);
    }
    private AvlNode rotateRightLeft(AvlNode nodeInput){
        nodeInput.right = rotateRightRight(nodeInput.right);
        return rotateRightRight(nodeInput);
    }
}
