/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Victor
 */
public class contato {

    private int ID;
    private String nome;
    private String cpf;
    private String telRes;
    private String telcel;
    private String email;
    private Date data;
    private int IDarea;
    private int IDempresa;
    private String nomedaArea, nomeDaEmpresa;

    public String getNomedaArea() {
        return nomedaArea;
    }

    public void setNomedaArea(String nomedaArea) {
        this.nomedaArea = nomedaArea;
    }

    public String getNomeDaEmpresa() {
        return nomeDaEmpresa;
    }

    public void setNomeDaEmpresa(String nomeDaEmpresa) {
        this.nomeDaEmpresa = nomeDaEmpresa;
    }

    public contato(String nome, String cpf, String telRes, String telcel, String email, int IDarea, int IDempresa) throws ParseException {
        this.nome = nome;
        this.cpf = cpf;
        this.telRes = telRes;
        this.telcel = telcel;
        this.email = email;
        this.IDarea = IDarea;
        this.IDempresa = IDempresa;
    }

    public int getIDempresa() {
        return IDempresa;
    }

    public void setIDempresa(int IDempresa) {
        this.IDempresa = IDempresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelRes() {
        return telRes;
    }

    public void setTelRes(String telRes) {
        this.telRes = telRes;
    }

    public String getTelcel() {
        return telcel;
    }

    public void setTelcel(String telcel) {
        this.telcel = telcel;
    }

    public Date getData() {
        return data;
    }

    public void setData(String data) throws ParseException {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date dataa = new java.sql.Date(fmt.parse(data).getTime());
        this.data = dataa;
    }

    public void setData(Date formatoDate) {
        this.data = formatoDate;
    }

    public int getIDarea() {
        return IDarea;
    }

    public void setIDarea(int IDarea) {
        this.IDarea = IDarea;
    }

}
