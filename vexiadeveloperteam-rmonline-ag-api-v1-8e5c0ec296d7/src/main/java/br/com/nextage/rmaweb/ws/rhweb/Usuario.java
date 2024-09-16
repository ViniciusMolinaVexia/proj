
package br.com.nextage.rmaweb.ws.rhweb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for usuario complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="usuario">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.rhweb_ws.nextage.com.br/}usuarioMaster">
 *       &lt;sequence>
 *         &lt;element name="eapsMultiEmpresa" type="{http://ws.rhweb_ws.nextage.com.br/}eapMultiEmpresa" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="etapas" type="{http://ws.rhweb_ws.nextage.com.br/}fcEtapa" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="impPess" type="{http://ws.rhweb_ws.nextage.com.br/}configImpPess" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="modulo" type="{http://ws.rhweb_ws.nextage.com.br/}modulo" minOccurs="0"/>
 *         &lt;element ref="{http://ws.rhweb_ws.nextage.com.br/}pessoa" minOccurs="0"/>
 *         &lt;element name="relatorioPessoaConfigs" type="{http://ws.rhweb_ws.nextage.com.br/}relatorioPessoaConfig" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="roles" type="{http://ws.rhweb_ws.nextage.com.br/}role" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="tiposDocumento" type="{http://ws.rhweb_ws.nextage.com.br/}tipoDocumento" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="userCopy" type="{http://ws.rhweb_ws.nextage.com.br/}usuario" minOccurs="0"/>
 *         &lt;element name="usuarioRoleCollection" type="{http://ws.rhweb_ws.nextage.com.br/}usuarioRole" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "usuario", propOrder = {
    "eapsMultiEmpresa",
    "etapas",
    "impPess",
    "modulo",
    "pessoa",
    "relatorioPessoaConfigs",
    "roles",
    "tiposDocumento",
    "userCopy",
    "usuarioRoleCollection"
})
public class Usuario
    extends UsuarioMaster
{

    @XmlElement(nillable = true)
    protected List<EapMultiEmpresa> eapsMultiEmpresa;
    @XmlElement(nillable = true)
    protected List<FcEtapa> etapas;
    @XmlElement(nillable = true)
    protected List<ConfigImpPess> impPess;
    protected Modulo modulo;
    @XmlElement(namespace = "http://ws.rhweb_ws.nextage.com.br/")
    protected Pessoa pessoa;
    @XmlElement(nillable = true)
    protected List<RelatorioPessoaConfig> relatorioPessoaConfigs;
    @XmlElement(nillable = true)
    protected List<Role> roles;
    @XmlElement(nillable = true)
    protected List<TipoDocumento> tiposDocumento;
    protected Usuario userCopy;
    @XmlElement(nillable = true)
    protected List<UsuarioRole> usuarioRoleCollection;

    /**
     * Gets the value of the eapsMultiEmpresa property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eapsMultiEmpresa property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEapsMultiEmpresa().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EapMultiEmpresa }
     * 
     * 
     */
    public List<EapMultiEmpresa> getEapsMultiEmpresa() {
        if (eapsMultiEmpresa == null) {
            eapsMultiEmpresa = new ArrayList<EapMultiEmpresa>();
        }
        return this.eapsMultiEmpresa;
    }

    /**
     * Gets the value of the etapas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the etapas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEtapas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FcEtapa }
     * 
     * 
     */
    public List<FcEtapa> getEtapas() {
        if (etapas == null) {
            etapas = new ArrayList<FcEtapa>();
        }
        return this.etapas;
    }

    /**
     * Gets the value of the impPess property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the impPess property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImpPess().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConfigImpPess }
     * 
     * 
     */
    public List<ConfigImpPess> getImpPess() {
        if (impPess == null) {
            impPess = new ArrayList<ConfigImpPess>();
        }
        return this.impPess;
    }

    /**
     * Gets the value of the modulo property.
     * 
     * @return
     *     possible object is
     *     {@link Modulo }
     *     
     */
    public Modulo getModulo() {
        return modulo;
    }

    /**
     * Sets the value of the modulo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Modulo }
     *     
     */
    public void setModulo(Modulo value) {
        this.modulo = value;
    }

    /**
     * Gets the value of the pessoa property.
     * 
     * @return
     *     possible object is
     *     {@link Pessoa }
     *     
     */
    public Pessoa getPessoa() {
        return pessoa;
    }

    /**
     * Sets the value of the pessoa property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pessoa }
     *     
     */
    public void setPessoa(Pessoa value) {
        this.pessoa = value;
    }

    /**
     * Gets the value of the relatorioPessoaConfigs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relatorioPessoaConfigs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelatorioPessoaConfigs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RelatorioPessoaConfig }
     * 
     * 
     */
    public List<RelatorioPessoaConfig> getRelatorioPessoaConfigs() {
        if (relatorioPessoaConfigs == null) {
            relatorioPessoaConfigs = new ArrayList<RelatorioPessoaConfig>();
        }
        return this.relatorioPessoaConfigs;
    }

    /**
     * Gets the value of the roles property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roles property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoles().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Role }
     * 
     * 
     */
    public List<Role> getRoles() {
        if (roles == null) {
            roles = new ArrayList<Role>();
        }
        return this.roles;
    }

    /**
     * Gets the value of the tiposDocumento property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tiposDocumento property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTiposDocumento().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TipoDocumento }
     * 
     * 
     */
    public List<TipoDocumento> getTiposDocumento() {
        if (tiposDocumento == null) {
            tiposDocumento = new ArrayList<TipoDocumento>();
        }
        return this.tiposDocumento;
    }

    /**
     * Gets the value of the userCopy property.
     * 
     * @return
     *     possible object is
     *     {@link Usuario }
     *     
     */
    public Usuario getUserCopy() {
        return userCopy;
    }

    /**
     * Sets the value of the userCopy property.
     * 
     * @param value
     *     allowed object is
     *     {@link Usuario }
     *     
     */
    public void setUserCopy(Usuario value) {
        this.userCopy = value;
    }

    /**
     * Gets the value of the usuarioRoleCollection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the usuarioRoleCollection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUsuarioRoleCollection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UsuarioRole }
     * 
     * 
     */
    public List<UsuarioRole> getUsuarioRoleCollection() {
        if (usuarioRoleCollection == null) {
            usuarioRoleCollection = new ArrayList<UsuarioRole>();
        }
        return this.usuarioRoleCollection;
    }

}
