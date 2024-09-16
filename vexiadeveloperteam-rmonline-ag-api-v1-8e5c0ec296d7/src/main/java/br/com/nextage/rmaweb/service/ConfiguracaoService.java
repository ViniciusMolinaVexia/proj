package br.com.nextage.rmaweb.service;

import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.Configuracao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel H Parisotto
 */
public class ConfiguracaoService {

    public ConfiguracaoService() {
    }

    public Configuracao getConfiguracao() {
        Configuracao conf = null;
        List<Propriedade> prop = new ArrayList<>();
        GenericDao<Configuracao> gd = new GenericDao<>();

        NxCriterion nxCriterion = null;

        try {

            prop.add(new Propriedade(Configuracao.CONFIGURACAO_ID));
            prop.add(new Propriedade(Configuracao.EMPRESA));
            prop.add(new Propriedade(Configuracao.EMAIL_HOST));
            prop.add(new Propriedade(Configuracao.EMAIL_PORTA));
            prop.add(new Propriedade(Configuracao.EMAIL_ORIGEM));
            prop.add(new Propriedade(Configuracao.EMAIL_USER));
            prop.add(new Propriedade(Configuracao.EMAIL_PASSWD));
            prop.add(new Propriedade(Configuracao.EMAIL_CHAVE));
            prop.add(new Propriedade(Configuracao.CAMINHO_URL_RHWEB));
            prop.add(new Propriedade(Configuracao.PREF_URL_EXTERNA_SIST));
            prop.add(new Propriedade(Configuracao.FILTRO_PAINEL_SUPRI_APROVADA));
            prop.add(new Propriedade(Configuracao.CAMINHO_URL_CPWEB));
            prop.add(new Propriedade(Configuracao.IDS_FUNCAO_APROVADOR_CUSTOS));
            prop.add(new Propriedade(Configuracao.IDS_FUNCAO_ENCARREGADO));
            prop.add(new Propriedade(Configuracao.IDS_FUNCAO_GERENTE_AREA));
            prop.add(new Propriedade(Configuracao.IDS_FUNCAO_GERENTE_OBRA));
            prop.add(new Propriedade(Configuracao.CENTRO_COD));
            prop.add(new Propriedade(Configuracao.DESABILITA_CAD_FORNECEDOR));
            prop.add(new Propriedade(Configuracao.DESABILITA_CAD_MATERIAL));
            prop.add(new Propriedade(Configuracao.DIAS_ALERTA_PREVISAO));
            prop.add(new Propriedade(Configuracao.MODO_AUTENTICACAO));
            prop.add(new Propriedade(Configuracao.QUANT_CASAS_COD_SAP));
            prop.add(new Propriedade(Configuracao.CAMINHO_REAL_INSTALACAO));
            prop.add(new Propriedade(Configuracao.CAMINHO_PATH_IMAGEM_REL));
            prop.add(new Propriedade(Configuracao.NOME));
            prop.add(new Propriedade(Configuracao.CODIGO_EPI));
            prop.add(new Propriedade(Configuracao.COLETOR_CUSTO_EPI));
            prop.add(new Propriedade(Configuracao.DEPOSITO_PADRAO));
            prop.add(new Propriedade(Configuracao.ENCAR_SOLICITA_RMA_MOBILE));
            prop.add(new Propriedade(Configuracao.HABILITA_EAP_MULTI_EMPRESA));

            conf = gd.selectUnique(prop, Configuracao.class, nxCriterion);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conf;
    }
}
