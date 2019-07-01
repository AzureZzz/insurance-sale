package com.sale.service;

import com.sale.entity.Clerk;

public interface IClerkService {

	Clerk login(Clerk clerk) throws Exception;

	Clerk getClerk(Integer id) throws Exception;

	void updateClerk(Clerk clerk) throws Exception;

	void modifyPassword(Clerk clerk,String newPassword) throws Exception;
}
