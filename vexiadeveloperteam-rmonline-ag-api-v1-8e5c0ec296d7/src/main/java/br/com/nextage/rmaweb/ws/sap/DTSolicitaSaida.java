
package br.com.nextage.rmaweb.ws.sap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DT_SolicitaSaida complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DT_SolicitaSaida">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Centro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Material" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Deposito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Coletor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DiagRede" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Operacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Quantidade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UnidMedida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_SolicitaSaida", namespace = "urn:cccc.rmaweb.saidamercadoria", propOrder = {
    "centro",
    "material",
    "deposito",
    "coletor",
    "diagRede",
    "operacao",
    "quantidade",
    "unidMedida"
})
public class DTSolicitaSaida {

    @XmlElement(name = "Centro")
    protected String centro;
    @XmlElement(name = "Material")
    protected String material;
    @XmlElement(name = "Deposito")
    protected String deposito;
    @XmlElement(name = "Coletor")
    protected String coletor;
    @XmlElement(name = "DiagRede")
    protected String diagRede;
    @XmlElement(name = "Operacao")
    protected String operacao;
    @XmlElement(name = "Quantidade")
    protected String quantidade;
    @XmlElement(name = "UnidMedida")
    protected String unidMedida;

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

    /**
     * Gets the value of the material property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Sets the value of the material property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaterial(String value) {
        this.material = value;
    }

    /**
     * Gets the value of the deposito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeposito() {
        return deposito;
    }

    /**
     * Sets the value of the deposito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeposito(String value) {
        this.deposito = value;
    }

    /**
     * Gets the value of the coletor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColetor() {
        return coletor;
    }

    /**
     * Sets the value of the coletor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColetor(String value) {
        this.coletor = value;
    }

    /**
     * Gets the value of the diagRede property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiagRede() {
        return diagRede;
    }

    /**
     * Sets the value of the diagRede property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiagRede(String value) {
        this.diagRede = value;
    }

    /**
     * Gets the value of the operacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperacao() {
        return operacao;
    }

    /**
     * Sets the value of the operacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperacao(String value) {
        this.operacao = value;
    }

    /**
     * Gets the value of the quantidade property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuantidade() {
        return quantidade;
    }

    /**
     * Sets the value of the quantidade property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuantidade(String value) {
        this.quantidade = value;
    }

    /**
     * Gets the value of the unidMedida property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnidMedida() {
        return unidMedida;
    }

    /**
     * Sets the value of the unidMedida property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnidMedida(String value) {
        this.unidMedida = value;
    }

}
