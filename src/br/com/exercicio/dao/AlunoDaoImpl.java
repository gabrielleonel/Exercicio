/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.exercicio.dao;

import br.com.exercicio.entidade.Aluno;
import br.com.exercicio.entidade.Endereco;
import br.com.exercicio.entidade.Telefone;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

            TelefoneDaoImpl telefoneDaoImpl = new TelefoneDaoImpl();
            telefoneDaoImpl.salvarTelefone(aluno.getTelefones(), aluno.getId(), conexao);

            EnderecoDaoImpl enderecoDaoImpl = new EnderecoDaoImpl();
            enderecoDaoImpl.salvar(aluno.getEndereco(), aluno.getId(), conexao);

        } catch (SQLException eSQL) {
            System.err.println("Erro ao salvar aluno" + eSQL.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }

    }

    public Aluno pesquisarPorId(Integer id) throws SQLException {
        Aluno aluno = null;
        Telefone telefone;
        List<Telefone> telefones = null;
        String consulta = "SELECT * FROM aluno a "
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
                aluno = new Aluno();
                aluno.setId(id);
                aluno.setNome(resultSet.getString("nome"));
                aluno.setCpf(resultSet.getString("cpf"));
                aluno.setRg(resultSet.getString("rg"));
                aluno.setEmail(resultSet.getString("email"));
                aluno.setMatricula(resultSet.getString("matricula"));

                aluno.setEndereco(new Endereco());
                aluno.getEndereco().setId(resultSet.getInt("e.id"));
                aluno.getEndereco().setLogradouro(resultSet.getString("logradouro"));
                aluno.getEndereco().setNumero(resultSet.getString("numero"));
                aluno.getEndereco().setBairro(resultSet.getString("bairro"));
                aluno.getEndereco().setCidade(resultSet.getString("cidade"));
                aluno.getEndereco().setEstado(resultSet.getString("estado"));
                aluno.getEndereco().setComplemento(resultSet.getString("complemento"));
                do {
                    telefone = new Telefone();
                    telefone.setId(resultSet.getInt("t.id"));
                    telefone.setNumero(resultSet.getString("numero"));
                    telefone.setTipo(resultSet.getString("tipo"));
                    telefone.setOperadora(resultSet.getString("operadora"));
                    telefones.add(telefone);
                } while (resultSet.next());
                aluno.setTelefones(telefones);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar por id +" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }

        return aluno;
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

    public List<Aluno> pesquisarPorNome(String nome) throws SQLException {
        String consulta = "SELECT * FROM professor pf "
                + "INNER JOIN pessoasenac p on pr.idPessoa = p.id "
                + "INNER JOIN endereco e on e.idPessoa = pr.idPessoa "
                + "INNER JOIN telefone t on t.idPessoa = pr.idPessoa WHERE p.nome LIKE ?";
        Aluno aluno = null;
        Endereco endereco;
        List<Aluno> alunos = new ArrayList<>();
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
                    aluno = new Aluno();
                    aluno.setId(idPessoa);
                    aluno.setNome(resultSet.getString("nome"));
                    aluno.setCpf(resultSet.getString("cpf"));
                    aluno.setRg(resultSet.getString("rg"));
                    aluno.setEmail(resultSet.getString("email"));
                    aluno.setMatricula(resultSet.getString("matricula"));
                    aluno.setTelefones(telefones);
                    alunos.add(aluno);
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
                aluno.setEndereco(endereco);

                telefone = new Telefone();
                telefone.setId(resultSet.getInt("t.id"));
                telefone.setNumero(resultSet.getString("numero"));
                telefone.setOperadora(resultSet.getString("operadora"));
                telefone.setTipo(resultSet.getString("tipo"));
                telefones.add(telefone);

            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar por  +" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }

        return alunos;
    }

}
