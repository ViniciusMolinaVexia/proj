/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.entitybean;

import br.com.nextage.entitybean.RoleMaster;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Ferri
 */
@Entity
@Table(name = "TB_ROLE")
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r WHERE r.roleId > 0 ORDER BY r.descricao"),
    @NamedQuery(name = "Role.findByRoleId", query = "SELECT r FROM Role r WHERE r.roleId = :roleId"),
    @NamedQuery(name = "Role.findByNome", query = "SELECT r FROM Role r WHERE r.nome = :nome"),
    @NamedQuery(name = "Role.findByDescricao", query = "SELECT r FROM Role r WHERE r.descricao = :descricao"),
    @NamedQuery(name = "Role.findByAtivo", query = "SELECT r FROM Role r WHERE r.ativo = :ativo")})
public class Role extends RoleMaster implements Serializable {
	
	public static final String ROLE_ADMINISTRADOR="ROLE_ADMINISTRADOR";
	public static final String ROLE_REQUISITANTE="ROLE_REQUISITANTE";
	public static final String ROLE_REQUISITANTE_CORPORATIVO="ROLE_REQUISITANTE_CORPORATIVO";
	public static final String ROLE_APROVADOR="ROLE_APROVADOR";
	public static final String ROLE_APROVADOR_AREA="ROLE_APROVADOR_AREA";
	public static final String ROLE_APROVADOR_GERENTE_AREA="ROLE_APROVADOR_GERENTE_AREA";
	public static final String ROLE_APROVADOR_CUSTO="ROLE_APROVADOR_CUSTO";
	public static final String ROLE_APROVADOR_EMERGENCIAL="ROLE_APROVADOR_EMERGENCIAL";
	public static final String ROLE_COMPRADOR = "ROLE_COMPRADOR";
	public static final String ROLE_ALMOXARIFE = "ROLE_ALMOXARIFE";
	
	@Transient
	private Boolean checked;
	
    public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (getRoleId() != null ? getRoleId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        return !((this.getRoleId() == null && other.getRoleId() != null) || (this.getRoleId() != null && !this.getRoleId().equals(other.getRoleId())));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rhweb.entitybean.Role[roleId=" + getRoleId() + "]";
    }

}
