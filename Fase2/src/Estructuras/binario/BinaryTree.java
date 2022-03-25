/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.binario;

import Estructuras.linkedlist.LinkedList;
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
    
    public Matriz search(int id){
        return this.root.search(id);
    }
    
    public String graph(){
        return root.graph();
    }
    
    public LinkedList<Matriz> preorden(){
        LinkedList<Matriz> lm = new LinkedList<>();
        preorden(lm, this.root);
        return lm;
    }
    
    private void preorden(LinkedList<Matriz> lm, BinaryNode node){
        if (node == null) return;
        lm.addToEnd(node.value);
        preorden(lm, node.left);
        preorden(lm, node.right);
    }
    
    public LinkedList<Matriz> inorden(){
        LinkedList<Matriz> lm = new LinkedList<>();
        inorden(lm, this.root);
        return lm;
    }
    
    private void inorden(LinkedList<Matriz> lm, BinaryNode node){
        if (node == null) return;
        inorden(lm, node.left);
        lm.addToEnd(node.value);
        inorden(lm, node.right);
    }
    
    public LinkedList<Matriz> posorden(){
        LinkedList<Matriz> lm = new LinkedList<>();
        posorden(lm, this.root);
        return lm;
    }
    
    private void posorden(LinkedList<Matriz> lm, BinaryNode node){
        if (node == null) return;
        posorden(lm, node.left);
        posorden(lm, node.right);
        lm.addToEnd(node.value);
    }
}
