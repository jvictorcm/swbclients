/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.legacy;

import javax.swing.JOptionPane;

/**
 *
 * @author Victor
 */
public class cadastroAreaDialog {
    Object[] opcoes = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
    String resposta = (String) JOptionPane.showInputDialog(null,"Em que número estou pensando?", "Jogo de Advinhar",JOptionPane.QUESTION_MESSAGE, null, opcoes, null);
    //String novo = = (String) JOptionPane.showInputDialog
}
