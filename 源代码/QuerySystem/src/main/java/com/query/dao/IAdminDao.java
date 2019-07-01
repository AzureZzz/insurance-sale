package com.query.dao;

import com.query.entity.Admin;

public interface IAdminDao extends ICommonDao<Admin,Integer>{

    Admin queryAdmin(Admin admin) throws Exception;

    void modifyps(Admin admin) throws Exception;
}
