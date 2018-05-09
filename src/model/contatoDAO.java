/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Victor
 */
public class contatoDAO {

    String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
    String username = "root";        //nome do usuário      
    String password = "teste";      //senha de acesso
    ArrayList<contato> contatos = new ArrayList<>();

    public void adicionaContato(contato contato) throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement ps;
        try {
            String query = "INSERT INTO mysql.contato (nome, cpf, telefone_residencial, telefone_celular, email, data, ref_area, ref_empresa)" + "VALUES ('"
                    + contato.getNome() + "','" + contato.getCpf() + "','" + contato.getTelRes()
                    + "','" + contato.getTelcel() + "','" + contato.getEmail() + "'," + "CURRENT_DATE()" + ",'" + contato.getIDarea() + "', '" + contato.getIDempresa() + "');";
            ps = con.prepareStatement(query);
            ps.execute();
            JOptionPane.showMessageDialog(new JFrame(), "Cadastrado com sucesso.");
        } catch (MySQLIntegrityConstraintViolationException e) {
            if (e.getLocalizedMessage().endsWith("'cpf'")) {
                JOptionPane.showMessageDialog(new JFrame(), "Não foi possível cadastrar esta entrada. CPF Duplicado.", "ERRO!",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Não foi possível cadastrar esta entrada. Nome Duplicado.", "ERRO!",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public contato consultaContatoByName(String nome) throws SQLException, ParseException {
        contatos.clear();
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement ps;
        try {
            String query = "select c.*, e.nome as nomeDaEmpresa, a.descricao as nomeDaArea from contato AS c "
                    + "JOIN empresa as e ON c.ref_empresa  = e.`ID` "
                    + "JOIN area as a ON c.ref_area = a.`ID` "
                    + "Where " + "c.nome = '" + nome + "'" + " ORDER BY c.ID";
            System.out.println(query);
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                contato contato;
                contato = new contato(rs.getString("nome"), rs.getString("cpf"),
                        rs.getString("telefone_residencial"), rs.getString("telefone_celular"),
                        rs.getString("email"), Integer.valueOf(rs.getString("ref_area")), Integer.valueOf(rs.getString("ref_empresa")));
                contato.setData(rs.getDate("data"));
                contato.setID(Integer.valueOf(rs.getString("ID")));
                contato.setNomeDaEmpresa(rs.getString("nomeDaEmpresa"));
                contato.setNomedaArea(rs.getString("nomeDaArea"));
                return contato;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public ArrayList<contato> consultaContatoByDate(String dataInicial, String dataFinal) throws SQLException, ParseException {
        contatos.clear();
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement ps;
        try {
            String query = "select * from contato ORDER BY ID where data between " + "'" + dataInicial + "'" + " and " + "'" + dataFinal + "'";
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            for (int i = 0; i <= contatos.size(); i++) {
                if (rs.next()) {
                    contato contato;
                    contato = new contato(rs.getString("nome"), rs.getString("cpf"),
                            rs.getString("telefone_residencial"), rs.getString("telefone_celular"),
                            rs.getString("email"), Integer.valueOf(rs.getString("ref_area")), Integer.valueOf(rs.getString("ref_empresa")));
                    contato.setData(rs.getDate("data"));
                    contato.setID(Integer.valueOf(rs.getString("ID")));
                    contatos.add(contato);
                } else {
                    return contatos;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                }
            }
        }
        return null;
    }

    public contato consultaContatoByCPF(String text) throws SQLException, ParseException {
        contatos.clear();
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from contato ORDER BY ID where cpf = ?");
            ps.setString(1, text);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                contato contato;
                contato = new contato(rs.getString("nome"), rs.getString("cpf"),
                        rs.getString("telefone_residencial"), rs.getString("telefone_celular"),
                        rs.getString("email"), Integer.valueOf(rs.getString("ref_area")), Integer.valueOf(rs.getString("ref_empresa")));
                contato.setData(rs.getDate("data"));
                contato.setID(Integer.valueOf(rs.getString("ID")));
                return contato;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public void atualizaContato(int id, contato updatedContato) throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement ps;
        try {
            String comandoSQL = "UPDATE contato SET ID='" + updatedContato.getID()
                    + "', nome='" + updatedContato.getNome()
                    + "', telefone_residencial='" + updatedContato.getTelRes()
                    + "', telefone_celular='" + updatedContato.getTelcel()
                    + "', ref_area='" + updatedContato.getIDarea()
                    + "', ref_empresa='" + updatedContato.getIDempresa()
                    + "', email='" + updatedContato.getEmail()
                    + "' WHERE ID = " + id;
            ps = con.prepareStatement(comandoSQL);
            ps.execute();
            JOptionPane.showMessageDialog(new JFrame(), "Entrada \"" + updatedContato.getNome() + "\" atualizada com sucesso.");
        } catch (MySQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Atualização falhou! Existe duplicidade para esta atualização.", "ERRO!",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public ArrayList<contato> relatorio(String query) throws SQLException, ParseException {
        contatos.clear();
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement ps;
        try {
            if (!query.equals("")) {
                ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                for (int i = 0; i <= contatos.size(); i++) {
                    if (rs.next()) {
                        contato contato;
                        contato = new contato(rs.getString("nome"), rs.getString("cpf"),
                                rs.getString("telefone_residencial"), rs.getString("telefone_celular"),
                                rs.getString("email"), Integer.valueOf(rs.getString("ref_area")), Integer.valueOf(rs.getString("ref_empresa")));
                        contato.setData(rs.getDate("data"));
                        contato.setID(Integer.valueOf(rs.getString("ID")));
                        contato.setNomeDaEmpresa(rs.getString("nomeDaEmpresa"));
                        contato.setNomedaArea(rs.getString("nomeDaArea"));
                        contatos.add(contato);
                    } else {
                        return contatos;
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                }
            }
        }
        return null;
    }

    public ArrayList<contato> consultaQuery(String query) throws ParseException, SQLException {
        contatos.clear();
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement ps;
        try {
            if (!query.equals("")) {
                System.out.println(query);
                ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                for (int i = 0; i <= contatos.size(); i++) {
                    if (rs.next()) {
//                        String nomeArea;
//                        nomeArea = rs.getString("nomeDaArea");
//                        String nomeEmpresa;
//                        nomeEmpresa = rs.getString("nomeDaEmpresa");
                        contato contato;
                        contato = new contato(rs.getString("nome"), rs.getString("cpf"),
                                rs.getString("telefone_residencial"), rs.getString("telefone_celular"),
                                rs.getString("email"), Integer.valueOf(rs.getString("ref_area")), Integer.valueOf(rs.getString("ref_empresa")));
                        contato.setData(rs.getDate("data"));
                        contato.setID(Integer.valueOf(rs.getString("ID")));
                        contato.setNomeDaEmpresa(rs.getString("nomeDaEmpresa"));
                        contato.setNomedaArea(rs.getString("nomeDaArea"));
                        contatos.add(contato);
                    } else {
                        return contatos;
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                }
            }
        }
        return null;
    }
}
