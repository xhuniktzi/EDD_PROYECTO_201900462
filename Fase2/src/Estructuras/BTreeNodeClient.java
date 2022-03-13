/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Edd.Proyecto.Fase2.Fase2;
import Modelos.Cliente;

/**
 *
 * @author Xhunik_Local
 */
public class BTreeNodeClient {
    ListaEnlazadaSimple<Cliente> keys; // valores
    int grado = Fase2.grado; //Grado
    ListaEnlazadaSimple<BTreeNodeClient> C; // Array de hijos
    int n; // Numero de keys actuales
    boolean isLeaf; // verdadero si es hoja, falso si no
    
    public BTreeNodeClient(boolean isLeaf) {
        this.isLeaf = isLeaf;
        this.keys = new ListaEnlazadaSimple<>(2 * grado - 1);
        this.C = new ListaEnlazadaSimple<>(2 * grado);
        this.n = 0;
    }
    
    public void recorrer() {
        int i;
        for (i = 0; i < this.n; i++) {
            if (this.isLeaf == false) {
                C.getByIndex(i).recorrer();
            }
            System.out.print(keys.getByIndex(i).dpi+ " ");
        }
        if (isLeaf == false)
            C.getByIndex(i).recorrer();
    }
    
    public BTreeNodeClient buscar(int k){
        int i = 0;
        while (i < n && k > keys.getByIndex(i).dpi)
            i++;
        
        if (keys.getByIndex(i).dpi == k)
            return this;
        
        if (isLeaf)
            return null;
        
        return C.getByIndex(i).buscar(k);
    }
}
