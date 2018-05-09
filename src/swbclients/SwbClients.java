/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swbclients;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import view.*;

/**
 *
 * @author Victor
 */
public class SwbClients {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, IOException, ParseException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
//        String dataString = "2015-01-25";
//        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
//        java.sql.Date data = new java.sql.Date(fmt.parse(dataString).getTime());

mainFrame a = new mainFrame();
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        a.setResizable(false);
        a.setSize(1200, 900);
        a.setVisible(true);
//        UsuarioDao teste = new UsuarioDao();
//        Usuario asd = teste.findByNomeUsuario("testando");
//        System.out.println(asd.NomeUsuario);
//        areaDAO teste = new areaDAO();
//        area[] lalala = teste.findAreaByData("2018-04-01", "2018-04-10");
//        ArrayList<String> teste = new ArrayList<>();
//        teste.add("primeiro teste");
//        teste.add("segunda coluna");
//        area vamo = new area();
//        vamo.setCodigo(007);
//        vamo.setDescricao("");
//        vamo.setData(new Date(1999, 01, 10));
//        area vamo2 = new area();
//        vamo2.setCodigo(777);
//        vamo2.setDescricao("");
//        vamo2.setData(new Date(1999, 2, 2));
//        Excel asd = new Excel();
//        ArrayList<area> teste2 = new ArrayList<>();
//        teste2.add(vamo);
//        teste2.add(vamo2);
//        asd.Escrever(teste, (ArrayList<Object>) (Object) teste2);
        //JTextField dataInicial = new JTextField();
        //JTextField dataFinal = new JPasswordField();
//        new ObjectTableModelDemo().show();
    }

}
