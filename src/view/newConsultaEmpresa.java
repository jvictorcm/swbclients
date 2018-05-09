
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import view.legacy.consultaAreaPanel;
import view.legacy.cadastroAreaPanel;
import externos.newTabbleModelCNPJ;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import model.area;
import model.empresa;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author Victor
 */
public class newConsultaEmpresa extends javax.swing.JPanel {

    DefaultTableModel tableModel;
    DefaultTableModel model;
    ArrayList<empresa> empresas;
    private javax.swing.JButton deletarButton;
    private javax.swing.JButton buttonData;
    private javax.swing.JButton buttonDescricao;
    private javax.swing.JButton buttonCadastro;
    private javax.swing.JButton buttonVoltar;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private JButton buttonExportar, cadastroArea, cadastroEmpresa, cadastroContato, relatorioContato;
    private JLabel jLabel1, newLabel1, newLabel2, newLabel6;
    JTextField descFieldConsulta;
    JFormattedTextField dataInicialFieldConsulta, dataFinalFieldConsulta;
    JTextField descricaoField;
    JFormattedTextField CNPJField;
    private JFormattedTextField CNPJFieldPesquisa;

    /**
     * Creates new form NewJPanel
     *
     * @throws java.text.ParseException
     */
    public newConsultaEmpresa() throws ParseException {
        initComponents();
        empresas = new ArrayList<>();
        buttonDescricao.setVisible(false);
        buttonData.setVisible(false);
        //        jTable2.setVisible(false);
        jScrollPane2.setVisible(false);
        buttonExportar.setVisible(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() throws ParseException {
        setMaximumSize(new Dimension(1200, 900));
        setMinimumSize(new Dimension(1200, 900));
        setPreferredSize(new Dimension(1200, 900));
        setLayout(new AbsoluteLayout());
        ////--init--///
        buttonExportar = new JButton("Exportar");
        buttonCadastro = new JButton();
        buttonDescricao = new JButton();
        deletarButton = new JButton();
        buttonData = new JButton();
        jScrollPane2 = new JScrollPane();
        buttonVoltar = new JButton();
        jButton3 = new JButton();
//
//
//
//
//
//
//
//
//
//
        newLabel1 = new JLabel();
        newLabel1.setText("Cadastro:");
        newLabel1.setFont(new Font("Tahoma", 0, 18));
        newLabel1.setVisible(true);
        this.add(newLabel1, new AbsoluteConstraints(10, 50));
        cadastroArea = new JButton("de Área");
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
        cadastroEmpresa.setEnabled(false);
        this.add(cadastroEmpresa, new AbsoluteConstraints(20, 110, 120, 40));
        cadastroContato = new JButton("de Contato");
        cadastroContato.setVisible(true);
        cadastroContato.setFont(new Font("Tahoma", 0, 16));
        cadastroContato.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                trocaCadContatoPerformed(evt);
            } catch (ParseException ex) {
                Logger.getLogger(newConsultaEmpresa.class.getName()).log(Level.SEVERE, null, ex);
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
        relatorioContato.setFont(new Font("Tahoma", 0, 16));

        relatorioContato.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                trocaRelatorioPerformed(evt);
            } catch (ParseException ex) {
                Logger.getLogger(newConsultaContato.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.add(relatorioContato, new AbsoluteConstraints(20, 210, 120, 40));
        JLabel newLabel4 = new JLabel();
        newLabel4.setText("Nome:");
        newLabel4.setFont(new Font("Tahoma", 0, 18));
        newLabel4.setVisible(true);
        this.add(newLabel4, new AbsoluteConstraints(200, 170));
        descricaoField = new JTextField();
        descricaoField.setVisible(true);
        this.add(descricaoField, new AbsoluteConstraints(280, 170, 200, 25));
        JLabel newLabel52 = new JLabel();
        newLabel52.setText("CNPJ:");
        newLabel52.setFont(new Font("Tahoma", 0, 18));
        newLabel52.setVisible(true);
        this.add(newLabel52, new AbsoluteConstraints(200, 205));
        MaskFormatter formaterCNPJ = new MaskFormatter("##.###.###/####-##");
        CNPJField = new JFormattedTextField(formaterCNPJ);
        CNPJField.setVisible(true);
        this.add(CNPJField, new AbsoluteConstraints(280, 205, 200, 25));
        buttonCadastro.setText("Nova Entrada");
        buttonCadastro.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                buttonCadastroPerformed(evt);
            } catch (SQLException ex) {
                Logger.getLogger(newConsultaArea.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        add(buttonCadastro, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 130, 35));
        //
//        JLabel newLabel3 = new JLabel();
//        newLabel3.setText("Código:");
//        newLabel3.setFont(new Font("Tahoma", 0, 18));
//        newLabel3.setVisible(true);
//        this.add(newLabel3, new AbsoluteConstraints(700, 100));
//        JTextField idField = new JTextField();
//        //idField.setEditable(false);
//        idField.setVisible(true);
//        this.add(idField, new AbsoluteConstraints(800, 100, 60, 25));
        JLabel newLabel512 = new JLabel();
        newLabel512.setText("CNPJ:");
        newLabel512.setFont(new Font("Tahoma", 0, 18));
        newLabel512.setVisible(true);
        this.add(newLabel512, new AbsoluteConstraints(700, 135));
        MaskFormatter formaterCNPJ2 = new MaskFormatter("##.###.###/####-##");
        CNPJFieldPesquisa = new JFormattedTextField(formaterCNPJ2);
        CNPJFieldPesquisa.setFocusLostBehavior(JFormattedTextField.PERSIST);
        CNPJFieldPesquisa.setVisible(true);
        this.add(CNPJFieldPesquisa, new AbsoluteConstraints(800, 135, 200, 25));
        JLabel newLabel5 = new JLabel();
        newLabel5.setText("Descrição:");
        newLabel5.setFont(new Font("Tahoma", 0, 18));
        newLabel5.setVisible(true);
        this.add(newLabel5, new AbsoluteConstraints(700, 100));
        descFieldConsulta = new JTextField();
        descFieldConsulta.setVisible(true);
        this.add(descFieldConsulta, new AbsoluteConstraints(800, 100, 200, 25));
        MaskFormatter formaterData = new MaskFormatter("##/##/####");
        MaskFormatter formaterData2 = new MaskFormatter("##/##/####");
        newLabel6 = new JLabel();
        newLabel6.setText("Data Inicial:");
        newLabel6.setFont(new Font("Tahoma", 0, 18));
        newLabel6.setVisible(true);
        this.add(newLabel6, new AbsoluteConstraints(700, 170));
        dataInicialFieldConsulta = new JFormattedTextField(formaterData);
        dataInicialFieldConsulta.setFocusLostBehavior(JFormattedTextField.PERSIST);
        dataInicialFieldConsulta.setVisible(true);
        this.add(dataInicialFieldConsulta, new AbsoluteConstraints(800, 170, 70, 25));
        JLabel newLabel7 = new JLabel();
        newLabel7.setText("Data Final:");
        newLabel7.setFont(new Font("Tahoma", 0, 18));
        newLabel7.setVisible(true);
        this.add(newLabel7, new AbsoluteConstraints(700, 205));
        dataFinalFieldConsulta = new JFormattedTextField(formaterData2);
        dataFinalFieldConsulta.setFocusLostBehavior(JFormattedTextField.PERSIST);
        dataFinalFieldConsulta.setVisible(true);
        this.add(dataFinalFieldConsulta, new AbsoluteConstraints(800, 205, 70, 25));
        JButton buttonPesquisa = new JButton();
        buttonPesquisa.setText("Pesquisar");
        buttonPesquisa.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                pesquisarButtonPerformed(evt);
            } catch (SQLException ex) {
                Logger.getLogger(newConsultaArea.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        add(buttonPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 240, 100, 35));
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        buttonExportar.addActionListener((ActionEvent e) -> {
            Component teste = SwingUtilities.getWindowAncestor(buttonCadastro);
            mainFrame a = (mainFrame) teste;
            ArrayList<String> colunas = new ArrayList<>();
            colunas.add("Código");
            colunas.add("Descrição");
            colunas.add("Data de Cadastro");
            try {
                ArrayList<Object> objetos = new ArrayList<>();
                Iterator asd = empresas.iterator();
                while (asd.hasNext()) {
                    objetos.add(asd.next());
                }
                a.exportarDados(colunas, objetos);
            } catch (IOException ex) {
                Logger.getLogger(consultaAreaPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        add(buttonExportar, new AbsoluteConstraints(372, 800, -1, -1));

        buttonDescricao.setText("por Descrição");
        buttonDescricao.addActionListener((java.awt.event.ActionEvent evt) -> {
            descricaoActionPerformed(evt);
        });
        add(buttonDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 700, 140, 70));

        deletarButton.setText("Excluir Entradas Selecionadas");
        deletarButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                deleteActionPerformed(evt);
            } catch (SQLException ex) {
                Logger.getLogger(newConsultaArea.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        deletarButton.setVisible(false);
        add(deletarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 800, -1, -1));

        buttonData.setText("por Data");
        buttonData.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                dataActionPerformed(evt);
            } catch (ParseException ex) {
                Logger.getLogger(consultaAreaPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        add(buttonData, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 150, 140, 70));

        jTable2 = new JTable(model);
        jScrollPane2.setViewportView(jTable2);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 360, 740, -1));

        buttonVoltar.setText("VOLTAR");
        buttonVoltar.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                jButton2ActionPerformed(evt);
            } catch (ParseException ex) {
                Logger.getLogger(consultaAreaPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        add(buttonVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jButton3.setText("Atualizar Dados");
        jButton3.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                jButton3ActionPerformed(evt);
            } catch (ParseException ex) {
                Logger.getLogger(consultaAreaPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(newConsultaArea.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 800, -1, -1));
        jButton3.setVisible(false);
        jLabel1 = new JLabel();
        jLabel1.setFont(new Font("Tahoma", 0, 24));
        jLabel1.setText("CADASTRO DE EMPRESA");
        jLabel1.setVisible(true);
        add(jLabel1, new AbsoluteConstraints(490, 10));
    }// </editor-fold>                        

    private void pesquisarButtonPerformed(java.awt.event.ActionEvent evt) throws SQLException {
        String query = "SELECT * FROM empresa WHERE ";
        String datainicial, datafinal, descricao, cnpj;
        if (dataInicialFieldConsulta.getText().equals("  /  /    ")) {
            datainicial = "";
        } else {
            datainicial = dataInicialFieldConsulta.getText();
        }
        if (dataFinalFieldConsulta.getText().equals("  /  /    ")) {
            datafinal = "";
        } else {
            datafinal = dataFinalFieldConsulta.getText();
        }
        if (CNPJFieldPesquisa.getText().equals("  .   .   /    -  ")) {
            cnpj = "";
        } else {
            cnpj = CNPJFieldPesquisa.getText();
        }
        descricao = descFieldConsulta.getText();
        //preparou
        if (!descricao.equals("")) {
            query = query + "nome='" + descFieldConsulta.getText() + "'";
        }
        if ((!datainicial.equals("") && datafinal.equals("")) || (datainicial.equals("") && !datafinal.equals(""))) {
            JOptionPane.showMessageDialog(null,
                    "Erro! Favor informar as duas datas ou deixar ambas vazias.");
            empresas.clear();
        } else {
            if (datainicial.equals("")) {

            } else {
                String dataInicialQuery = datainicial.substring(6, 10) + "-" + datainicial.substring(3, 5) + "-" + datainicial.substring(0, 2);
                String dataFinalQuery = datafinal.substring(6, 10) + "-" + datafinal.substring(3, 5) + "-" + datafinal.substring(0, 2);
                if (query.equals("SELECT * FROM empresa WHERE ")) {

                } else {
                    query = query + " AND ";
                }
                query = query + "data between " + "'" + dataInicialQuery + "'" + " and " + "'" + dataFinalQuery + "'";
            }

        }
        if (!cnpj.equals("")) {
            if (query.endsWith("WHERE ")) {
                query = query + "cnpj='" + cnpj + "'";
            } else {
                query = query + " AND " + "cnpj='" + cnpj + "'";
            }
        }
        if (query.equals("SELECT * FROM empresa WHERE ")) {
            query = "";
            JOptionPane.showMessageDialog(null,
                    "Preencher algum dado para pesquisa.");

        } else {
            //System.out.println(query);
            Component teste = SwingUtilities.getWindowAncestor(buttonCadastro);
            mainFrame a = (mainFrame) teste;
            empresas = a.consultaEmpresaQuery(query);
            if (empresas == null || empresas.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Não existe nenhum resultado para esta pesquisa.");
                limpatable();
                jScrollPane2.setVisible(false);
                jTable2.setVisible(false);
                jButton3.setVisible(false);
                buttonExportar.setVisible(false);
            } else {
                preperajtable();
            }
        }
    }

    private void preperajtable() throws SQLException {
        jScrollPane2.setVisible(true);
        jTable2.setVisible(true);
        jButton3.setVisible(true);
        buttonExportar.setVisible(true);
        deletarButton.setVisible(true);

        jTable2.setModel(new newTabbleModelCNPJ(new String[]{
            "Codigo", "Nome", "CNPJ", "Data"
        }, empresas.size()));
        limpatable();
        for (int i = 0; i <= empresas.size() - 1; i++) {
            if (empresas.get(i) != null) {
                addTable(i, empresas.get(i));
            } else {
                break;
            }
        }
    }

    private void descricaoActionPerformed(java.awt.event.ActionEvent evt) {
        String descricao;
        empresas.clear();
        while (true) {
            try {
                descricao = JOptionPane.showInputDialog(null, "Informe a descrição a ser consultada:", "Consulta de Área", JOptionPane.PLAIN_MESSAGE);
                if (descricao.equals("null") || descricao.equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "Favor informar uma descrição.");
                } else {
                    Component teste = SwingUtilities.getWindowAncestor(buttonCadastro);
                    mainFrame a = (mainFrame) teste;
                    try {
                        area retorno = a.consultaArea(descricao);
                        if (retorno != null) {
                            //empresas.add(a.consultaArea(descricao));
                        }

                        if (empresas.isEmpty()) {
                            JOptionPane.showMessageDialog(null,
                                    "Não existe cadastro de descrição \"" + descricao + "\"");
                            jTable2.setVisible(false);
                            limpatable();
                        } else {
                            jScrollPane2.setVisible(true);
                            jTable2.setVisible(true);
                            jButton3.setVisible(true);
                            buttonExportar.setVisible(true);
                            deletarButton.setVisible(true);
                            jTable2.setModel(new newTabbleModelCNPJ(new String[]{
                                "Codigo", "Descrição", "Data"
                            }, 1));
                            limpatable();
                            addTable(0, empresas.get(0));
                        }

                        //addToTable(retorno);
                        break;
                    } catch (SQLException ex) {
                        Logger.getLogger(cadastroAreaPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (NullPointerException e) {
                break;
            }
        }
    }

    private void dataActionPerformed(java.awt.event.ActionEvent evt) throws ParseException {
//        area[] lalala = teste.findAreaByData("2018-04-01", "2018-04-10");
        while (true) {
            empresas.clear();
            MaskFormatter formaterData = new MaskFormatter("##/##/####");
            MaskFormatter formaterData2 = new MaskFormatter("##/##/####");
            JFormattedTextField dataInicial = new JFormattedTextField(formaterData);
            JFormattedTextField dataFinal = new JFormattedTextField(formaterData2);
            Object[] message = {
                "Data Inicial:", dataInicial,
                "Data Final:", dataFinal
            };
            int option = JOptionPane.showConfirmDialog(null, message, "Informe a data inicial e final para pesquisa", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.CANCEL_OPTION) {
                break;
            } else if (dataFinal.getText().equals("") || dataInicial.getText().equals("")) {
                JOptionPane.showMessageDialog(null,
                        "Favor informar datas válidas no padrão dd/mm/yyyy.");
                continue;
            }
            if (option == JOptionPane.OK_OPTION) {
                Component teste = SwingUtilities.getWindowAncestor(buttonCadastro);
                mainFrame a = (mainFrame) teste;
                String dataInicialQuery = dataInicial.getText().substring(6, 10) + "-" + dataInicial.getText().substring(3, 5) + "-" + dataInicial.getText().substring(0, 2);
                String dataFinalQuery = dataFinal.getText().substring(6, 10) + "-" + dataFinal.getText().substring(3, 5) + "-" + dataFinal.getText().substring(0, 2);
                // empresas = a.consultaArea(dataInicialQuery, dataFinalQuery);
            } else {
            }
            jScrollPane2.setVisible(true);
            jTable2.setVisible(true);
            jButton3.setVisible(true);
            buttonExportar.setVisible(true);
            deletarButton.setVisible(true);
            jTable2.setModel(new newTabbleModelCNPJ(new String[]{
                "Codigo", "Descrição", "Data"
            }, empresas.size()));
            limpatable();
            for (int i = 0; i <= empresas.size() - 1; i++) {
                if (empresas.get(i) != null) {
                    addTable(i, empresas.get(i));
                } else {
                    break;
                }
            }
            break;
        }

    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) throws ParseException {
        Component teste = SwingUtilities.getWindowAncestor(buttonCadastro);
        mainFrame a = (mainFrame) teste;
        a.switchPanels(1);
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) throws ParseException, SQLException {
        // ATUALIZAR DADOS
        //int linhaASerAtualizada = 9999999;
        Component teste = SwingUtilities.getWindowAncestor(buttonCadastro);
        mainFrame a = (mainFrame) teste;
        String dado;
        boolean muda = false;
        for (int i = 0; i < jTable2.getRowCount(); i++) {
            muda = false;
            if (empresas.get(i) == null) {
                break;
            }
            for (int j = 0; j <= 3; j++) {
                switch (j) {
                    case 0:
                        dado = String.valueOf(empresas.get(i).getID());
                        break;
                    case 1:
                        dado = String.valueOf(empresas.get(i).getNome());
                        break;
                    case 2:
                        dado = String.valueOf(empresas.get(i).getCodigoCNPJ());
                        break;
                    default:
                        dado = String.valueOf(empresas.get(i).getData());
                        break;
                }
                if (!dado.equals(String.valueOf(jTable2.getValueAt(i, j)))) {
                    muda = true;
                    switch (j) {
                        case 0:
                            empresas.get(i).setID((String) jTable2.getValueAt(i, j));
                            break;
                        case 1:
                            empresas.get(i).setNome((String) jTable2.getValueAt(i, j));
                            break;
                        case 2:
                            empresas.get(i).setCodigoCNPJ((String) jTable2.getValueAt(i, j));
                            break;
                        default:
                            String dataString = (String) jTable2.getValueAt(i, j);
                            DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                            java.sql.Date data = new java.sql.Date(fmt.parse(dataString).getTime());
                            empresas.get(i).setData(data);
                            break;
                    }

                    //a.atualizaEmpresa(empresas.get(i).getID(), empresas.get(i));
                    // a.atualizaArae(empresas.get(i).getID(), empresas.get(i));
//                    } catch (SQLException ex) {
//                        Logger.getLogger(cadastroAreaPanel.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                }
                if (muda && j == 3) {
                    a.atualizaEmpresa(empresas.get(i).getID(), empresas.get(i));
                }
            }
        }
        pesquisarButtonPerformed(null);

    }
    // End of variables declaration                   

    private void addTable(int indice, empresa conteudo) {
        jTable2.setValueAt(conteudo.getID(), indice, 0);
        jTable2.setValueAt(conteudo.getNome(), indice, 1);
        jTable2.setValueAt(conteudo.getCodigoCNPJ(), indice, 2);
        jTable2.setValueAt(conteudo.getData(), indice, 3);
    }

    private void limpatable() {
        if (jTable2.isVisible()) {
            for (int i = 0; i < jTable2.getRowCount(); i++) {
                jTable2.setValueAt("", i, 0);
                jTable2.setValueAt("", i, 1);
                jTable2.setValueAt("", i, 2);
                jTable2.setValueAt("", i, 3);
            }
        }
//        jScrollPane2.setVisible(false);
//        jTable2.setVisible(false);
//        jButton3.setVisible(false);
//        buttonExportar.setVisible(false);
    }

    private void buttonCadastroPerformed(ActionEvent evt) throws SQLException {
        Component teste = SwingUtilities.getWindowAncestor(buttonCadastro);
        mainFrame a = (mainFrame) teste;
        if (descricaoField.getText().equals("") || CNPJField.getText().equals("  .   .   /    -  ")) {
            JOptionPane.showMessageDialog(null,
                    "ERRO! É necessário Descrição e CNPJ para efetuar um novo cadastro");
        } else {
            a.cadastroEmpresa(descricaoField.getText(), CNPJField.getText());
            empresa retorno = a.consultaEmpresa(descricaoField.getText());
            if (retorno != null) {
                empresas.add(retorno);
                preperajtable();
                empresas.clear();
            }
        }

    }

    private void deleteActionPerformed(ActionEvent evt) throws SQLException {
        int[] selecao = jTable2.getSelectedRows();
        String query = "DELETE FROM empresa WHERE id IN (";
        for (int i = 0; i < selecao.length; i++) {
            query = query + jTable2.getValueAt(selecao[i], 0) + ",";
            if (i + 1 == selecao.length) {
                query = query.substring(0, query.length() - 1);
            }
        }
        query = query + ")";
        Component teste = SwingUtilities.getWindowAncestor(buttonCadastro);
        mainFrame a = (mainFrame) teste;
        a.deletaEmpresa(query);
        pesquisarButtonPerformed(null);
    }

    private void trocaParaAreaPerformed(ActionEvent evt) throws ParseException {
        Component teste = SwingUtilities.getWindowAncestor(buttonCadastro);
        mainFrame a = (mainFrame) teste;
        a.switchPanels(2);
    }

    private void trocaCadContatoPerformed(ActionEvent evt) throws ParseException {
        Component teste = SwingUtilities.getWindowAncestor(buttonCadastro);
        mainFrame a = (mainFrame) teste;
        a.switchPanels(4);
    }

    private void trocaRelatorioPerformed(ActionEvent evt) throws ParseException {
        Component teste = SwingUtilities.getWindowAncestor(buttonCadastro);
        mainFrame a = (mainFrame) teste;
        a.switchPanels(5);
    }

}
