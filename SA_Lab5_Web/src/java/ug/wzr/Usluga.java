/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ug.wzr;

import java.util.Arrays;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author jsarnowski
 */
@WebService(serviceName = "Usluga")
public class Usluga {


    /**
     * Web service operation
     */
    @WebMethod(operationName = "pobierzStudentow")
    public List<Student> pobierzStudentow() {
        Student std1 = new Student(111111, "test1", "test1");
        Student std2 = new Student(222222, "test2", "test2");
        Student std3 = new Student(333333, "test3", "test3");
        
        List<Student> studenci = Arrays.asList(std1, std2, std3);
        
        return studenci;
    }
}
