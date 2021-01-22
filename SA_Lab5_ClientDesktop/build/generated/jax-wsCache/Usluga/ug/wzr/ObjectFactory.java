
package ug.wzr;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ug.wzr package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PobierzStudentow_QNAME = new QName("http://wzr.ug/", "pobierzStudentow");
    private final static QName _PobierzStudentowResponse_QNAME = new QName("http://wzr.ug/", "pobierzStudentowResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ug.wzr
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PobierzStudentow }
     * 
     */
    public PobierzStudentow createPobierzStudentow() {
        return new PobierzStudentow();
    }

    /**
     * Create an instance of {@link PobierzStudentowResponse }
     * 
     */
    public PobierzStudentowResponse createPobierzStudentowResponse() {
        return new PobierzStudentowResponse();
    }

    /**
     * Create an instance of {@link Student }
     * 
     */
    public Student createStudent() {
        return new Student();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PobierzStudentow }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PobierzStudentow }{@code >}
     */
    @XmlElementDecl(namespace = "http://wzr.ug/", name = "pobierzStudentow")
    public JAXBElement<PobierzStudentow> createPobierzStudentow(PobierzStudentow value) {
        return new JAXBElement<PobierzStudentow>(_PobierzStudentow_QNAME, PobierzStudentow.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PobierzStudentowResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PobierzStudentowResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://wzr.ug/", name = "pobierzStudentowResponse")
    public JAXBElement<PobierzStudentowResponse> createPobierzStudentowResponse(PobierzStudentowResponse value) {
        return new JAXBElement<PobierzStudentowResponse>(_PobierzStudentowResponse_QNAME, PobierzStudentowResponse.class, null, value);
    }

}
