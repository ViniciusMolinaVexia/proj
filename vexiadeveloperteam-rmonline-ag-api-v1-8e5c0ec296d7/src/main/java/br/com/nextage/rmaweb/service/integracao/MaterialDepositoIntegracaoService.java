package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.dao.MaterialDepositoSaidaDao;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.service.MaterialDepositoService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.MaterialDepositoVo;
import br.com.nextage.rmaweb.ws.sap.RetiradaMateriaisSapService;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author l.pordeus
 */
public class MaterialDepositoIntegracaoService {

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    /**
     * Adiciona uma quantidade material ao depoisito, incrementa seu estoue e
     * cria historico de entrada de material.
     *
     * @param material
     * @param deposito
     * @param qtdeInserida
     * @return
     * @throws Exception
     */
    public Info entrataDeposito(Material material, Deposito deposito, Double qtdeInserida, Boolean enviaCp, MaterialDepositoVo depositoVo) throws Exception {
        Info info = new Info();
        try {
            MaterialDeposito matDep;
            //Adiciona a quantidade no deposito
            GenericDao<MaterialDeposito> genericDao = new GenericDao<>();

            NxCriterion nxCriterion;
            nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDeposito.MATERIAL, material, Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDeposito.DEPOSITO, deposito, Filtro.EQUAL)));

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
            propriedades.add(new Propriedade(MaterialDeposito.DEPOSITO));
            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL));
            propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));

            matDep = genericDao.selectUnique(propriedades, MaterialDeposito.class, nxCriterion);

            if (matDep == null) {
                matDep = new MaterialDeposito(-1);
                matDep.setMaterial(material);
                matDep.setDeposito(deposito);
                matDep.setQuantidade(qtdeInserida);
            } else {
                matDep.setQuantidade(matDep.getQuantidade() + qtdeInserida);
            }

            //Salva o material no deposito
            MaterialDepositoService mDS = new MaterialDepositoService();
            //cria o Objeto MaterialDepositoEntrada (historico de entrada de material)
            MaterialDepositoEntrada objMDE = new MaterialDepositoEntrada();
            objMDE.setDataEntrada(new Date());
            //Se recebeu do CP o mesmo atribui o valor no metodo anterior enviaCP como falso, então, se for falso, eu seto uma
            //observação apropriada da cautela.
            if (enviaCp != null && enviaCp == false) {
                objMDE.setObservacao(Constantes.ENTRADA_DE_MATERIAL_POR_DEV_CAUTELA);
            } else {
                objMDE.setObservacao(Constantes.ENTRADA_DE_MATERIAL_POR_RM);
            }
            objMDE.setQuantidade(qtdeInserida);
            objMDE.setOrigemMovimentacao(depositoVo.getOrigemMovimentacao());
            objMDE.setOrigemSincRegistro(depositoVo.getOrigemSincRegistro());
            objMDE.setMaterialDepositoEntradaId(-1);
            //Salva os objetos
            mDS.salvarEntrada(matDep, objMDE, enviaCp);

            info.setCodigo(Info.INFO_001);
            info.setTipo("S");
            info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
            info.setErro(null);

        } catch (Exception e) {
            info.setCodigo(Info.INFO_002);
            info.setTipo("E");
            info.setErro(e.getMessage());
            info.setMensagem(Constantes.REG_SALVO_ERRO_I18N);
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }

    /**
     * Retira uma quantidade material do deposito, decrementa seu estoque e cria
     * historico de entrada de material.
     *
     * @param material
     * @param deposito
     * @param qtdeRetirada
     * @param codigoRequisicao
     * @param retiradoPor
     * @return
     * @throws Exception
     */
    public Info retiradaDeposito(Material material, Deposito deposito, Double qtdeRetirada, String codigoRequisicao, String retiradoPor, Boolean reaproveitadosEpi, MaterialDepositoVo depositoVo) throws Exception {
        Info info = new Info();
        GenericDao<MaterialDeposito> genericDao = new GenericDao<>();
        Date data = new Date();

        Rm rm = null;
        RmMaterial rmMaterial = null;
        MaterialDeposito matDep;
        NxCriterion nxCriterion;
        RmMaterialStatus rmMaterialStatus;

        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService();
        RmaService rmaService = new RmaService();
        MaterialDepositoSaidaDao matDepSaidaDao = new MaterialDepositoSaidaDao();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(null));
        try {
            nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDeposito.MATERIAL, material, Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDeposito.DEPOSITO, deposito, Filtro.EQUAL)));

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
            propriedades.add(new Propriedade(MaterialDeposito.DEPOSITO));
            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL));
            propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));

            matDep = genericDao.selectUnique(propriedades, MaterialDeposito.class, nxCriterion);

            if (matDep == null) {
                matDep = new MaterialDeposito(-1);
                matDep.setMaterial(material);
                matDep.setDeposito(deposito);
                matDep.setQuantidade(qtdeRetirada);
            } else {
                matDep.setQuantidade(matDep.getQuantidade() - qtdeRetirada);
            }

            //Cria o Objeto MaterialDepositoEntrada (historico de entrada de material)
            MaterialDepositoSaida objMDS = null;
            Pessoa pessoa = rmaService.listarPessoa(retiradoPor, depositoVo.getPessoaEap());

            if (codigoRequisicao != null) {
                rm = rmaService.selectRmByNumero(codigoRequisicao);

                objMDS = rmaService.listarMaterialDepositoSaida(rm, material, deposito, null);
                rmMaterial = rmaService.listarRmMaterial(deposito, material, codigoRequisicao);
            }

            genericDao.beginTransaction();

            if (matDep.getMaterialDepositoId() > 0) {
                propriedades.clear();
                propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));

                genericDao.updateWithCurrentTransaction(matDep, propriedades);
            } else {
                genericDao.persistWithCurrentTransaction(matDep);
            }

            if (objMDS == null) {
                objMDS = new MaterialDepositoSaida();

                if (codigoRequisicao != null) {
                    objMDS.setRm(rm);
                }
                objMDS.setDataSaida(new Date());
                objMDS.setObservacao(Constantes.SAIDA_DE_MATERIAL_POR_CAUTELA);
                objMDS.setQuantidade(qtdeRetirada);
                objMDS.setMaterialDepositoSaidaId(-1);
                objMDS.setMaterialDeposito(matDep);
                objMDS.setRecebidoCp(true);
                objMDS.setOrigemMovimentacao(depositoVo.getOrigemMovimentacao());
                objMDS.setOrigemSincRegistro(depositoVo.getOrigemSincRegistro());

                if (objMDS.getRm() == null && objMDS.getNumeroProtocoloSaida() != null && objMDS.getNumeroProtocoloSaida() < 1) {
                    objMDS.setNumeroProtocoloSaida(matDepSaidaDao.numeroProtocoloSaidaMax());
                    if (objMDS.getNumeroProtocoloSaida() == null) {
                        objMDS.setNumeroProtocoloSaida(1);
                    } else {
                        objMDS.setNumeroProtocoloSaida(objMDS.getNumeroProtocoloSaida() + 1);
                    }
                }

                genericDao.persistWithCurrentTransaction(objMDS);
            } else {
                if (objMDS.getQuantidade() == qtdeRetirada) {
                    propriedades.clear();
                    propriedades.add(new Propriedade(MaterialDepositoSaida.RESERVA));
                    propriedades.add(new Propriedade(MaterialDepositoSaida.NUMERO_PROTOCOLO_SAIDA));
                    propriedades.add(new Propriedade(MaterialDepositoSaida.IS_RECEBIDO_CP));
                    propriedades.add(new Propriedade(MaterialDepositoSaida.ORIGEM_MOVIMENTACAO));
                    propriedades.add(new Propriedade(MaterialDepositoSaida.ORIGEM_SINC_REGISTRO));

                    objMDS.setReserva(Boolean.FALSE);
                    objMDS.setRecebidoCp(true);
                    objMDS.setOrigemMovimentacao(depositoVo.getOrigemMovimentacao());
                    objMDS.setOrigemSincRegistro(depositoVo.getOrigemSincRegistro());

                    if (objMDS.getRm() == null && objMDS.getNumeroProtocoloSaida() != null && objMDS.getNumeroProtocoloSaida() < 1) {
                        objMDS.setNumeroProtocoloSaida(matDepSaidaDao.numeroProtocoloSaidaMax());
                        if (objMDS.getNumeroProtocoloSaida() == null) {
                            objMDS.setNumeroProtocoloSaida(1);
                        } else {
                            objMDS.setNumeroProtocoloSaida(objMDS.getNumeroProtocoloSaida() + 1);
                        }
                    }

                    rmMaterialStatus = rmMaterialStatusService.listarStatusAtual(rmMaterial);
                    rmMaterialStatusService.finalizarStatus(rmMaterialStatus, genericDao, data);
                    rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_FINALIZADO, genericDao, data, Util.setParamsLabel(rb.getString("label_retirado_x_materiais"), qtdeRetirada, material.getUnidadeMedida().getSigla()), pessoa.getNome());

                    genericDao.updateWithCurrentTransaction(objMDS, propriedades);
                } else {
                    MaterialDepositoSaida materialDepositoSaida = new MaterialDepositoSaida();
                    materialDepositoSaida.setDataSaida(objMDS.getDataSaida());
                    materialDepositoSaida.setMaterialDeposito(matDep);
                    materialDepositoSaida.setMaterialDepositoSaidaReserva(objMDS.getMaterialDepositoSaidaReserva());
                    materialDepositoSaida.setNumeroProtocoloSaida(objMDS.getNumeroProtocoloSaida());
                    materialDepositoSaida.setObservacao(objMDS.getObservacao());
                    materialDepositoSaida.setReserva(Boolean.TRUE);
                    materialDepositoSaida.setRetiradoPor(pessoa);
                    materialDepositoSaida.setRm(rm);
                    materialDepositoSaida.setQuantidade(objMDS.getQuantidade() - qtdeRetirada);

                    propriedades.clear();
                    propriedades.add(new Propriedade(MaterialDepositoSaida.RESERVA));
                    propriedades.add(new Propriedade(MaterialDepositoSaida.QUANTIDADE));
                    propriedades.add(new Propriedade(MaterialDepositoSaida.NUMERO_PROTOCOLO_SAIDA));
                    propriedades.add(new Propriedade(MaterialDepositoSaida.IS_RECEBIDO_CP));
                    propriedades.add(new Propriedade(MaterialDepositoSaida.ORIGEM_MOVIMENTACAO));
                    propriedades.add(new Propriedade(MaterialDepositoSaida.ORIGEM_SINC_REGISTRO));


                    objMDS.setRecebidoCp(true);
                    objMDS.setOrigemMovimentacao(depositoVo.getOrigemMovimentacao());
                    objMDS.setOrigemSincRegistro(depositoVo.getOrigemSincRegistro());
                    objMDS.setQuantidade(qtdeRetirada);
                    objMDS.setReserva(Boolean.FALSE);

                    if (objMDS.getRm() == null && objMDS.getNumeroProtocoloSaida() != null && objMDS.getNumeroProtocoloSaida() < 1) {
                        objMDS.setNumeroProtocoloSaida(matDepSaidaDao.numeroProtocoloSaidaMax());
                        if (objMDS.getNumeroProtocoloSaida() == null) {
                            objMDS.setNumeroProtocoloSaida(1);
                        } else {
                            objMDS.setNumeroProtocoloSaida(objMDS.getNumeroProtocoloSaida() + 1);
                        }
                    }

                    rmMaterialStatus = rmMaterialStatusService.listarStatusAtual(rmMaterial);
                    rmMaterialStatusService.finalizarStatus(rmMaterialStatus, genericDao, data);
                    rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_RETIRADO_PARCIALMENTE, genericDao, data, Util.setParamsLabel(rb.getString("label_retirado_x_materiais"), qtdeRetirada, material.getUnidadeMedida().getSigla()), pessoa.getNome());

                    genericDao.persistWithCurrentTransaction(materialDepositoSaida);
                    genericDao.updateWithCurrentTransaction(objMDS, propriedades);
                }
            }

            if (codigoRequisicao != null) {
                RmMaterialRetirada rmMaterialRetirada = new RmMaterialRetirada();
                rmMaterialRetirada.setDataRetirada(new Date());
                rmMaterialRetirada.setNumeroProtocolo(objMDS.getNumeroProtocoloSaida());
                rmMaterialRetirada.setQuantidade(qtdeRetirada);
                rmMaterialRetirada.setRmMaterial(rmMaterial);
                rmMaterialRetirada.setRetiradoPor(pessoa);

                genericDao.persistWithCurrentTransaction(rmMaterialRetirada);

                if (!rm.getRequisitante().equals(pessoa)) {
                    rmaService.enviarEmailRequisitanteRetiradaMaterial(rm, pessoa, rmMaterial, qtdeRetirada);
                }
            }

            MaterialDepositoSaida teste = objMDS;

            if(reaproveitadosEpi == false) {
                RetiradaMateriaisSapService retiradaMateriaisService = new RetiradaMateriaisSapService();
                info = retiradaMateriaisService.enviarRetirada(objMDS);
            }

            genericDao.commitCurrentTransaction();

            info.setCodigo(Info.INFO_001);
            info.setTipo("S");
            info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
            info.setErro(null);

        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            info.setCodigo(Info.INFO_002);
            info.setTipo("E");
            info.setErro(e.getMessage());
            info.setMensagem(Constantes.REG_SALVO_ERRO_I18N);
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }
}
