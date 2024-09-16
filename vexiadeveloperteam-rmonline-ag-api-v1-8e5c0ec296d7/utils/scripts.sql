-- Inserir ROLES
insert into TB_ROLE (ROLE_ID,ATIVO,DESCRICAO,NOME)
values (1,'S','Acesso ao menu principal de CADASTRAR / CONSULTAR', 'ROLE_CADASTRAR_CONSULTAR');

insert into TB_ROLE (ROLE_ID,ATIVO,DESCRICAO,NOME)
values (2,'S','Acesso ao menu principal de APROVAR', 'ROLE_APROVAR');

insert into TB_ROLE (ROLE_ID,ATIVO,DESCRICAO,NOME)
values (3,'S','Acesso ao menu principal de ADMINISTRAR ENVIO AO SAP', 'ROLE_ADMINISTRAR_ENVIO_SAP');

insert into TB_ROLE (ROLE_ID,ATIVO,DESCRICAO,NOME)
values (4,'S','Acesso ao menu principal de RASTREABILIDADE', 'ROLE_RASTREABILIDADE');

insert into TB_ROLE (ROLE_ID,ATIVO,DESCRICAO,NOME)
values (5,'S','Acesso ao menu principal de SUPRIMENTOS', 'ROLE_SUPRIMENTOS');

insert into TB_ROLE (ROLE_ID,ATIVO,DESCRICAO,NOME)
values (6,'S','Acesso ao menu principal de PAINEL DE REQUISIÇÃO DE MATERIAIS ', 'ROLE_PAINEL_REQUISICAO_MATERIAIS');

insert into TB_ROLE (ROLE_ID,ATIVO,DESCRICAO,NOME)
values (9,'S','Acesso ao menu principal de GESTÃO DE ESTOQUE', 'ROLE_GESTAO_ESTOQUE');

insert into TB_ROLE (ROLE_ID,ATIVO,DESCRICAO,NOME)
values (10,'S','Acesso ao menu principal de RELATÓRIOS DE GESTÃO', 'ROLE_RELATORIO_GESTAO');

insert into TB_ROLE (ROLE_ID,ATIVO,DESCRICAO,NOME)
values (11,'S','Acesso ao menu principal de PREVISÃO/PENDÊNCIA RECEBIMENTO', 'ROLE_RELATORIO_PREVISOES_PENDENCIA_RECEBIMENTO');

insert into TB_ROLE (ROLE_ID,ATIVO,DESCRICAO,NOME)
values (12,'S','Acesso ao menu principal de LOG INTERFACE', 'ROLE_LOG_INTERFACE');

insert into TB_ROLE (ROLE_ID,ATIVO,DESCRICAO,NOME)
values (13,'S','Acesso ao menu principal de HIERARQUIA COMPRADOR', 'ROLE_HIERARQUIA_COMPRADOR');

insert into TB_ROLE (ROLE_ID,ATIVO,DESCRICAO,NOME)
values (14,'S','Acesso ao menu principal de USUÁRIOS', 'ROLE_USUARIOS');

insert into TB_ROLE (ROLE_ID,ATIVO,DESCRICAO,NOME)
values (15,'S','Acesso ao menu principal de PERFIL', 'ROLE_PERFIL');

insert into TB_ROLE (ROLE_ID,ATIVO,DESCRICAO,NOME)
values (17,'S','Acesso as regras de REQUISITANTE', 'ROLE_REQUISITANTE');

insert into TB_ROLE (ROLE_ID,ATIVO,DESCRICAO,NOME)
values (18,'S','Acesso as regras de REQUISITANTE CORPORATIVO', 'ROLE_REQUISITANTE_CORPORATIVO');

insert into TB_ROLE (ROLE_ID,ATIVO,DESCRICAO,NOME)
values (19,'S','Acesso as regras de CADASTRO DE USUÁRIO', 'ROLE_USUARIOS_CADASTRO');

insert into TB_ROLE (ROLE_ID,ATIVO,DESCRICAO,NOME)
values (21,'S','Acesso as regras de ADMINISTRADOR', 'ROLE_ADMINISTRADOR');

insert into TB_ROLE (ROLE_ID,ATIVO,DESCRICAO,NOME)
values (22,'S','Acesso as regras de COMPRADOR', 'ROLE_COMPRADOR');

insert into TB_ROLE (ROLE_ID,ATIVO,DESCRICAO,NOME)
values (23,'S','Acesso as regras de APROVADOR DE AREA', 'ROLE_APROVADOR_AREA');

insert into TB_ROLE (ROLE_ID,ATIVO,DESCRICAO,NOME)
values (24,'S','Acesso as regras de APROVADOR DE CUSTO', 'ROLE_APROVADOR_CUSTO');

insert into TB_ROLE (ROLE_ID,ATIVO,DESCRICAO,NOME)
values (25,'S','Acesso as regras de APROVADOR EMERGENCIAL', 'ROLE_APROVADOR_EMERGENCIAL');

insert into TB_ROLE (ROLE_ID,ATIVO,DESCRICAO,NOME)
values (26,'S','Acesso as regras de WORKFLOW', 'ROLE_WORKFLOW');

insert into TB_ROLE (ROLE_ID,ATIVO,DESCRICAO,NOME)
values (27,'S','Acesso as regras de APROVADOR DE GERENTE DE AREA', 'ROLE_APROVADOR_GERENTE_AREA');

insert into TB_ROLE (ROLE_ID,ATIVO,DESCRICAO,NOME)
values (28,'S','Acesso as regras de Estoquista', 'ROLE_ESTOQUISTA');

-- INSERIR PERFIS
insert into TB_PERFIL(ATIVO,NOME)
values ('S','Requisitante');

insert into TB_PERFIL(ATIVO,NOME)
values ('S','Requisitante Corporativo');

insert into TB_PERFIL(ATIVO,NOME)
values ('S','Aprovador Area');

insert into TB_PERFIL(ATIVO,NOME)
values ('S','Aprovador Custo');

insert into TB_PERFIL(ATIVO,NOME)
values ('S','Aprovador Emergencial');

insert into TB_PERFIL(ATIVO,NOME)
values ('S','Aprovador Gerente Area');

insert into TB_PERFIL(ATIVO,NOME)
values ('S','Comprador');

insert into TB_PERFIL(ATIVO,NOME)
values ('S','Almoxarifado');

insert into TB_PERFIL(ATIVO,NOME)
values ('S','Administrador');

-- ASSOCIAR ROLES AOS PERFIS
insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Requisitante'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_CADASTRAR_CONSULTAR')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Requisitante'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_REQUISITANTE')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Requisitante'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_USUARIOS')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Requisitante Corporativo'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_REQUISITANTE_CORPORATIVO')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Requisitante Corporativo'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_CADASTRAR_CONSULTAR')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Requisitante Corporativo'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_USUARIOS')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Administrador'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_CADASTRAR_CONSULTAR')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Administrador'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_APROVAR')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Administrador'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_ADMINISTRAR_ENVIO_SAP')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Administrador'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_RASTREABILIDADE')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Administrador'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_SUPRIMENTOS')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Administrador'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_PAINEL_REQUISICAO_MATERIAIS')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Administrador'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_GESTAO_ESTOQUE')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Administrador'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_RELATORIO_GESTAO')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Administrador'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_RELATORIO_PREVISOES_PENDENCIA_RECEBIMENTO')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Administrador'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_LOG_INTERFACE')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Administrador'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_HIERARQUIA_COMPRADOR')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Administrador'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_USUARIOS')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Administrador'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_PERFIL')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Administrador'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_REQUISITANTE')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Administrador'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_REQUISITANTE_CORPORATIVO')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Administrador'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_USUARIOS_CADASTRO')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Administrador'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_ADMINISTRADOR')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Administrador'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_WORKFLOW')
);

-- Aprovador 
insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Aprovador Area'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_APROVADOR_AREA')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Aprovador Area'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_APROVAR')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Aprovador Custo'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_APROVADOR_CUSTO')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Aprovador Emergencial'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_APROVADOR_EMERGENCIAL')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Aprovador Gerente Area'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_APROVADOR_GERENTE_AREA')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Aprovador Gerente Area'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_APROVAR')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Aprovador Custo'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_APROVAR')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Comprador'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_COMPRADOR')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Comprador'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_PAINEL_REQUISICAO_MATERIAIS')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Comprador'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_USUARIOS')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Almoxarifado'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_ESTOQUISTA')
);

insert into TB_PERFIL_ROLE (PERFIL_ID,ROLE_ID) values (
	(select PERFIL_ID from TB_PERFIL where NOME = 'Almoxarifado'),
	(select ROLE_ID from TB_ROLE where NOME = 'ROLE_USUARIOS')
);



-- Novas prioridades
insert into dbo.TB_PRIORIDADE
(DESCRICAO,CODIGO,CONF_DIAS_PREV_ENTREGA,CONF_DIAS_FIM_PRIORIDADE,CONF_DIAS_INI_PRIORIDADE)
values
('Máquina Parada','MAQ_PARADA',2,2,1);

insert into dbo.TB_PRIORIDADE
(DESCRICAO,CODIGO,CONF_DIAS_PREV_ENTREGA,CONF_DIAS_FIM_PRIORIDADE,CONF_DIAS_INI_PRIORIDADE)
values
('Urgente','URGENTE',5,7,3);

insert into dbo.TB_PRIORIDADE
(DESCRICAO,CODIGO,CONF_DIAS_PREV_ENTREGA,CONF_DIAS_FIM_PRIORIDADE,CONF_DIAS_INI_PRIORIDADE)
values
('Normal','NORMAL',10,8,999999);

-- Tipos de REquisicao
insert into TB_TIPO_REQUISICAO 
(DESCRICAO,CODIGO) values
('Solicitação de Reserva de Estoque', 'ESTOQUE');

insert into TB_TIPO_REQUISICAO 
(DESCRICAO,CODIGO) values
('Solicitação de Compra de Materiais', 'COMPRA');

insert into dbo.TB_USUARIO
(ATIVO,LOGIN,NOME,SENHA,PERFIL_ID) values 
('S','Vexia','Vexia','202cb962ac59075b964b07152d234b70', select PERFIL_ID from TB_PERFIL where NOME = 'Administrador');

-- Novos status de RM
insert into dbo.TB_STATUS (CODIGO,NOME) values ('AprA','Aprovação Área');
insert into dbo.TB_STATUS (CODIGO,NOME) values ('AprE','Aprovação Emergencial');