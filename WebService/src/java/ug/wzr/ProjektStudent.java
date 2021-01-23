/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ug.wzr;

import java.util.Set;

/**
 *
 * @author jsarnowski
 */
public class ProjektStudent {
    private int nrIndeksu, idProjektu;
    private Student student;
    private Projekt projekt;
    
    public ProjektStudent(int nrIndeksu, int idProjektu, String imie, String nazwisko, String tytul, String opis) {
        this.nrIndeksu = nrIndeksu;
        this.idProjektu = idProjektu;
        this.student = new Student(nrIndeksu, imie, nazwisko);
        this.projekt = new Projekt(idProjektu, tytul, opis);
    }
    
    public int getNrIndeksu() {
        return student.getNrIndeksu();
    }

    public void setNrIndeksu(int nrIndeksu) {
        student.setNrIndeksu(nrIndeksu);
    }
    
    public int getIdProjektu() {
        return projekt.getIdProjektu();
    }
    
    public void setIdProjektu(int idProjektu) {
        projekt.setIdProjektu(idProjektu);
    }
    
    public String getImie() {
        return student.getImie();
    }
    
    public void setImie(String imie) {
        student.setImie(imie);
    }
    
    public String getNazwisko() {
        return student.getNazwisko();
    }
    
    public void setNazwisko(String nazwisko) {
        student.setNazwisko(nazwisko);
    }
    
    public String getTytul() {
        return projekt.getTytul();
    }
    
    public void setTytul(String tytul) {
        projekt.setTytul(tytul);
    }
    
    public String getOpis() {
        return projekt.getOpis();
    }
    
    public void setOpis(String opis) {
        projekt.setOpis(opis);
    }
}
