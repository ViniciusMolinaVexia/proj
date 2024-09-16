
package br.com.nextage.rmaweb.ws.sap.consultarEstoque;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DT_ConsultaEstoque_Response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DT_ConsultaEstoque_Response">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="material_deposito" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="cod_deposito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="cod_material" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="nome_material" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="quantidade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "DT_ConsultaEstoque_Response", namespace = "urn:cccc.rmaweb.consultaestoque", propOrder = {
    "materialDeposito"
})
public class DTConsultaEstoqueResponse {

    @XmlElement(name = "material_deposito")
    protected List<DTConsultaEstoqueResponse.MaterialDeposito> materialDeposito;

    /**
     * Gets the value of the materialDeposito property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the materialDeposito property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMaterialDeposito().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTConsultaEstoqueResponse.MaterialDeposito }
     * 
     * 
     */
    public List<DTConsultaEstoqueResponse.MaterialDeposito> getMaterialDeposito() {
        if (materialDeposito == null) {
            materialDeposito = new ArrayList<DTConsultaEstoqueResponse.MaterialDeposito>();
        }
        return this.materialDeposito;
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
     *         &lt;element name="cod_deposito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="cod_material" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="nome_material" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="quantidade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "codDeposito",
        "codMaterial",
        "nomeMaterial",
        "quantidade"
    })
    public static class MaterialDeposito {

        @XmlElement(name = "cod_deposito")
        protected String codDeposito;
        @XmlElement(name = "cod_material")
        protected String codMaterial;
        @XmlElement(name = "nome_material")
        protected String nomeMaterial;
        protected String quantidade;

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
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodMaterial() {
            return codMaterial;
        }

        /**
         * Sets the value of the codMaterial property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodMaterial(String value) {
            this.codMaterial = value;
        }

        /**
         * Gets the value of the nomeMaterial property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNomeMaterial() {
            return nomeMaterial;
        }

        /**
         * Sets the value of the nomeMaterial property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNomeMaterial(String value) {
            this.nomeMaterial = value;
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

    }

}
