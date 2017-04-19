package br.pro.delfino.drogaria.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.pro.delfino.drogaria.domain.Estado;
import br.pro.delfino.drogaria.util.HibernateUtil;

public class EstadoDAO extends GenericDAO<Estado> {

	public Estado buscarNome(String nome){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(classe);
			consulta.add(Restrictions.eq("nome", nome));
			Estado resultado = (Estado) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
	}
}
