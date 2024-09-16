package br.com.nextage.rmaweb.ws.rmaweb;

import br.com.nextage.rmaweb.service.integracao.SincEquipamentoService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.MaterialDepositoVo;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author l.pordeus
 */
@WebService(serviceName = "CpwebWs")
public class  CpwebWs {

    /**
     * Da entrada de materiais em um determinado deposito
     *
     * @param materialDepositoVo
     * @return
     */
    @WebMethod(operationName = "entradaMaterial")
    public br.com.nextage.util.Info entradaMaterial(@WebParam(name = "materialDepositoVo") MaterialDepositoVo materialDepositoVo) {
        br.com.nextage.util.Info info = null;
        try {
            SincEquipamentoService sincEquipamentoService = new SincEquipamentoService();
            info = sincEquipamentoService.entradaMaterial(materialDepositoVo, false);
        } catch (Exception e) {
            info.setCodigo(br.com.nextage.util.Info.INFO_002);
            info.setTipo(br.com.nextage.util.Info.TIPO_MSG_DANGER);
            info.setErro(e.getMessage());
            info.setMensagem(Constantes.REG_SALVO_ERRO_I18N);
        }
        return info;
    }

    /**
     * Da saida de materiais em um determinado deposito
     *
     * @param materialDepositoVo
     * @return
     */
    @WebMethod(operationName = "saidaMaterial")
    public br.com.nextage.util.Info saidaMaterial(@WebParam(name = "materialDepositoVo") MaterialDepositoVo materialDepositoVo) {
        br.com.nextage.util.Info info = null;
        try {
            SincEquipamentoService sincEquipamentoService = new SincEquipamentoService();
            info = sincEquipamentoService.saidaMaterial(materialDepositoVo);
        } catch (Exception e) {
            info.setCodigo(br.com.nextage.util.Info.INFO_002);
            info.setTipo(br.com.nextage.util.Info.TIPO_MSG_DANGER);
            info.setErro(e.getMessage());
            info.setMensagem(Constantes.REG_SALVO_ERRO_I18N);
        }
        return info;
    }
}
