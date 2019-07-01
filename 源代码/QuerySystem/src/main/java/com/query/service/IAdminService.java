package com.query.service;

import com.query.entity.Admin;

public interface IAdminService {

    Admin login(Admin admin) throws Exception;

    Admin getAdmin(Integer id) throws Exception;

    void updateAdmin(Admin admin) throws Exception;

    void modifyPassword(Admin admin,String newPassword) throws Exception;
}
