
package br.com.nextage.rmaweb.ws.sap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "MT_Erro", targetNamespace = "urn:cccc.reeweb.objetoscomuns")
public class P3MTErro
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private MTErro faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public P3MTErro(String message, MTErro faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public P3MTErro(String message, MTErro faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: br.com.nextage.rmaweb.ws.sap.MTErro
     */
    public MTErro getFaultInfo() {
        return faultInfo;
    }

}
