package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.rmaweb.vo.integracao.MensagemIntegracao;
import br.com.nextage.util.Info;

/**
 * @author l.pordeus
 */
public class MensagemIntegracaoService {

    /**
     * Cria mensagem de integracao de acordo com o objeto Info passado por
     * parâmetro
     *
     * @param info
     * @return
     */
    public static MensagemIntegracao criaMensagemIntegracao(Info info) {
        MensagemIntegracao mensagemIntegracao = null;
        try {
            mensagemIntegracao = new MensagemIntegracao();
            if (info.getErro() != null) {
                mensagemIntegracao.setTipoMensagem("E");
                mensagemIntegracao.setMensagem(info.getMensagem());
            } else {
                mensagemIntegracao.setTipoMensagem("S");
                mensagemIntegracao.setMensagem(info.getMensagem());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mensagemIntegracao;
    }
}
