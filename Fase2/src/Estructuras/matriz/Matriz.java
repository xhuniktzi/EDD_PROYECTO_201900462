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
    private final Header colsList;
    private final Header rowsList;
    
    public Matriz(String name) {
        this.name = name;
        colsList = new Header();
        rowsList = new Header();
    }
    public void printCols(){
        System.out.println("Imprimir por columnas");
        NodoHeader auxc = colsList.head;
        while (auxc != null) {
            System.out.println("Columna: " + auxc.pos + " -> ");
            NodoMatriz aux = auxc.access;
            while (aux != null){
                System.out.println("X: " + aux.x + " Y: "+ aux.y + ". ");
                aux = aux.down;
            }
            auxc = auxc.next;
            System.out.println();
        }
    }
    
    public void printRows(){
        System.out.println("Imprimir por filas");
        NodoHeader auxr = rowsList.head;
        while (auxr != null) {
            System.out.println("Fila: " + auxr.pos + " -> ");
            NodoMatriz aux = auxr.access;
            while (aux != null){
                System.out.println("X: " + aux.x + " Y: "+ aux.y + ". ");
                aux = aux.next;
            }
            auxr = auxr.next;
            System.out.println();
        }
    }

   
    public void insert(int x, int y, String color){
        NodoMatriz newCell = new NodoMatriz(x, y, color);
        
        NodoMatriz check = this.returnIfExists(x, y);
        if (check != null){
            check.color = color;
            return;
        }
        
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
        } else if (x < nodoFil.access.x){
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

            if (aux.next == null){
                aux.next = newCell;
                newCell.up = aux;
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
        str.append("Mt -> U").append(rowsList.head.pos).append(";\n");
        str.append("Mt -> A").append(colsList.head.pos).append(";\n");
        str.append(this.graphHeaderRanks());
        str.append(this.graphInternalNodes());
        str.append(this.graphInternalRelations());
        str.append("\n}");
        
        return str.toString();
    }
    
    
    private NodoMatriz returnIfExists(int x, int y){
        NodoHeader headRow = this.rowsList.getHeader(y);
        if (headRow == null)
            return null;
        NodoHeader headCol = this.colsList.getHeader(x);
        if (headCol == null)
            return null;
        
        NodoMatriz aux = headRow.access;
        while (aux != null){
            if (aux.x == x)
                return aux;
            
            aux = aux.next;
        }
        
        return null;
    }
    
    private String graphRowsNodes(){
        StringBuilder str = new StringBuilder();
        NodoHeader auxr = rowsList.head;
        while (auxr != null){
            str.append("U").append(auxr.pos).append("[label =\"")
                    .append(auxr.pos).append("\"");
            str.append("width = 1.5 style = filled, fillcolor = lightskyblue, group =  1 ];")
                    .append("\n");
            auxr = auxr.next;
        }
        return str.toString();
    }
    
    private String graphRowsRelations(){
        StringBuilder str = new StringBuilder();
        NodoHeader auxr = rowsList.head;
        while (auxr != null){
            if (auxr.next != null){
                str.append("U").append(auxr.pos).append(" -> ")
                        .append("U").append(auxr.next.pos).append(";\n");
            }
            if (auxr.prev != null){
                str.append("U").append(auxr.pos).append(" -> ")
                        .append("U").append(auxr.prev.pos).append(";\n");
            }
            auxr = auxr.next;
        }
        
        return str.toString();
    }
    
    private String graphColsNodes(){
        StringBuilder str = new StringBuilder();
        NodoHeader auxc = colsList.head;
        while (auxc != null){
            str.append("A").append(auxc.pos).append("[label =\"")
                    .append(auxc.pos).append("\"").append("width = 1.5 style = filled, fillcolor = bisque1, group =")
                    .append(auxc.pos + 2)
                    .append(" ];").append("\n");
            auxc = auxc.next;
        }
        
        return str.toString();
    }
    
    private String graphColsRelations(){
        StringBuilder str = new StringBuilder();
        NodoHeader auxc = colsList.head;
        while (auxc != null){
            if (auxc.next != null){
                str.append("A").append(auxc.pos).append(" -> ")
                        .append("A").append(auxc.next.pos).append(";\n");
            }
            if (auxc.prev != null){
                str.append("A").append(auxc.pos).append(" -> ")
                        .append("A").append(auxc.prev.pos).append(";\n");
            }
            auxc = auxc.next;
        }
        
        return str.toString();
    }
    
    private String graphHeaderRanks(){
        StringBuilder str = new StringBuilder();
        str.append("{ rank = same; Mt; ");
        NodoHeader auxc = colsList.head;
        while (auxc != null){
            str.append("A").append(auxc.pos).append(";");
            auxc = auxc.next;
        }
        str.append("}\n");
        return str.toString();
    }
    
    private String graphInternalNodes(){
        StringBuilder str = new StringBuilder();
        
        NodoHeader auxr = rowsList.head;
        while (auxr != null){
            NodoMatriz aux = auxr.access;
            while (aux != null){
                str.append("R").append(aux.y).append("_").append("C")
                        .append(aux.x).append("[label = \" X: ").append(aux.x)
                        .append(" Y: ").append(aux.y).append("Color: ").append(aux.color)
                        .append("\" width = 1.5, group = ").append(aux.y + 2).append("];\n");                
                aux = aux.next;
            }
            auxr = auxr.next;
        }
        return str.toString();
    }
    
    private String graphInternalRelations(){
        StringBuilder str = new StringBuilder();
        NodoHeader auxr = rowsList.head;
        while (auxr != null) {
            NodoMatriz aux = auxr.access;
            
            if (aux != null){
                str.append("U").append(auxr.pos).append(" -> ").append("R")
                    .append(aux.y).append("_").append("C").append(aux.x).append(";\n");
            }
            
            StringBuilder strrank = new StringBuilder("{ rank = same; ");
            strrank.append("U").append(auxr.pos).append(";");
            while (aux != null){
                strrank.append("R").append(aux.y).append("_").append("C")
                        .append(aux.x).append(";");
                
                if (aux.next != null)
                    str.append("R").append(aux.y).append("_").append("C").append(aux.x)
                        .append(" -> ").append("R").append(aux.next.y)
                            .append("_").append("C").append(aux.next.x).append(";\n");
                
                if (aux.prev != null)
                    str.append("R").append(aux.y).append("_").append("C").append(aux.x)
                        .append(" -> ").append("R").append(aux.prev.y)
                            .append("_").append("C").append(aux.prev.x).append(";\n");
                
                aux = aux.next;
            }
            strrank.append(" }\n");
            str.append(strrank);
            
            auxr = auxr.next;
        }
        
        NodoHeader auxc = colsList.head;
        while (auxc != null){
            NodoMatriz aux = auxc.access;
            
            if (aux != null){
                str.append("A").append(auxc.pos).append(" -> ").append("R")
                    .append(aux.y).append("_").append("C").append(aux.x).append(";\n");
            }
            
            while (aux != null){
                
                if (aux.next != null)
                    str.append("R").append(aux.y).append("_").append("C").append(aux.x)
                        .append(" -> ").append("R").append(aux.down.y)
                            .append("_").append("C").append(aux.down.x).append(";\n");
                
                if (aux.prev != null)
                    str.append("R").append(aux.y).append("_").append("C").append(aux.x)
                        .append(" -> ").append("R").append(aux.up.y)
                            .append("_").append("C").append(aux.up.x).append(";\n");
                
                aux = aux.next;
            }
            
            auxc = auxc.next;
        }
        
        
        return str.toString();
    }
    
}
