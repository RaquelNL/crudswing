package com.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.Serie;
import com.hibernate.util.HibernateUtil;
import com.mysql.cj.Query;

public class SerieDAO {

	/**
	 * INSERCIÓN
	 */
	
	public void insertCiudad(Serie s) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(s);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	/**
	 * ACTUALIZACIÓN
	 */
	
	public void updateCiudad(Serie s) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.merge(s);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	/**
	 * BORRADO
	 */
	
	public void deleteCiudad(int id) {
		Transaction transaction = null;
		Serie s = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			s = session.get(Serie.class, id);
			session.remove(s);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	/**
	 * SELECCIÓN SIMPLE
	 */
	
	public Serie selectCiudadById(int id) {
		Transaction transaction = null;
		Serie s = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			s = session.get(Serie.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return s;
	}
	
	/**
	 * SELECCIÓN MÚLTIPLE
	 */
	
	public List<Serie> selectAllSerie() {
		Transaction transaction = null;
		List<Serie> series = null;
		Serie s = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			series = session.createQuery("from S", Serie .class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return series;
	}
}