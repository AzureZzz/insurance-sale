package com.query.service;

import com.query.entity.HolderBill;

import java.util.List;
import java.util.Set;

public interface IHolderBillService {

	HolderBill getHolderBill(Integer id) throws Exception;
	
	void deleteHolderBill(Integer id) throws Exception ;

	void updateHolderBill(HolderBill holderBill) throws Exception;
	
	void deleteHolderBills(Set<Integer> ids) throws Exception;

	List<HolderBill> getAllBills() throws Exception;

	List<HolderBill> getBillsById(Integer cId) throws Exception;

	List<HolderBill> getBillsPage(int offest, int pageSize, boolean sortDir, String column, String keyWord) throws Exception;

	int getBillsCount() throws Exception;

	List<Integer> getPolsCount() throws Exception;

	List<Integer> getWeekCount() throws Exception;
}
