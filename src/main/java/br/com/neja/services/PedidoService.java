package br.com.neja.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.neja.domain.ItemPedido;
import br.com.neja.domain.PagamentoComBoleto;
import br.com.neja.domain.Pedido;
import br.com.neja.domain.enums.EstadoPagamento;
import br.com.neja.repositories.ItemPedidoRepository;
import br.com.neja.repositories.PagamentoRepository;
import br.com.neja.repositories.PedidoRepository;
import br.com.neja.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private ItemPedidoRepository itemRepo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pgRepo;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private EmailService emailService;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id:" + id + " Tipo: " + Pedido.class.getSimpleName()));
	}

	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(LocalDateTime.now());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgto= (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoBoleto(pgto,obj.getInstante());
		}
		
		obj = repo.save(obj);
		pgRepo.save(obj.getPagamento());
		for (ItemPedido Item: obj.getItens()) {
			Item.setPedido(obj);
			Item.setDesconto(BigDecimal.ZERO);
			Item.setPreco(produtoService.find(Item.getProduto().getId()).getPreco());
			
		}
		
		itemRepo.saveAll(obj.getItens());
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}
}
