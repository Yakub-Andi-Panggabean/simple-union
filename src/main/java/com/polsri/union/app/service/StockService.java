package com.polsri.union.app.service;

import java.util.List;

import com.polsri.union.app.domain.Stock;

public interface StockService {

	void insert(Stock stock);

	void update(Stock stock);

	void delete(String id);

	Stock findById(String id);

	List<Stock> findAll();

}
