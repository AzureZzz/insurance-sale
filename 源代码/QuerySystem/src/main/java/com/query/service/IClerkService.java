package com.query.service;

import com.query.entity.Clerk;
import com.query.entity.HolderBill;

import java.util.List;

public interface IClerkService {

	Integer addClerk(Clerk clerk) throws Exception;

	void deleteClerk(Integer id) throws Exception;

	void updateClerk(Clerk clerk) throws Exception;

	Clerk getClerk(Integer id) throws Exception;

	List<Clerk> getAllClerk() throws Exception;

	List<Clerk> getClerksPage(int offest, int pageSize, boolean sortDir, String column, String keyWord) throws Exception;

	int getClerksCount() throws Exception;

}
