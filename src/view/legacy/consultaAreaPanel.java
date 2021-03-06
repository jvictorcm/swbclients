/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.legacy;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import model.area;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import view.mainFrame;

/**
 *
 * @author Victor
 */
public class consultaAreaPanel extends javax.swing.JPanel {

    DefaultTableModel tableModel;
    DefaultTableModel model;
    ArrayList<area> areas;
    private javax.swing.JButton buttonConsultar;
    private javax.swing.JButton buttonData;
    private javax.swing.JButton buttonDescricao;
    private javax.swing.JButton buttonCadastro;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private JButton buttonExportar;
    private JLabel jLabel1;

    /**
     * Creates new form NewJPanel
     */
    public consultaAreaPanel() {
        initComponents();
        areas = new ArrayList<>();
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
    private void initComponents() {
        buttonExportar = new JButton("Exportar");
        buttonCadastro = new JButton();
        buttonDescricao = new JButton();
        buttonConsultar = new JButton();
        buttonData = new JButton();
        jScrollPane2 = new JScrollPane();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setMaximumSize(new Dimension(1200, 900));
        setMinimumSize(new Dimension(1200, 900));
        setPreferredSize(new Dimension(1200, 900));
        setLayout(new AbsoluteLayout());

        buttonExportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component teste = SwingUtilities.getWindowAncestor(buttonCadastro);
                mainFrame a = (mainFrame) teste;
                ArrayList<String> colunas = new ArrayList<>();
                colunas.add("Código");
                colunas.add("Descrição");
                colunas.add("Data de Cadastro");
                try {
                    ArrayList<Object> objetos = new ArrayList<Object>();
                    Iterator asd = areas.iterator();
                    while (asd.hasNext()) {
                        objetos.add(asd.next());
                    }
                    //a.exportarDados(colunas, objetos);
                } catch (IOException ex) {
                    Logger.getLogger(consultaAreaPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        add(buttonExportar, new AbsoluteConstraints(1000, 500, 170, 70));

        buttonCadastro.setText("Cadastrar Nova Área");
        buttonCadastro.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButton1ActionPerformed(evt);
        });
        add(buttonCadastro, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, 170, 70));

        buttonDescricao.setText("por Descrição");
        buttonDescricao.addActionListener((java.awt.event.ActionEvent evt) -> {
            descricaoActionPerformed(evt);
        });
        add(buttonDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 220, 140, 70));

        buttonConsultar.setText("Consultar Área");
        buttonConsultar.addActionListener((java.awt.event.ActionEvent evt) -> {
            consultarActionPerformed(evt);
        });
        add(buttonConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, 140, 70));

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

        jButton2.setText("VOLTAR");
        jButton2.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                jButton2ActionPerformed(evt);
            } catch (ParseException ex) {
                Logger.getLogger(consultaAreaPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, -1, -1));

        jButton3.setText("Atualizar Dados");
        jButton3.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                jButton3ActionPerformed(evt);
            } catch (ParseException ex) {
                Logger.getLogger(consultaAreaPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 330, -1, -1));
        jButton3.setVisible(false);
        jLabel1 = new JLabel();
        jLabel1.setFont(new Font("Tahoma", 0, 24));
        jLabel1.setText("CADASTRO DE ÁREA");
        jLabel1.setVisible(true);
        add(jLabel1, new AbsoluteConstraints(490, 100));
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String descricaoArea;
        while (true) {
            try {
                descricaoArea = JOptionPane.showInputDialog(null, "Informe uma descrição", "Cadastro de Área", JOptionPane.PLAIN_MESSAGE);
                if (descricaoArea.equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "Favor informar uma descrição.");
                } else {
                    Component teste = SwingUtilities.getWindowAncestor(buttonCadastro);
                    mainFrame a = (mainFrame) teste;
                    try {
                        a.cadastroAreaDAO(descricaoArea);
                    } catch (SQLException ex) {
                        Logger.getLogger(cadastroAreaPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
            } catch (NullPointerException e) {
                break;
            }
        }

    }

    private void descricaoActionPerformed(java.awt.event.ActionEvent evt) {
        String descricao;
        areas.clear();
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
                            areas.add(a.consultaArea(descricao));
                        }

                        if (areas.isEmpty()) {
                            JOptionPane.showMessageDialog(null,
                                    "Não existe cadastro de descrição \"" + descricao + "\"");
                            jTable2.setVisible(false);
                            limpatable();
                        } else {
                            jScrollPane2.setVisible(true);
                            jTable2.setVisible(true);
                            jButton3.setVisible(true);
                            buttonExportar.setVisible(true);
                            jTable2.setModel(new DefaultTableModel(new String[]{
                                "Codigo", "Descrição", "Data"
                            }, 1));
                            limpatable();
                            addTable(0, areas.get(0));
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
        buttonDescricao.setVisible(true);
        buttonData.setVisible(true);
    }

    private void dataActionPerformed(java.awt.event.ActionEvent evt) throws ParseException {
//        area[] lalala = teste.findAreaByData("2018-04-01", "2018-04-10");
        while (true) {
            areas.clear();
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
                try {
                    areas = a.consultaArea(dataInicialQuery, dataFinalQuery);
                } catch (SQLException ex) {
                    Logger.getLogger(cadastroAreaPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
            }
            jScrollPane2.setVisible(true);
            jTable2.setVisible(true);
            jButton3.setVisible(true);
            buttonExportar.setVisible(true);

            jTable2.setModel(new DefaultTableModel(new String[]{
                "Codigo", "Descrição", "Data"
            }, areas.size()));
            limpatable();
            for (int i = 0; i <= areas.size() - 1; i++) {
                if (areas.get(i) != null) {
                    addTable(i, areas.get(i));
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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) throws ParseException {
        // ATUALIZAR DADOS
        //int linhaASerAtualizada = 9999999;
        Component teste = SwingUtilities.getWindowAncestor(buttonCadastro);
        mainFrame a = (mainFrame) teste;
        String dado;
        for (int i = 0; i < jTable2.getRowCount(); i++) {
            if (areas.get(i) == null) {
                break;
            }
            for (int j = 0; j <= 2; j++) {
                switch (j) {
                    case 0:
                        dado = String.valueOf(areas.get(i).getID());
                        break;
                    case 1:
                        dado = String.valueOf(areas.get(i).getDescricao());
                        break;
                    default:
                        dado = String.valueOf(areas.get(i).getData());
                        break;
                }
                if (!dado.equals(String.valueOf(jTable2.getValueAt(i, j)))) {
                    try {
                        switch (j) {
                            case 0:
                                areas.get(i).setCodigo((int) jTable2.getValueAt(i, j));
                                break;
                            case 1:
                                areas.get(i).setDescricao(String.valueOf(jTable2.getValueAt(i, j)));
                                break;
                            default:
                                String dataString = (String) jTable2.getValueAt(i, j);
                                DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                                java.sql.Date data = new java.sql.Date(fmt.parse(dataString).getTime());
                                areas.get(i).setData(data);
                                break;
                        }
                        a.atualizaArae(areas.get(i).getID(), areas.get(i));
                    } catch (SQLException ex) {
                        Logger.getLogger(cadastroAreaPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
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
    // Variables declaration - do not modify                     

    // End of variables declaration                   
    private void addTable(int indice, area conteudo) {
        jTable2.setValueAt(conteudo.getID(), indice, 0);
        jTable2.setValueAt(conteudo.getDescricao(), indice, 1);
        jTable2.setValueAt(conteudo.getData(), indice, 2);
    }

    private void limpatable() {
        if (jTable2.isVisible()) {
            for (int i = 0; i < jTable2.getRowCount(); i++) {
                jTable2.setValueAt("", i, 0);
                jTable2.setValueAt("", i, 1);
                jTable2.setValueAt("", i, 2);
            }
        }
    }
}
