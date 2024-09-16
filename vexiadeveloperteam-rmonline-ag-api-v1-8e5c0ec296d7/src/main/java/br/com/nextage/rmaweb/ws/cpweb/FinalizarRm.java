
package br.com.nextage.rmaweb.ws.cpweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for finalizarRm complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="finalizarRm">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="materialDepositoVo" type="{http://ws.rmaweb.nextage.com.br/}materialDepositoVo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "finalizarRm", propOrder = {
        "materialDepositoVo"
})
public class FinalizarRm {

    protected MaterialDepositoVo materialDepositoVo;

    /**
     * Gets the value of the materialDepositoVo property.
     *
     * @return possible object is
     * {@link MaterialDepositoVo }
     */
    public MaterialDepositoVo getMaterialDepositoVo() {
        return materialDepositoVo;
    }

    /**
     * Sets the value of the materialDepositoVo property.
     *
     * @param value allowed object is
     *              {@link MaterialDepositoVo }
     */
    public void setMaterialDepositoVo(MaterialDepositoVo value) {
        this.materialDepositoVo = value;
    }

}
