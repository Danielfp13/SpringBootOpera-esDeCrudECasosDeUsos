package com.daniel.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.daniel.cursomc.domain.Categoria;
import com.daniel.cursomc.domain.Cidade;
import com.daniel.cursomc.domain.Cliente;
import com.daniel.cursomc.domain.Endereco;
import com.daniel.cursomc.domain.Estado;
import com.daniel.cursomc.domain.Produto;
import com.daniel.cursomc.domain.enums.TipoCliente;
import com.daniel.cursomc.repositories.CategoriaRepository;
import com.daniel.cursomc.repositories.CidadeRepository;
import com.daniel.cursomc.repositories.ClienteRepository;
import com.daniel.cursomc.repositories.EnderecoRepository;
import com.daniel.cursomc.repositories.EstadoRepository;
import com.daniel.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "computador", 2000.00);
		Produto p2 = new Produto(null, "impressora", 800.00);
		Produto p3 = new Produto(null, "mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São paulo");
		
		Cidade c1 = new Cidade(null,"Uberlândia",est1);
		Cidade c2 = new Cidade(null,"São paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		Cliente cli1 = new Cliente(null,"Maria Silva","maria@gmail.com","36378912377",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("36533243","984532143"));
		
		Endereco e1 = new Endereco(null,"Rua Flores","300","Apto 203","jardim","38220834",c1, cli1);
		Endereco e2 = new Endereco(null,"Avenida Matos","105","Sala 800","Centro","74898666",c2,cli1);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		
		
		

	}

}
