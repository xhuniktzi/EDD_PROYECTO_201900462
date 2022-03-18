/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Modelos.Cliente;


/**
 *
 * @author Xhunik_Local
 */
public class BTreeClients {
    BTreeNodeClient root;
    int MinDeg;

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
}
