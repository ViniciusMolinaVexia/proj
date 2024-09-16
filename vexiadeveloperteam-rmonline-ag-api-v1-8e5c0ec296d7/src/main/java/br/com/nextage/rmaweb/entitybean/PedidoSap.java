/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.entitybean;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Nextage
 */
@Entity
@Table(name = "TB_PEDIDO_SAP", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NUMERO_PEDIDO_COMPRA"})})
@NamedQueries({
    @NamedQuery(name = "PedidoSap.findAll", query = "SELECT p FROM PedidoSap p"),
    @NamedQuery(name = "PedidoSap.findByPedidoSapId", query = "SELECT p FROM PedidoSap p WHERE p.pedidoSapId = :pedidoSapId"),
    @NamedQuery(name = "PedidoSap.findByNumeroPedidoCompra", query = "SELECT p FROM PedidoSap p WHERE p.numeroPedidoCompra = :numeroPedidoCompra"),
    @NamedQuery(name = "PedidoSap.findByDataCompra", query = "SELECT p FROM PedidoSap p WHERE p.dataCompra = :dataCompra")})
public class PedidoSap implements Serializable {

    private static final long serialVersionUID = 1L;
    // Constantes com os nomes da classe
    public static final String ALIAS_CLASSE = "pedidoSap";
    public static final String ALIAS_CLASSE_UNDERLINE = "pedidoSap_";
    // Constantes com os nomes dos campos.
    public static final String PEDIDO_SAP_ID = "pedidoSapId";
    public static final String NUMERO_PEDIDO_COMPRA = "numeroPedidoCompra";
    public static final String DATA_COMPRA = "dataCompra";
    public static final String ENDERECO = "endereco";
    public static final String FORNECEDOR_ID = "endereco";
    public static final String FORNECEDOR = "fornecedor";

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_PEDIDO_SAP_ID", allocationSize = 150)
    @Column(name = "PEDIDO_SAP_ID", nullable = false)
    private Long pedidoSapId;

    @Basic(optional = false)
    @Column(name = "NUMERO_PEDIDO_COMPRA", nullable = false, length = 15)
    private String numeroPedidoCompra;

    @Basic(optional = false)
    @Column(name = "DATA_COMPRA", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataCompra;

    @JoinColumn(name = "FORNECEDOR_ID", referencedColumnName = "FORNECEDOR_ID")
    @ManyToOne
    private Fornecedor fornecedor;

    @Transient
    private DateFormat dFmt = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("pt", "BR"));

    public PedidoSap() {
    }

    public PedidoSap(Long pedidoSapId) {
        this.pedidoSapId = pedidoSapId;
    }

    public PedidoSap(Long pedidoSapId, String numeroPedidoCompra, Date dataCompra) {
        this.pedidoSapId = pedidoSapId;
        this.numeroPedidoCompra = numeroPedidoCompra;
        this.dataCompra = dataCompra;
    }

    public Long getPedidoSapId() {
        return pedidoSapId;
    }

    public void setPedidoSapId(Long pedidoSapId) {
        this.pedidoSapId = pedidoSapId;
    }

    public String getNumeroPedidoCompra() {
        return numeroPedidoCompra;
    }

    public void setNumeroPedidoCompra(String numeroPedidoCompra) {
        this.numeroPedidoCompra = numeroPedidoCompra;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pedidoSapId != null ? pedidoSapId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoSap)) {
            return false;
        }
        PedidoSap other = (PedidoSap) object;
        return !((this.pedidoSapId == null && other.pedidoSapId != null) || (this.pedidoSapId != null && !this.pedidoSapId.equals(other.pedidoSapId)));
    }

    @Override
    public String toString() {
        return String.format("ID: %d | CODFORN: %s | DATE: %s",
                pedidoSapId, fornecedor.getCodigo(),
                dFmt.format(dataCompra));

    }
}
