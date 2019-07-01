package com.sale.dao;

import com.sale.entity.Clerk;

public interface IClerkDao extends ICommonDao<Clerk, Integer> {
	
	Clerk queryClerk(Clerk clerk) throws Exception;

	void modifypassword(Clerk clerk)throws Exception;
}
