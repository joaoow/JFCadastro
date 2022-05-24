/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Pessoa;
import util.BancoDados;

/**
 *
 * @author sala303b
 */
public class PessoaControle {
    
    
    public static boolean Cadastrar(Pessoa p) {
           try {
            Connection conn = BancoDados.getConexao(); //conectar com o bando de dados e enviar os dados salvos da classe Contato.
            String sql = "INSERT INTO tb_pessoa ";
            sql += " (nome_completo,cpf,data_nascimento,email,cep,bairro,cidade,numero,sexo,uf,dddtelefone) ";
            sql += " VALUES (?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
          
            ps.setString(1, p.getNomecompleto());
            ps.setString(2, p.getCpf());
            ps.setDate(3, p.getDatadenascimento());
            ps.setString(4, p.getEmail());
            ps.setString(5, p.getCep());
            ps.setString(6, p.getBairro());
            ps.setString(7, p.getCidade());
            ps.setInt(8, p.getNumero());
            ps.setString(9, p.getSexo());
            ps.setString(10, p.getUf());
            ps.setString(11, p.getDddtelefone());
            
         
            
            int linhasafetadas = ps.executeUpdate();
            if (linhasafetadas > 0) {
                final ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    final int lastId = rs.getInt(1);
                    System.out.println("O numero do id é:"
                            + lastId);
                    return true;

                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    
    public static Pessoa getUsuarioPorId(long iduser) {
        try {
            Connection conn = BancoDados.getConexao();
            String sql = "SELECT * FROM tb_pessoa WHERE id = ?; ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, iduser);
            final ResultSet rs = ps.executeQuery();

            Pessoa p = new Pessoa();
            if (rs.next()) {
                p.setId(rs.getInt("id"));
                p.setNomecompleto(rs.getString("nome_usuario"));
                p.setLogin(rs.getString("login"));
                p.setSenha(rs.getString("senha"));
                p.setDatadenascimento(rs.getDate("data_cadastro"));
                p.setCep(rs.getString("cep"));
                p.setCidade(rs.getString("cidade"));
                p.setLogradouro(rs.getString("logradouro"));
                p.setSexo(rs.getString("sexo"));
                p.setBairro(rs.getString("bairro"));
                p.setUf(rs.getString("uf"));
                p.setIdusuario(rs.getInt("idusuario"));
               
                
            }
            return p;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    public static List<Pessoa> ListarPessoa() {
        try {

            Connection conn = BancoDados.getConexao();
            String sql = "SELECT * FROM tb_pessoa; ";
            PreparedStatement ps = conn.prepareStatement(sql);
            List<Pessoa> lista = new ArrayList();
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Pessoa p = new Pessoa();
                p.setId(rs.getInt("id"));
                p.setNomecompleto(rs.getString("nome completo"));
                p.setCpf(rs.getString("cpf"));
                p.setBairro(rs.getString("bairro"));
                p.setCidade(rs.getString("cidade"));
                p.setDddtelefone(rs.getString("dddtelefone"));
                p.setEmail(rs.getString("Email"));
                p.setNumero(rs.getInt("telefone"));
                p.setPessoa(rs.getString("pessoa"));
                p.setUf(rs.getString("uf"));
                p.setSexo(rs.getString("sexo"));
                p.setLogradouro(rs.getString("logradouro"));
               
                lista.add(p);
            }
            return lista;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static boolean Excluir(long id) {
        try {
            Connection conn = BancoDados.getConexao(); //conectar com o bando de dados e enviar os dados salvos da classe Contato.
            String sql = "DELETE FROM tb_pessoa  WHERE id = ?; ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            int linhasafetadas = ps.executeUpdate();
            if (linhasafetadas > 0) {
                System.out.println("Apagou!!");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

}


    

