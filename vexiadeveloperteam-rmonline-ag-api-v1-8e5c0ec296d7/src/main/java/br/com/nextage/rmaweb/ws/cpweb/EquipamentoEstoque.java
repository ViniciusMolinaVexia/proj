
package br.com.nextage.rmaweb.ws.cpweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for equipamentoEstoque complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="equipamentoEstoque">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="equipamento" type="{http://ws.cpweb.nextage.com.br/}equipamento" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="localEstoque" type="{http://ws.cpweb.nextage.com.br/}localEstoque" minOccurs="0"/>
 *         &lt;element name="qtde" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="qtdeErp" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="qtdeTranferencia" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="reserva" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="totalPrecoEquipamento" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "equipamentoEstoque", propOrder = {
    "equipamento",
    "id",
    "localEstoque",
    "qtde",
    "qtdeErp",
    "qtdeTranferencia",
    "reserva",
    "totalPrecoEquipamento"
})
public class EquipamentoEstoque {

    protected Equipamento equipamento;
    protected Integer id;
    protected LocalEstoque localEstoque;
    protected Double qtde;
    protected Double qtdeErp;
    protected Double qtdeTranferencia;
    protected Boolean reserva;
    protected Double totalPrecoEquipamento;

    /**
     * Gets the value of the equipamento property.
     * 
     * @return
     *     possible object is
     *     {@link Equipamento }
     *     
     */
    public Equipamento getEquipamento() {
        return equipamento;
    }

    /**
     * Sets the value of the equipamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Equipamento }
     *     
     */
    public void setEquipamento(Equipamento value) {
        this.equipamento = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Gets the value of the localEstoque property.
     * 
     * @return
     *     possible object is
     *     {@link LocalEstoque }
     *     
     */
    public LocalEstoque getLocalEstoque() {
        return localEstoque;
    }

    /**
     * Sets the value of the localEstoque property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalEstoque }
     *     
     */
    public void setLocalEstoque(LocalEstoque value) {
        this.localEstoque = value;
    }

    /**
     * Gets the value of the qtde property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getQtde() {
        return qtde;
    }

    /**
     * Sets the value of the qtde property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setQtde(Double value) {
        this.qtde = value;
    }

    /**
     * Gets the value of the qtdeErp property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getQtdeErp() {
        return qtdeErp;
    }

    /**
     * Sets the value of the qtdeErp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setQtdeErp(Double value) {
        this.qtdeErp = value;
    }

    /**
     * Gets the value of the qtdeTranferencia property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getQtdeTranferencia() {
        return qtdeTranferencia;
    }

    /**
     * Sets the value of the qtdeTranferencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setQtdeTranferencia(Double value) {
        this.qtdeTranferencia = value;
    }

    /**
     * Gets the value of the reserva property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReserva() {
        return reserva;
    }

    /**
     * Sets the value of the reserva property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReserva(Boolean value) {
        this.reserva = value;
    }

    /**
     * Gets the value of the totalPrecoEquipamento property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalPrecoEquipamento() {
        return totalPrecoEquipamento;
    }

    /**
     * Sets the value of the totalPrecoEquipamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalPrecoEquipamento(Double value) {
        this.totalPrecoEquipamento = value;
    }

}
