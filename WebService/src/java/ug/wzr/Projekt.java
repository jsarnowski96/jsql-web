/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ug.wzr;

/**
 *
 * @author jsarnowski
 */
public class Projekt {
    private int idProjektu;
    private String tytul, opis;
    
    public Projekt(int idProjektu, String tytul, String opis) {
        this.idProjektu = idProjektu;
        this.tytul = tytul;
        this.opis = opis;
    }
    
    public Projekt(int idProjektu) {
        this.idProjektu = idProjektu;
    }

    public int getIdProjektu() {
        return idProjektu;
    }
    
    public void setIdProjektu(int idProjektu) {
        this.idProjektu = idProjektu;
    }
    
    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
