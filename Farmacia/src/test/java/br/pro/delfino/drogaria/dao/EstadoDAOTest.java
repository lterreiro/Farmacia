package br.pro.delfino.drogaria.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Estado;

public class EstadoDAOTest {

	@Test
	@Ignore
	public void testSalvar() {
		Estado estado = new Estado();
		estado.setNome("São Paulo");
		estado.setSigla("SP");

		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);

	}
	
	@Test(expected=org.hibernate.PropertyValueException.class)
	@Ignore
	public void testSalvarSemSigla() {
		Estado estado = new Estado();
		estado.setNome("Rio Janeiro");

		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
	}
	
	@Test
	@Ignore
	public void testListar(){
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> lista = estadoDAO.listar();
		for(Estado estado : lista){
			System.out.println(estado.toString());
		}
	}
	
	@Test
	public void testBuscarExistente(){
		Long codigoEsperado = 2L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado resultado = estadoDAO.buscar(codigoEsperado);
		Assert.assertNotNull(resultado);
		Assert.assertEquals(codigoEsperado.longValue(), resultado.getCodigo().longValue());
		System.out.println("Buscar codigo " + codigoEsperado + ": " + resultado.toString());
		
	}
	
	@Test
	public void testBuscarInexistente(){
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado resultado = estadoDAO.buscar(99L);
		Assert.assertNull(resultado);		
	}
}
