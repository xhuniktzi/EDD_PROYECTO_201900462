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
    public String name;
    private final Header colsList; // coord x
    private final Header rowsList; // coord y
    
    public Matriz(String name) {
        this.name = name;
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
    
    public String graph(){
        StringBuilder str = new StringBuilder();
        
        
        
        str.append("digraph Sparce_Matrix {\n").append("node [shape=box];\n");
        str.append("Mt[ label =\"").append(this.name)
                .append("\", width = 1.5, style = filled, fillcolor = firebrick1, group = 1 ];\n");
        str.append("e0[ shape = point, width = 0 ];\n").append("e1[ shape = point, width = 0 ];\n");
        
        str.append(this.graphRowsNodes());
        str.append(this.graphRowsRelations());
        str.append(this.graphColsNodes());
        str.append(this.graphColsRelations());
        str.append("Mt -> U0; Mt -> A0;\n");
        str.append(this.graphColHeadersRank());
        
        str.append("\n}");
        
        return str.toString();
    }
    
    
    private String graphRowsNodes(){
        int countrows = 0;
        StringBuilder str = new StringBuilder();
        NodoHeader auxr = rowsList.head;
        while (auxr != null){
            str.append("U").append(countrows).append("[label =\"")
                    .append(auxr.pos).append("\"");
            if (countrows == 0){
                str.append(" pos = \"5.3,3.5!\" ");
            }
            str.append("width = 1.5 style = filled, fillcolor = lightskyblue, group =  1 ];")
                    .append("\n");
            
            
            countrows++;
            auxr = auxr.next;
        }
        return str.toString();
    }
    
    private String graphRowsRelations(){
        int countRowAux = 0;
        StringBuilder str = new StringBuilder();
        NodoHeader auxr = rowsList.head;
        while (auxr != null){
            if (auxr.next != null){
                str.append("U").append(countRowAux).append(" -> ")
                        .append("U").append(countRowAux + 1).append("\n");
            }
            if (auxr.prev != null){
                str.append("U").append(countRowAux).append(" -> ")
                        .append("U").append(countRowAux - 1).append("\n");
            }
            countRowAux++;
            auxr = auxr.next;
        }
        
        return str.toString();
    }
    
    private String graphColsNodes(){
        StringBuilder str = new StringBuilder();
        int countcols = 0;
        NodoHeader auxc = colsList.head;
        while (auxc != null){
            str.append("A").append(countcols).append("[label =\"")
                    .append(auxc.pos).append("\"").append("width = 1.5 style = filled, fillcolor = bisque1, group =")
                    .append(countcols + 2)
                    .append(" ];").append("\n");
            countcols++;
            auxc = auxc.next;
        }
        
        return str.toString();
    }
    
    private String graphColsRelations(){
        StringBuilder str = new StringBuilder();
        int countColAux = 0;
        NodoHeader auxc = colsList.head;
        while (auxc != null){
            if (auxc.next != null){
                str.append("A").append(countColAux).append(" -> ")
                        .append("A").append(countColAux + 1).append("\n");
            }
            if (auxc.prev != null){
                str.append("A").append(countColAux).append(" -> ")
                        .append("A").append(countColAux - 1).append("\n");
            }
            countColAux++;
            auxc = auxc.next;
        }
        
        return str.toString();
    }
    
    private String graphColHeadersRank(){
        StringBuilder str = new StringBuilder();
        int countColAuxRank = 0;
        NodoHeader auxc = colsList.head;
        str.append("{ rank = same; Mt;");
        while (auxc != null){
            str.append("A").append(countColAuxRank).append(";");
            
            countColAuxRank++;
            auxc = auxc.next;
        }
        str.append("}");
        
        return str.toString();
    }
    
    private String graphInternalNodes(){
        StringBuilder str = new StringBuilder();
        int col = 0;
        int row = 0;
        
        NodoHeader auxr = rowsList.head;
        NodoHeader auxc = colsList.head;
        
        while (auxr != null){
            while (auxc != null){
                
            }
            auxr = auxr.next;
        }
        
        return str.toString();
    }
}
