/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Victor
 */
public class area {

    private int codigo;
    private String descricao;
    private Date data;

    public int getID() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public ArrayList<String> getAllData() {
        ArrayList<String> retorno = new ArrayList<>();
        retorno.add(String.valueOf(this.codigo));
        retorno.add(this.descricao);
        retorno.add(String.valueOf(this.data));
        return retorno;
    }
}
