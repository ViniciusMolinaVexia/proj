
package br.com.nextage.rmaweb.ws.sap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "SI_SolicitaRequisicaoSyncOut", targetNamespace = "urn:cccc.rmaweb.requisicao")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface SISolicitaRequisicaoSyncOut {


    /**
     * 
     * @param mtSolicitaRequisicao
     * @return
     *     returns br.com.nextage.rmaweb.ws.sap.DTSolicitaRequisicaoResponse
     * @throws P3MTErro
     */
    @WebMethod(operationName = "SI_SolicitaRequisicaoSyncOut", action = "http://sap.com/xi/WebService/soap1.1")
    @WebResult(name = "MT_SolicitaRequisicao_response", targetNamespace = "urn:cccc.rmaweb.requisicao", partName = "MT_SolicitaRequisicao_response")
    public DTSolicitaRequisicaoResponse siSolicitaRequisicaoSyncOut(
        @WebParam(name = "MT_SolicitaRequisicao", targetNamespace = "urn:cccc.rmaweb.requisicao", partName = "MT_SolicitaRequisicao")
        DTSolicitaRequisicao mtSolicitaRequisicao)
        throws P3MTErro
    ;

}
