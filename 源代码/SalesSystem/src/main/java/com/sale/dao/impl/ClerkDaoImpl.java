package com.sale.dao.impl;

import com.sale.dao.IClerkDao;
import com.sale.entity.Clerk;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("IClerkDao")
public class ClerkDaoImpl implements IClerkDao {

	@Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }
	
	@Override
	public Clerk load(Integer id) throws Exception{
		Session session = getCurrentSession();
    	session.beginTransaction();
    	Clerk clerk = session.get(Clerk.class, id);
    	session.getTransaction().commit();
        return clerk;
	}

	@Override
	public Clerk get(Integer id) throws Exception{
		Session session = getCurrentSession();
    	session.beginTransaction();
    	Clerk clerk = session.get(Clerk.class, id);
    	session.getTransaction().commit();
        return clerk;
	}

	@Override
	public Integer save(Clerk entity) throws Exception{
		Session session = getCurrentSession();
    	session.beginTransaction();
    	Integer id= (Integer)session.save(entity);
    	session.getTransaction().commit();
        return id;
	}

	@Override
	public void saveOrUpdate(Clerk entity) throws Exception{
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Clerk entity) throws Exception {
    	Clerk clerk = get(entity.getClerkId());
    	clerk.setBirthday(entity.getBirthday());
    	clerk.setMobile(entity.getMobile());
    	clerk.setNote(entity.getNote());
    	clerk.setSex(entity.getSex());
    	clerk.setClerkName(entity.getClerkName());
		Session session = getCurrentSession();
		session.beginTransaction();
		session.update(clerk);
		session.getTransaction().commit();
	}

	@Override
	public void delete(Integer id) throws Exception{
		Session session = getCurrentSession();
    	session.beginTransaction();
    	session.delete(session.get(Clerk.class, id));
    	session.getTransaction().commit();
	}

	@Override
	public List<Clerk> findAll() throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persist(Clerk entity) throws Exception{
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
	public Clerk queryClerk(Clerk clerk) throws Exception{
		String hql = "FROM Clerk WHERE CLERK_ID = ? AND PASSWORD = ?";
		Session session = getCurrentSession();
		session.beginTransaction();
		List<Clerk> clerks = session.createQuery(hql).setParameter(0, clerk.getClerkId()).setParameter(1, clerk.getPassword()).list();
		session.getTransaction().commit();
		if(clerks.isEmpty()) {
			return null;
		}
		return clerks.get(0);
	}

	@Override
	public void modifypassword(Clerk clerk) throws Exception {
		Session session = getCurrentSession();
		session.beginTransaction();
		session.update(clerk);
		session.getTransaction().commit();
	}

}
