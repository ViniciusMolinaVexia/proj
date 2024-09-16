package br.com.nextage.rmaweb.ws.sap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 * <pre>
 *<b>author:</b> Marlos Morbis Novo
 *<b>date:  </b> 18/08/2015
 * </pre>
 */
public class SoapHandlerSapService implements SOAPHandler<SOAPMessageContext> {

    public SoapHandlerSapService() {
    }

    /**
     * <PRE>
     *<b>author:</b>  Marlos Morbis Novo
     *<b>date  :</b>  23/02/2015
     *<b>param :</b>  SOAPMessageContext
     *<b>return :</b> boolean
     * </PRE>
     *
     * <p>
     * Recupera a mensagem a ser enviada do contexto SOAP para setar no Header o
     * SoapHandlerSapService para acesso ao web service.
     *
     * @param messageContext
     */
    @Override
    public boolean handleMessage(SOAPMessageContext messageContext) {
        try {
            if ((Boolean) messageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)) {

                final SOAPMessage message1 = messageContext.getMessage();
                final StringWriter sw1 = new StringWriter();

                TransformerFactory.newInstance().newTransformer().transform(
                        new DOMSource(message1.getSOAPPart()),
                        new StreamResult(sw1));

                // Now you have the XML as a String:
                System.out.println("ADM SAP: " + sw1.toString());

            } else {

                final StringWriter sw = new StringWriter();
                // Now you have the XML as a String:
                System.out.println("ADM SAP retorno: " + sw.toString());
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext messageContext) {
        try {
            System.out.println("ERRO XML: " + messageContext.toString());
            final SOAPMessage message1 = messageContext.getMessage();
            final StringWriter sw1 = new StringWriter();

            TransformerFactory.newInstance().newTransformer().transform(
                    new DOMSource(message1.getSOAPPart()),
                    new StreamResult(sw1));

            // Now you have the XML as a String:
            System.out.println("ERRO XML2: " + sw1.toString());
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(SoapHandlerSapService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(SoapHandlerSapService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public void close(MessageContext messageContext) {
        messageContext.getClass();
    }

    // I'm not quite sure about what should this function do, but I guess something like this...
    @Override
    public Set getHeaders() {
        Set headers = new HashSet();
        return headers;
    }
}
