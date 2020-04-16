package com.rubenrodrigues.cursomc.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.rubenrodrigues.cursomc.domain.PagamentoBoleto;

@Service
public class BoletoService {
	
	public void preencherPagamentoBoleto(PagamentoBoleto pagto, Instant instanteDoPedido) {
		pagto.setDataVencimento(instanteDoPedido.plus(7, ChronoUnit.DAYS));
	}

}
