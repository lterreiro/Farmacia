package br.com.drogaria.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.util.JSFUtil;

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean {

	private final static Logger logger = Logger.getLogger(FabricanteBean.class);

	private ArrayList<Produto> itens;

	private ArrayList<Produto> itensFiltrados;

	private Produto produto;

	private ArrayList<Fabricante> comboFabricantes;

	public ArrayList<Produto> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Produto> itens) {
		this.itens = itens;
	}

	public ArrayList<Produto> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Produto> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ArrayList<Fabricante> getComboFabricantes() {
		return comboFabricantes;
	}

	public void setComboFabricantes(ArrayList<Fabricante> comboFabricantes) {
		this.comboFabricantes = comboFabricantes;
	}

	public void prepararNovo() {
		this.produto = new Produto();
		carregarFabricantes();
	}
	
	public void prepararEditar() {
		carregarFabricantes();
	}

	public void carregarListagem() {
		ProdutoDAO dao = new ProdutoDAO();
		try {
			this.itens = dao.listar();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Erro a listar produtos.");
			logger.error("Erro a listar produtos.", e);
		}
	}

	public void carregarFabricantes() {
		FabricanteDAO dao = new FabricanteDAO();
		try {
			this.comboFabricantes = dao.listar();
			if (this.comboFabricantes != null) {
				logger.debug(String.format("Foram obtidos %d fabricantes.", this.comboFabricantes.size()));
			}
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Erro a listar fabricantes.");
			logger.error("Erro a listar fabricantes.", e);
		}
	}

	public void novo() {
		ProdutoDAO dao = new ProdutoDAO();
		try {
			dao.salvar(this.produto);
			itens = dao.listar();
			JSFUtil.adicionarMensagemSucesso("Produto salvado com sucesso.");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Erro a salvar produto.");
			logger.error("Erro a salvar produto.", e);
		}
	}
	
	public void excluir() {
		ProdutoDAO dao = new ProdutoDAO();
		try {
			dao.excluir(this.produto);
			itens = dao.listar();
			JSFUtil.adicionarMensagemSucesso("Produto excluido com sucesso.");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Erro a excluir produto.");
			logger.error("Erro a excluir produto.", e);
		}
	}
	
	public void editar() {
		ProdutoDAO dao = new ProdutoDAO();
		try {
			dao.editar(this.produto);
			itens = dao.listar();
			JSFUtil.adicionarMensagemSucesso("Produto editado com sucesso.");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Erro a editar produto.");
			logger.error("Erro a editar produto.", e);
		}
	}
}
