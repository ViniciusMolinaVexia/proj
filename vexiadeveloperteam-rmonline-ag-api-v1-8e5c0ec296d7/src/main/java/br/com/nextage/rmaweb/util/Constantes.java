/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.util;

/**
 *
 * @author marcelo
 */
public class Constantes {

    public static final String NOME_PROJETO = "RMAWEB";
    /**
     * Sucesso / Falha
     *
     */
    public static final String SUCESSO = "sucesso";
    public static final String FALHA = "falha";

    /**
     * Quantidade de registros a serem retornados nas consultas das telas de
     * "Listas".
     */
    public static final int QTD_REGISTROS = 50;
    public static final int NO_LIMIT = -1;

    public static final String VAR_AMBIENTE = "ambiente";

    public static final String URL_IMAGES = "URL_IMAGES";
    public static final String SUBREPORT_DIR = "SUBREPORT_DIR";

    //UTILIZADO PARA A SESSION
    public static String USUARIOLOGADO = "UsuarioLogado";
    public static String USUARIOLOGADO_FLEX = "usuarioLogadoFlex";
    public static String USERNAME = "userName";
    public static String LOCALE = "locale";
    public static String ORIGIN = "origin";
    public static String REFERER = "referer";
    public static String HOST = "host";

    public static final String SISTEMA = "sistema";

    public static final String SIM_ABREVIADO = "S";
    public static final String NAO_ABREVIADO = "N";

    public static final String ATIVO_ABREVIADO = "A";
    public static final String INATIVO_ABREVIADO = "I";

    /**
     * Relatorios.
     */
    public static final String RELATORIO_TESTE = "ReportTeste.jasper";
    public static final String RELATORIO_CONTROLE_RM = "ControleRm";
    public static final String RELATORIO_PROTOCOLO_RETIRADA_MATERIAL = "ProtocoloRetiradaMaterial.jasper";
    public static final String RELATORIO_PROTOCOLO_SAIDA_MATERIAL = "ProtocoloSaidaMaterial.jasper";

    public static final String DATA_COMPRA_RM_MATERIAL_SIM = "Finalizado";
    public static final String DATA_COMPRA_RM_MATERIAL_NAO = "Ativo";

    //Historico de Entrada de Material no Deposito
    public static final String ENTRADA_DE_MATERIAL_POR_RM = "MATERIAL ADICIONADO POR RM";
    public static final String ENTRADA_DE_MATERIAL_POR_DEV_CAUTELA = "MATERIAL ADICIONADO POR DEVOLUÇÃO NA CAUTELA";
    public static final String ENTRADA_DE_MATERIAL_POR_MOVIMENTACAO = "MATERIAL ADICIONADO POR MOVIMENTACÃO ENTRE DEPÓSITOS";
    public static final String EXCLUIDO_ENVIO_SAP_COM_ERRO_RETIRADA = "ERRO NO ENVIO DE RETIRADA, EXCLUÍDO POR";

    //Historico de Saida de Material no Deposito
    public static final String SAIDA_DE_MATERIAL_POR_RM = "MATERIAL RETIRADO POR RM";
    public static final String SAIDA_DE_MATERIAL_POR_CAUTELA = "MATERIAL RETIRADO POR CAUTELA";
    public static final String SAIDA_DE_MATERIAL_POR_MOVIMENTACAO = "MATERIAL RETIRADO POR MOVIMENTACÃO ENTRE DEPÓSITOS";

    public static final String MSG_USUARIO_NAO_ENCONTRADO_I18N = "msg_usuario_senha_invalidos";
    public static final String MSG_USUARIO_SENHA_INVALIDOS_I18N = "msg_usuario_senha_invalidos";
    public static final String MSG_USUARIO_NAO_POSSUI_PESSOA_I18N = "O usuário informado não possui pessoa.";

    public static final String REGS_SALVOS_SUCESSO_I18N = "msg_registros_salvo_sucesso";
    public static final String REG_EXCLUSAO_ERRO_I18N = "msg_registro_exclusao_erro";
    public static final String REG_SALVO_ERRO_I18N = "msg_registro_salvo_erro";
    public static final String REG_EXCLUSAO_SUCESSO_I18N = "msg_registro_exclusao_sucesso";
    public static final String REG_SALVO_SUCESSO_I18N = "msg_registro_salvo_sucesso";
    public static final String SUCESSO_OPERACAO_COM_EMAIL_I18N = "msg_operacao_realizada_sucesso_com_email";
    public static final String SUCESSO_OPERACAO_SEM_EMAIL_I18N = "msg_operacao_realizada_sucesso_sem_email";
    public static final String ERRO_OPERACAO_I18N = "msg_erro_realizar_operacao";
    public static final String SUCESSO_OPERACAO_I18N = "msg_operacao_realizada_sucesso";
    public static final String MSG_REQUISICAO_APROVADA_SUCESSO_I18N = "msg_requisicao_aprovada_sucesso";
    public static final String MSG_REQUISICAO_APROVADA_ERRO_I18N = "msg_requisicao_aprovada_erro";
    public static final String MSG_REQUISICAO_REPROVADA_SUCESSO_I18N = "msg_requisicao_reprovada_sucesso";
    public static final String MSG_REQUISICAO_REPROVADA_ERRO_I18N = "msg_requisicao_reprovada_erro";
    public static final String MSG_REQUISICAO_CANCELADA_SUCESSO_I18N = "msg_requisicao_cancelada_sucesso";
    public static final String MSG_REQUISICAO_CANCELADA_ERRO_I18N = "msg_requisicao_cancelada_erro";
    public static final String MSG_MATERIAL_CANCELADO_SUCESSO_I18N = "msg_material_cancelado_sucesso";
    public static final String MSG_MATERIAL_CANCELADO_ERRO_I18N = "msg_material_cancelado_erro";
    public static final String MSG_REQUISICAO_ENVIADO_APROVACAO_SUCESSO_I18N = "msg_requisicao_enviada_aprovacao_sucesso";
    public static final String MSG_REQUISICAO_NAO_CANCELADA_I18N = "msg_requisicao_nao_cancelada";
    public static final String MSG_MATERIAL_NAO_CANCELADO_I18N = "msg_material_nao_cancelado";
    public static final String MSG_MATERIAL_JA_CANCELADO_I18N = "msg_material_ja_cancelado";
    public static final String ERRO_SEM_APROVADOR = "erro_sem_aprovador";

    public static final String MSG_PREENCHER_NOME_MATERIAL = "msg_preencher_nome_material";
    public static final String MSG_PREENCHER_UNIDADE_MEDIDA = "msg_preencher_unidade_medida";

    public static final String ERRO_COMUNICACAO_SAP_CONTATE_ADMINISTRADOR_I18N = "msg_erro_comunicacao_com_sap_contate_administrador";
    public static final String ERRO_COMUNICACAO_CPWEB_CONTATE_ADMINISTRADOR_I18N = "msg_erro_comunicacao_com_cpweb_contate_administrador";

    public static final String TIPO_ATUACAO_GERENTE_AREA = "GA";
    public static final String TIPO_ATUACAO_GERENTE_CUSTO = "GC";
    public static final String TIPO_ATUACAO_CUSTOS = "C";
    public static final String TIPO_ATUACAO_LIDER_CUSTOS = "LC";
    public static final String TIPO_ATUACAO_ENCARREGADO = "E";
    public static final String TIPO_ATUACAO_GERENTE_OBRA = "GO";
    public static final String TIPO_ATUACAO_COORDENADOR = "CO";
    public static final String TIPO_ATUACAO_RESP_RET_ESTOQ = "RRE";

    public static final String TIPO_REQUISICAO_MATERIAIS = "TIP_REQ_MAT";
    public static final String TIPO_REQUISICAO_MANUTENCAO = "TIP_REQ_MANUTENCAO";
    public static final String TIPO_REQUISICAO_FRENTE_SERVICO = "TIP_REQ_FRE_SER";
    public static final String TIPO_REQUISICAO_RETIRADA_ESTOQUE = "TIP_RET_EM_ESTOQ";
    public static final String TIPO_REQUISICAO_RETIRADA_ESTOQUE_ESTOQUISTA = "TIP_RET_ESTOQUISTA";

    public static final String ROLE_CUSTOS = "RMA_CUSTOS";
    public static final String ROLE_MATERIAIS_SEM_CODIGO_SAP = "MATERIAIS_SEM_CODIGO_SAP";
    public static final String STATUS_RM_MATERIAL_CADASTRADA = "Cad";

    public static final String ROLE_LIDER_CUSTOS = "ROLE_LIDER_CUSTOS";
    public static final String STATUS_RM_MATERIAL_ENVIADA_APROVACAO = "Env";
    public static final String STATUS_RM_MATERIAL_APROVADA_CO = "AprCo";
    public static final String STATUS_RM_MATERIAL_APROVADA_GA = "AprGa";
    public static final String STATUS_RM_MATERIAL_APROVADA_A = "AprA";
    public static final String STATUS_RM_MATERIAL_APROVADA_E = "AprE";
    public static final String STATUS_RM_MATERIAL_APROVADA_GC = "AprGc";
    public static final String STATUS_RM_MATERIAL_APROVADA_GO = "AprGo";
    public static final String STATUS_RM_MATERIAL_APROVADA_RRE = "AprRre";
    public static final String STATUS_RM_MATERIAL_COMPLEMENTO_CUSTOS = "CompCust";
    public static final String STATUS_RM_MATERIAL_REPROVADA = "Rep";
    public static final String STATUS_RM_MATERIAL_CANCELADA = "Can";
    public static final String STATUS_RM_MATERIAL_RESERVA_MATERIAL = "Reserva";
    public static final String STATUS_RM_MATERIAL_AGUARDANDO_COMPRA = "AgComp";
    public static final String STATUS_RM_MATERIAL_AGUARDANDO_ESTOQUE = "AgEst";
    public static final String STATUS_RM_MATERIAL_AGUARDANDO_RETIRADA = "AgRet";
    public static final String STATUS_RM_MATERIAL_RECUSADO_ESTOQUISTA = "RecEst";
    public static final String STATUS_RM_MATERIAL_ESTORNO_RECEBIMENTO = "MatEstornoRec";
    public static final String STATUS_RM_MATERIAL_COMPRADO_AGUARDANDO_RECEBIMENTO = "MatComAgRec";
    public static final String STATUS_RM_MATERIAL_RETIRADO_PARCIALMENTE = "RetParc";
    public static final String STATUS_RM_MATERIAL_RECEBIDO_PARCIALMENTE = "RecParc";
    public static final String STATUS_RM_MATERIAL_RECEBIDO = "Rec";
    public static final String STATUS_RM_MATERIAL_FINALIZADO = "Fim";
    public static final String STATUS_RM_MATERIAL_INDISPONIVEL = "Indisp";
    public static final String STATUS_RM_MATERIAL_SOLICITAR_TRANSFERENCIA = "SolicTransf";
    public static final String STATUS_RM_MATERIAL_MATERIAL_ALTERADO = "MatAlt";
    public static final String STATUS_APROVADO = "Apr";
    
    public static final String STATUS_ENVIADO_SAP = "EnvSAP";

    public static final String STATUS_INDISPONIVEL = "I";

    //FUNCIONALIDADE
    public static final String SINC_EQUIPAMENTO = "SincEquipamento";
    public static final String SINC_TRANF_EQUIPAMENTO = "SincTransfEquipamento";
    public static final String SINC_GERA_CAUTELA = "SincGeraCautela";
    public static final String SINC_RESERVA = "SincReserva";

    //TIPO MATERIAL
    public static final String CAUTELAVEL = "C";
    public static final String NAO_CAUTELAVEL = "NC";

    //FLUXO_MATERIAL
    public static final String FLUXO_MATERIAL_COMPRA = "Compra";
    public static final String FLUXO_MATERIAL_RESERVA = "Reserva";
    public static final String FLUXO_MATERIAL_CAMPO = "Campo";

    public static final String CODIGO_INTEGRACAO_FORNECEDOR = "INTEGRACAO_FORNECEDOR";
    public static final String CODIGO_INTEGRACAO_MATERIAL = "INTEGRACAO_MATERIAL";
    public static final String CODIGO_INTEGRACAO_COMPRA = "INTEGRACAO_COMPRA";
    public static final String CODIGO_INTEGRACAO_RECEBER_ENTRADA_MATERIAL = "INTEGRACAO_RECEBER_ENTRADA";
    public static final String CODIGO_INTEGRACAO_ESTOQUE = "INTEGRACAO_ESTOQUE";
    public static final String CODIGO_INTEGRACAO_ESTORNO = "INTEGRACAO_ESTORNO";
    public static final String CODIGO_INTEGRACAO_RM = "INTEGRACAO_RM";
    public static final String CODIGO_INTEGRACAO_RESERVA_MATERIAL = "INTEGRACAO_RESERVA_MATERIAL";
    public static final String CODIGO_INTEGRACAO_COMPRA_MATERIAL = "INTEGRACAO_COMPRA_MATERIAL";
    public static final String CODIGO_INTEGRACAO_RETIRADA_MATERIAL = "INTEGRACAO_RETIRADA_MATERIAL";
    public static final String CODIGO_INTEGRACAO_CONSULTAR_ESTOQUE = "INTEGRACAO_CONSULTAR_ESTOQUE";
    public static final String CODIGO_INTEGRACAO_DEPOSITO = "INTEGRACAO_DEPOSITO";

    public static final String SIGLA_COM_CODIGO = "C";

    public static final String STATUS_MATERIAL_RESERVA_APROVADO = "A";
    public static final String STATUS_MATERIAL_RESERVA_REPROVADO = "R";

    public static final String STATUS_MATERIAL_ATIVO = "A";

    public static final String TIPO_MENSAGEM_ERRO = "E";
    public static final String TIPO_MENSAGEM_SUCESSO = "S";

    //TIPO PENDENCIA
    public static final String TIPO_PENDENCIA_ATRASADA = "ATR";
    public static final String TIPO_PENDENCIA_NO_PRAZO = "NPR";

    public static final String CODIGO_PRIORIDADE_ALTA = "ALTA";
    public static final String CODIGO_PRIORIDADE_MEDIA = "MEDIA";
    public static final String CODIGO_PRIORIDADE_BAIXA = "BAIXA";

    //TIPO REQUISICAO
    public static final String CODIGO_TIPO_REQUISICAO_FRENTE_SERVICO = "TIP_REQ_FRE_SER";

    public static final String SESSAO_EXPIRADA_I18N = "label_sessao_expirada";

    public static final String ERRO_BAIXA_MATERIAL = "Erro ao realizar baixa!";

    //ANEXO
    public static final String ESCOPO_ANEXO_RM = "RM";
    public static final String ESCOPO_ANEXO_RM_MATERIAL = "RM_MATERIAL";

    //SISTEMAS
    public static final String SISTEMA_CPWEB = "CPWEB";
    public static final String SISTEMA_SAP = "SAP";
    public static final String SISTEMA_RMAWEB = "RMAWEB";

    //Interfaces
    public static final String INTERFACE_ESTOQUE = "ESTOQUE";
    public static final String INTERFACE_REQUISICAO_COMPRA = "REQUISIÇÃO DE COMPRA";
    public static final String INTERFACE_REQUISICAO_COMPRA_REQUEST = "REQUISIÇÃO DE COMPRA REQUEST";
    public static final String INTERFACE_REQUISICAO_COMPRA_RESPONSE = "REQUISIÇÃO DE COMPRA RESPONSE";
    public static final String INTERFACE_REQUISICAO_RESERVA_REQUEST = "REQUISIÇÃO DE RESERVA REQUEST";
    public static final String INTERFACE_REQUISICAO_RESERVA_RESPONSE = "REQUISIÇÃO DE RESERVA RESPONSE";
    public static final String INTERFACE_RETIRADA_MATERIAIS = "RETIRADA DE MATERIAIS";
    public static final String INTERFACE_ESTORNO = "ESTORNO";
    public static final String INTERFACE_COMPRA = "COMPRA";
    public static final String INTERFACE_RECEBER_ENTRADA = "RECEBER ENTRADA MATERIAL";
    public static final String INTERFACE_ENTRADA_MATERIAL = "ENTRADA DE MATERIAL";
    public static final String INTERFACE_SAIDA_MATERIAL = "SAÍDA DE MATERIAL";
    public static final String INTERFACE_ENVIAR_RESERVA = "ENVIAR RESERVA";
    public static final String INTERFACE_ENVIAR_MATERIAL = "ENVIAR MATERIAL";
    public static final String INTERFACE_TRANSFERIR_MATERIAL = "TRANSFERIR MATERIAL";
    public static final String INTERFACE_FINALIZA_FRENTE = "FINALIZA FRENTE";
    public static final String INTERFACE_ENVIAR_CAUTELA = "ENVIAR CAUTELA";

    public static final String ENTRADA_ESTOQUE = "Entrada";
    public static final String SAIDA_ESTOQUE = "Saida";

    public static final String ESTORNO = "ESTORNO";
    public static final String SAIDA_ESTOQUE_RMA = "SAIDA DE ESTOQUE POR RMA";

    public static final String EQUIPAMENTO = "E";
    public static final String EPI = "EPI";
    public static final String MC = "MC";

    public static final String SINCRONIZACAO_EFETIVO = "SINCRONIZAÇÃO DE EFETIVOS COM O RHWEB";

}
