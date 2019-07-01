package com.query.dao;

import com.query.entity.Clerk;

import java.util.List;

public interface IClerkDao extends ICommonDao<Clerk, Integer> {
	
	Clerk queryClerk(Clerk clerk) throws Exception;

	List<Clerk> findAllPage(int offest, int size, boolean sortDir, String column, String keyWord) throws Exception;

	int getAllCount() throws Exception;
}
