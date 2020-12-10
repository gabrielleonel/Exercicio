/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.exercicio.dao;

import br.com.exercicio.entidade.Endereco;
import br.com.exercicio.entidade.Professor;
import br.com.exercicio.entidade.Telefone;
import br.com.utilitario.UtilGerador;
import java.util.ArrayList;
import java.util.List;
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

        Endereco endereco = new Endereco(
                null,
                "logradouro" + UtilGerador.gerarCaracter(3),
                "numero" + UtilGerador.gerarCaracter(3),
                "bairro" + UtilGerador.gerarCaracter(3),
                "cidade" + UtilGerador.gerarCaracter(3),
                "estado" + UtilGerador.gerarCaracter(3),
                "complemento" + UtilGerador.gerarCaracter(3));

        professor.setEndereco(endereco);
        List<Telefone> telefones = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            telefones.add(gerarTelefone());
        }

        professor.setTelefones(telefones);
        professorDaoImpl = new ProfessorDaoImpl();
        professorDaoImpl.salvar(professor);

    }

    private Telefone gerarTelefone() {
        Telefone tel = new Telefone(null,
                UtilGerador.gerarTelefoneFixo(),
                "Fixo",
                "Vivo");

        return tel;

    }

   // @Test
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

   // @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
       professorDaoImpl.excluir(1);
    }
    
     private void mostrarProfessor(Professor prof) {
        System.out.println("id: " + prof.getId());
        System.out.println("professor: " + prof.getNome());
        System.out.println("cpf: " + prof.getCpf());
        System.out.println("rg: " + prof.getRg());
        System.out.println("email: " + prof.getEmail());
        System.out.println("cracha: " + prof.getCracha());
        System.out.println("");
        System.out.println("id: " + prof.getEndereco().getId());
        System.out.println("logradouro: " + prof.getEndereco().getLogradouro());
        System.out.println("numero: " + prof.getEndereco().getNumero());
        System.out.println("bairro: " + prof.getEndereco().getBairro());
        System.out.println("cidade: " + prof.getEndereco().getCidade());
        System.out.println("estado: " + prof.getEndereco().getEstado());
        System.out.println("complemento: " + prof.getEndereco().getComplemento());
        System.out.println("");
        for (Telefone telefone : prof.getTelefones()) {
            System.out.println("");
            System.out.println("id" + telefone.getId());
            System.out.println("numero " + telefone.getNumero());
            System.out.println("tipo " + telefone.getTipo());
            System.out.println("operadora " + telefone.getOperadora());
        }
        
    }

   // @Test
  //  public void testPesquisarPorNome() throws Exception {
  //     System.out.println("pesquisarPorNome");
   //    List<Professor> professor = professorDaoImpl.pesquisarPorNome("");
   //     for (Professor professor : professor) {
  //          mostrarProfessor(professor);
  //      }
  //  }

   // @Test
  //  public void testPesquisarPorId() throws Exception {
  //      System.out.println("pesquisarPorId");
  //   professor = professorDaoImpl.pesquisarPorId(1);
  //  }

}
