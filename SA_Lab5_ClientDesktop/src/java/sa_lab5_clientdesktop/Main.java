/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa_lab5_clientdesktop;

import javax.xml.ws.WebServiceRef;
import ug.wzr.Usluga_Service;

/**
 *
 * @author jsarnowski
 */
public class Main {

    @WebServiceRef(wsdlLocation = "META-INF/wsdl/localhost_8080/SA_Lab5_Web/Usluga.wsdl")
    private static Usluga_Service service;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(pobierzStudentow());
    }

    private static java.util.List<ug.wzr.Student> pobierzStudentow() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ug.wzr.Usluga port = service.getUslugaPort();
        return port.pobierzStudentow();
    }
    
}
