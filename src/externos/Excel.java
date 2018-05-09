/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package externos;

/**
 *
 * @author Victor
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.area;
import model.areaDAO;
import model.contato;
import model.empresa;
import model.empresaDAO;
import view.legacy.consultaContatoPanel;

public class Excel {

    public void Escrever(ArrayList<String> nomeColuna, ArrayList<Object> objetos) throws IOException, FileNotFoundException, FileNotFoundException, IOException {
        // arquivo do excel
        HSSFWorkbook wb = new HSSFWorkbook();
        // planilha
        HSSFSheet plan1 = null;
        // cria a planilha
        plan1 = wb.createSheet("Plan1");
        // linha
        HSSFRow row = null;
        // cria a linha na planilha o parametro da função create row é a linha
        row = plan1.createRow(0);
        // cria a célula na planilha
        for (int j = 0; j <= nomeColuna.size() - 1; j++) {
            row.createCell((short) j).setCellValue(nomeColuna.get(j));
        }
        if (objetos.get(0) instanceof area) {
            for (int i = 0; i <= objetos.size() - 1; i++) {
                row = plan1.createRow(i + 1);
                area dado = (area) objetos.get(i);
                String ID = String.valueOf(dado.getID());
                row.createCell((short) 0).setCellValue(ID);
                String descricao = dado.getDescricao();
                row.createCell((short) 1).setCellValue(descricao);
                String data = String.valueOf(dado.getData());
                row.createCell((short) 2).setCellValue(data);
            }
        } else if (objetos.get(0) instanceof empresa) {
            for (int i = 0; i <= objetos.size() - 1; i++) {
                row = plan1.createRow(i + 1);
                empresa dado = (empresa) objetos.get(i);
                String ID = String.valueOf(dado.getID());
                row.createCell((short) 0).setCellValue(ID);
                String nome = dado.getNome();
                row.createCell((short) 1).setCellValue(nome);
                String CNPJ = String.valueOf(dado.getCodigoCNPJ());
                row.createCell((short) 2).setCellValue(CNPJ);
                String data = String.valueOf(dado.getData());
                row.createCell((short) 3).setCellValue(data);
            }
        } else if (objetos.get(0) instanceof contato) {
//            colunas.add("Código");
//            colunas.add("Nome");
//            colunas.add("CPF");
//            colunas.add("Telefone Residencial");
//            colunas.add("Telefone Celular");
//            colunas.add("Email");
//            colunas.add("Data de Cadastro");
//            colunas.add("Nome da Empresa");
//            colunas.add("Área");
            for (int i = 0; i <= objetos.size() - 1; i++) {
                row = plan1.createRow(i + 1);
                contato dado = (contato) objetos.get(i);
                String ID = String.valueOf(dado.getID());
                row.createCell((short) 0).setCellValue(ID);
                String nome = dado.getNome();
                row.createCell((short) 1).setCellValue(nome);
                String CNPJ = dado.getCpf();
                row.createCell((short) 2).setCellValue(CNPJ);
                String telres = dado.getTelRes();
                row.createCell((short) 3).setCellValue(telres);
                String telcel = dado.getTelcel();
                row.createCell((short) 4).setCellValue(telcel);
                String email = dado.getEmail();
                row.createCell((short) 5).setCellValue(email);
                String data = String.valueOf(dado.getData());
                row.createCell((short) 6).setCellValue(data);
                ArrayList<empresa> retornoEmpresa = new ArrayList<>();
                try {
                    empresaDAO empresaDao = new empresaDAO();
                    retornoEmpresa = empresaDao.findEmpresaByData("0000-00-00", "9999-01-01");
                } catch (SQLException ex) {
                    Logger.getLogger(consultaContatoPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int x = 0; x < retornoEmpresa.size(); x++) {
                    if (dado.getIDempresa() == Integer.valueOf(retornoEmpresa.get(x).getID())) {
                        String nomeEmpresa = String.valueOf(retornoEmpresa.get(x).getNome());
                        row.createCell((short) 7).setCellValue(nomeEmpresa);
                        break;
                    }
                }
                ArrayList<area> retornoArea = new ArrayList<>();
                try {
                    areaDAO areaDao = new areaDAO();
                    retornoArea = areaDao.findAreaByData("0000-00-00", "9999-01-01");
                } catch (SQLException ex) {
                    Logger.getLogger(consultaContatoPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int x = 0; x < retornoArea.size(); x++) {
                    if (dado.getIDarea() == retornoArea.get(x).getID()) {
                        String nomeArea = String.valueOf(retornoArea.get(x).getDescricao());
                        row.createCell((short) 8).setCellValue(nomeArea);
                        break;
                    }
                }
            }
        }
//        else if(objetos.get(0) instanceof ){
//            
//        }
        // cria o arquivo do excel
        String retorno = filechooser();
        if (!retorno.equals("")) {
            FileOutputStream stream = new FileOutputStream(retorno);
            // escreve os dados na planilha
            wb.write(stream);
            JOptionPane.showMessageDialog(null,
                    "Exportação concluida com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null,
                    "Exportação cancelada.");
        }

    }

    public void Ler(String url) throws IOException {
        FileInputStream stream = new FileInputStream(url);
        HSSFWorkbook rb = new HSSFWorkbook(stream);
        HSSFSheet plan1 = null;
        plan1 = rb.getSheet("Plan1");
        HSSFRow row = null;
        for (int i = 0; i <= 1; i++) {
            System.out.println("Linha " + i);
            row = plan1.getRow(1);
            for (int j = 1; j <= row.getLastCellNum(); j++) {
                //System.out.println(row.getCell((short) j));
                System.out.println(row.toString());
            }
        }
    }

    private String filechooser() {
        JFileChooser arquivo = new JFileChooser();
        arquivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        String caminho = "";
        File file = null;
        int retorno = arquivo.showSaveDialog(null); // showSaveDialog retorna um inteiro , e ele ira determinar que o chooser será para salvar.
        if (retorno == JFileChooser.APPROVE_OPTION) {
            caminho = arquivo.getSelectedFile().getAbsolutePath();  // o getSelectedFile pega o arquivo e o getAbsolutePath retorna uma string contendo o endereço.
            file = new File(caminho + ".xls");
            return file.getAbsolutePath();
        } else {
            Excel asd = new Excel();
            if (!caminho.equals("")) {
                file = new File(caminho + ".xls"); // o +".txt" é para ele salvar como txt . Para outro tipo de arquivo, mude a extensao final. se você nao mudar a extensao, ele vai criar como ".bin"
            } else {
                return "";
            }

        }
        return null;
    }

//    public void Escrever(ArrayList<String> nomeColuna, ArrayList<area> objetosArea) throws IOException, FileNotFoundException, FileNotFoundException, IOException {
//        HSSFWorkbook wb = new HSSFWorkbook();
//        // planilha
//        HSSFSheet plan1 = null;
//        // cria a planilha
//        plan1 = wb.createSheet("Plan1");
//        // linha
//        HSSFRow row = null;
//        // cria a linha na planilha o parametro da função create row é a linha
//        row = plan1.createRow(0);
//        // cria a célula na planilha
//        for (int j = 0; j <= nomeColuna.size() - 1; j++) {
//            row.createCell((short) j).setCellValue(nomeColuna.get(j));
//        }
//        for (int i = 0; i <= objetosArea.size() - 1; i++) {
//            row = plan1.createRow(i + 1);
//            if (objetosArea.get(i) instanceof area) {
//                area dado = (area) objetosArea.get(i);
//                String valor = String.valueOf(dado.getCodigo());
//                row.createCell((short) i).setCellValue(valor);
//            }
//
//        }
//        row.createCell((short) 0).setCellValue("teste");
//        row = plan1.createRow(1);
//        row.createCell((short) 0).setCellValue("teste2");
//        row.createCell((short) 1).setCellValue(12);
//        // cria o arquivo do excel
//        FileOutputStream stream = new FileOutputStream(filechooser());
//        // escreve os dados na planilha
//        wb.write(stream);
//    }
}
