
package br.com.nextage.rmaweb.ws.sap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.nextage.rmaweb.ws.sap package. 
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

    private final static QName _MTSolicitaRequisicao_QNAME = new QName("urn:cccc.rmaweb.requisicao", "MT_SolicitaRequisicao");
    private final static QName _MTSolicitaRequisicaoResponse_QNAME = new QName("urn:cccc.rmaweb.requisicao", "MT_SolicitaRequisicao_response");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.nextage.rmaweb.ws.sap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTSolicitaRequisicao }
     * 
     */
    public DTSolicitaRequisicao createDTSolicitaRequisicao() {
        return new DTSolicitaRequisicao();
    }

    /**
     * Create an instance of {@link DTSolicitaRequisicaoResponse }
     * 
     */
    public DTSolicitaRequisicaoResponse createDTSolicitaRequisicaoResponse() {
        return new DTSolicitaRequisicaoResponse();
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
     * Create an instance of {@link ExchangeLogData }
     * 
     */
    public ExchangeLogData createExchangeLogData() {
        return new ExchangeLogData();
    }

    /**
     * Create an instance of {@link DTSolicitaRequisicao.Itens }
     * 
     */
    public DTSolicitaRequisicao.Itens createDTSolicitaRequisicaoItens() {
        return new DTSolicitaRequisicao.Itens();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSolicitaRequisicao }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:cccc.rmaweb.requisicao", name = "MT_SolicitaRequisicao")
    public JAXBElement<DTSolicitaRequisicao> createMTSolicitaRequisicao(DTSolicitaRequisicao value) {
        return new JAXBElement<DTSolicitaRequisicao>(_MTSolicitaRequisicao_QNAME, DTSolicitaRequisicao.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSolicitaRequisicaoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:cccc.rmaweb.requisicao", name = "MT_SolicitaRequisicao_response")
    public JAXBElement<DTSolicitaRequisicaoResponse> createMTSolicitaRequisicaoResponse(DTSolicitaRequisicaoResponse value) {
        return new JAXBElement<DTSolicitaRequisicaoResponse>(_MTSolicitaRequisicaoResponse_QNAME, DTSolicitaRequisicaoResponse.class, null, value);
    }

}
