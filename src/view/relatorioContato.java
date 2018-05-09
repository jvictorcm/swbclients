/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import view.legacy.consultaAreaPanel;
import externos.relatorioTable;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import model.area;
import model.areaDAO;
import model.contato;
import model.empresa;
import model.empresaDAO;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author Victor
 */
public class relatorioContato extends JPanel {

    DefaultTableModel model;
    private JButton buttonVoltar;
    private JButton jButton3;
    private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8;
    private JTextField nomeContatoFIELD, nomeEMpresaFIELD, areaField;
    private JFormattedTextField dataFinalField, dataInicialField, cpfField, cnpjField;
    private JButton buttonRelatorio;
    private boolean erro = false;
    private JButton buttonExportar;

    private JScrollPane scrollpane;
    private JTable tableResultados;
    String query = "";
    private empresaDAO empresaDao;
    private areaDAO areaDao;

    mainFrame a;
    Component teste;
    private ArrayList<contato> resultado = new ArrayList<>();
    private JLabel newLabel1;
    private JButton cadastroEmpresa;
    private JButton cadastroContato;
    private JLabel newLabel2;
    private JButton relatorioContato;

    public relatorioContato() throws ParseException {
        initComponents();
    }

    private void jRelatorioActionPerformed(ActionEvent evt) throws SQLException, ParseException {
        String dataInicial = dataInicialField.getText();
        if (dataInicial.equals("  /  /    ")) {
            dataInicial = "";
        }
        String dataFinal = dataFinalField.getText();
        if (dataFinal.equals("  /  /    ")) {
            dataFinal = "";
        }
        String cpf = cpfField.getText();
        if (cpf.equals("   .   .   -  ")) {
            cpf = "";
        }
        String nomeContato = nomeContatoFIELD.getText();
        String cnpj = cnpjField.getText();
        if (cnpj.equals("  .   .   /    -  ")) {
            cnpj = "";
        }
        String nomeEMpresa = nomeEMpresaFIELD.getText();
        String nomeArea = areaField.getText();
        //GERAR RELATORIO IMPLEMENTAR

        query = "select c.*, e.nome as nomeDaEmpresa, a.descricao as nomeDaArea from contato AS c \n"
                + "JOIN empresa as e ON c.ref_empresa  = e.`ID`\n"
                + "JOIN area as a ON c.ref_area = a.`ID`\n"
                + "Where ";
        if ((!dataFinal.equals("") && dataInicial.equals("")) || (dataFinal.equals("") && !dataInicial.equals(""))) {
            System.out.println("erro de data incompleta");
        } else {
            if (!(dataFinal.equals("") && dataInicial.equals(""))) {
                String dataInicialQuery = dataInicial.substring(6, 10) + "-" + dataInicial.substring(3, 5) + "-" + dataInicial.substring(0, 2);
                String dataFinalQuery = dataFinal.substring(6, 10) + "-" + dataFinal.substring(3, 5) + "-" + dataFinal.substring(0, 2);
                query = query + "c.data between " + "'" + dataInicialQuery + "'" + " and " + "'" + dataFinalQuery + "'";
            }
            if (!cpf.equals("")) {
                addAND();
                query = query + "c.cpf='" + cpf + "'";
            }
            if (!nomeContato.equals("")) {
                addAND();
                query = query + "c.nome='" + nomeContato + "'";
            }
            if (!cnpj.equals("")) {
                empresaDao = new empresaDAO();
                empresa a = empresaDao.findEmpresaByCNPJ(cnpj);
                if (a == null) {
                    erro = true;
                    JOptionPane.showMessageDialog(null,
                            "O CNPJ: " + cnpj + " não consta na base de dados");
                    //System.out.println("CNPJ inexistente");
                } else {
                    addAND();
                    query = query + "ref_empresa='" + a.getID() + "'";
                }
            }
            if (!nomeEMpresa.equals("")) {
                empresaDao = new empresaDAO();
                empresa a = empresaDao.findEmpresaByNome(nomeEMpresa);
                if (a == null) {
                    erro = true;
                    JOptionPane.showMessageDialog(null,
                            "A Empresa de Nome: " + nomeEMpresa + " não consta na base de dados");
                    //System.out.println("Nome inexistente");
                } else {
                    addAND();
                    query = query + "ref_empresa='" + a.getID() + "'";
                }
            }
            if (!nomeArea.equals("")) {
                areaDao = new areaDAO();
                area a = areaDao.findAreaByDescricao(nomeArea);
                if (a == null) {
                    erro = true;
                    JOptionPane.showMessageDialog(null,
                            "A Área de Nome: " + nomeArea + " não consta na base de dados");
                    System.out.println("Nome inexistente");
                } else {
                    addAND();
                    query = query + "ref_area='" + a.getID() + "'";
                }
            }
        }
        if (!erro) {
            teste = SwingUtilities.getWindowAncestor(buttonVoltar);
            a = (mainFrame) teste;
            if (query.endsWith("WHERE ")) {
                query = "";
            }
            System.out.println(query);
            resultado.clear();
            resultado = a.relatorioContatos(query);
            preperajtable();

        } else {
            erro = false;
        }
    }

    public void addAND() {
        if (!query.endsWith("Where ")) {
            query = query + " AND ";
        }
    }

    private void initComponents() throws ParseException {
        buttonExportar = new JButton("Exportar");
        buttonVoltar = new JButton();
        buttonRelatorio = new JButton();
        jButton3 = new JButton();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        MaskFormatter msk = new MaskFormatter("##/##/####");
        MaskFormatter msk2 = new MaskFormatter("##/##/####");
        dataFinalField = new JFormattedTextField(msk);
        dataFinalField.setFocusLostBehavior(JFormattedTextField.PERSIST);
        dataInicialField = new JFormattedTextField(msk2);
        dataInicialField.setFocusLostBehavior(JFormattedTextField.PERSIST);
        MaskFormatter msk3 = new MaskFormatter("###.###.###-##");
        cpfField = new JFormattedTextField(msk3);
        cpfField.setFocusLostBehavior(JFormattedTextField.PERSIST);

        nomeContatoFIELD = new JTextField();
        MaskFormatter msk4 = new MaskFormatter("##.###.###/####-##");
        cnpjField = new JFormattedTextField(msk4);
        cnpjField.setFocusLostBehavior(JFormattedTextField.PERSIST);
        nomeEMpresaFIELD = new JTextField();
        areaField = new JTextField();

        setMaximumSize(new Dimension(1200, 900));
        setMinimumSize(new Dimension(1200, 900));
        setPreferredSize(new Dimension(1200, 900));
        setLayout(new AbsoluteLayout());

        buttonVoltar.setText("VOLTAR");
        buttonVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    jButton2ActionPerformed(evt);
                } catch (ParseException ex) {
                    Logger.getLogger(relatorioContato.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void jButton2ActionPerformed(ActionEvent evt) throws ParseException {
                Component teste = SwingUtilities.getWindowAncestor(buttonVoltar);
                mainFrame a = (mainFrame) teste;
                a.switchPanels(1);
            }
        });
        add(buttonVoltar, new AbsoluteConstraints(10, 10, -1, -1));

        buttonRelatorio.setText("Gerar");
        buttonRelatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    jRelatorioActionPerformed(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(relatorioContato.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(relatorioContato.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        add(buttonRelatorio, new AbsoluteConstraints(880, 250, 110, 30));

        //--------------------
        jLabel1 = new JLabel();
        jLabel1.setFont(new Font("Tahoma", 0, 24));
        jLabel1.setText("RELATÓRIO DE CONTATOS");
        jLabel1.setVisible(true);
        add(jLabel1, new AbsoluteConstraints(490, 10));

        jLabel2.setText("Data Final:");
        add(jLabel2, new AbsoluteConstraints(280, 200, -1, -1));

        jLabel3.setText("Data Inicial:");
        add(jLabel3, new AbsoluteConstraints(280, 170, -1, -1));

        dataFinalField.setText("");
        add(dataFinalField, new AbsoluteConstraints(350, 190, 70, 25));

        dataInicialField.setText("");
        add(dataInicialField, new AbsoluteConstraints(350, 160, 70, 25));

        jLabel4.setText("CPF:");
        add(jLabel4, new AbsoluteConstraints(430, 200, -1, -1));

        jLabel5.setText("Nome do Contato:");
        add(jLabel5, new AbsoluteConstraints(430, 170, -1, -1));

        cpfField.setText("");
        add(cpfField, new AbsoluteConstraints(535, 190, 110, 25));

        nomeContatoFIELD.setText("");
        add(nomeContatoFIELD, new AbsoluteConstraints(535, 160, 110, 25));

        jLabel6.setText("CNPJ:");
        add(jLabel6, new AbsoluteConstraints(650, 200, -1, -1));

        jLabel7.setText("Nome da Empresa:");
        add(jLabel7, new AbsoluteConstraints(650, 170, -1, -1));

        jLabel8.setText("Área:");
        add(jLabel8, new AbsoluteConstraints(650, 230, -1, -1));

        cnpjField.setText("");
        add(cnpjField, new AbsoluteConstraints(760, 190, 120, 25));

        nomeEMpresaFIELD.setText("");
        add(nomeEMpresaFIELD, new AbsoluteConstraints(760, 160, 120, 25));

        areaField.setText("");
        add(areaField, new AbsoluteConstraints(760, 220, 120, 25));

        //--------------------
        scrollpane = new JScrollPane();
        tableResultados = new JTable(model);
        scrollpane.setViewportView(tableResultados);
        add(scrollpane, new AbsoluteConstraints(232, 360, 740, 350));
        scrollpane.setVisible(true);
        tableResultados.setVisible(true);

        buttonExportar.addActionListener((ActionEvent e) -> {
            teste = SwingUtilities.getWindowAncestor(buttonExportar);
            a = (mainFrame) teste;
            ArrayList<String> colunas = new ArrayList<>();
            colunas.add("Código");
            colunas.add("Nome");
            colunas.add("CPF");
            colunas.add("Telefone Residencial");
            colunas.add("Telefone Celular");
            colunas.add("Email");
            colunas.add("Data de Cadastro");
            colunas.add("Nome da Empresa");
            colunas.add("Area");

            try {
                ArrayList<Object> objetos = new ArrayList<>();
                Iterator asd = resultado.iterator();
                while (asd.hasNext()) {
                    objetos.add(asd.next());
                }
                a.exportarDados(colunas, objetos);
            } catch (IOException ex) {
                Logger.getLogger(consultaAreaPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        buttonExportar.setVisible(false);
        add(buttonExportar, new AbsoluteConstraints(1000, 500, 170, 70));
        newLabel1 = new JLabel();
        newLabel1.setText("Cadastro:");
        newLabel1.setFont(new Font("Tahoma", 0, 18));
        newLabel1.setVisible(true);
        this.add(newLabel1, new AbsoluteConstraints(10, 50));
        JButton cadastroArea = new JButton("de Área");
        cadastroArea.setVisible(true);
        cadastroArea.setFont(new Font("Tahoma", 0, 16));
        cadastroArea.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                trocaParaAreaPerformed(evt);
            } catch (ParseException ex) {
                Logger.getLogger(newConsultaEmpresa.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //cadastroArea.setEnabled(false);
        this.add(cadastroArea, new AbsoluteConstraints(20, 70, 120, 40));
        cadastroEmpresa = new JButton("de Empresa");
        cadastroEmpresa.setVisible(true);
        cadastroEmpresa.setFont(new Font("Tahoma", 0, 16));
        //cadastroEmpresa.setEnabled(false);
        cadastroEmpresa.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                trocaCadEmpresaPerformed(evt);
            } catch (ParseException ex) {
                Logger.getLogger(newConsultaContato.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.add(cadastroEmpresa, new AbsoluteConstraints(20, 110, 120, 40));
        cadastroContato = new JButton("de Contato");
        cadastroContato.setVisible(true);
        cadastroContato.setFont(new Font("Tahoma", 0, 16));
        //cadastroContato.setEnabled(false);
        cadastroContato.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                trocaParaContatoEvent(evt);
            } catch (ParseException ex) {
                Logger.getLogger(newConsultaContato.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.add(cadastroContato, new AbsoluteConstraints(20, 150, 120, 40));
        newLabel2 = new JLabel();
        newLabel2.setText("Relatório:");
        newLabel2.setFont(new Font("Tahoma", 0, 18));
        newLabel2.setVisible(true);
        this.add(newLabel2, new AbsoluteConstraints(10, 190));
        relatorioContato = new JButton("de Contato");
        relatorioContato.setVisible(true);
        relatorioContato.setEnabled(false);
        relatorioContato.setFont(new Font("Tahoma", 0, 16));
        relatorioContato.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                trocaRelatorioPerformed(evt);
            } catch (ParseException ex) {
                Logger.getLogger(newConsultaContato.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.add(relatorioContato, new AbsoluteConstraints(20, 210, 120, 40));

    }

    private void preperajtable() throws SQLException {
        scrollpane.setVisible(true);
        tableResultados.setVisible(true);
        jButton3.setVisible(true);
        buttonExportar.setVisible(true);
        if (query.equals("")) {

        } else {
            tableResultados.setModel(new relatorioTable(new String[]{
                "Codigo", "Nome", "CPF", "Tel. Res.", "Tel. Cel.", "Email", "Empresa", "Área"
            }, resultado.size()));
            limpatable();
            for (int i = 0; i <= resultado.size() - 1; i++) {
                if (resultado.get(i) != null) {
                    addTable(i, resultado.get(i));
                } else {
                    break;
                }
            }
        }
    }

    private void addTable(int indice, contato conteudo) throws SQLException {
//        tableResultados.setValueAt(conteudo.getID(), indice, 0);
//        tableResultados.setValueAt(conteudo.getNome(), indice, 1);
//        tableResultados.setValueAt(conteudo.getCpf(), indice, 2);
//        tableResultados.setValueAt(conteudo.getTelRes(), indice, 3);
//        tableResultados.setValueAt(conteudo.getTelcel(), indice, 4);
//        ArrayList<area> retorno = a.consultaArea("0000-00-00", "9999-01-01");
//        ArrayList<empresa> retornoEmpresa = a.consultaEmpresa("0000-00-00", "9999-01-01");
//        String Area = "";
//        String Empresa = "";
//        for (int i = 0; i < retorno.size(); i++) {
//            if (retorno.get(i).getID() == conteudo.getIDarea()) {
//                Area = retorno.get(i).getDescricao();
//                break;
//            }
//        }
//        for (int i = 0; i < retornoEmpresa.size(); i++) {
//            if (retornoEmpresa.get(i).getID().equals(String.valueOf(conteudo.getIDempresa()))) {
//                Empresa = retornoEmpresa.get(i).getNome();
//                break;
//            }
//        }
//        tableResultados.setValueAt(Empresa, indice, 5);
//        tableResultados.setValueAt(Area, indice, 6);
        tableResultados.setValueAt(conteudo.getID(), indice, 0);
        tableResultados.setValueAt(conteudo.getNome(), indice, 1);
        tableResultados.setValueAt(conteudo.getCpf(), indice, 2);
        tableResultados.setValueAt(conteudo.getTelRes(), indice, 3);
        tableResultados.setValueAt(conteudo.getTelcel(), indice, 4);
//        teste = SwingUtilities.getWindowAncestor(buttonVoltar);
//        a = (mainFrame) teste;
//        ArrayList<area> retorno = a.consultaArea("0000-00-00", "9999-01-01");
//        ArrayList<empresa> retornoEmpresa = a.consultaEmpresa("0000-00-00", "9999-01-01");
        String Area = conteudo.getNomedaArea();
        String Empresa = conteudo.getNomeDaEmpresa();
//        for (int i = 0; i < retorno.size(); i++) {
//            if (retorno.get(i).getID() == conteudo.getIDarea()) {
//                Area = retorno.get(i).getDescricao();
//                break;
//            }
//        }
//        for (int i = 0; i < retornoEmpresa.size(); i++) {
//            if (retornoEmpresa.get(i).getID().equals(String.valueOf(conteudo.getIDempresa()))) {
//                Empresa = retornoEmpresa.get(i).getNome();
//                break;
//            }
//        }
        tableResultados.setValueAt(conteudo.getEmail(), indice, 5);
        tableResultados.setValueAt(Empresa, indice, 6);
        tableResultados.setValueAt(Area, indice, 7);
    }

    private void limpatable() {
        if (tableResultados.isVisible()) {
            for (int i = 0; i < tableResultados.getRowCount(); i++) {
                tableResultados.setValueAt("", i, 0);
                tableResultados.setValueAt("", i, 1);
                tableResultados.setValueAt("", i, 2);
                tableResultados.setValueAt("", i, 3);
                tableResultados.setValueAt("", i, 4);
                tableResultados.setValueAt("", i, 5);
                tableResultados.setValueAt("", i, 6);
            }
        }
    }

    private void trocaParaAreaPerformed(ActionEvent evt) throws ParseException {
        Component teste = SwingUtilities.getWindowAncestor(newLabel1);
        mainFrame a = (mainFrame) teste;
        a.switchPanels(2);
    }

    private void trocaCadEmpresaPerformed(ActionEvent evt) throws ParseException {
        Component teste = SwingUtilities.getWindowAncestor(newLabel1);
        mainFrame a = (mainFrame) teste;
        a.switchPanels(3);
    }

    private void trocaRelatorioPerformed(ActionEvent evt) throws ParseException {
        Component teste = SwingUtilities.getWindowAncestor(newLabel1);
        mainFrame a = (mainFrame) teste;
        a.switchPanels(5);
    }

    private void trocaParaContatoEvent(ActionEvent evt) throws ParseException {
        Component teste = SwingUtilities.getWindowAncestor(newLabel1);
        mainFrame a = (mainFrame) teste;
        a.switchPanels(4);
    }

}
