
package br.com.nextage.rmaweb.ws.cpweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for listarCpNaoPatrimoniados complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listarCpNaoPatrimoniados">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="filtro" type="{http://ws.cpweb.nextage.com.br/}filtroEquipamentoEstoqueVo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listarCpNaoPatrimoniados", propOrder = {
    "filtro"
})
public class ListarCpNaoPatrimoniados {

    protected FiltroEquipamentoEstoqueVo filtro;

    /**
     * Gets the value of the filtro property.
     * 
     * @return
     *     possible object is
     *     {@link FiltroEquipamentoEstoqueVo }
     *     
     */
    public FiltroEquipamentoEstoqueVo getFiltro() {
        return filtro;
    }

    /**
     * Sets the value of the filtro property.
     * 
     * @param value
     *     allowed object is
     *     {@link FiltroEquipamentoEstoqueVo }
     *     
     */
    public void setFiltro(FiltroEquipamentoEstoqueVo value) {
        this.filtro = value;
    }

}
