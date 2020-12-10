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
import org.junit.Test;


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

 
  

     @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        aluno = new Aluno(
                "matricula" + UtilGerador.gerarCaracter(4),
                null,
                "nome" + UtilGerador.gerarNome(),
                "cpf" + UtilGerador.gerarNumero(11),
                "rg" + UtilGerador.gerarNumero(6),
                "email" + UtilGerador.gerarEmail());
                

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
        alunoDaoImpl.excluir(Integer.SIZE);
   
    }
     private void mostrarAluno(Aluno aluno) {
        System.out.println("id:" + aluno.getId());
        System.out.println("aluno:" + aluno.getNome());
        System.out.println("cpf:" + aluno.getCpf());
        System.out.println("rg:" + aluno.getRg());
        System.out.println("email:" + aluno.getEmail());
        System.out.println("matricula:" + aluno.getMatricula());
        System.out.println("");
        System.out.println("id:" + aluno.getEndereco().getId());
        System.out.println("logradouro:" + aluno.getEndereco().getLogradouro());
        System.out.println("numero:" + aluno.getEndereco().getNumero());
        System.out.println("bairro:" + aluno.getEndereco().getBairro());
        System.out.println("cidade:" + aluno.getEndereco().getCidade());
        System.out.println("estado:" + aluno.getEndereco().getEstado());
        System.out.println("complemento:" + aluno.getEndereco().getComplemento());
        System.out.println("");
        for (Telefone telefone : aluno.getTelefones()) {
            System.out.println("");
            System.out.println("id:" + telefone.getId());
            System.out.println("numero" + telefone.getNumero());
            System.out.println("tipo" + telefone.getTipo());
            System.out.println("operadora" + telefone.getOperadora());
        }

    }

   // @Test
    public void testPesquisarPorId() throws Exception {
        System.out.println("pesquisarPorId");
        aluno = alunoDaoImpl.pesquisarPorId(3);
        mostrarAluno(aluno);
    }

   // @Test
    public void testPesquisarPorNome() throws Exception {
        System.out.println("pesquisarPorNome");
       List<Aluno> alunos = alunoDaoImpl.pesquisarPorNome("Nome");
        for (Aluno aluno : alunos) {
            mostrarAluno(aluno);
        }
    }

}
