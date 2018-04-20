package br.com.neja.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.neja.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoBoleto(PagamentoComBoleto pgto, LocalDateTime instante) {
		pgto.setDataVencimento(instante.plusDays(7));
	}
	
	
	
}
