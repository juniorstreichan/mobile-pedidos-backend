package br.com.neja.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.neja.domain.Categoria;
import br.com.neja.domain.Cidade;
import br.com.neja.domain.Cliente;
import br.com.neja.domain.Endereco;
import br.com.neja.domain.Estado;
import br.com.neja.domain.ItemPedido;
import br.com.neja.domain.Pagamento;
import br.com.neja.domain.PagamentoComBoleto;
import br.com.neja.domain.PagamentoComCartao;
import br.com.neja.domain.Pedido;
import br.com.neja.domain.Produto;
import br.com.neja.domain.enums.EstadoPagamento;
import br.com.neja.domain.enums.TipoCliente;
import br.com.neja.repositories.CategoriaRepository;
import br.com.neja.repositories.CidadeRepository;
import br.com.neja.repositories.ClienteRepository;
import br.com.neja.repositories.EnderecoRepository;
import br.com.neja.repositories.EstadoRepository;
import br.com.neja.repositories.ItemPedidoRepository;
import br.com.neja.repositories.PagamentoRepository;
import br.com.neja.repositories.PedidoRepository;
import br.com.neja.repositories.ProdutoRepository;


@Service
public class DBService {
	@Autowired
	private CategoriaRepository catRepo;

	@Autowired
	private ProdutoRepository prodRepo;

	@Autowired
	private EstadoRepository estRepo;

	@Autowired
	private EnderecoRepository endRepo;

	@Autowired
	private ClienteRepository cliRepo;

	@Autowired
	private CidadeRepository cidRepo;

	@Autowired
	private PagamentoRepository pagRepo;

	@Autowired
	private PedidoRepository pedRepo;
	
	@Autowired
	private ItemPedidoRepository itemRepo;

	@Autowired
	private BCryptPasswordEncoder crypt;
	
	public void initTestDatabase() {
		// CATEGORIA E PRODUTO

		Categoria c1 = new Categoria("informatica");
		Categoria c2 = new Categoria("robotica");
		Categoria c3 = new Categoria("limpeza");
		Categoria c4 = new Categoria("açougue");
		Categoria c5 = new Categoria("pratos");
		Categoria c6 = new Categoria("verduras");
		Categoria c7 = new Categoria("esportes");
		Categoria c8 = new Categoria("pizza");
		

		Produto p1 = new Produto(null, "pcgamer", new BigDecimal("1200"));
		Produto p2 = new Produto(null, "impressora", new BigDecimal("800"));
		Produto p3 = new Produto(null, "sabao", new BigDecimal("10"));
		

		c1.getProdutos().addAll(Arrays.asList(p1, p2));
		c2.getProdutos().addAll(Arrays.asList(p1, p2));
		c3.getProdutos().addAll(Arrays.asList(p3));

		p1.getCategorias().addAll(Arrays.asList(c2, c1));
		p2.getCategorias().addAll(Arrays.asList(c2, c1));
		p3.getCategorias().addAll(Arrays.asList(c3));

		catRepo.saveAll(Arrays.asList(c1, c2, c3,c4,c5,c6,c7,c8));
		prodRepo.saveAll(Arrays.asList(p1, p2, p3));

		// ESTADO E CIDADE

		Estado mt = new Estado(111, "Mato grosso", "MT");
		Estado sp = new Estado(222, "Sao Paulo", "SP");

		Cidade cd1 = new Cidade(null,255, "Cuiaba", mt);
		Cidade cd2 = new Cidade(null,245, "Sorocaba", sp);

		mt.getCidades().addAll(Arrays.asList(cd1));
		sp.getCidades().addAll(Arrays.asList(cd2));

		estRepo.saveAll(Arrays.asList(mt, sp));
		cidRepo.saveAll(Arrays.asList(cd1, cd2));

		// CLIENTE E ENDEREÇO

		Cliente cl1 = new Cliente(null, "Junior", "junior_strs@hotmail.com", "012345678996", TipoCliente.PESSOA_FISICA,crypt.encode("123"));
		cl1.getTelefones().addAll(Arrays.asList("9 99896554", "9 65895421"));
		Endereco e1 = new Endereco(null, "bem ali", "123", "A", cd1, "centro", "78026888", cl1);
		cl1.getEnderecos().addAll(Arrays.asList(e1));

		Cliente cl2 = new Cliente(null, "zé", "zé@zé.com", "888345678996", TipoCliente.PESSOA_FISICA,crypt.encode("123"));
		cl2.getTelefones().addAll(Arrays.asList("9 45789963", "9 21547898"));
		Endereco e2 = new Endereco(null, "bem ali", "555", "B", cd2, "vila maria", "78076888", cl2);
		cl1.getEnderecos().addAll(Arrays.asList(e2));

		cliRepo.saveAll(Arrays.asList(cl2, cl1));
		endRepo.saveAll(Arrays.asList(e1, e2));

		// PEDIDO E PAGAMENTO
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		Pedido pd1 = new Pedido(null, LocalDateTime.parse("30/09/2017 10:32", formatter), cl1, e1);
		Pedido pd2 = new Pedido(null, LocalDateTime.parse("10/10/2017 19:35", formatter), cl2, e1);

		Pagamento pg1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pd1, 6);
		pd1.setPagamento(pg1);

		Pagamento pg2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pd2,
				LocalDateTime.parse("20/10/2017 00:00", formatter), null);
		pd2.setPagamento(pg2);

		cl1.getPedidos().addAll(Arrays.asList(pd1));
		cl2.getPedidos().addAll(Arrays.asList(pd2));

		pedRepo.saveAll(Arrays.asList(pd1, pd2));
		pagRepo.saveAll(Arrays.asList(pg1, pg2));

		// ITENS DE PEDIDO
		
		ItemPedido ip1 = new ItemPedido(pd1, p1, new BigDecimal("10"), 2, p1.getPreco().multiply(BigDecimal.valueOf(2)));
		ItemPedido ip2 = new ItemPedido(pd2, p2, new BigDecimal("10"), 2, p2.getPreco().multiply(BigDecimal.valueOf(2)));
		
		pd1.getItens().addAll(Arrays.asList(ip1));
		pd2.getItens().addAll(Arrays.asList(ip2));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip2));
		itemRepo.saveAll(Arrays.asList(ip1, ip2));
	}
	
}
