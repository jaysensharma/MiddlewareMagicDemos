
package client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the client package. 
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

    private final static QName _GetRequestHeadersResponse_QNAME = new QName("http://ws/", "getRequestHeadersResponse");
    private final static QName _GetRequestHeaders_QNAME = new QName("http://ws/", "getRequestHeaders");
    private final static QName _HelloResponse_QNAME = new QName("http://ws/", "helloResponse");
    private final static QName _Hello_QNAME = new QName("http://ws/", "hello");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link GetRequestHeaders }
     * 
     */
    public GetRequestHeaders createGetRequestHeaders() {
        return new GetRequestHeaders();
    }

    /**
     * Create an instance of {@link GetRequestHeadersResponse }
     * 
     */
    public GetRequestHeadersResponse createGetRequestHeadersResponse() {
        return new GetRequestHeadersResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRequestHeadersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "getRequestHeadersResponse")
    public JAXBElement<GetRequestHeadersResponse> createGetRequestHeadersResponse(GetRequestHeadersResponse value) {
        return new JAXBElement<GetRequestHeadersResponse>(_GetRequestHeadersResponse_QNAME, GetRequestHeadersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRequestHeaders }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "getRequestHeaders")
    public JAXBElement<GetRequestHeaders> createGetRequestHeaders(GetRequestHeaders value) {
        return new JAXBElement<GetRequestHeaders>(_GetRequestHeaders_QNAME, GetRequestHeaders.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

}
