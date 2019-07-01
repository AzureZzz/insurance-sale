package com.query.dao.impl;

import com.query.dao.IAdminDao;
import com.query.entity.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("IAdminDao")
public class AdminDaoImpl implements IAdminDao{

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }


    @Override
    public Admin queryAdmin(Admin admin) throws Exception {
        String hql = "FROM Admin WHERE USERNAME = ? AND PASSWORD = ?";
        Session session = getCurrentSession();
        session.beginTransaction();
        List<Admin> admins = session.createQuery(hql).setParameter(0, admin.getUsername()).setParameter(1, admin.getPassword()).list();
        session.getTransaction().commit();
        if(admins.isEmpty()) {
            return null;
        }
        return admins.get(0);
    }

    @Override
    public void modifyps(Admin admin) throws Exception {
        Session session = getCurrentSession();
        session.beginTransaction();
        session.update(admin);
        session.getTransaction().commit();
    }

    @Override
    public Admin load(Integer id) throws Exception {
        Session session = getCurrentSession();
        session.beginTransaction();
        Admin admin = session.load(Admin.class, id);
        session.getTransaction().commit();
        return admin;
    }

    @Override
    public Admin get(Integer id) throws Exception {
        Session session = getCurrentSession();
        session.beginTransaction();
        Admin admin = session.get(Admin.class, id);
        session.getTransaction().commit();
        return admin;
    }

    @Override
    public Integer save(Admin admin) throws Exception {
        Session session = getCurrentSession();
        session.beginTransaction();
        Integer id= (Integer)session.save(admin);
        session.getTransaction().commit();
        return id;
    }

    @Override
    public void saveOrUpdate(Admin entity) throws Exception {
    }

    @Override
    public void update(Admin entity) throws Exception {
        Session session = getCurrentSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Integer id) throws Exception {

    }

    @Override
    public List<Admin> findAll() throws Exception {
        return null;
    }

    @Override
    public void persist(Admin entity) throws Exception {

    }

    @Override
    public void flush() throws Exception {

    }
}
