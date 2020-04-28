package com.rubenrodrigues.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.rubenrodrigues.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
