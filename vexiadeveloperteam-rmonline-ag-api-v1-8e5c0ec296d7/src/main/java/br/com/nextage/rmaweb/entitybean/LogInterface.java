package br.com.nextage.rmaweb.entitybean;

import javax.persistence.*;
import java.io.Serializable;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Marlos on 09/05/2016.
 */
@Entity
@Table(name = "TB_LOG_INTERFACE")
public class LogInterface implements Serializable {

    private static final long serialVersionUID = 1L;

    // Constantes com os nomes da classe
    public static final String ALIAS_CLASSE = "logInterface";

    // Constantes com os nomes dos campos.
    public static final String ID = "id";
    public static final String SISTEMA = "sistema";
    public static final String INTERFACE_EXEC = "interfaceExec";
    public static final String NUM_RMA = "numRma";
    public static final String CODIGO_DEPOSITO = "codigoDeposito";
    public static final String ITEM_REQUICISAO_SAP = "itemRequisicaoSap";
    public static final String NUM_REQUISICAO_SAP = "numRequisicaoSap";
    public static final String NUM_PEDIDO_SAP = "numPedidoSap";
    public static final String DATA_HORA_INCLUSAO_LOG = "dataHoraInclusaoLog";
    public static final String USUARIO_INCLUSAO = "usuarioInclusao";
    public static final String JSON = "json";
    public static final String MENSAGEM = "mensagem";
    public static final String TIPO_MENSAGEM = "tipoMensagem";
    public static final String ERRO_MENSAGEM = "erroMensagem";
    public static final String NOME_CLASSE = "nomeClasse";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_LOG_INTERFACE_ID", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "SISTEMA")
    private String sistema;

    @Column(name = "INTERFACE_EXEC")
    private String interfaceExec;

    @Column(name = "NUM_RMA")
    private String numRma;

    @Column(name = "CODIGO_DEPOSITO")
    private String codigoDeposito;

    @Column(name = "ITEM_REQUICISAO_SAP")
    private String itemRequisicaoSap;

    @Column(name = "NUM_REQUISICAO_SAP")
    private String numRequisicaoSap;

    @Column(name = "NUM_PEDIDO_SAP")
    private String numPedidoSap;

    @Column(name = "DATA_HORA_INCLUSAO_LOG")
    private Date dataHoraInclusaoLog;

    @Column(name = "USUARIO_INCLUSAO")
    private String usuarioInclusao;

    @Lob
    @Column(name = "JSON")
    private String json;

    @Column(name = "MENSAGEM")
    private String mensagem;

    @Column(name = "TIPO_MENSAGEM")
    private String tipoMensagem;

    @Lob
    @Column(name = "ERRO_MENSAGEM")
    private String erroMensagem;

    @Lob
    @Column(name = "NOME_CLASSE")
    private String nomeClasse;

    public LogInterface() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public String getInterfaceExec() {
        return interfaceExec;
    }

    public void setInterfaceExec(String interfaceExec) {
        this.interfaceExec = interfaceExec;
    }

    public String getNumRma() {
        return numRma;
    }

    public void setNumRma(String numRma) {
        this.numRma = numRma;
    }

    public String getCodigoDeposito() {
        return codigoDeposito;
    }

    public void setCodigoDeposito(String codigoDeposito) {
        this.codigoDeposito = codigoDeposito;
    }

    public String getItemRequisicaoSap() {
        return itemRequisicaoSap;
    }

    public void setItemRequisicaoSap(String itemRequisicaoSap) {
        this.itemRequisicaoSap = itemRequisicaoSap;
    }

    public String getNumRequisicaoSap() {
        return numRequisicaoSap;
    }

    public void setNumRequisicaoSap(String numRequisicaoSap) {
        this.numRequisicaoSap = numRequisicaoSap;
    }

    public String getNumPedidoSap() {
        return numPedidoSap;
    }

    public void setNumPedidoSap(String numPedidoSap) {
        this.numPedidoSap = numPedidoSap;
    }

    public Date getDataHoraInclusaoLog() {
        return dataHoraInclusaoLog;
    }

    public void setDataHoraInclusaoLog(Date dataHoraInclusaoLog) {
        this.dataHoraInclusaoLog = dataHoraInclusaoLog;
    }

    public String getUsuarioInclusao() {
        return usuarioInclusao;
    }

    public void setUsuarioInclusao(String usuarioInclusao) {
        this.usuarioInclusao = usuarioInclusao;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getErroMensagem() {
        return erroMensagem;
    }

    public void setErroMensagem(String erroMensagem) {
        this.erroMensagem = erroMensagem;
    }

    public String getTipoMensagem() {
        return tipoMensagem;
    }

    public void setTipoMensagem(String tipoMensagem) {
        this.tipoMensagem = tipoMensagem;
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
        if (!(object instanceof LogInterface)) {
            return false;
        }
        LogInterface other = (LogInterface) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.LogInterface[id=" + id + "]";
    }

}

