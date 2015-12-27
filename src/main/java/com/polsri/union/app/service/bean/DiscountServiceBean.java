package com.polsri.union.app.service.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polsri.union.app.dao.DiscountDao;
import com.polsri.union.app.domain.Discount;
import com.polsri.union.app.service.DiscountService;

@Service
@Transactional(readOnly = true)
public class DiscountServiceBean implements DiscountService {

	@Autowired
	private DiscountDao dao;

	@Override
	@Transactional(readOnly = false)
	public void insert(Discount discount) {
		// TODO Auto-generated method stub
		dao.insert(discount);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(Discount discount) {
		// TODO Auto-generated method stub
		dao.update(discount);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(String id) {
		// TODO Auto-generated method stub
		dao.delete(new Discount(id, null, null, null, null, null, null, null, null, null, null));
	}

	@Override
	public Discount findById(String id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<Discount> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

}
