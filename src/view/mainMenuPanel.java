/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author Victor
 */
public class mainMenuPanel extends JPanel {

    JButton cadastroButton;
    JButton relatorioButton;
    private final JLabel newLabel1;
    private final JButton cadastroEmpresa;
    private final JButton cadastroContato;
    private final JLabel newLabel2;
    private final JButton relatorioContato;
    private final JLabel jLabel1;

    public mainMenuPanel() {

        setMaximumSize(new Dimension(1200, 900));
        setMinimumSize(new Dimension(1200, 900));
        setPreferredSize(new Dimension(1200, 900));
        setLayout(new AbsoluteLayout());
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
        relatorioContato.setFont(new Font("Tahoma", 0, 16));
        relatorioContato.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                trocaRelatorioPerformed(evt);
            } catch (ParseException ex) {
                Logger.getLogger(newConsultaContato.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.add(relatorioContato, new AbsoluteConstraints(20, 210, 120, 40));
//        this.setLayout(null);
//        cadastroButton = new JButton("Cadastro");
//        cadastroButton.setBounds(530, 200, 120, 50);
//        cadastroButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                cadastroAction();
//            }
//        });
//        add(cadastroButton);
//
//        relatorioButton = new JButton("Relatório de Contato");
//        relatorioButton.setBounds(300, 200, 170, 50);
//        relatorioButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    relatorioAction();
//                } catch (ParseException ex) {
//                    Logger.getLogger(mainMenuPanel.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//        add(relatorioButton);
        jLabel1 = new JLabel();
        jLabel1.setFont(new Font("Tahoma", 0, 24));
        jLabel1.setText("SWB CLIENTS");
        jLabel1.setVisible(true);
        add(jLabel1, new AbsoluteConstraints(490, 10));
    }

    private void relatorioAction() throws ParseException {
//        Component teste = SwingUtilities.getWindowAncestor(cadastroButton);
//        mainFrame a = (mainFrame) teste;
//        a.switchPanels(5);
    }

    void cadastroAction() {
//        JButton cadastroAreasButton = new JButton("de Áreas");
//        cadastroAreasButton.setBounds(652, 150, 120, 50);
//        cadastroAreasButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //Component teste = SwingUtilities.getWindowAncestor(cadastroAreasButton);
//                Component teste = SwingUtilities.getWindowAncestor(cadastroButton);
//                mainFrame a = (mainFrame) teste;
//                try {
//                    a.switchPanels(2);
//                } catch (ParseException ex) {
//                    Logger.getLogger(mainMenuPanel.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                //teste.getM
//                System.out.println("");
//            }
//        });
//        add(cadastroAreasButton);
//        JButton cadastroEmpresasButton = new JButton("de Empresas");
//        cadastroEmpresasButton.setBounds(652, 201, 120, 50);
//        cadastroEmpresasButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //Component teste = SwingUtilities.getWindowAncestor(cadastroAreasButton);
//                Component teste = SwingUtilities.getWindowAncestor(cadastroButton);
//                mainFrame a = (mainFrame) teste;
//                try {
//                    a.switchPanels(3);
//                } catch (ParseException ex) {
//                    Logger.getLogger(mainMenuPanel.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                //teste.getM
//                System.out.println("");
//            }
//        });
//        add(cadastroEmpresasButton);
//        JButton cadastroContatosButton = new JButton("de Contatos");
//        cadastroContatosButton.setBounds(652, 252, 120, 50);
//        cadastroContatosButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //Component teste = SwingUtilities.getWindowAncestor(cadastroAreasButton);
//                Component teste = SwingUtilities.getWindowAncestor(cadastroButton);
//                mainFrame a = (mainFrame) teste;
//                try {
//                    a.switchPanels(4);
//                } catch (ParseException ex) {
//                    Logger.getLogger(mainMenuPanel.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                //teste.getM
//                System.out.println("");
//            }
//        });
//        add(cadastroContatosButton);
//        repaint();
    }

    void relatoriosAction() {
//        JButton relContButton = new JButton("de Contatos");
//        relContButton.setBounds(652, 353, 120, 50);
//        add(relContButton);
//        repaint();
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
