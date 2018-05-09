/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import view.legacy.consultaAreaPanel;
import externos.Excel;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JFrame;
import model.area;
import model.areaDAO;
import model.contato;
import model.contatoDAO;
import model.empresa;
import model.empresaDAO;

/**
 *
 * @author Victor
 */
public class mainFrame extends JFrame {

    private areaDAO areaDao;
    private contatoDAO contatoDao;
    mainMenuPanel main;
    consultaAreaPanel cadastroAreas;
    newConsultaEmpresa newEmpresas;
    // cadastroEmpresa;
    private empresaDAO empresaDao;
    private newConsultaContato cadastroContatos;
    private relatorioContato relatorioContatos;
    private newConsultaArea newArea;

    public mainFrame() {
        contatoDao = new contatoDAO();
        areaDao = new areaDAO();
        empresaDao = new empresaDAO();
        main = new mainMenuPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1200, 900);
        this.setVisible(true);
        this.setTitle("SWB");
        this.add(main);
    }

//index: 1. main / 2. cadastroArea / 3. cadastroEmpresa
    public void switchPanels(int index) throws ParseException {
        switch (index) {
            case 1:
                limpaPanels();
                main = new mainMenuPanel();
                main.setVisible(true);
                this.add(main);
                break;
            case 2:
                limpaPanels();
                newArea = new newConsultaArea();
                newArea.setVisible(true);
                this.add(newArea);
                break;
            case 3:
                limpaPanels();
                newEmpresas = new newConsultaEmpresa();
                newEmpresas.setVisible(true);
                this.add(newEmpresas);
                break;
            case 4:
                limpaPanels();
                cadastroContatos = new newConsultaContato();
                cadastroContatos.setVisible(true);
                this.add(cadastroContatos);
                break;
            case 5:
                limpaPanels();
                relatorioContatos = new relatorioContato();
                relatorioContatos.setVisible(true);
                this.add(relatorioContatos);
            default:
                break;
        }
        repaint();
    }

    private void limpaPanels() {
        if (main != null) {
            main.setVisible(false);                                                 //é redundante?
            remove(main);
        }
//        if (cadastroAreas != null) {
//            cadastroAreas.setVisible(false);
//            remove(cadastroAreas);
//        }
        if (newArea != null) {
            newArea.setVisible(false);
            remove(newArea);
        }
        if (newEmpresas != null) {
            newEmpresas.setVisible(false);
            remove(newEmpresas);
        }
        if (cadastroContatos != null) {
            cadastroContatos.setVisible(false);
            remove(cadastroContatos);
        }
        if (relatorioContatos != null) {
            relatorioContatos.setVisible(false);
            remove(relatorioContatos);
        }
    }

    public void cadastroAreaDAO(String descricao) throws SQLException {
        areaDao.adicionaArea(descricao);
    }

    void cadastroEmpresa(String text, String text0) throws SQLException {
        empresaDao.adicionaEmpresa(text, text0);
    }

    public area consultaArea(String descricao) throws SQLException {
        return areaDao.findAreaByDescricao(descricao);
    }

    public empresa consultaEmpresa(String nome) throws SQLException {
        return empresaDao.findEmpresaByNome(nome);
    }

    public ArrayList<area> consultaArea(String dataInicial, String dataFinal) throws SQLException {
        return areaDao.findAreaByData(dataInicial, dataFinal);
    }

    ArrayList<empresa> consultaEmpresa(String dataInicialQuery, String dataFinalQuery) throws SQLException {
        return empresaDao.findEmpresaByData(dataInicialQuery, dataFinalQuery);
    }

    public void atualizaArae(int i, area areaNovo) throws SQLException {
        areaDao.atualizaArea(i, areaNovo);
    }

    void atualizaEmpresa(String id, empresa get) throws SQLException {
        empresaDao.atualizaEmpresa(id, get);
    }

    void exportarDados(ArrayList<String> colunas, ArrayList<Object> objects) throws IOException {
        Excel exportar = new Excel();
        exportar.Escrever(colunas, objects);
    }

    empresa consultaEmpresaCNPJ(String text) throws SQLException {
        return empresaDao.findEmpresaByCNPJ(text);
    }
//            "Nome:", nome,
//            "CPF:", codigoCPF,
//            "Telefone:", telefoneResidencial,
//            "Celular:", telefoneCelular,
//            "E-mail:", email,
//            "Área:", areaCombobox

    void cadastroContato(String nome, String cpf, String telefone_residencial, String telefone_celular, String email, int ref_area, int ref_empresa) throws ParseException, SQLException {
        contatoDao.adicionaContato(new contato(nome, cpf, telefone_residencial, telefone_celular, email, ref_area, ref_empresa));
    }

    contato consultaContato(String nome) throws SQLException, ParseException {
        return contatoDao.consultaContatoByName(nome);
    }

    ArrayList<contato> consultaContato(String dataInicial, String dataFinal) throws SQLException, ParseException {
        return contatoDao.consultaContatoByDate(dataInicial, dataFinal);
    }

    contato consultaContatoCPF(String text) throws SQLException, ParseException {
        return contatoDao.consultaContatoByCPF(text);
    }

    void atualizaContato(int id, contato get) throws SQLException {
        contatoDao.atualizaContato(id, get);
    }

    ArrayList<contato> relatorioContatos(String query) throws SQLException, ParseException {
        return contatoDao.relatorio(query);
    }

    ArrayList<area> consultaAreaQuery(String query) throws SQLException {
        return areaDao.findQuery(query);
    }

    void deletaAreas(String query) throws SQLException {
        areaDao.deleteAreas(query);
    }

    ArrayList<empresa> consultaEmpresaQuery(String query) throws SQLException {
        return empresaDao.findQuery(query);
    }

    void deletaEmpresa(String query) throws SQLException {
        empresaDao.deletaEmpresas(query);
    }

    ArrayList<contato> consultaContatosQuery(String query) throws ParseException, SQLException {
        return contatoDao.consultaQuery(query);
    }
}
