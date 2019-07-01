package com.sale.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.sale.dao.IHolderBillDao;
import com.sale.dao.IInsuredDao;
import com.sale.entity.HolderBill;
import com.sale.entity.Insured;
import com.sale.service.IHolderBillService;
import org.springframework.stereotype.Service;

@Service("IHolderBillService")
public class HolderBillServiceImpl implements IHolderBillService {

	@Autowired
    private IHolderBillDao holderBillDao;
	
	@Autowired
    private IInsuredDao insuredDao;

	@Override
	public Integer saveHolderBill(HolderBill holderBill) throws Exception{
		Integer id = holderBillDao.save(holderBill);
		Iterator<Insured> its = holderBill.getInsureds().iterator();
		while (its.hasNext()) {
			Insured insured = its.next();
			HolderBill bill = new HolderBill();
			bill.setId(id);
			insured.setHolderBill(bill);
			insuredDao.save(insured);
		}
		return id;
	}

}
