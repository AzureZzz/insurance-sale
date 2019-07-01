package com.query.service.impl;

import com.query.dao.IClerkDao;
import com.query.dao.IHolderBillDao;
import com.query.entity.Clerk;
import com.query.service.IClerkService;
import com.query.utils.ServiceUtils;
import org.hibernate.loader.plan.spi.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("IClerkService")
public class ClerkServiceImpl implements IClerkService{

	@Autowired
	private IClerkDao clerkDao;

	@Autowired
	private IHolderBillDao holderBillDao;

	@Override
	public Integer addClerk(Clerk clerk) throws Exception {
		clerk.setPassword(ServiceUtils.md5(clerk.getPassword()));
		return clerkDao.save(clerk);
	}

	@Override
	public void deleteClerk(Integer id) throws Exception{
		clerkDao.delete(id);	
	}

	@Override
	public void updateClerk(Clerk clerk) throws Exception {
		clerkDao.update(clerk);
	}

	@Override
	public Clerk getClerk(Integer id) throws Exception{
		Clerk clerk = clerkDao.get(id);
		if(clerk!=null){
			clerk.setPassword("");
			clerk.setSaleNum(holderBillDao.getIdCount(clerk.getClerkId()));
			return clerk;
		}
		return null;
	}

	@Override
	public List<Clerk> getAllClerk() throws Exception {
		List<Clerk> clerks = addSaleNum(clerkDao.findAll());
		return clerks;
	}

	@Override
	public List<Clerk> getClerksPage(int offest, int pageSize, boolean sortDir, String column, String keyWord) throws Exception {
		List<Clerk> clerks = addSaleNum(clerkDao.findAllPage(offest, pageSize, sortDir, column, keyWord));
		return clerks;
	}

	@Override
	public int getClerksCount() throws Exception {
		return clerkDao.getAllCount();
	}

	private List<Clerk> addSaleNum(List<Clerk> clerks) throws Exception{
		List<Clerk> result = new ArrayList<>();
		Iterator<Clerk> it = clerks.iterator();
		while (it.hasNext()){
			Clerk clerk = it.next();
			clerk.setPassword("");
			clerk.setSaleNum(holderBillDao.getIdCount(clerk.getClerkId()));
			result.add(clerk);
		}
		return result;
	}

}
