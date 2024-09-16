
package br.com.nextage.rmaweb.ws.sap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for DT_SolicitaReserva complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DT_SolicitaReserva">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DataReserva" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="CentroReceptor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SolicitanteRma" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AprovadorRma" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Itens" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Material" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="Centro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="Quantidade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="UnMedida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="Deposito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="DataNecessidade" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_SolicitaReserva", namespace = "urn:cccc.rmaweb.reservamat", propOrder = {
    "dataReserva",
    "centroReceptor",
    "solicitanteRma",
    "aprovadorRma",
    "itens"
})
public class DTSolicitaReserva {

    @XmlElement(name = "DataReserva")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataReserva;
    @XmlElement(name = "CentroReceptor")
    protected String centroReceptor;
    @XmlElement(name = "SolicitanteRma")
    protected String solicitanteRma;
    @XmlElement(name = "AprovadorRma")
    protected String aprovadorRma;
    @XmlElement(name = "Itens")
    protected List<DTSolicitaReserva.Itens> itens;

    /**
     * Gets the value of the dataReserva property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataReserva() {
        return dataReserva;
    }

    /**
     * Sets the value of the dataReserva property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataReserva(XMLGregorianCalendar value) {
        this.dataReserva = value;
    }

    /**
     * Gets the value of the centroReceptor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCentroReceptor() {
        return centroReceptor;
    }

    /**
     * Sets the value of the centroReceptor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCentroReceptor(String value) {
        this.centroReceptor = value;
    }

    /**
     * Gets the value of the solicitanteRma property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSolicitanteRma() {
        return solicitanteRma;
    }

    /**
     * Sets the value of the solicitanteRma property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSolicitanteRma(String value) {
        this.solicitanteRma = value;
    }

    /**
     * Gets the value of the aprovadorRma property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAprovadorRma() {
        return aprovadorRma;
    }

    /**
     * Sets the value of the aprovadorRma property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAprovadorRma(String value) {
        this.aprovadorRma = value;
    }

    /**
     * Gets the value of the itens property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itens property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItens().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTSolicitaReserva.Itens }
     * 
     * 
     */
    public List<DTSolicitaReserva.Itens> getItens() {
        if (itens == null) {
            itens = new ArrayList<DTSolicitaReserva.Itens>();
        }
        return this.itens;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Material" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="Centro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="Quantidade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="UnMedida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="Deposito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DataNecessidade" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
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
        "material",
        "centro",
        "quantidade",
        "unMedida",
        "deposito",
        "dataNecessidade"
    })
    public static class Itens {

        @XmlElement(name = "Material")
        protected String material;
        @XmlElement(name = "Centro")
        protected String centro;
        @XmlElement(name = "Quantidade")
        protected String quantidade;
        @XmlElement(name = "UnMedida")
        protected String unMedida;
        @XmlElement(name = "Deposito")
        protected String deposito;
        @XmlElement(name = "DataNecessidade")
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar dataNecessidade;

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
         * Gets the value of the unMedida property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUnMedida() {
            return unMedida;
        }

        /**
         * Sets the value of the unMedida property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUnMedida(String value) {
            this.unMedida = value;
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
         * Gets the value of the dataNecessidade property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDataNecessidade() {
            return dataNecessidade;
        }

        /**
         * Sets the value of the dataNecessidade property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDataNecessidade(XMLGregorianCalendar value) {
            this.dataNecessidade = value;
        }

    }

}
