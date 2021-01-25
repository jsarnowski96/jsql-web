/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ug.wzr;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author jsarnowski
 */
@WebService(serviceName = "ProjektStudentService")
public class ProjektStudentService {
    public DatabaseConnection databaseConnection = new DatabaseConnection();
    public Connection connection;

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllStudentProjects")
    public ArrayList<ProjektStudent> getAllStudentProjects() {
        databaseConnection.ConnectToDatabase();
        connection = databaseConnection.getConnection();
        
        ArrayList<ProjektStudent> projektyStudenci = new ArrayList<ProjektStudent>();
        ResultSet rs = null;
        String query = "use ug_wzr; Select Studenci.nrIndeksu, Studenci.imie, Studenci.nazwisko, Projekty.id, Projekty.tytul, Projekty.opis"
                + " from ProjektyStudenci"
                + " inner join Studenci on ProjektyStudenci.nrIndeksu = Studenci.nrIndeksu"
                + " inner join Projekty on ProjektyStudenci.idProjektu = Projekty.id";
        
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                int nrIndeksu = rs.getInt("nrIndeksu");
                int idProjektu = rs.getInt("id");
                String imie = rs.getString("imie");
                String nazwisko = rs.getString("nazwisko");
                String tytul = rs.getString("tytul");
                String opis = rs.getString("opis");
                
                ProjektStudent projektStudent = new ProjektStudent(nrIndeksu, idProjektu, imie, nazwisko, tytul, opis);
                projektyStudenci.add(projektStudent);
            }
            
            ps.close();
            connection.close();
            databaseConnection.CloseConnection();
                       
            return projektyStudenci;
        } catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "addToProject")
    public Boolean addToProject(@WebParam(name = "nrIndeksu") int nrIndeksu, @WebParam(name = "idProjektu") int idProjektu) {
        databaseConnection.ConnectToDatabase();
        connection = databaseConnection.getConnection();
       
        ResultSet rs = null;
        boolean ss;
        int id, is, ns;
        String query = "use ug_wzr; select nrIndeksu from Studenci where nrIndeksu = " + Integer.toString(nrIndeksu);
        
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            if(rs.next()) {
                query = "select id from Projekty where id = " + Integer.toString(idProjektu);
                ps.close();
                ps = connection.prepareStatement(query);
                rs = ps.executeQuery();
                
                if(rs.next()) {
                    query = "select nrIndeksu, idProjektu from ProjektyStudenci where nrIndeksu = " + Integer.toString(nrIndeksu) + " and idProjektu = " + Integer.toString(idProjektu);
                    ps.close();
                    ps = connection.prepareStatement(query);
                    rs = ps.executeQuery();
                    
                    if(rs.next()) {
                        System.out.println("Student o numere indeksu " + Integer.toString(nrIndeksu) + " jest już wpisany w projekcie " + Integer.toString(idProjektu));
                    } else {
                        query = "insert into ProjektyStudenci values(" + Integer.toString(nrIndeksu) + "," + Integer.toString(idProjektu) + ")";
                        ps.close();
                        ps = connection.prepareStatement(query);
                        is = ps.executeUpdate();

                        if(is > 0) {
                            ps.close();
                            connection.close();
                            databaseConnection.CloseConnection();

                            return true;
                        }
                    }
                }
            } else {
                System.out.println("Student o podanym numerze indeksu nie istnieje");
            }
                
            ps.close();
            connection.close();
            databaseConnection.CloseConnection();
            
            return false;
        } catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
}
