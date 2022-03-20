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
public class Matriz {
    private final Header colsList; // coord x
    private final Header rowsList; // coord y
    
    public Matriz() {
        colsList = new Header();
        rowsList = new Header();
    }
    
    public void insert(int x, int y, String color){
        NodoMatriz newCell = new NodoMatriz(x, y, color);
        
        NodoHeader nodoCol = colsList.getHeader(x);
        if (nodoCol == null){
            nodoCol = new NodoHeader(x);
            colsList.setHeader(nodoCol);
            nodoCol.access = newCell;
        } else if (y < nodoCol.access.y) {
            newCell.down = nodoCol.access;
            nodoCol.access.up = newCell;
            nodoCol.access = newCell;
        } else {
            NodoMatriz aux = nodoCol.access;
            while (aux.down != null){
                if (y < aux.down.y){
                    newCell.down = aux.down;
                    aux.down.up = newCell;
                    aux.down = newCell;
                    newCell.up = aux;
                    break;
                }
                aux = aux.down;
            }
            
            if (aux.down == null){
                aux.down = newCell;
                newCell.up = aux;
            }
        }
        
        
        
        NodoHeader nodoFil = rowsList.getHeader(y);
        if (nodoFil == null){
            nodoFil = new NodoHeader(y);
            rowsList.setHeader(nodoFil);
            nodoFil.access = newCell;
        }  else if (x < nodoFil.access.x){
            newCell.next = nodoFil.access;
            nodoFil.access.prev = newCell;
            nodoFil.access = newCell;
        } else {
            NodoMatriz aux = nodoFil.access;
            while (aux.next != null) {
                if (x < aux.next.x) {
                    newCell.next = aux.next;
                    aux.next.prev = newCell;
                    aux.next = newCell;
                    newCell.prev = aux;
                    break;
                }
                aux = aux.next;
            }
            
            if (aux.next != null){
                aux.next = newCell;
                newCell.prev = aux;
            }
        }
    }
}
