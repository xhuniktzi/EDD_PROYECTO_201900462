/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Edd.Proyecto.Fase2.Fase2;

/**
 *
 * @author Xhunik_Local
 */
public class BTreeClients {
    public BTreeNodeClient root;
    public int grado = Fase2.grado;

    public BTreeClients() {
        this.root = null;
    }
    
    public void recorrer(){
        if (this.root != null)
            this.root.recorrer();
        System.out.println();
    }
    
    public BTreeNodeClient buscar(int k){
        if (this.root == null)
            return null;
        else
            return this.root.buscar(k);
    }
}
