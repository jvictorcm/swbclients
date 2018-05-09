/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.legacy;

import externos.newTabbleModelCNPJ;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
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
import model.contato;
import model.empresa;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import view.mainFrame;

/**
 *
 * @author Victor
 */
public class consultaContatoPanel extends javax.swing.JPanel {

    DefaultTableModel tableModel;
    DefaultTableModel model;
    ArrayList<contato> contatos;
    private javax.swing.JButton buttonConsultar;
    private javax.swing.JButton buttonData;
    private javax.swing.JButton buttonNome;
    private javax.swing.JButton buttonCadastro;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private JButton buttonExportar;
    private JLabel jLabel1;
    private String descricao;
    private JButton buttonCNPJ;
    mainFrame a;
    Component teste;

    /**
     * Creates new form NewJPanel
     */
    public consultaContatoPanel() {
        initComponents();
        contatos = new ArrayList<>();
        buttonNome.setVisible(false);
        buttonData.setVisible(false);
        buttonCNPJ.setVisible(false);
        jScrollPane2.setVisible(false);
        buttonExportar.setVisible(false);

    }

    private void initComponents() {
        buttonExportar = new JButton("Exportar");
        buttonCadastro = new JButton(); //feito
        buttonNome = new JButton(); //feito
        buttonConsultar = new JButton(); //feito
        buttonData = new JButton();
        jScrollPane2 = new JScrollPane();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        buttonCNPJ = new JButton();

        setMaximumSize(new Dimension(1200, 900));
        setMinimumSize(new Dimension(1200, 900));
        setPreferredSize(new Dimension(1200, 900));
        setLayout(new AbsoluteLayout());

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
        add(buttonExportar, new AbsoluteConstraints(1000, 500, 170, 70));

        buttonCadastro.setText("Cadastrar Novo Contato");
        buttonCadastro.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                jButton1ActionPerformed(evt);
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(consultaEmpresasPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        add(buttonCadastro, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, 200, 70));

        buttonNome.setText("por Nome");
        buttonNome.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                descricaoActionPerformed(evt);
            } catch (ParseException ex) {
                Logger.getLogger(consultaContatoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        add(buttonNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 200, 140, 70));

        buttonConsultar.setText("Consultar Contato");
        buttonConsultar.addActionListener((java.awt.event.ActionEvent evt) -> {
            consultarActionPerformed(evt);
        });
        add(buttonConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 200, 170, 70));

        buttonData.setText("por Data");
        buttonData.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                dataActionPerformed(evt);
            } catch (ParseException ex) {
                Logger.getLogger(consultaEmpresasPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(consultaContatoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        add(buttonData, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 130, 140, 70));
        buttonCNPJ.setText("por CPF");
        buttonCNPJ.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                buttonCPFActionPerformed(evt);
            } catch (ParseException ex) {
                Logger.getLogger(consultaEmpresasPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        add(buttonCNPJ, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 270, 140, 70));

        jTable2 = new JTable(model);

        jTable2.addMouseListener(new MouseAdapter() {
            //asdasd

            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
//                    System.out.println(table.getSelectedColumn());
//                    System.out.println(table.getSelectedRow());
                    if (table.getSelectedColumn() == 5) { //escolher empresa
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
                    } else if (table.getSelectedColumn() == 6) {
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
        jScrollPane2.setViewportView(jTable2);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 360, 740, -1));

        jButton2.setText("VOLTAR");
        jButton2.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                jButton2ActionPerformed(evt);
            } catch (ParseException ex) {
                Logger.getLogger(consultaContatoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, -1, -1));

        jButton3.setText("Atualizar Dados");
        jButton3.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                jButton3ActionPerformed(evt);
            } catch (ParseException ex) {
                Logger.getLogger(consultaAreaPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(consultaContatoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 330, -1, -1));
        jButton3.setVisible(false);
        jLabel1 = new JLabel();
        jLabel1.setFont(new Font("Tahoma", 0, 24));
        jLabel1.setText("CADASTRO DE CONTATO");
        jLabel1.setVisible(true);
        add(jLabel1, new AbsoluteConstraints(490, 100));
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws SQLException, ParseException {
        teste = SwingUtilities.getWindowAncestor(buttonCadastro);
        a = (mainFrame) teste;
        MaskFormatter msk = new MaskFormatter("###.###.###-##");
        MaskFormatter msk2 = new MaskFormatter("(##)####-####");
        MaskFormatter msk3 = new MaskFormatter("(##)#####-####");
        JTextField nome = new JTextField();
        JTextField email = new JTextField();
        JFormattedTextField codigoCPF = new JFormattedTextField(msk);
        JFormattedTextField telefoneResidencial = new JFormattedTextField(msk2);
        JFormattedTextField telefoneCelular = new JFormattedTextField(msk3);
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
        JComboBox areaCombobox = new JComboBox(itemsArea);
        JComboBox empresaCombobox = new JComboBox(itemsEmpresa);
        Object[] message = {
            "Nome:", nome,
            "CPF:", codigoCPF,
            "Telefone:", telefoneResidencial,
            "Celular:", telefoneCelular,
            "E-mail:", email,
            "Área:", areaCombobox,
            "Empresa:", empresaCombobox
        };
        while (true) {
            try {
                int option = JOptionPane.showConfirmDialog(null, message, "Cadastro de Novo Contato", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    if (nome.getText().equals("") || codigoCPF.getText().equals("  .   .   /    -  ")) {
                        JOptionPane.showMessageDialog(null,
                                "Favor informar nome e/ou CPF válidos."); //colocar feedback separado?
                        retorno.clear();
                    } else {
                        int id_area = retorno.get(areaCombobox.getSelectedIndex()).getID();
                        int id_empresa = Integer.valueOf(retornoEmpresa.get(empresaCombobox.getSelectedIndex()).getID());
                        a.cadastroContato(nome.getText(), codigoCPF.getText(), telefoneResidencial.getText(), telefoneCelular.getText(), email.getText(), id_area, id_empresa);
                        retorno.clear();
                        retornoEmpresa.clear();
                        break;
                    }
                    //tratamento
                } else {
                    retorno.clear();
                    retornoEmpresa.clear();
                    break;
                }
            } catch (NullPointerException e) {
                break;
            }
        }

    }

    private void descricaoActionPerformed(java.awt.event.ActionEvent evt) throws ParseException {
        String nome;
        contatos.clear();
        while (true) {
            try {
                nome = JOptionPane.showInputDialog(null, "Informe o nome a ser consultado:", "Consulta de Contato", JOptionPane.PLAIN_MESSAGE);
                if (nome.equals("null") || nome.equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "Favor informar um nome.");
                } else {
                    teste = SwingUtilities.getWindowAncestor(buttonCadastro);
                    a = (mainFrame) teste;
                    try {
                        contato retorno = a.consultaContato(nome);
                        if (retorno != null) {
                            contatos.add(retorno);
                        }

                        if (contatos.isEmpty()) {
                            JOptionPane.showMessageDialog(null,
                                    "Não existe cadastro de nome \"" + nome + "\"");
                            jTable2.setVisible(false);
                            limpatable();
                        } else {
                            preperajtable();
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

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {
        buttonNome.setVisible(true);
        buttonData.setVisible(true);
        buttonCNPJ.setVisible(true);
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
                teste = SwingUtilities.getWindowAncestor(buttonCadastro);
                a = (mainFrame) teste;
                String dataInicialQuery = dataInicial.getText().substring(6, 10) + "-" + dataInicial.getText().substring(3, 5) + "-" + dataInicial.getText().substring(0, 2);
                String dataFinalQuery = dataFinal.getText().substring(6, 10) + "-" + dataFinal.getText().substring(3, 5) + "-" + dataFinal.getText().substring(0, 2);
                try {
                    contatos = a.consultaContato(dataInicialQuery, dataFinalQuery);
                    System.out.println("");
                } catch (SQLException ex) {
                    Logger.getLogger(cadastroAreaPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
            }
            preperajtable();

            break;
        }

    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) throws ParseException {
        teste = SwingUtilities.getWindowAncestor(buttonCadastro);
        a = (mainFrame) teste;
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
        for (int i = 0; i < jTable2.getRowCount(); i++) {
            if (contatos.get(i) == null) {
                break;
            }
            for (int j = 0; j <= 6; j++) {
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
                    case 6:
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
                        //dado = String.valueOf(contatos.get(i).getIDempresa());
                        break;
                    default:
//                        dado = String.valueOf(contatos.get(i).getData());
                        break;
                }
                if (!dado.equals(String.valueOf(jTable2.getValueAt(i, j)))) {
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
                        case 5: //set Empresa ID
                            for (int w = 0; w < retornoEmpresa.size(); w++) {
                                if (String.valueOf(jTable2.getValueAt(i, j)).equals(retornoEmpresa.get(w).getNome())) {
                                    contatos.get(i).setIDempresa(Integer.valueOf(retornoEmpresa.get(w).getID()));
                                    break;
                                }
                            }
                            break;
                        case 6: // set Area ID
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
                }
            }
            a.atualizaContato(contatos.get(i).getID(), contatos.get(i));
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int pixel;
        for (pixel = 0; pixel <= getHeight(); pixel += 10) {
            g.drawLine(0, pixel, pixel, getHeight());
        }
        for (pixel = getHeight(); pixel >= 0; pixel -= 10) {
            g.drawLine(0, pixel, getHeight() - pixel, 0);
        }
        for (pixel = 0; pixel <= getHeight(); pixel += 10) {
            g.drawLine(getWidth(), pixel, getWidth() - pixel, getHeight());
        }
        for (pixel = getHeight(); pixel >= 0; pixel -= 10) {
            g.drawLine(getWidth(), pixel, getWidth() - (getHeight() - pixel), 0);
        }
    }

//"Codigo", "Nome", "CNPJ", "Data de Cadastro"
    private void addTable(int indice, contato conteudo) throws SQLException {
        jTable2.setValueAt(conteudo.getID(), indice, 0);
        jTable2.setValueAt(conteudo.getNome(), indice, 1);
        jTable2.setValueAt(conteudo.getCpf(), indice, 2);
        jTable2.setValueAt(conteudo.getTelRes(), indice, 3);
        jTable2.setValueAt(conteudo.getTelcel(), indice, 4);
        ArrayList<area> retorno = a.consultaArea("0000-00-00", "9999-01-01");
        ArrayList<empresa> retornoEmpresa = a.consultaEmpresa("0000-00-00", "9999-01-01");
        String Area = "";
        String Empresa = "";
        for (int i = 0; i < retorno.size(); i++) {
            if (retorno.get(i).getID() == conteudo.getIDarea()) {
                Area = retorno.get(i).getDescricao();
                break;
            }
        }
        for (int i = 0; i < retornoEmpresa.size(); i++) {
            if (retornoEmpresa.get(i).getID().equals(String.valueOf(conteudo.getIDempresa()))) {
                Empresa = retornoEmpresa.get(i).getNome();
                break;
            }
        }
        jTable2.setValueAt(Empresa, indice, 5);
        jTable2.setValueAt(Area, indice, 6);

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

    private void buttonCPFActionPerformed(ActionEvent evt) throws ParseException {
        MaskFormatter formaterData = new MaskFormatter("###.###.###-##");
        JFormattedTextField cpf = new JFormattedTextField(formaterData);
        Object[] message = {
            "CNPJ:", cpf
        };
        contatos.clear();
        while (true) {
            try {
                int option = JOptionPane.showConfirmDialog(null, message, "Informe o CNPJ para pesquisa", JOptionPane.OK_CANCEL_OPTION);
                if (cpf.getText().equals("null") || cpf.getText().equals("   .   .   -  ")) {
                    JOptionPane.showMessageDialog(null,
                            "Favor informar um CPF válido.");
                } else {
                    teste = SwingUtilities.getWindowAncestor(buttonCadastro);
                    a = (mainFrame) teste;
                    try {
                        contato retorno = a.consultaContatoCPF(cpf.getText());
                        if (retorno != null) {
                            contatos.add(retorno);
                        }

                        if (contatos.isEmpty()) {
                            JOptionPane.showMessageDialog(null,
                                    "Não existe cadastro de nome \"" + cpf.getText() + "\"");
                            jTable2.setVisible(false);
                            limpatable();
                        } else {
                            preperajtable();
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

    private void preperajtable() throws SQLException {
        jScrollPane2.setVisible(true);
        jTable2.setVisible(true);
        jButton3.setVisible(true);
        buttonExportar.setVisible(true);

        jTable2.setModel(new newTabbleModelCNPJ(new String[]{
            "Codigo", "Nome", "CPF", "Tel. Res.", "Tel. Cel.", "Empresa", "Área"
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
}
