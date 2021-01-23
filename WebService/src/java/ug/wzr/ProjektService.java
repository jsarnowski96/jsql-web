/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ug.wzr;

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
@WebService(serviceName = "ProjektService")
public class ProjektService {
    public DatabaseConnection databaseConnection = new DatabaseConnection();
    public Connection connection;
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllProjects")
    public ArrayList<Projekt> getAllProjects() {
        databaseConnection.ConnectToDatabase();
        connection = databaseConnection.getConnection();
        
        ArrayList<Projekt> projekty = new ArrayList<Projekt>();
        ResultSet rs = null;
        String query = "use ug_wzr; Select * from Projekty";
        
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                int idProjektu = rs.getInt("id");
                String tytul = rs.getString("tytul");
                String opis = rs.getString("opis");
                
                Projekt projekt = new Projekt(idProjektu, tytul, opis);
                projekty.add(projekt);
            }
            
            connection.close();
            databaseConnection.CloseConnection();
                       
            return projekty;
        } catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getProjekt")
    public Projekt getProjekt(@WebParam(name = "idProjektu") int idProjektu) {
        databaseConnection.ConnectToDatabase();
        connection = databaseConnection.getConnection();
        
        Projekt projekt;
        ResultSet rs = null;
        String query = "use ug_wzr; select tytul, opis from Projekty where id = " + Integer.toString(idProjektu);
        
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            if(rs.next()) {
                String tytul = rs.getString("tytul");
                String opis = rs.getString("opis");

                projekt = new Projekt(idProjektu, tytul, opis);   
                
                connection.close();
                databaseConnection.CloseConnection();
                
                return projekt;
            }
            
            connection.close();
            databaseConnection.CloseConnection();
            
            return null;
        } catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteProjekt")
    public boolean deleteProjekt(@WebParam(name = "idProjektu") int idProjektu) {
        databaseConnection.ConnectToDatabase();
        connection = databaseConnection.getConnection();
        
        Projekt projekt;
        int ds;
        String query = "use ug_wzr; delete from ProjektyStudenci where idProjektu = " + Integer.toString(idProjektu);
        //String query = "use ug_wzr; delete from Projekty where idProjektu = " + Integer.toString(idProjektu);
        
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ds = ps.executeUpdate();
            
            if(ds > 0) {    
                query = "delete from Projekty where id = " + Integer.toString(idProjektu);
                ps.close();
                ps = connection.prepareStatement(query);
                ds = ps.executeUpdate();
                
                if(ds > 0) {
                    ps.close();
                    connection.close();
                    databaseConnection.CloseConnection();
                 
                    return true;
                }
            }
            
            ps.close();
            connection.close();
            databaseConnection.CloseConnection();
            
            System.out.println("Podany projekt nie istnieje");
            return false;
        } catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createProjekt")
    public boolean createProjekt(@WebParam(name = "nrIndeksu") int nrIndeksu, @WebParam(name = "tytul") String tytul, @WebParam(name = "opis") String opis) {
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
                query = "insert into Projekty values('" + tytul + "', '" + opis + "')"; 
                ps.close();
                ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                is = ps.executeUpdate();

                if(is > 0) {
                    ResultSet idRes = ps.getGeneratedKeys();
                    if(idRes.next()) {
                        id = idRes.getInt(1);
                        query = "insert into ProjektyStudenci values(" + Integer.toString(nrIndeksu) + "," + Integer.toString(id) + ")";
                        ps.close();
                        ps = connection.prepareStatement(query);
                        ns = ps.executeUpdate();

                        if(ns > 0) {
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

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateProjekt")
    public boolean updateProjekt(@WebParam(name = "idProjektu") int idProjektu, @WebParam(name = "tytul") String tytul, @WebParam(name = "opis") String opis) {
        databaseConnection.ConnectToDatabase();
        connection = databaseConnection.getConnection();
        
        int us;
        String query = "use ug_wzr; update Projekty set tytul = '" + tytul + "', opis = '" + opis + "' where id = " + Integer.toString(idProjektu);
        
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            us = ps.executeUpdate();
            
            if(us > 0) {
                ps.close();               
                connection.close();
                databaseConnection.CloseConnection();
                
                return true;
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
