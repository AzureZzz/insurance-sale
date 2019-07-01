package com.sale.dao.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sale.dao.IInsuredDao;
import com.sale.entity.Clerk;
import com.sale.entity.HolderBill;
import com.sale.entity.Insured;

@Repository("IInsuredDao")
public class InsuredDaoImpl implements IInsuredDao {

	@Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }
	
	@Override
	public Insured load(Integer id) throws Exception{
		Session session = getCurrentSession();
    	session.beginTransaction();
    	Insured insured = session.load(Insured.class, id);
    	session.getTransaction().commit();
        return insured;
	}

	@Override
	public Insured get(Integer id) throws Exception{
		Session session = getCurrentSession();
    	session.beginTransaction();
    	Insured insured = session.get(Insured.class, id);
    	session.getTransaction().commit();
        return insured;
	}

	@Override
	public Integer save(Insured entity) throws Exception{
		Session session = getCurrentSession();
    	session.beginTransaction();
    	Integer id = (Integer)session.save(entity);
    	session.getTransaction().commit();
        return id;
	}

	@Override
	public void saveOrUpdate(Insured entity) throws Exception{
		Session session = getCurrentSession();
    	session.beginTransaction();
    	session.saveOrUpdate(entity);
    	session.getTransaction().commit();
	}

	@Override
	public void update(Insured entity) throws Exception {
		Session session = getCurrentSession();
		session.beginTransaction();
		session.update(entity);
		session.getTransaction().commit();
	}

	@Override
	public void delete(Integer id) throws Exception{
		Session session = getCurrentSession();
    	session.beginTransaction();
    	session.delete(session.get(Insured.class, id));
    	session.getTransaction().commit();
	}

	@Override
	public List<Insured> findAll() throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persist(Insured entity) throws Exception{
		Session session = getCurrentSession();
    	session.beginTransaction();
    	session.persist(entity);
    	session.getTransaction().commit();
	}

	@Override
	public void flush() throws Exception{
		Session session = getCurrentSession();
    	session.beginTransaction();
    	session.flush();
    	session.getTransaction().commit();
	}

	@Override
	public List<Insured> getAll(Integer id) throws Exception {
		String hql = "FROM Insured WHERE BILL_ID = ? ";
		Session session = getCurrentSession();
		session.beginTransaction();
		List<Insured> insureds = session.createQuery(hql).setParameter(0, id).list();
		session.getTransaction().commit();
		return insureds;
	}
}
