
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for listarPessoasRmaweb complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listarPessoasRmaweb">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="filtroImportacaoBloco" type="{http://ws.rhweb_ws.nextage.com.br/}filtroImportacaoBloco" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listarPessoasRmaweb", propOrder = {
    "filtroImportacaoBloco"
})
public class ListarPessoasRmaweb {

    protected FiltroImportacaoBloco filtroImportacaoBloco;

    /**
     * Gets the value of the filtroImportacaoBloco property.
     * 
     * @return
     *     possible object is
     *     {@link FiltroImportacaoBloco }
     *     
     */
    public FiltroImportacaoBloco getFiltroImportacaoBloco() {
        return filtroImportacaoBloco;
    }

    /**
     * Sets the value of the filtroImportacaoBloco property.
     * 
     * @param value
     *     allowed object is
     *     {@link FiltroImportacaoBloco }
     *     
     */
    public void setFiltroImportacaoBloco(FiltroImportacaoBloco value) {
        this.filtroImportacaoBloco = value;
    }

}
