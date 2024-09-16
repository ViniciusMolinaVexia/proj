
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for filtroImportacaoBloco complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="filtroImportacaoBloco">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="qtdeBloco" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ultimoId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "filtroImportacaoBloco", propOrder = {
    "qtdeBloco",
    "ultimoId"
})
public class FiltroImportacaoBloco {

    protected int qtdeBloco;
    protected Integer ultimoId;

    /**
     * Gets the value of the qtdeBloco property.
     * 
     */
    public int getQtdeBloco() {
        return qtdeBloco;
    }

    /**
     * Sets the value of the qtdeBloco property.
     * 
     */
    public void setQtdeBloco(int value) {
        this.qtdeBloco = value;
    }

    /**
     * Gets the value of the ultimoId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUltimoId() {
        return ultimoId;
    }

    /**
     * Sets the value of the ultimoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUltimoId(Integer value) {
        this.ultimoId = value;
    }

}
