package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.factory.ConexaoFactory;

public class ProdutoDAO {

	final static Logger logger = Logger.getLogger(ProdutoDAO.class);

	public void salvar(Produto p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produto ");
		sql.append("(descricao, quantidade, preco, fabricante_codigo) ");
		sql.append("VALUES (?, ?, ?, ?)");
		Connection conexao = null;
		PreparedStatement comando = null;
		try {
			conexao = ConexaoFactory.conectar();
			comando = conexao.prepareStatement(sql.toString());
			comando.setString(1, p.getDescricao());
			comando.setLong(2, p.getQuantidade());
			comando.setDouble(3, p.getPreco());
			comando.setLong(4, p.getFabricante().getCodigo());
			comando.executeUpdate();
		} finally {
			if (comando != null)
				comando.close();
			if (conexao != null)
				conexao.close();
		}
	}

	
	public void excluirTudo() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produto ");
		Connection conexao = null;
		PreparedStatement comando = null;
		try {
			conexao = ConexaoFactory.conectar();
			comando = conexao.prepareStatement(sql.toString());
			comando.executeUpdate();
		} finally {
			if (comando != null)
				comando.close();
			if (conexao != null)
				conexao.close();
		}
	}
	
	
	public void excluir(Produto p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produto ");
		sql.append("WHERE CODIGO = ? ");
		Connection conexao = null;
		PreparedStatement comando = null;
		try {
			conexao = ConexaoFactory.conectar();
			comando = conexao.prepareStatement(sql.toString());
			comando.setLong(1, p.getCodigo());
			comando.executeUpdate();
		} finally {
			if (comando != null)
				comando.close();
			if (conexao != null)
				conexao.close();
		}
	}

	public void editar(Produto p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE produto ");
		sql.append("SET descricao = ?, quantidade = ?, preco = ?, fabricante_codigo = ? ");
		sql.append("WHERE codigo = ? ");
		Connection conexao = null;
		PreparedStatement comando = null;
		try {
			conexao = ConexaoFactory.conectar();
			comando = conexao.prepareStatement(sql.toString());
			comando.setString(1, p.getDescricao());
			comando.setLong(2, p.getQuantidade());
			comando.setDouble(3, p.getPreco());
			comando.setLong(4, p.getFabricante().getCodigo());
			comando.setLong(5, p.getCodigo());
			comando.executeUpdate();
		} finally {
			if (comando != null)
				comando.close();
			if (conexao != null)
				conexao.close();
		}
	}

	public Produto buscarPorCodigo(Produto p) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao, quantidade, preco, fabricante_codigo FROM produto ");
		sql.append("WHERE codigo = ? ");
		Connection conexao = null;
		PreparedStatement comando = null;
		conexao = ConexaoFactory.conectar();
		comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCodigo());
		ResultSet rs = comando.executeQuery();
		Produto pr = null;

		try {
			if (rs.next()) {
				pr = new Produto();
				pr.setCodigo(rs.getLong(1));
				pr.setDescricao(rs.getString(2));
				pr.setQuantidade(rs.getLong(3));
				pr.setPreco(rs.getDouble(4));
				Fabricante f = new Fabricante();
				f.setCodigo(rs.getLong("fabricante_codigo"));
				pr.setFabricante(f);
			}
			rs.close();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (comando != null)
				comando.close();
			if (conexao != null)
				conexao.close();
		}

		return pr;
	}

	public ArrayList<Produto> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.codigo, p.descricao, p.quantidade, p.preco, f.codigo, f.descricao ");
		sql.append("FROM produto p ");
		sql.append("INNER JOIN fabricante f ON f.codigo = p.fabricante_codigo ");
		Connection conexao = null;
		PreparedStatement comando = null;
		conexao = ConexaoFactory.conectar();
		comando = conexao.prepareStatement(sql.toString());
		ResultSet rs = comando.executeQuery();
		Produto pr = null;
		Fabricante fr = null;
		ArrayList<Produto> lista = new ArrayList<Produto>();
		try {
			while (rs.next()) {
				fr = new Fabricante();
				fr.setCodigo(rs.getLong("f.codigo"));
				fr.setDescricao(rs.getString("f.descricao"));
				
				pr = new Produto();
				pr.setCodigo(rs.getLong("p.codigo"));
				pr.setDescricao(rs.getString("p.descricao"));
				pr.setQuantidade(rs.getLong("p.quantidade"));
				pr.setPreco(rs.getDouble("p.preco"));
				pr.setFabricante(fr);
				
				lista.add(pr);
			} 
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (comando != null)
				comando.close();
			if (conexao != null)
				conexao.close();
		}
		
		return lista;
	}

	public ArrayList<Produto> buscarPorDescricao(Produto p) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao, quantidade, preco, fabricante_codigo ");
		sql.append("FROM produto ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC");
		Connection conexao = null;
		PreparedStatement comando = null;
		conexao = ConexaoFactory.conectar();
		comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%" + p.getDescricao() + "%");
		ResultSet rs = comando.executeQuery();
		Produto pr = null;
		ArrayList<Produto> lista = new ArrayList<Produto>();
		while (rs.next()) {
			pr = new Produto();
			pr.setCodigo(rs.getLong("codigo"));
			pr.setDescricao(rs.getString("descricao"));
			pr.setPreco(rs.getDouble("preco"));
			pr.setQuantidade(rs.getLong("quantidade"));
			Fabricante f = new Fabricante();
			f.setCodigo(rs.getLong("fabricante_codigo"));
			pr.setFabricante(f);
			lista.add(pr);
		}
		rs.close();
		comando.close();
		conexao.close();
		return lista;
	}
}
