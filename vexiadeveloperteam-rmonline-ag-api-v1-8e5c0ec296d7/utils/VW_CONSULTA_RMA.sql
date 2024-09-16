CREATE VIEW [dbo].[VW_CONSULTA_RMA]
AS			   
SELECT RMMS.ID                                        						 AS ID,
       RM.RM_ID                                                              AS RM_RM_ID,
       RM.TIPO_RM							     							 AS TIPO_RM,
       RM.CENTRO_ID                                                          AS RM_CENTRO_ID,
       CENTRO.NOME                                                           AS RM_CENTRO_NOME,
       CENTRO.IDIOMA													     AS RM_CENTRO_IDIOMA,
       AREA.AREA_ID                                                          AS RM_AREA_ID,
       AREA.NOME                                                             AS RM_AREA_NOME,
       AREA.IDIOMA                                                           AS RM_AREA_IDIOMA,
       RM.NUMERO_RM                                                          AS RM_NUMERO_RM,
       P.DESCRICAO                                                           AS PRIORIDADE_DESCRICAO,
       P.CODIGO                                                              AS PRIORIDADE_CODIGO,
       M.CODIGO                                                              AS MATERIAL_CODIGO,
       M.NOME                                                                AS MATERIAL_NOME,
       M.IS_SERVICO                                                          AS MATERIAL_IS_SERVICO,
       RMM.QUANTIDADE                                                        AS RM_MATERIAL_QUANTIDADE,
       UNI.SIGLA                                                             AS UNIDADE_MEDIDA_SIGLA,
       TM.DESCRICAO                                                          AS TIPO_MATERIAL_DESCRICAO,
	   RMM.NOTA															     AS NOTA,
       TM.CODIGO                                                             AS TIPO_MATERIAL_CODIGO,
       S.NOME                                                                AS STATUS_NOME,
       S.CODIGO                                                              AS STATUS_CODIGO,
       TR.DESCRICAO                                                          AS TIPO_REQUISICAO_DESCRICAO,
       TR.CODIGO                                                             AS TIPO_REQUISICAO_CODIGO,
       DEP.CODIGO                                                            AS DEPOSITO_CODIGO,
       DEP.NOME                                                              AS DEPOSITO_DESCRICAO,
       RMM.COLETOR_CUSTOS                                                    AS RM_MATERIAL_COLETOR_CUSTOS,
       RMM.COLETOR_ESTOQUE                                                   AS RM_MATERIAL_COLETOR_ESTOQUE,
       RMM.DIAGRAMA_REDE                                                     AS RM_MATERIAL_DIAGRAMA_REDE,
       RMM.OPERACAO                                                          AS RM_MATERIAL_OPERACAO,
       RMM.COLETOR_ORDEM                                                     AS RM_MATERIAL_COLETOR_ORDEM,
       RMM.FLUXO_MATERIAL                                                    AS RM_MATERIAL_FLUXO_MATERIAL,
       RMM.JUSTIFICATIVA_ALTERACAO_QUANTIDADE                                AS RM_MATERIAL_JUST_ALT_QUANT,
       USUARIO.USUARIO_ID                                                    AS USUARIO_USUARIO_ID,
       USUARIO_PESSOA.NOME                                                   AS USUARIO_PESSOA_NOME,
       USUARIO_PESSOA.REFERENCIA                                             AS USUARIO_PESSOA_REFERENCIA,
       REQUISITANTE.NOME                                                     AS REQUISITANTE_NOME,
       REQUISITANTE.REFERENCIA                                               AS REQUISITANTE_REFERENCIA,
       ST.DESCRICAO                                                          AS SETOR_DESCRICAO,
       ST.CODIGO                                                             AS SETOR_CODIGO,
       SA.CODIGO                                                             AS SUB_AREA_CODIGO,
       SA.DESCRICAO                                                          AS SUB_AREA_DESCRICAO,
       RM.DATA_EMISSAO                                                       AS RM_DATA_EMISSAO,
       RM.DATA_APLICACAO                                                     AS RM_DATA_APLICACAO,
       RMM.DATA_RECEBIMENTO_SUPRIMENTOS                                      AS RM_MATERIAL_DATA_REC_SUP,
       RMM.DATA_PREV_ENTREGA_SUPRIMENTOS                                     AS RM_MATERIAL_DATA_PREV_ENTR,
       RMM.DATA_COMPRA                                                       AS RM_MATERIAL_DATA_COMPRA,
       RMM.DATA_ULTIMA_NF                                                    AS RM_MATERIAL_DATA_ULTIMA_NF,
       C.NOME                                                                AS COMPRADOR_NOME,
       C.COMPRADOR_ID                                                        AS COMPRADOR_COMPRADOR_ID,
       RMM.DATA_COMPRA                                                       AS DATA_COMPRA,
       RMM.NUMERO_REQUISICAO_SAP                                             AS RM_MATERIAL_NUMERO_REQUISICAO_SAP,
       RMM.ITEM_REQUISICAO_SAP                                               AS RM_MATERIAL_ITEM_REQUISICAO_SAP,
       RMM.NUMERO_DOC_ENTRADA                                                AS RM_MATERIAL_NUMERO_DOC_ENTRADA,
       RMM.NUMERO_PEDIDO_COMPRA                                              AS RM_MATERIAL_NUMERO_PEDIDO_COMPRA,
       RMM.DATA_PREVISTA_ENTREGA                                             AS RM_MATERIAL_DATA_PREVISTA_ENTR,
       RM.RESPONSAVEL_RETIRADA_ESTOQUE                                       AS RM_RESP_RET_ESTOQ,
       RM.OBSERVACAO                                                         AS RM_OBSERVACAO,
       RM.DATA_ENVIO_APROVACAO                                               AS RM_DATA_ENVIO_APROVACAO,
       RM.DATA_REPROVACAO                                                    AS RM_DATA_REPROVACAO,
       RM.DATA_CANCELAMENTO                                                  AS RM_DATA_CANCELAMENTO,
       RMM.OBSERVACAO                                                        AS RM_MATERIAL_OBSERVACAO,
       EAP.ID                                                                AS RM_EAP_MULTI_EMPRESA_ID,
       EAP_DEP.ID                                                            AS DEPOSITO_EAP_MULTI_EMPRESA_ID,
       EAP.DESCRICAO                                                         AS RM_EAP_MULTI_EMPRESA_DESCRICAO,
       EAP_DEP.DESCRICAO                                                     AS DEPOSITO_EAP_MULTI_EMPRESA_DESCRICAO,
       RMMS.OBSERVACAO                                                       AS RM_MATERIAL_STATUS_OBSERVACAO,
       EAP_COM.DESCRICAO                                                     AS COMPRADOR_EAP,
       RMM.RM_MATERIAL_ID                                                    AS RM_MATERIAL_RM_MATERIAL_ID,
       RMM.GRUPO_COMPRADOR													 AS RM_MATERIAL_GRUPO_COMPRADOR,
       (SELECT DATEDIFF(DAY, GETDATE(), RMM.DATA_PREVISAO_CHEGADA))          AS DIAS_PREVISTOS,
       (SELECT MAX (RMS.DATA_HORA_STATUS)
        FROM TB_RM_MATERIAL_STATUS RMS WITH (NOLOCK)
                 LEFT JOIN
             TB_STATUS S ON RMS.STATUS_ID = S.ID
        WHERE RMS.RM_MATERIAL_ID = RMM.RM_MATERIAL_ID
								
          AND S.CODIGO = 'AprGa')                                                AS DATA_APROV_GERENTE_AREA,

       (SELECT MAX (USER_G.NOME)
        FROM TB_RM_MATERIAL_STATUS RMS WITH (NOLOCK)
                 LEFT JOIN
             TB_USUARIO USER_G ON USER_G.NOME = RMS.USUARIO
                 LEFT JOIN
             TB_PESSOA USER_P ON USER_P.REFERENCIA = USER_G.LOGIN
                 LEFT JOIN
             TB_STATUS S ON RMS.STATUS_ID = S.ID
        WHERE RMS.RM_MATERIAL_ID = RMM.RM_MATERIAL_ID
								
          AND S.CODIGO = 'AprGc')                                                AS NOME_APROV_GERENTE_AREA,

       (SELECT TOP (1)APRGA.NOME
        FROM TB_PESSOA APRGA WITH (NOLOCK)
                LEFT JOIN
             TB_RM RM ON APRGA.PESSOA_ID = RM.GERENTE_AREA)                  AS GERENTE_AREA,
       RM.GERENTE_AREA                                                       AS GERENTE_AREA_ID,

       (SELECT MAX (USER_G.NOME)
        FROM TB_RM_MATERIAL_STATUS RMS WITH (NOLOCK)
                 LEFT JOIN
             TB_USUARIO USER_G ON USER_G.NOME = RMS.USUARIO
                 LEFT JOIN
             TB_PESSOA USER_P ON USER_P.REFERENCIA = USER_G.LOGIN
                 LEFT JOIN
             TB_STATUS S ON RMS.STATUS_ID = S.ID
        WHERE RMS.RM_MATERIAL_ID = RMM.RM_MATERIAL_ID
							  
          AND S.CODIGO = 'Apr')                                                AS NOME_APROV_MAQUINA_PARADA,

       (SELECT MAX (RMS.DATA_HORA_STATUS)
        FROM TB_RM_MATERIAL_STATUS RMS WITH (NOLOCK)
                 LEFT JOIN
             TB_STATUS S ON RMS.STATUS_ID = S.ID
        WHERE RMS.RM_MATERIAL_ID = RMM.RM_MATERIAL_ID
							   
          AND S.CODIGO = 'AprE')                                                AS DATA_APROV_MAQUINA_PARADA,
       (SELECT MAX(RMS.DATA_HORA_STATUS)
        FROM TB_RM_MATERIAL_STATUS RMS WITH (NOLOCK)
                 LEFT JOIN
             TB_STATUS S ON RMS.STATUS_ID = S.ID
        WHERE RMS.RM_MATERIAL_ID = RMM.RM_MATERIAL_ID
								
          AND S.CODIGO = 'AprGc')                                                AS DATA_APROV_GERENTE_CUSTOS,

       (SELECT MAX(USER_G.NOME)
        FROM TB_RM_MATERIAL_STATUS RMS WITH (NOLOCK)
                 LEFT JOIN
             TB_USUARIO USER_G ON USER_G.NOME = RMS.USUARIO
                 LEFT JOIN
             TB_PESSOA USER_P ON USER_P.REFERENCIA = USER_G.LOGIN
                 LEFT JOIN
             TB_STATUS S ON RMS.STATUS_ID = S.ID
        WHERE RMS.RM_MATERIAL_ID = RMM.RM_MATERIAL_ID
								  
          AND S.CODIGO IN ('AprE'))                                                AS NOME_APROV_GERENTE_CUSTOS,

       (SELECT TOP (1) APRGC.NOME
        FROM TB_PESSOA APRGC WITH (NOLOCK)
                 LEFT JOIN
             TB_RM RM ON APRGC.PESSOA_ID = RM.GERENTE_CUSTOS)                AS GERENTE_CUSTO,
       RM.GERENTE_CUSTOS                                                     AS GERENTE_CUSTO_ID,
       (SELECT MAX (RMS.DATA_HORA_STATUS)
        FROM TB_RM_MATERIAL_STATUS RMS WITH (NOLOCK)
                 LEFT JOIN
             TB_STATUS S ON RMS.STATUS_ID = S.ID
        WHERE RMS.RM_MATERIAL_ID = RMM.RM_MATERIAL_ID
							   
          AND S.CODIGO = 'AprA')                                                AS DATA_APROV_COORDENADOR,

       -- TODO - Criar coluna para usuário responsável pela aprovação
       (SELECT MAX (USER_G.NOME)
        FROM TB_RM_MATERIAL_STATUS RMS WITH (NOLOCK)
                 LEFT JOIN
             TB_USUARIO USER_G ON USER_G.NOME = RMS.USUARIO
                 LEFT JOIN
             TB_PESSOA USER_P ON USER_P.REFERENCIA = USER_G.LOGIN
                 LEFT JOIN
             TB_STATUS S ON RMS.STATUS_ID = S.ID
        WHERE RMS.RM_MATERIAL_ID = RMM.RM_MATERIAL_ID
								
          AND S.CODIGO = 'AprGa')                                                AS NOME_APROV_COORDENADOR,

       (SELECT TOP (1) APRCOO.NOME
        FROM TB_PESSOA APRCOO WITH (NOLOCK)
                 LEFT JOIN
             TB_RM RM ON APRCOO.PESSOA_ID = RM.COORDENADOR)                  AS COORDENADOR,
       RM.COORDENADOR                                                        AS COORDENADOR_ID,
      (SELECT MAX (RMS.DATA_HORA_STATUS)
        FROM TB_RM_MATERIAL_STATUS RMS WITH (NOLOCK)
                 LEFT JOIN
             TB_STATUS S ON RMS.STATUS_ID = S.ID
        WHERE RMS.RM_MATERIAL_ID = RMM.RM_MATERIAL_ID
								 
          AND S.CODIGO = 'AprRre')                                                AS DATA_APROV_RESPONSAVEL_RETIRADA_ESTOQUE,
       (SELECT TOP (1) APRRRE.NOME
        FROM TB_PESSOA APRRRE WITH (NOLOCK)
                 LEFT JOIN
             TB_RM RM ON APRRRE.PESSOA_ID = RM.RESPONSAVEL_RETIRADA_ESTOQUE) AS RESPONSAVEL_RETIRADA_ESTOQUE,
       RM.RESPONSAVEL_RETIRADA_ESTOQUE                           AS RESPONSAVEL_RETIRADA_ESTOQUE_ID,
       (SELECT MAX (RMS.DATA_HORA_STATUS)
        FROM TB_RM_MATERIAL_STATUS RMS WITH (NOLOCK)
                 LEFT JOIN
             TB_STATUS S ON RMS.STATUS_ID = S.ID
        WHERE RMS.RM_MATERIAL_ID = RMM.RM_MATERIAL_ID
								   
          AND S.CODIGO = 'CompCust')                                                AS DATA_APROV_COMPLEMENTO_CUSTOS,
       (SELECT CASE
                   WHEN EXISTS
                            (SELECT RMR.RM_MATERIAL_RETIRADA_ID
                             FROM TB_RM_MATERIAL_RETIRADA RMR WITH (NOLOCK)
                             WHERE RMM.RM_MATERIAL_ID = RMR.RM_MATERIAL_ID
                               AND RMR.PRE_RETIRADA = 0
                               AND RMR.DATA_AUTENTICACAO IS NULL) AND
                        (RMM.CONFIRMA_RET_NAO_PRESENCIAL IS NULL OR
                         RMM.CONFIRMA_RET_NAO_PRESENCIAL = 0) AND EXISTS
                            (SELECT RMS.ID
                             FROM TB_RM_MATERIAL_STATUS RMS WITH (NOLOCK)
                                      LEFT JOIN
                                  TB_STATUS S ON RMS.STATUS_ID = S.ID
                             WHERE RMS.RM_MATERIAL_ID = RMM.RM_MATERIAL_ID
                               AND (RMS.IS_HISTORICO IS NULL OR
                                    RMS.IS_HISTORICO = 0)
                               AND S.CODIGO = 'Fim') THEN CAST(1 AS BIT)
                   ELSE CAST(0 AS BIT) END)                                  AS CONFIRMAR_RETIRADA,
       (SELECT sum([QUANTIDADE])
        FROM [TB_RM_MATERIAL_RECEBIMENTO] AS rec WITH (NOLOCK)
        WHERE rec.[RM_MATERIAL_ID] = RMM.[RM_MATERIAL_ID])                   AS QTDE_RECEBIDA
FROM TB_RM RM
         LEFT JOIN
     TB_RM_MATERIAL RMM ON RMM.RM_ID = RM.RM_ID
         LEFT JOIN
     TB_RM_MATERIAL_STATUS RMMS ON RMMS.RM_MATERIAL_ID = RMM.RM_MATERIAL_ID
         LEFT JOIN
     TB_CENTRO CENTRO ON CENTRO.CENTRO_ID = RM.CENTRO_ID
         LEFT JOIN
     TB_AREA AREA ON AREA.AREA_ID = RM.AREA_ID
         LEFT JOIN
     TB_MATERIAL M ON M.MATERIAL_ID = RMM.MATERIAL_ID
         LEFT JOIN
     TB_STATUS S ON S.ID = RMMS.STATUS_ID
         LEFT JOIN
     TB_USUARIO USUARIO ON USUARIO.USUARIO_ID = RM.USUARIO_ID
         LEFT JOIN
     TB_PESSOA USUARIO_PESSOA ON USUARIO.PESSOA_ID = USUARIO_PESSOA.PESSOA_ID
         LEFT JOIN
     TB_PESSOA REQUISITANTE ON REQUISITANTE.PESSOA_ID = RM.REQUISITANTE_ID
         LEFT JOIN
     TB_TIPO_REQUISICAO TR ON TR.TIPO_REQUISICAO_ID = RM.TIPO_REQUISICAO_ID
         LEFT JOIN
     TB_DEPOSITO DEP ON DEP.DEPOSITO_ID = RM.DEPOSITO_ID
         LEFT JOIN
     TB_TIPO_MATERIAL TM ON TM.TIPO_MATERIAL_ID = M.TIPO_MATERIAL_ID
         LEFT JOIN
     TB_SUB_AREA SA ON SA.SUB_AREA_ID = REQUISITANTE.SUB_AREA_ID
         LEFT JOIN
     TB_SETOR ST ON ST.SETOR_ID = REQUISITANTE.SETOR_ID
         LEFT JOIN
     TB_PRIORIDADE P ON P.PRIORIDADE_ID = RM.PRIORIDADE_ID
         LEFT JOIN
     TB_UNIDADE_MEDIDA UNI ON UNI.UNIDADE_MEDIDA_ID = M.UNIDADE_MEDIDA_ID
         LEFT JOIN
     TB_COMPRADOR C ON C.COMPRADOR_ID = RMM.COMPRADOR_ID
         LEFT JOIN
     TB_EAP_MULTI_EMPRESA EAP_COM ON C.EAP_MULTI_EMPRESA_ID = EAP_COM.ID
         LEFT JOIN
     TB_EAP_MULTI_EMPRESA EAP ON EAP.ID = RM.EAP_MULTI_EMPRESA_ID
         LEFT JOIN
     TB_EAP_MULTI_EMPRESA EAP_DEP ON EAP_DEP.ID = DEP.EAP_MULTI_EMPRESA_ID
         LEFT JOIN
     TB_RM_SERVICO TRS ON TRS.RM_ID = RM.RM_ID
WHERE (RMMS.ID IN
       (SELECT MAX(ID)

        FROM TB_RM_MATERIAL_STATUS WITH (NOLOCK)
        WHERE RM_MATERIAL_ID = RMM.RM_MATERIAL_ID))
   or (TRS.RM_ID is not null)