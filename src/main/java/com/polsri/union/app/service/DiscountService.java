package com.polsri.union.app.service;

import java.util.List;

import com.polsri.union.app.domain.Discount;

public interface DiscountService {
	void insert(Discount discount);;

	void update(Discount discount);

	void delete(String id);

	Discount findById(String id);

	List<Discount> findAll();
}
