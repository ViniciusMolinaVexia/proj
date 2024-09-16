package br.com.nextage.rmaweb.relatorio;

import br.com.nextage.persistence.classes.NxOrder;
import br.com.nextage.persistence.classes.Propriedade;
import br.com.nextage.persistence.dao.GenericDao;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.rmaweb.entitybean.Comprador;
import br.com.nextage.rmaweb.entitybean.Fornecedor;
import br.com.nextage.rmaweb.entitybean.Material;
import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.rmaweb.entitybean.Prioridade;
import br.com.nextage.rmaweb.entitybean.Rm;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.entitybean.RmMaterialRecebimento;
import br.com.nextage.rmaweb.entitybean.RmMaterialRetirada;
import br.com.nextage.rmaweb.entitybean.TipoMaterial;
import br.com.nextage.rmaweb.entitybean.UnidadeMedida;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.service.RmMaterialRecebimentoService;
import br.com.nextage.rmaweb.service.RmMaterialRetiradaService;
import br.com.nextage.rmaweb.service.RmMaterialService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletOutputStream;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 *
 * @author Juliano A. Felipe
 */
public class ControleRmRelatorio {

    private GenericDao genericDao;
    private Rm rm;
    private RmMaterialRecebimentoService recebimentoService;
    private RmMaterialRetiradaService retiradaService;
    private List<RmMaterialRecebimento> listaRecebimentos;
    private List<RmMaterialRetirada> listaRetiradas;
    //CAMPOS A SEREM MOSTRADOS NO RELATORIO
    private boolean addcodigo = false;//Código
    private boolean addstatus = false;//Status
    private boolean addnumeroRm = false;//Nº da RM
    private boolean adddataEmissao = false;//Data de Emissão
    private boolean adddataRecebimento = false;//Data de Recebimento
    private boolean additem = false;//Item
    private boolean adddescricaoItem = false;//Descrição Item
    private boolean addquantidade = false;//Quantidade
    private boolean addunidadeMedida = false;//Unidade de Medida
    private boolean adddataAplicacao = false;//Data de Aplicação
    private boolean addprazoCompraRecebimento = false;//Prazo para Compra / Recebimento
    private boolean addcomprador = false;//Comprador
    private boolean adddisciplina = false;//Disciplina
    private boolean addnivelPrioridade = false;//Nível Prioridade
    private boolean addobservacao = false;//Observação
    private boolean addrequisitante = false;//Requisitante
    private boolean addnumeroPedidoCompra = false;//Nº de Pedido da Compra
    private boolean adddata = false;//Data
    private boolean addcodigoFornecedor = false;//Cód. do Fornecedor
    private boolean addnomeFornecedor = false;//Nome do Fornecedor
    private boolean addtempoRealCompra = false;//Tempo Real de Compra
    private boolean addprevisaoChegada = false;//Previsão de Chegada
    private boolean addqtdeRecebida = false;//Qtde Recebida
    private boolean addsaldo = false;//Saldo
    private boolean addnumeroNotaFiscal = false;//Nº da Nota Fiscal
    private boolean adddataEmissaoNF = false;//Data de Emissão
    private boolean addleadTime = false;//Lead-Time
    private boolean adddataRecebimentoMaterial = false;//Data de Recebimento do Material
    private boolean adddataUltimaRetiradaMaterial = false;//Data da Última Retirada do Material
    private boolean addleadTimeTotal = false;//Lead-Time Total
    private boolean addanotacao = false;//Anotações
    private boolean addcadastrante = false;//Cadastrante

    public Rm getRm() {
        return rm;
    }

    public void setRm(Rm rm) {
        this.rm = rm;
    }

    public ControleRmRelatorio() {
        recebimentoService = new RmMaterialRecebimentoService();
        retiradaService = new RmMaterialRetiradaService();
        genericDao = new GenericDao();
    }

    /**
     * @author Juliano A. Felipe
     *
     * Lista os campos necessários para gerar o Relatório "Controle de RM's" em
     * Excel.
     *
     * Alterações:
     * @author Jerry Melhorado a performance.
     *
     * @param filtros Deve conter os filtros necessários para a geração do
     * relatório, caso existam. Pode ser enviado <i>null</i>.
     *
     * @return <b>lista</b> - Retorna uma lista com os campos necessários para a
     * geração do Relatório.
     * <br/>
     * <b>null</b> - Em caso de falha.
     */
    private List<RmMaterial> listar(NxCriterion nxCriterion) {
        List<RmMaterial> lista = new ArrayList<>();
        List<br.com.nextage.persistence_2.classes.Propriedade> prop = new ArrayList<>();

        String aliasRm = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM);
        String aliasMaterial = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL);
        String aliasFornecedor = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.FORNECEDOR_ID);
        String aliasComprador = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.COMPRADOR);
        String aliasRequisitante = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.REQUISITANTE);
        String aliasPrioridade = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.PRIORIDADE);
        String aliasUsuario = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.USUARIO);
        String aliasUnidadeMedida = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL, Material.UNIDADE_MEDIDA);
        String aliasTipoMaterial = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL, Material.TIPO_MATERIAL);
        String aliasUsuarioPessoa = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.USUARIO, Usuario.PESSOA);

        try {
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(RmMaterial.RM_MATERIAL_ID));
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(RmMaterial.QUANTIDADE));
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(RmMaterial.DATA_COMPRA));
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(RmMaterial.DATA_PREVISAO_CHEGADA));
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(RmMaterial.NUMERO_PEDIDO_COMPRA));
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(RmMaterial.OBSERVACAO));
            //RM - 2 nivel
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(Rm.DATA_EMISSAO, Rm.class, aliasRm));
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(Rm.DATA_RECEBIMENTO, Rm.class, aliasRm));
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(Rm.DATA_APLICACAO, Rm.class, aliasRm));
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(Rm.DATA_APROVACAO_COMPRA, Rm.class, aliasRm));
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(Rm.OBSERVACAO, Rm.class, aliasRm));
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(Rm.DATA_CANCELAMENTO, Rm.class, aliasRm));
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(Rm.DATA_ACEITE_COMPRADOR, Rm.class, aliasRm));
            // Material - 2 nivel
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.NOME, Material.class, aliasMaterial));
            // Fornecedor - 2 nivel
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(Fornecedor.FORNECEDOR_ID, Fornecedor.class, aliasFornecedor));
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(Fornecedor.CODIGO, Fornecedor.class, aliasFornecedor));
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(Fornecedor.NOME, Fornecedor.class, aliasFornecedor));
            // Comprador - 3 nivel
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(Comprador.COMPRADOR_ID, Comprador.class, aliasComprador));
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(Comprador.NOME, Comprador.class, aliasComprador));
            // Requisitante - 3 nivel
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRequisitante));
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(Pessoa.NOME, Pessoa.class, aliasRequisitante));
            // Prioridade - 4 nivel
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(Prioridade.PRIORIDADE_ID, Prioridade.class, aliasPrioridade));
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(Prioridade.DESCRICAO, Prioridade.class, aliasPrioridade));
            //usuario
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(Usuario.USUARIO_ID, Usuario.class, aliasUsuario));
            // Unidade Medida - 5 nivel
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(UnidadeMedida.DESCRICAO, UnidadeMedida.class, aliasUnidadeMedida));
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));
            // Tipo Material - 5 nivel
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(TipoMaterial.TIPO_MATERIAL_ID, TipoMaterial.class, aliasTipoMaterial));
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(TipoMaterial.DESCRICAO, TipoMaterial.class, aliasTipoMaterial));
            //usuario pessoa
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasUsuarioPessoa));
            prop.add(new br.com.nextage.persistence_2.classes.Propriedade(Pessoa.NOME, Pessoa.class, aliasUsuarioPessoa));

            br.com.nextage.persistence_2.classes.NxOrder nxOrder = new br.com.nextage.persistence_2.classes.NxOrder(RmMaterial.RM + "." + Rm.NUMERO_RM, br.com.nextage.persistence_2.classes.NxOrder.NX_ORDER.DESC);
            List<br.com.nextage.persistence_2.classes.NxOrder> nxOrders = Arrays.asList(nxOrder);

            br.com.nextage.persistence_2.dao.GenericDao<RmMaterial> dao = new br.com.nextage.persistence_2.dao.GenericDao<>();
            lista = dao.listarByFilter(prop, nxOrders, RmMaterial.class, -1, nxCriterion);

            //Seta as quantidades de Material recebido e retirado
            if (lista != null && lista.size() > 0) {
                RmMaterialService service = new RmMaterialService();
                lista = service.setaQtdeRetiradaRecebimentoForRmMaterial(lista, null);
            }
        } catch (Exception e) {
            lista = null;
        }

        return lista;
    }

    /**
     * @author Juliano A. Felipe
     *
     * Gera o Relatório "Controle de RM's" em Excel.
     * @param nxCriterion
     * @param outputStream
     * @param campos
     *
     * @return <b>Sucesso</b> ou <b>Falha</b>
     *
     */
    public String geraRelatorioXls(NxCriterion nxCriterion, ServletOutputStream outputStream, String[] campos) {
        List<RmMaterial> lista = new ArrayList<>();

        //verifica quais campos serão mostrados no relatorio
        setaCamposRelatorio(campos);

        try {
            //Traz todas as RmMaterial
            lista = listar(nxCriterion);
            if (lista != null && lista.size() > 0) {
                //lista que auxilia para pegar todos os recebimentos da RmMaterial
                carregalistaRecebimentos();
                //lista que auxilia para pegar a ultima retirada de RmMaterial
                carregalistaRetirada();
            }

            WorkbookSettings ws = new WorkbookSettings();
            ws.setLocale(new Locale("pt", "BR"));
            WritableWorkbook workbook = Workbook.createWorkbook(outputStream, ws);
            WritableSheet sheet = workbook.createSheet("Controle RM", 0);

            jxl.write.Number number;
            DateTime dateCell;

            //Formata Data
            DateFormat customDateFormat = new DateFormat("dd/MM/yyyy");
            WritableCellFormat dateFormat = new WritableCellFormat(customDateFormat);

            //Formata Número.
            NumberFormat custonNumberFormat = new NumberFormat("#,##0.000");
            WritableCellFormat numberFormat = new WritableCellFormat(custonNumberFormat);

            WritableCellFormat cabecalho = new WritableCellFormat();
            Colour color = Colour.GREY_25_PERCENT;
            cabecalho.setBackground(color);
            cabecalho.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
            cabecalho.setAlignment(Alignment.CENTRE);
            WritableFont font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            cabecalho.setFont(font);

            int linha = 0;
            int coluna = 0;
            Label label;

            /**
             * ***********************| Cabeçalho |************************
             */
            if (addcodigo) {
                label = new Label(coluna, linha, "Código");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (addstatus) {
                label = new Label(coluna, linha, "Status");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (addnumeroRm) {
                label = new Label(coluna, linha, "Nº da RM");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (adddataEmissao) {
                label = new Label(coluna, linha, "Data de Emissão");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (adddataRecebimento) {
                label = new Label(coluna, linha, "Data de Recebimento");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (additem) {
                label = new Label(coluna, linha, "Item");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (adddescricaoItem) {
                label = new Label(coluna, linha, "Descrição Item");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (addquantidade) {
                label = new Label(coluna, linha, "Quantidade");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (addunidadeMedida) {
                label = new Label(coluna, linha, "Unidade de Medida");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (adddataAplicacao) {
                label = new Label(coluna, linha, "Data de Aplicação");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (addprazoCompraRecebimento) {
                label = new Label(coluna, linha, "Prazo para Compra / Recebimento");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (addcomprador) {
                label = new Label(coluna, linha, "Comprador");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (adddisciplina) {
                label = new Label(coluna, linha, "Disciplina");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (addnivelPrioridade) {
                label = new Label(coluna, linha, "Nível Prioridade");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (addobservacao) {
                label = new Label(coluna, linha, "Observação");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (addrequisitante) {
                label = new Label(coluna, linha, "Requisitante");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (addcadastrante) {
                label = new Label(coluna, linha, "Cadastrante");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (addnumeroPedidoCompra) {
                label = new Label(coluna, linha, "Nº de Pedido da Compra");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (adddata) {
                label = new Label(coluna, linha, "Data");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (addcodigoFornecedor) {
                label = new Label(coluna, linha, "Cód. do Fornecedor");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (addnomeFornecedor) {
                label = new Label(coluna, linha, "Nome do Fornecedor");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (addtempoRealCompra) {
                label = new Label(coluna, linha, "Tempo Real de Compra");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (addprevisaoChegada) {
                label = new Label(coluna, linha, "Previsão de Chegada");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (addqtdeRecebida) {
                label = new Label(coluna, linha, "Qtde Recebida");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (addsaldo) {
                label = new Label(coluna, linha, "Saldo");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (addnumeroNotaFiscal) {
                label = new Label(coluna, linha, "Nº da Nota Fiscal");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (adddataEmissaoNF) {
                label = new Label(coluna, linha, "Data de Emissão");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (addleadTime) {
                label = new Label(coluna, linha, "Lead-Time");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (adddataRecebimentoMaterial) {
                label = new Label(coluna, linha, "Data de Recebimento do Material");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (adddataUltimaRetiradaMaterial) {
                label = new Label(coluna, linha, "Data da Última Retirada do Material");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (addleadTimeTotal) {
                label = new Label(coluna, linha, "Lead-Time Total");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            if (addanotacao) {
                label = new Label(coluna, linha, "Anotações");
                label.setCellFormat(cabecalho);
                sheet.addCell(label);
                coluna++;
            }
            /**
             * ***********************************************************
             */
            linha++;

            /**
             * **************| Contador para os Itens da RM |*************
             */
            int contItem = 1;
            RmMaterial aux = null;

            /**
             * *******************| Dados do Relatório |******************
             */
            for (RmMaterial r : lista) {
                coluna = 0;

                // VERIFICA OS RECEBIMENTOS DO MATERIAL DA RM.
                List<RmMaterialRecebimento> recebimentos = listarRecebimentos(r.getRmMaterialId());
                //recebimentoService.listarRecebimentos(r.getRmMaterialId());

                // VERIFICA SE O MATERIAL FOI RETIRADO. Obtem A ÚLTIMA DATA DE RETIRADA.
                RmMaterialRetirada lastRetirada = getLastRetirada(r.getRmMaterialId());
                //retiradaService.getLastRetirada(r);

                // Avalia a variável conItem, que contém o contador de Itens
                // de cada RM
                if (aux != null && aux.getRm().getNumeroRm().equals(r.getRm().getNumeroRm())) {
                    contItem++;
                } else {
                    contItem = 1;
                }

                // Código
                if (addcodigo) {
                    if (r.getRm() != null) {
                        label = new Label(coluna, linha, r.getRm().getNumeroRm() + "-" + contItem);
                        sheet.addCell(label);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // STATUS - CONTROLE DO STATUS DA RM.
                if (addstatus) {
                    if (r.getRm() != null) {
                        Double qtdRecebido = r.getQtdRecebido();
                        Double qtdRetirado = r.getQtdRetirado();
                        // CANCELADO.
                        if (r.getRm() != null && r.getRm().getDataCancelamento() != null) {
                            label = new Label(coluna, linha, "CANCELADO");
                            sheet.addCell(label);
                            coluna++;
                        } else if (r.getRm().getDataAceiteComprador() == null) {
                            // SEM COMPRADOR
                            label = new Label(coluna, linha, "EM ANÁLISE");
                            sheet.addCell(label);
                            coluna++;
                        } else if (r.getDataCompra() == null && qtdRecebido.equals(0d)) {
                            // Comprador indicado, porém a compra não foi realizada
                            label = new Label(coluna, linha, "EM COMPRA");
                            sheet.addCell(label);
                            coluna++;
                        } else if (qtdRecebido == 0) {

                            // Compra realizada, porém o material ainda nao foi recebido.
                            label = new Label(coluna, linha, "PENDENTE DE RECEBIMENTO");
                            sheet.addCell(label);
                            coluna++;
                        } else if (qtdRecebido > 0 && qtdRetirado > 0 && (qtdRecebido - qtdRetirado) == 0) {

                            // Compra realizada e material recebido.
                            label = new Label(coluna, linha, "RETIRADO");
                            sheet.addCell(label);
                            coluna++;
                        } else if (qtdRecebido > 0 && qtdRetirado > 0 && (qtdRecebido - qtdRetirado) > 0) {
                            // Compra realizada, porém o material foi recebido parcialmente
                            label = new Label(coluna, linha, "RETIRADO PARCIALMENTE");
                            sheet.addCell(label);
                            coluna++;
                        } else if (qtdRecebido > 0 && (r.getQuantidade() - qtdRecebido) == 0) {

                            // Compra realizada e material recebido.
                            label = new Label(coluna, linha, "RECEBIDO");
                            sheet.addCell(label);
                            coluna++;
                        } else if (qtdRecebido > 0 && (r.getQuantidade() - qtdRecebido) > 0) {
                            // Compra realizada, porém o material foi recebido parcialmente
                            label = new Label(coluna, linha, "RECEBIDO PARCIALMENTE");
                            sheet.addCell(label);
                            coluna++;
                        } else {
                            coluna++;
                        }
                    } else {
                        coluna++;
                    }
                }
                // Nº da RM
                if (addnumeroRm) {
                    if (r.getRm().getNumeroRm() != null) {
                        label = new Label(coluna, linha, r.getRm().getNumeroRm());
                        sheet.addCell(label);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Data de Emissão
                if (adddataEmissao) {
                    if (r.getRm().getDataEmissao() != null) {
                        dateCell = new DateTime(coluna, linha, r.getRm().getDataEmissao(), dateFormat);
                        sheet.addCell(dateCell);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Data de Recebimento
                if (adddataRecebimento) {
                    if (r.getRm().getDataRecebimento() != null) {
                        dateCell = new DateTime(coluna, linha, r.getRm().getDataRecebimento(), dateFormat);
                        sheet.addCell(dateCell);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Item
                if (additem) {
                    if (r.getMaterial().getNome() != null) {
                        number = new jxl.write.Number(coluna, linha, contItem);
                        sheet.addCell(number);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Descrição do Item
                if (adddescricaoItem) {
                    if (r.getMaterial() != null && r.getMaterial().getNome() != null) {
                        label = new Label(coluna, linha, r.getMaterial().getNome());
                        sheet.addCell(label);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }
                // Quantidade
                if (addquantidade) {
                    if (r.getQuantidade() > -1) {
                        number = new jxl.write.Number(coluna, linha, r.getQuantidade());
                        sheet.addCell(number);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Unidade de Medida
                if (addunidadeMedida) {
                    if (r.getMaterial().getUnidadeMedida() != null) {
                        label = new Label(coluna, linha, r.getMaterial().getUnidadeMedida().getSigla());
                        sheet.addCell(label);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Data de Aplicação
                if (adddataAplicacao) {
                    if (r.getRm().getDataAplicacao() != null) {
                        dateCell = new DateTime(coluna, linha, r.getRm().getDataAplicacao(), dateFormat);
                        sheet.addCell(dateCell);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Prazo para Compra / Recebimento
                if (addprazoCompraRecebimento) {
                    if (r.getRm().getDataAplicacao() != null && r.getRm().getDataRecebimento() != null) {
                        number = new jxl.write.Number(coluna, linha, Util.diferencaDias(r.getRm().getDataRecebimento(), r.getRm().getDataAplicacao()));
                        sheet.addCell(number);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Comprador
                if (addcomprador) {
                    if (r.getRm().getComprador() != null && r.getRm().getComprador().getNome() != null) {
                        label = new Label(coluna, linha, r.getRm().getComprador().getNome());
                        sheet.addCell(label);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Disciplina
                if (adddisciplina) {
                    if (r.getMaterial() != null && r.getMaterial().getTipoMaterial() != null) {
                        label = new Label(coluna, linha, r.getMaterial().getTipoMaterial().getDescricao());
                        sheet.addCell(label);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Nivel Prioridade
                if (addnivelPrioridade) {
                    if (r.getRm() != null && r.getRm().getPrioridade() != null) {
                        label = new Label(coluna, linha, r.getRm().getPrioridade().getDescricao());
                        sheet.addCell(label);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Observação
                if (addobservacao) {
                    if (r.getRm().getObservacao() != null) {
                        label = new Label(coluna, linha, r.getRm().getObservacao());
                        sheet.addCell(label);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Requisitante
                if (addrequisitante) {
                    if (r.getRm().getRequisitante() != null && r.getRm().getRequisitante().getNome() != null) {
                        label = new Label(coluna, linha, r.getRm().getRequisitante().getNome());
                        sheet.addCell(label);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Cadastrante
                if (addcadastrante) {
                    if (r.getRm().getUsuario() != null && r.getRm().getUsuario().getPessoa() != null) {
                        label = new Label(coluna, linha, r.getRm().getUsuario().getPessoa().getNome());
                        sheet.addCell(label);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Nº Pedido da Compra
                if (addnumeroPedidoCompra) {
                    if (r.getNumeroPedidoCompra() != null) {
                        label = new Label(coluna, linha, r.getNumeroPedidoCompra());
                        sheet.addCell(label);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Data (de Compra)
                if (adddata) {
                    if (r.getDataCompra() != null) {
                        dateCell = new DateTime(coluna, linha, r.getDataCompra(), dateFormat);
                        sheet.addCell(dateCell);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Cód. do Fornecedor
                if (addcodigoFornecedor) {
                    if (r.getFornecedor() != null) {
                        label = new Label(coluna, linha, r.getFornecedor().getCodigo());
                        sheet.addCell(label);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Nome do Fornecedor
                if (addnomeFornecedor) {
                    if (r.getFornecedor() != null) {
                        label = new Label(coluna, linha, r.getFornecedor().getNome());
                        sheet.addCell(label);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Tempo Real de Compra
                if (addtempoRealCompra) {
                    if (r.getDataCompra() != null && r.getRm().getDataRecebimento() != null) {
                        number = new jxl.write.Number(coluna, linha, Util.diferencaDias(r.getRm().getDataRecebimento(), r.getDataCompra()));
                        sheet.addCell(number);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Previsão de chegada
                if (addprevisaoChegada) {
                    if (r.getDataPrevisaoChegada() != null) {
                        dateCell = new DateTime(coluna, linha, r.getDataPrevisaoChegada(), dateFormat);
                        sheet.addCell(dateCell);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Quantidade Recebida.
                if (addqtdeRecebida) {
                    if (recebimentos != null && recebimentos.size() > 0) {
                        number = new jxl.write.Number(coluna, linha, recebimentos.get(0).getQuantidade());
                        sheet.addCell(number);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Saldo.
                if (addsaldo) {
                    if (recebimentos != null && recebimentos.size() > 0) {
                        double qtd = recebimentos.get(0).getQuantidade();
                        double saldo = r.getQuantidade() - qtd;
                        number = new jxl.write.Number(coluna, linha, saldo);
                        sheet.addCell(number);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Nº da Nota Fiscal
                if (addnumeroNotaFiscal) {
                    if (recebimentos != null && recebimentos.size() > 0) {
                        label = new Label(coluna, linha, recebimentos.get(0).getNumeroNotaFiscal());
                        sheet.addCell(label);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Data de Emissão
                if (adddataEmissaoNF) {
                    if (recebimentos != null && recebimentos.size() > 0) {
                        dateCell = new DateTime(coluna, linha, recebimentos.get(0).getDataEmissaoNf(), dateFormat);
                        sheet.addCell(dateCell);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Lead-Time
                if (addleadTime) {
                    if (r.getDataCompra() != null && recebimentos != null && recebimentos.size() > 0) {
                        number = new jxl.write.Number(coluna, linha, Util.diferencaDias(r.getDataCompra(), recebimentos.get(0).getDataEmissaoNf()));
                        sheet.addCell(number);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Data de Recebimento do Material
                if (adddataRecebimentoMaterial) {
                    if (recebimentos != null && recebimentos.size() > 0) {
                        dateCell = new DateTime(coluna, linha, recebimentos.get(0).getDataRecebimentoMaterial(), dateFormat);
                        sheet.addCell(dateCell);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Data de Retirada do Material
                if (adddataUltimaRetiradaMaterial) {
                    if (lastRetirada != null && lastRetirada.getDataRetirada() != null) {
                        dateCell = new DateTime(coluna, linha, lastRetirada.getDataRetirada(), dateFormat);
                        sheet.addCell(dateCell);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Lead-Time Total
                if (addleadTimeTotal) {
                    if (recebimentos != null && recebimentos.size() > 0) {
                        number = new jxl.write.Number(coluna, linha, Util.diferencaDias(recebimentos.get(0).getDataEmissaoNf(), recebimentos.get(0).getDataRecebimentoMaterial()));
                        sheet.addCell(number);
                        coluna++;
                    } else {
                        coluna++;
                    }
                }

                // Anotações
                if (addanotacao) {
                    label = new Label(coluna, linha, "");
                    sheet.addCell(label);
                }
                coluna++;

                /* ###### Controla os recebimentos parciais. ###### */
                if (recebimentos != null && recebimentos.size() > 1) {
                    for (int j = 1; j < recebimentos.size(); j++) {
                        coluna = 0;
                        linha++;

                        // Código
                        if (addcodigo) {
                            if (r.getRm() != null) {
                                label = new Label(coluna, linha, r.getRm().getNumeroRm() + "-" + contItem);
                                sheet.addCell(label);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // STATUS - CONTROLE DO STATUS DA RM.
                        if (addstatus) {
                            if (r.getRm() != null) {

                                Double qtdRecebido = r.getQtdRecebido();
                                Double qtdRetirado = r.getQtdRetirado();
                                // CANCELADO.
                                if (r.getRm() != null && r.getRm().getDataCancelamento() != null) {
                                    label = new Label(coluna, linha, "CANCELADO");
                                    sheet.addCell(label);
                                    coluna++;
                                } else if (r.getRm().getDataAceiteComprador() == null) {
                                    // SEM COMPRADOR
                                    label = new Label(coluna, linha, "EM ANÁLISE");
                                    sheet.addCell(label);
                                    coluna++;
                                } else if (r.getDataCompra() == null && qtdRecebido.equals(0d)) {
                                    // Comprador indicado, porém a compra não foi realizada
                                    label = new Label(coluna, linha, "EM COMPRA");
                                    sheet.addCell(label);
                                    coluna++;
                                } else if (qtdRecebido.equals(0d)) {

                                    // Compra realizada, porém o material ainda nao foi recebido.
                                    label = new Label(coluna, linha, "PENDENTE DE RECEBIMENTO");
                                    sheet.addCell(label);
                                    coluna++;
                                } else if (qtdRecebido > 0 && qtdRetirado > 0 && (qtdRecebido - qtdRetirado) == 0) {

                                    // Compra realizada e material recebido.
                                    label = new Label(coluna, linha, "RETIRADO");
                                    sheet.addCell(label);
                                    coluna++;
                                } else if (qtdRecebido > 0 && qtdRetirado > 0 && (qtdRecebido - qtdRetirado) > 0) {
                                    // Compra realizada, porém o material foi recebido parcialmente
                                    label = new Label(coluna, linha, "RETIRADO PARCIALMENTE");
                                    sheet.addCell(label);
                                    coluna++;
                                } else if (qtdRecebido > 0 && (r.getQuantidade() - qtdRecebido) == 0) {

                                    // Compra realizada e material recebido.
                                    label = new Label(coluna, linha, "RECEBIDO");
                                    sheet.addCell(label);
                                    coluna++;
                                } else if (qtdRecebido > 0 && (r.getQuantidade() - qtdRecebido) > 0) {

                                    // Compra realizada, porém o material foi recebido parcialmente
                                    label = new Label(coluna, linha, "RECEBIDO PARCIALMENTE");
                                    sheet.addCell(label);
                                    coluna++;
                                } else {
                                    coluna++;
                                }
                            } else {
                                coluna++;
                            }
                        }

                        if (addnumeroRm) {
                            // Nº da RM
                            if (r.getRm().getNumeroRm() != null) {
                                label = new Label(coluna, linha, r.getRm().getNumeroRm());
                                sheet.addCell(label);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Data de Emissão
                        if (adddataEmissao) {
                            if (r.getRm().getDataEmissao() != null) {
                                dateCell = new DateTime(coluna, linha, r.getRm().getDataEmissao(), dateFormat);
                                sheet.addCell(dateCell);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Data de Recebimento
                        if (adddataRecebimento) {
                            if (r.getRm().getDataRecebimento() != null) {
                                dateCell = new DateTime(coluna, linha, r.getRm().getDataRecebimento(), dateFormat);
                                sheet.addCell(dateCell);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Item
                        if (additem) {
                            if (r.getMaterial().getNome() != null) {
                                number = new jxl.write.Number(coluna, linha, contItem);
                                sheet.addCell(number);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Descrição do Item
                        if (adddescricaoItem) {
                            if (r.getMaterial() != null && r.getMaterial().getNome() != null) {
                                label = new Label(coluna, linha, r.getMaterial().getNome());
                                sheet.addCell(label);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Quantidade
                        if (addquantidade) {
                            if (r.getQuantidade() > -1) {
                                number = new jxl.write.Number(coluna, linha, r.getQuantidade(), numberFormat);
                                sheet.addCell(number);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Unidade de Medida
                        if (addunidadeMedida) {
                            if (r.getMaterial().getUnidadeMedida() != null) {
                                label = new Label(coluna, linha, r.getMaterial().getUnidadeMedida().getSigla());
                                sheet.addCell(label);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Data de Aplicação
                        if (adddataAplicacao) {
                            if (r.getRm().getDataAplicacao() != null) {
                                dateCell = new DateTime(coluna, linha, r.getRm().getDataAplicacao(), dateFormat);
                                sheet.addCell(dateCell);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Prazo para Compra / Recebimento
                        if (addprazoCompraRecebimento) {
                            if (r.getRm().getDataAplicacao() != null && r.getRm().getDataRecebimento() != null) {
                                number = new jxl.write.Number(coluna, linha, Util.diferencaDias(r.getRm().getDataRecebimento(), r.getRm().getDataAplicacao()));
                                sheet.addCell(number);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Comprador
                        if (addcomprador) {
                            if (r.getRm().getComprador() != null && r.getRm().getComprador().getNome() != null) {
                                label = new Label(coluna, linha, r.getRm().getComprador().getNome());
                                sheet.addCell(label);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Disciplina
                        if (adddisciplina) {
                            if (r.getMaterial() != null && r.getMaterial().getTipoMaterial() != null) {
                                label = new Label(coluna, linha, r.getMaterial().getTipoMaterial().getDescricao());

                                sheet.addCell(label);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Nivel Prioridade
                        if (addnivelPrioridade) {
                            if (r.getRm() != null && r.getRm().getPrioridade() != null) {
                                label = new Label(coluna, linha, r.getRm().getPrioridade().getDescricao());
                                sheet.addCell(label);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Observação
                        if (addobservacao) {
                            if (r.getRm().getObservacao() != null) {
                                label = new Label(coluna, linha, r.getRm().getObservacao());
                                sheet.addCell(label);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Requisitante
                        if (addrequisitante) {
                            if (r.getRm().getRequisitante() != null && r.getRm().getRequisitante().getNome() != null) {
                                label = new Label(coluna, linha, r.getRm().getRequisitante().getNome());
                                sheet.addCell(label);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Cadastrante
                        if (addcadastrante) {
                            if (r.getRm().getUsuario() != null && r.getRm().getUsuario() != null) {
                                label = new Label(coluna, linha, r.getRm().getUsuario().getPessoa().getNome());
                                sheet.addCell(label);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Nº Pedido da Compra
                        if (addnumeroPedidoCompra) {
                            if (r.getNumeroPedidoCompra() != null) {
                                label = new Label(coluna, linha, r.getNumeroPedidoCompra());
                                sheet.addCell(label);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Data (de Compra)
                        if (adddata) {
                            if (r.getDataCompra() != null) {
                                dateCell = new DateTime(coluna, linha, r.getDataCompra(), dateFormat);
                                sheet.addCell(dateCell);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Cód. do Fornecedor
                        if (addcodigoFornecedor) {
                            if (r.getFornecedor() != null) {
                                label = new Label(coluna, linha, r.getFornecedor().getCodigo());
                                sheet.addCell(label);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Nome do Fornecedor
                        if (addnomeFornecedor) {
                            if (r.getFornecedor() != null) {
                                label = new Label(coluna, linha, r.getFornecedor().getNome());
                                sheet.addCell(label);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Tempo Real de Compra
                        if (addtempoRealCompra) {
                            if (r.getDataCompra() != null && r.getRm().getDataRecebimento() != null) {
                                number = new jxl.write.Number(coluna, linha, Util.diferencaDias(r.getRm().getDataRecebimento(), r.getDataCompra()));
                                sheet.addCell(number);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Previsão de chegada
                        if (addprevisaoChegada) {
                            if (r.getDataPrevisaoChegada() != null) {
                                dateCell = new DateTime(coluna, linha, r.getDataPrevisaoChegada(), dateFormat);
                                sheet.addCell(dateCell);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Quantidade Recebida.
                        if (addqtdeRecebida) {
                            if (recebimentos.size() > 0) {
                                number = new jxl.write.Number(coluna, linha, recebimentos.get(j).getQuantidade(), numberFormat);
                                sheet.addCell(number);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Saldo.
                        if (addsaldo) {
                            if (recebimentos.size() > 0) {
                                double saldo = r.getQuantidade();
                                for (int k = 0; k <= j; k++) {
                                    double qtd = recebimentos.get(k).getQuantidade();
                                    saldo = saldo - qtd;
                                }
                                number = new jxl.write.Number(coluna, linha, saldo);
                                sheet.addCell(number);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Nº da Nota Fiscal
                        if (addnumeroNotaFiscal) {
                            if (recebimentos.size() > 0) {
                                label = new Label(coluna, linha, recebimentos.get(j).getNumeroNotaFiscal());
                                sheet.addCell(label);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Data de Emissão
                        if (adddataEmissaoNF) {
                            if (recebimentos.size() > 0) {
                                dateCell = new DateTime(coluna, linha, recebimentos.get(j).getDataEmissaoNf(), dateFormat);
                                sheet.addCell(dateCell);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Lead-Time
                        if (addleadTime) {
                            if (r.getDataCompra() != null && recebimentos.size() > 0) {
                                number = new jxl.write.Number(coluna, linha, Util.diferencaDias(r.getDataCompra(), recebimentos.get(j).getDataEmissaoNf()));
                                sheet.addCell(number);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Data de Recebimento do Material
                        if (adddataRecebimentoMaterial) {
                            if (recebimentos.size() > 0) {
                                dateCell = new DateTime(coluna, linha, recebimentos.get(j).getDataRecebimentoMaterial(), dateFormat);
                                sheet.addCell(dateCell);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Data de Retirada do Material
                        if (adddataUltimaRetiradaMaterial) {
                            if (lastRetirada != null && lastRetirada.getDataRetirada() != null) {
                                dateCell = new DateTime(coluna, linha, lastRetirada.getDataRetirada(), dateFormat);
                                sheet.addCell(dateCell);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Lead-Time Total
                        if (addleadTimeTotal) {
                            if (recebimentos.size() > 0) {
                                number = new jxl.write.Number(coluna, linha, Util.diferencaDias(recebimentos.get(j).getDataEmissaoNf(), recebimentos.get(j).getDataRecebimentoMaterial()));
                                sheet.addCell(number);
                                coluna++;
                            } else {
                                coluna++;
                            }
                        }

                        // Anotações
                        if (addanotacao) {
                            label = new Label(coluna, linha, "");
                            sheet.addCell(label);
                        }
                        coluna++;
                    }
                }

                linha++;

                // aux = objeto RmMaterial atual.
                // Na próxima iteração, aux será o objeto RmMaterial
                // Anterior a r.
                aux = (RmMaterial) r;
            }
            /**
             * ***********************************************************
             */
            workbook.write();
            workbook.close();

        } catch (Exception e) {
            return Constantes.FALHA;
        }

        return Constantes.SUCESSO;
    }

    /**
     * <pre>
     * Carrega a lista de RmMaterialRecebimento para auxiliar no relatorio
     *
     * Foi feito para carregar todos e procurar os recebimentos dentro de um for
     * porque antes fazir uma consulta para cada RMMaterial ou seja 15.000 consultas
     * no total do relatorio.
     *
     * OPrdenado por: RmMaterial e DataRecebimentoMaterial
     *
     * <b>Author</b> : Jerry Adriano
     * <b>Data</b>   : 31/05/2012
     * </pre>
     */
    public void carregalistaRecebimentos() {
        List<Propriedade> prop = new ArrayList<>();

        String aliasRmMaterial = NxCriterion.montaAlias(RmMaterialRecebimento.ALIAS_CLASSE, RmMaterialRecebimento.RM_MATERIAL);

        try {
            prop.add(new Propriedade(RmMaterialRecebimento.RM_MATERIAL_RECEBIMENTO_ID));
            prop.add(new Propriedade(RmMaterialRecebimento.DATA_RECEBIMENTO_MATERIAL));
            prop.add(new Propriedade(RmMaterialRecebimento.NUMERO_NOTA_FISCAL));
            prop.add(new Propriedade(RmMaterialRecebimento.DATA_EMISSAO_NF));
            prop.add(new Propriedade(RmMaterialRecebimento.QUANTIDADE));

            prop.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));

            List<NxOrder> nxOrders = Arrays.asList(new NxOrder(RmMaterialRecebimento.RM_MATERIAL + "." + RmMaterial.RM_MATERIAL_ID, NxOrder.NX_ORDER.ASC), new NxOrder(RmMaterialRecebimento.DATA_RECEBIMENTO_MATERIAL, NxOrder.NX_ORDER.ASC));

            GenericDao<RmMaterialRecebimento> gDao = new GenericDao<>();
            listaRecebimentos = gDao.listarByFilter(prop, nxOrders, RmMaterialRecebimento.class, Constantes.NO_LIMIT, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <pre>
     * Carrega a lista de RmMaterialRetirada para auxiliar no relatorio
     *
     * Foi feito para carregar todos e procurar a ultima retirada dentro de um for
     * porque antes fazir uma consulta para cada RMMaterial ou seja 15.000 consultas
     * no total do relatorio.
     *
     * OPrdenado por: RmMaterial ASC e (DataRetirada DESC = pega o ultimo)
     *
     * <b>Author</b> : Jerry Adriano
     * <b>Data</b>   : 31/05/2012
     * </pre>
     *
     * @throws java.lang.Exception
     */
    public void carregalistaRetirada() throws Exception {
        try {
            String aliasRmMaterial = NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RM_MATERIAL);
            String aliasPessoa = NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RETIRADO_POR);

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterialRetirada.RM_MATERIAL_RETIRADA_ID));
            propriedades.add(new Propriedade(RmMaterialRetirada.QUANTIDADE));
            propriedades.add(new Propriedade(RmMaterialRetirada.DATA_RETIRADA));

            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasPessoa));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasPessoa));

            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));

            List<NxOrder> nxOrders = Arrays.asList(new NxOrder(RmMaterialRetirada.RM_MATERIAL + "." + RmMaterial.RM_MATERIAL_ID, NxOrder.NX_ORDER.ASC), new NxOrder(RmMaterialRetirada.DATA_RETIRADA, NxOrder.NX_ORDER.DESC));

            GenericDao<RmMaterialRetirada> gDao = new GenericDao<>();
            listaRetiradas = gDao.listarByFilter(propriedades, nxOrders, RmMaterialRetirada.class, Constantes.NO_LIMIT, null);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * <pre>
     * Retorna os Recebimentos da RmMaterial passada como parametro
     *
     * A Lista está Ordenado por: RmMaterial-ASC e DataRecebimentoMaterial-ASC
     *
     * <b>Author</b> : Jerry Adriano
     * <b>Data</b>   : 31/05/2012
     * <b>Param</b>  : Integer rmMaterialId
     * <b>Return</b> : List
     * </pre>
     *
     * @param rmMaterialId
     * @return
     */
    public List<RmMaterialRecebimento> listarRecebimentos(Integer rmMaterialId) {
        List<RmMaterialRecebimento> lista = new ArrayList<>();
        boolean achou = false;
        try {
            /**
             * Percorre a lista ate achar todos os recebimentos Se achou um
             * registro e o proximo não é da mesma RmMaterial então sai do for
             * poruqe não tem mais a necessidade de continuar já que está
             * ordenado por RmMateril.
             */
            for (RmMaterialRecebimento obj : listaRecebimentos) {
                if (obj.getRmMaterial().getRmMaterialId().equals(rmMaterialId)) {
                    lista.add(obj);
                    achou = true;
                } else if (achou) {
                    break;
                }
            }

        } catch (Exception e) {
            lista = null;
        }
        return lista;
    }

    /**
     * <pre>
     * Retorna a ultima Retirada do RmMaterial passada como parametro
     *
     * A Lista está Ordenado por: RmMaterial-ASC e DataRetirada-DESC
     *
     * <b>Author</b> : Jerry Adriano
     * <b>Data</b>   : 31/05/2012
     * <b>Param</b>  : Integer rmMaterialId
     * <b>Return</b> : RmMaterialRetirada
     * </pre>
     *
     * @param rmMaterialId
     * @return
     */
    public RmMaterialRetirada getLastRetirada(Integer rmMaterialId) {
        RmMaterialRetirada rmRet = null;
        try {
            /**
             * Percorre a lista ate achar todos os recebimentos Se achou um
             * registro e o proximo não é da mesma RmMaterial então sai do for
             * poruqe não tem mais a necessidade de continuar já que está
             * ordenado por RmMateril.
             */
            for (RmMaterialRetirada obj : listaRetiradas) {
                if (obj.getRmMaterial().getRmMaterialId().equals(rmMaterialId)) {
                    rmRet = obj;
                    break;
                }
            }

        } catch (Exception e) {
            rmRet = null;
        }
        return rmRet;
    }

    /**
     * <pre>
     * Verifica quais campos vão ser adicionados no relatorio
     *
     * <b>Author</b> : Jerry Adriano
     * <b>Data</b>   : 12/06/2012
     * <b>Param</b>  : String[] campos
     * <b>Return</b> :
     * </pre>
     *
     * @param campos
     */
    public void setaCamposRelatorio(String[] campos) {

        try {
            for (String campo : campos) {
                if (null != campo) {
                    switch (campo) {
                        case "codigo":
                            addcodigo = true;//Códigos
                            break;
                        case "status":
                            addstatus = true;
                            break;
                        case "numeroRm":
                            addnumeroRm = true;
                            break;
                        case "dataEmissao":
                            adddataEmissao = true;
                            break;
                        case "dataRecebimento":
                            adddataRecebimento = true;
                            break;
                        case "item":
                            additem = true;
                            break;
                        case "descricaoItem":
                            adddescricaoItem = true;
                            break;
                        case "quantidade":
                            addquantidade = true;
                            break;
                        case "unidadeMedida":
                            addunidadeMedida = true;
                            break;
                        case "dataAplicacao":
                            adddataAplicacao = true;
                            break;
                        case "prazoCompraRecebimento":
                            addprazoCompraRecebimento = true;
                            break;
                        case "comprador":
                            addcomprador = true;
                            break;
                        case "disciplina":
                            adddisciplina = true;
                            break;
                        case "nivelPrioridade":
                            addnivelPrioridade = true;
                            break;
                        case "observacao":
                            addobservacao = true;
                            break;
                        case "requisitante":
                            addrequisitante = true;
                            break;
                        case "cadastrante":
                            addcadastrante = true;
                            break;
                        case "numeroPedidoCompra":
                            addnumeroPedidoCompra = true;
                            break;
                        case "data":
                            adddata = true;
                            break;
                        case "codigoFornecedor":
                            addcodigoFornecedor = true;
                            break;
                        case "nomeFornecedor":
                            addnomeFornecedor = true;
                            break;
                        case "tempoRealCompra":
                            addtempoRealCompra = true;
                            break;
                        case "previsaoChegada":
                            addprevisaoChegada = true;
                            break;
                        case "qtdeRecebida":
                            addqtdeRecebida = true;
                            break;
                        case "saldo":
                            addsaldo = true;
                            break;
                        case "numeroNotaFiscal":
                            addnumeroNotaFiscal = true;
                            break;
                        case "dataEmissaoNF":
                            adddataEmissaoNF = true;
                            break;
                        case "leadTime":
                            addleadTime = true;
                            break;
                        case "dataRecebimentoMaterial":
                            adddataRecebimentoMaterial = true;
                            break;
                        case "dataUltimaRetiradaMaterial":
                            adddataUltimaRetiradaMaterial = true;
                            break;
                        case "leadTimeTotal":
                            addleadTimeTotal = true;
                            break;
                        case "anotacao":
                            addanotacao = true;
                            break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
