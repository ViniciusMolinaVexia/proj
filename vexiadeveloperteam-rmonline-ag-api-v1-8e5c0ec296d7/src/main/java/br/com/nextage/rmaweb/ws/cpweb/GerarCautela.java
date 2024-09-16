
package br.com.nextage.rmaweb.ws.cpweb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for gerarCautela complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="gerarCautela">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="listaEquipamento" type="{http://ws.cpweb.nextage.com.br/}sincEquipamentoVo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gerarCautela", propOrder = {
    "listaEquipamento"
})
public class GerarCautela {

    protected List<SincEquipamentoVo> listaEquipamento;

    /**
     * Gets the value of the listaEquipamento property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaEquipamento property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaEquipamento().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SincEquipamentoVo }
     * 
     * 
     */
    public List<SincEquipamentoVo> getListaEquipamento() {
        if (listaEquipamento == null) {
            listaEquipamento = new ArrayList<SincEquipamentoVo>();
        }
        return this.listaEquipamento;
    }

}
