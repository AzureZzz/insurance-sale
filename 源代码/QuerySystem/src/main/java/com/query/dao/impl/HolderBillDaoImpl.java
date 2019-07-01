package com.query.dao.impl;

import com.query.dao.IHolderBillDao;
import com.query.entity.HolderBill;
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
    	HolderBill bill = get(entity.getId());
    	bill.setBirthDate(entity.getBirthDate());
    	bill.setHolderName(entity.getHolderName());
    	bill.setMobile(entity.getMobile());
    	bill.setMoney(entity.getMoney());
    	bill.setSex(entity.getSex());
    	bill.setPolName(entity.getPolName());
    	bill.setInsureds(entity.getInsureds());
		Session session = getCurrentSession();
		session.beginTransaction();
		session.update(bill);
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
	public void deleteAll(Set<Integer> ids) throws Exception{
		String hql = " Delete FROM HOLDERBILL WHERE ID = ? ";
		Session session = getCurrentSession();
		session.beginTransaction();
		for (Integer id : ids) {
			session.createQuery(hql).setParameter(0, id).executeUpdate();
		}
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
	public List<HolderBill> findAllPage(int offest, int size, boolean sortDir, String column, String keyWord) throws Exception {

//    	String hql = null;
//    	if(sortDir){
//    		if(keyWord.equals("")) {
//				hql = "FROM HolderBill order by "+column+" desc";
//			}else{
//				hql = " FROM HolderBill where BAODAN_NO LIKE  '%"+keyWord+"%' " +
//						"or BIRTH_DATE LIKE  '%"+keyWord+"%'" +
//						"or POL_NAME LIKE  '%"+keyWord+"%'" +
//						"or HOLDER_NAME LIKE  '%"+keyWord+"%'" +
//						"or CLERK_ID LIKE  '%"+keyWord+"%'" +
//						"order by "+column+" desc ";
//			}
//
//		}else {
//			if(keyWord.equals("")) {
//				hql = "FROM HolderBill order by "+column+" asc";
//			}else {
//				hql = " FROM HolderBill where BAODAN_NO LIKE  '%" + keyWord + "%' " +
//						"or BIRTH_DATE LIKE  '%" + keyWord + "%'" +
//						"or POL_NAME LIKE  '%" + keyWord + "%'" +
//						"or HOLDER_NAME LIKE  '%" + keyWord + "%'" +
//						"or CLERK_ID LIKE  '%"+keyWord+"%'" +
//						"order by " + column + " asc ";
//			}
//		}
		String hql = null;
		if(sortDir){
			if(keyWord.equals("")) {
				hql = "FROM HolderBill order by "+column+" desc ";
			}else {
				hql = " FROM HolderBill where BAODAN_NO LIKE ? " +
						"or POL_NAME LIKE  ? " +
						"or HOLDER_NAME LIKE ? " +
						"or CLERK_ID LIKE ? " +
						"order by " + column + " desc ";
				Session session = getCurrentSession();
				session.beginTransaction();
				org.hibernate.query.Query query = session.createQuery(hql);
				List<HolderBill> bills = query.setParameter(0,"%"+keyWord+"%")
						.setParameter(1,"%"+keyWord+"%")
						.setParameter(2,"%"+keyWord+"%")
						.setParameter(3,"%"+keyWord+"%")
						.setFirstResult(offest).setMaxResults(size).list();
				session.getTransaction().commit();
				return bills;
			}

		}else {
			if(keyWord.equals("")) {
				hql = "FROM HolderBill order by "+column+" asc";
			}else {
				hql = " FROM HolderBill where BAODAN_NO LIKE ? " +
						"or POL_NAME LIKE  ? " +
						"or HOLDER_NAME LIKE ? " +
						"or CLERK_ID LIKE ? " +
						"order by " + column + " asc ";
				Session session = getCurrentSession();
				session.beginTransaction();
				org.hibernate.query.Query query = session.createQuery(hql);
				List<HolderBill> bills = query.setParameter(0,"%"+keyWord+"%")
						.setParameter(1,"%"+keyWord+"%")
						.setParameter(2,"%"+keyWord+"%")
						.setParameter(3,"%"+keyWord+"%")
						.setFirstResult(offest).setMaxResults(size).list();
				session.getTransaction().commit();
				return bills;
			}
		}
		Session session = getCurrentSession();
		session.beginTransaction();
		org.hibernate.query.Query query = session.createQuery(hql);
		List<HolderBill> bills = query.setFirstResult(offest).setMaxResults(size).list();
		session.getTransaction().commit();
		return bills;
	}

	@Override
	public int getAllCount() throws Exception {
		String hql = "SELECT count(*) from HolderBill ";
		Session session = getCurrentSession();
		session.beginTransaction();
		Long count = (Long)session.createQuery(hql).uniqueResult();
		session.getTransaction().commit();
		return  count.intValue();
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
	public int getPolCount(String polName) throws Exception {
		String hql = "SELECT count(*) from HolderBill WHERE POL_NAME = ? ";
		Session session = getCurrentSession();
		session.beginTransaction();
		Long count = (Long)session.createQuery(hql).setParameter(0,polName).uniqueResult();
		session.getTransaction().commit();
		return  count.intValue();
	}

	@Override
	public int getDayCount(String date1,String data2) throws Exception {
		String hql = "SELECT count(*) from HolderBill WHERE INFORCE_TIME > ? and INFORCE_TIME < ? ";
		Session session = getCurrentSession();
		session.beginTransaction();
		Long count = (Long)session.createQuery(hql).setParameter(0,date1).setParameter(1,data2).uniqueResult();
		session.getTransaction().commit();
		return  count.intValue();
	}

}
