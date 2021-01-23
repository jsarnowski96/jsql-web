/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ug.wzr.client;

import java.util.Iterator;
import java.util.List;
import javax.xml.ws.WebServiceRef;
import ug.wzr.ProjektStudent;
import ug.wzr.ProjektStudentService_Service;

/**
 *
 * @author jsarnowski
 */
public class Main {

    @WebServiceRef(wsdlLocation = "META-INF/wsdl/localhost_8080/WebService/ProjektStudentService.wsdl")
    private static ProjektStudentService_Service service;

    @WebServiceRef(wsdlLocation = "META-INF/wsdl/localhost_8080/WebService/ProjektStudentService.wsdl")
    private static ProjektStudentService_Service ProjektStudentService;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.out.println(pobierzStudentow());
        List<ProjektStudent> projektyStudenci = getAllStudentProjects();
        Iterator<ProjektStudent> iterator = projektyStudenci.iterator();
        while(iterator.hasNext()) {
            ProjektStudent projektStudent = iterator.next();
            System.out.println(projektStudent.getNrIndeksu());
            System.out.println(projektStudent.getImie());
            System.out.println(projektStudent.getNazwisko());
            System.out.println(projektStudent.getIdProjektu());
            System.out.println(projektStudent.getTytul());
            System.out.println(projektStudent.getOpis());
        }
    }           

    private static java.util.List<ug.wzr.ProjektStudent> getAllStudentProjects() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ug.wzr.ProjektStudentService port = service.getProjektStudentServicePort();
        return port.getAllStudentProjects();
    }
    
    
}
