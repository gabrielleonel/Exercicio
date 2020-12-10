/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.exercicio.dao;

import br.com.exercicio.entidade.Professor;
import br.com.utilitario.UtilGerador;
import org.junit.Test;


/**
 *
 * @author admin
 */
public class ProfessorDaoImplTest {

    private Professor professor;
    private ProfessorDaoImpl professorDaoImpl;

    public ProfessorDaoImplTest() {
        professorDaoImpl = new ProfessorDaoImpl();
    }

   @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
      professor = new Professor(
              "cracha" + UtilGerador.gerarNumero(11),
              null,
              UtilGerador.gerarNome(),
              "cpf" + UtilGerador.gerarNumero(11),
              "rg" + UtilGerador.gerarNumero(10),
              UtilGerador.gerarEmail());
              

       professorDaoImpl.salvar(professor);

    }

  @Test
    public void testAlterar() throws Exception {
        System.out.println("alterar");
     professor = new Professor(
                "crachaALTERADO" + UtilGerador.gerarNumero(5),
                7,
                UtilGerador.gerarNome(),
               "cpfA" + UtilGerador.gerarNumero(11),
                "rg" + UtilGerador.gerarNumero(7),
                UtilGerador.gerarEmail());

       professorDaoImpl.alterar(professor);
   }

}
