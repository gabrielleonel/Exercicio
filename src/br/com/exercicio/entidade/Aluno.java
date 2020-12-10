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
public class Aluno extends PessoaSenac implements Serializable{
   
    private String matricula;
    
    
    public Aluno() { 
        
    }

    public Aluno(String matricula, Integer id, String nome, String cpf, String rg, String email) {
        super(id, nome, cpf, rg, email);
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

 

  

   
}
