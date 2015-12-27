package com.polsri.union.app.service.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polsri.union.app.dao.PriceDao;
import com.polsri.union.app.domain.Price;
import com.polsri.union.app.service.PriceService;

@Service
@Transactional(readOnly = true)
public class PriceServiceBean implements PriceService {

	@Autowired
	private PriceDao priceDao;

	@Override
	@Transactional(readOnly = false)
	public void insert(Price price) {
		// TODO Auto-generated method stub
		priceDao.insert(price);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(Price price) {
		// TODO Auto-generated method stub
		priceDao.update(price);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(String goodsId) {
		// TODO Auto-generated method stub
		priceDao.delete(new Price(goodsId, null, null, null, null, null, null));
	}

	@Override
	public Price findById(String goodsId) {
		// TODO Auto-generated method stub
		return priceDao.findById(goodsId);
	}

	@Override
	public List<Price> findAll() {
		// TODO Auto-generated method stub
		return priceDao.findAll();
	}

}
