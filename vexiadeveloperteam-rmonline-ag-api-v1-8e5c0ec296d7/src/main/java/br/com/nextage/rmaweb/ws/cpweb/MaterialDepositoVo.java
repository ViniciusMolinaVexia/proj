
package br.com.nextage.rmaweb.ws.cpweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for materialDepositoVo complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="materialDepositoVo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigoRequisicao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deposito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificadorRmMaterial" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="material" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="origemMovimentacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="origemSincRegistro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qtdeInserida" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="reaproveitadosEpi" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="retiradoPor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "materialDepositoVo", propOrder = {
        "codigoRequisicao",
        "deposito",
        "identificadorRmMaterial",
        "material",
        "origemMovimentacao",
        "origemSincRegistro",
        "qtdeInserida",
        "reaproveitadosEpi",
        "retiradoPor"
})
public class MaterialDepositoVo {

    protected String codigoRequisicao;
    protected String deposito;
    protected Integer identificadorRmMaterial;
    protected String material;
    protected String origemMovimentacao;
    protected String origemSincRegistro;
    protected Double qtdeInserida;
    protected Boolean reaproveitadosEpi;
    protected String retiradoPor;

    /**
     * Gets the value of the codigoRequisicao property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCodigoRequisicao() {
        return codigoRequisicao;
    }

    /**
     * Sets the value of the codigoRequisicao property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCodigoRequisicao(String value) {
        this.codigoRequisicao = value;
    }

    /**
     * Gets the value of the deposito property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDeposito() {
        return deposito;
    }

    /**
     * Sets the value of the deposito property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDeposito(String value) {
        this.deposito = value;
    }

    /**
     * Gets the value of the identificadorRmMaterial property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getIdentificadorRmMaterial() {
        return identificadorRmMaterial;
    }

    /**
     * Sets the value of the identificadorRmMaterial property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setIdentificadorRmMaterial(Integer value) {
        this.identificadorRmMaterial = value;
    }

    /**
     * Gets the value of the material property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Sets the value of the material property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMaterial(String value) {
        this.material = value;
    }

    /**
     * Gets the value of the origemMovimentacao property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getOrigemMovimentacao() {
        return origemMovimentacao;
    }

    /**
     * Sets the value of the origemMovimentacao property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setOrigemMovimentacao(String value) {
        this.origemMovimentacao = value;
    }

    /**
     * Gets the value of the origemSincRegistro property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getOrigemSincRegistro() {
        return origemSincRegistro;
    }

    /**
     * Sets the value of the origemSincRegistro property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setOrigemSincRegistro(String value) {
        this.origemSincRegistro = value;
    }

    /**
     * Gets the value of the qtdeInserida property.
     *
     * @return possible object is
     * {@link Double }
     */
    public Double getQtdeInserida() {
        return qtdeInserida;
    }

    /**
     * Sets the value of the qtdeInserida property.
     *
     * @param value allowed object is
     *              {@link Double }
     */
    public void setQtdeInserida(Double value) {
        this.qtdeInserida = value;
    }

    /**
     * Gets the value of the reaproveitadosEpi property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isReaproveitadosEpi() {
        return reaproveitadosEpi;
    }

    /**
     * Sets the value of the reaproveitadosEpi property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setReaproveitadosEpi(Boolean value) {
        this.reaproveitadosEpi = value;
    }

    /**
     * Gets the value of the retiradoPor property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getRetiradoPor() {
        return retiradoPor;
    }

    /**
     * Sets the value of the retiradoPor property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRetiradoPor(String value) {
        this.retiradoPor = value;
    }

}
