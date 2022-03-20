/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.matriz;

/**
 *
 * @author Xhunik_Local
 */
public class NodoHeader {
    public NodoHeader next;
    public NodoHeader prev;
    public int pos;
    public  NodoMatriz access;
    
    public NodoHeader(int pos) {
        this.next = null;
        this.prev = null;
        this.access = null;
        this.pos = pos;
    }
}
