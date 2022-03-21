package Estructuras.btree;

import Modelos.Cliente;

public class BTreeClients {
    BTreeNodeClient root;
    int t;
    private final StringBuilder str = new StringBuilder();
    private int nodos;

    public BTreeClients(int deg){
        this.root = null;
        this.t = deg;
    }

    public void printTree(){
        if (root != null)
            root.printTree();
        System.out.println();
    }

    public Cliente findClienteByDpi(String dpi){
        BTreeNodeClient node = root == null ? null : root.findClienteByDpi(dpi);
        if (node != null)
        {
            int index = node.findKey(dpi);
            return node.dataEntries.getByIndex(index);
        } else { 
            return null;
        }
    }

    public void insert(Cliente c){
        if (root == null){
            root = new BTreeNodeClient(t,true);
            root.dataEntries.setByIndex(0, c);
            root.num = 1;
        }
        else {
            if (root.num == 2*t-1){
                BTreeNodeClient s = new BTreeNodeClient(t,false);
                s.children.setByIndex(0, root);
                s.split(0,root);
                int i = 0;
                if (s.dataEntries.getByIndex(0).dpi.compareTo(c.dpi) < 0)
                    i++;
                s.children.getByIndex(i).insertNotFull(c);
                root = s;
            }
            else
                root.insertNotFull(c);
        }
    }

    public void remove(String dpi){
        if (root == null){
            return;
        }
        root.remove(dpi);

        if (root.num == 0){ 
            if (root.isLeaf)
                root = null;
            else
                root = root.children.getByIndex(0);
        }
    }
    
    public String graph(){
        str.append("digraph ArbolB{\n").append("\nrankdir=TB;\n")
                .append("node[color=\"blue\",style=\"rounded,filled\",fillcolor=lightgray, shape=record];\n");
        
        graphIdentity(this.root);
        graphRelations(this.root);
        
        str.append("\n}\n");
        return str.toString();
    }
    
    private void graphIdentity(BTreeNodeClient page){
        int contador = 0;
        if(page != null){
            this.nodos = 0;
            for (int i=0; i < page.num; i++){
                if (page.dataEntries.getByIndex(i) != null){
                    this.nodos++;
                    if (i != 0)
                        str.append("|");
                    if(this.nodos == 1)
                        str.append("\nNodo").append(page.dataEntries.getByIndex(i).dpi)
                                .append("[label=\"<f0> |");
                    
                    if (i == 0){
                        str.append("<f").append(i + 1).append(">")
                                .append(page.dataEntries.getByIndex(i).dpi).append("\\n")
                                .append(page.dataEntries.getByIndex(i).nombre_cliente)
                                .append("|<f").append(i + 2).append("> ");
                        
                        contador = 3;
                    } else {
                        str.append("<f").append(contador).append(">")
                                .append(page.dataEntries.getByIndex(i).dpi)
                                .append("\\n").append(page.dataEntries.getByIndex(i).nombre_cliente)
                                .append("|<f").append(contador + 1).append("> ");
                        
                        contador += 2;
                    }
                    
                    if (i == page.num - 1){
                        contador = 0;
                        str.append(" \",group=0];\n");
                    }
                }
            }
            graphIdentity(page.children.getByIndex(0));
            graphIdentity(page.children.getByIndex(1));
            graphIdentity(page.children.getByIndex(2));
            graphIdentity(page.children.getByIndex(3));
            graphIdentity(page.children.getByIndex(4));
        }

    }
    
    private void graphRelations(BTreeNodeClient page){
        if (page != null){
            if (page.dataEntries.getByIndex(0) != null){
                if (page.children.getByIndex(0) != null && page.children.getByIndex(0).dataEntries.getByIndex(0) != null){
                    str.append("\nNodo").append(page.dataEntries.getByIndex(0).dpi)
                            .append(":f0->Nodo").append(page.children.getByIndex(0).dataEntries.getByIndex(0).dpi);
                }
                if (page.children.getByIndex(1) != null && page.children.getByIndex(1).dataEntries.getByIndex(0) != null){
                    str.append("\nNodo").append(page.dataEntries.getByIndex(0).dpi)
                            .append(":f2->Nodo").append(page.children.getByIndex(1).dataEntries.getByIndex(0).dpi);
                }
                if (page.children.getByIndex(2) != null && page.children.getByIndex(2).dataEntries.getByIndex(0) != null){
                    str.append("\nNodo").append(page.dataEntries.getByIndex(0).dpi)
                            .append(":f4->Nodo").append(page.children.getByIndex(2).dataEntries.getByIndex(0).dpi);
                }
                if (page.children.getByIndex(3) != null && page.children.getByIndex(3).dataEntries.getByIndex(0) != null){
                    str.append("\nNodo").append(page.dataEntries.getByIndex(0).dpi)
                            .append(":f6->Nodo").append(page.children.getByIndex(3).dataEntries.getByIndex(0).dpi);
                }
                if (page.children.getByIndex(4) != null && page.children.getByIndex(4).dataEntries.getByIndex(0) != null){
                    str.append("\nNodo").append(page.dataEntries.getByIndex(0).dpi)
                            .append(":f8->Nodo").append(page.children.getByIndex(4).dataEntries.getByIndex(0).dpi);
                }
            }
            graphRelations(page.children.getByIndex(0));
            graphRelations(page.children.getByIndex(1));
            graphRelations(page.children.getByIndex(2));
            graphRelations(page.children.getByIndex(3));
            graphRelations(page.children.getByIndex(4));
        }
    }
}
