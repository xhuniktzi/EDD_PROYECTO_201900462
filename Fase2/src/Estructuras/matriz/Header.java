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
public class Header {
    private NodoHeader head;
    
    public Header(){
        this.head = null;
    }
    public boolean isVoid(){
        return this.head == null;
    }
    public NodoHeader getHeader(int pos){
        NodoHeader aux = head;
        while (aux != null) {
            if (aux.pos == pos){
                return aux;
            }
            aux = aux.next;
        }
        return null;
    }
    
        public void setHeader(NodoHeader newNode){
        if (isVoid()){
            head = newNode;
        } else if (newNode.pos < head.pos){
            newNode.next = this.head;
            this.head.prev = newNode;
            this.head = newNode;
        } else {
            NodoHeader aux = head;
            while (aux.next != null) {
                if (newNode.pos < aux.next.pos){
                    newNode.next = aux.next;
                    aux.next.prev = newNode;
                    newNode.prev = aux;
                    aux.next = newNode;
                    break;
                }
                aux = aux.next;
            }
            
            if (aux.next == null){
                aux.next = newNode;
                newNode.prev = aux;
            }
        }
    }
}
