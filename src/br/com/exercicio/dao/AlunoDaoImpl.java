/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.exercicio.dao;

import br.com.exercicio.entidade.Aluno;
import br.com.exercicio.entidade.Endereco;
import java.io.Serializable;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class AlunoDaoImpl extends PessoaSenacDaoImpl implements Serializable {

    public void salvar(Aluno aluno) throws SQLException {
        super.salvar(aluno);
        String sql = "INSERT INTO aluno (matricula, idPessoaSenac) VALUES(?, ?)";
        try {
            preparando = conexao.prepareStatement(sql);
            preparando.setString(1, aluno.getMatricula());
            preparando.setInt(2, aluno.getId());
            preparando.executeUpdate();

        } catch (SQLException eSQL) {
            System.err.println("Erro ao salvar aluno" + eSQL.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }

    public void alterar(Aluno aluno) throws SQLException {
        super.alterar(aluno);
        String sql = "UPDATE aluno SET matricula = ? WHERE idPessoaSenac = ?";
        try {
            preparando = conexao.prepareStatement(sql);
            preparando.setString(1, aluno.getMatricula());
            preparando.setInt(2, aluno.getId());
            preparando.executeUpdate();

        } catch (SQLException eSQL) {
            System.err.println("Erro ao alterar aluno " + eSQL.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }

    public void excluir(Integer id) throws SQLException {
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement("DELETE FROM aluno WHERE id = ?");
            preparando.setInt(1, id);
            preparando.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir aluno " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }

    public Aluno pesquisarPorId(Integer id) throws SQLException {
        Aluno aluno = null;
        String consulta = "SELECT * FROM professor pf "
                + "INNER JOIN pessoa p on pf.idPessoa = p.id "
                + "INNER JOIN endereco e on e.idPessoa = pf.idPessoa "
                + "INNER JOIN telefone t on t.idPessoa = pf.idPessoa WHERE p.nome LIKE ?";

        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, id);
            resultSet = preparando.executeQuery();
            if (resultSet.next()) {
                aluno = new Aluno();
                aluno.setId(id);
                aluno.setNome(resultSet.getString("nome"));
                aluno.setEmail(resultSet.getString("email"));
                aluno.setMatricula(resultSet.getString("matricula"));
                aluno.setCpf(resultSet.getString("cpf"));
                aluno.setRg(resultSet.getString("rg"));
                aluno.setEndereco(new Endereco());
                aluno.getEndereco().setId(resultSet.getInt("p.id"));
                aluno.getEndereco().setLogradouro(resultSet.getString("logradouro"));
                aluno.getEndereco().setNumero(resultSet.getString("numero"));
                aluno.getEndereco().setBairro(resultSet.getString("bairro"));
                aluno.getEndereco().setCidade(resultSet.getString("cidade"));
                aluno.getEndereco().setEstado(resultSet.getString("estado"));
                aluno.getEndereco().setComplemento(resultSet.getString("complemento"));
                   

            }
        } catch (Exception e) {
             System.err.println("Erro ao pesquisar por id +" + e.getMessage());
        }finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }

        return aluno;

    }

}
