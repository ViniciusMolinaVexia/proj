
package br.com.nextage.rmaweb.ws.cpweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tipoEquipFuncao complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tipoEquipFuncao">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="funcao" type="{http://ws.cpweb.nextage.com.br/}funcao" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tipoEquipamento" type="{http://ws.cpweb.nextage.com.br/}tipoEquipamento" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tipoEquipFuncao", propOrder = {
    "funcao",
    "id",
    "tipoEquipamento"
})
public class TipoEquipFuncao {

    protected Funcao funcao;
    protected Integer id;
    protected TipoEquipamento tipoEquipamento;

    /**
     * Gets the value of the funcao property.
     * 
     * @return
     *     possible object is
     *     {@link Funcao }
     *     
     */
    public Funcao getFuncao() {
        return funcao;
    }

    /**
     * Sets the value of the funcao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Funcao }
     *     
     */
    public void setFuncao(Funcao value) {
        this.funcao = value;
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
     * Gets the value of the tipoEquipamento property.
     * 
     * @return
     *     possible object is
     *     {@link TipoEquipamento }
     *     
     */
    public TipoEquipamento getTipoEquipamento() {
        return tipoEquipamento;
    }

    /**
     * Sets the value of the tipoEquipamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoEquipamento }
     *     
     */
    public void setTipoEquipamento(TipoEquipamento value) {
        this.tipoEquipamento = value;
    }

}
