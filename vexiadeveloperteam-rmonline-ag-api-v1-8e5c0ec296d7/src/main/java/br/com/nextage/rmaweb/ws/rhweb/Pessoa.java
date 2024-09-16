
package br.com.nextage.rmaweb.ws.rhweb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for pessoa complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pessoa">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pessoaId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="referencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telefone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoMo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoContrato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ramalAtual" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataNascimento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="sexo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="motivoDemissao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="anoEndereco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endereco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bairro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataAdmissao" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="desistenteReprovado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataDesistenteReprovado" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="motivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="indicadoPor" type="{http://ws.rhweb_ws.nextage.com.br/}pessoa" minOccurs="0"/>
 *         &lt;element name="dataRecrutamento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="localRecrutamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recrutador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataEncaminhadoExame" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataRecebimentoAso" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="horaRecebimentoAso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataRecebimentoCtps" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataDemissao" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataHomologacaoDemissao" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataEntregaDocsDp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataChegadaPrevista" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataLiberacaoCracha" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataEntradaArea" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="emailPessoal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailCcpr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataRetorno" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="tipoSanguineo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pcd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deficiencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomePai" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomeMae" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomeEscola" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="conselho" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroRegistro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataEmissaoRegistro" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cpf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgaoExpedidorRg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nrCarteiraTrabalho" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serieCarteiraTrabalho" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ufCarteiraTrabalho" type="{http://ws.rhweb_ws.nextage.com.br/}uf" minOccurs="0"/>
 *         &lt;element name="pisPasep" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fatorPlr" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="horistaMensalista" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="podeRequisitarVaga" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cidade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cep" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cidadeNascimento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cidadeOrigem" type="{http://ws.rhweb_ws.nextage.com.br/}cidade" minOccurs="0"/>
 *         &lt;element name="funcao" type="{http://ws.rhweb_ws.nextage.com.br/}funcao" minOccurs="0"/>
 *         &lt;element name="funcaoPb" type="{http://ws.rhweb_ws.nextage.com.br/}funcaoPetrobras" minOccurs="0"/>
 *         &lt;element name="subArea" type="{http://ws.rhweb_ws.nextage.com.br/}subArea" minOccurs="0"/>
 *         &lt;element name="vaga" type="{http://ws.rhweb_ws.nextage.com.br/}vaga" minOccurs="0"/>
 *         &lt;element name="uf" type="{http://ws.rhweb_ws.nextage.com.br/}uf" minOccurs="0"/>
 *         &lt;element name="ufEscola" type="{http://ws.rhweb_ws.nextage.com.br/}uf" minOccurs="0"/>
 *         &lt;element name="ufLocalTrabalho" type="{http://ws.rhweb_ws.nextage.com.br/}uf" minOccurs="0"/>
 *         &lt;element name="ufNascimento" type="{http://ws.rhweb_ws.nextage.com.br/}uf" minOccurs="0"/>
 *         &lt;element name="ufOrigem" type="{http://ws.rhweb_ws.nextage.com.br/}uf" minOccurs="0"/>
 *         &lt;element name="area" type="{http://ws.rhweb_ws.nextage.com.br/}area" minOccurs="0"/>
 *         &lt;element name="subordinacao" type="{http://ws.rhweb_ws.nextage.com.br/}subordinacao" minOccurs="0"/>
 *         &lt;element name="recados" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataCadastro" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataInicioTreinCcpr" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataFimTreinCcpr" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataTreinamentoPB" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataExperiencia30Dias" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataExperiencia60Dias" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="nacionalidade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataMobilizacaoFamilia" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataDesmobilizacaoFamilia" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataMudanca" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataTransferencia" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="pontoReferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pai" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mae" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataEmissaoCPF" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataEmissaoRG" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataEmissaoCTPS" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataEmissaoTituloEleitor" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="tituloEleitor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="zonaEleitoral" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="secaoEleitoral" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ufEmissaoCPF" type="{http://ws.rhweb_ws.nextage.com.br/}uf" minOccurs="0"/>
 *         &lt;element name="ufEmissaoRG" type="{http://ws.rhweb_ws.nextage.com.br/}uf" minOccurs="0"/>
 *         &lt;element name="cidadeVotacao" type="{http://ws.rhweb_ws.nextage.com.br/}cidade" minOccurs="0"/>
 *         &lt;element name="ufVotacao" type="{http://ws.rhweb_ws.nextage.com.br/}uf" minOccurs="0"/>
 *         &lt;element name="ufPisPasep" type="{http://ws.rhweb_ws.nextage.com.br/}uf" minOccurs="0"/>
 *         &lt;element name="numeroCNH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="categoriaCNH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataEmissaoCNH" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataValidadeCNH" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataSolicitacaoCV" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataRecebimentoCV" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataEnvioCVparaPB" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataRecebimentoCVdaPB" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="observacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataEntrevistaTecnica" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="aptoEntrevistaTecnica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataExameMedico" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="aptoExameMedico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clinica" type="{http://ws.rhweb_ws.nextage.com.br/}clinica" minOccurs="0"/>
 *         &lt;element name="alojar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroIf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataProtocoloIf" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="observacaoAdmissao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crachaCcpr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataAssinaturaContrato" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="autorizadoDirigir" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataAdmissaoAprovada" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataSolicitacaoCracha" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="observacaoIf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoContratacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="diasIntervaloViagem" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="mobilizacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobilizacaoPaga" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="empresaId" type="{http://ws.rhweb_ws.nextage.com.br/}empresa" minOccurs="0"/>
 *         &lt;element name="encarregado" type="{http://ws.rhweb_ws.nextage.com.br/}pessoa" minOccurs="0"/>
 *         &lt;element name="centroCusto" type="{http://ws.rhweb_ws.nextage.com.br/}centroCusto" minOccurs="0"/>
 *         &lt;element name="isSubcontratado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isIndicacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isEncarregado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="periodoTrabalho" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dadosBancarios" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataInicioProjeto" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="numeroCrachaProvisorio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataRecCrachaProvisorio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataBaixaCrachaProvisorio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="isRecrutador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isReadmissivel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataEnvioDocCrachaDp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="chaveBandejaEnvio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chaveBandejaRetorno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planseq" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataTransfSaida" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="direitoPlr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="treinamentoIntegracao" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="foto" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="nomeCracha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="viasCracha" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="dataPrevisaoDesmobilizacao" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="possuiContaCorrente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomeConjuge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cbo" type="{http://ws.rhweb_ws.nextage.com.br/}cbo" minOccurs="0"/>
 *         &lt;element name="dataInicioProcessoDemissao" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="indicado" type="{http://ws.rhweb_ws.nextage.com.br/}vagaRqsIndicado" minOccurs="0"/>
 *         &lt;element name="ut" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nrCracha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomeContato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telefoneContato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salarioBase" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="salarioMensal" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="salarioContratacao" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="salarioBaseCript" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salarioMensalCript" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contribuicaoSindical" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nrFichaRegistro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="liberadoServicoMilitar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observacaoAlojamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroBota" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroCalca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tamanhoCamisa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primeiroEmprego" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observacaoEntregaDocs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataEntregaDocs" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataSolicPassagemMob" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="eap" type="{http://ws.rhweb_ws.nextage.com.br/}eap" minOccurs="0"/>
 *         &lt;element name="dataAux1" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataAux2" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataAux3" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataAux4" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataAux5" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataAux6" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataAux7" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataAux8" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataAux9" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataAux10" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataAux11" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataAux12" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataAux13" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataAux14" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataAux15" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="textoAux1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textoAux2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textoAux3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textoAux4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textoAux5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textoAux6" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textoAux7" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textoAux8" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textoAux9" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textoAux10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textoAux11" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textoAux12" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textoAux13" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textoAux14" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textoAux15" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textoAux16" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textoAux17" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textoAux18" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textoAux19" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textoAux20" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textoAux21" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textoAux22" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textoAux23" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textoAux24" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textoAux25" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textAreaAux1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textAreaAux2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textAreaAux3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroAux1" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="numeroAux2" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="doubleAux1" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="doubleAux2" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="doubleAux3" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="doubleAux4" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="doubleAux5" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="doubleAux6" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="doubleAux7" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="doubleAux8" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="doubleAux9" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="doubleAux10" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="doubleAux11" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="doubleAux12" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="doubleAux13" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="doubleAux14" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="doubleAux15" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="raca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoDocumento" type="{http://ws.rhweb_ws.nextage.com.br/}tipoDocumento" minOccurs="0"/>
 *         &lt;element name="vwRelPessoa" type="{http://ws.rhweb_ws.nextage.com.br/}vwRelPessoa" minOccurs="0"/>
 *         &lt;element name="statusIf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="horaChegadaAeroporto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aeroportoChegada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chegouAeroporto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="responsavelDocDesligamento" type="{http://ws.rhweb_ws.nextage.com.br/}pessoa" minOccurs="0"/>
 *         &lt;element name="areaRh" type="{http://ws.rhweb_ws.nextage.com.br/}areaRh" minOccurs="0"/>
 *         &lt;element name="planoHorarioTrabalho" type="{http://ws.rhweb_ws.nextage.com.br/}planoHorarioTrabalho" minOccurs="0"/>
 *         &lt;element name="motivoAdmissao" type="{http://ws.rhweb_ws.nextage.com.br/}motivoAdmissao" minOccurs="0"/>
 *         &lt;element name="unidadeOrganizacional" type="{http://ws.rhweb_ws.nextage.com.br/}unidadeOrganizacional" minOccurs="0"/>
 *         &lt;element name="conhecidoComo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paisNacionalidade" type="{http://ws.rhweb_ws.nextage.com.br/}pais" minOccurs="0"/>
 *         &lt;element name="estadoCivilId" type="{http://ws.rhweb_ws.nextage.com.br/}estadoCivil" minOccurs="0"/>
 *         &lt;element name="racaId" type="{http://ws.rhweb_ws.nextage.com.br/}raca" minOccurs="0"/>
 *         &lt;element name="pais" type="{http://ws.rhweb_ws.nextage.com.br/}pais" minOccurs="0"/>
 *         &lt;element name="complemento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ddd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataEmissaoPisPasep" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="nuCertificadoMilitar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="categoriaCertificadoMilitar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="especieCertificadoMilitar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nuPassaporte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataEmissaoPassaporte" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="paisNaturalizado" type="{http://ws.rhweb_ws.nextage.com.br/}pais" minOccurs="0"/>
 *         &lt;element name="dataValidadePassaporte" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="localEmissaoPassaporte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nuIdentificacaoTrabalhador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nuCartaoNacionalSaude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sgConselhoRegional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ufConselhoRegional" type="{http://ws.rhweb_ws.nextage.com.br/}uf" minOccurs="0"/>
 *         &lt;element name="nuIdentidadeEstrangeiro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataChegadaBrasil" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="conjugueBrasileiro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nuVisto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tpVisto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataEmissaoVisto" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="enderecoRecrutamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroRecrutamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="complementoRecrutamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bairroRecrutamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cidadeRecrutamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cepRecrutamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ufRecrutamento" type="{http://ws.rhweb_ws.nextage.com.br/}uf" minOccurs="0"/>
 *         &lt;element name="paisRecrutamento" type="{http://ws.rhweb_ws.nextage.com.br/}pais" minOccurs="0"/>
 *         &lt;element name="dddRecrutamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telefoneRecrutamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="grauInstrucao" type="{http://ws.rhweb_ws.nextage.com.br/}escolaridade" minOccurs="0"/>
 *         &lt;element name="tipoEstabEnsino" type="{http://ws.rhweb_ws.nextage.com.br/}tipoEstabEnsino" minOccurs="0"/>
 *         &lt;element name="certificadoEscolar" type="{http://ws.rhweb_ws.nextage.com.br/}certificadoEscolar" minOccurs="0"/>
 *         &lt;element name="formacao" type="{http://ws.rhweb_ws.nextage.com.br/}formacao" minOccurs="0"/>
 *         &lt;element name="duracaoCurso" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="unidadeDuracaoCurso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mesConclusaoCurso" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="anoConclusaoCurso" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="instituicaoEnsino" type="{http://ws.rhweb_ws.nextage.com.br/}instituicaoEnsino" minOccurs="0"/>
 *         &lt;element name="paisInstituicao" type="{http://ws.rhweb_ws.nextage.com.br/}pais" minOccurs="0"/>
 *         &lt;element name="centroCustoEmissor" type="{http://ws.rhweb_ws.nextage.com.br/}centroCusto" minOccurs="0"/>
 *         &lt;element name="centroCustoReceptor" type="{http://ws.rhweb_ws.nextage.com.br/}centroCusto" minOccurs="0"/>
 *         &lt;element name="elementoPep" type="{http://ws.rhweb_ws.nextage.com.br/}pep" minOccurs="0"/>
 *         &lt;element name="nivelSalarial" type="{http://ws.rhweb_ws.nextage.com.br/}nivelSalarial" minOccurs="0"/>
 *         &lt;element name="horarioTrabalho" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="periodoExperiencia" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="periodoExperiencia1" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="periodoExperiencia2" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="exposicaoAgenteNocivo" type="{http://ws.rhweb_ws.nextage.com.br/}exposicaoAgenteNocivo" minOccurs="0"/>
 *         &lt;element name="vinculoEmpregaticio" type="{http://ws.rhweb_ws.nextage.com.br/}vinculoEmpregaticio" minOccurs="0"/>
 *         &lt;element name="aposentado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="categoriaTrabalhador" type="{http://ws.rhweb_ws.nextage.com.br/}categoriaTrabalhador" minOccurs="0"/>
 *         &lt;element name="adicionalInsalubridade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="porcentagemInsalubridade" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="vinculo" type="{http://ws.rhweb_ws.nextage.com.br/}vinculo" minOccurs="0"/>
 *         &lt;element name="adicionalPericulosidade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="porcentagemPericulosidade" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="sindicato" type="{http://ws.rhweb_ws.nextage.com.br/}sindicato" minOccurs="0"/>
 *         &lt;element name="valorMensalidadeSindical" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="codigoMensalidadeSindical" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="contribuicaoAssistencial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contribuicaoConfederativa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formaPagamento" type="{http://ws.rhweb_ws.nextage.com.br/}formaPagamento" minOccurs="0"/>
 *         &lt;element name="banco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroContaBancaria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="digitoContaCorrente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="grupoSanguineo" type="{http://ws.rhweb_ws.nextage.com.br/}grupoSanguineo" minOccurs="0"/>
 *         &lt;element name="fatorRh" type="{http://ws.rhweb_ws.nextage.com.br/}fatorRh" minOccurs="0"/>
 *         &lt;element name="codigoTomador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salaoRefeitorio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoRefeicao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="funcionarioRefeitorio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postoPagamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gremio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="superintendencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nivel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoComunicacao" type="{http://ws.rhweb_ws.nextage.com.br/}tipoComunicacao" minOccurs="0"/>
 *         &lt;element name="tipoComunicacaoRecrut" type="{http://ws.rhweb_ws.nextage.com.br/}tipoComunicacao" minOccurs="0"/>
 *         &lt;element name="tipoDemissao" type="{http://ws.rhweb_ws.nextage.com.br/}tipoDemissao" minOccurs="0"/>
 *         &lt;element name="eapMultiEmpresa" type="{http://ws.rhweb_ws.nextage.com.br/}eapMultiEmpresa" minOccurs="0"/>
 *         &lt;element name="subgrupo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataSolicitacaoDocumentacao" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataEnvioSap" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataAdmSap" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="necessitaTransporte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observacaoTransporte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observacaoRecontratacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="somaCargaHorariaHoras" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="somaCargaHorariaMinutos" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="valorTotalTreinamento" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="cboDescricao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="possuiFoto" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="beneficios" type="{http://ws.rhweb_ws.nextage.com.br/}beneficio" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="direitoTransporte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tempoAguarAso" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="dataEntradaUltimaEtapa" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fcFluxo" type="{http://ws.rhweb_ws.nextage.com.br/}fcFluxo" minOccurs="0"/>
 *         &lt;element name="statusContratacao" type="{http://ws.rhweb_ws.nextage.com.br/}statusContratacao" minOccurs="0"/>
 *         &lt;element name="nivelEncarregado" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="telefoneCompleto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="escolaridade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estadoCivil" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataAdmissao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataDemissao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataRecebimentoAso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataEncaminhadoExame" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataInicioTreinCcpr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataFimTreinCcpr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataTreinamentoPB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataEntregaDocsDp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataRecebimentoCtps" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataSolicitacaoCracha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataLiberacaoCracha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataTransferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataTransfSaida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataCadastro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataNascimento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataPrevisaoDesmobilizacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataRecrutamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataEntradaArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataDesistenteReprovado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataChegadaPrevista" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataExameMedico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataAdmissaoAprovada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataProtocoloIf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataEnvioDocCrachaDp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataEmissaoRG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataInicioProjeto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataMobilizacaoFamilia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataDesmobilizacaoFamilia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataMudanca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataEmissaoCTPS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataEmissaoTituloEleitor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataEmissaoCNH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataValidadeCNH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataEmissaoCPF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataEmissaoRegistro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataHomologacaoDemissao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataExperiencia30Dias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataExperiencia60Dias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataEntrevistaTecnica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataSolicitacaoCV" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataRecebimentoCV" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataEnvioCVparaPB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataRecebimentoCVdaPB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataInicioProcessoDemissao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataAssinaturaContrato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataBaixaCrachaProvisorio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataRecCrachaProvisorio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataSolicPassagemMob" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataEntregaDocs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataAux1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataAux2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataAux3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataAux4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataAux5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataAux6" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataAux7" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataAux8" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataAux9" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataAux10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataAux11" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataAux12" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataAux13" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataAux14" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataAux15" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataEmissaoPisPasep" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataEmissaoPassaporte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataValidadePassaporte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataChegadaBrasil" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataEmissaoVisto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataSolicitacaoDocumentacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataEntradaUltimaEtapa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataRetorno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataEnvioSap" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataAdmSap" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idade" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="diasDesdeDemissao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pessoa", propOrder = {
    "pessoaId",
    "referencia",
    "nome",
    "telefone",
    "tipoMo",
    "tipoContrato",
    "ramalAtual",
    "dataNascimento",
    "sexo",
    "motivoDemissao",
    "anoEndereco",
    "endereco",
    "bairro",
    "numero",
    "dataAdmissao",
    "desistenteReprovado",
    "dataDesistenteReprovado",
    "motivo",
    "indicadoPor",
    "dataRecrutamento",
    "localRecrutamento",
    "recrutador",
    "dataEncaminhadoExame",
    "dataRecebimentoAso",
    "horaRecebimentoAso",
    "dataRecebimentoCtps",
    "dataDemissao",
    "dataHomologacaoDemissao",
    "dataEntregaDocsDp",
    "dataChegadaPrevista",
    "dataLiberacaoCracha",
    "dataEntradaArea",
    "emailPessoal",
    "emailCcpr",
    "dataRetorno",
    "tipoSanguineo",
    "pcd",
    "deficiencia",
    "nomePai",
    "nomeMae",
    "nomeEscola",
    "conselho",
    "numeroRegistro",
    "dataEmissaoRegistro",
    "status",
    "cpf",
    "rg",
    "orgaoExpedidorRg",
    "nrCarteiraTrabalho",
    "serieCarteiraTrabalho",
    "ufCarteiraTrabalho",
    "pisPasep",
    "fatorPlr",
    "horistaMensalista",
    "podeRequisitarVaga",
    "cidade",
    "cep",
    "cidadeNascimento",
    "cidadeOrigem",
    "funcao",
    "funcaoPb",
    "subArea",
    "vaga",
    "uf",
    "ufEscola",
    "ufLocalTrabalho",
    "ufNascimento",
    "ufOrigem",
    "area",
    "subordinacao",
    "recados",
    "dataCadastro",
    "dataInicioTreinCcpr",
    "dataFimTreinCcpr",
    "dataTreinamentoPB",
    "dataExperiencia30Dias",
    "dataExperiencia60Dias",
    "nacionalidade",
    "dataMobilizacaoFamilia",
    "dataDesmobilizacaoFamilia",
    "dataMudanca",
    "dataTransferencia",
    "pontoReferencia",
    "pai",
    "mae",
    "dataEmissaoCPF",
    "dataEmissaoRG",
    "dataEmissaoCTPS",
    "dataEmissaoTituloEleitor",
    "tituloEleitor",
    "zonaEleitoral",
    "secaoEleitoral",
    "ufEmissaoCPF",
    "ufEmissaoRG",
    "cidadeVotacao",
    "ufVotacao",
    "ufPisPasep",
    "numeroCNH",
    "categoriaCNH",
    "dataEmissaoCNH",
    "dataValidadeCNH",
    "dataSolicitacaoCV",
    "dataRecebimentoCV",
    "dataEnvioCVparaPB",
    "dataRecebimentoCVdaPB",
    "observacao",
    "dataEntrevistaTecnica",
    "aptoEntrevistaTecnica",
    "dataExameMedico",
    "aptoExameMedico",
    "clinica",
    "alojar",
    "numeroIf",
    "dataProtocoloIf",
    "observacaoAdmissao",
    "crachaCcpr",
    "dataAssinaturaContrato",
    "autorizadoDirigir",
    "dataAdmissaoAprovada",
    "dataSolicitacaoCracha",
    "observacaoIf",
    "tipoContratacao",
    "diasIntervaloViagem",
    "mobilizacao",
    "mobilizacaoPaga",
    "empresaId",
    "encarregado",
    "centroCusto",
    "isSubcontratado",
    "isIndicacao",
    "isEncarregado",
    "periodoTrabalho",
    "dadosBancarios",
    "dataInicioProjeto",
    "numeroCrachaProvisorio",
    "dataRecCrachaProvisorio",
    "dataBaixaCrachaProvisorio",
    "isRecrutador",
    "isReadmissivel",
    "dataEnvioDocCrachaDp",
    "chaveBandejaEnvio",
    "chaveBandejaRetorno",
    "planseq",
    "dataTransfSaida",
    "direitoPlr",
    "treinamentoIntegracao",
    "foto",
    "nomeCracha",
    "viasCracha",
    "dataPrevisaoDesmobilizacao",
    "possuiContaCorrente",
    "nomeConjuge",
    "cbo",
    "dataInicioProcessoDemissao",
    "indicado",
    "ut",
    "nrCracha",
    "nomeContato",
    "telefoneContato",
    "salarioBase",
    "salarioMensal",
    "salarioContratacao",
    "salarioBaseCript",
    "salarioMensalCript",
    "contribuicaoSindical",
    "nrFichaRegistro",
    "liberadoServicoMilitar",
    "observacaoAlojamento",
    "numeroBota",
    "numeroCalca",
    "tamanhoCamisa",
    "primeiroEmprego",
    "observacaoEntregaDocs",
    "dataEntregaDocs",
    "dataSolicPassagemMob",
    "eap",
    "dataAux1",
    "dataAux2",
    "dataAux3",
    "dataAux4",
    "dataAux5",
    "dataAux6",
    "dataAux7",
    "dataAux8",
    "dataAux9",
    "dataAux10",
    "dataAux11",
    "dataAux12",
    "dataAux13",
    "dataAux14",
    "dataAux15",
    "textoAux1",
    "textoAux2",
    "textoAux3",
    "textoAux4",
    "textoAux5",
    "textoAux6",
    "textoAux7",
    "textoAux8",
    "textoAux9",
    "textoAux10",
    "textoAux11",
    "textoAux12",
    "textoAux13",
    "textoAux14",
    "textoAux15",
    "textoAux16",
    "textoAux17",
    "textoAux18",
    "textoAux19",
    "textoAux20",
    "textoAux21",
    "textoAux22",
    "textoAux23",
    "textoAux24",
    "textoAux25",
    "textAreaAux1",
    "textAreaAux2",
    "textAreaAux3",
    "numeroAux1",
    "numeroAux2",
    "doubleAux1",
    "doubleAux2",
    "doubleAux3",
    "doubleAux4",
    "doubleAux5",
    "doubleAux6",
    "doubleAux7",
    "doubleAux8",
    "doubleAux9",
    "doubleAux10",
    "doubleAux11",
    "doubleAux12",
    "doubleAux13",
    "doubleAux14",
    "doubleAux15",
    "raca",
    "tipoDocumento",
    "vwRelPessoa",
    "statusIf",
    "horaChegadaAeroporto",
    "aeroportoChegada",
    "chegouAeroporto",
    "responsavelDocDesligamento",
    "areaRh",
    "planoHorarioTrabalho",
    "motivoAdmissao",
    "unidadeOrganizacional",
    "conhecidoComo",
    "paisNacionalidade",
    "estadoCivilId",
    "racaId",
    "pais",
    "complemento",
    "ddd",
    "dataEmissaoPisPasep",
    "nuCertificadoMilitar",
    "categoriaCertificadoMilitar",
    "especieCertificadoMilitar",
    "nuPassaporte",
    "dataEmissaoPassaporte",
    "paisNaturalizado",
    "dataValidadePassaporte",
    "localEmissaoPassaporte",
    "nuIdentificacaoTrabalhador",
    "nuCartaoNacionalSaude",
    "sgConselhoRegional",
    "ufConselhoRegional",
    "nuIdentidadeEstrangeiro",
    "dataChegadaBrasil",
    "conjugueBrasileiro",
    "nuVisto",
    "tpVisto",
    "dataEmissaoVisto",
    "enderecoRecrutamento",
    "numeroRecrutamento",
    "complementoRecrutamento",
    "bairroRecrutamento",
    "cidadeRecrutamento",
    "cepRecrutamento",
    "ufRecrutamento",
    "paisRecrutamento",
    "dddRecrutamento",
    "telefoneRecrutamento",
    "grauInstrucao",
    "tipoEstabEnsino",
    "certificadoEscolar",
    "formacao",
    "duracaoCurso",
    "unidadeDuracaoCurso",
    "mesConclusaoCurso",
    "anoConclusaoCurso",
    "instituicaoEnsino",
    "paisInstituicao",
    "centroCustoEmissor",
    "centroCustoReceptor",
    "elementoPep",
    "nivelSalarial",
    "horarioTrabalho",
    "periodoExperiencia",
    "periodoExperiencia1",
    "periodoExperiencia2",
    "exposicaoAgenteNocivo",
    "vinculoEmpregaticio",
    "aposentado",
    "categoriaTrabalhador",
    "adicionalInsalubridade",
    "porcentagemInsalubridade",
    "vinculo",
    "adicionalPericulosidade",
    "porcentagemPericulosidade",
    "sindicato",
    "valorMensalidadeSindical",
    "codigoMensalidadeSindical",
    "contribuicaoAssistencial",
    "contribuicaoConfederativa",
    "formaPagamento",
    "banco",
    "numeroContaBancaria",
    "digitoContaCorrente",
    "grupoSanguineo",
    "fatorRh",
    "codigoTomador",
    "salaoRefeitorio",
    "codigoRefeicao",
    "funcionarioRefeitorio",
    "postoPagamento",
    "gremio",
    "superintendencia",
    "nivel",
    "tipoComunicacao",
    "tipoComunicacaoRecrut",
    "tipoDemissao",
    "eapMultiEmpresa",
    "subgrupo",
    "dataSolicitacaoDocumentacao",
    "dataEnvioSap",
    "dataAdmSap",
    "necessitaTransporte",
    "observacaoTransporte",
    "observacaoRecontratacao",
    "somaCargaHorariaHoras",
    "somaCargaHorariaMinutos",
    "valorTotalTreinamento",
    "cboDescricao",
    "possuiFoto",
    "beneficios",
    "direitoTransporte",
    "tempoAguarAso",
    "dataEntradaUltimaEtapa",
    "fcFluxo",
    "statusContratacao",
    "nivelEncarregado",
    "telefoneCompleto",
    "escolaridade",
    "estadoCivil",
    "stDataAdmissao",
    "stDataDemissao",
    "stDataRecebimentoAso",
    "stDataEncaminhadoExame",
    "stDataInicioTreinCcpr",
    "stDataFimTreinCcpr",
    "stDataTreinamentoPB",
    "stDataEntregaDocsDp",
    "stDataRecebimentoCtps",
    "stDataSolicitacaoCracha",
    "stDataLiberacaoCracha",
    "stDataTransferencia",
    "stDataTransfSaida",
    "stDataCadastro",
    "stDataNascimento",
    "stDataPrevisaoDesmobilizacao",
    "stDataRecrutamento",
    "stDataEntradaArea",
    "stDataDesistenteReprovado",
    "stDataChegadaPrevista",
    "stDataExameMedico",
    "stDataAdmissaoAprovada",
    "stDataProtocoloIf",
    "stDataEnvioDocCrachaDp",
    "stDataEmissaoRG",
    "stDataInicioProjeto",
    "stDataMobilizacaoFamilia",
    "stDataDesmobilizacaoFamilia",
    "stDataMudanca",
    "stDataEmissaoCTPS",
    "stDataEmissaoTituloEleitor",
    "stDataEmissaoCNH",
    "stDataValidadeCNH",
    "stDataEmissaoCPF",
    "stDataEmissaoRegistro",
    "stDataHomologacaoDemissao",
    "stDataExperiencia30Dias",
    "stDataExperiencia60Dias",
    "stDataEntrevistaTecnica",
    "stDataSolicitacaoCV",
    "stDataRecebimentoCV",
    "stDataEnvioCVparaPB",
    "stDataRecebimentoCVdaPB",
    "stDataInicioProcessoDemissao",
    "stDataAssinaturaContrato",
    "stDataBaixaCrachaProvisorio",
    "stDataRecCrachaProvisorio",
    "stDataSolicPassagemMob",
    "stDataEntregaDocs",
    "stDataAux1",
    "stDataAux2",
    "stDataAux3",
    "stDataAux4",
    "stDataAux5",
    "stDataAux6",
    "stDataAux7",
    "stDataAux8",
    "stDataAux9",
    "stDataAux10",
    "stDataAux11",
    "stDataAux12",
    "stDataAux13",
    "stDataAux14",
    "stDataAux15",
    "stDataEmissaoPisPasep",
    "stDataEmissaoPassaporte",
    "stDataValidadePassaporte",
    "stDataChegadaBrasil",
    "stDataEmissaoVisto",
    "stDataSolicitacaoDocumentacao",
    "stDataEntradaUltimaEtapa",
    "stDataRetorno",
    "stDataEnvioSap",
    "stDataAdmSap",
    "idade",
    "diasDesdeDemissao"
})
public class Pessoa {

    protected Integer pessoaId;
    protected String referencia;
    protected String nome;
    protected String telefone;
    protected String tipoMo;
    protected String tipoContrato;
    protected String ramalAtual;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataNascimento;
    protected String sexo;
    protected String motivoDemissao;
    protected String anoEndereco;
    protected String endereco;
    protected String bairro;
    protected String numero;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAdmissao;
    protected String desistenteReprovado;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataDesistenteReprovado;
    protected String motivo;
    protected Pessoa indicadoPor;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRecrutamento;
    protected String localRecrutamento;
    protected String recrutador;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEncaminhadoExame;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRecebimentoAso;
    protected String horaRecebimentoAso;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRecebimentoCtps;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataDemissao;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataHomologacaoDemissao;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEntregaDocsDp;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataChegadaPrevista;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataLiberacaoCracha;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEntradaArea;
    protected String emailPessoal;
    protected String emailCcpr;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRetorno;
    protected String tipoSanguineo;
    protected String pcd;
    protected String deficiencia;
    protected String nomePai;
    protected String nomeMae;
    protected String nomeEscola;
    protected String conselho;
    protected String numeroRegistro;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEmissaoRegistro;
    protected String status;
    protected String cpf;
    protected String rg;
    protected String orgaoExpedidorRg;
    protected String nrCarteiraTrabalho;
    protected String serieCarteiraTrabalho;
    protected Uf ufCarteiraTrabalho;
    protected String pisPasep;
    protected Double fatorPlr;
    protected String horistaMensalista;
    protected String podeRequisitarVaga;
    protected String cidade;
    protected String cep;
    protected String cidadeNascimento;
    protected Cidade cidadeOrigem;
    protected Funcao funcao;
    protected FuncaoPetrobras funcaoPb;
    protected SubArea subArea;
    protected Vaga vaga;
    protected Uf uf;
    protected Uf ufEscola;
    protected Uf ufLocalTrabalho;
    protected Uf ufNascimento;
    protected Uf ufOrigem;
    protected Area area;
    protected Subordinacao subordinacao;
    protected String recados;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataCadastro;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInicioTreinCcpr;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFimTreinCcpr;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataTreinamentoPB;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataExperiencia30Dias;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataExperiencia60Dias;
    protected String nacionalidade;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataMobilizacaoFamilia;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataDesmobilizacaoFamilia;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataMudanca;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataTransferencia;
    protected String pontoReferencia;
    protected String pai;
    protected String mae;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEmissaoCPF;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEmissaoRG;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEmissaoCTPS;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEmissaoTituloEleitor;
    protected String tituloEleitor;
    protected String zonaEleitoral;
    protected String secaoEleitoral;
    protected Uf ufEmissaoCPF;
    protected Uf ufEmissaoRG;
    protected Cidade cidadeVotacao;
    protected Uf ufVotacao;
    protected Uf ufPisPasep;
    protected String numeroCNH;
    protected String categoriaCNH;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEmissaoCNH;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataValidadeCNH;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataSolicitacaoCV;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRecebimentoCV;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEnvioCVparaPB;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRecebimentoCVdaPB;
    protected String observacao;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEntrevistaTecnica;
    protected String aptoEntrevistaTecnica;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataExameMedico;
    protected String aptoExameMedico;
    protected Clinica clinica;
    protected String alojar;
    protected String numeroIf;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataProtocoloIf;
    protected String observacaoAdmissao;
    protected String crachaCcpr;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAssinaturaContrato;
    protected String autorizadoDirigir;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAdmissaoAprovada;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataSolicitacaoCracha;
    protected String observacaoIf;
    protected String tipoContratacao;
    protected Integer diasIntervaloViagem;
    protected String mobilizacao;
    protected String mobilizacaoPaga;
    protected Empresa empresaId;
    protected Pessoa encarregado;
    protected CentroCusto centroCusto;
    protected String isSubcontratado;
    protected String isIndicacao;
    protected String isEncarregado;
    protected String periodoTrabalho;
    protected String dadosBancarios;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInicioProjeto;
    protected String numeroCrachaProvisorio;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRecCrachaProvisorio;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataBaixaCrachaProvisorio;
    protected String isRecrutador;
    protected String isReadmissivel;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEnvioDocCrachaDp;
    protected String chaveBandejaEnvio;
    protected String chaveBandejaRetorno;
    protected String planseq;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataTransfSaida;
    protected String direitoPlr;
    protected Integer treinamentoIntegracao;
    protected byte[] foto;
    protected String nomeCracha;
    protected Integer viasCracha;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataPrevisaoDesmobilizacao;
    protected String possuiContaCorrente;
    protected String nomeConjuge;
    protected Cbo cbo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInicioProcessoDemissao;
    protected VagaRqsIndicado indicado;
    protected String ut;
    protected String nrCracha;
    protected String nomeContato;
    protected String telefoneContato;
    protected Double salarioBase;
    protected Double salarioMensal;
    protected Double salarioContratacao;
    protected String salarioBaseCript;
    protected String salarioMensalCript;
    protected String contribuicaoSindical;
    protected String nrFichaRegistro;
    protected String liberadoServicoMilitar;
    protected String observacaoAlojamento;
    protected String numeroBota;
    protected String numeroCalca;
    protected String tamanhoCamisa;
    protected String primeiroEmprego;
    protected String observacaoEntregaDocs;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEntregaDocs;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataSolicPassagemMob;
    protected Eap eap;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAux1;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAux2;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAux3;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAux4;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAux5;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAux6;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAux7;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAux8;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAux9;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAux10;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAux11;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAux12;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAux13;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAux14;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAux15;
    protected String textoAux1;
    protected String textoAux2;
    protected String textoAux3;
    protected String textoAux4;
    protected String textoAux5;
    protected String textoAux6;
    protected String textoAux7;
    protected String textoAux8;
    protected String textoAux9;
    protected String textoAux10;
    protected String textoAux11;
    protected String textoAux12;
    protected String textoAux13;
    protected String textoAux14;
    protected String textoAux15;
    protected String textoAux16;
    protected String textoAux17;
    protected String textoAux18;
    protected String textoAux19;
    protected String textoAux20;
    protected String textoAux21;
    protected String textoAux22;
    protected String textoAux23;
    protected String textoAux24;
    protected String textoAux25;
    protected String textAreaAux1;
    protected String textAreaAux2;
    protected String textAreaAux3;
    protected Integer numeroAux1;
    protected Integer numeroAux2;
    protected Double doubleAux1;
    protected Double doubleAux2;
    protected Double doubleAux3;
    protected Double doubleAux4;
    protected Double doubleAux5;
    protected Double doubleAux6;
    protected Double doubleAux7;
    protected Double doubleAux8;
    protected Double doubleAux9;
    protected Double doubleAux10;
    protected Double doubleAux11;
    protected Double doubleAux12;
    protected Double doubleAux13;
    protected Double doubleAux14;
    protected Double doubleAux15;
    protected String raca;
    protected TipoDocumento tipoDocumento;
    protected VwRelPessoa vwRelPessoa;
    protected String statusIf;
    protected String horaChegadaAeroporto;
    protected String aeroportoChegada;
    protected String chegouAeroporto;
    protected Pessoa responsavelDocDesligamento;
    protected AreaRh areaRh;
    protected PlanoHorarioTrabalho planoHorarioTrabalho;
    protected MotivoAdmissao motivoAdmissao;
    protected UnidadeOrganizacional unidadeOrganizacional;
    protected String conhecidoComo;
    protected Pais paisNacionalidade;
    protected EstadoCivil estadoCivilId;
    protected Raca racaId;
    protected Pais pais;
    protected String complemento;
    protected String ddd;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEmissaoPisPasep;
    protected String nuCertificadoMilitar;
    protected String categoriaCertificadoMilitar;
    protected String especieCertificadoMilitar;
    protected String nuPassaporte;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEmissaoPassaporte;
    protected Pais paisNaturalizado;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataValidadePassaporte;
    protected String localEmissaoPassaporte;
    protected String nuIdentificacaoTrabalhador;
    protected String nuCartaoNacionalSaude;
    protected String sgConselhoRegional;
    protected Uf ufConselhoRegional;
    protected String nuIdentidadeEstrangeiro;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataChegadaBrasil;
    protected String conjugueBrasileiro;
    protected String nuVisto;
    protected String tpVisto;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEmissaoVisto;
    protected String enderecoRecrutamento;
    protected String numeroRecrutamento;
    protected String complementoRecrutamento;
    protected String bairroRecrutamento;
    protected String cidadeRecrutamento;
    protected String cepRecrutamento;
    protected Uf ufRecrutamento;
    protected Pais paisRecrutamento;
    protected String dddRecrutamento;
    protected String telefoneRecrutamento;
    protected Escolaridade grauInstrucao;
    protected TipoEstabEnsino tipoEstabEnsino;
    protected CertificadoEscolar certificadoEscolar;
    protected Formacao formacao;
    protected Integer duracaoCurso;
    protected String unidadeDuracaoCurso;
    protected Integer mesConclusaoCurso;
    protected Integer anoConclusaoCurso;
    protected InstituicaoEnsino instituicaoEnsino;
    protected Pais paisInstituicao;
    protected CentroCusto centroCustoEmissor;
    protected CentroCusto centroCustoReceptor;
    protected Pep elementoPep;
    protected NivelSalarial nivelSalarial;
    protected String horarioTrabalho;
    protected Integer periodoExperiencia;
    protected Integer periodoExperiencia1;
    protected Integer periodoExperiencia2;
    protected ExposicaoAgenteNocivo exposicaoAgenteNocivo;
    protected VinculoEmpregaticio vinculoEmpregaticio;
    protected String aposentado;
    protected CategoriaTrabalhador categoriaTrabalhador;
    protected String adicionalInsalubridade;
    protected Double porcentagemInsalubridade;
    protected Vinculo vinculo;
    protected String adicionalPericulosidade;
    protected Double porcentagemPericulosidade;
    protected Sindicato sindicato;
    protected Double valorMensalidadeSindical;
    protected Double codigoMensalidadeSindical;
    protected String contribuicaoAssistencial;
    protected String contribuicaoConfederativa;
    protected FormaPagamento formaPagamento;
    protected String banco;
    protected String numeroContaBancaria;
    protected String digitoContaCorrente;
    protected GrupoSanguineo grupoSanguineo;
    protected FatorRh fatorRh;
    protected String codigoTomador;
    protected String salaoRefeitorio;
    protected String codigoRefeicao;
    protected String funcionarioRefeitorio;
    protected String postoPagamento;
    protected String gremio;
    protected String superintendencia;
    protected String nivel;
    protected TipoComunicacao tipoComunicacao;
    protected TipoComunicacao tipoComunicacaoRecrut;
    protected TipoDemissao tipoDemissao;
    protected EapMultiEmpresa eapMultiEmpresa;
    protected String subgrupo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataSolicitacaoDocumentacao;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEnvioSap;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAdmSap;
    protected String necessitaTransporte;
    protected String observacaoTransporte;
    protected String observacaoRecontratacao;
    protected Integer somaCargaHorariaHoras;
    protected Integer somaCargaHorariaMinutos;
    protected Double valorTotalTreinamento;
    protected String cboDescricao;
    protected Boolean possuiFoto;
    @XmlElement(nillable = true)
    protected List<Beneficio> beneficios;
    protected String direitoTransporte;
    protected Integer tempoAguarAso;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEntradaUltimaEtapa;
    protected FcFluxo fcFluxo;
    protected StatusContratacao statusContratacao;
    protected Integer nivelEncarregado;
    protected String telefoneCompleto;
    protected String escolaridade;
    protected String estadoCivil;
    protected String stDataAdmissao;
    protected String stDataDemissao;
    protected String stDataRecebimentoAso;
    protected String stDataEncaminhadoExame;
    protected String stDataInicioTreinCcpr;
    protected String stDataFimTreinCcpr;
    protected String stDataTreinamentoPB;
    protected String stDataEntregaDocsDp;
    protected String stDataRecebimentoCtps;
    protected String stDataSolicitacaoCracha;
    protected String stDataLiberacaoCracha;
    protected String stDataTransferencia;
    protected String stDataTransfSaida;
    protected String stDataCadastro;
    protected String stDataNascimento;
    protected String stDataPrevisaoDesmobilizacao;
    protected String stDataRecrutamento;
    protected String stDataEntradaArea;
    protected String stDataDesistenteReprovado;
    protected String stDataChegadaPrevista;
    protected String stDataExameMedico;
    protected String stDataAdmissaoAprovada;
    protected String stDataProtocoloIf;
    protected String stDataEnvioDocCrachaDp;
    protected String stDataEmissaoRG;
    protected String stDataInicioProjeto;
    protected String stDataMobilizacaoFamilia;
    protected String stDataDesmobilizacaoFamilia;
    protected String stDataMudanca;
    protected String stDataEmissaoCTPS;
    protected String stDataEmissaoTituloEleitor;
    protected String stDataEmissaoCNH;
    protected String stDataValidadeCNH;
    protected String stDataEmissaoCPF;
    protected String stDataEmissaoRegistro;
    protected String stDataHomologacaoDemissao;
    protected String stDataExperiencia30Dias;
    protected String stDataExperiencia60Dias;
    protected String stDataEntrevistaTecnica;
    protected String stDataSolicitacaoCV;
    protected String stDataRecebimentoCV;
    protected String stDataEnvioCVparaPB;
    protected String stDataRecebimentoCVdaPB;
    protected String stDataInicioProcessoDemissao;
    protected String stDataAssinaturaContrato;
    protected String stDataBaixaCrachaProvisorio;
    protected String stDataRecCrachaProvisorio;
    protected String stDataSolicPassagemMob;
    protected String stDataEntregaDocs;
    protected String stDataAux1;
    protected String stDataAux2;
    protected String stDataAux3;
    protected String stDataAux4;
    protected String stDataAux5;
    protected String stDataAux6;
    protected String stDataAux7;
    protected String stDataAux8;
    protected String stDataAux9;
    protected String stDataAux10;
    protected String stDataAux11;
    protected String stDataAux12;
    protected String stDataAux13;
    protected String stDataAux14;
    protected String stDataAux15;
    protected String stDataEmissaoPisPasep;
    protected String stDataEmissaoPassaporte;
    protected String stDataValidadePassaporte;
    protected String stDataChegadaBrasil;
    protected String stDataEmissaoVisto;
    protected String stDataSolicitacaoDocumentacao;
    protected String stDataEntradaUltimaEtapa;
    protected String stDataRetorno;
    protected String stDataEnvioSap;
    protected String stDataAdmSap;
    protected Integer idade;
    protected String diasDesdeDemissao;

    /**
     * Gets the value of the pessoaId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPessoaId() {
        return pessoaId;
    }

    /**
     * Sets the value of the pessoaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPessoaId(Integer value) {
        this.pessoaId = value;
    }

    /**
     * Gets the value of the referencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Sets the value of the referencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferencia(String value) {
        this.referencia = value;
    }

    /**
     * Gets the value of the nome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the value of the nome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Gets the value of the telefone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Sets the value of the telefone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefone(String value) {
        this.telefone = value;
    }

    /**
     * Gets the value of the tipoMo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoMo() {
        return tipoMo;
    }

    /**
     * Sets the value of the tipoMo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoMo(String value) {
        this.tipoMo = value;
    }

    /**
     * Gets the value of the tipoContrato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoContrato() {
        return tipoContrato;
    }

    /**
     * Sets the value of the tipoContrato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoContrato(String value) {
        this.tipoContrato = value;
    }

    /**
     * Gets the value of the ramalAtual property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRamalAtual() {
        return ramalAtual;
    }

    /**
     * Sets the value of the ramalAtual property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRamalAtual(String value) {
        this.ramalAtual = value;
    }

    /**
     * Gets the value of the dataNascimento property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Sets the value of the dataNascimento property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataNascimento(XMLGregorianCalendar value) {
        this.dataNascimento = value;
    }

    /**
     * Gets the value of the sexo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Sets the value of the sexo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSexo(String value) {
        this.sexo = value;
    }

    /**
     * Gets the value of the motivoDemissao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivoDemissao() {
        return motivoDemissao;
    }

    /**
     * Sets the value of the motivoDemissao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivoDemissao(String value) {
        this.motivoDemissao = value;
    }

    /**
     * Gets the value of the anoEndereco property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnoEndereco() {
        return anoEndereco;
    }

    /**
     * Sets the value of the anoEndereco property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnoEndereco(String value) {
        this.anoEndereco = value;
    }

    /**
     * Gets the value of the endereco property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Sets the value of the endereco property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndereco(String value) {
        this.endereco = value;
    }

    /**
     * Gets the value of the bairro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * Sets the value of the bairro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBairro(String value) {
        this.bairro = value;
    }

    /**
     * Gets the value of the numero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Sets the value of the numero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumero(String value) {
        this.numero = value;
    }

    /**
     * Gets the value of the dataAdmissao property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAdmissao() {
        return dataAdmissao;
    }

    /**
     * Sets the value of the dataAdmissao property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAdmissao(XMLGregorianCalendar value) {
        this.dataAdmissao = value;
    }

    /**
     * Gets the value of the desistenteReprovado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesistenteReprovado() {
        return desistenteReprovado;
    }

    /**
     * Sets the value of the desistenteReprovado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesistenteReprovado(String value) {
        this.desistenteReprovado = value;
    }

    /**
     * Gets the value of the dataDesistenteReprovado property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDesistenteReprovado() {
        return dataDesistenteReprovado;
    }

    /**
     * Sets the value of the dataDesistenteReprovado property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDesistenteReprovado(XMLGregorianCalendar value) {
        this.dataDesistenteReprovado = value;
    }

    /**
     * Gets the value of the motivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Sets the value of the motivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivo(String value) {
        this.motivo = value;
    }

    /**
     * Gets the value of the indicadoPor property.
     * 
     * @return
     *     possible object is
     *     {@link Pessoa }
     *     
     */
    public Pessoa getIndicadoPor() {
        return indicadoPor;
    }

    /**
     * Sets the value of the indicadoPor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pessoa }
     *     
     */
    public void setIndicadoPor(Pessoa value) {
        this.indicadoPor = value;
    }

    /**
     * Gets the value of the dataRecrutamento property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRecrutamento() {
        return dataRecrutamento;
    }

    /**
     * Sets the value of the dataRecrutamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRecrutamento(XMLGregorianCalendar value) {
        this.dataRecrutamento = value;
    }

    /**
     * Gets the value of the localRecrutamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalRecrutamento() {
        return localRecrutamento;
    }

    /**
     * Sets the value of the localRecrutamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalRecrutamento(String value) {
        this.localRecrutamento = value;
    }

    /**
     * Gets the value of the recrutador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecrutador() {
        return recrutador;
    }

    /**
     * Sets the value of the recrutador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecrutador(String value) {
        this.recrutador = value;
    }

    /**
     * Gets the value of the dataEncaminhadoExame property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEncaminhadoExame() {
        return dataEncaminhadoExame;
    }

    /**
     * Sets the value of the dataEncaminhadoExame property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEncaminhadoExame(XMLGregorianCalendar value) {
        this.dataEncaminhadoExame = value;
    }

    /**
     * Gets the value of the dataRecebimentoAso property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRecebimentoAso() {
        return dataRecebimentoAso;
    }

    /**
     * Sets the value of the dataRecebimentoAso property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRecebimentoAso(XMLGregorianCalendar value) {
        this.dataRecebimentoAso = value;
    }

    /**
     * Gets the value of the horaRecebimentoAso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoraRecebimentoAso() {
        return horaRecebimentoAso;
    }

    /**
     * Sets the value of the horaRecebimentoAso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoraRecebimentoAso(String value) {
        this.horaRecebimentoAso = value;
    }

    /**
     * Gets the value of the dataRecebimentoCtps property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRecebimentoCtps() {
        return dataRecebimentoCtps;
    }

    /**
     * Sets the value of the dataRecebimentoCtps property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRecebimentoCtps(XMLGregorianCalendar value) {
        this.dataRecebimentoCtps = value;
    }

    /**
     * Gets the value of the dataDemissao property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDemissao() {
        return dataDemissao;
    }

    /**
     * Sets the value of the dataDemissao property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDemissao(XMLGregorianCalendar value) {
        this.dataDemissao = value;
    }

    /**
     * Gets the value of the dataHomologacaoDemissao property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataHomologacaoDemissao() {
        return dataHomologacaoDemissao;
    }

    /**
     * Sets the value of the dataHomologacaoDemissao property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataHomologacaoDemissao(XMLGregorianCalendar value) {
        this.dataHomologacaoDemissao = value;
    }

    /**
     * Gets the value of the dataEntregaDocsDp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEntregaDocsDp() {
        return dataEntregaDocsDp;
    }

    /**
     * Sets the value of the dataEntregaDocsDp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEntregaDocsDp(XMLGregorianCalendar value) {
        this.dataEntregaDocsDp = value;
    }

    /**
     * Gets the value of the dataChegadaPrevista property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataChegadaPrevista() {
        return dataChegadaPrevista;
    }

    /**
     * Sets the value of the dataChegadaPrevista property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataChegadaPrevista(XMLGregorianCalendar value) {
        this.dataChegadaPrevista = value;
    }

    /**
     * Gets the value of the dataLiberacaoCracha property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataLiberacaoCracha() {
        return dataLiberacaoCracha;
    }

    /**
     * Sets the value of the dataLiberacaoCracha property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataLiberacaoCracha(XMLGregorianCalendar value) {
        this.dataLiberacaoCracha = value;
    }

    /**
     * Gets the value of the dataEntradaArea property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEntradaArea() {
        return dataEntradaArea;
    }

    /**
     * Sets the value of the dataEntradaArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEntradaArea(XMLGregorianCalendar value) {
        this.dataEntradaArea = value;
    }

    /**
     * Gets the value of the emailPessoal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailPessoal() {
        return emailPessoal;
    }

    /**
     * Sets the value of the emailPessoal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailPessoal(String value) {
        this.emailPessoal = value;
    }

    /**
     * Gets the value of the emailCcpr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailCcpr() {
        return emailCcpr;
    }

    /**
     * Sets the value of the emailCcpr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailCcpr(String value) {
        this.emailCcpr = value;
    }

    /**
     * Gets the value of the dataRetorno property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRetorno() {
        return dataRetorno;
    }

    /**
     * Sets the value of the dataRetorno property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRetorno(XMLGregorianCalendar value) {
        this.dataRetorno = value;
    }

    /**
     * Gets the value of the tipoSanguineo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    /**
     * Sets the value of the tipoSanguineo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoSanguineo(String value) {
        this.tipoSanguineo = value;
    }

    /**
     * Gets the value of the pcd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPcd() {
        return pcd;
    }

    /**
     * Sets the value of the pcd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPcd(String value) {
        this.pcd = value;
    }

    /**
     * Gets the value of the deficiencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeficiencia() {
        return deficiencia;
    }

    /**
     * Sets the value of the deficiencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeficiencia(String value) {
        this.deficiencia = value;
    }

    /**
     * Gets the value of the nomePai property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomePai() {
        return nomePai;
    }

    /**
     * Sets the value of the nomePai property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomePai(String value) {
        this.nomePai = value;
    }

    /**
     * Gets the value of the nomeMae property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeMae() {
        return nomeMae;
    }

    /**
     * Sets the value of the nomeMae property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeMae(String value) {
        this.nomeMae = value;
    }

    /**
     * Gets the value of the nomeEscola property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeEscola() {
        return nomeEscola;
    }

    /**
     * Sets the value of the nomeEscola property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeEscola(String value) {
        this.nomeEscola = value;
    }

    /**
     * Gets the value of the conselho property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConselho() {
        return conselho;
    }

    /**
     * Sets the value of the conselho property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConselho(String value) {
        this.conselho = value;
    }

    /**
     * Gets the value of the numeroRegistro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    /**
     * Sets the value of the numeroRegistro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroRegistro(String value) {
        this.numeroRegistro = value;
    }

    /**
     * Gets the value of the dataEmissaoRegistro property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEmissaoRegistro() {
        return dataEmissaoRegistro;
    }

    /**
     * Sets the value of the dataEmissaoRegistro property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEmissaoRegistro(XMLGregorianCalendar value) {
        this.dataEmissaoRegistro = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the cpf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Sets the value of the cpf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpf(String value) {
        this.cpf = value;
    }

    /**
     * Gets the value of the rg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRg() {
        return rg;
    }

    /**
     * Sets the value of the rg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRg(String value) {
        this.rg = value;
    }

    /**
     * Gets the value of the orgaoExpedidorRg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgaoExpedidorRg() {
        return orgaoExpedidorRg;
    }

    /**
     * Sets the value of the orgaoExpedidorRg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgaoExpedidorRg(String value) {
        this.orgaoExpedidorRg = value;
    }

    /**
     * Gets the value of the nrCarteiraTrabalho property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNrCarteiraTrabalho() {
        return nrCarteiraTrabalho;
    }

    /**
     * Sets the value of the nrCarteiraTrabalho property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNrCarteiraTrabalho(String value) {
        this.nrCarteiraTrabalho = value;
    }

    /**
     * Gets the value of the serieCarteiraTrabalho property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerieCarteiraTrabalho() {
        return serieCarteiraTrabalho;
    }

    /**
     * Sets the value of the serieCarteiraTrabalho property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerieCarteiraTrabalho(String value) {
        this.serieCarteiraTrabalho = value;
    }

    /**
     * Gets the value of the ufCarteiraTrabalho property.
     * 
     * @return
     *     possible object is
     *     {@link Uf }
     *     
     */
    public Uf getUfCarteiraTrabalho() {
        return ufCarteiraTrabalho;
    }

    /**
     * Sets the value of the ufCarteiraTrabalho property.
     * 
     * @param value
     *     allowed object is
     *     {@link Uf }
     *     
     */
    public void setUfCarteiraTrabalho(Uf value) {
        this.ufCarteiraTrabalho = value;
    }

    /**
     * Gets the value of the pisPasep property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPisPasep() {
        return pisPasep;
    }

    /**
     * Sets the value of the pisPasep property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPisPasep(String value) {
        this.pisPasep = value;
    }

    /**
     * Gets the value of the fatorPlr property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getFatorPlr() {
        return fatorPlr;
    }

    /**
     * Sets the value of the fatorPlr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setFatorPlr(Double value) {
        this.fatorPlr = value;
    }

    /**
     * Gets the value of the horistaMensalista property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoristaMensalista() {
        return horistaMensalista;
    }

    /**
     * Sets the value of the horistaMensalista property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoristaMensalista(String value) {
        this.horistaMensalista = value;
    }

    /**
     * Gets the value of the podeRequisitarVaga property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPodeRequisitarVaga() {
        return podeRequisitarVaga;
    }

    /**
     * Sets the value of the podeRequisitarVaga property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPodeRequisitarVaga(String value) {
        this.podeRequisitarVaga = value;
    }

    /**
     * Gets the value of the cidade property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * Sets the value of the cidade property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCidade(String value) {
        this.cidade = value;
    }

    /**
     * Gets the value of the cep property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCep() {
        return cep;
    }

    /**
     * Sets the value of the cep property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCep(String value) {
        this.cep = value;
    }

    /**
     * Gets the value of the cidadeNascimento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    /**
     * Sets the value of the cidadeNascimento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCidadeNascimento(String value) {
        this.cidadeNascimento = value;
    }

    /**
     * Gets the value of the cidadeOrigem property.
     * 
     * @return
     *     possible object is
     *     {@link Cidade }
     *     
     */
    public Cidade getCidadeOrigem() {
        return cidadeOrigem;
    }

    /**
     * Sets the value of the cidadeOrigem property.
     * 
     * @param value
     *     allowed object is
     *     {@link Cidade }
     *     
     */
    public void setCidadeOrigem(Cidade value) {
        this.cidadeOrigem = value;
    }

    /**
     * Gets the value of the funcao property.
     * 
     * @return
     *     possible object is
     *     {@link Funcao }
     *     
     */
    public Funcao getFuncao() {
        return funcao;
    }

    /**
     * Sets the value of the funcao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Funcao }
     *     
     */
    public void setFuncao(Funcao value) {
        this.funcao = value;
    }

    /**
     * Gets the value of the funcaoPb property.
     * 
     * @return
     *     possible object is
     *     {@link FuncaoPetrobras }
     *     
     */
    public FuncaoPetrobras getFuncaoPb() {
        return funcaoPb;
    }

    /**
     * Sets the value of the funcaoPb property.
     * 
     * @param value
     *     allowed object is
     *     {@link FuncaoPetrobras }
     *     
     */
    public void setFuncaoPb(FuncaoPetrobras value) {
        this.funcaoPb = value;
    }

    /**
     * Gets the value of the subArea property.
     * 
     * @return
     *     possible object is
     *     {@link SubArea }
     *     
     */
    public SubArea getSubArea() {
        return subArea;
    }

    /**
     * Sets the value of the subArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubArea }
     *     
     */
    public void setSubArea(SubArea value) {
        this.subArea = value;
    }

    /**
     * Gets the value of the vaga property.
     * 
     * @return
     *     possible object is
     *     {@link Vaga }
     *     
     */
    public Vaga getVaga() {
        return vaga;
    }

    /**
     * Sets the value of the vaga property.
     * 
     * @param value
     *     allowed object is
     *     {@link Vaga }
     *     
     */
    public void setVaga(Vaga value) {
        this.vaga = value;
    }

    /**
     * Gets the value of the uf property.
     * 
     * @return
     *     possible object is
     *     {@link Uf }
     *     
     */
    public Uf getUf() {
        return uf;
    }

    /**
     * Sets the value of the uf property.
     * 
     * @param value
     *     allowed object is
     *     {@link Uf }
     *     
     */
    public void setUf(Uf value) {
        this.uf = value;
    }

    /**
     * Gets the value of the ufEscola property.
     * 
     * @return
     *     possible object is
     *     {@link Uf }
     *     
     */
    public Uf getUfEscola() {
        return ufEscola;
    }

    /**
     * Sets the value of the ufEscola property.
     * 
     * @param value
     *     allowed object is
     *     {@link Uf }
     *     
     */
    public void setUfEscola(Uf value) {
        this.ufEscola = value;
    }

    /**
     * Gets the value of the ufLocalTrabalho property.
     * 
     * @return
     *     possible object is
     *     {@link Uf }
     *     
     */
    public Uf getUfLocalTrabalho() {
        return ufLocalTrabalho;
    }

    /**
     * Sets the value of the ufLocalTrabalho property.
     * 
     * @param value
     *     allowed object is
     *     {@link Uf }
     *     
     */
    public void setUfLocalTrabalho(Uf value) {
        this.ufLocalTrabalho = value;
    }

    /**
     * Gets the value of the ufNascimento property.
     * 
     * @return
     *     possible object is
     *     {@link Uf }
     *     
     */
    public Uf getUfNascimento() {
        return ufNascimento;
    }

    /**
     * Sets the value of the ufNascimento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Uf }
     *     
     */
    public void setUfNascimento(Uf value) {
        this.ufNascimento = value;
    }

    /**
     * Gets the value of the ufOrigem property.
     * 
     * @return
     *     possible object is
     *     {@link Uf }
     *     
     */
    public Uf getUfOrigem() {
        return ufOrigem;
    }

    /**
     * Sets the value of the ufOrigem property.
     * 
     * @param value
     *     allowed object is
     *     {@link Uf }
     *     
     */
    public void setUfOrigem(Uf value) {
        this.ufOrigem = value;
    }

    /**
     * Gets the value of the area property.
     * 
     * @return
     *     possible object is
     *     {@link Area }
     *     
     */
    public Area getArea() {
        return area;
    }

    /**
     * Sets the value of the area property.
     * 
     * @param value
     *     allowed object is
     *     {@link Area }
     *     
     */
    public void setArea(Area value) {
        this.area = value;
    }

    /**
     * Gets the value of the subordinacao property.
     * 
     * @return
     *     possible object is
     *     {@link Subordinacao }
     *     
     */
    public Subordinacao getSubordinacao() {
        return subordinacao;
    }

    /**
     * Sets the value of the subordinacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Subordinacao }
     *     
     */
    public void setSubordinacao(Subordinacao value) {
        this.subordinacao = value;
    }

    /**
     * Gets the value of the recados property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecados() {
        return recados;
    }

    /**
     * Sets the value of the recados property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecados(String value) {
        this.recados = value;
    }

    /**
     * Gets the value of the dataCadastro property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataCadastro() {
        return dataCadastro;
    }

    /**
     * Sets the value of the dataCadastro property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataCadastro(XMLGregorianCalendar value) {
        this.dataCadastro = value;
    }

    /**
     * Gets the value of the dataInicioTreinCcpr property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInicioTreinCcpr() {
        return dataInicioTreinCcpr;
    }

    /**
     * Sets the value of the dataInicioTreinCcpr property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInicioTreinCcpr(XMLGregorianCalendar value) {
        this.dataInicioTreinCcpr = value;
    }

    /**
     * Gets the value of the dataFimTreinCcpr property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFimTreinCcpr() {
        return dataFimTreinCcpr;
    }

    /**
     * Sets the value of the dataFimTreinCcpr property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFimTreinCcpr(XMLGregorianCalendar value) {
        this.dataFimTreinCcpr = value;
    }

    /**
     * Gets the value of the dataTreinamentoPB property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataTreinamentoPB() {
        return dataTreinamentoPB;
    }

    /**
     * Sets the value of the dataTreinamentoPB property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataTreinamentoPB(XMLGregorianCalendar value) {
        this.dataTreinamentoPB = value;
    }

    /**
     * Gets the value of the dataExperiencia30Dias property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataExperiencia30Dias() {
        return dataExperiencia30Dias;
    }

    /**
     * Sets the value of the dataExperiencia30Dias property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataExperiencia30Dias(XMLGregorianCalendar value) {
        this.dataExperiencia30Dias = value;
    }

    /**
     * Gets the value of the dataExperiencia60Dias property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataExperiencia60Dias() {
        return dataExperiencia60Dias;
    }

    /**
     * Sets the value of the dataExperiencia60Dias property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataExperiencia60Dias(XMLGregorianCalendar value) {
        this.dataExperiencia60Dias = value;
    }

    /**
     * Gets the value of the nacionalidade property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacionalidade() {
        return nacionalidade;
    }

    /**
     * Sets the value of the nacionalidade property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacionalidade(String value) {
        this.nacionalidade = value;
    }

    /**
     * Gets the value of the dataMobilizacaoFamilia property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataMobilizacaoFamilia() {
        return dataMobilizacaoFamilia;
    }

    /**
     * Sets the value of the dataMobilizacaoFamilia property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataMobilizacaoFamilia(XMLGregorianCalendar value) {
        this.dataMobilizacaoFamilia = value;
    }

    /**
     * Gets the value of the dataDesmobilizacaoFamilia property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDesmobilizacaoFamilia() {
        return dataDesmobilizacaoFamilia;
    }

    /**
     * Sets the value of the dataDesmobilizacaoFamilia property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDesmobilizacaoFamilia(XMLGregorianCalendar value) {
        this.dataDesmobilizacaoFamilia = value;
    }

    /**
     * Gets the value of the dataMudanca property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataMudanca() {
        return dataMudanca;
    }

    /**
     * Sets the value of the dataMudanca property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataMudanca(XMLGregorianCalendar value) {
        this.dataMudanca = value;
    }

    /**
     * Gets the value of the dataTransferencia property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataTransferencia() {
        return dataTransferencia;
    }

    /**
     * Sets the value of the dataTransferencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataTransferencia(XMLGregorianCalendar value) {
        this.dataTransferencia = value;
    }

    /**
     * Gets the value of the pontoReferencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPontoReferencia() {
        return pontoReferencia;
    }

    /**
     * Sets the value of the pontoReferencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPontoReferencia(String value) {
        this.pontoReferencia = value;
    }

    /**
     * Gets the value of the pai property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPai() {
        return pai;
    }

    /**
     * Sets the value of the pai property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPai(String value) {
        this.pai = value;
    }

    /**
     * Gets the value of the mae property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMae() {
        return mae;
    }

    /**
     * Sets the value of the mae property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMae(String value) {
        this.mae = value;
    }

    /**
     * Gets the value of the dataEmissaoCPF property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEmissaoCPF() {
        return dataEmissaoCPF;
    }

    /**
     * Sets the value of the dataEmissaoCPF property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEmissaoCPF(XMLGregorianCalendar value) {
        this.dataEmissaoCPF = value;
    }

    /**
     * Gets the value of the dataEmissaoRG property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEmissaoRG() {
        return dataEmissaoRG;
    }

    /**
     * Sets the value of the dataEmissaoRG property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEmissaoRG(XMLGregorianCalendar value) {
        this.dataEmissaoRG = value;
    }

    /**
     * Gets the value of the dataEmissaoCTPS property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEmissaoCTPS() {
        return dataEmissaoCTPS;
    }

    /**
     * Sets the value of the dataEmissaoCTPS property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEmissaoCTPS(XMLGregorianCalendar value) {
        this.dataEmissaoCTPS = value;
    }

    /**
     * Gets the value of the dataEmissaoTituloEleitor property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEmissaoTituloEleitor() {
        return dataEmissaoTituloEleitor;
    }

    /**
     * Sets the value of the dataEmissaoTituloEleitor property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEmissaoTituloEleitor(XMLGregorianCalendar value) {
        this.dataEmissaoTituloEleitor = value;
    }

    /**
     * Gets the value of the tituloEleitor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTituloEleitor() {
        return tituloEleitor;
    }

    /**
     * Sets the value of the tituloEleitor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTituloEleitor(String value) {
        this.tituloEleitor = value;
    }

    /**
     * Gets the value of the zonaEleitoral property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZonaEleitoral() {
        return zonaEleitoral;
    }

    /**
     * Sets the value of the zonaEleitoral property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZonaEleitoral(String value) {
        this.zonaEleitoral = value;
    }

    /**
     * Gets the value of the secaoEleitoral property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecaoEleitoral() {
        return secaoEleitoral;
    }

    /**
     * Sets the value of the secaoEleitoral property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecaoEleitoral(String value) {
        this.secaoEleitoral = value;
    }

    /**
     * Gets the value of the ufEmissaoCPF property.
     * 
     * @return
     *     possible object is
     *     {@link Uf }
     *     
     */
    public Uf getUfEmissaoCPF() {
        return ufEmissaoCPF;
    }

    /**
     * Sets the value of the ufEmissaoCPF property.
     * 
     * @param value
     *     allowed object is
     *     {@link Uf }
     *     
     */
    public void setUfEmissaoCPF(Uf value) {
        this.ufEmissaoCPF = value;
    }

    /**
     * Gets the value of the ufEmissaoRG property.
     * 
     * @return
     *     possible object is
     *     {@link Uf }
     *     
     */
    public Uf getUfEmissaoRG() {
        return ufEmissaoRG;
    }

    /**
     * Sets the value of the ufEmissaoRG property.
     * 
     * @param value
     *     allowed object is
     *     {@link Uf }
     *     
     */
    public void setUfEmissaoRG(Uf value) {
        this.ufEmissaoRG = value;
    }

    /**
     * Gets the value of the cidadeVotacao property.
     * 
     * @return
     *     possible object is
     *     {@link Cidade }
     *     
     */
    public Cidade getCidadeVotacao() {
        return cidadeVotacao;
    }

    /**
     * Sets the value of the cidadeVotacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Cidade }
     *     
     */
    public void setCidadeVotacao(Cidade value) {
        this.cidadeVotacao = value;
    }

    /**
     * Gets the value of the ufVotacao property.
     * 
     * @return
     *     possible object is
     *     {@link Uf }
     *     
     */
    public Uf getUfVotacao() {
        return ufVotacao;
    }

    /**
     * Sets the value of the ufVotacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Uf }
     *     
     */
    public void setUfVotacao(Uf value) {
        this.ufVotacao = value;
    }

    /**
     * Gets the value of the ufPisPasep property.
     * 
     * @return
     *     possible object is
     *     {@link Uf }
     *     
     */
    public Uf getUfPisPasep() {
        return ufPisPasep;
    }

    /**
     * Sets the value of the ufPisPasep property.
     * 
     * @param value
     *     allowed object is
     *     {@link Uf }
     *     
     */
    public void setUfPisPasep(Uf value) {
        this.ufPisPasep = value;
    }

    /**
     * Gets the value of the numeroCNH property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCNH() {
        return numeroCNH;
    }

    /**
     * Sets the value of the numeroCNH property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCNH(String value) {
        this.numeroCNH = value;
    }

    /**
     * Gets the value of the categoriaCNH property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoriaCNH() {
        return categoriaCNH;
    }

    /**
     * Sets the value of the categoriaCNH property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoriaCNH(String value) {
        this.categoriaCNH = value;
    }

    /**
     * Gets the value of the dataEmissaoCNH property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEmissaoCNH() {
        return dataEmissaoCNH;
    }

    /**
     * Sets the value of the dataEmissaoCNH property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEmissaoCNH(XMLGregorianCalendar value) {
        this.dataEmissaoCNH = value;
    }

    /**
     * Gets the value of the dataValidadeCNH property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataValidadeCNH() {
        return dataValidadeCNH;
    }

    /**
     * Sets the value of the dataValidadeCNH property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataValidadeCNH(XMLGregorianCalendar value) {
        this.dataValidadeCNH = value;
    }

    /**
     * Gets the value of the dataSolicitacaoCV property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataSolicitacaoCV() {
        return dataSolicitacaoCV;
    }

    /**
     * Sets the value of the dataSolicitacaoCV property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataSolicitacaoCV(XMLGregorianCalendar value) {
        this.dataSolicitacaoCV = value;
    }

    /**
     * Gets the value of the dataRecebimentoCV property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRecebimentoCV() {
        return dataRecebimentoCV;
    }

    /**
     * Sets the value of the dataRecebimentoCV property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRecebimentoCV(XMLGregorianCalendar value) {
        this.dataRecebimentoCV = value;
    }

    /**
     * Gets the value of the dataEnvioCVparaPB property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEnvioCVparaPB() {
        return dataEnvioCVparaPB;
    }

    /**
     * Sets the value of the dataEnvioCVparaPB property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEnvioCVparaPB(XMLGregorianCalendar value) {
        this.dataEnvioCVparaPB = value;
    }

    /**
     * Gets the value of the dataRecebimentoCVdaPB property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRecebimentoCVdaPB() {
        return dataRecebimentoCVdaPB;
    }

    /**
     * Sets the value of the dataRecebimentoCVdaPB property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRecebimentoCVdaPB(XMLGregorianCalendar value) {
        this.dataRecebimentoCVdaPB = value;
    }

    /**
     * Gets the value of the observacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * Sets the value of the observacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacao(String value) {
        this.observacao = value;
    }

    /**
     * Gets the value of the dataEntrevistaTecnica property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEntrevistaTecnica() {
        return dataEntrevistaTecnica;
    }

    /**
     * Sets the value of the dataEntrevistaTecnica property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEntrevistaTecnica(XMLGregorianCalendar value) {
        this.dataEntrevistaTecnica = value;
    }

    /**
     * Gets the value of the aptoEntrevistaTecnica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAptoEntrevistaTecnica() {
        return aptoEntrevistaTecnica;
    }

    /**
     * Sets the value of the aptoEntrevistaTecnica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAptoEntrevistaTecnica(String value) {
        this.aptoEntrevistaTecnica = value;
    }

    /**
     * Gets the value of the dataExameMedico property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataExameMedico() {
        return dataExameMedico;
    }

    /**
     * Sets the value of the dataExameMedico property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataExameMedico(XMLGregorianCalendar value) {
        this.dataExameMedico = value;
    }

    /**
     * Gets the value of the aptoExameMedico property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAptoExameMedico() {
        return aptoExameMedico;
    }

    /**
     * Sets the value of the aptoExameMedico property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAptoExameMedico(String value) {
        this.aptoExameMedico = value;
    }

    /**
     * Gets the value of the clinica property.
     * 
     * @return
     *     possible object is
     *     {@link Clinica }
     *     
     */
    public Clinica getClinica() {
        return clinica;
    }

    /**
     * Sets the value of the clinica property.
     * 
     * @param value
     *     allowed object is
     *     {@link Clinica }
     *     
     */
    public void setClinica(Clinica value) {
        this.clinica = value;
    }

    /**
     * Gets the value of the alojar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlojar() {
        return alojar;
    }

    /**
     * Sets the value of the alojar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlojar(String value) {
        this.alojar = value;
    }

    /**
     * Gets the value of the numeroIf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroIf() {
        return numeroIf;
    }

    /**
     * Sets the value of the numeroIf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroIf(String value) {
        this.numeroIf = value;
    }

    /**
     * Gets the value of the dataProtocoloIf property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataProtocoloIf() {
        return dataProtocoloIf;
    }

    /**
     * Sets the value of the dataProtocoloIf property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataProtocoloIf(XMLGregorianCalendar value) {
        this.dataProtocoloIf = value;
    }

    /**
     * Gets the value of the observacaoAdmissao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacaoAdmissao() {
        return observacaoAdmissao;
    }

    /**
     * Sets the value of the observacaoAdmissao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacaoAdmissao(String value) {
        this.observacaoAdmissao = value;
    }

    /**
     * Gets the value of the crachaCcpr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrachaCcpr() {
        return crachaCcpr;
    }

    /**
     * Sets the value of the crachaCcpr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrachaCcpr(String value) {
        this.crachaCcpr = value;
    }

    /**
     * Gets the value of the dataAssinaturaContrato property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAssinaturaContrato() {
        return dataAssinaturaContrato;
    }

    /**
     * Sets the value of the dataAssinaturaContrato property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAssinaturaContrato(XMLGregorianCalendar value) {
        this.dataAssinaturaContrato = value;
    }

    /**
     * Gets the value of the autorizadoDirigir property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutorizadoDirigir() {
        return autorizadoDirigir;
    }

    /**
     * Sets the value of the autorizadoDirigir property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutorizadoDirigir(String value) {
        this.autorizadoDirigir = value;
    }

    /**
     * Gets the value of the dataAdmissaoAprovada property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAdmissaoAprovada() {
        return dataAdmissaoAprovada;
    }

    /**
     * Sets the value of the dataAdmissaoAprovada property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAdmissaoAprovada(XMLGregorianCalendar value) {
        this.dataAdmissaoAprovada = value;
    }

    /**
     * Gets the value of the dataSolicitacaoCracha property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataSolicitacaoCracha() {
        return dataSolicitacaoCracha;
    }

    /**
     * Sets the value of the dataSolicitacaoCracha property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataSolicitacaoCracha(XMLGregorianCalendar value) {
        this.dataSolicitacaoCracha = value;
    }

    /**
     * Gets the value of the observacaoIf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacaoIf() {
        return observacaoIf;
    }

    /**
     * Sets the value of the observacaoIf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacaoIf(String value) {
        this.observacaoIf = value;
    }

    /**
     * Gets the value of the tipoContratacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoContratacao() {
        return tipoContratacao;
    }

    /**
     * Sets the value of the tipoContratacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoContratacao(String value) {
        this.tipoContratacao = value;
    }

    /**
     * Gets the value of the diasIntervaloViagem property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDiasIntervaloViagem() {
        return diasIntervaloViagem;
    }

    /**
     * Sets the value of the diasIntervaloViagem property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDiasIntervaloViagem(Integer value) {
        this.diasIntervaloViagem = value;
    }

    /**
     * Gets the value of the mobilizacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobilizacao() {
        return mobilizacao;
    }

    /**
     * Sets the value of the mobilizacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobilizacao(String value) {
        this.mobilizacao = value;
    }

    /**
     * Gets the value of the mobilizacaoPaga property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobilizacaoPaga() {
        return mobilizacaoPaga;
    }

    /**
     * Sets the value of the mobilizacaoPaga property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobilizacaoPaga(String value) {
        this.mobilizacaoPaga = value;
    }

    /**
     * Gets the value of the empresaId property.
     * 
     * @return
     *     possible object is
     *     {@link Empresa }
     *     
     */
    public Empresa getEmpresaId() {
        return empresaId;
    }

    /**
     * Sets the value of the empresaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Empresa }
     *     
     */
    public void setEmpresaId(Empresa value) {
        this.empresaId = value;
    }

    /**
     * Gets the value of the encarregado property.
     * 
     * @return
     *     possible object is
     *     {@link Pessoa }
     *     
     */
    public Pessoa getEncarregado() {
        return encarregado;
    }

    /**
     * Sets the value of the encarregado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pessoa }
     *     
     */
    public void setEncarregado(Pessoa value) {
        this.encarregado = value;
    }

    /**
     * Gets the value of the centroCusto property.
     * 
     * @return
     *     possible object is
     *     {@link CentroCusto }
     *     
     */
    public CentroCusto getCentroCusto() {
        return centroCusto;
    }

    /**
     * Sets the value of the centroCusto property.
     * 
     * @param value
     *     allowed object is
     *     {@link CentroCusto }
     *     
     */
    public void setCentroCusto(CentroCusto value) {
        this.centroCusto = value;
    }

    /**
     * Gets the value of the isSubcontratado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsSubcontratado() {
        return isSubcontratado;
    }

    /**
     * Sets the value of the isSubcontratado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsSubcontratado(String value) {
        this.isSubcontratado = value;
    }

    /**
     * Gets the value of the isIndicacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsIndicacao() {
        return isIndicacao;
    }

    /**
     * Sets the value of the isIndicacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsIndicacao(String value) {
        this.isIndicacao = value;
    }

    /**
     * Gets the value of the isEncarregado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsEncarregado() {
        return isEncarregado;
    }

    /**
     * Sets the value of the isEncarregado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsEncarregado(String value) {
        this.isEncarregado = value;
    }

    /**
     * Gets the value of the periodoTrabalho property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodoTrabalho() {
        return periodoTrabalho;
    }

    /**
     * Sets the value of the periodoTrabalho property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodoTrabalho(String value) {
        this.periodoTrabalho = value;
    }

    /**
     * Gets the value of the dadosBancarios property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDadosBancarios() {
        return dadosBancarios;
    }

    /**
     * Sets the value of the dadosBancarios property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDadosBancarios(String value) {
        this.dadosBancarios = value;
    }

    /**
     * Gets the value of the dataInicioProjeto property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInicioProjeto() {
        return dataInicioProjeto;
    }

    /**
     * Sets the value of the dataInicioProjeto property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInicioProjeto(XMLGregorianCalendar value) {
        this.dataInicioProjeto = value;
    }

    /**
     * Gets the value of the numeroCrachaProvisorio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCrachaProvisorio() {
        return numeroCrachaProvisorio;
    }

    /**
     * Sets the value of the numeroCrachaProvisorio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCrachaProvisorio(String value) {
        this.numeroCrachaProvisorio = value;
    }

    /**
     * Gets the value of the dataRecCrachaProvisorio property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRecCrachaProvisorio() {
        return dataRecCrachaProvisorio;
    }

    /**
     * Sets the value of the dataRecCrachaProvisorio property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRecCrachaProvisorio(XMLGregorianCalendar value) {
        this.dataRecCrachaProvisorio = value;
    }

    /**
     * Gets the value of the dataBaixaCrachaProvisorio property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataBaixaCrachaProvisorio() {
        return dataBaixaCrachaProvisorio;
    }

    /**
     * Sets the value of the dataBaixaCrachaProvisorio property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataBaixaCrachaProvisorio(XMLGregorianCalendar value) {
        this.dataBaixaCrachaProvisorio = value;
    }

    /**
     * Gets the value of the isRecrutador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsRecrutador() {
        return isRecrutador;
    }

    /**
     * Sets the value of the isRecrutador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsRecrutador(String value) {
        this.isRecrutador = value;
    }

    /**
     * Gets the value of the isReadmissivel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsReadmissivel() {
        return isReadmissivel;
    }

    /**
     * Sets the value of the isReadmissivel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsReadmissivel(String value) {
        this.isReadmissivel = value;
    }

    /**
     * Gets the value of the dataEnvioDocCrachaDp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEnvioDocCrachaDp() {
        return dataEnvioDocCrachaDp;
    }

    /**
     * Sets the value of the dataEnvioDocCrachaDp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEnvioDocCrachaDp(XMLGregorianCalendar value) {
        this.dataEnvioDocCrachaDp = value;
    }

    /**
     * Gets the value of the chaveBandejaEnvio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChaveBandejaEnvio() {
        return chaveBandejaEnvio;
    }

    /**
     * Sets the value of the chaveBandejaEnvio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChaveBandejaEnvio(String value) {
        this.chaveBandejaEnvio = value;
    }

    /**
     * Gets the value of the chaveBandejaRetorno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChaveBandejaRetorno() {
        return chaveBandejaRetorno;
    }

    /**
     * Sets the value of the chaveBandejaRetorno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChaveBandejaRetorno(String value) {
        this.chaveBandejaRetorno = value;
    }

    /**
     * Gets the value of the planseq property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanseq() {
        return planseq;
    }

    /**
     * Sets the value of the planseq property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanseq(String value) {
        this.planseq = value;
    }

    /**
     * Gets the value of the dataTransfSaida property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataTransfSaida() {
        return dataTransfSaida;
    }

    /**
     * Sets the value of the dataTransfSaida property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataTransfSaida(XMLGregorianCalendar value) {
        this.dataTransfSaida = value;
    }

    /**
     * Gets the value of the direitoPlr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireitoPlr() {
        return direitoPlr;
    }

    /**
     * Sets the value of the direitoPlr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireitoPlr(String value) {
        this.direitoPlr = value;
    }

    /**
     * Gets the value of the treinamentoIntegracao property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTreinamentoIntegracao() {
        return treinamentoIntegracao;
    }

    /**
     * Sets the value of the treinamentoIntegracao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTreinamentoIntegracao(Integer value) {
        this.treinamentoIntegracao = value;
    }

    /**
     * Gets the value of the foto property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getFoto() {
        return foto;
    }

    /**
     * Sets the value of the foto property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setFoto(byte[] value) {
        this.foto = value;
    }

    /**
     * Gets the value of the nomeCracha property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeCracha() {
        return nomeCracha;
    }

    /**
     * Sets the value of the nomeCracha property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeCracha(String value) {
        this.nomeCracha = value;
    }

    /**
     * Gets the value of the viasCracha property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getViasCracha() {
        return viasCracha;
    }

    /**
     * Sets the value of the viasCracha property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setViasCracha(Integer value) {
        this.viasCracha = value;
    }

    /**
     * Gets the value of the dataPrevisaoDesmobilizacao property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataPrevisaoDesmobilizacao() {
        return dataPrevisaoDesmobilizacao;
    }

    /**
     * Sets the value of the dataPrevisaoDesmobilizacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataPrevisaoDesmobilizacao(XMLGregorianCalendar value) {
        this.dataPrevisaoDesmobilizacao = value;
    }

    /**
     * Gets the value of the possuiContaCorrente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPossuiContaCorrente() {
        return possuiContaCorrente;
    }

    /**
     * Sets the value of the possuiContaCorrente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPossuiContaCorrente(String value) {
        this.possuiContaCorrente = value;
    }

    /**
     * Gets the value of the nomeConjuge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeConjuge() {
        return nomeConjuge;
    }

    /**
     * Sets the value of the nomeConjuge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeConjuge(String value) {
        this.nomeConjuge = value;
    }

    /**
     * Gets the value of the cbo property.
     * 
     * @return
     *     possible object is
     *     {@link Cbo }
     *     
     */
    public Cbo getCbo() {
        return cbo;
    }

    /**
     * Sets the value of the cbo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Cbo }
     *     
     */
    public void setCbo(Cbo value) {
        this.cbo = value;
    }

    /**
     * Gets the value of the dataInicioProcessoDemissao property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInicioProcessoDemissao() {
        return dataInicioProcessoDemissao;
    }

    /**
     * Sets the value of the dataInicioProcessoDemissao property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInicioProcessoDemissao(XMLGregorianCalendar value) {
        this.dataInicioProcessoDemissao = value;
    }

    /**
     * Gets the value of the indicado property.
     * 
     * @return
     *     possible object is
     *     {@link VagaRqsIndicado }
     *     
     */
    public VagaRqsIndicado getIndicado() {
        return indicado;
    }

    /**
     * Sets the value of the indicado property.
     * 
     * @param value
     *     allowed object is
     *     {@link VagaRqsIndicado }
     *     
     */
    public void setIndicado(VagaRqsIndicado value) {
        this.indicado = value;
    }

    /**
     * Gets the value of the ut property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUt() {
        return ut;
    }

    /**
     * Sets the value of the ut property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUt(String value) {
        this.ut = value;
    }

    /**
     * Gets the value of the nrCracha property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNrCracha() {
        return nrCracha;
    }

    /**
     * Sets the value of the nrCracha property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNrCracha(String value) {
        this.nrCracha = value;
    }

    /**
     * Gets the value of the nomeContato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeContato() {
        return nomeContato;
    }

    /**
     * Sets the value of the nomeContato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeContato(String value) {
        this.nomeContato = value;
    }

    /**
     * Gets the value of the telefoneContato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefoneContato() {
        return telefoneContato;
    }

    /**
     * Sets the value of the telefoneContato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefoneContato(String value) {
        this.telefoneContato = value;
    }

    /**
     * Gets the value of the salarioBase property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSalarioBase() {
        return salarioBase;
    }

    /**
     * Sets the value of the salarioBase property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSalarioBase(Double value) {
        this.salarioBase = value;
    }

    /**
     * Gets the value of the salarioMensal property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSalarioMensal() {
        return salarioMensal;
    }

    /**
     * Sets the value of the salarioMensal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSalarioMensal(Double value) {
        this.salarioMensal = value;
    }

    /**
     * Gets the value of the salarioContratacao property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSalarioContratacao() {
        return salarioContratacao;
    }

    /**
     * Sets the value of the salarioContratacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSalarioContratacao(Double value) {
        this.salarioContratacao = value;
    }

    /**
     * Gets the value of the salarioBaseCript property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalarioBaseCript() {
        return salarioBaseCript;
    }

    /**
     * Sets the value of the salarioBaseCript property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalarioBaseCript(String value) {
        this.salarioBaseCript = value;
    }

    /**
     * Gets the value of the salarioMensalCript property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalarioMensalCript() {
        return salarioMensalCript;
    }

    /**
     * Sets the value of the salarioMensalCript property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalarioMensalCript(String value) {
        this.salarioMensalCript = value;
    }

    /**
     * Gets the value of the contribuicaoSindical property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContribuicaoSindical() {
        return contribuicaoSindical;
    }

    /**
     * Sets the value of the contribuicaoSindical property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContribuicaoSindical(String value) {
        this.contribuicaoSindical = value;
    }

    /**
     * Gets the value of the nrFichaRegistro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNrFichaRegistro() {
        return nrFichaRegistro;
    }

    /**
     * Sets the value of the nrFichaRegistro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNrFichaRegistro(String value) {
        this.nrFichaRegistro = value;
    }

    /**
     * Gets the value of the liberadoServicoMilitar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLiberadoServicoMilitar() {
        return liberadoServicoMilitar;
    }

    /**
     * Sets the value of the liberadoServicoMilitar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLiberadoServicoMilitar(String value) {
        this.liberadoServicoMilitar = value;
    }

    /**
     * Gets the value of the observacaoAlojamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacaoAlojamento() {
        return observacaoAlojamento;
    }

    /**
     * Sets the value of the observacaoAlojamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacaoAlojamento(String value) {
        this.observacaoAlojamento = value;
    }

    /**
     * Gets the value of the numeroBota property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroBota() {
        return numeroBota;
    }

    /**
     * Sets the value of the numeroBota property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroBota(String value) {
        this.numeroBota = value;
    }

    /**
     * Gets the value of the numeroCalca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCalca() {
        return numeroCalca;
    }

    /**
     * Sets the value of the numeroCalca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCalca(String value) {
        this.numeroCalca = value;
    }

    /**
     * Gets the value of the tamanhoCamisa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTamanhoCamisa() {
        return tamanhoCamisa;
    }

    /**
     * Sets the value of the tamanhoCamisa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTamanhoCamisa(String value) {
        this.tamanhoCamisa = value;
    }

    /**
     * Gets the value of the primeiroEmprego property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimeiroEmprego() {
        return primeiroEmprego;
    }

    /**
     * Sets the value of the primeiroEmprego property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimeiroEmprego(String value) {
        this.primeiroEmprego = value;
    }

    /**
     * Gets the value of the observacaoEntregaDocs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacaoEntregaDocs() {
        return observacaoEntregaDocs;
    }

    /**
     * Sets the value of the observacaoEntregaDocs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacaoEntregaDocs(String value) {
        this.observacaoEntregaDocs = value;
    }

    /**
     * Gets the value of the dataEntregaDocs property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEntregaDocs() {
        return dataEntregaDocs;
    }

    /**
     * Sets the value of the dataEntregaDocs property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEntregaDocs(XMLGregorianCalendar value) {
        this.dataEntregaDocs = value;
    }

    /**
     * Gets the value of the dataSolicPassagemMob property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataSolicPassagemMob() {
        return dataSolicPassagemMob;
    }

    /**
     * Sets the value of the dataSolicPassagemMob property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataSolicPassagemMob(XMLGregorianCalendar value) {
        this.dataSolicPassagemMob = value;
    }

    /**
     * Gets the value of the eap property.
     * 
     * @return
     *     possible object is
     *     {@link Eap }
     *     
     */
    public Eap getEap() {
        return eap;
    }

    /**
     * Sets the value of the eap property.
     * 
     * @param value
     *     allowed object is
     *     {@link Eap }
     *     
     */
    public void setEap(Eap value) {
        this.eap = value;
    }

    /**
     * Gets the value of the dataAux1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAux1() {
        return dataAux1;
    }

    /**
     * Sets the value of the dataAux1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAux1(XMLGregorianCalendar value) {
        this.dataAux1 = value;
    }

    /**
     * Gets the value of the dataAux2 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAux2() {
        return dataAux2;
    }

    /**
     * Sets the value of the dataAux2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAux2(XMLGregorianCalendar value) {
        this.dataAux2 = value;
    }

    /**
     * Gets the value of the dataAux3 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAux3() {
        return dataAux3;
    }

    /**
     * Sets the value of the dataAux3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAux3(XMLGregorianCalendar value) {
        this.dataAux3 = value;
    }

    /**
     * Gets the value of the dataAux4 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAux4() {
        return dataAux4;
    }

    /**
     * Sets the value of the dataAux4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAux4(XMLGregorianCalendar value) {
        this.dataAux4 = value;
    }

    /**
     * Gets the value of the dataAux5 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAux5() {
        return dataAux5;
    }

    /**
     * Sets the value of the dataAux5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAux5(XMLGregorianCalendar value) {
        this.dataAux5 = value;
    }

    /**
     * Gets the value of the dataAux6 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAux6() {
        return dataAux6;
    }

    /**
     * Sets the value of the dataAux6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAux6(XMLGregorianCalendar value) {
        this.dataAux6 = value;
    }

    /**
     * Gets the value of the dataAux7 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAux7() {
        return dataAux7;
    }

    /**
     * Sets the value of the dataAux7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAux7(XMLGregorianCalendar value) {
        this.dataAux7 = value;
    }

    /**
     * Gets the value of the dataAux8 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAux8() {
        return dataAux8;
    }

    /**
     * Sets the value of the dataAux8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAux8(XMLGregorianCalendar value) {
        this.dataAux8 = value;
    }

    /**
     * Gets the value of the dataAux9 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAux9() {
        return dataAux9;
    }

    /**
     * Sets the value of the dataAux9 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAux9(XMLGregorianCalendar value) {
        this.dataAux9 = value;
    }

    /**
     * Gets the value of the dataAux10 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAux10() {
        return dataAux10;
    }

    /**
     * Sets the value of the dataAux10 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAux10(XMLGregorianCalendar value) {
        this.dataAux10 = value;
    }

    /**
     * Gets the value of the dataAux11 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAux11() {
        return dataAux11;
    }

    /**
     * Sets the value of the dataAux11 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAux11(XMLGregorianCalendar value) {
        this.dataAux11 = value;
    }

    /**
     * Gets the value of the dataAux12 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAux12() {
        return dataAux12;
    }

    /**
     * Sets the value of the dataAux12 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAux12(XMLGregorianCalendar value) {
        this.dataAux12 = value;
    }

    /**
     * Gets the value of the dataAux13 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAux13() {
        return dataAux13;
    }

    /**
     * Sets the value of the dataAux13 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAux13(XMLGregorianCalendar value) {
        this.dataAux13 = value;
    }

    /**
     * Gets the value of the dataAux14 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAux14() {
        return dataAux14;
    }

    /**
     * Sets the value of the dataAux14 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAux14(XMLGregorianCalendar value) {
        this.dataAux14 = value;
    }

    /**
     * Gets the value of the dataAux15 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAux15() {
        return dataAux15;
    }

    /**
     * Sets the value of the dataAux15 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAux15(XMLGregorianCalendar value) {
        this.dataAux15 = value;
    }

    /**
     * Gets the value of the textoAux1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextoAux1() {
        return textoAux1;
    }

    /**
     * Sets the value of the textoAux1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextoAux1(String value) {
        this.textoAux1 = value;
    }

    /**
     * Gets the value of the textoAux2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextoAux2() {
        return textoAux2;
    }

    /**
     * Sets the value of the textoAux2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextoAux2(String value) {
        this.textoAux2 = value;
    }

    /**
     * Gets the value of the textoAux3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextoAux3() {
        return textoAux3;
    }

    /**
     * Sets the value of the textoAux3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextoAux3(String value) {
        this.textoAux3 = value;
    }

    /**
     * Gets the value of the textoAux4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextoAux4() {
        return textoAux4;
    }

    /**
     * Sets the value of the textoAux4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextoAux4(String value) {
        this.textoAux4 = value;
    }

    /**
     * Gets the value of the textoAux5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextoAux5() {
        return textoAux5;
    }

    /**
     * Sets the value of the textoAux5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextoAux5(String value) {
        this.textoAux5 = value;
    }

    /**
     * Gets the value of the textoAux6 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextoAux6() {
        return textoAux6;
    }

    /**
     * Sets the value of the textoAux6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextoAux6(String value) {
        this.textoAux6 = value;
    }

    /**
     * Gets the value of the textoAux7 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextoAux7() {
        return textoAux7;
    }

    /**
     * Sets the value of the textoAux7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextoAux7(String value) {
        this.textoAux7 = value;
    }

    /**
     * Gets the value of the textoAux8 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextoAux8() {
        return textoAux8;
    }

    /**
     * Sets the value of the textoAux8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextoAux8(String value) {
        this.textoAux8 = value;
    }

    /**
     * Gets the value of the textoAux9 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextoAux9() {
        return textoAux9;
    }

    /**
     * Sets the value of the textoAux9 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextoAux9(String value) {
        this.textoAux9 = value;
    }

    /**
     * Gets the value of the textoAux10 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextoAux10() {
        return textoAux10;
    }

    /**
     * Sets the value of the textoAux10 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextoAux10(String value) {
        this.textoAux10 = value;
    }

    /**
     * Gets the value of the textoAux11 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextoAux11() {
        return textoAux11;
    }

    /**
     * Sets the value of the textoAux11 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextoAux11(String value) {
        this.textoAux11 = value;
    }

    /**
     * Gets the value of the textoAux12 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextoAux12() {
        return textoAux12;
    }

    /**
     * Sets the value of the textoAux12 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextoAux12(String value) {
        this.textoAux12 = value;
    }

    /**
     * Gets the value of the textoAux13 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextoAux13() {
        return textoAux13;
    }

    /**
     * Sets the value of the textoAux13 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextoAux13(String value) {
        this.textoAux13 = value;
    }

    /**
     * Gets the value of the textoAux14 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextoAux14() {
        return textoAux14;
    }

    /**
     * Sets the value of the textoAux14 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextoAux14(String value) {
        this.textoAux14 = value;
    }

    /**
     * Gets the value of the textoAux15 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextoAux15() {
        return textoAux15;
    }

    /**
     * Sets the value of the textoAux15 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextoAux15(String value) {
        this.textoAux15 = value;
    }

    /**
     * Gets the value of the textoAux16 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextoAux16() {
        return textoAux16;
    }

    /**
     * Sets the value of the textoAux16 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextoAux16(String value) {
        this.textoAux16 = value;
    }

    /**
     * Gets the value of the textoAux17 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextoAux17() {
        return textoAux17;
    }

    /**
     * Sets the value of the textoAux17 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextoAux17(String value) {
        this.textoAux17 = value;
    }

    /**
     * Gets the value of the textoAux18 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextoAux18() {
        return textoAux18;
    }

    /**
     * Sets the value of the textoAux18 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextoAux18(String value) {
        this.textoAux18 = value;
    }

    /**
     * Gets the value of the textoAux19 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextoAux19() {
        return textoAux19;
    }

    /**
     * Sets the value of the textoAux19 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextoAux19(String value) {
        this.textoAux19 = value;
    }

    /**
     * Gets the value of the textoAux20 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextoAux20() {
        return textoAux20;
    }

    /**
     * Sets the value of the textoAux20 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextoAux20(String value) {
        this.textoAux20 = value;
    }

    /**
     * Gets the value of the textoAux21 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextoAux21() {
        return textoAux21;
    }

    /**
     * Sets the value of the textoAux21 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextoAux21(String value) {
        this.textoAux21 = value;
    }

    /**
     * Gets the value of the textoAux22 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextoAux22() {
        return textoAux22;
    }

    /**
     * Sets the value of the textoAux22 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextoAux22(String value) {
        this.textoAux22 = value;
    }

    /**
     * Gets the value of the textoAux23 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextoAux23() {
        return textoAux23;
    }

    /**
     * Sets the value of the textoAux23 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextoAux23(String value) {
        this.textoAux23 = value;
    }

    /**
     * Gets the value of the textoAux24 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextoAux24() {
        return textoAux24;
    }

    /**
     * Sets the value of the textoAux24 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextoAux24(String value) {
        this.textoAux24 = value;
    }

    /**
     * Gets the value of the textoAux25 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextoAux25() {
        return textoAux25;
    }

    /**
     * Sets the value of the textoAux25 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextoAux25(String value) {
        this.textoAux25 = value;
    }

    /**
     * Gets the value of the textAreaAux1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextAreaAux1() {
        return textAreaAux1;
    }

    /**
     * Sets the value of the textAreaAux1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextAreaAux1(String value) {
        this.textAreaAux1 = value;
    }

    /**
     * Gets the value of the textAreaAux2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextAreaAux2() {
        return textAreaAux2;
    }

    /**
     * Sets the value of the textAreaAux2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextAreaAux2(String value) {
        this.textAreaAux2 = value;
    }

    /**
     * Gets the value of the textAreaAux3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextAreaAux3() {
        return textAreaAux3;
    }

    /**
     * Sets the value of the textAreaAux3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextAreaAux3(String value) {
        this.textAreaAux3 = value;
    }

    /**
     * Gets the value of the numeroAux1 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroAux1() {
        return numeroAux1;
    }

    /**
     * Sets the value of the numeroAux1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroAux1(Integer value) {
        this.numeroAux1 = value;
    }

    /**
     * Gets the value of the numeroAux2 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroAux2() {
        return numeroAux2;
    }

    /**
     * Sets the value of the numeroAux2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroAux2(Integer value) {
        this.numeroAux2 = value;
    }

    /**
     * Gets the value of the doubleAux1 property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDoubleAux1() {
        return doubleAux1;
    }

    /**
     * Sets the value of the doubleAux1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDoubleAux1(Double value) {
        this.doubleAux1 = value;
    }

    /**
     * Gets the value of the doubleAux2 property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDoubleAux2() {
        return doubleAux2;
    }

    /**
     * Sets the value of the doubleAux2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDoubleAux2(Double value) {
        this.doubleAux2 = value;
    }

    /**
     * Gets the value of the doubleAux3 property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDoubleAux3() {
        return doubleAux3;
    }

    /**
     * Sets the value of the doubleAux3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDoubleAux3(Double value) {
        this.doubleAux3 = value;
    }

    /**
     * Gets the value of the doubleAux4 property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDoubleAux4() {
        return doubleAux4;
    }

    /**
     * Sets the value of the doubleAux4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDoubleAux4(Double value) {
        this.doubleAux4 = value;
    }

    /**
     * Gets the value of the doubleAux5 property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDoubleAux5() {
        return doubleAux5;
    }

    /**
     * Sets the value of the doubleAux5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDoubleAux5(Double value) {
        this.doubleAux5 = value;
    }

    /**
     * Gets the value of the doubleAux6 property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDoubleAux6() {
        return doubleAux6;
    }

    /**
     * Sets the value of the doubleAux6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDoubleAux6(Double value) {
        this.doubleAux6 = value;
    }

    /**
     * Gets the value of the doubleAux7 property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDoubleAux7() {
        return doubleAux7;
    }

    /**
     * Sets the value of the doubleAux7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDoubleAux7(Double value) {
        this.doubleAux7 = value;
    }

    /**
     * Gets the value of the doubleAux8 property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDoubleAux8() {
        return doubleAux8;
    }

    /**
     * Sets the value of the doubleAux8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDoubleAux8(Double value) {
        this.doubleAux8 = value;
    }

    /**
     * Gets the value of the doubleAux9 property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDoubleAux9() {
        return doubleAux9;
    }

    /**
     * Sets the value of the doubleAux9 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDoubleAux9(Double value) {
        this.doubleAux9 = value;
    }

    /**
     * Gets the value of the doubleAux10 property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDoubleAux10() {
        return doubleAux10;
    }

    /**
     * Sets the value of the doubleAux10 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDoubleAux10(Double value) {
        this.doubleAux10 = value;
    }

    /**
     * Gets the value of the doubleAux11 property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDoubleAux11() {
        return doubleAux11;
    }

    /**
     * Sets the value of the doubleAux11 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDoubleAux11(Double value) {
        this.doubleAux11 = value;
    }

    /**
     * Gets the value of the doubleAux12 property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDoubleAux12() {
        return doubleAux12;
    }

    /**
     * Sets the value of the doubleAux12 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDoubleAux12(Double value) {
        this.doubleAux12 = value;
    }

    /**
     * Gets the value of the doubleAux13 property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDoubleAux13() {
        return doubleAux13;
    }

    /**
     * Sets the value of the doubleAux13 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDoubleAux13(Double value) {
        this.doubleAux13 = value;
    }

    /**
     * Gets the value of the doubleAux14 property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDoubleAux14() {
        return doubleAux14;
    }

    /**
     * Sets the value of the doubleAux14 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDoubleAux14(Double value) {
        this.doubleAux14 = value;
    }

    /**
     * Gets the value of the doubleAux15 property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDoubleAux15() {
        return doubleAux15;
    }

    /**
     * Sets the value of the doubleAux15 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDoubleAux15(Double value) {
        this.doubleAux15 = value;
    }

    /**
     * Gets the value of the raca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRaca() {
        return raca;
    }

    /**
     * Sets the value of the raca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRaca(String value) {
        this.raca = value;
    }

    /**
     * Gets the value of the tipoDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link TipoDocumento }
     *     
     */
    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Sets the value of the tipoDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoDocumento }
     *     
     */
    public void setTipoDocumento(TipoDocumento value) {
        this.tipoDocumento = value;
    }

    /**
     * Gets the value of the vwRelPessoa property.
     * 
     * @return
     *     possible object is
     *     {@link VwRelPessoa }
     *     
     */
    public VwRelPessoa getVwRelPessoa() {
        return vwRelPessoa;
    }

    /**
     * Sets the value of the vwRelPessoa property.
     * 
     * @param value
     *     allowed object is
     *     {@link VwRelPessoa }
     *     
     */
    public void setVwRelPessoa(VwRelPessoa value) {
        this.vwRelPessoa = value;
    }

    /**
     * Gets the value of the statusIf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusIf() {
        return statusIf;
    }

    /**
     * Sets the value of the statusIf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusIf(String value) {
        this.statusIf = value;
    }

    /**
     * Gets the value of the horaChegadaAeroporto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoraChegadaAeroporto() {
        return horaChegadaAeroporto;
    }

    /**
     * Sets the value of the horaChegadaAeroporto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoraChegadaAeroporto(String value) {
        this.horaChegadaAeroporto = value;
    }

    /**
     * Gets the value of the aeroportoChegada property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAeroportoChegada() {
        return aeroportoChegada;
    }

    /**
     * Sets the value of the aeroportoChegada property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAeroportoChegada(String value) {
        this.aeroportoChegada = value;
    }

    /**
     * Gets the value of the chegouAeroporto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChegouAeroporto() {
        return chegouAeroporto;
    }

    /**
     * Sets the value of the chegouAeroporto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChegouAeroporto(String value) {
        this.chegouAeroporto = value;
    }

    /**
     * Gets the value of the responsavelDocDesligamento property.
     * 
     * @return
     *     possible object is
     *     {@link Pessoa }
     *     
     */
    public Pessoa getResponsavelDocDesligamento() {
        return responsavelDocDesligamento;
    }

    /**
     * Sets the value of the responsavelDocDesligamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pessoa }
     *     
     */
    public void setResponsavelDocDesligamento(Pessoa value) {
        this.responsavelDocDesligamento = value;
    }

    /**
     * Gets the value of the areaRh property.
     * 
     * @return
     *     possible object is
     *     {@link AreaRh }
     *     
     */
    public AreaRh getAreaRh() {
        return areaRh;
    }

    /**
     * Sets the value of the areaRh property.
     * 
     * @param value
     *     allowed object is
     *     {@link AreaRh }
     *     
     */
    public void setAreaRh(AreaRh value) {
        this.areaRh = value;
    }

    /**
     * Gets the value of the planoHorarioTrabalho property.
     * 
     * @return
     *     possible object is
     *     {@link PlanoHorarioTrabalho }
     *     
     */
    public PlanoHorarioTrabalho getPlanoHorarioTrabalho() {
        return planoHorarioTrabalho;
    }

    /**
     * Sets the value of the planoHorarioTrabalho property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlanoHorarioTrabalho }
     *     
     */
    public void setPlanoHorarioTrabalho(PlanoHorarioTrabalho value) {
        this.planoHorarioTrabalho = value;
    }

    /**
     * Gets the value of the motivoAdmissao property.
     * 
     * @return
     *     possible object is
     *     {@link MotivoAdmissao }
     *     
     */
    public MotivoAdmissao getMotivoAdmissao() {
        return motivoAdmissao;
    }

    /**
     * Sets the value of the motivoAdmissao property.
     * 
     * @param value
     *     allowed object is
     *     {@link MotivoAdmissao }
     *     
     */
    public void setMotivoAdmissao(MotivoAdmissao value) {
        this.motivoAdmissao = value;
    }

    /**
     * Gets the value of the unidadeOrganizacional property.
     * 
     * @return
     *     possible object is
     *     {@link UnidadeOrganizacional }
     *     
     */
    public UnidadeOrganizacional getUnidadeOrganizacional() {
        return unidadeOrganizacional;
    }

    /**
     * Sets the value of the unidadeOrganizacional property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnidadeOrganizacional }
     *     
     */
    public void setUnidadeOrganizacional(UnidadeOrganizacional value) {
        this.unidadeOrganizacional = value;
    }

    /**
     * Gets the value of the conhecidoComo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConhecidoComo() {
        return conhecidoComo;
    }

    /**
     * Sets the value of the conhecidoComo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConhecidoComo(String value) {
        this.conhecidoComo = value;
    }

    /**
     * Gets the value of the paisNacionalidade property.
     * 
     * @return
     *     possible object is
     *     {@link Pais }
     *     
     */
    public Pais getPaisNacionalidade() {
        return paisNacionalidade;
    }

    /**
     * Sets the value of the paisNacionalidade property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pais }
     *     
     */
    public void setPaisNacionalidade(Pais value) {
        this.paisNacionalidade = value;
    }

    /**
     * Gets the value of the estadoCivilId property.
     * 
     * @return
     *     possible object is
     *     {@link EstadoCivil }
     *     
     */
    public EstadoCivil getEstadoCivilId() {
        return estadoCivilId;
    }

    /**
     * Sets the value of the estadoCivilId property.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoCivil }
     *     
     */
    public void setEstadoCivilId(EstadoCivil value) {
        this.estadoCivilId = value;
    }

    /**
     * Gets the value of the racaId property.
     * 
     * @return
     *     possible object is
     *     {@link Raca }
     *     
     */
    public Raca getRacaId() {
        return racaId;
    }

    /**
     * Sets the value of the racaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Raca }
     *     
     */
    public void setRacaId(Raca value) {
        this.racaId = value;
    }

    /**
     * Gets the value of the pais property.
     * 
     * @return
     *     possible object is
     *     {@link Pais }
     *     
     */
    public Pais getPais() {
        return pais;
    }

    /**
     * Sets the value of the pais property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pais }
     *     
     */
    public void setPais(Pais value) {
        this.pais = value;
    }

    /**
     * Gets the value of the complemento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * Sets the value of the complemento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComplemento(String value) {
        this.complemento = value;
    }

    /**
     * Gets the value of the ddd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDdd() {
        return ddd;
    }

    /**
     * Sets the value of the ddd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDdd(String value) {
        this.ddd = value;
    }

    /**
     * Gets the value of the dataEmissaoPisPasep property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEmissaoPisPasep() {
        return dataEmissaoPisPasep;
    }

    /**
     * Sets the value of the dataEmissaoPisPasep property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEmissaoPisPasep(XMLGregorianCalendar value) {
        this.dataEmissaoPisPasep = value;
    }

    /**
     * Gets the value of the nuCertificadoMilitar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNuCertificadoMilitar() {
        return nuCertificadoMilitar;
    }

    /**
     * Sets the value of the nuCertificadoMilitar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNuCertificadoMilitar(String value) {
        this.nuCertificadoMilitar = value;
    }

    /**
     * Gets the value of the categoriaCertificadoMilitar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoriaCertificadoMilitar() {
        return categoriaCertificadoMilitar;
    }

    /**
     * Sets the value of the categoriaCertificadoMilitar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoriaCertificadoMilitar(String value) {
        this.categoriaCertificadoMilitar = value;
    }

    /**
     * Gets the value of the especieCertificadoMilitar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEspecieCertificadoMilitar() {
        return especieCertificadoMilitar;
    }

    /**
     * Sets the value of the especieCertificadoMilitar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEspecieCertificadoMilitar(String value) {
        this.especieCertificadoMilitar = value;
    }

    /**
     * Gets the value of the nuPassaporte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNuPassaporte() {
        return nuPassaporte;
    }

    /**
     * Sets the value of the nuPassaporte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNuPassaporte(String value) {
        this.nuPassaporte = value;
    }

    /**
     * Gets the value of the dataEmissaoPassaporte property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEmissaoPassaporte() {
        return dataEmissaoPassaporte;
    }

    /**
     * Sets the value of the dataEmissaoPassaporte property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEmissaoPassaporte(XMLGregorianCalendar value) {
        this.dataEmissaoPassaporte = value;
    }

    /**
     * Gets the value of the paisNaturalizado property.
     * 
     * @return
     *     possible object is
     *     {@link Pais }
     *     
     */
    public Pais getPaisNaturalizado() {
        return paisNaturalizado;
    }

    /**
     * Sets the value of the paisNaturalizado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pais }
     *     
     */
    public void setPaisNaturalizado(Pais value) {
        this.paisNaturalizado = value;
    }

    /**
     * Gets the value of the dataValidadePassaporte property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataValidadePassaporte() {
        return dataValidadePassaporte;
    }

    /**
     * Sets the value of the dataValidadePassaporte property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataValidadePassaporte(XMLGregorianCalendar value) {
        this.dataValidadePassaporte = value;
    }

    /**
     * Gets the value of the localEmissaoPassaporte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalEmissaoPassaporte() {
        return localEmissaoPassaporte;
    }

    /**
     * Sets the value of the localEmissaoPassaporte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalEmissaoPassaporte(String value) {
        this.localEmissaoPassaporte = value;
    }

    /**
     * Gets the value of the nuIdentificacaoTrabalhador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNuIdentificacaoTrabalhador() {
        return nuIdentificacaoTrabalhador;
    }

    /**
     * Sets the value of the nuIdentificacaoTrabalhador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNuIdentificacaoTrabalhador(String value) {
        this.nuIdentificacaoTrabalhador = value;
    }

    /**
     * Gets the value of the nuCartaoNacionalSaude property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNuCartaoNacionalSaude() {
        return nuCartaoNacionalSaude;
    }

    /**
     * Sets the value of the nuCartaoNacionalSaude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNuCartaoNacionalSaude(String value) {
        this.nuCartaoNacionalSaude = value;
    }

    /**
     * Gets the value of the sgConselhoRegional property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSgConselhoRegional() {
        return sgConselhoRegional;
    }

    /**
     * Sets the value of the sgConselhoRegional property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSgConselhoRegional(String value) {
        this.sgConselhoRegional = value;
    }

    /**
     * Gets the value of the ufConselhoRegional property.
     * 
     * @return
     *     possible object is
     *     {@link Uf }
     *     
     */
    public Uf getUfConselhoRegional() {
        return ufConselhoRegional;
    }

    /**
     * Sets the value of the ufConselhoRegional property.
     * 
     * @param value
     *     allowed object is
     *     {@link Uf }
     *     
     */
    public void setUfConselhoRegional(Uf value) {
        this.ufConselhoRegional = value;
    }

    /**
     * Gets the value of the nuIdentidadeEstrangeiro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNuIdentidadeEstrangeiro() {
        return nuIdentidadeEstrangeiro;
    }

    /**
     * Sets the value of the nuIdentidadeEstrangeiro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNuIdentidadeEstrangeiro(String value) {
        this.nuIdentidadeEstrangeiro = value;
    }

    /**
     * Gets the value of the dataChegadaBrasil property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataChegadaBrasil() {
        return dataChegadaBrasil;
    }

    /**
     * Sets the value of the dataChegadaBrasil property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataChegadaBrasil(XMLGregorianCalendar value) {
        this.dataChegadaBrasil = value;
    }

    /**
     * Gets the value of the conjugueBrasileiro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConjugueBrasileiro() {
        return conjugueBrasileiro;
    }

    /**
     * Sets the value of the conjugueBrasileiro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConjugueBrasileiro(String value) {
        this.conjugueBrasileiro = value;
    }

    /**
     * Gets the value of the nuVisto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNuVisto() {
        return nuVisto;
    }

    /**
     * Sets the value of the nuVisto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNuVisto(String value) {
        this.nuVisto = value;
    }

    /**
     * Gets the value of the tpVisto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpVisto() {
        return tpVisto;
    }

    /**
     * Sets the value of the tpVisto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpVisto(String value) {
        this.tpVisto = value;
    }

    /**
     * Gets the value of the dataEmissaoVisto property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEmissaoVisto() {
        return dataEmissaoVisto;
    }

    /**
     * Sets the value of the dataEmissaoVisto property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEmissaoVisto(XMLGregorianCalendar value) {
        this.dataEmissaoVisto = value;
    }

    /**
     * Gets the value of the enderecoRecrutamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnderecoRecrutamento() {
        return enderecoRecrutamento;
    }

    /**
     * Sets the value of the enderecoRecrutamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnderecoRecrutamento(String value) {
        this.enderecoRecrutamento = value;
    }

    /**
     * Gets the value of the numeroRecrutamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroRecrutamento() {
        return numeroRecrutamento;
    }

    /**
     * Sets the value of the numeroRecrutamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroRecrutamento(String value) {
        this.numeroRecrutamento = value;
    }

    /**
     * Gets the value of the complementoRecrutamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComplementoRecrutamento() {
        return complementoRecrutamento;
    }

    /**
     * Sets the value of the complementoRecrutamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComplementoRecrutamento(String value) {
        this.complementoRecrutamento = value;
    }

    /**
     * Gets the value of the bairroRecrutamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBairroRecrutamento() {
        return bairroRecrutamento;
    }

    /**
     * Sets the value of the bairroRecrutamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBairroRecrutamento(String value) {
        this.bairroRecrutamento = value;
    }

    /**
     * Gets the value of the cidadeRecrutamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCidadeRecrutamento() {
        return cidadeRecrutamento;
    }

    /**
     * Sets the value of the cidadeRecrutamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCidadeRecrutamento(String value) {
        this.cidadeRecrutamento = value;
    }

    /**
     * Gets the value of the cepRecrutamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCepRecrutamento() {
        return cepRecrutamento;
    }

    /**
     * Sets the value of the cepRecrutamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCepRecrutamento(String value) {
        this.cepRecrutamento = value;
    }

    /**
     * Gets the value of the ufRecrutamento property.
     * 
     * @return
     *     possible object is
     *     {@link Uf }
     *     
     */
    public Uf getUfRecrutamento() {
        return ufRecrutamento;
    }

    /**
     * Sets the value of the ufRecrutamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Uf }
     *     
     */
    public void setUfRecrutamento(Uf value) {
        this.ufRecrutamento = value;
    }

    /**
     * Gets the value of the paisRecrutamento property.
     * 
     * @return
     *     possible object is
     *     {@link Pais }
     *     
     */
    public Pais getPaisRecrutamento() {
        return paisRecrutamento;
    }

    /**
     * Sets the value of the paisRecrutamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pais }
     *     
     */
    public void setPaisRecrutamento(Pais value) {
        this.paisRecrutamento = value;
    }

    /**
     * Gets the value of the dddRecrutamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDddRecrutamento() {
        return dddRecrutamento;
    }

    /**
     * Sets the value of the dddRecrutamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDddRecrutamento(String value) {
        this.dddRecrutamento = value;
    }

    /**
     * Gets the value of the telefoneRecrutamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefoneRecrutamento() {
        return telefoneRecrutamento;
    }

    /**
     * Sets the value of the telefoneRecrutamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefoneRecrutamento(String value) {
        this.telefoneRecrutamento = value;
    }

    /**
     * Gets the value of the grauInstrucao property.
     * 
     * @return
     *     possible object is
     *     {@link Escolaridade }
     *     
     */
    public Escolaridade getGrauInstrucao() {
        return grauInstrucao;
    }

    /**
     * Sets the value of the grauInstrucao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Escolaridade }
     *     
     */
    public void setGrauInstrucao(Escolaridade value) {
        this.grauInstrucao = value;
    }

    /**
     * Gets the value of the tipoEstabEnsino property.
     * 
     * @return
     *     possible object is
     *     {@link TipoEstabEnsino }
     *     
     */
    public TipoEstabEnsino getTipoEstabEnsino() {
        return tipoEstabEnsino;
    }

    /**
     * Sets the value of the tipoEstabEnsino property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoEstabEnsino }
     *     
     */
    public void setTipoEstabEnsino(TipoEstabEnsino value) {
        this.tipoEstabEnsino = value;
    }

    /**
     * Gets the value of the certificadoEscolar property.
     * 
     * @return
     *     possible object is
     *     {@link CertificadoEscolar }
     *     
     */
    public CertificadoEscolar getCertificadoEscolar() {
        return certificadoEscolar;
    }

    /**
     * Sets the value of the certificadoEscolar property.
     * 
     * @param value
     *     allowed object is
     *     {@link CertificadoEscolar }
     *     
     */
    public void setCertificadoEscolar(CertificadoEscolar value) {
        this.certificadoEscolar = value;
    }

    /**
     * Gets the value of the formacao property.
     * 
     * @return
     *     possible object is
     *     {@link Formacao }
     *     
     */
    public Formacao getFormacao() {
        return formacao;
    }

    /**
     * Sets the value of the formacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Formacao }
     *     
     */
    public void setFormacao(Formacao value) {
        this.formacao = value;
    }

    /**
     * Gets the value of the duracaoCurso property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDuracaoCurso() {
        return duracaoCurso;
    }

    /**
     * Sets the value of the duracaoCurso property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDuracaoCurso(Integer value) {
        this.duracaoCurso = value;
    }

    /**
     * Gets the value of the unidadeDuracaoCurso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnidadeDuracaoCurso() {
        return unidadeDuracaoCurso;
    }

    /**
     * Sets the value of the unidadeDuracaoCurso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnidadeDuracaoCurso(String value) {
        this.unidadeDuracaoCurso = value;
    }

    /**
     * Gets the value of the mesConclusaoCurso property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMesConclusaoCurso() {
        return mesConclusaoCurso;
    }

    /**
     * Sets the value of the mesConclusaoCurso property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMesConclusaoCurso(Integer value) {
        this.mesConclusaoCurso = value;
    }

    /**
     * Gets the value of the anoConclusaoCurso property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAnoConclusaoCurso() {
        return anoConclusaoCurso;
    }

    /**
     * Sets the value of the anoConclusaoCurso property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAnoConclusaoCurso(Integer value) {
        this.anoConclusaoCurso = value;
    }

    /**
     * Gets the value of the instituicaoEnsino property.
     * 
     * @return
     *     possible object is
     *     {@link InstituicaoEnsino }
     *     
     */
    public InstituicaoEnsino getInstituicaoEnsino() {
        return instituicaoEnsino;
    }

    /**
     * Sets the value of the instituicaoEnsino property.
     * 
     * @param value
     *     allowed object is
     *     {@link InstituicaoEnsino }
     *     
     */
    public void setInstituicaoEnsino(InstituicaoEnsino value) {
        this.instituicaoEnsino = value;
    }

    /**
     * Gets the value of the paisInstituicao property.
     * 
     * @return
     *     possible object is
     *     {@link Pais }
     *     
     */
    public Pais getPaisInstituicao() {
        return paisInstituicao;
    }

    /**
     * Sets the value of the paisInstituicao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pais }
     *     
     */
    public void setPaisInstituicao(Pais value) {
        this.paisInstituicao = value;
    }

    /**
     * Gets the value of the centroCustoEmissor property.
     * 
     * @return
     *     possible object is
     *     {@link CentroCusto }
     *     
     */
    public CentroCusto getCentroCustoEmissor() {
        return centroCustoEmissor;
    }

    /**
     * Sets the value of the centroCustoEmissor property.
     * 
     * @param value
     *     allowed object is
     *     {@link CentroCusto }
     *     
     */
    public void setCentroCustoEmissor(CentroCusto value) {
        this.centroCustoEmissor = value;
    }

    /**
     * Gets the value of the centroCustoReceptor property.
     * 
     * @return
     *     possible object is
     *     {@link CentroCusto }
     *     
     */
    public CentroCusto getCentroCustoReceptor() {
        return centroCustoReceptor;
    }

    /**
     * Sets the value of the centroCustoReceptor property.
     * 
     * @param value
     *     allowed object is
     *     {@link CentroCusto }
     *     
     */
    public void setCentroCustoReceptor(CentroCusto value) {
        this.centroCustoReceptor = value;
    }

    /**
     * Gets the value of the elementoPep property.
     * 
     * @return
     *     possible object is
     *     {@link Pep }
     *     
     */
    public Pep getElementoPep() {
        return elementoPep;
    }

    /**
     * Sets the value of the elementoPep property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pep }
     *     
     */
    public void setElementoPep(Pep value) {
        this.elementoPep = value;
    }

    /**
     * Gets the value of the nivelSalarial property.
     * 
     * @return
     *     possible object is
     *     {@link NivelSalarial }
     *     
     */
    public NivelSalarial getNivelSalarial() {
        return nivelSalarial;
    }

    /**
     * Sets the value of the nivelSalarial property.
     * 
     * @param value
     *     allowed object is
     *     {@link NivelSalarial }
     *     
     */
    public void setNivelSalarial(NivelSalarial value) {
        this.nivelSalarial = value;
    }

    /**
     * Gets the value of the horarioTrabalho property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHorarioTrabalho() {
        return horarioTrabalho;
    }

    /**
     * Sets the value of the horarioTrabalho property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHorarioTrabalho(String value) {
        this.horarioTrabalho = value;
    }

    /**
     * Gets the value of the periodoExperiencia property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPeriodoExperiencia() {
        return periodoExperiencia;
    }

    /**
     * Sets the value of the periodoExperiencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPeriodoExperiencia(Integer value) {
        this.periodoExperiencia = value;
    }

    /**
     * Gets the value of the periodoExperiencia1 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPeriodoExperiencia1() {
        return periodoExperiencia1;
    }

    /**
     * Sets the value of the periodoExperiencia1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPeriodoExperiencia1(Integer value) {
        this.periodoExperiencia1 = value;
    }

    /**
     * Gets the value of the periodoExperiencia2 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPeriodoExperiencia2() {
        return periodoExperiencia2;
    }

    /**
     * Sets the value of the periodoExperiencia2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPeriodoExperiencia2(Integer value) {
        this.periodoExperiencia2 = value;
    }

    /**
     * Gets the value of the exposicaoAgenteNocivo property.
     * 
     * @return
     *     possible object is
     *     {@link ExposicaoAgenteNocivo }
     *     
     */
    public ExposicaoAgenteNocivo getExposicaoAgenteNocivo() {
        return exposicaoAgenteNocivo;
    }

    /**
     * Sets the value of the exposicaoAgenteNocivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExposicaoAgenteNocivo }
     *     
     */
    public void setExposicaoAgenteNocivo(ExposicaoAgenteNocivo value) {
        this.exposicaoAgenteNocivo = value;
    }

    /**
     * Gets the value of the vinculoEmpregaticio property.
     * 
     * @return
     *     possible object is
     *     {@link VinculoEmpregaticio }
     *     
     */
    public VinculoEmpregaticio getVinculoEmpregaticio() {
        return vinculoEmpregaticio;
    }

    /**
     * Sets the value of the vinculoEmpregaticio property.
     * 
     * @param value
     *     allowed object is
     *     {@link VinculoEmpregaticio }
     *     
     */
    public void setVinculoEmpregaticio(VinculoEmpregaticio value) {
        this.vinculoEmpregaticio = value;
    }

    /**
     * Gets the value of the aposentado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAposentado() {
        return aposentado;
    }

    /**
     * Sets the value of the aposentado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAposentado(String value) {
        this.aposentado = value;
    }

    /**
     * Gets the value of the categoriaTrabalhador property.
     * 
     * @return
     *     possible object is
     *     {@link CategoriaTrabalhador }
     *     
     */
    public CategoriaTrabalhador getCategoriaTrabalhador() {
        return categoriaTrabalhador;
    }

    /**
     * Sets the value of the categoriaTrabalhador property.
     * 
     * @param value
     *     allowed object is
     *     {@link CategoriaTrabalhador }
     *     
     */
    public void setCategoriaTrabalhador(CategoriaTrabalhador value) {
        this.categoriaTrabalhador = value;
    }

    /**
     * Gets the value of the adicionalInsalubridade property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdicionalInsalubridade() {
        return adicionalInsalubridade;
    }

    /**
     * Sets the value of the adicionalInsalubridade property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdicionalInsalubridade(String value) {
        this.adicionalInsalubridade = value;
    }

    /**
     * Gets the value of the porcentagemInsalubridade property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPorcentagemInsalubridade() {
        return porcentagemInsalubridade;
    }

    /**
     * Sets the value of the porcentagemInsalubridade property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPorcentagemInsalubridade(Double value) {
        this.porcentagemInsalubridade = value;
    }

    /**
     * Gets the value of the vinculo property.
     * 
     * @return
     *     possible object is
     *     {@link Vinculo }
     *     
     */
    public Vinculo getVinculo() {
        return vinculo;
    }

    /**
     * Sets the value of the vinculo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Vinculo }
     *     
     */
    public void setVinculo(Vinculo value) {
        this.vinculo = value;
    }

    /**
     * Gets the value of the adicionalPericulosidade property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdicionalPericulosidade() {
        return adicionalPericulosidade;
    }

    /**
     * Sets the value of the adicionalPericulosidade property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdicionalPericulosidade(String value) {
        this.adicionalPericulosidade = value;
    }

    /**
     * Gets the value of the porcentagemPericulosidade property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPorcentagemPericulosidade() {
        return porcentagemPericulosidade;
    }

    /**
     * Sets the value of the porcentagemPericulosidade property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPorcentagemPericulosidade(Double value) {
        this.porcentagemPericulosidade = value;
    }

    /**
     * Gets the value of the sindicato property.
     * 
     * @return
     *     possible object is
     *     {@link Sindicato }
     *     
     */
    public Sindicato getSindicato() {
        return sindicato;
    }

    /**
     * Sets the value of the sindicato property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sindicato }
     *     
     */
    public void setSindicato(Sindicato value) {
        this.sindicato = value;
    }

    /**
     * Gets the value of the valorMensalidadeSindical property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getValorMensalidadeSindical() {
        return valorMensalidadeSindical;
    }

    /**
     * Sets the value of the valorMensalidadeSindical property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setValorMensalidadeSindical(Double value) {
        this.valorMensalidadeSindical = value;
    }

    /**
     * Gets the value of the codigoMensalidadeSindical property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCodigoMensalidadeSindical() {
        return codigoMensalidadeSindical;
    }

    /**
     * Sets the value of the codigoMensalidadeSindical property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCodigoMensalidadeSindical(Double value) {
        this.codigoMensalidadeSindical = value;
    }

    /**
     * Gets the value of the contribuicaoAssistencial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContribuicaoAssistencial() {
        return contribuicaoAssistencial;
    }

    /**
     * Sets the value of the contribuicaoAssistencial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContribuicaoAssistencial(String value) {
        this.contribuicaoAssistencial = value;
    }

    /**
     * Gets the value of the contribuicaoConfederativa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContribuicaoConfederativa() {
        return contribuicaoConfederativa;
    }

    /**
     * Sets the value of the contribuicaoConfederativa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContribuicaoConfederativa(String value) {
        this.contribuicaoConfederativa = value;
    }

    /**
     * Gets the value of the formaPagamento property.
     * 
     * @return
     *     possible object is
     *     {@link FormaPagamento }
     *     
     */
    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    /**
     * Sets the value of the formaPagamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormaPagamento }
     *     
     */
    public void setFormaPagamento(FormaPagamento value) {
        this.formaPagamento = value;
    }

    /**
     * Gets the value of the banco property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBanco() {
        return banco;
    }

    /**
     * Sets the value of the banco property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBanco(String value) {
        this.banco = value;
    }

    /**
     * Gets the value of the numeroContaBancaria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroContaBancaria() {
        return numeroContaBancaria;
    }

    /**
     * Sets the value of the numeroContaBancaria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroContaBancaria(String value) {
        this.numeroContaBancaria = value;
    }

    /**
     * Gets the value of the digitoContaCorrente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDigitoContaCorrente() {
        return digitoContaCorrente;
    }

    /**
     * Sets the value of the digitoContaCorrente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDigitoContaCorrente(String value) {
        this.digitoContaCorrente = value;
    }

    /**
     * Gets the value of the grupoSanguineo property.
     * 
     * @return
     *     possible object is
     *     {@link GrupoSanguineo }
     *     
     */
    public GrupoSanguineo getGrupoSanguineo() {
        return grupoSanguineo;
    }

    /**
     * Sets the value of the grupoSanguineo property.
     * 
     * @param value
     *     allowed object is
     *     {@link GrupoSanguineo }
     *     
     */
    public void setGrupoSanguineo(GrupoSanguineo value) {
        this.grupoSanguineo = value;
    }

    /**
     * Gets the value of the fatorRh property.
     * 
     * @return
     *     possible object is
     *     {@link FatorRh }
     *     
     */
    public FatorRh getFatorRh() {
        return fatorRh;
    }

    /**
     * Sets the value of the fatorRh property.
     * 
     * @param value
     *     allowed object is
     *     {@link FatorRh }
     *     
     */
    public void setFatorRh(FatorRh value) {
        this.fatorRh = value;
    }

    /**
     * Gets the value of the codigoTomador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoTomador() {
        return codigoTomador;
    }

    /**
     * Sets the value of the codigoTomador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoTomador(String value) {
        this.codigoTomador = value;
    }

    /**
     * Gets the value of the salaoRefeitorio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalaoRefeitorio() {
        return salaoRefeitorio;
    }

    /**
     * Sets the value of the salaoRefeitorio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalaoRefeitorio(String value) {
        this.salaoRefeitorio = value;
    }

    /**
     * Gets the value of the codigoRefeicao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoRefeicao() {
        return codigoRefeicao;
    }

    /**
     * Sets the value of the codigoRefeicao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoRefeicao(String value) {
        this.codigoRefeicao = value;
    }

    /**
     * Gets the value of the funcionarioRefeitorio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFuncionarioRefeitorio() {
        return funcionarioRefeitorio;
    }

    /**
     * Sets the value of the funcionarioRefeitorio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFuncionarioRefeitorio(String value) {
        this.funcionarioRefeitorio = value;
    }

    /**
     * Gets the value of the postoPagamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostoPagamento() {
        return postoPagamento;
    }

    /**
     * Sets the value of the postoPagamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostoPagamento(String value) {
        this.postoPagamento = value;
    }

    /**
     * Gets the value of the gremio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGremio() {
        return gremio;
    }

    /**
     * Sets the value of the gremio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGremio(String value) {
        this.gremio = value;
    }

    /**
     * Gets the value of the superintendencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuperintendencia() {
        return superintendencia;
    }

    /**
     * Sets the value of the superintendencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuperintendencia(String value) {
        this.superintendencia = value;
    }

    /**
     * Gets the value of the nivel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNivel() {
        return nivel;
    }

    /**
     * Sets the value of the nivel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNivel(String value) {
        this.nivel = value;
    }

    /**
     * Gets the value of the tipoComunicacao property.
     * 
     * @return
     *     possible object is
     *     {@link TipoComunicacao }
     *     
     */
    public TipoComunicacao getTipoComunicacao() {
        return tipoComunicacao;
    }

    /**
     * Sets the value of the tipoComunicacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoComunicacao }
     *     
     */
    public void setTipoComunicacao(TipoComunicacao value) {
        this.tipoComunicacao = value;
    }

    /**
     * Gets the value of the tipoComunicacaoRecrut property.
     * 
     * @return
     *     possible object is
     *     {@link TipoComunicacao }
     *     
     */
    public TipoComunicacao getTipoComunicacaoRecrut() {
        return tipoComunicacaoRecrut;
    }

    /**
     * Sets the value of the tipoComunicacaoRecrut property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoComunicacao }
     *     
     */
    public void setTipoComunicacaoRecrut(TipoComunicacao value) {
        this.tipoComunicacaoRecrut = value;
    }

    /**
     * Gets the value of the tipoDemissao property.
     * 
     * @return
     *     possible object is
     *     {@link TipoDemissao }
     *     
     */
    public TipoDemissao getTipoDemissao() {
        return tipoDemissao;
    }

    /**
     * Sets the value of the tipoDemissao property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoDemissao }
     *     
     */
    public void setTipoDemissao(TipoDemissao value) {
        this.tipoDemissao = value;
    }

    /**
     * Gets the value of the eapMultiEmpresa property.
     * 
     * @return
     *     possible object is
     *     {@link EapMultiEmpresa }
     *     
     */
    public EapMultiEmpresa getEapMultiEmpresa() {
        return eapMultiEmpresa;
    }

    /**
     * Sets the value of the eapMultiEmpresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link EapMultiEmpresa }
     *     
     */
    public void setEapMultiEmpresa(EapMultiEmpresa value) {
        this.eapMultiEmpresa = value;
    }

    /**
     * Gets the value of the subgrupo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubgrupo() {
        return subgrupo;
    }

    /**
     * Sets the value of the subgrupo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubgrupo(String value) {
        this.subgrupo = value;
    }

    /**
     * Gets the value of the dataSolicitacaoDocumentacao property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataSolicitacaoDocumentacao() {
        return dataSolicitacaoDocumentacao;
    }

    /**
     * Sets the value of the dataSolicitacaoDocumentacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataSolicitacaoDocumentacao(XMLGregorianCalendar value) {
        this.dataSolicitacaoDocumentacao = value;
    }

    /**
     * Gets the value of the dataEnvioSap property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEnvioSap() {
        return dataEnvioSap;
    }

    /**
     * Sets the value of the dataEnvioSap property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEnvioSap(XMLGregorianCalendar value) {
        this.dataEnvioSap = value;
    }

    /**
     * Gets the value of the dataAdmSap property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAdmSap() {
        return dataAdmSap;
    }

    /**
     * Sets the value of the dataAdmSap property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAdmSap(XMLGregorianCalendar value) {
        this.dataAdmSap = value;
    }

    /**
     * Gets the value of the necessitaTransporte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNecessitaTransporte() {
        return necessitaTransporte;
    }

    /**
     * Sets the value of the necessitaTransporte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNecessitaTransporte(String value) {
        this.necessitaTransporte = value;
    }

    /**
     * Gets the value of the observacaoTransporte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacaoTransporte() {
        return observacaoTransporte;
    }

    /**
     * Sets the value of the observacaoTransporte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacaoTransporte(String value) {
        this.observacaoTransporte = value;
    }

    /**
     * Gets the value of the observacaoRecontratacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacaoRecontratacao() {
        return observacaoRecontratacao;
    }

    /**
     * Sets the value of the observacaoRecontratacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacaoRecontratacao(String value) {
        this.observacaoRecontratacao = value;
    }

    /**
     * Gets the value of the somaCargaHorariaHoras property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSomaCargaHorariaHoras() {
        return somaCargaHorariaHoras;
    }

    /**
     * Sets the value of the somaCargaHorariaHoras property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSomaCargaHorariaHoras(Integer value) {
        this.somaCargaHorariaHoras = value;
    }

    /**
     * Gets the value of the somaCargaHorariaMinutos property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSomaCargaHorariaMinutos() {
        return somaCargaHorariaMinutos;
    }

    /**
     * Sets the value of the somaCargaHorariaMinutos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSomaCargaHorariaMinutos(Integer value) {
        this.somaCargaHorariaMinutos = value;
    }

    /**
     * Gets the value of the valorTotalTreinamento property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getValorTotalTreinamento() {
        return valorTotalTreinamento;
    }

    /**
     * Sets the value of the valorTotalTreinamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setValorTotalTreinamento(Double value) {
        this.valorTotalTreinamento = value;
    }

    /**
     * Gets the value of the cboDescricao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCboDescricao() {
        return cboDescricao;
    }

    /**
     * Sets the value of the cboDescricao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCboDescricao(String value) {
        this.cboDescricao = value;
    }

    /**
     * Gets the value of the possuiFoto property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPossuiFoto() {
        return possuiFoto;
    }

    /**
     * Sets the value of the possuiFoto property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPossuiFoto(Boolean value) {
        this.possuiFoto = value;
    }

    /**
     * Gets the value of the beneficios property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the beneficios property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBeneficios().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Beneficio }
     * 
     * 
     */
    public List<Beneficio> getBeneficios() {
        if (beneficios == null) {
            beneficios = new ArrayList<Beneficio>();
        }
        return this.beneficios;
    }

    /**
     * Gets the value of the direitoTransporte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireitoTransporte() {
        return direitoTransporte;
    }

    /**
     * Sets the value of the direitoTransporte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireitoTransporte(String value) {
        this.direitoTransporte = value;
    }

    /**
     * Gets the value of the tempoAguarAso property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTempoAguarAso() {
        return tempoAguarAso;
    }

    /**
     * Sets the value of the tempoAguarAso property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTempoAguarAso(Integer value) {
        this.tempoAguarAso = value;
    }

    /**
     * Gets the value of the dataEntradaUltimaEtapa property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEntradaUltimaEtapa() {
        return dataEntradaUltimaEtapa;
    }

    /**
     * Sets the value of the dataEntradaUltimaEtapa property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEntradaUltimaEtapa(XMLGregorianCalendar value) {
        this.dataEntradaUltimaEtapa = value;
    }

    /**
     * Gets the value of the fcFluxo property.
     * 
     * @return
     *     possible object is
     *     {@link FcFluxo }
     *     
     */
    public FcFluxo getFcFluxo() {
        return fcFluxo;
    }

    /**
     * Sets the value of the fcFluxo property.
     * 
     * @param value
     *     allowed object is
     *     {@link FcFluxo }
     *     
     */
    public void setFcFluxo(FcFluxo value) {
        this.fcFluxo = value;
    }

    /**
     * Gets the value of the statusContratacao property.
     * 
     * @return
     *     possible object is
     *     {@link StatusContratacao }
     *     
     */
    public StatusContratacao getStatusContratacao() {
        return statusContratacao;
    }

    /**
     * Sets the value of the statusContratacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusContratacao }
     *     
     */
    public void setStatusContratacao(StatusContratacao value) {
        this.statusContratacao = value;
    }

    /**
     * Gets the value of the nivelEncarregado property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNivelEncarregado() {
        return nivelEncarregado;
    }

    /**
     * Sets the value of the nivelEncarregado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNivelEncarregado(Integer value) {
        this.nivelEncarregado = value;
    }

    /**
     * Gets the value of the telefoneCompleto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefoneCompleto() {
        return telefoneCompleto;
    }

    /**
     * Sets the value of the telefoneCompleto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefoneCompleto(String value) {
        this.telefoneCompleto = value;
    }

    /**
     * Gets the value of the escolaridade property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEscolaridade() {
        return escolaridade;
    }

    /**
     * Sets the value of the escolaridade property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEscolaridade(String value) {
        this.escolaridade = value;
    }

    /**
     * Gets the value of the estadoCivil property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoCivil() {
        return estadoCivil;
    }

    /**
     * Sets the value of the estadoCivil property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoCivil(String value) {
        this.estadoCivil = value;
    }

    /**
     * Gets the value of the stDataAdmissao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataAdmissao() {
        return stDataAdmissao;
    }

    /**
     * Sets the value of the stDataAdmissao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataAdmissao(String value) {
        this.stDataAdmissao = value;
    }

    /**
     * Gets the value of the stDataDemissao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataDemissao() {
        return stDataDemissao;
    }

    /**
     * Sets the value of the stDataDemissao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataDemissao(String value) {
        this.stDataDemissao = value;
    }

    /**
     * Gets the value of the stDataRecebimentoAso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataRecebimentoAso() {
        return stDataRecebimentoAso;
    }

    /**
     * Sets the value of the stDataRecebimentoAso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataRecebimentoAso(String value) {
        this.stDataRecebimentoAso = value;
    }

    /**
     * Gets the value of the stDataEncaminhadoExame property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataEncaminhadoExame() {
        return stDataEncaminhadoExame;
    }

    /**
     * Sets the value of the stDataEncaminhadoExame property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataEncaminhadoExame(String value) {
        this.stDataEncaminhadoExame = value;
    }

    /**
     * Gets the value of the stDataInicioTreinCcpr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataInicioTreinCcpr() {
        return stDataInicioTreinCcpr;
    }

    /**
     * Sets the value of the stDataInicioTreinCcpr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataInicioTreinCcpr(String value) {
        this.stDataInicioTreinCcpr = value;
    }

    /**
     * Gets the value of the stDataFimTreinCcpr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataFimTreinCcpr() {
        return stDataFimTreinCcpr;
    }

    /**
     * Sets the value of the stDataFimTreinCcpr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataFimTreinCcpr(String value) {
        this.stDataFimTreinCcpr = value;
    }

    /**
     * Gets the value of the stDataTreinamentoPB property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataTreinamentoPB() {
        return stDataTreinamentoPB;
    }

    /**
     * Sets the value of the stDataTreinamentoPB property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataTreinamentoPB(String value) {
        this.stDataTreinamentoPB = value;
    }

    /**
     * Gets the value of the stDataEntregaDocsDp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataEntregaDocsDp() {
        return stDataEntregaDocsDp;
    }

    /**
     * Sets the value of the stDataEntregaDocsDp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataEntregaDocsDp(String value) {
        this.stDataEntregaDocsDp = value;
    }

    /**
     * Gets the value of the stDataRecebimentoCtps property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataRecebimentoCtps() {
        return stDataRecebimentoCtps;
    }

    /**
     * Sets the value of the stDataRecebimentoCtps property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataRecebimentoCtps(String value) {
        this.stDataRecebimentoCtps = value;
    }

    /**
     * Gets the value of the stDataSolicitacaoCracha property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataSolicitacaoCracha() {
        return stDataSolicitacaoCracha;
    }

    /**
     * Sets the value of the stDataSolicitacaoCracha property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataSolicitacaoCracha(String value) {
        this.stDataSolicitacaoCracha = value;
    }

    /**
     * Gets the value of the stDataLiberacaoCracha property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataLiberacaoCracha() {
        return stDataLiberacaoCracha;
    }

    /**
     * Sets the value of the stDataLiberacaoCracha property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataLiberacaoCracha(String value) {
        this.stDataLiberacaoCracha = value;
    }

    /**
     * Gets the value of the stDataTransferencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataTransferencia() {
        return stDataTransferencia;
    }

    /**
     * Sets the value of the stDataTransferencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataTransferencia(String value) {
        this.stDataTransferencia = value;
    }

    /**
     * Gets the value of the stDataTransfSaida property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataTransfSaida() {
        return stDataTransfSaida;
    }

    /**
     * Sets the value of the stDataTransfSaida property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataTransfSaida(String value) {
        this.stDataTransfSaida = value;
    }

    /**
     * Gets the value of the stDataCadastro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataCadastro() {
        return stDataCadastro;
    }

    /**
     * Sets the value of the stDataCadastro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataCadastro(String value) {
        this.stDataCadastro = value;
    }

    /**
     * Gets the value of the stDataNascimento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataNascimento() {
        return stDataNascimento;
    }

    /**
     * Sets the value of the stDataNascimento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataNascimento(String value) {
        this.stDataNascimento = value;
    }

    /**
     * Gets the value of the stDataPrevisaoDesmobilizacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataPrevisaoDesmobilizacao() {
        return stDataPrevisaoDesmobilizacao;
    }

    /**
     * Sets the value of the stDataPrevisaoDesmobilizacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataPrevisaoDesmobilizacao(String value) {
        this.stDataPrevisaoDesmobilizacao = value;
    }

    /**
     * Gets the value of the stDataRecrutamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataRecrutamento() {
        return stDataRecrutamento;
    }

    /**
     * Sets the value of the stDataRecrutamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataRecrutamento(String value) {
        this.stDataRecrutamento = value;
    }

    /**
     * Gets the value of the stDataEntradaArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataEntradaArea() {
        return stDataEntradaArea;
    }

    /**
     * Sets the value of the stDataEntradaArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataEntradaArea(String value) {
        this.stDataEntradaArea = value;
    }

    /**
     * Gets the value of the stDataDesistenteReprovado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataDesistenteReprovado() {
        return stDataDesistenteReprovado;
    }

    /**
     * Sets the value of the stDataDesistenteReprovado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataDesistenteReprovado(String value) {
        this.stDataDesistenteReprovado = value;
    }

    /**
     * Gets the value of the stDataChegadaPrevista property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataChegadaPrevista() {
        return stDataChegadaPrevista;
    }

    /**
     * Sets the value of the stDataChegadaPrevista property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataChegadaPrevista(String value) {
        this.stDataChegadaPrevista = value;
    }

    /**
     * Gets the value of the stDataExameMedico property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataExameMedico() {
        return stDataExameMedico;
    }

    /**
     * Sets the value of the stDataExameMedico property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataExameMedico(String value) {
        this.stDataExameMedico = value;
    }

    /**
     * Gets the value of the stDataAdmissaoAprovada property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataAdmissaoAprovada() {
        return stDataAdmissaoAprovada;
    }

    /**
     * Sets the value of the stDataAdmissaoAprovada property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataAdmissaoAprovada(String value) {
        this.stDataAdmissaoAprovada = value;
    }

    /**
     * Gets the value of the stDataProtocoloIf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataProtocoloIf() {
        return stDataProtocoloIf;
    }

    /**
     * Sets the value of the stDataProtocoloIf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataProtocoloIf(String value) {
        this.stDataProtocoloIf = value;
    }

    /**
     * Gets the value of the stDataEnvioDocCrachaDp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataEnvioDocCrachaDp() {
        return stDataEnvioDocCrachaDp;
    }

    /**
     * Sets the value of the stDataEnvioDocCrachaDp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataEnvioDocCrachaDp(String value) {
        this.stDataEnvioDocCrachaDp = value;
    }

    /**
     * Gets the value of the stDataEmissaoRG property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataEmissaoRG() {
        return stDataEmissaoRG;
    }

    /**
     * Sets the value of the stDataEmissaoRG property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataEmissaoRG(String value) {
        this.stDataEmissaoRG = value;
    }

    /**
     * Gets the value of the stDataInicioProjeto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataInicioProjeto() {
        return stDataInicioProjeto;
    }

    /**
     * Sets the value of the stDataInicioProjeto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataInicioProjeto(String value) {
        this.stDataInicioProjeto = value;
    }

    /**
     * Gets the value of the stDataMobilizacaoFamilia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataMobilizacaoFamilia() {
        return stDataMobilizacaoFamilia;
    }

    /**
     * Sets the value of the stDataMobilizacaoFamilia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataMobilizacaoFamilia(String value) {
        this.stDataMobilizacaoFamilia = value;
    }

    /**
     * Gets the value of the stDataDesmobilizacaoFamilia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataDesmobilizacaoFamilia() {
        return stDataDesmobilizacaoFamilia;
    }

    /**
     * Sets the value of the stDataDesmobilizacaoFamilia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataDesmobilizacaoFamilia(String value) {
        this.stDataDesmobilizacaoFamilia = value;
    }

    /**
     * Gets the value of the stDataMudanca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataMudanca() {
        return stDataMudanca;
    }

    /**
     * Sets the value of the stDataMudanca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataMudanca(String value) {
        this.stDataMudanca = value;
    }

    /**
     * Gets the value of the stDataEmissaoCTPS property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataEmissaoCTPS() {
        return stDataEmissaoCTPS;
    }

    /**
     * Sets the value of the stDataEmissaoCTPS property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataEmissaoCTPS(String value) {
        this.stDataEmissaoCTPS = value;
    }

    /**
     * Gets the value of the stDataEmissaoTituloEleitor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataEmissaoTituloEleitor() {
        return stDataEmissaoTituloEleitor;
    }

    /**
     * Sets the value of the stDataEmissaoTituloEleitor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataEmissaoTituloEleitor(String value) {
        this.stDataEmissaoTituloEleitor = value;
    }

    /**
     * Gets the value of the stDataEmissaoCNH property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataEmissaoCNH() {
        return stDataEmissaoCNH;
    }

    /**
     * Sets the value of the stDataEmissaoCNH property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataEmissaoCNH(String value) {
        this.stDataEmissaoCNH = value;
    }

    /**
     * Gets the value of the stDataValidadeCNH property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataValidadeCNH() {
        return stDataValidadeCNH;
    }

    /**
     * Sets the value of the stDataValidadeCNH property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataValidadeCNH(String value) {
        this.stDataValidadeCNH = value;
    }

    /**
     * Gets the value of the stDataEmissaoCPF property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataEmissaoCPF() {
        return stDataEmissaoCPF;
    }

    /**
     * Sets the value of the stDataEmissaoCPF property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataEmissaoCPF(String value) {
        this.stDataEmissaoCPF = value;
    }

    /**
     * Gets the value of the stDataEmissaoRegistro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataEmissaoRegistro() {
        return stDataEmissaoRegistro;
    }

    /**
     * Sets the value of the stDataEmissaoRegistro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataEmissaoRegistro(String value) {
        this.stDataEmissaoRegistro = value;
    }

    /**
     * Gets the value of the stDataHomologacaoDemissao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataHomologacaoDemissao() {
        return stDataHomologacaoDemissao;
    }

    /**
     * Sets the value of the stDataHomologacaoDemissao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataHomologacaoDemissao(String value) {
        this.stDataHomologacaoDemissao = value;
    }

    /**
     * Gets the value of the stDataExperiencia30Dias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataExperiencia30Dias() {
        return stDataExperiencia30Dias;
    }

    /**
     * Sets the value of the stDataExperiencia30Dias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataExperiencia30Dias(String value) {
        this.stDataExperiencia30Dias = value;
    }

    /**
     * Gets the value of the stDataExperiencia60Dias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataExperiencia60Dias() {
        return stDataExperiencia60Dias;
    }

    /**
     * Sets the value of the stDataExperiencia60Dias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataExperiencia60Dias(String value) {
        this.stDataExperiencia60Dias = value;
    }

    /**
     * Gets the value of the stDataEntrevistaTecnica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataEntrevistaTecnica() {
        return stDataEntrevistaTecnica;
    }

    /**
     * Sets the value of the stDataEntrevistaTecnica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataEntrevistaTecnica(String value) {
        this.stDataEntrevistaTecnica = value;
    }

    /**
     * Gets the value of the stDataSolicitacaoCV property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataSolicitacaoCV() {
        return stDataSolicitacaoCV;
    }

    /**
     * Sets the value of the stDataSolicitacaoCV property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataSolicitacaoCV(String value) {
        this.stDataSolicitacaoCV = value;
    }

    /**
     * Gets the value of the stDataRecebimentoCV property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataRecebimentoCV() {
        return stDataRecebimentoCV;
    }

    /**
     * Sets the value of the stDataRecebimentoCV property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataRecebimentoCV(String value) {
        this.stDataRecebimentoCV = value;
    }

    /**
     * Gets the value of the stDataEnvioCVparaPB property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataEnvioCVparaPB() {
        return stDataEnvioCVparaPB;
    }

    /**
     * Sets the value of the stDataEnvioCVparaPB property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataEnvioCVparaPB(String value) {
        this.stDataEnvioCVparaPB = value;
    }

    /**
     * Gets the value of the stDataRecebimentoCVdaPB property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataRecebimentoCVdaPB() {
        return stDataRecebimentoCVdaPB;
    }

    /**
     * Sets the value of the stDataRecebimentoCVdaPB property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataRecebimentoCVdaPB(String value) {
        this.stDataRecebimentoCVdaPB = value;
    }

    /**
     * Gets the value of the stDataInicioProcessoDemissao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataInicioProcessoDemissao() {
        return stDataInicioProcessoDemissao;
    }

    /**
     * Sets the value of the stDataInicioProcessoDemissao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataInicioProcessoDemissao(String value) {
        this.stDataInicioProcessoDemissao = value;
    }

    /**
     * Gets the value of the stDataAssinaturaContrato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataAssinaturaContrato() {
        return stDataAssinaturaContrato;
    }

    /**
     * Sets the value of the stDataAssinaturaContrato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataAssinaturaContrato(String value) {
        this.stDataAssinaturaContrato = value;
    }

    /**
     * Gets the value of the stDataBaixaCrachaProvisorio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataBaixaCrachaProvisorio() {
        return stDataBaixaCrachaProvisorio;
    }

    /**
     * Sets the value of the stDataBaixaCrachaProvisorio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataBaixaCrachaProvisorio(String value) {
        this.stDataBaixaCrachaProvisorio = value;
    }

    /**
     * Gets the value of the stDataRecCrachaProvisorio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataRecCrachaProvisorio() {
        return stDataRecCrachaProvisorio;
    }

    /**
     * Sets the value of the stDataRecCrachaProvisorio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataRecCrachaProvisorio(String value) {
        this.stDataRecCrachaProvisorio = value;
    }

    /**
     * Gets the value of the stDataSolicPassagemMob property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataSolicPassagemMob() {
        return stDataSolicPassagemMob;
    }

    /**
     * Sets the value of the stDataSolicPassagemMob property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataSolicPassagemMob(String value) {
        this.stDataSolicPassagemMob = value;
    }

    /**
     * Gets the value of the stDataEntregaDocs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataEntregaDocs() {
        return stDataEntregaDocs;
    }

    /**
     * Sets the value of the stDataEntregaDocs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataEntregaDocs(String value) {
        this.stDataEntregaDocs = value;
    }

    /**
     * Gets the value of the stDataAux1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataAux1() {
        return stDataAux1;
    }

    /**
     * Sets the value of the stDataAux1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataAux1(String value) {
        this.stDataAux1 = value;
    }

    /**
     * Gets the value of the stDataAux2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataAux2() {
        return stDataAux2;
    }

    /**
     * Sets the value of the stDataAux2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataAux2(String value) {
        this.stDataAux2 = value;
    }

    /**
     * Gets the value of the stDataAux3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataAux3() {
        return stDataAux3;
    }

    /**
     * Sets the value of the stDataAux3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataAux3(String value) {
        this.stDataAux3 = value;
    }

    /**
     * Gets the value of the stDataAux4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataAux4() {
        return stDataAux4;
    }

    /**
     * Sets the value of the stDataAux4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataAux4(String value) {
        this.stDataAux4 = value;
    }

    /**
     * Gets the value of the stDataAux5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataAux5() {
        return stDataAux5;
    }

    /**
     * Sets the value of the stDataAux5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataAux5(String value) {
        this.stDataAux5 = value;
    }

    /**
     * Gets the value of the stDataAux6 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataAux6() {
        return stDataAux6;
    }

    /**
     * Sets the value of the stDataAux6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataAux6(String value) {
        this.stDataAux6 = value;
    }

    /**
     * Gets the value of the stDataAux7 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataAux7() {
        return stDataAux7;
    }

    /**
     * Sets the value of the stDataAux7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataAux7(String value) {
        this.stDataAux7 = value;
    }

    /**
     * Gets the value of the stDataAux8 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataAux8() {
        return stDataAux8;
    }

    /**
     * Sets the value of the stDataAux8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataAux8(String value) {
        this.stDataAux8 = value;
    }

    /**
     * Gets the value of the stDataAux9 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataAux9() {
        return stDataAux9;
    }

    /**
     * Sets the value of the stDataAux9 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataAux9(String value) {
        this.stDataAux9 = value;
    }

    /**
     * Gets the value of the stDataAux10 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataAux10() {
        return stDataAux10;
    }

    /**
     * Sets the value of the stDataAux10 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataAux10(String value) {
        this.stDataAux10 = value;
    }

    /**
     * Gets the value of the stDataAux11 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataAux11() {
        return stDataAux11;
    }

    /**
     * Sets the value of the stDataAux11 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataAux11(String value) {
        this.stDataAux11 = value;
    }

    /**
     * Gets the value of the stDataAux12 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataAux12() {
        return stDataAux12;
    }

    /**
     * Sets the value of the stDataAux12 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataAux12(String value) {
        this.stDataAux12 = value;
    }

    /**
     * Gets the value of the stDataAux13 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataAux13() {
        return stDataAux13;
    }

    /**
     * Sets the value of the stDataAux13 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataAux13(String value) {
        this.stDataAux13 = value;
    }

    /**
     * Gets the value of the stDataAux14 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataAux14() {
        return stDataAux14;
    }

    /**
     * Sets the value of the stDataAux14 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataAux14(String value) {
        this.stDataAux14 = value;
    }

    /**
     * Gets the value of the stDataAux15 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataAux15() {
        return stDataAux15;
    }

    /**
     * Sets the value of the stDataAux15 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataAux15(String value) {
        this.stDataAux15 = value;
    }

    /**
     * Gets the value of the stDataEmissaoPisPasep property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataEmissaoPisPasep() {
        return stDataEmissaoPisPasep;
    }

    /**
     * Sets the value of the stDataEmissaoPisPasep property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataEmissaoPisPasep(String value) {
        this.stDataEmissaoPisPasep = value;
    }

    /**
     * Gets the value of the stDataEmissaoPassaporte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataEmissaoPassaporte() {
        return stDataEmissaoPassaporte;
    }

    /**
     * Sets the value of the stDataEmissaoPassaporte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataEmissaoPassaporte(String value) {
        this.stDataEmissaoPassaporte = value;
    }

    /**
     * Gets the value of the stDataValidadePassaporte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataValidadePassaporte() {
        return stDataValidadePassaporte;
    }

    /**
     * Sets the value of the stDataValidadePassaporte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataValidadePassaporte(String value) {
        this.stDataValidadePassaporte = value;
    }

    /**
     * Gets the value of the stDataChegadaBrasil property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataChegadaBrasil() {
        return stDataChegadaBrasil;
    }

    /**
     * Sets the value of the stDataChegadaBrasil property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataChegadaBrasil(String value) {
        this.stDataChegadaBrasil = value;
    }

    /**
     * Gets the value of the stDataEmissaoVisto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataEmissaoVisto() {
        return stDataEmissaoVisto;
    }

    /**
     * Sets the value of the stDataEmissaoVisto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataEmissaoVisto(String value) {
        this.stDataEmissaoVisto = value;
    }

    /**
     * Gets the value of the stDataSolicitacaoDocumentacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataSolicitacaoDocumentacao() {
        return stDataSolicitacaoDocumentacao;
    }

    /**
     * Sets the value of the stDataSolicitacaoDocumentacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataSolicitacaoDocumentacao(String value) {
        this.stDataSolicitacaoDocumentacao = value;
    }

    /**
     * Gets the value of the stDataEntradaUltimaEtapa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataEntradaUltimaEtapa() {
        return stDataEntradaUltimaEtapa;
    }

    /**
     * Sets the value of the stDataEntradaUltimaEtapa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataEntradaUltimaEtapa(String value) {
        this.stDataEntradaUltimaEtapa = value;
    }

    /**
     * Gets the value of the stDataRetorno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataRetorno() {
        return stDataRetorno;
    }

    /**
     * Sets the value of the stDataRetorno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataRetorno(String value) {
        this.stDataRetorno = value;
    }

    /**
     * Gets the value of the stDataEnvioSap property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataEnvioSap() {
        return stDataEnvioSap;
    }

    /**
     * Sets the value of the stDataEnvioSap property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataEnvioSap(String value) {
        this.stDataEnvioSap = value;
    }

    /**
     * Gets the value of the stDataAdmSap property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataAdmSap() {
        return stDataAdmSap;
    }

    /**
     * Sets the value of the stDataAdmSap property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataAdmSap(String value) {
        this.stDataAdmSap = value;
    }

    /**
     * Gets the value of the idade property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdade() {
        return idade;
    }

    /**
     * Sets the value of the idade property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdade(Integer value) {
        this.idade = value;
    }

    /**
     * Gets the value of the diasDesdeDemissao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiasDesdeDemissao() {
        return diasDesdeDemissao;
    }

    /**
     * Sets the value of the diasDesdeDemissao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiasDesdeDemissao(String value) {
        this.diasDesdeDemissao = value;
    }

}
