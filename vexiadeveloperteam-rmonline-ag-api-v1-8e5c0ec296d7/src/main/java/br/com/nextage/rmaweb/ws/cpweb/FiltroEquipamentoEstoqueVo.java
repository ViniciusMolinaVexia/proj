
package br.com.nextage.rmaweb.ws.cpweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for filtroEquipamentoEstoqueVo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="filtroEquipamentoEstoqueVo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codDep" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codMat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eapDeposito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomeMat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "filtroEquipamentoEstoqueVo", propOrder = {
    "codDep",
    "codMat",
    "eapDeposito",
    "nomeMat"
})
public class FiltroEquipamentoEstoqueVo {

    protected String codDep;
    protected String codMat;
    protected String eapDeposito;
    protected String nomeMat;

    /**
     * Gets the value of the codDep property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodDep() {
        return codDep;
    }

    /**
     * Sets the value of the codDep property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodDep(String value) {
        this.codDep = value;
    }

    /**
     * Gets the value of the codMat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodMat() {
        return codMat;
    }

    /**
     * Sets the value of the codMat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodMat(String value) {
        this.codMat = value;
    }

    /**
     * Gets the value of the eapDeposito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEapDeposito() {
        return eapDeposito;
    }

    /**
     * Sets the value of the eapDeposito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEapDeposito(String value) {
        this.eapDeposito = value;
    }

    /**
     * Gets the value of the nomeMat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeMat() {
        return nomeMat;
    }

    /**
     * Sets the value of the nomeMat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeMat(String value) {
        this.nomeMat = value;
    }

}
