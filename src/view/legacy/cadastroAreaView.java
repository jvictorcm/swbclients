/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.legacy;

import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Victor
 */
public class cadastroAreaView extends JPanel {
    
    public cadastroAreaView(){
        this.setLayout(null);
        this.setVisible(true);
        JButton cadastroButton = new JButton("teste");
        cadastroButton.setBounds(530, 200, 120, 50);
        cadastroButton.setVisible(true);
        add(cadastroButton);
        System.out.println("cadastropanel");
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
}
