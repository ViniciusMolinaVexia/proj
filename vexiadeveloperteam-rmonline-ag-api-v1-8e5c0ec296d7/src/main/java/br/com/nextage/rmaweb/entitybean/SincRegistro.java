package br.com.nextage.rmaweb.entitybean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author l.pordeus
 */
@Entity
@Table(name = "TB_SINC_REGISTRO")
public class SincRegistro implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ALIAS_CLASSE = "setor";
    public static final String ALIAS_CLASSE_UNDERLINE = "setor_";

    public static final String ID = "id";
    public static final String FUNCIONALIDADE = "funcionalidade";
    public static final String ID_REGISTRO = "idRegistro";
    public static final String DATA_HORA = "dataHora";
    public static final String OBSERVACAO = "observacao";
    public static final String ERRO = "erro";
    public static final String ATIVO = "ativo";
    public static final String AGRUPADOR_ERRO = "agrupadorErro";
    public static final String QUANTIDADE = "quantidade";
    public static final String SISTEMA = "sistema";
    public static final String RMA_CONCATENA = "rmaConcatena";
    public static final String RM_MATERIAL_CONCATENA = "rmMaterialConcatena";
    public static final String ERRO_MENSAGEM = "erroMensagem";
    public static final String JSON = "json";
    public static final String NOME_CLASSE = "nomeClasse";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_SETOR_ID", allocationSize = 1)
    private Integer id;

    @Column(name = "FUNCIONALIDADE")
    private String funcionalidade;

    @Column(name = "ID_REGISTRO")
    private Integer idRegistro;

    @Column(name = "DATA_HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;

    @Column(name = "OBSERVACAO")
    private String observacao;

    @Column(name = "ERRO")
    @Basic(optional = true)
    private String erro;

    @Column(name = "ATIVO", length = 1)
    private String ativo;

    @Column(name = "AGRUPADOR_ERRO")
    private Integer agrupadorErro;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

    @Column(name = "SISTEMA", length = 100)
    private String sistema;

    @Column(name = "RMA_CONCATENA")
    private String rmaConcatena;

    @Column(name = "RM_MATERIAL_CONCATENA")
    private String rmMaterialConcatena;

    @Lob
    @Column(name = "ERRO_MENSAGEM")
    private String erroMensagem;

    @Lob
    @Column(name = "JSON")
    private String json;

    @Lob
    @Column(name = "NOME_CLASSE")
    private String nomeClasse;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFuncionalidade() {
        return funcionalidade;
    }

    public void setFuncionalidade(String funcionalidade) {
        this.funcionalidade = funcionalidade;
    }

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public Integer getAgrupadorErro() {
        return agrupadorErro;
    }

    public void setAgrupadorErro(Integer agrupadorErro) {
        this.agrupadorErro = agrupadorErro;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public String getRmaConcatena() {
        return rmaConcatena;
    }

    public void setRmaConcatena(String rmaConcatena) {
        this.rmaConcatena = rmaConcatena;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public String getRmMaterialConcatena() {
        return rmMaterialConcatena;
    }

    public void setRmMaterialConcatena(String rmMaterialConcatena) {
        this.rmMaterialConcatena = rmMaterialConcatena;
    }

    public String getErroMensagem() {
        return erroMensagem;
    }

    public void setErroMensagem(String erroMensagem) {
        this.erroMensagem = erroMensagem;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getNomeClasse() {
        return nomeClasse;
    }

    public void setNomeClasse(String nomeClasse) {
        this.nomeClasse = nomeClasse;
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
        if (!(object instanceof SincRegistro)) {
            return false;
        }
        SincRegistro other = (SincRegistro) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.SincRegistro[id=" + id + "]";
    }
}
