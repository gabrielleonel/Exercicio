/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.exercicio.entidade;

import java.io.Serializable;

/**
 *
 * @author admin
 */
public class Professor extends PessoaSenac implements Serializable {
   
    private String cracha;
    
    public Professor(){
        
    }

    public Professor(String cracha, Integer id, String nome, String cpf, String rg, String email) {
        super(id, nome, cpf, rg, email);
        this.cracha = cracha;
    }

    public String getCracha() {
        return cracha;
    }

    public void setCracha(String cracha) {
        this.cracha = cracha;
    }

   
 

 

  


}
