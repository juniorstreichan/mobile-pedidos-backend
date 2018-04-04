package br.com.neja;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.neja.domain.Categoria;
import br.com.neja.domain.Produto;
import br.com.neja.repositories.CategoriaRepository;
import br.com.neja.repositories.ProdutoRepository;

@SpringBootApplication
public class MobilePedidosApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository catRepo;
	
	@Autowired
	private ProdutoRepository prodRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(MobilePedidosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria c1 =new Categoria("informatica");
		Categoria c2 =new Categoria("robotica");
		Categoria c3 =new Categoria("limpeza");
		
		Produto p1 = new Produto(null,"pcgamer",new BigDecimal("1200"));
		Produto p2 = new Produto(null,"impressora",new BigDecimal("800"));
		Produto p3 = new Produto(null,"sabao",new BigDecimal("10"));
		
		c1.getProdutos().addAll(Arrays.asList(p1,p2));
		c2.getProdutos().addAll(Arrays.asList(p1,p2));
		c3.getProdutos().addAll(Arrays.asList(p3));
		
		p1.getCategorias().addAll(Arrays.asList(c2,c1));
		p2.getCategorias().addAll(Arrays.asList(c2,c1));
		p3.getCategorias().addAll(Arrays.asList(c3));
		
		catRepo.saveAll(Arrays.asList(c1,c2,c3));
		prodRepo.saveAll(Arrays.asList(p1,p2,p3));
		
	}
}
