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
    public int id;
    public String name;
    private final Header colsList;
    private final Header rowsList;
    
    public Matriz(int id, String name) {
        this.id = id;
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
    
    public String graph(){
        StringBuilder str = new StringBuilder();
        str.append("digraph G{ node[shape=box style=filled]\n");
        str.append("subgraph cluster_p{\n");
        str.append("label = \"").append(this.name).append("\";\n");
        str.append("edge[dir = \"both\"];\n");
        str.append(nodeCols());
        str.append(nodeRows());
        str.append(renderNodes());
        str.append(relationsByCols());
        str.append(relationsByRows());
        str.append(graphRanks());
        str.append("}}");
        return str.toString();
    }
    
    private String nodeCols(){
        StringBuilder str = new StringBuilder();
        NodoHeader auxc = colsList.head;
        str.append("Mt -> C").append(auxc.pos).append(";\n");
        while (auxc != null){
            str.append("C").append(auxc.pos).append("[group =")
                    .append(auxc.pos + 1).append(", fillcolor=white ];\n");
            if (auxc.next != null)
                str.append("C").append(auxc.pos).append(" -> C").append(auxc.next.pos).append(";\n");
            auxc = auxc.next;
        }
        
        auxc = colsList.head;
        str.append("{ rank = same; Mt;");
        while (auxc != null){
            str.append("C").append(auxc.pos).append(";");
            auxc = auxc.next;
        }
        str.append("}\n");
        return str.toString();
    }
    
    private String nodeRows(){
        StringBuilder str = new StringBuilder();
        NodoHeader auxr = rowsList.head;
        str.append("Mt -> F").append(auxr.pos).append(";\n");
        while (auxr != null){
            str.append("F").append(auxr.pos).append("[group = 1, fillcolor=white ];\n");
            if (auxr.next != null)
                str.append("F").append(auxr.pos).append(" -> F").append(auxr.next.pos).append(";\n");
            auxr = auxr.next;
        }
        return str.toString();
    }
    
    private String renderNodes(){
        StringBuilder str = new StringBuilder();
        NodoHeader auxc = colsList.head;
        while (auxc != null){
            NodoMatriz aux = auxc.access;
            while (aux != null){
                str.append("X").append(aux.x).append("Y").append(aux.y)
                        .append("[label = \"\", fillcolor = \"").append(aux.color)
                        .append("\", group = ").append(aux.x + 1).append("];\n");
                aux = aux.down;
            }
            auxc = auxc.next;
        }
        return str.toString();
    }
    
    private String relationsByCols(){
        StringBuilder str = new StringBuilder();
        NodoHeader auxc = colsList.head;
        
        while (auxc != null){
            if (auxc.access != null)
                str.append("C").append(auxc.pos).append(" -> ")
                        .append("X").append(auxc.access.x).append("Y")
                        .append(auxc.access.y).append(";\n");
            
            NodoMatriz aux = auxc.access;
            while (aux != null){
                if (aux.down != null)
                    str.append("X").append(aux.x).append("Y").append(aux.y)
                            .append(" -> ").append("X").append(aux.down.x)
                            .append("Y").append(aux.down.y).append(";\n");
                aux = aux.down;
            }
            
            auxc = auxc.next;
        }
        return str.toString();
    }
    
    private String relationsByRows(){
        StringBuilder str = new StringBuilder();
        NodoHeader auxr = rowsList.head;
        
        while (auxr != null){
            if (auxr.access != null)
                str.append("F").append(auxr.pos).append(" -> ")
                        .append("X").append(auxr.access.x).append("Y")
                        .append(auxr.access.y).append(";\n");
            
            NodoMatriz aux = auxr.access;
            while (aux != null){
                if (aux.next != null)
                    str.append("X").append(aux.x).append("Y").append(aux.y)
                            .append(" -> ").append("X").append(aux.next.x)
                            .append("Y").append(aux.next.y).append(";\n");
                aux = aux.next;
            }
            
            auxr = auxr.next;
        }
        return str.toString();
    }
    
    
    private String graphRanks(){
        StringBuilder str = new StringBuilder();
        
        NodoHeader auxr = rowsList.head;
        while (auxr != null){
            str.append("{ rank = same; F").append(auxr.pos).append(";");
            
            NodoMatriz aux = auxr.access;
            while (aux != null){
                str.append("X").append(aux.x).append("Y").append(aux.y).append(";");
                aux = aux.next;
            }
            str.append("}\n");
            auxr = auxr.next;
        }
        return str.toString();
    }
}
