package br.com.drogaria.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProdutoDAOTeste {

	@Before
	public void setUp() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			dao.excluirTudo();
		} catch (SQLException e) {
			fail("Erro a limpar produtos");
		}
	}

	@After
	public void tearDown() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			dao.excluirTudo();
		} catch (SQLException e) {
			fail("Erro a limpar produtos");
		}
	}

	@Test
	public void teste1Salvar() throws SQLException {
		ProdutoDAO dao = new ProdutoDAO();
		Fabricante f = new Fabricante();
		f.setCodigo(1L);

		Produto p = new Produto();
		p.setDescricao("BEN-U-RON 500mg");
		p.setPreco(2.45D);
		p.setQuantidade(13L);
		p.setFabricante(f);

		dao.salvar(p);

		ArrayList<Produto> p2 = dao.buscarPorDescricao(p);
		assertNotNull(p2);
	}

	@Test
	public void teste3Excluir() throws SQLException {
		ProdutoDAO dao = new ProdutoDAO();
		Produto p = new Produto();
		p.setCodigo(1L);
		dao.excluir(p);

		Produto p1 = dao.buscarPorCodigo(p);
		assertNull(p1);
	}

	@Test
	public void teste4Listar() throws SQLException {
		ProdutoDAO dao = new ProdutoDAO();
		Fabricante f = new Fabricante();
		f.setCodigo(1L);

		Produto p = new Produto();
		p.setDescricao("BEN-U-RON 500mg");
		p.setPreco(2.45D);
		p.setQuantidade(10L);
		p.setFabricante(f);

		dao.salvar(p);

		p = new Produto();
		p.setDescricao("BRUFEN 600mg");
		p.setPreco(1.5D);
		p.setQuantidade(25L);
		p.setFabricante(f);

		dao.salvar(p);

		p = new Produto();
		p.setDescricao("ASPIRINA C");
		p.setPreco(3D);
		p.setQuantidade(5L);
		p.setFabricante(f);

		dao.salvar(p);

		ArrayList<Produto> listar = dao.listar();
		assertNotNull(listar);
		assertEquals(3, listar.size());
		System.out.println("Produtos listados:");
		for (Produto produto : listar) {
			System.out.println(produto.toString());
		}
	}

	@Test
	public void teste5Editar() throws SQLException {

		ProdutoDAO dao = new ProdutoDAO();
		Fabricante f = new Fabricante();
		f.setCodigo(1L);

		Produto p = new Produto();
		p.setDescricao("BEN-U-RON 500mg");
		p.setPreco(2.45D);
		p.setQuantidade(10L);
		p.setFabricante(f);

		dao.salvar(p);
		
		ArrayList<Produto> pl = dao.buscarPorDescricao(p);
		assertNotNull(pl);
		assertFalse(pl.isEmpty());
		Produto p1 = pl.get(0);
		System.out.println("Produto antes da edição:\n" + p1.toString());
		
		f = new Fabricante();
		f.setCodigo(2L);
		
		p1.setDescricao("BRUFEN 600mg");
		p1.setPreco(1.5D);
		p1.setQuantidade(25L);
		p1.setFabricante(f);
		
		dao.editar(p1);
		
		Produto p2 = dao.buscarPorCodigo(p1);
		assertNotNull(p2);
		System.out.println("Produto depois da edição:\n" + p2.toString());
	}
}
