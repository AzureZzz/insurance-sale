package com.sale.service.impl;

import com.sale.dao.IHolderBillDao;
import com.sale.dao.IInsuredDao;
import com.sale.entity.HolderBill;
import com.sale.entity.Insured;
import com.sale.service.IHolderBillService;
import com.sale.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.*;

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

	@Override
	public List<HolderBill> getBillsIdPage(Integer cId, int offest, int pageSize, boolean sortDir, String column, String keyWord) throws Exception {
		List<HolderBill> bills = holderBillDao.findIdPage(cId,offest,pageSize,sortDir,column,keyWord);
		return addInsureds(bills);
	}

	@Override
	public int getIdBillsCount(Integer id) throws Exception {
		return holderBillDao.getIdCount(id);
	}

	@Override
	public HolderBill getHolderBill(Integer id) throws Exception{
		HolderBill holderBill = holderBillDao.get(id);
		if(holderBill == null){
			return null;
		}
		holderBill.setClerkID(holderBill.getClerk().getClerkId());
		Set<Insured> insureds = new HashSet<>();
		List<Insured> insureds1 = insuredDao.getAll(id);
		Iterator<Insured> its =  insureds1.iterator();
		while (its.hasNext()){
			insureds.add(its.next());
		}
		holderBill.setInsureds(insureds);
		return holderBill;
	}

	@Override
	public List<HolderBill> getIdBills(Integer id) throws Exception {
		List<HolderBill> bills = addInsureds(holderBillDao.findAllById(id));
		return bills;
	}

	@Override
	public int getTodayCount(Integer id) throws Exception {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return holderBillDao.getTodayCount(sdf.format(date),sdf.format(ServiceUtils.addAndSubtractDaysByCalendar(date,1)),id);
	}

	@Override
	public List<Integer> getWeekCount(Integer id) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int week = ServiceUtils.dayForWeek(df.format(new Date()));
		Date date = ServiceUtils.addAndSubtractDaysByCalendar(new Date(),1-week);
		List<Integer> result = new ArrayList<>();
		for(int i = 0;i<7;i++){
			result.add(holderBillDao.getTodayCount(df.format(date),df.format(ServiceUtils.addAndSubtractDaysByCalendar(date,1)),id));
			date = ServiceUtils.addAndSubtractDaysByCalendar(date,1);
		}
		System.out.println(result);
		return result;
	}

	private List<HolderBill> addInsureds(List<HolderBill> bills) throws Exception{
		List<HolderBill> result = new ArrayList<>();
		Iterator<HolderBill> it = bills.iterator();
		while (it.hasNext()){
			HolderBill holderBill = it.next();
			holderBill.setInforceTime(new java.sql.Date(holderBill.getInforceTime().getTime()));
			holderBill.setClerkID(holderBill.getClerk().getClerkId());
			Set<Insured> insureds = new HashSet<>();
			Iterator<Insured> its =  insuredDao.getAll(holderBill.getId()).iterator();
			while (its.hasNext()){
				insureds.add(its.next());
			}
			holderBill.setInsureds(insureds);
			result.add(holderBill);
		}
		return result;
	}
}
