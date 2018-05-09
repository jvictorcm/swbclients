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
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Victor
 */
public class empresaDAO {

    String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
    String username = "root";        //nome do usuário      
    String password = "teste";      //senha de acesso
    ArrayList<empresa> empresas = new ArrayList<>();

    public void adicionaEmpresa(String nome, String CNPJ) throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement ps;
        try {
            String query = "INSERT INTO mysql.empresa (cnpj, nome, data)" + "VALUES ('" + CNPJ + "','" + nome + "', CURRENT_DATE());";
            ps = con.prepareStatement(query);
            ps.execute();
            JOptionPane.showMessageDialog(new JFrame(), "Cadastrado com sucesso.");
        } catch (MySQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Já consta como cadastrado. Verifique se CNPJ e nome não estão nulos e são únicos.", "ERRO!",
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

    public empresa findEmpresaByNome(String nome) throws SQLException {
        empresas.clear();
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from empresa where nome = ?");
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                empresa empresa = new empresa();
                empresa.setCodigoCNPJ(rs.getString("cnpj"));
                empresa.setData(rs.getDate("data"));
                empresa.setID(String.valueOf(rs.getInt("ID")));
                empresa.setNome(rs.getString("nome"));
                return empresa;
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

    public ArrayList<empresa> findEmpresaByData(String dataInicialQuery, String dataFinalQuery) throws SQLException {
        empresas.clear();
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement ps;
        try {
            String query = "select * from empresa where data between " + "'" + dataInicialQuery + "'" + " and " + "'" + dataFinalQuery + "'";
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            for (int i = 0; i <= empresas.size(); i++) {
                if (rs.next()) {
                    empresa empresa = new empresa();
                    empresa.setCodigoCNPJ(rs.getString("cnpj"));
                    empresa.setData(rs.getDate("data"));
                    empresa.setID(String.valueOf(rs.getInt("ID")));
                    empresa.setNome(rs.getString("nome"));
                    empresas.add(empresa);
                } else {
                    return empresas;
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

    public void atualizaEmpresa(String id, empresa updatedEmpresa) throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement ps;
        try {
            String comandoSQL = "UPDATE empresa SET ID='" + updatedEmpresa.getID() + "', cnpj='" + updatedEmpresa.getCodigoCNPJ() + "', data='" + updatedEmpresa.getData() + "', nome='" + updatedEmpresa.getNome() + "' WHERE ID = " + Integer.parseInt(id);
            ps = con.prepareStatement(comandoSQL);
            ps.execute();
            JOptionPane.showMessageDialog(new JFrame(), "Entrada \"" + updatedEmpresa.getNome() + "\" atualizada com sucesso.");
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

    public empresa findEmpresaByCNPJ(String text) throws SQLException {
        empresas.clear();
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from empresa where cnpj = ?");
            ps.setString(1, text);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                empresa empresa = new empresa();
                empresa.setCodigoCNPJ(rs.getString("cnpj"));
                empresa.setData(rs.getDate("data"));
                empresa.setID(String.valueOf(rs.getInt("ID")));
                empresa.setNome(rs.getString("nome"));
                return empresa;
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

    public ArrayList<empresa> findQuery(String query) throws SQLException {
        empresas.clear();
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            for (int i = 0; i <= empresas.size(); i++) {
                if (rs.next()) {
                    empresa empresa = new empresa();
                    empresa.setCodigoCNPJ(rs.getString("cnpj"));
                    empresa.setData(rs.getDate("data"));
                    empresa.setID(String.valueOf(rs.getInt("ID")));
                    empresa.setNome(rs.getString("nome"));
                    empresas.add(empresa);
                } else {
                    return empresas;
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

    public void deletaEmpresas(String query) throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.execute();
            JOptionPane.showMessageDialog(new JFrame(), "Exclusão feito com sucesso!");
        } catch (MySQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(new JFrame(), "ERRO! Existem contatos que estão utilizando esta Empresa.", "ERRO!",
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
}
