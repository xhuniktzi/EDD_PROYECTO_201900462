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
public class NodoMatriz {
    public int x;
    public int y;
    public String color;
    
    public NodoMatriz next;
    public NodoMatriz prev;
    public NodoMatriz up;
    public NodoMatriz down;
    
    public NodoMatriz(int x, int y, String color){
        this.x = x;
        this.y = y;
        this.color = color;
    }
}
