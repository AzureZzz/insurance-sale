package com.sale.service;

import com.sale.entity.HolderBill;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface IHolderBillService {
	
	Integer saveHolderBill(HolderBill holderBill) throws Exception;

	List<HolderBill> getBillsIdPage(Integer cId, int offest, int pageSize, boolean sortDir, String column, String keyWord) throws Exception;

	int getIdBillsCount(Integer id) throws Exception;

	HolderBill getHolderBill(Integer id) throws Exception;

	List<HolderBill> getIdBills(Integer id) throws Exception;

	int getTodayCount(Integer id) throws Exception;

	List<Integer> getWeekCount(Integer id) throws Exception;
}
