
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import view.legacy.consultaAreaPanel;
import view.legacy.cadastroAreaPanel;
import view.legacy.consultaContatoPanel;
import externos.newTabbleModelCNPJ;
import externos.relatorioTable;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JComboBox;
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
public class newConsultaContato extends javax.swing.JPanel {

    DefaultTableModel tableModel;
    DefaultTableModel model;
    ArrayList<contato> contatos;
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
    JTextField nomeFieldConsulta;
    JFormattedTextField dataInicialFieldConsulta, dataFinalFieldConsulta;
    JTextField descricaoField;
    JFormattedTextField CPFField;
    private JFormattedTextField CPFFieldPesquisa;
    private JFormattedTextField telResField;
    private JFormattedTextField telCelField;
    mainFrame a;
    Component teste;
    private JComboBox empresaCombobox;
    private JComboBox areaCombobox;
    private JTextField emailField;

    public newConsultaContato() throws ParseException {
        initComponents();
        contatos = new ArrayList<>();
        buttonDescricao.setVisible(false);
        buttonData.setVisible(false);
        //        jTable2.setVisible(false);
        jScrollPane2.setVisible(false);
        buttonExportar.setVisible(false);
deletarButton.setVisible(false);
    }

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
        cadastroContato.setEnabled(false);

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
        this.add(newLabel4, new AbsoluteConstraints(200, 35));
        descricaoField = new JTextField();
        descricaoField.setVisible(true);
        this.add(descricaoField, new AbsoluteConstraints(280, 35, 200, 25));
        JLabel newLabel52 = new JLabel();
        newLabel52.setText("CPF:");
        newLabel52.setFont(new Font("Tahoma", 0, 18));
        newLabel52.setVisible(true);
        this.add(newLabel52, new AbsoluteConstraints(200, 70));
        MaskFormatter formaterCPF = new MaskFormatter("###.###.###-##");
        CPFField = new JFormattedTextField(formaterCPF);
        CPFField.setVisible(true);
        this.add(CPFField, new AbsoluteConstraints(280, 70, 200, 25));
        //
        JLabel newLabel522 = new JLabel();
        newLabel522.setText("Tel.Res.:");
        newLabel522.setFont(new Font("Tahoma", 0, 18));
        newLabel522.setVisible(true);
        this.add(newLabel522, new AbsoluteConstraints(200, 105));
        MaskFormatter formatertel = new MaskFormatter("(##)####-####");
        telResField = new JFormattedTextField(formatertel);
        telResField.setVisible(true);
        this.add(telResField, new AbsoluteConstraints(280, 105, 200, 25));
        //
        //
        JLabel newLabel533 = new JLabel();
        newLabel533.setText("Tel.Cel.:");
        newLabel533.setFont(new Font("Tahoma", 0, 18));
        newLabel533.setVisible(true);
        this.add(newLabel533, new AbsoluteConstraints(200, 140));
        MaskFormatter formatertelcel = new MaskFormatter("(##)#####-####");
        telCelField = new JFormattedTextField(formatertelcel);
        telCelField.setVisible(true);
        this.add(telCelField, new AbsoluteConstraints(280, 140, 200, 25));
        //
        JLabel newLabel3321 = new JLabel();
        newLabel3321.setText("Empresa:");
        newLabel3321.setFont(new Font("Tahoma", 0, 18));
        newLabel3321.setVisible(true);
        this.add(newLabel3321, new AbsoluteConstraints(200, 175));
        addComboBoxArea();
        //
        JLabel newLabel3218 = new JLabel();
        newLabel3218.setText("Área:");
        newLabel3218.setFont(new Font("Tahoma", 0, 18));
        newLabel3218.setVisible(true);
        this.add(newLabel3218, new AbsoluteConstraints(200, 210));
        addComboBoxEmpresa();
        //

        JLabel newLabel32218 = new JLabel();
        newLabel32218.setText("Email:");
        newLabel32218.setFont(new Font("Tahoma", 0, 18));
        newLabel32218.setVisible(true);
        this.add(newLabel32218, new AbsoluteConstraints(200, 245));
        emailField = new JTextField();
        emailField.setVisible(true);
        this.add(emailField, new AbsoluteConstraints(280, 245, 200, 25));

        //
        buttonCadastro.setText("Nova Entrada");
        buttonCadastro.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                buttonCadastroPerformed(evt);
            } catch (SQLException ex) {
                Logger.getLogger(newConsultaArea.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(newConsultaContato.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        add(buttonCadastro, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 275, 130, 35));
        JLabel newLabel512 = new JLabel();
        newLabel512.setText("CPF:");
        newLabel512.setFont(new Font("Tahoma", 0, 18));
        newLabel512.setVisible(true);
        this.add(newLabel512, new AbsoluteConstraints(700, 135));
        MaskFormatter formaterCPF2 = new MaskFormatter("###.###.###-##");
        CPFFieldPesquisa = new JFormattedTextField(formaterCPF2);
        CPFFieldPesquisa.setFocusLostBehavior(JFormattedTextField.PERSIST);
        CPFFieldPesquisa.setVisible(true);
        this.add(CPFFieldPesquisa, new AbsoluteConstraints(800, 135, 200, 25));
        JLabel newLabel5 = new JLabel();
        newLabel5.setText("Nome:");
        newLabel5.setFont(new Font("Tahoma", 0, 18));
        newLabel5.setVisible(true);
        this.add(newLabel5, new AbsoluteConstraints(700, 100));
        nomeFieldConsulta = new JTextField();
        nomeFieldConsulta.setVisible(true);
        this.add(nomeFieldConsulta, new AbsoluteConstraints(800, 100, 200, 25));
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
            } catch (ParseException ex) {
                Logger.getLogger(newConsultaContato.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        add(buttonPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 240, 100, 35));
        buttonExportar.addActionListener((ActionEvent e) -> {
            teste = SwingUtilities.getWindowAncestor(buttonCadastro);
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
                Iterator asd = contatos.iterator();
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
            } catch (ParseException ex) {
                Logger.getLogger(newConsultaContato.class.getName()).log(Level.SEVERE, null, ex);
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
            } catch (SQLException ex) {
                Logger.getLogger(newConsultaContato.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        add(buttonData, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 150, 140, 70));

        jTable2 = new JTable(model);
        jScrollPane2.setViewportView(jTable2);
        jTable2.addMouseListener(new MouseAdapter() {
            //asdasd

            public void mousePressed(MouseEvent mouseEvent) {
                teste = SwingUtilities.getWindowAncestor(buttonCadastro);
                a = (mainFrame) teste;
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
//                    System.out.println(table.getSelectedColumn());
//                    System.out.println(table.getSelectedRow());
                    if (table.getSelectedColumn() == 6) { //escolher empresa
                        ArrayList<empresa> retornoEmpresa = new ArrayList<>();
                        try {
                            retornoEmpresa = a.consultaEmpresa("0000-00-00", "9999-01-01");
                        } catch (SQLException ex) {
                            Logger.getLogger(consultaContatoPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Object[] itemsEmpresa = new Object[retornoEmpresa.size()];
                        for (int i = 0; i < retornoEmpresa.size(); i++) {
                            itemsEmpresa[i] = retornoEmpresa.get(i).getNome();
                        }
                        JComboBox empresaCombobox = new JComboBox(itemsEmpresa);
                        Object[] message = {
                            "Selecione a Empresa desejada:", empresaCombobox,};
                        int option = JOptionPane.showConfirmDialog(null, message, "Edição de Empresa", JOptionPane.OK_CANCEL_OPTION);
                        if (option == JOptionPane.OK_OPTION) {
                            String id_empresa = (String) empresaCombobox.getSelectedItem();
                            jTable2.setValueAt(id_empresa, table.getSelectedRow(), table.getSelectedColumn());
                        }
                        retornoEmpresa.clear();
                    } else if (table.getSelectedColumn() == 7) {
                        ArrayList<area> retornoArea = new ArrayList<>();
                        try {
                            retornoArea = a.consultaArea("0000-00-00", "9999-01-01");
                        } catch (SQLException ex) {
                            Logger.getLogger(consultaContatoPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Object[] itemsArea = new Object[retornoArea.size()];
                        for (int i = 0; i < retornoArea.size(); i++) {
                            itemsArea[i] = retornoArea.get(i).getDescricao();
                        }
                        JComboBox areaCombobox = new JComboBox(itemsArea);
                        Object[] message = {
                            "Selecione a Área desejada:", areaCombobox,};
                        int option = JOptionPane.showConfirmDialog(null, message, "Edição de Área", JOptionPane.OK_CANCEL_OPTION);
                        if (option == JOptionPane.OK_OPTION) {
                            String id_Area = (String) areaCombobox.getSelectedItem();
                            jTable2.setValueAt(id_Area, table.getSelectedRow(), table.getSelectedColumn());
                        }
                        retornoArea.clear();

                    }
                }
            }
        });
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
        jLabel1.setText("CADASTRO DE CONTATO");
        jLabel1.setVisible(true);
        add(jLabel1, new AbsoluteConstraints(490, 10));
    }

    private void pesquisarButtonPerformed(java.awt.event.ActionEvent evt) throws SQLException, ParseException {
        String query = "select c.*, e.nome as nomeDaEmpresa, a.descricao as nomeDaArea from contato AS c \n"
                + "JOIN empresa as e ON c.ref_empresa  = e.`ID`\n"
                + "JOIN area as a ON c.ref_area = a.`ID`\n"
                + "Where ";
        String datainicial, datafinal, descricao, cpf;
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
        if (CPFFieldPesquisa.getText().equals("   .   .   -  ")) {
            cpf = "";
        } else {
            cpf = CPFFieldPesquisa.getText();
        }
        descricao = nomeFieldConsulta.getText();
        //preparou
        if (!descricao.equals("")) {
            query = query + "c.nome='" + nomeFieldConsulta.getText() + "'";
        }
        if ((!datainicial.equals("") && datafinal.equals("")) || (datainicial.equals("") && !datafinal.equals(""))) {
            JOptionPane.showMessageDialog(null,
                    "Erro! Favor informar as duas datas ou deixar ambas vazias.");
        } else {
            if (datainicial.equals("")) {

            } else {
                String dataInicialQuery = datainicial.substring(6, 10) + "-" + datainicial.substring(3, 5) + "-" + datainicial.substring(0, 2);
                String dataFinalQuery = datafinal.substring(6, 10) + "-" + datafinal.substring(3, 5) + "-" + datafinal.substring(0, 2);
                if (query.endsWith("Where ")) {
                } else {
                    query = query + " AND ";
                }
                query = query + "c.data between " + "'" + dataInicialQuery + "'" + " and " + "'" + dataFinalQuery + "'";
            }

        }
        if (!cpf.equals("")) {
            if (query.endsWith("Where ")) {
                query = query + "c.cpf='" + cpf + "'";
            } else {
                query = query + " AND " + "c.cpf='" + cpf + "'";
            }
        }
        if (query.endsWith("Where ")) {
            query = "";
            JOptionPane.showMessageDialog(null,
                    "Preencher algum dado para pesquisa.");

        } else {
            Component teste = SwingUtilities.getWindowAncestor(buttonCadastro);
            mainFrame a = (mainFrame) teste;
            query = query + " ORDER BY c.ID";
            System.out.println(query);
            contatos = a.consultaContatosQuery(query);
            if (contatos == null || contatos.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Não existe nenhum resultado para esta pesquisa.");
                limpatable();
                jScrollPane2.setVisible(false);
                jTable2.setVisible(false);
                jButton3.setVisible(false);
                buttonExportar.setVisible(false);
                deletarButton.setVisible(false);
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
            "Codigo", "Nome", "CPF", "Tel. Res.", "Tel. Cel.", "Email", "Empresa", "Área"
        }, contatos.size()));
        limpatable();
        for (int i = 0; i <= contatos.size() - 1; i++) {
            if (contatos.get(i) != null) {
                addTable(i, contatos.get(i));
            } else {
                break;
            }
        }
    }

    private void descricaoActionPerformed(java.awt.event.ActionEvent evt) {
        String descricao;
        contatos.clear();
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

                        if (contatos.isEmpty()) {
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
                            jTable2.setModel(new DefaultTableModel(new String[]{
                                "Codigo", "Descrição", "Data"
                            }, 1));
                            limpatable();
                            addTable(0, contatos.get(0));
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

    private void dataActionPerformed(java.awt.event.ActionEvent evt) throws ParseException, SQLException {
//        area[] lalala = teste.findAreaByData("2018-04-01", "2018-04-10");
        while (true) {
            contatos.clear();
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
            jTable2.setModel(new DefaultTableModel(new String[]{
                "Codigo", "Descrição", "Data"
            }, contatos.size()));
            limpatable();
            for (int i = 0; i <= contatos.size() - 1; i++) {
                if (contatos.get(i) != null) {
                    addTable(i, contatos.get(i));
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
        ArrayList<area> retornoArea = new ArrayList<>();
        ArrayList<empresa> retornoEmpresa = new ArrayList<>();
        teste = SwingUtilities.getWindowAncestor(buttonCadastro);
        int x = 999999999, k = 999999999;
        a = (mainFrame) teste;
        String dado = "";
        boolean muda = false;
        for (int i = 0; i < jTable2.getRowCount(); i++) {
            muda = false;
            if (contatos.get(i) == null) {
                break;
            }
            for (int j = 0; j <= 7; j++) {
                switch (j) {
                    case 0:
                        dado = String.valueOf(contatos.get(i).getID());
                        break;
                    case 1:
                        dado = String.valueOf(contatos.get(i).getNome());
                        break;
                    case 2:
                        dado = String.valueOf(contatos.get(i).getCpf());
                        break;
                    case 3:
                        dado = String.valueOf(contatos.get(i).getTelRes());
                        break;
                    case 4:
                        dado = String.valueOf(contatos.get(i).getTelcel());
                        break;
                    case 5:
                        dado = String.valueOf(contatos.get(i).getEmail());
                        break;
                    case 6:
                        try {
                            retornoEmpresa = a.consultaEmpresa("0000-00-00", "9999-01-01");
                        } catch (SQLException ex) {
                            Logger.getLogger(consultaContatoPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        for (x = 0; x < retornoEmpresa.size(); x++) {
                            if (contatos.get(i).getIDempresa() == Integer.valueOf(retornoEmpresa.get(x).getID())) {
                                dado = retornoEmpresa.get(x).getNome();
                                break;
                            }
                        }
                        //dado = String.valueOf(contatos.get(i).getIDempresa());
                        break;
                    case 7:
                        try {
                            retornoArea = a.consultaArea("0000-00-00", "9999-01-01");
                        } catch (SQLException ex) {
                            Logger.getLogger(consultaContatoPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        for (k = 0; k < retornoArea.size(); k++) {
                            if (contatos.get(i).getIDarea() == retornoArea.get(k).getID()) {
                                dado = retornoArea.get(k).getDescricao();
                                break;
                            }
                        }
                        //dado = String.valueOf(contatos.get(i).getIDarea());
                        break;
                    default:
//                        dado = String.valueOf(contatos.get(i).getData());
                        break;
                }
                if (!dado.equals(String.valueOf(jTable2.getValueAt(i, j)))) {
                    muda = true;
                    switch (j) {
                        case 0:
                            contatos.get(i).setID(Integer.valueOf((String) jTable2.getValueAt(i, j)));
                            break;
                        case 1:
                            contatos.get(i).setNome(String.valueOf(jTable2.getValueAt(i, j)));
                            break;
                        case 2:
                            contatos.get(i).setCpf(String.valueOf(jTable2.getValueAt(i, j)));
                            break;
                        case 3:
                            contatos.get(i).setTelRes(String.valueOf(jTable2.getValueAt(i, j)));
                            break;
                        case 4:
                            contatos.get(i).setTelcel(String.valueOf(jTable2.getValueAt(i, j)));
                            break;
                        case 5:
                            contatos.get(i).setEmail(String.valueOf(jTable2.getValueAt(i, j)));
                            break;
                        case 6: //set Empresa ID
                            for (int w = 0; w < retornoEmpresa.size(); w++) {
                                if (String.valueOf(jTable2.getValueAt(i, j)).equals(retornoEmpresa.get(w).getNome())) {
                                    contatos.get(i).setIDempresa(Integer.valueOf(retornoEmpresa.get(w).getID()));
                                    break;
                                }
                            }
                            break;
                        case 7: // set Area ID
                            for (int o = 0; o < retornoArea.size(); o++) {
                                if (String.valueOf(jTable2.getValueAt(i, j)).equals(retornoArea.get(o).getDescricao())) {
                                    contatos.get(i).setIDarea(retornoArea.get(o).getID());
                                    break;
                                }
                            }
                            break;
                        default:
//                                String dataString = (String) jTable2.getValueAt(i, j);
//                                DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
//                                java.sql.Date data = new java.sql.Date(fmt.parse(dataString).getTime());
//                                contatos.get(i).setData(data);
                            break;
                    }
                } else {

                    System.out.println("nada mudou");
                }
                if (muda && j == 7) {
                    a.atualizaContato(contatos.get(i).getID(), contatos.get(i));
                }
            }

        }
        pesquisarButtonPerformed(null);
    }

    private void addTable(int indice, contato conteudo) throws SQLException {
        jTable2.setValueAt(conteudo.getID(), indice, 0);
        jTable2.setValueAt(conteudo.getNome(), indice, 1);
        jTable2.setValueAt(conteudo.getCpf(), indice, 2);
        jTable2.setValueAt(conteudo.getTelRes(), indice, 3);
        jTable2.setValueAt(conteudo.getTelcel(), indice, 4);
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
        jTable2.setValueAt(conteudo.getEmail(), indice, 5);
        jTable2.setValueAt(Empresa, indice, 6);
        jTable2.setValueAt(Area, indice, 7);
    }

    private void limpatable() {
        if (jTable2.isVisible()) {
            for (int i = 0; i < jTable2.getRowCount(); i++) {
                jTable2.setValueAt("", i, 0);
                jTable2.setValueAt("", i, 1);
                jTable2.setValueAt("", i, 2);
                jTable2.setValueAt("", i, 3);
                jTable2.setValueAt("", i, 4);
                jTable2.setValueAt("", i, 5);
                jTable2.setValueAt("", i, 6);
            }
        }
    }

    private void buttonCadastroPerformed(ActionEvent evt) throws SQLException, ParseException {
        contatos.clear();
        Component teste = SwingUtilities.getWindowAncestor(buttonCadastro);
        mainFrame a = (mainFrame) teste;
        ArrayList<area> retorno = a.consultaArea("0000-00-00", "9999-01-01");
        ArrayList<empresa> retornoEmpresa = a.consultaEmpresa("0000-00-00", "9999-01-01");
        Object[] itemsArea = new Object[retorno.size()];
        Object[] itemsEmpresa = new Object[retornoEmpresa.size()];
        for (int i = 0; i < retorno.size(); i++) {
            itemsArea[i] = retorno.get(i).getDescricao();
        }
        for (int i = 0; i < retornoEmpresa.size(); i++) {
            itemsEmpresa[i] = retornoEmpresa.get(i).getNome();
        }
        int id_area = retorno.get(areaCombobox.getSelectedIndex()).getID();
        int id_empresa = Integer.valueOf(retornoEmpresa.get(empresaCombobox.getSelectedIndex()).getID());
        if (descricaoField.getText().equals("") || CPFField.getText().equals("   .   .   -  ")) {
            JOptionPane.showMessageDialog(null,
                    "ERRO! É necessário Nome e CPF para efetuar um novo cadastro.");
        } else {
            String telcel = "", telRes = "";
            if (telResField.getText().equals("(  )    -    ")) {
                telRes = "";
            } else {
                telRes = telResField.getText();
            }
            if (telCelField.getText().equals("(  )     -    ")) {
                telcel = "";
            } else {
                telcel = telCelField.getText();
            }
            a.cadastroContato(descricaoField.getText(), CPFField.getText(), telRes, telcel, emailField.getText(), id_area, id_empresa);
            // empresa retorno = a.consultaEmpresa(descricaoField.getText());

            if (retorno != null) {
                contatos.add(a.consultaContato(descricaoField.getText()));
                preperajtable();
            }
        }

    }

    private void deleteActionPerformed(ActionEvent evt) throws SQLException, ParseException {
        int[] selecao = jTable2.getSelectedRows();
        String query = "DELETE FROM contato WHERE id IN (";
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

    private void trocaCadEmpresaPerformed(ActionEvent evt) throws ParseException {
        Component teste = SwingUtilities.getWindowAncestor(buttonCadastro);
        mainFrame a = (mainFrame) teste;
        a.switchPanels(3);
    }

    private void addComboBoxEmpresa() {
        try {
            ArrayList<empresa> retornoEmpresa = null;
            retornoEmpresa = new ArrayList<>();
            empresaDAO teste = new empresaDAO();
            retornoEmpresa = teste.findQuery("select * from empresa");
            // retornoEmpresa = a.consultaEmpresa("0000-00-00", "9999-01-01");
            Object[] itemsEmpresa = new Object[retornoEmpresa.size()];
            for (int i = 0; i < retornoEmpresa.size(); i++) {
                itemsEmpresa[i] = retornoEmpresa.get(i).getNome();
            }
            empresaCombobox = new JComboBox(itemsEmpresa);
            this.add(empresaCombobox, new AbsoluteConstraints(280, 175, 200, 25));
        } catch (SQLException ex) {
            Logger.getLogger(consultaContatoPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addComboBoxArea() {
        try {
            ArrayList<area> retornoArae = null;
            retornoArae = new ArrayList<>();
            areaDAO teste = new areaDAO();
            retornoArae = teste.findQuery("select * from area");
            // retornoEmpresa = a.consultaEmpresa("0000-00-00", "9999-01-01");
            Object[] itemsEmpresa = new Object[retornoArae.size()];
            for (int i = 0; i < retornoArae.size(); i++) {
                itemsEmpresa[i] = retornoArae.get(i).getDescricao();
            }
            areaCombobox = new JComboBox(itemsEmpresa);
            this.add(areaCombobox, new AbsoluteConstraints(280, 210, 200, 25));
        } catch (SQLException ex) {
            Logger.getLogger(consultaContatoPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void trocaRelatorioPerformed(ActionEvent evt) throws ParseException {
        Component teste = SwingUtilities.getWindowAncestor(buttonCadastro);
        mainFrame a = (mainFrame) teste;
        a.switchPanels(5);
    }

}
