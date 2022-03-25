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
    
    public BinaryTree search(int id){
        if (value.id == id){
            return this.value;
        }
        else {
            if (id < value.id){
                if (this.left == null)
                    return null;
                else
                    return this.left.search(id);
            }
            else if (id > value.id){
                if (this.right == null)
                    return null;
                else
                    return this.right.search(id);
            }
        }
        return null;
    }
    
    public String graph(){
        StringBuilder str = new StringBuilder();
        str.append("digraph G { rankdir=TB; node [shape = record, style=filled, fillcolor=seashell2];\n");
        str.append(exploreTree());
        str.append("}\n");
        return str.toString();
    }
    
    public String exploreTree(){
        StringBuilder str = new StringBuilder();
        if (left == null && right == null)
            str.append("node").append(value.id).append(" [ label =\"").append(value.id).append("\"];\n");
        else
            str.append("node").append(value.id).append("  [ label =\"<C0>|").append(value.id).append("|<C1>\"];\n");
        
        if (left != null){
            str.append(left.exploreTree()).append("node").append(value.id)
                    .append(":C0->node").append(left.value.id).append("\n");
        }
        
        if (right != null){
            str.append(right.exploreTree()).append("node").append(value.id)
                    .append(":C1->node").append(right.value.id).append("\n");
        }
        
        return str.toString();
    }
}
