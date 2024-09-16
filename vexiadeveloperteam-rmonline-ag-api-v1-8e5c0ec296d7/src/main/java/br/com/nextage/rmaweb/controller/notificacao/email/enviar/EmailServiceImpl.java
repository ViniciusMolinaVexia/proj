package br.com.nextage.rmaweb.controller.notificacao.email.enviar;

import javax.inject.Inject;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

import br.com.nextage.rmaweb.entitybean.Configuracao;
import br.com.nextage.rmaweb.service.ConfiguracaoService;
import org.apache.log4j.Logger;

public class EmailServiceImpl implements EmailService {
    private static final Logger log = Logger.getLogger(EmailServiceImpl.class);

    @Inject
    private ConfiguracaoService configuracaoService;

    private boolean prod = true;
    private String username;
    private String password;

    @Override
    public void enviarEmail(String subject, String recipients, String body) {
        try {

            //TODO - implementar anexo

            Configuracao configuracao = configuracaoService.getConfiguracao();

            Properties prop;
            if(prod) {
                prop = getProperties(configuracao);
                username = configuracao.getEmailUser();
                password = configuracao.getEmailPasswd();
            } else {
                prop = getPropertiesDev();
                username = "contato@adrianogomes.dev";
                password = "unix@007";
                recipients = "jrrodrigues.junior@gmail.com,edercmalmeida@gmail.com,eder.cmalmeida@gmail.com,kapiturasp@gmail.com";
                configuracao.setEmailOrigem("contato@adrianogomes.dev");
            }

            Session session = Session.getInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            /**
             * Construct MSG
             */
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(configuracao.getEmailOrigem()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
            message.setSubject(subject);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(body, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);

            log.info("E-mail enviado com sucesso");
            log.info("Subject: " + subject);
            log.info("Recipients: " + recipients);
            log.info("Body: " + body);

        } catch (Exception e) {
            log.error("EmailServiceImpl", e);
        }
    }

    private Properties getProperties(Configuracao configuracao) {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
//        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", configuracao.getEmailHost());
        prop.put("mail.smtp.port", configuracao.getEmailPorta());
        prop.put("mail.smtp.ssl.trust", configuracao.getEmailHost());

        return prop;
    }

    private Properties getPropertiesDev() {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "mail.adrianogomes.dev");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "mail.adrianogomes.dev");

        return prop;
    }

}
