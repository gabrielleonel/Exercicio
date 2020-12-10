/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.exercicio.dao;

import br.com.exercicio.entidade.Professor;
import java.io.Serializable;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class ProfessorDaoImpl extends PessoaSenacDaoImpl implements Serializable {



    public void salvar(Professor professor) throws SQLException {
        super.salvar(professor);
        String sql = "INSERT INTO professor (cracha, idPessoaSenac) VALUES(?, ?)";
        try {
            preparando = conexao.prepareStatement(sql);
            preparando.setString(1, professor.getCracha());
            preparando.setInt(2, professor.getId());
            preparando.executeUpdate();
            
            TelefoneDaoImpl telefoneDaoImpl = new TelefoneDaoImpl();
            telefoneDaoImpl.salvarTelefone(professor.getTelefones(), professor.getId(), conexao);

            EnderecoDaoImpl enderecoDaoImpl = new EnderecoDaoImpl();
            enderecoDaoImpl.salvar(professor.getEndereco(), professor.getId(), conexao);
 
        } catch (SQLException eSQL) {
            System.err.println("Erro ao salvar professor" + eSQL.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }

    public void alterar(Professor professor) throws SQLException {
        super.alterar(professor);
        String sql = "UPDATE professor SET Cracha = ? WHERE idPessoaSenac = ?";
        try {
            preparando = conexao.prepareStatement(sql);
            preparando.setString(1, professor.getCracha());
            preparando.setInt(2, professor.getId());
            preparando.executeUpdate();

        } catch (SQLException eSQL) {
            System.err.println("Erro ao salvar professor " + eSQL.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }
     public void excluir(Integer id) throws SQLException {
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement("DELETE FROM professor WHERE id = ?");
            preparando.setInt(1, id);
            preparando.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir professor " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }
}
