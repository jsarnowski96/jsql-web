/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ug.wzr;

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
@WebService(serviceName = "StudentService")
public class StudentService {
    public DatabaseConnection databaseConnection = new DatabaseConnection();
    public Connection connection;
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllStudents")
    public ArrayList<Student> getAllStudents() {
        databaseConnection.ConnectToDatabase();
        connection = databaseConnection.getConnection();
        
        ArrayList<Student> studenci = new ArrayList<Student>();
        ResultSet rs = null;
        String query = "use ug_wzr; Select * from Studenci";
        
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                int nrIndeksu = rs.getInt("nrIndeksu");
                String imie = rs.getString("imie");
                String nazwisko = rs.getString("nazwisko");
                
                Student student = new Student(nrIndeksu, imie, nazwisko);
                studenci.add(student);
            }
            
            connection.close();
            databaseConnection.CloseConnection();
                       
            return studenci;
        } catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getStudent")
    public Student getStudent(@WebParam(name = "nrIndeksu") int nrIndeksu) {
        databaseConnection.ConnectToDatabase();
        connection = databaseConnection.getConnection();
        
        Student student;
        ResultSet rs = null;
        String query = "use ug_wzr; select imie, nazwisko from Studenci where nrIndeksu = " + Integer.toString(nrIndeksu);
        
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            if(rs.next()) {
                String imie = rs.getString("imie");
                String nazwisko = rs.getString("nazwisko");

                student = new Student(nrIndeksu, imie, nazwisko);   
                
                connection.close();
                databaseConnection.CloseConnection();
                
                return student;
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
    @WebMethod(operationName = "deleteStudent")
    public boolean deleteStudent(@WebParam(name = "nrIndeksu") int nrIndeksu) {
        databaseConnection.ConnectToDatabase();
        connection = databaseConnection.getConnection();
        
        Student student;
        int ds;
        String query = "use ug_wzr; delete from ProjektyStudenci where nrIndeksu = " + Integer.toString(nrIndeksu);
        
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ds = ps.executeUpdate();
            
            if(ds > 0) {          
                query = "delete from Studenci where nrIndeksu = " + Integer.toString(nrIndeksu);
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
            
            System.out.println("Podany student nie istnieje");
            return false;
        } catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createStudent")
    public Boolean createStudent(@WebParam(name = "nrIndeksu") int nrIndeksu, @WebParam(name = "imie") String imie, @WebParam(name = "nazwisko") String nazwisko) {
        databaseConnection.ConnectToDatabase();
        connection = databaseConnection.getConnection();
       
        ResultSet rs = null;
        int ns;
        String query = "use ug_wzr; select * from Studenci where nrIndeksu = " + Integer.toString(nrIndeksu);
        
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            if(rs.next()) {
                System.out.println("Student o podanym numerze indeksu już istnieje w bazie");
            } else {
                query = "insert into Studenci values(" + Integer.toString(nrIndeksu) + ", '" + imie + "', '" + nazwisko + "')"; 
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
    @WebMethod(operationName = "updateStudent")
    public Boolean updateStudent(@WebParam(name="nrIndeksu") int nrIndeksu, @WebParam(name = "imie") String imie, @WebParam(name = "nazwisko") String nazwisko) {
        databaseConnection.ConnectToDatabase();
        connection = databaseConnection.getConnection();
        
        int us;
        String query = "use ug_wzr; update Studenci set imie = '" + imie + "', nazwisko = '" + nazwisko + "' where nrIndeksu = " + Integer.toString(nrIndeksu);
        
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
