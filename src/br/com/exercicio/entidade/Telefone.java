/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.exercicio.entidade;

/**
 *
 * @author admin
 */
public class Telefone {
    private Integer id;
    private String numero;
    private String tipo;
    private String operadora;
    
    
     public Telefone(){
        
    }

    public Telefone(Integer id, String numero, String tipo, String operadora) {
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
        this.operadora = operadora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOperadora() {
        return operadora;
    }

    public void setOperadora(String operadora) {
        this.operadora = operadora;
    }
}
