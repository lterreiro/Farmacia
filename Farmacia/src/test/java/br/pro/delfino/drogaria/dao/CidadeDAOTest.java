package br.pro.delfino.drogaria.dao;

import java.util.List;

import org.hibernate.PropertyValueException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Cidade;
import br.pro.delfino.drogaria.domain.Estado;

public class CidadeDAOTest {

	@Test
	@Ignore
	public void testSalvar() {
		EstadoDAO estadoDAO = new EstadoDAO();

		Estado estado = estadoDAO.buscar(1L);

		Cidade cidade = new Cidade();
		cidade.setNome("Ourinhos");
		cidade.setEstado(estado);

		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.salvar(cidade);
	}

	@Test(expected = PropertyValueException.class)
	@Ignore
	public void testSalvarEstadoInexistente() {
		EstadoDAO estadoDAO = new EstadoDAO();

		Estado estado = estadoDAO.buscar(9L);

		Cidade cidade = new Cidade();
		cidade.setNome("Marilia");
		cidade.setEstado(estado);

		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.salvar(cidade);
	}

	@Test
	@Ignore
	public void testListar() {
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> listaCidades = cidadeDAO.listar();
		Assert.assertNotNull(listaCidades);
		for (Cidade cidade : listaCidades) {
			System.out.println(cidade);
		}
	}

	@Test
	@Ignore
	public void testBuscar() {
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(1L);
		Assert.assertNotNull(cidade);
		System.out.println(cidade);
	}

	@Test
	@Ignore
	public void testExcluir() {
		CidadeDAO cidadeDAO = new CidadeDAO();
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(1L);
		Cidade cidade = new Cidade();
		cidade.setNome("ABC");
		cidade.setEstado(estado);
		cidadeDAO.salvar(cidade);
		cidadeDAO.excluir(cidade);
	}

	@Test
	@Ignore
	public void testEditar() {
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(1L);
		Assert.assertNotNull(cidade);
		cidade.setNome("Ourinhos do Sul");
		cidadeDAO.editar(cidade);
	}

}
