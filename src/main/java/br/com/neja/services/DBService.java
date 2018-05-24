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
import br.com.neja.domain.enums.Perfil;
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
		Categoria cat1 = new Categoria("informatica2");
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
		Produto p4 = new Produto(null, "pcgamer", new BigDecimal("1200"));
		Produto p5 = new Produto(null, "impressora", new BigDecimal("800"));
		Produto p6 = new Produto(null, "sabao", new BigDecimal("10"));
		Produto p7 = new Produto(null, "pcgamer", new BigDecimal("1200"));
		Produto p8 = new Produto(null, "impressora", new BigDecimal("800"));
		Produto p9 = new Produto(null, "sabao", new BigDecimal("10"));
		Produto p10 = new Produto(null, "pcgamer", new BigDecimal("1200"));
		Produto p11= new Produto(null, "impressora", new BigDecimal("800"));
		Produto p12 = new Produto(null, "Produto 12", new BigDecimal("10"));
		Produto p13 = new Produto(null, "Produto 13", new BigDecimal("10"));
		Produto p14 = new Produto(null, "Produto 14", new BigDecimal("10"));
		Produto p15 = new Produto(null, "Produto 15", new BigDecimal("10"));
		Produto p16 = new Produto(null, "Produto 16", new BigDecimal("10"));
		Produto p17 = new Produto(null, "Produto 17", new BigDecimal("10"));
		Produto p18 = new Produto(null, "Produto 18", new BigDecimal("10"));
		Produto p19 = new Produto(null, "Produto 19", new BigDecimal("10"));
		Produto p20 = new Produto(null, "Produto 20", new BigDecimal("10"));
		Produto p21 = new Produto(null, "Produto 21", new BigDecimal("10"));
		Produto p22 = new Produto(null, "Produto 22", new BigDecimal("10"));
		Produto p23 = new Produto(null, "Produto 23", new BigDecimal("10"));
		Produto p24 = new Produto(null, "Produto 24", new BigDecimal("10"));
		Produto p25 = new Produto(null, "Produto 25", new BigDecimal("10"));
		Produto p26 = new Produto(null, "Produto 26", new BigDecimal("10"));
		Produto p27 = new Produto(null, "Produto 27", new BigDecimal("10"));
		Produto p28 = new Produto(null, "Produto 28", new BigDecimal("10"));
		Produto p29 = new Produto(null, "Produto 29", new BigDecimal("10"));
		Produto p30 = new Produto(null, "Produto 30", new BigDecimal("10"));
		Produto p31 = new Produto(null, "Produto 31", new BigDecimal("10"));
		Produto p32 = new Produto(null, "Produto 32", new BigDecimal("10"));
		Produto p33 = new Produto(null, "Produto 33", new BigDecimal("10"));
		Produto p34 = new Produto(null, "Produto 34", new BigDecimal("10"));
		Produto p35 = new Produto(null, "Produto 35", new BigDecimal("10"));
		Produto p36 = new Produto(null, "Produto 36", new BigDecimal("10"));
		Produto p37 = new Produto(null, "Produto 37", new BigDecimal("10"));
		Produto p38 = new Produto(null, "Produto 38", new BigDecimal("10"));
		Produto p39 = new Produto(null, "Produto 39", new BigDecimal("10"));
		Produto p40 = new Produto(null, "Produto 40", new BigDecimal("10"));
		Produto p41 = new Produto(null, "Produto 41", new BigDecimal("10"));
		Produto p42 = new Produto(null, "Produto 42", new BigDecimal("10"));
		Produto p43 = new Produto(null, "Produto 43", new BigDecimal("10"));
		Produto p44 = new Produto(null, "Produto 44", new BigDecimal("10"));
		Produto p45 = new Produto(null, "Produto 45", new BigDecimal("10"));
		Produto p46 = new Produto(null, "Produto 46", new BigDecimal("10"));
		Produto p47 = new Produto(null, "Produto 47", new BigDecimal("10"));
		Produto p48 = new Produto(null, "Produto 48", new BigDecimal("10"));
		Produto p49 = new Produto(null, "Produto 49", new BigDecimal("10"));
		Produto p50 = new Produto(null, "Produto 50", new BigDecimal("10"));
		
		cat1.getProdutos().addAll(Arrays.asList(p4,p5,p6,p7,p8,p9,p10,p11,p12, p13, p14, p15, p16, p17, p18, p19, p20,
		p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
		p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		p12.getCategorias().add(cat1);
		p13.getCategorias().add(cat1);
		p14.getCategorias().add(cat1);
		p15.getCategorias().add(cat1);
		p16.getCategorias().add(cat1);
		p17.getCategorias().add(cat1);
		p18.getCategorias().add(cat1);
		p19.getCategorias().add(cat1);
		p20.getCategorias().add(cat1);
		p21.getCategorias().add(cat1);
		p22.getCategorias().add(cat1);
		p23.getCategorias().add(cat1);
		p24.getCategorias().add(cat1);
		p25.getCategorias().add(cat1);
		p26.getCategorias().add(cat1);
		p27.getCategorias().add(cat1);
		p28.getCategorias().add(cat1);
		p29.getCategorias().add(cat1);
		p30.getCategorias().add(cat1);
		p31.getCategorias().add(cat1);
		p32.getCategorias().add(cat1);
		p33.getCategorias().add(cat1);
		p34.getCategorias().add(cat1);
		p35.getCategorias().add(cat1);
		p36.getCategorias().add(cat1);
		p37.getCategorias().add(cat1);
		p38.getCategorias().add(cat1);
		p39.getCategorias().add(cat1);
		p40.getCategorias().add(cat1);
		p41.getCategorias().add(cat1);
		p42.getCategorias().add(cat1);
		p43.getCategorias().add(cat1);
		p44.getCategorias().add(cat1);
		p45.getCategorias().add(cat1);
		p46.getCategorias().add(cat1);
		p47.getCategorias().add(cat1);
		p48.getCategorias().add(cat1);
		p49.getCategorias().add(cat1);
		p50.getCategorias().add(cat1);
		c1.getProdutos().addAll(Arrays.asList(p1, p2));
		c2.getProdutos().addAll(Arrays.asList(p1, p2));
		c3.getProdutos().addAll(Arrays.asList(p3));

		p1.getCategorias().addAll(Arrays.asList(c2, c1));
		p2.getCategorias().addAll(Arrays.asList(c2, c1));
		p3.getCategorias().addAll(Arrays.asList(c3));

		catRepo.saveAll(Arrays.asList(cat1,c1, c2, c3,c4,c5,c6,c7,c8));
		
		prodRepo.saveAll(Arrays.asList(p1, p2, p3,p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

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
		cl1.addPerfil(Perfil.ADMIN);
		Endereco e1 = new Endereco(null, "bem ali", "123", "A", cd1, "centro", "78026888", cl1);
		cl1.getEnderecos().addAll(Arrays.asList(e1));

		Cliente cl2 = new Cliente(null, "zé", "ze@ze.com", "888345678996", TipoCliente.PESSOA_FISICA,crypt.encode("123"));
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
