package br.com.drogaria.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.JSFUtil;

@ManagedBean(name = "MBFabricante", eager = true)
@ViewScoped
public class FabricanteBean {

	private final static Logger logger = Logger.getLogger(FabricanteBean.class);

	private Fabricante fabricante;

	private ArrayList<Fabricante> itens = null;
	
	private ArrayList<Fabricante> itemsFiltrados = null;

	private FabricanteDAO dao;

	public FabricanteBean() {
		logger.debug("criar fabricante dao");
		dao = new FabricanteDAO();
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public ArrayList<Fabricante> getItens() {
		logger.trace("getItems");
		return itens;
	}

	public ArrayList<Fabricante> getItemsFiltrados() {
		return itemsFiltrados;
	}
	
	public void setItemsFiltrados(ArrayList<Fabricante> itemsFiltrados) {
		this.itemsFiltrados = itemsFiltrados;
	}
	
	public void setItens(ArrayList<Fabricante> items) {
		logger.trace("setItems");
		this.itens = items;
	}

	@PostConstruct
	public void init() {
		
		try {
			logger.debug("obter fabricantes");			
			buscarFabricantes();
		} catch (SQLException e) {
			logger.error(e);
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	private void buscarFabricantes() throws SQLException {
		ArrayList<Fabricante> lista = dao.listar();
		if (lista == null || lista.size() <= 0) {
			logger.warn("Lista vazia.");
		}
		setItens(lista);
		logger.debug("tamanho lista: " + this.itens.size());
	}

	public void prepararNovo() {
		fabricante = new Fabricante();
	}

	public void novo() {
		try {
			dao.salvar(fabricante);
			buscarFabricantes();
			JSFUtil.adicionarMensagemSucesso("Fabricante criado com sucesso.");
		} catch (SQLException e) {
			logger.error(e);
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	public void excluir(){
		try {
			dao.excluir(fabricante);
			buscarFabricantes();
			JSFUtil.adicionarMensagemSucesso("Fabricante excluido com sucesso.");
		} catch (SQLException e) {
			logger.error(e);
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	public void editar(){
		try {
			dao.editar(fabricante);
			buscarFabricantes();
			JSFUtil.adicionarMensagemSucesso("Fabricante editado com sucesso.");
		} catch (SQLException e) {
			logger.error(e);
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
}
