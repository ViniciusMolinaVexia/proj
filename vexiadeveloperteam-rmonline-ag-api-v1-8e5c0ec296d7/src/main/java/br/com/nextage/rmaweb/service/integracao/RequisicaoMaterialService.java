package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.dao.RequisicaoCompraDAO;
import br.com.nextage.rmaweb.dao.RequisicaoRecebimentoDAO;
import br.com.nextage.rmaweb.entitybean.RequisicaoCompra;
import br.com.nextage.rmaweb.entitybean.RequisicaoRecebimento;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.vo.integracao.CompraVo;
import br.com.nextage.rmaweb.ws.receberentradamaterial.ReceberEntradaMaterialRequest;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Date;

public class RequisicaoMaterialService {

    private static final Logger LOG = Logger.getLogger(RequisicaoMaterialService.class);

    private RequisicaoCompraDAO compraDAO;

    private RequisicaoRecebimentoDAO recebimentoDAO;

    public RequisicaoMaterialService() {
        this.compraDAO = new RequisicaoCompraDAO();
        this.recebimentoDAO = new RequisicaoRecebimentoDAO();
    }

    public void savePurchaseRequestIfNotExists(RmMaterial rmMaterial, CompraVo compraVo, GenericDao<?> genericDao) throws Exception {
        if (compraDAO.exists(compraVo)) {
            LOG.info("Documento de compra já inserido: " + compraVo.toString());
            return;
        }


        RequisicaoCompra requisicaoCompra = new RequisicaoCompra();
        requisicaoCompra.setQuantidade(compraVo.getQuantidade());
        requisicaoCompra.setDataCadastro(new Date());
        requisicaoCompra.setMaterial(rmMaterial.getMaterial());
        requisicaoCompra.setRmMaterial(rmMaterial);
        requisicaoCompra.setRm(rmMaterial.getRm());
        requisicaoCompra.setItemRmSAP(compraVo.getItemRmSap());
        requisicaoCompra.setNumeroRmSAP(compraVo.getRmSap());
        requisicaoCompra.setDocumentoCompra(compraVo.getDocumentoCompra());
        compraDAO.persist(requisicaoCompra, genericDao);
    }

    public void saveMaterialReceivedIfNotExists(RmMaterial rmMaterial, ReceberEntradaMaterialRequest entradaMaterialRequest, GenericDao<?> genericDao) throws Exception {
        if (recebimentoDAO.exists(entradaMaterialRequest)) {
            LOG.info("Documento de entrada já inserido: " + entradaMaterialRequest.toString());
            return;
        }


        RequisicaoRecebimento requisicaoRecebimento = new RequisicaoRecebimento();
        requisicaoRecebimento.setDataCadastro(new Date());
        requisicaoRecebimento.setDocumentoRecebimento(entradaMaterialRequest.getDocumentoEntrada());
        requisicaoRecebimento.setItemSAP(entradaMaterialRequest.getItemRmSap());
        requisicaoRecebimento.setNumeroSAP(entradaMaterialRequest.getRmSap());
        requisicaoRecebimento.setMaterial(rmMaterial.getMaterial());
        requisicaoRecebimento.setQuantidade(entradaMaterialRequest.getQuantidade());
        requisicaoRecebimento.setRm(rmMaterial.getRm());
        requisicaoRecebimento.setRmMaterial(rmMaterial);
        requisicaoRecebimento.setDataRecebimento(entradaMaterialRequest.getDataRecebimento());
        recebimentoDAO.persist(requisicaoRecebimento, genericDao);
    }

    public Long getCountRequestsByRmId(Integer rmId) {
        Long purchaseAmount = compraDAO.getAmountByRmId(rmId);
        Long materialReceivedAmount = recebimentoDAO.getAmountByRmId(rmId);
        return purchaseAmount;
    }
}
