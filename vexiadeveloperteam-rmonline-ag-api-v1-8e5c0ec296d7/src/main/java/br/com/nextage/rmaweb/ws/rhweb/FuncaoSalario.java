
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for funcaoSalario complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="funcaoSalario">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contem" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="funcao" type="{http://ws.rhweb_ws.nextage.com.br/}funcao" minOccurs="0"/>
 *         &lt;element name="historico" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="salario" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "funcaoSalario", propOrder = {
    "contem",
    "funcao",
    "historico",
    "id",
    "salario"
})
public class FuncaoSalario {

    protected Boolean contem;
    protected Funcao funcao;
    protected Boolean historico;
    protected Integer id;
    protected Double salario;

    /**
     * Gets the value of the contem property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isContem() {
        return contem;
    }

    /**
     * Sets the value of the contem property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setContem(Boolean value) {
        this.contem = value;
    }

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
     * Gets the value of the historico property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHistorico() {
        return historico;
    }

    /**
     * Sets the value of the historico property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHistorico(Boolean value) {
        this.historico = value;
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
     * Gets the value of the salario property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSalario() {
        return salario;
    }

    /**
     * Sets the value of the salario property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSalario(Double value) {
        this.salario = value;
    }

}
