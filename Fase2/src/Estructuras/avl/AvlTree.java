/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.avl;

import Estructuras.binario.BinaryTree;
import Estructuras.linkedlist.LinkedList;

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
        this.root = insert(this.root, b);
    }
    
    public String graph(){
        return root.graph();
    }
    
    public BinaryTree search(int id){
        return this.root.search(id);
    }
    
    public void delete(int id){
        this.delete(this.root, id);
    }
    
    public AvlNode delete(AvlNode node, int id){
        if (node == null)
            return node;
        
        if (id < node.value.id){
            node.left = this.delete(node.left, id);
        }
        else if (id > node.value.id){
            node.right = this.delete(node.right, id);
        }
        else {
            if (node.left == null || node.right == null){
                AvlNode aux = null;
                if (aux == node.left)
                    aux = node.right;
                else
                    aux = node.left;
                
                if (aux == null){
                    aux = node;
                    node = null;
                } else {
                    node = aux;
                }  
            } else {
                AvlNode aux = minValueNode(node.right);
                node.value = aux.value;
                node.right = delete(node.right, aux.value.id);
            }
        }
        
        if (node == null)
            return null;
        
        
        root.height = major(height(node.left), height(node.right)) + 1;
        int balance = balance(node);
        
        if (balance > 1 && balance(node.left) >= 0)
            return rotateRight(node);
        if (balance > 1 && balance(node.left) < 0)
        {}
        
        if (balance < -1 && balance(node.right) <= 0)
            return rotateLeft(node);
        if (balance < -1 && balance(node.right) > 0)
        {}
        
        return node;
    }
    
    private AvlNode minValueNode(AvlNode node)
    {
        AvlNode aux = node;
 
        while (aux.left != null)
           aux = aux.left;
        return aux;
    }
    
    int balance(AvlNode node)
    {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }
    
    public LinkedList<BinaryTree> preorden(){
        LinkedList<BinaryTree> lb = new LinkedList<>();
        preorden(lb, this.root);
        return lb;
    }
    
    private void preorden(LinkedList<BinaryTree> lm, AvlNode node){
        if (node == null) return;
        lm.addToEnd(node.value);
        preorden(lm, node.left);
        preorden(lm, node.right);
    }
    
    public LinkedList<BinaryTree> inorden(){
        LinkedList<BinaryTree> lb = new LinkedList<>();
        inorden(lb, this.root);
        return lb;
    }
    
    private void inorden(LinkedList<BinaryTree> lm, AvlNode node){
        if (node == null) return;
        inorden(lm, node.left);
        lm.addToEnd(node.value);
        inorden(lm, node.right);
    }
    
    public LinkedList<BinaryTree> posorden(){
        LinkedList<BinaryTree> lb = new LinkedList<>();
        posorden(lb, this.root);
        return lb;
    }
    
    private void posorden(LinkedList<BinaryTree> lm, AvlNode node){
        if (node == null) return;
        posorden(lm, node.left);
        posorden(lm, node.right);
        lm.addToEnd(node.value);
    }
    
    private AvlNode insert(AvlNode r, BinaryTree b){
        if (r == null) {
            return new AvlNode(b);
        }
        if (b.id < r.value.id)
            r.left = insert(r.left, b);
        else if (b.id > r.value.id)
            r.right = insert(r.right, b);
        else
            return r;
       
        r.height = major(height(r.left), height(r.right)) + 1;
        
        int balance = balance(r);
        
        if (balance > 1 && b.id < r.left.value.id)
            return rotateRight(r);
        
        if (balance < -1 && b.id > r.right.value.id)
            return rotateLeft(r);
        
        if (balance > 1 && b.id > r.left.value.id){
            r.left = rotateLeft(r.left);
            return rotateRight(r);
        }
            
        
        if (balance < -1 && b.id < r.right.value.id){
            r.right = rotateRight(r.right);
            return rotateLeft(r);            
        }
            
        
        return r;
    }
    
    private int height( AvlNode nodo )
    {
        if(nodo==null)
            return 0;
        else
            return nodo.height;
    }
    
    private int major(int node1, int node2)
    {
        if(node1 > node2)
            return node1;
        return node2;
    }
    
    
    private AvlNode rotateRight(AvlNode nodeInput){
        AvlNode nodeAux = nodeInput.left;
        AvlNode nodeAux2 = nodeAux.right;
        
        nodeAux.right = nodeInput;
        nodeInput.left = nodeAux2;
        
        nodeInput.height = major(height(nodeInput.left), height(nodeInput.right)) + 1;
        nodeAux.height = major(height(nodeAux.left), nodeInput.height) + 1;
        return nodeAux;
    }
    
    private AvlNode rotateLeft(AvlNode nodeInput){
        AvlNode nodeAux = nodeInput.right;
        AvlNode nodeAux2 = nodeAux.left;
        
        nodeAux.left = nodeInput;
        nodeInput.right = nodeAux2;
        
        nodeInput.height = major(height(nodeInput.left), height(nodeInput.right)) + 1;
        nodeAux.height = major(height(nodeAux.left), nodeInput.height) + 1;
        return nodeAux;
    }
    
//    private AvlNode rotateLeftRight(AvlNode nodeInput){
//        nodeInput.left = rotateLeft(nodeInput.left);
//        return rotateRight(nodeInput);
//    }
//    
//    private AvlNode rotateRightLeft(AvlNode nodeInput){
//        nodeInput.right = rotateRight(nodeInput.right);
//        return rotateLeft(nodeInput);
//    }
    
//    private AvlNode rotateLeftRight(AvlNode nodeInput){
//        nodeInput.left = rotateRightRight(nodeInput.left);
//        return rotateLeftLeft(nodeInput);
//    }
//    private AvlNode rotateRightLeft(AvlNode nodeInput){
//        nodeInput.right = rotateRightRight(nodeInput.right);
//        return rotateRightRight(nodeInput);
//    }
}
