package com.polsri.union.app.service;

import java.util.List;

import com.polsri.union.app.domain.Price;

public interface PriceService {
	void insert(Price price);

	void update(Price price);

	void delete(String goodsId);

	Price findById(String goodsId);

	List<Price> findAll();
}
