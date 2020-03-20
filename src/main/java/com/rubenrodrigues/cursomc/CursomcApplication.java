package com.rubenrodrigues.cursomc;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rubenrodrigues.cursomc.domain.Categoria;
import com.rubenrodrigues.cursomc.domain.Cidade;
import com.rubenrodrigues.cursomc.domain.Cliente;
import com.rubenrodrigues.cursomc.domain.Endereco;
import com.rubenrodrigues.cursomc.domain.Estado;
import com.rubenrodrigues.cursomc.domain.Pagamento;
import com.rubenrodrigues.cursomc.domain.PagamentoBoleto;
import com.rubenrodrigues.cursomc.domain.PagamentoCartao;
import com.rubenrodrigues.cursomc.domain.Pedido;
import com.rubenrodrigues.cursomc.domain.Produto;
import com.rubenrodrigues.cursomc.domain.enums.EstadoPagamento;
import com.rubenrodrigues.cursomc.domain.enums.TipoCliente;
import com.rubenrodrigues.cursomc.repositories.CategoriaRepository;
import com.rubenrodrigues.cursomc.repositories.CidadeRepository;
import com.rubenrodrigues.cursomc.repositories.ClienteRepository;
import com.rubenrodrigues.cursomc.repositories.EnderecoRepository;
import com.rubenrodrigues.cursomc.repositories.EstadoRepository;
import com.rubenrodrigues.cursomc.repositories.PagamentoRepository;
import com.rubenrodrigues.cursomc.repositories.PedidoRepository;
import com.rubenrodrigues.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 800.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().add(p2);

		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().add(cat1);

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().add(c1);
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", c1, cli1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", c2, cli1);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, LocalDateTime.parse("30/09/2017 10:32", formatter).toInstant(ZoneOffset.UTC),
				cli1, e1);
		Pedido ped2 = new Pedido(null, LocalDateTime.parse("10/10/2017 19:35", formatter).toInstant(ZoneOffset.UTC),
				cli1, e2);

		Pagamento pagto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, null,
				LocalDateTime.parse("20/10/2017 00:00", formatter).toInstant(ZoneOffset.UTC));
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

	}

}
