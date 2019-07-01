package com.sale.service.impl;

import com.sale.dao.IClerkDao;
import com.sale.entity.Clerk;
import com.sale.exceptions.PasswordErrorException;
import com.sale.service.IClerkService;
import com.sale.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("IClerkService")
public class ClerkServiceImpl implements IClerkService {

	@Autowired
	private IClerkDao clerkDao;
	
	@Override
	public Clerk login(Clerk clerk) throws Exception{
		clerk.setPassword(ServiceUtils.md5(clerk.getPassword()));
		return clerkDao.queryClerk(clerk);
	}

	@Override
	public Clerk getClerk(Integer id) throws Exception{
		Clerk clerk = clerkDao.get(id);
		clerk.setPassword("");
		return clerk;
	}

	@Override
	public void updateClerk(Clerk clerk) throws Exception {
		clerkDao.update(clerk);
	}

	@Override
	public void modifyPassword(Clerk clerk,String newPassword) throws Exception {
		Clerk c = clerkDao.get(clerk.getClerkId());
		if(ServiceUtils.md5(clerk.getPassword()).equals(c.getPassword())){
			c.setPassword(ServiceUtils.md5(newPassword));
			clerkDao.modifypassword(c);
		}else{
			throw new PasswordErrorException();
		}
	}

}
