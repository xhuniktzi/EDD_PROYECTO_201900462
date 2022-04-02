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
public class BTreeNode {
    public StaticLinkedList<Cliente> values = new StaticLinkedList<>(4);
    public StaticLinkedList<BTreeNode> nodes = new StaticLinkedList<>(5);
    public boolean isLeaf;
    
    public BTreeNode(){
        this.isLeaf = true;
    }
    
    public Cliente findByDpi(String dpi){
        StaticSimpleNode<Cliente> aux = this.values.head;
        while (aux != null){
            if (aux.value.dpi.equals(dpi))
                return aux.value;
            
            aux = aux.next;
        }
        return null;
    }
}
