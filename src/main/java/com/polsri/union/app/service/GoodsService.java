package com.polsri.union.app.service;

import java.util.List;

import com.polsri.union.app.domain.Goods;

public interface GoodsService {
	void insert(Goods goods);

	void update(Goods goods);

	void delete(String id);

	Goods findById(String id);

	List<Goods> findAll(int start, int limit);

	Long count();

}
