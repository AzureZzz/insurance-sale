package com.sale.dao;

import java.util.List;
import java.util.Set;

import com.sale.entity.HolderBill;

public interface IHolderBillDao extends ICommonDao<HolderBill, Integer>{
	
	void deleteAll(Set<Integer> ids) throws Exception;

	List<HolderBill> findAllById(Integer id) throws Exception;

	List<HolderBill> findAllPage(int offest,int size) throws Exception;

	List<HolderBill> findIdPage(Integer id,int offest,int size);

	int getAllCount() throws Exception;

	int getIdCount(Integer id) throws Exception;
}
