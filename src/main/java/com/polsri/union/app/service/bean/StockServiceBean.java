package com.polsri.union.app.service.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polsri.union.app.dao.StockDao;
import com.polsri.union.app.domain.Stock;
import com.polsri.union.app.service.StockService;

@Service
@Transactional(readOnly = true)
public class StockServiceBean implements StockService {

	@Autowired
	private StockDao dao;

	@Override
	@Transactional(readOnly = false)
	public void insert(Stock stock) {
		// TODO Auto-generated method stub
		dao.insert(stock);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(Stock stock) {
		// TODO Auto-generated method stub
		dao.update(stock);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(String id) {
		// TODO Auto-generated method stub
		dao.delete(new Stock(id, null, null, null, null, null, null, null, null, null));
	}

	@Override
	public Stock findById(String id) {
		// TODO Auto-generated method stub
		return dao.findStockById(id);
	}

	@Override
	public List<Stock> findAll() {
		// TODO Auto-generated method stub
		return dao.findAllStock();
	}

}
