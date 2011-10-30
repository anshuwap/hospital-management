package ece651.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;
	
	static {
		try {
//			System.out.println("<<<before configuration");
			Configuration configuration = new Configuration().configure();
//			System.out.println(">>>before sessinFactory");
			sessionFactory = configuration.buildSessionFactory();
//			System.out.println(">>>after sessionFactory");
		}catch(HibernateException e){
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void shutdown(){
		getSessionFactory().close();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
