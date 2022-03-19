/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.btree;

import Modelos.Cliente;


/**
 *
 * @author Xhunik_Local
 */
public class BTreeClients {
    BTreeNodeClient root;
    int MinDeg;
    private final StringBuilder str = new StringBuilder();
    private int nodos;

    public BTreeClients(int deg){
        this.root = null;
        this.MinDeg = deg;
    }

    public void traverse(){
        if (root != null)
            root.traverse();
        System.out.println();
    }

    public BTreeNodeClient search(String key){
        return root == null ? null : root.search(key);
    }

    public void insert(Cliente key){
        if (root == null){

            root = new BTreeNodeClient(MinDeg,true);
            root.keys.setByIndex(0, key);
            root.num = 1;
        }
        else {
            if (root.num == 2*MinDeg-1){
                BTreeNodeClient s = new BTreeNodeClient(MinDeg,false);
                s.children.setByIndex(0, root);
                s.splitChild(0,root);
                int i = 0;
                if (s.keys.getByIndex(0).dpi.compareTo(key.dpi) < 0)
                    i++;
                s.children.getByIndex(i).insertNotFull(key);

                root = s;
            }
            else
                root.insertNotFull(key);
        }
    }

    public void remove(String key){
        if (root == null){
            System.out.println("The tree is empty");
            return;
        }
        root.remove(key);

        if (root.num == 0){ 
            if (root.isLeaf)
                root = null;
            else
                root = root.children.getByIndex(0);
        }
    }
    
    public void graph(){
        str.append("digraph ArbolB{\n").append("\nrankdir=TB;\n")
                .append("node[color=\"blue\",style=\"rounded,filled\",fillcolor=lightgray, shape=record];\n");
        
        graphIdentity(this.root);
        
        str.append("\n}\n");
        
        System.out.println(str.toString());
    }
    
    private void graphIdentity(BTreeNodeClient page){
        int contador = 0;
        if(page != null){
            this.nodos = 0;
            for (int i=0; i < page.num; i++){
                if (page.keys.getByIndex(i) != null){
                    this.nodos++;
                    if (i != 0)
                        str.append("|");
                    if(this.nodos == 1)
                        str.append("\nNodo").append(page.keys.getByIndex(i).dpi)
                                .append("[label=\"<f0> |");
                    
                    if (i == 0){
                        str.append("<f").append(i).append(1).append(">")
                                .append(page.keys.getByIndex(i).dpi).append("\\n")
                                .append(page.keys.getByIndex(i).nombre_cliente)
                                .append("|<f").append(i).append(2).append("> ");
                        
                        contador = 3;
                    } else {
                        str.append("<f").append(contador).append(">")
                                .append(page.keys.getByIndex(i).dpi)
                                .append("\\n").append(page.keys.getByIndex(i).nombre_cliente)
                                .append("|<f").append(contador).append(1).append("> ");
                        
                        contador += 2;
                    }
                    
                    if (i == page.num - 1){
                        contador = 0;
                        str.append(" \",group=0];\n");
                    }
                }
            }
        }
        if (page != null){
            graphIdentity(page.children.getByIndex(0));
            graphIdentity(page.children.getByIndex(1));
            graphIdentity(page.children.getByIndex(2));
            graphIdentity(page.children.getByIndex(3));
            graphIdentity(page.children.getByIndex(4));
        }
    }
    
    private void graphRelations(BTreeNodeClient page){
    
    }
}
