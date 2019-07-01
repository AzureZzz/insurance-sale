package com.query.dao;

import com.query.entity.HolderBill;

import java.util.List;
import java.util.Set;

public interface IHolderBillDao extends ICommonDao<HolderBill, Integer> {
	
	void deleteAll(Set<Integer> ids) throws Exception;

	List<HolderBill> findAllById(Integer id) throws Exception;

	List<HolderBill> findAllPage(int offest, int size, boolean sortDir, String column, String keyWord) throws Exception;

	int getAllCount() throws Exception;

	int getIdCount(Integer id) throws Exception;

	int getPolCount(String polName) throws Exception;

	int getDayCount(String date1,String date2) throws Exception;
}
