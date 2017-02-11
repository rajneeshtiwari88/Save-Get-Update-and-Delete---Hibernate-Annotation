package com.demo.hibernate.firstproject;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RepositoryService {
	public static void main(String[] args) {
		RepositoryService rs = new RepositoryService();
		Session session = rs.getSession();
		ModelEmployee me = rs.createModelEmployee();
		rs.persistData(session, me);
		List<ModelEmployee> meList = rs.getData(session);
		System.out.println(meList.get(0).getEmpName());
		me.setEmpName("Java- Hibernate");
		rs.updateData(session, me);
		meList = rs.getData(session);
		System.out.println(meList.get(0).getEmpName());
		rs.deleteData(session, me.getId());
	}

	private Session getSession() {
		SessionFactory sf = new Configuration().configure("hibernate.xml").buildSessionFactory();
		Session session = sf.openSession();
		return session;
	}

	private ModelEmployee createModelEmployee() {
		ModelEmployee me = new ModelEmployee();
		me.setId("2");
		me.setEmpName("Rajneesh");
		me.setEmpSalary(new BigDecimal(2000));
		return me;
	}

	private void persistData(Session session, ModelEmployee modelEmployee) {
		Transaction tx = session.beginTransaction();
		try {
			session.save(modelEmployee);
			tx.commit();
		} catch (Exception e) {
			System.out.println("Transaction Rolled Back due to :" + e);
		}
	}

	private List<ModelEmployee> getData(Session session) {
		Query query = session.getNamedQuery(ModelEmployee.GET_EMPLOYEE_LIST);
		List<ModelEmployee> listEmployee = query.getResultList();
		return listEmployee;
	}

	private void updateData(Session session, ModelEmployee modelEmployee) {
		Transaction tx = session.beginTransaction();
		try {
			session.update(modelEmployee);
			tx.commit();
		} catch (Exception e) {
			System.out.println("Unable to update due to : " + e);
		}
	}

	private void deleteData(Session session, String id) {
		try {
			Transaction tx = session.beginTransaction();
			ModelEmployee me = session.find(ModelEmployee.class, id);
			session.delete(me);
			tx.commit();
			System.out.println("data deleted with id :" +id);
		} catch (Exception e) {

		} finally {
			if (session != null)
				session.close();
		}
	}
}
