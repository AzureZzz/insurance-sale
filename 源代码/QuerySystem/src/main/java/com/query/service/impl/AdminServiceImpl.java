package com.query.service.impl;

import com.query.Exception.PasswordErrorException;
import com.query.dao.IAdminDao;
import com.query.entity.Admin;
import com.query.service.IAdminService;
import com.query.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("IAdminService")
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private IAdminDao adminDao;

    @Override
    public Admin login(Admin admin) throws Exception {
        admin.setPassword(ServiceUtils.md5(admin.getPassword()));
        return adminDao.queryAdmin(admin);
    }

    @Override
    public Admin getAdmin(Integer id) throws Exception {
        return adminDao.get(id);
    }

    @Override
    public void updateAdmin(Admin admin) throws Exception {
        adminDao.update(admin);
    }

    @Override
    public void modifyPassword(Admin admin,String newPassword) throws Exception {
        Admin a = adminDao.get(admin.getId());
        System.out.println(a.getPassword());
        System.out.println(ServiceUtils.md5(admin.getPassword()));
        if(ServiceUtils.md5(admin.getPassword()).equals(a.getPassword())){
            a.setPassword(ServiceUtils.md5(newPassword));
            adminDao.modifyps(a);
        }else{
            throw new PasswordErrorException();
        }
    }
}
