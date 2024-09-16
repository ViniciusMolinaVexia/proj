
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for clinica complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="clinica">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clinicaId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="endereco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imgLogo" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="imgMapa" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="logo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mapa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telefone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "clinica", propOrder = {
    "clinicaId",
    "endereco",
    "imgLogo",
    "imgMapa",
    "logo",
    "mapa",
    "nome",
    "telefone"
})
public class Clinica {

    protected Integer clinicaId;
    protected String endereco;
    protected byte[] imgLogo;
    protected byte[] imgMapa;
    protected String logo;
    protected String mapa;
    protected String nome;
    protected String telefone;

    /**
     * Gets the value of the clinicaId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getClinicaId() {
        return clinicaId;
    }

    /**
     * Sets the value of the clinicaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setClinicaId(Integer value) {
        this.clinicaId = value;
    }

    /**
     * Gets the value of the endereco property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Sets the value of the endereco property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndereco(String value) {
        this.endereco = value;
    }

    /**
     * Gets the value of the imgLogo property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getImgLogo() {
        return imgLogo;
    }

    /**
     * Sets the value of the imgLogo property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setImgLogo(byte[] value) {
        this.imgLogo = value;
    }

    /**
     * Gets the value of the imgMapa property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getImgMapa() {
        return imgMapa;
    }

    /**
     * Sets the value of the imgMapa property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setImgMapa(byte[] value) {
        this.imgMapa = value;
    }

    /**
     * Gets the value of the logo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogo() {
        return logo;
    }

    /**
     * Sets the value of the logo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogo(String value) {
        this.logo = value;
    }

    /**
     * Gets the value of the mapa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMapa() {
        return mapa;
    }

    /**
     * Sets the value of the mapa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMapa(String value) {
        this.mapa = value;
    }

    /**
     * Gets the value of the nome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the value of the nome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Gets the value of the telefone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Sets the value of the telefone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefone(String value) {
        this.telefone = value;
    }

}
