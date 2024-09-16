package br.com.nextage.rmaweb.controller.notificacao.email.enviar;

public interface EmailService {
    void enviarEmail(String subject, String recipients, String body);
}
