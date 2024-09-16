
package com.sap.document.sap.rfc.functions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteudo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="T_ITENS" type="{urn:sap-com:document:sap:rfc:functions}ZGLS_TAB_RMA_REQUISICAO"/>
 *         &lt;element name="T_ANEXO" type="{urn:sap-com:document:sap:rfc:functions}ZGLS_TAB_RMA_REQUISICAO_ANEXO"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "titens",
    "tanexo"
})
@XmlRootElement(name = "ZGL_RMA_CRIA_REQUISICAO")
public class ZGLRMACRIAREQUISICAO {

    @XmlElement(name = "T_ITENS", required = true)
    protected ZGLSTABRMAREQUISICAO titens;
    @XmlElement(name = "T_ANEXO", required = true)
    protected ZGLSTABRMAREQUISICAOANEXO tanexo;

    /**
     * obtem o valor da propriedade titens.
     * 
     * @return
     *     possible object is
     *     {@link ZGLSTABRMAREQUISICAO }
     *     
     */
    public ZGLSTABRMAREQUISICAO getTITENS() {
        return titens;
    }

    /**
     * Define o valor da propriedade titens.
     * 
     * @param value
     *     allowed object is
     *     {@link ZGLSTABRMAREQUISICAO }
     *     
     */
    public void setTITENS(ZGLSTABRMAREQUISICAO value) {
        this.titens = value;
    }

    /**
     * obtem o valor da propriedade tanexo.
     * 
     * @return
     *     possible object is
     *     {@link ZGLSTABRMAREQUISICAOANEXO }
     *     
     */
    public ZGLSTABRMAREQUISICAOANEXO getTANEXO() {
        return tanexo;
    }

    /**
     * Define o valor da propriedade tanexo.
     * 
     * @param value
     *     allowed object is
     *     {@link ZGLSTABRMAREQUISICAOANEXO }
     *     
     */
    public void setTANEXO(ZGLSTABRMAREQUISICAOANEXO value) {
        this.tanexo = value;
    }

}
