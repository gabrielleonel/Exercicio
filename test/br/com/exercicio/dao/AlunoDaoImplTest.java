/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.exercicio.dao;

import br.com.exercicio.entidade.Aluno;
import br.com.exercicio.entidade.Endereco;
import br.com.exercicio.entidade.Telefone;
import br.com.utilitario.UtilGerador;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;


/**
 *
 * @author admin
 */
public class AlunoDaoImplTest {

    private Aluno aluno;
    private final AlunoDaoImpl alunoDaoImpl;

    public AlunoDaoImplTest() {
        alunoDaoImpl = new AlunoDaoImpl();
    }

  

    // @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        aluno = new Aluno(
                "matricula" + UtilGerador.gerarNumero(11),
                null,
                UtilGerador.gerarNome(),
                "cpf" + UtilGerador.gerarNumero(11),
                "rg" + UtilGerador.gerarNumero(11),
                UtilGerador.gerarEmail(),
                "Endereco" + UtilGerador.gerarCaracter(11));

        Endereco endereco = new Endereco(null,
                "logradouro" + UtilGerador.gerarCaracter(10),
                "numero" + UtilGerador.gerarCaracter(10),
                "bairro" + UtilGerador.gerarCaracter(10),
                "cidade" + UtilGerador.gerarCaracter(10),
                "estado" + UtilGerador.gerarCaracter(10),
                "complemento" + UtilGerador.gerarCaracter(10));

        List<Telefone> telefones;
        telefones = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            telefones.add(criarTelefone());
        }
        aluno.setEndereco(endereco);
        alunoDaoImpl.salvar(aluno);
    }

    private Telefone criarTelefone() {
        Telefone telefone = new Telefone(
                null,
                UtilGerador.gerarTelefoneFixo(),
                "tipo" + UtilGerador.gerarCaracter(4),
                "operadora" + UtilGerador.gerarCaracter(5));

        return telefone;
    }

   // @Test
    public void testAlterar() throws Exception {
        System.out.println("alterar");
        Aluno aluno = null;
        AlunoDaoImpl instance = new AlunoDaoImpl();
        instance.alterar(aluno);
      
    }

  //  @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        Integer id = null;
        AlunoDaoImpl instance = new AlunoDaoImpl();
        instance.excluir(id);
   
    }

}
