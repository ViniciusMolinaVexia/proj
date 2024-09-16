
package com.sap.document.sap.rfc.functions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de ZGLS_RMA_REQUISICAO_ANEXO complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteudo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="ZGLS_RMA_REQUISICAO_ANEXO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ANEXO" type="{urn:sap-com:document:sap:rfc:functions}string"/>
 *         &lt;element name="EXTENSAO" type="{urn:sap-com:document:sap:rfc:functions}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZGLS_RMA_REQUISICAO_ANEXO", propOrder = {
    "anexo",
    "extensao"
})
public class ZGLSRMAREQUISICAOANEXO {

    @XmlElement(name = "ANEXO", required = true)
    protected String anexo;
    @XmlElement(name = "EXTENSAO", required = true)
    protected String extensao;

    /**
     * obtem o valor da propriedade anexo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getANEXO() {
        return anexo;
    }

    /**
     * Define o valor da propriedade anexo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setANEXO(String value) {
        this.anexo = value;
    }

    /**
     * obtem o valor da propriedade extensao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEXTENSAO() {
        return extensao;
    }

    /**
     * Define o valor da propriedade extensao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEXTENSAO(String value) {
        this.extensao = value;
    }

}
