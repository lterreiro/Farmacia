package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.factory.ConexaoFactory;

public class FabricanteDAO {

	final static Logger logger = Logger.getLogger(FabricanteDAO.class);

	public void salvar(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fabricante ");
		sql.append("(descricao) ");
		sql.append("VALUES (?)");
		Connection conexao = null;
		PreparedStatement comando = null;
		conexao = ConexaoFactory.conectar();
		comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.executeUpdate();
		comando.close();
		conexao.close();
	}

	public void excluir(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fabricante ");
		sql.append("WHERE CODIGO = ? ");
		Connection conexao = null;
		PreparedStatement comando = null;
		conexao = ConexaoFactory.conectar();
		comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());
		comando.executeUpdate();
		comando.close();
		conexao.close();
	}

	public void editar(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fabricante ");
		sql.append("SET descricao = ?");
		sql.append("WHERE codigo = ? ");
		Connection conexao = null;
		PreparedStatement comando = null;
		conexao = ConexaoFactory.conectar();
		comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());
		comando.executeUpdate();
		comando.close();
		conexao.close();
	}

	public Fabricante buscarPorCodigo(Fabricante f) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao FROM fabricante ");
		sql.append("WHERE codigo = ? ");
		Connection conexao = null;
		PreparedStatement comando = null;
		conexao = ConexaoFactory.conectar();
		comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());
		ResultSet rs = comando.executeQuery();
		Fabricante fr = null;

		if (rs.next()) {
			fr = new Fabricante();
			fr.setCodigo(rs.getLong(1));
			fr.setDescricao(rs.getString(2));
		}
		rs.close();
		comando.close();
		conexao.close();
		return fr;
	}

	public ArrayList<Fabricante> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("ORDER BY descricao ASC");
		Connection conexao = null;
		PreparedStatement comando = null;
		conexao = ConexaoFactory.conectar();
		comando = conexao.prepareStatement(sql.toString());
		ResultSet rs = comando.executeQuery();
		Fabricante fr = null;
		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();
		while (rs.next()) {
			fr = new Fabricante();
			fr.setCodigo(rs.getLong("codigo"));
			fr.setDescricao(rs.getString("descricao"));
			lista.add(fr);
		}
		rs.close();
		comando.close();
		conexao.close();
		return lista;
	}

	public ArrayList<Fabricante> buscarPorDescricao(Fabricante f) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC");
		Connection conexao = null;
		PreparedStatement comando = null;
		conexao = ConexaoFactory.conectar();
		comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%" + f.getDescricao() + "%");
		ResultSet rs = comando.executeQuery();
		Fabricante fr = null;
		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();
		while (rs.next()) {
			fr = new Fabricante();
			fr.setCodigo(rs.getLong("codigo"));
			fr.setDescricao(rs.getString("descricao"));
			lista.add(fr);
		}
		rs.close();
		comando.close();
		conexao.close();
		return lista;
	}

	public static void main(String[] args) {
		FabricanteDAO dao = new FabricanteDAO();

		// Fabricante f1 = new Fabricante();
		// Fabricante f2 = new Fabricante();

		// f1.setDescricao("DESCRICAO 1");
		//
		// f2.setDescricao("DESCRICAO 2");
		//
		// try {
		// dao.salvar(f1);
		// dao.salvar(f2);
		// System.out.println("Fabricantes salvos com sucesso.");
		// } catch (SQLException e) {
		// e.printStackTrace();
		// System.err.println("Erro a criar fabricante.");
		// }
		//
		// f1 = new Fabricante();
		// f1.setCodigo(1l);
		//
		// f2 = new Fabricante();
		// f2.setCodigo(2l);
		//
		// try {
		// dao.excluir(f1);
		// dao.excluir(f2);
		// System.out.println("Fabricante excluido com sucesso.");
		// } catch (SQLException e) {
		// e.printStackTrace();
		// System.err.println("Erro a excluir fabricante.");
		// }

		// f1.setDescricao("DESCRICAO 1111");
		// f1.setCodigo(1l);
		// f2.setDescricao("DESCRICAO 2222");
		// f2.setCodigo(2l);
		//
		// try {
		// dao.editar(f1);
		// dao.editar(f2);
		// System.out.println("Fabricante editado com sucesso.");
		// } catch (SQLException e) {
		// e.printStackTrace();
		// System.err.println("Erro a editado fabricante.");
		// }

		// try {
		// f1.setCodigo(1l);
		// Fabricante fabricante = dao.buscarPorCodigo(f1);
		// if (fabricante == null) {
		// System.out.println("Fabricante não encontrado");
		// } else {
		// System.out.println("Fabricante pesquisado: " +
		// fabricante.toString());
		// }
		//
		// f2.setCodigo(3L);
		// fabricante = dao.buscarPorCodigo(f2);
		// if (fabricante == null) {
		// System.out.println("Fabricante não encontrado");
		// } else {
		// System.out.println("Fabricante pesquisado: " +
		// fabricante.toString());
		// }
		// } catch (SQLException e) {
		// e.printStackTrace();
		// System.err.println("Erro a editado fabricante.");
		// }

		try {
			ArrayList<Fabricante> fabricantes = dao.listar();
			if (fabricantes == null) {
				System.out.println("Fabricantes não encontrados");
			} else {
				StringBuilder sb = new StringBuilder("Fabricantes pesquisados:\n");
				for (Fabricante fabricante2 : fabricantes) {
					sb.append(fabricante2.toString()).append("\n");
				}
				System.out.println(sb.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Erro a editado fabricante.");
		}
		//
		// try {
		// f1.setDescricao("DESCR");
		// ArrayList<Fabricante> fabricantes = dao.buscarPorDescricao(f1);
		// if (fabricantes == null) {
		// System.out.println("Fabricantes não encontrados");
		// } else {
		// StringBuilder sb = new StringBuilder("Fabricantes pesquisados:\n");
		//
		// for (Iterator<Fabricante> iterator = fabricantes.iterator();
		// iterator.hasNext();) {
		// Fabricante fabricante = iterator.next();
		// sb.append(fabricante.toString()).append("\n");
		//
		// }
		// System.out.println(sb.toString());
		// }
		// } catch (SQLException e) {
		// e.printStackTrace();
		// System.err.println("Erro a editado fabricante.");
		// }
	}
}
