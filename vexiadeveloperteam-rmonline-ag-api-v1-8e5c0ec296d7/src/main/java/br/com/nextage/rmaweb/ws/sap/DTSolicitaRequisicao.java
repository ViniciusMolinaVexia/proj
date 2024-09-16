
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
 * <p>Java class for DT_SolicitaRequisicao complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DT_SolicitaRequisicao">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Itens" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Item" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="CodRma" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="Material" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="Solicitante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="Centro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="Quantidade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="UnidMedida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="Coletor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="DiagRede" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="Operacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="Deposito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="DtNecessidade" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                   &lt;element name="LocalEntrega" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="Fornecedor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="Aprovador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "DT_SolicitaRequisicao", namespace = "urn:cccc.rmaweb.requisicao", propOrder = {
    "itens"
})
public class DTSolicitaRequisicao {

    @XmlElement(name = "Itens")
    protected List<DTSolicitaRequisicao.Itens> itens;

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
     * {@link DTSolicitaRequisicao.Itens }
     * 
     * 
     */
    public List<DTSolicitaRequisicao.Itens> getItens() {
        if (itens == null) {
            itens = new ArrayList<DTSolicitaRequisicao.Itens>();
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
     *         &lt;element name="Item" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="CodRma" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="Material" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="Solicitante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="Centro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="Quantidade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="UnidMedida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="Coletor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DiagRede" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="Operacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="Deposito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DtNecessidade" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *         &lt;element name="LocalEntrega" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="Fornecedor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="Aprovador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "item",
        "codRma",
        "material",
        "solicitante",
        "centro",
        "quantidade",
        "unidMedida",
        "coletor",
        "diagRede",
        "operacao",
        "deposito",
        "dtNecessidade",
        "localEntrega",
        "fornecedor",
        "aprovador"
    })
    public static class Itens {

        @XmlElement(name = "Item")
        protected String item;
        @XmlElement(name = "CodRma")
        protected String codRma;
        @XmlElement(name = "Material")
        protected String material;
        @XmlElement(name = "Solicitante")
        protected String solicitante;
        @XmlElement(name = "Centro")
        protected String centro;
        @XmlElement(name = "Quantidade")
        protected String quantidade;
        @XmlElement(name = "UnidMedida")
        protected String unidMedida;
        @XmlElement(name = "Coletor")
        protected String coletor;
        @XmlElement(name = "DiagRede")
        protected String diagRede;
        @XmlElement(name = "Operacao")
        protected String operacao;
        @XmlElement(name = "Deposito")
        protected String deposito;
        @XmlElement(name = "DtNecessidade")
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar dtNecessidade;
        @XmlElement(name = "LocalEntrega")
        protected String localEntrega;
        @XmlElement(name = "Fornecedor")
        protected String fornecedor;
        @XmlElement(name = "Aprovador")
        protected String aprovador;

        /**
         * Gets the value of the item property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getItem() {
            return item;
        }

        /**
         * Sets the value of the item property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setItem(String value) {
            this.item = value;
        }

        /**
         * Gets the value of the codRma property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodRma() {
            return codRma;
        }

        /**
         * Sets the value of the codRma property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodRma(String value) {
            this.codRma = value;
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
         * Gets the value of the solicitante property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSolicitante() {
            return solicitante;
        }

        /**
         * Sets the value of the solicitante property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSolicitante(String value) {
            this.solicitante = value;
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
         * Gets the value of the dtNecessidade property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDtNecessidade() {
            return dtNecessidade;
        }

        /**
         * Sets the value of the dtNecessidade property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDtNecessidade(XMLGregorianCalendar value) {
            this.dtNecessidade = value;
        }

        /**
         * Gets the value of the localEntrega property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLocalEntrega() {
            return localEntrega;
        }

        /**
         * Sets the value of the localEntrega property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLocalEntrega(String value) {
            this.localEntrega = value;
        }

        /**
         * Gets the value of the fornecedor property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFornecedor() {
            return fornecedor;
        }

        /**
         * Sets the value of the fornecedor property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFornecedor(String value) {
            this.fornecedor = value;
        }

        /**
         * Gets the value of the aprovador property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAprovador() {
            return aprovador;
        }

        /**
         * Sets the value of the aprovador property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAprovador(String value) {
            this.aprovador = value;
        }

    }

}
