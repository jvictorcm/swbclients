/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Victor
 */
public class empresa {

    private String nome;
    private String codigoCNPJ;
    private String ID;
    private Date data;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigoCNPJ() {
        return codigoCNPJ;
    }

    public void setCodigoCNPJ(String codigoCNPJ) {
        this.codigoCNPJ = codigoCNPJ;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void empresa() {

    }

}
