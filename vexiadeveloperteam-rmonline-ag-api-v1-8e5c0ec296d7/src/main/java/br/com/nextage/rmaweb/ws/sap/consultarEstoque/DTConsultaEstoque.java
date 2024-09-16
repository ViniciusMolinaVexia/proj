
package br.com.nextage.rmaweb.ws.sap.consultarEstoque;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DT_ConsultaEstoque complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DT_ConsultaEstoque">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cod_deposito" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cod_material" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="centro" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_ConsultaEstoque", namespace = "urn:cccc.rmaweb.consultaestoque", propOrder = {
    "codDeposito",
    "codMaterial",
    "centro"
})
public class DTConsultaEstoque {

    @XmlElement(name = "cod_deposito", required = true)
    protected String codDeposito;
    @XmlElement(name = "cod_material")
    protected List<String> codMaterial;
    @XmlElement(required = true)
    protected String centro;

    /**
     * Gets the value of the codDeposito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodDeposito() {
        return codDeposito;
    }

    /**
     * Sets the value of the codDeposito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodDeposito(String value) {
        this.codDeposito = value;
    }

    /**
     * Gets the value of the codMaterial property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the codMaterial property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCodMaterial().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCodMaterial() {
        if (codMaterial == null) {
            codMaterial = new ArrayList<String>();
        }
        return this.codMaterial;
    }

    /**
     * Gets the value of the centro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCentro() {
        return centro;
    }

    /**
     * Sets the value of the centro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCentro(String value) {
        this.centro = value;
    }

}
