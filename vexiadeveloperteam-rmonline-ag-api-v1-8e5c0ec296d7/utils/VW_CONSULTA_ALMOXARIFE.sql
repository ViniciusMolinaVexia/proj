CREATE   view VW_CONSULTA_ALMOXARIFE as

select 
ROW_NUMBER() OVER (ORDER BY a.RM_MATERIAL_ID) AS id, 
d.NOME as centro, 
d.CODIGO as cod_centro,
b.NUMERO_RM as numero, 
a.NUMERO_REQUISICAO_SAP as numero_sap, 
e.NOME as material, 
e.CODIGO as cod_material,
j.SIGLA as unidade_medida,
f.CODIGO as codigo, 
a.QUANTIDADE as quantidade, 
b.DATA_EMISSAO as data_emissao, 
b.DATA_APLICACAO as data_aplicacao, 
g.NOME as requisitante, 
h.NOME as cadastrante, 
a.FLUXO_MATERIAL as fluxo_material, 
a.COLETOR_CUSTOS as coletor_custos, 
b.DATA_EMISSAO as data_avaliacao, 
a.STATUS as status,  
a.STATUS as status_item, 
i.DESCRICAO as tipo_requisicao, 
b.OBSERVACAO as observacao,
a.RM_MATERIAL_ID as rmMaterialId,
b.RM_ID as rmId,
k.CODIGO as area_codigo,
l.CODIGO as deposito_codigo,
e.GRUPO_MERCADORIA as grupo_mercadoria,
m.LOGIN as solicitante,
a.IS_BAIXA as is_baixa,
a.NUMERO_BAIXA as numero_baixa,
a.GRUPO_COMPRADOR as grupo_comprador
from TB_RM_MATERIAL a 
inner join TB_RM b on a.RM_ID=b.RM_ID 
inner join TB_RM_APROVADOR c on c.RM_ID = b.RM_ID 
inner join TB_CENTRO d on d.CENTRO_ID=b.CENTRO_ID 
inner join TB_MATERIAL e on e.MATERIAL_ID = a.MATERIAL_ID 
inner join TB_TIPO_MATERIAL f on f.TIPO_MATERIAL_ID=e.TIPO_MATERIAL_ID 
inner join TB_USUARIO g on g.USUARIO_ID = b.REQUISITANTE_ID 
inner join TB_USUARIO h on h.USUARIO_ID=b.USUARIO_ID 
inner join TB_TIPO_REQUISICAO i on b.TIPO_REQUISICAO_ID=i.TIPO_REQUISICAO_ID 
inner join TB_UNIDADE_MEDIDA j on j.UNIDADE_MEDIDA_ID = e.UNIDADE_MEDIDA_ID
inner join TB_AREA k on k.AREA_ID = b.AREA_ID
inner join TB_DEPOSITO l on l.DEPOSITO_ID = a.DEPOSITO_ID
inner join TB_USUARIO m on m.USUARIO_ID = b.USUARIO_ID
where c.APROVADA = 1 and g.NOME like '%VEXIA%';