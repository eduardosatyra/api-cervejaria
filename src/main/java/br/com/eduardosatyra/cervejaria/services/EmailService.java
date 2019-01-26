package br.com.eduardosatyra.cervejaria.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import br.com.eduardosatyra.cervejaria.domain.Pedido;

/**
 * @author eduardosatyra
 *
 */
public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);

	void sendEmail(SimpleMailMessage msg);

	//HTMl
	void sendOrderConfirmationHtmlEmail(Pedido obj);

	void sendHtmlEmail(MimeMessage msg);

}
