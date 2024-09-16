
package br.com.nextage.rmaweb.ws.sap.consultarEstoque;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.nextage.rmaweb.ws.sap.consultarEstoque package. 
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

    private final static QName _MTConsultaEstoqueResponse_QNAME = new QName("urn:cccc.rmaweb.consultaestoque", "MT_ConsultaEstoque_Response");
    private final static QName _MTConsultaEstoque_QNAME = new QName("urn:cccc.rmaweb.consultaestoque", "MT_ConsultaEstoque");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.nextage.rmaweb.ws.sap.consultarEstoque
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTConsultaEstoqueResponse }
     * 
     */
    public DTConsultaEstoqueResponse createDTConsultaEstoqueResponse() {
        return new DTConsultaEstoqueResponse();
    }

    /**
     * Create an instance of {@link MTErro }
     * 
     */
    public MTErro createMTErro() {
        return new MTErro();
    }

    /**
     * Create an instance of {@link ExchangeFaultData }
     * 
     */
    public ExchangeFaultData createExchangeFaultData() {
        return new ExchangeFaultData();
    }

    /**
     * Create an instance of {@link DTConsultaEstoque }
     * 
     */
    public DTConsultaEstoque createDTConsultaEstoque() {
        return new DTConsultaEstoque();
    }

    /**
     * Create an instance of {@link ExchangeLogData }
     * 
     */
    public ExchangeLogData createExchangeLogData() {
        return new ExchangeLogData();
    }

    /**
     * Create an instance of {@link DTConsultaEstoqueResponse.MaterialDeposito }
     * 
     */
    public DTConsultaEstoqueResponse.MaterialDeposito createDTConsultaEstoqueResponseMaterialDeposito() {
        return new DTConsultaEstoqueResponse.MaterialDeposito();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTConsultaEstoqueResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:cccc.rmaweb.consultaestoque", name = "MT_ConsultaEstoque_Response")
    public JAXBElement<DTConsultaEstoqueResponse> createMTConsultaEstoqueResponse(DTConsultaEstoqueResponse value) {
        return new JAXBElement<DTConsultaEstoqueResponse>(_MTConsultaEstoqueResponse_QNAME, DTConsultaEstoqueResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTConsultaEstoque }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:cccc.rmaweb.consultaestoque", name = "MT_ConsultaEstoque")
    public JAXBElement<DTConsultaEstoque> createMTConsultaEstoque(DTConsultaEstoque value) {
        return new JAXBElement<DTConsultaEstoque>(_MTConsultaEstoque_QNAME, DTConsultaEstoque.class, null, value);
    }

}
