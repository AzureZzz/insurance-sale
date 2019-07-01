package com.sale.dao;

import com.sale.entity.HolderBill;

import java.util.List;
import java.util.Set;

public interface IHolderBillDao extends ICommonDao<HolderBill, Integer> {

	List<HolderBill> findAllById(Integer id) throws Exception;

	List<HolderBill> findIdPage(Integer id, int offest, int size, boolean sortDir, String column, String keyWord);

	int getIdCount(Integer id) throws Exception;

	int getTodayCount(String date,String date2,Integer id) throws Exception;
}
