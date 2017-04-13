package br.pro.delfino.drogaria.util;

import org.hibernate.Session;
import org.junit.Test;

import br.pro.delfino.drogaria.util.HibernateUtil;

public class HibernateUtilTest {
	
	@Test
	public void testConnection() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		sessao.close();
		HibernateUtil.getFabricaDeSessoes().close();
	}
}
