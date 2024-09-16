package br.com.nextage.rmaweb.entitybean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VW_REQUISICAO_MATERIAL")
public class VWRequisicaoMateriail {
	
	@Id
    @Column(name = "ID")
    private Integer id;

}
