/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author Xhunik_Local
 */
public class Cliente {
    public String dpi;
    public String nombre_cliente;
    public String password;
    
    public Cliente(String dpi, String nombre_cliente, String password){
        this.dpi = dpi;
        this.nombre_cliente = nombre_cliente;
        this.password = password;
    }
}
