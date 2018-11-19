package org.nlt.database;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class DatabaseConnection implements ServletContextListener{
private static SessionFactory sf;
private static Session ses;
	public void contextInitialized(ServletContextEvent sce) {
		try {
			
			System.out.println("LLLLLLLLLLLHHHHHHHHKKKKKKKKK");
			 sf = new Configuration().configure("org/nlt/controller/hibernate.cfg.xml").buildSessionFactory();
				ses=sf.openSession();
				System.out.println("Database Connection created");

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	

	public static Session getOpenSession() {
		return ses;
	}
	
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			ses.clear();
			sf.close();
			
		} catch (Exception ex) {
System.out.println(ex);
		}
	}

}
