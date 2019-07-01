package com.sale.dao.impl;

import com.sale.dao.IHolderBillDao;
import com.sale.entity.HolderBill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository("IHolderBillDao")
public class HolderBillDaoImpl implements IHolderBillDao{

	@Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

	@Override
	public HolderBill load(Integer id) throws Exception{
		Session session = getCurrentSession();
    	session.beginTransaction();
    	HolderBill bill = session.load(HolderBill.class, id);
    	session.getTransaction().commit();
        return bill;
	}

	@Override
	public HolderBill get(Integer id) throws Exception{
		Session session = getCurrentSession();
    	session.beginTransaction();
    	HolderBill bill = session.get(HolderBill.class, id);
    	session.getTransaction().commit();
        return bill;
	}

	@Override
	public Integer save(HolderBill entity) throws Exception{
		Session session = getCurrentSession();
    	session.beginTransaction();
    	Integer id= (Integer)session.save(entity);
    	session.getTransaction().commit();
        return id;
	}

	@Override
	public void saveOrUpdate(HolderBill entity) throws Exception{
		Session session = getCurrentSession();
    	session.beginTransaction();
    	session.saveOrUpdate(entity);
    	session.getTransaction().commit();
	}

	@Override
	public void update(HolderBill entity) throws Exception {
		Session session = getCurrentSession();
		session.beginTransaction();
		session.update(entity);
		session.getTransaction().commit();
	}

	@Override
	public void delete(Integer id) throws Exception{
		Session session = getCurrentSession();
    	session.beginTransaction();
    	session.delete(session.get(HolderBill.class, id));
    	session.getTransaction().commit();
	}

	@Override
	public List<HolderBill> findAll() throws Exception{
		String hql = "FROM HolderBill ";
		Session session = getCurrentSession();
		session.beginTransaction();
		List<HolderBill> bills = session.createQuery(hql).list();
		session.getTransaction().commit();
		return bills;
	}

	@Override
	public void persist(HolderBill entity) throws Exception{
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
	public List<HolderBill> findAllById(Integer id) throws Exception {
		String hql = "FROM HolderBill WHERE CLERK_ID = ? ";
		Session session = getCurrentSession();
		session.beginTransaction();
		List<HolderBill> bills = session.createQuery(hql).setParameter(0,id).list();
		session.getTransaction().commit();
		return bills;
	}

	@Override
	public List<HolderBill> findIdPage(Integer id, int offest, int size, boolean sortDir, String column, String keyWord) {

		String hql = null;
		if(sortDir){
			if(keyWord.equals("")) {
				hql = "FROM HolderBill order by "+column+" desc";
			}else{
				hql = " FROM HolderBill where CLERK_ID = "+id+" and BAODAN_NO LIKE  '%"+keyWord+"%' " +
						"or BIRTH_DATE LIKE  '%"+keyWord+"%'" +
						"or POL_NAME LIKE  '%"+keyWord+"%'" +
						"or HOLDER_NAME LIKE  '%"+keyWord+"%'" +
						"order by "+column+" desc ";
			}

		}else {
			if(keyWord.equals("")) {
				hql = "FROM HolderBill order by "+column+" asc";
			}else {
				hql = " FROM HolderBill where CLERK_ID = "+id+" and BAODAN_NO LIKE  '%" + keyWord + "%' " +
						"or BIRTH_DATE LIKE  '%" + keyWord + "%'" +
						"or POL_NAME LIKE  '%" + keyWord + "%'" +
						"or HOLDER_NAME LIKE  '%" + keyWord + "%'" +
						"order by " + column + " asc ";
			}
		}
		Session session = getCurrentSession();
		session.beginTransaction();
		List<HolderBill> bills = session.createQuery(hql).setFirstResult(offest).setMaxResults(size).list();
		session.getTransaction().commit();
		return bills;
	}
	@Override
	public int getIdCount(Integer id) throws Exception {
		String hql = "SELECT count(*) from HolderBill WHERE CLERK_ID = ? ";
		Session session = getCurrentSession();
		session.beginTransaction();
		Long count = (Long)session.createQuery(hql).setParameter(0,id).uniqueResult();
		session.getTransaction().commit();
		return  count.intValue();
	}

	@Override
	public int getTodayCount(String date,String date2,Integer id) throws Exception {
		String hql = "SELECT count(*) from HolderBill WHERE CLERK_ID = ? and INFORCE_TIME > ? and INFORCE_TIME < ? ";
		Session session = getCurrentSession();
		session.beginTransaction();
		Long count = (Long)session.createQuery(hql).setParameter(0,id).setParameter(1,date).setParameter(2,date2).uniqueResult();
		session.getTransaction().commit();
		return  count.intValue();
	}

}
