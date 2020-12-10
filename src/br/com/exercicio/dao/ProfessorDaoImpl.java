/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.exercicio.dao;

import br.com.exercicio.entidade.Endereco;
import br.com.exercicio.entidade.Professor;
import br.com.exercicio.entidade.Telefone;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
     public List<Professor> pesquisarPorNome(String nome) throws SQLException {
        String consulta = "SELECT * FROM professor pf "
                + "INNER JOIN pessoasenac p on pr.idPessoa = p.id "
                + "INNER JOIN endereco e on e.idPessoa = pr.idPessoa "
                + "INNER JOIN telefone t on t.idPessoa = pr.idPessoa WHERE p.nome LIKE ?";
        Professor professor = null;
        Endereco endereco;
        List<Professor> professores = new ArrayList<>();
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setString(1, "%" + nome + "%");
            resultSet = preparando.executeQuery();
            int idPessoa;
            int idAntigo = 0;
            Telefone telefone;
            List<Telefone> telefones = null;
            while (resultSet.next()) {
                idPessoa = resultSet.getInt("p.id");

                if (idPessoa != idAntigo) {
                    telefones = new ArrayList<>();
                    professor = new Professor();
                    professor.setId(idPessoa);
                    professor.setNome(resultSet.getString("nome"));
                    professor.setCpf(resultSet.getString("cpf"));
                    professor.setRg(resultSet.getString("rg"));
                    professor.setEmail(resultSet.getString("email"));
                    professor.setCracha(resultSet.getString("cracha"));
                    professor.setTelefones(telefones);
                    professores.add(professor);
                    idAntigo = idPessoa;
                }
                endereco = new Endereco();
                endereco.setId(resultSet.getInt("e.id"));
                endereco.setLogradouro(resultSet.getString("logradouro"));
                endereco.setNumero(resultSet.getString("numero"));
                endereco.setBairro(resultSet.getString("bairro"));
                endereco.setCidade(resultSet.getString("cidade"));
                endereco.setEstado(resultSet.getString("estado"));
                endereco.setComplemento(resultSet.getString("complemento"));
                professor.setEndereco(endereco);

                telefone = new Telefone();
                telefone.setId(resultSet.getInt("t.id"));
                telefone.setNumero(resultSet.getString("numero"));
                telefone.setOperadora(resultSet.getString("operadora"));
                telefone.setTipo(resultSet.getString("tipo"));
                telefones.add(telefone);

            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar por nome +" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }

        return professores;
    }
    public Professor pesquisarPorId(Integer id) throws SQLException {
        Professor professor = null;
        Telefone telefone;
        List<Telefone> telefones = null;
        String consulta = "SELECT * FROM professor a "
                + " INNER JOIN pessoasenac p on a.idPessoa = p.id "
                + " INNER JOIN endereco e on e.idPessoa = a.idPessoa "
                + " INNER JOIN telefone t on t.idPessoa = a.idPessoa WHERE p.id = ?";

        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, id);
            resultSet = preparando.executeQuery();
            if (resultSet.next()) {
                telefones = new ArrayList<>();
                professor = new Professor();
                professor.setId(id);
                professor.setNome(resultSet.getString("nome"));
                professor.setCpf(resultSet.getString("cpf"));
                professor.setRg(resultSet.getString("rg"));
                professor.setEmail(resultSet.getString("email"));
                professor.setCracha(resultSet.getString("cracha"));

                professor.setEndereco(new Endereco());
                professor.getEndereco().setId(resultSet.getInt("e.id"));
                professor.getEndereco().setLogradouro(resultSet.getString("logradouro"));
                professor.getEndereco().setNumero(resultSet.getString("numero"));
                professor.getEndereco().setBairro(resultSet.getString("bairro"));
                professor.getEndereco().setCidade(resultSet.getString("cidade"));
                professor.getEndereco().setEstado(resultSet.getString("estado"));
                professor.getEndereco().setComplemento(resultSet.getString("complemento"));
                do {
                    telefone = new Telefone();
                    telefone.setId(resultSet.getInt("t.id"));
                    telefone.setNumero(resultSet.getString("numero"));
                    telefone.setTipo(resultSet.getString("tipo"));
                    telefone.setOperadora(resultSet.getString("operadora"));
                    telefones.add(telefone);
                } while (resultSet.next());
                professor.setTelefones(telefones);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar por id +" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }

        return professor;
    }
}
