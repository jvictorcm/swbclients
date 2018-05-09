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
public class areaDAO {

    String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
    String username = "root";        //nome do usuário      
    String password = "teste";      //senha de acesso
    ArrayList<area> areas = new ArrayList<>();

    public ArrayList<area> findAreaByData(String dataInicial, String dataFinal) throws SQLException { //RETORNAR VETOR
        areas.clear();
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement ps;
        try {
            String query = "select * from area where data between " + "'" + dataInicial + "'" + " and " + "'" + dataFinal + "'";
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            for (int i = 0; i <= areas.size(); i++) {
                if (rs.next()) {
                    area area = new area();
                    area.setCodigo(rs.getInt("ID"));
                    area.setData(rs.getDate("data"));
                    area.setDescricao(rs.getString("descricao"));
                    areas.add(area);
                } else {
                    return areas;
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

    public area findAreaByDescricao(String string) throws SQLException {
        areas.clear();
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from area where descricao = ?");
            ps.setString(1, string);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                area area = new area();
                area.setCodigo(rs.getInt("ID"));
                area.setData(rs.getDate("data"));
                area.setDescricao(rs.getString("descricao"));
                return area;
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

    public void adicionaArea(String descricao) throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement ps;
        try {
            String query = "INSERT INTO mysql.area (descricao, data)" + "VALUES ('" + descricao + "', CURRENT_DATE());";
            ps = con.prepareStatement(query);
            ps.execute();
            JOptionPane.showMessageDialog(new JFrame(), "Cadastrado com sucesso.");
        } catch (MySQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(new JFrame(), "\"" + descricao + "\"" + " já consta como cadastrado. Tente novamente.", "ERRO!",
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

    public void atualizaArea(int ID, area updatedArea) throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement ps;
        try {
            String comandoSQL = "UPDATE area SET ID='" + updatedArea.getID() + "', descricao='" + updatedArea.getDescricao() + "', data='" + updatedArea.getData() + "' WHERE ID = " + ID;
            ps = con.prepareStatement(comandoSQL);
            ps.execute();
            JOptionPane.showMessageDialog(new JFrame(), "Entrada \"" + updatedArea.getDescricao() + "\" atualizada com sucesso.");
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

    public ArrayList<area> findQuery(String query) throws SQLException {
        areas.clear();
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            for (int i = 0; i <= areas.size(); i++) {
                if (rs.next()) {
                    area area = new area();
                    area.setCodigo(rs.getInt("ID"));
                    area.setData(rs.getDate("data"));
                    area.setDescricao(rs.getString("descricao"));
                    areas.add(area);
                } else {
                    return areas;
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

    public void deleteAreas(String query) throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.execute();
            JOptionPane.showMessageDialog(new JFrame(), "Exclusão feito com sucesso!");
        } catch (MySQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(new JFrame(), "ERRO! Existem contatos que estão utilizando esta Área.", "ERRO!",
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
