package br.com.nextage.rmaweb.entitybean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="TB_PERFIL_ROLE")
@NamedQueries({
		@NamedQuery(name="PerfilRole.deleteByPerfil", query="DELETE FROM PerfilRole pr where pr.perfil.perfilId = :perfilId "),
		@NamedQuery(name="PerfilRole.getRolesPorPerfilId", query="SELECT pr.role FROM PerfilRole pr WHERE pr.perfil.perfilId = :perfilId "),
		@NamedQuery(name="PerfilRole.getPerfisPorRole", query="SELECT pr.perfil FROM PerfilRole pr WHERE pr.role.nome = :role")
})
public class PerfilRole implements Serializable{

	private static final long serialVersionUID = -7483929675131391309L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PERFIL_ROLE_ID")
	private int perfilRoleId;
	
	@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
    @ManyToOne(optional = false)
    private Role role;

    @JoinColumn(name = "PERFIL_ID", referencedColumnName = "PERFIL_ID")
    @ManyToOne(optional = false)
    private Perfil perfil;

	public int getPerfilRoleId() {
		return perfilRoleId;
	}

	public void setPerfilRoleId(int perfilRoleId) {
		this.perfilRoleId = perfilRoleId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
		result = prime * result + perfilRoleId;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerfilRole other = (PerfilRole) obj;
		if (perfil == null) {
			if (other.perfil != null)
				return false;
		} else if (!perfil.equals(other.perfil))
			return false;
		if (perfilRoleId != other.perfilRoleId)
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PerfilRole [perfilRoleId=" + perfilRoleId + ", role=" + role + ", perfil=" + perfil + "]";
	}

}
