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
                int idProjektu = rs.getInt("idProjektu");
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
}
