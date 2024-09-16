package br.com.nextage.rmaweb.controller.estoque;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import br.com.nextage.rmaweb.controller.ConsultaMaterialSap;
import br.com.nextage.rmaweb.controller.DepositoSap;
import br.com.nextage.rmaweb.controller.MaterialSap;
import br.com.nextage.rmaweb.controller.ResponseConsultaInformacoesSap;
import br.com.nextage.rmaweb.service.ConsultaEstoqueMaterialSap;
import br.com.nextage.rmaweb.service.ConsultaEstoqueMaterialSapResponse;
import br.com.nextage.rmaweb.service.EstoqueMateriaisSap;
import br.com.nextage.rmaweb.service.requisicao.compra.SapServiceRequisicaoCompraClient;

import static br.com.nextage.rmaweb.util.UtilsApp.stringDate;

/**
 * @author adriano.gomes
 */
@Path("estoqueMaterial")
public class BuscarEstoqueMaterialController {

    @Context
    HttpServletRequest request;
    @Inject
    private SapServiceRequisicaoCompraClient sapServiceClient;


    @POST
    @Path("consultaMateriaisSimilaresSap")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseConsultaInformacoesSap consultanformacoesMaterialSap(ConsultaMaterialSap consultaMaterialSap) {

        ConsultaEstoqueMaterialSap requestSap = new ConsultaEstoqueMaterialSap();
        requestSap.setCentro(consultaMaterialSap.getCentro());
        requestSap.setMateriais(consultaMaterialSap.getMateriais());

        ConsultaEstoqueMaterialSapResponse retornoSap = sapServiceClient.zglRmaBuscaEstoqueMaterial(requestSap);
        List<EstoqueMateriaisSap> estoqueMateriaisSap = retornoSap.getEstoqueMateriaisSap();

        BigDecimal totalMateriaisEmEstoque = estoqueMateriaisSap.stream()
                .map(EstoqueMateriaisSap::getQuantidadeEstoque)
                .reduce(BigDecimal::add)
                .get();


        MaterialSap materialSap = new MaterialSap();
        materialSap.setCodigo(consultaMaterialSap.getMateriais().get(0));
        materialSap.setTotalEstoque(totalMateriaisEmEstoque);

        Optional<String> deposito = Optional.ofNullable(consultaMaterialSap.getDeposito());

        if(deposito.isPresent()){
            Optional<EstoqueMateriaisSap> estoqueMaterialSap = estoqueMateriaisSap
              .stream().filter(d -> consultaMaterialSap.getDeposito().equals(d.getNomeDeposito())).findFirst();
            if (estoqueMaterialSap.isPresent()) {
                DepositoSap depositoSap = new DepositoSap();
                depositoSap.setId(estoqueMaterialSap.get().getNomeDeposito());
                depositoSap.setQuantidade(estoqueMaterialSap.get().getQuantidadeEstoque());
                materialSap.setDepositos(Collections.singletonList(depositoSap));
            }
        }


        ResponseConsultaInformacoesSap responseConsultaInformacoesSap = new ResponseConsultaInformacoesSap();
        responseConsultaInformacoesSap.setCentro(requestSap.getCentro());
        responseConsultaInformacoesSap.setMaterial(materialSap);


        return responseConsultaInformacoesSap;
    }

    @POST
    @Path("validarEstoqueMaterial")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BuscarEstoqueMaterialResponse validarEstoqueMaterial(List<BuscarEstoqueMaterialRequest> lista) {
        BuscarEstoqueMaterialResponse response = new BuscarEstoqueMaterialResponse();

        for (BuscarEstoqueMaterialRequest mat :lista) {
            ConsultaEstoqueMaterialSap requestSap = new ConsultaEstoqueMaterialSap();
            requestSap.setCentro(mat.getCentro());
            requestSap.setDeposito(mat.getCodigoDeposito());
            requestSap.getMateriais().add(mat.getCodigoMaterial());

            ConsultaEstoqueMaterialSapResponse retornoSap = sapServiceClient.zglRmaBuscaEstoqueMaterial(requestSap);
            List<EstoqueMateriaisSap> estoqueMateriaisSap = retornoSap.getEstoqueMateriaisSap();

            for (EstoqueMateriaisSap estoque :estoqueMateriaisSap) {
                BigDecimal quantidadeEstoque = estoque.getQuantidadeEstoque() == null ? BigDecimal.ZERO.setScale(2): estoque.getQuantidadeEstoque().setScale(2);
                String codigoDeposito = estoque.getNomeDeposito();
                if(mat.getCodigoDeposito().equals(codigoDeposito)
                        && (quantidadeEstoque.equals(BigDecimal.ZERO.setScale(2)) || quantidadeEstoque.compareTo(mat.getQuantidade()) == -1)) {
                    response.setMaterial(mat.getNomeMaterial());
                    response.setMensagem( mat.getNomeMaterial() + " sem estoque disponível para o depósito selecionado (" + mat.getNomeDeposito() + ").");
                    return response;
                }
            }
        }

        response.setMensagem("Validação realizada com sucesso.");

        return response;
    }

}
