
package br.com.nextage.rmaweb.ws.cpweb;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "SincEstoque", targetNamespace = "http://ws.cpweb.nextage.com.br/", wsdlLocation = "http://localhost:8080/cpweb/SincEstoque?wsdl")
public class SincEstoque_Service
    extends Service
{

    private final static URL SINCESTOQUE_WSDL_LOCATION;
    private final static WebServiceException SINCESTOQUE_EXCEPTION;
    private final static QName SINCESTOQUE_QNAME = new QName("http://ws.cpweb.nextage.com.br/", "SincEstoque");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/cpweb/SincEstoque?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SINCESTOQUE_WSDL_LOCATION = url;
        SINCESTOQUE_EXCEPTION = e;
    }

    public SincEstoque_Service() {
        super(__getWsdlLocation(), SINCESTOQUE_QNAME);
    }

    public SincEstoque_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), SINCESTOQUE_QNAME, features);
    }

    public SincEstoque_Service(URL wsdlLocation) {
        super(wsdlLocation, SINCESTOQUE_QNAME);
    }

    public SincEstoque_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SINCESTOQUE_QNAME, features);
    }

    public SincEstoque_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SincEstoque_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns SincEstoque
     */
    @WebEndpoint(name = "SincEstoquePort")
    public SincEstoque getSincEstoquePort() {
        return super.getPort(new QName("http://ws.cpweb.nextage.com.br/", "SincEstoquePort"), SincEstoque.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SincEstoque
     */
    @WebEndpoint(name = "SincEstoquePort")
    public SincEstoque getSincEstoquePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.cpweb.nextage.com.br/", "SincEstoquePort"), SincEstoque.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SINCESTOQUE_EXCEPTION!= null) {
            throw SINCESTOQUE_EXCEPTION;
        }
        return SINCESTOQUE_WSDL_LOCATION;
    }

}
