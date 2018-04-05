package br.com.neja;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.neja.domain.Categoria;
import br.com.neja.domain.Cidade;
import br.com.neja.domain.Cliente;
import br.com.neja.domain.Endereco;
import br.com.neja.domain.Estado;
import br.com.neja.domain.Produto;
import br.com.neja.domain.enums.TipoCliente;
import br.com.neja.repositories.CategoriaRepository;
import br.com.neja.repositories.CidadeRepository;
import br.com.neja.repositories.ClienteRepository;
import br.com.neja.repositories.EnderecoRepository;
import br.com.neja.repositories.EstadoRepository;
import br.com.neja.repositories.ProdutoRepository;

@SpringBootApplication
public class MobilePedidosApplication implements CommandLineRunner {

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

	public static void main(String[] args) {
		SpringApplication.run(MobilePedidosApplication.class, args);
	}

	// START INICIAL DO BANCO
	@Override
	public void run(String... args) throws Exception {
		// CATEGORIA E PRODUTO

		Categoria c1 = new Categoria("informatica");
		Categoria c2 = new Categoria("robotica");
		Categoria c3 = new Categoria("limpeza");

		Produto p1 = new Produto(null, "pcgamer", new BigDecimal("1200"));
		Produto p2 = new Produto(null, "impressora", new BigDecimal("800"));
		Produto p3 = new Produto(null, "sabao", new BigDecimal("10"));

		c1.getProdutos().addAll(Arrays.asList(p1, p2));
		c2.getProdutos().addAll(Arrays.asList(p1, p2));
		c3.getProdutos().addAll(Arrays.asList(p3));

		p1.getCategorias().addAll(Arrays.asList(c2, c1));
		p2.getCategorias().addAll(Arrays.asList(c2, c1));
		p3.getCategorias().addAll(Arrays.asList(c3));

		catRepo.saveAll(Arrays.asList(c1, c2, c3));
		prodRepo.saveAll(Arrays.asList(p1, p2, p3));

		// ESTADO E CIDADE

		Estado mt = new Estado(111, "Mato grosso", "MT");
		Estado sp = new Estado(222, "Sao Paulo", "SP");

		Cidade cd1 = new Cidade(255, "Cuiaba", mt);
		Cidade cd2 = new Cidade(245, "Sorocaba", sp);

		mt.getCidades().addAll(Arrays.asList(cd1));
		sp.getCidades().addAll(Arrays.asList(cd2));

		estRepo.saveAll(Arrays.asList(mt, sp));
		cidRepo.saveAll(Arrays.asList(cd1, cd2));

		// CLIENTE E ENDEREÇO

		Cliente cl1 = new Cliente(null, "Maria", "Maria@Maria.com", "012345678996", TipoCliente.PESSOA_FISICA);
		cl1.getTelefones().addAll(Arrays.asList("9 99896554", "9 65895421"));
		Endereco e1 = new Endereco(null, "bem ali", "123", "A", cd1, "centro", "78026888", cl1);
		cl1.getEnderecos().addAll(Arrays.asList(e1));

		Cliente cl2 = new Cliente(null, "zé", "zé@zé.com", "888345678996", TipoCliente.PESSOA_FISICA);
		cl2.getTelefones().addAll(Arrays.asList("9 45789963", "9 21547898"));
		Endereco e2 = new Endereco(null, "bem ali", "555", "B", cd2, "vila maria", "78076888", cl2);
		cl1.getEnderecos().addAll(Arrays.asList(e2));
		
		cliRepo.saveAll(Arrays.asList(cl2,cl1));
		endRepo.saveAll(Arrays.asList(e1,e2));
	}
}
