package br.com.eduardosatyra.cervejaria.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.eduardosatyra.cervejaria.domain.Pedido;

/**
 * @author eduardosatyra
 *
 */
public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);

	void sendEmail(SimpleMailMessage msg);

}
