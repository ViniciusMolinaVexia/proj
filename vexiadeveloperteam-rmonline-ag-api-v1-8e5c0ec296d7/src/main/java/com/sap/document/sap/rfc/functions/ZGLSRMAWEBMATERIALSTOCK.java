
package com.sap.document.sap.rfc.functions;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de ZGLS_RMAWEB_MATERIAL_STOCK complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteudo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="ZGLS_RMAWEB_MATERIAL_STOCK">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MATERIAL" type="{urn:sap-com:document:sap:rfc:functions}char18"/>
 *         &lt;element name="DEPOSITO" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="ESTOQUE_LIVRE" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZGLS_RMAWEB_MATERIAL_STOCK", propOrder = {
    "material",
    "deposito",
    "estoquelivre"
})
public class ZGLSRMAWEBMATERIALSTOCK {

    @XmlElement(name = "MATERIAL", required = true)
    protected String material;
    @XmlElement(name = "DEPOSITO", required = true)
    protected String deposito;
    @XmlElement(name = "ESTOQUE_LIVRE", required = true)
    protected BigDecimal estoquelivre;

    /**
     * obtem o valor da propriedade material.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMATERIAL() {
        return material;
    }

    /**
     * Define o valor da propriedade material.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMATERIAL(String value) {
        this.material = value;
    }

    /**
     * obtem o valor da propriedade deposito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDEPOSITO() {
        return deposito;
    }

    /**
     * Define o valor da propriedade deposito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDEPOSITO(String value) {
        this.deposito = value;
    }

    /**
     * obtem o valor da propriedade estoquelivre.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getESTOQUELIVRE() {
        return estoquelivre;
    }

    /**
     * Define o valor da propriedade estoquelivre.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setESTOQUELIVRE(BigDecimal value) {
        this.estoquelivre = value;
    }

}
