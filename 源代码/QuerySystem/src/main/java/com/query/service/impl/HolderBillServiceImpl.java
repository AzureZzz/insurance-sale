package com.query.service.impl;

import com.query.dao.IClerkDao;
import com.query.dao.IHolderBillDao;
import com.query.dao.IInsuredDao;
import com.query.entity.HolderBill;
import com.query.entity.Insured;
import com.query.service.IHolderBillService;
import com.query.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service("IHolderBillService")
public class HolderBillServiceImpl implements IHolderBillService {

	@Autowired
    private IHolderBillDao holderBillDao;
	
	@Autowired
    private IInsuredDao insuredDao;
	
	@Autowired
    private IClerkDao clerkDao;

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
	public void deleteHolderBill(Integer id) throws Exception{
		holderBillDao.delete(id);
	}

	@Override
	public void updateHolderBill(HolderBill holderBill) throws Exception {
		holderBillDao.update(holderBill);
	}

	@Override
	public void deleteHolderBills(Set<Integer> ids) throws Exception{
		holderBillDao.deleteAll(ids);
	}

	@Override
	public List<HolderBill> getAllBills() throws Exception {
		List<HolderBill> bills = holderBillDao.findAll();
		return addInsureds(bills);
	}

	@Override
	public List<HolderBill> getBillsById(Integer cId) throws Exception {
		List<HolderBill> bills = holderBillDao.findAllById(cId);
		return addInsureds(bills);
	}

	@Override
	public List<HolderBill> getBillsPage(int offest, int pageSize, boolean sortDir, String column,String keyWord) throws Exception {
//		int offest = pageNumToOffest(pageNumber,pageSize);
		List<HolderBill> bills = holderBillDao.findAllPage(offest,pageSize,sortDir,column.trim(),keyWord.trim());
		return addInsureds(bills);
	}


	@Override
	public int getBillsCount() throws Exception {
		return holderBillDao.getAllCount();
	}

	@Override
	public List<Integer> getPolsCount() throws Exception {
		String[] pols = {"意外险","健康险","补充医疗险","分红险"};
		List<Integer> result = new ArrayList<>();
		for(int i = 0;i<pols.length;i++){
			result.add(holderBillDao.getPolCount(pols[i]));
		}
		return result;
	}

	@Override
	public List<Integer> getWeekCount() throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int week = ServiceUtils.dayForWeek(df.format(new Date()));
		Date date = ServiceUtils.addAndSubtractDaysByCalendar(new Date(),1-week);
		List<Integer> result = new ArrayList<>();
		for(int i = 0;i<7;i++){
			result.add(holderBillDao.getDayCount(df.format(date),df.format(ServiceUtils.addAndSubtractDaysByCalendar(date,1))));
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

	private int pageNumToOffest(int pageNumber,int pageSize) throws Exception {
		if (pageNumber <= 1) {
			pageNumber = 1;
		}
		int count = holderBillDao.getAllCount();
		int offest = (pageNumber - 1) * pageSize;
		if (offest >= count - 1) {
			if(count%pageSize == 0){
				offest = (count / pageSize-1) * pageSize;
			}else{
				offest = (count / pageSize) * pageSize;
			}
		}
		return offest;
	}
}
