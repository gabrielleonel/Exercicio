/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.exercicio.dao;


import br.com.exercicio.entidade.PessoaSenac;
import br.com.utilitario.UtilGerador;

/**
 *
 * @author admin
 */
public class PessoaSenacDaoImplTest {
    
    
     private PessoaSenac pessoaSenac;
     private PessoaSenacDaoImpl pessoaSenacDaoImpl;        
             
    public PessoaSenacDaoImplTest() {
          pessoaSenacDaoImpl = new PessoaSenacDaoImpl();
    }


 //   @Test
     public void testSalvar() throws Exception {
        System.out.println("salvar");
        pessoaSenac = new PessoaSenac(null,
                UtilGerador.gerarNome(),
                "CPF" + UtilGerador.gerarNumero(11),
                "rg" + UtilGerador.gerarNumero(11),
                UtilGerador.gerarEmail());

        pessoaSenacDaoImpl.salvar(pessoaSenac);
     
    }

  // @Test
    public void testAlterar() throws Exception {
        System.out.println("alterar");
        pessoaSenac = new PessoaSenac(
                1,
                 UtilGerador.gerarNome(),
                "cpfALTERADO" + UtilGerador.gerarNumero(11),
                "rg" + UtilGerador.gerarNumero(7),
                UtilGerador.gerarEmail());
        
      pessoaSenacDaoImpl.alterar(pessoaSenac);
    }

 // @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        Integer id = 3;
        PessoaSenacDaoImpl instance = new PessoaSenacDaoImpl();
        instance.excluir(id);
   
    }
    
}
