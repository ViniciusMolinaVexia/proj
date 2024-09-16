package br.com.nextage.rmaweb.entitybean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author nextage
 */
@Entity
@Table(name = "TB_RM_APROVADOR")
public class RmAprovador implements Serializable {

    private static final long serialVersionUID = 1L;

    // Constantes com os nomes da classe
    public static final String ALIAS_CLASSE = "rmAprovador";

    public static final String ID = "id";
    public static final String DATA_AVALIACAO = "dataAvaliacao";
    public static final String HORA_AVALIACAO = "horaAvaliacao";
    public static final String APROVADA = "aprovada";
    public static final String OBSERVACAO = "observacao";
    public static final String RM_ID = "rm";
    public static final String APROVADOR_PESSOA_ID = "aprovador";
    public static final String TIPO_APROVADOR = "tipoAprovador";
    public static final String ORDEM = "ordem";
    public static final String APROVADOR_VEZ = "aprovadorVez";
    public static final String APROVADOR_AREA = "usuarioAprovadorArea";
    public static final String APROVADOR_GERENTE_AREA = "usuarioAprovadorGerenteArea";
    public static final String APROVADOR_CUSTO = "usuarioAprovadorCusto";
    public static final String APROVADOR_EMERGENCIAL = "usuarioAprovadorEmergencial";
    public static final String NIVEL_APROVADOR = "nivelAprovador";

    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_RM_APROVADOR_ID", allocationSize = 1)
    private Integer id;

    @Column(name = "DATA_AVALIACAO")
    @Temporal(TemporalType.DATE)
    private Date dataAvaliacao;

    @Column(name = "HORA_AVALIACAO")
    @Temporal(TemporalType.TIME)
    private Date horaAvaliacao;

    @Column(name = "APROVADA")
    private Boolean aprovada;

    @Column(name = "OBSERVACAO", length = 500)
    private String observacao;

    @JoinColumn(name = "RM_ID", referencedColumnName = "RM_ID")
    @ManyToOne(optional = false)
    private Rm rm;

    @JoinColumn(name = "APROVADOR_PESSOA_ID", referencedColumnName = "PESSOA_ID")
    @ManyToOne
    private Pessoa aprovador;

    @Column(name = "TIPO_APROVADOR", length = 10)
    private String tipoAprovador;

    @Column(name = "APROVADOR_VEZ")
    private Boolean aprovadorVez;

    @Column(name = "ORDEM")
    private Integer ordem;
    
    @Column(name="NIVEL_APROVADOR")
    private String nivelAprovador;
    
    @JoinColumn(name = "APROVADOR_AREA_ID", referencedColumnName = "USUARIO_ID")
    @ManyToOne
    private Usuario usuarioAprovadorArea;
    
    @JoinColumn(name = "APROVADOR_GERENTE_AREA_ID", referencedColumnName = "USUARIO_ID")
    @ManyToOne
    private Usuario usuarioAprovadorGerenteArea;
    
    @JoinColumn(name = "APROVADOR_CUSTO_ID", referencedColumnName = "USUARIO_ID")
    @ManyToOne
    private Usuario usuarioAprovadorCusto;
    
    @JoinColumn(name = "APROVADOR_EMERGENCIAL_ID", referencedColumnName = "USUARIO_ID")
    @ManyToOne
    private Usuario usuarioAprovadorEmergencial;
    
    public String getNivelAprovador() {
    	return this.nivelAprovador;
    }
    
    public void setNivelAprovador(String nivelAprovador) {
    	this.nivelAprovador=nivelAprovador;
    }
    
    public Usuario getUsuarioAprovadorArea() {
		return usuarioAprovadorArea;
	}

	public void setUsuarioAprovadorArea(Usuario usuarioAprovadorArea) {
		this.usuarioAprovadorArea = usuarioAprovadorArea;
	}

	public Usuario getUsuarioAprovadorGerenteArea() {
		return usuarioAprovadorGerenteArea;
	}

	public void setUsuarioAprovadorGerenteArea(Usuario usuarioAprovadorGerenteArea) {
		this.usuarioAprovadorGerenteArea = usuarioAprovadorGerenteArea;
	}

	public Usuario getUsuarioAprovadorCusto() {
		return usuarioAprovadorCusto;
	}

	public void setUsuarioAprovadorCusto(Usuario usuarioAprovadorCusto) {
		this.usuarioAprovadorCusto = usuarioAprovadorCusto;
	}

	public Usuario getUsuarioAprovadorEmergencial() {
		return usuarioAprovadorEmergencial;
	}

	public void setUsuarioAprovadorEmergencial(Usuario usuarioAprovadorEmergencial) {
		this.usuarioAprovadorEmergencial = usuarioAprovadorEmergencial;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public Date getHoraAvaliacao() {
        return horaAvaliacao;
    }

    public void setHoraAvaliacao(Date horaAvaliacao) {
        this.horaAvaliacao = horaAvaliacao;
    }

    public Boolean getAprovada() {
        return aprovada;
    }

    public void setAprovada(Boolean aprovada) {
        this.aprovada = aprovada;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Rm getRm() {
        return rm;
    }

    public void setRm(Rm rm) {
        this.rm = rm;
    }

    public Pessoa getAprovador() {
        return aprovador;
    }

    public void setAprovador(Pessoa aprovador) {
        this.aprovador = aprovador;
    }

    public String getTipoAprovador() {
        return tipoAprovador;
    }

    public void setTipoAprovador(String tipoAprovador) {
        this.tipoAprovador = tipoAprovador;
    }

    public Boolean getAprovadorVez() {
        return aprovadorVez;
    }

    public void setAprovadorVez(Boolean aprovadorVez) {
        this.aprovadorVez = aprovadorVez;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RmAprovador)) {
            return false;
        }
        RmAprovador other = (RmAprovador) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.RmAprovador[id=" + id + "]";
    }
}
