package com.polsri.union.app.service.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polsri.union.app.dao.GoodsCategoryDao;
import com.polsri.union.app.dao.GoodsDao;
import com.polsri.union.app.dao.GoodsSupplierDao;
import com.polsri.union.app.domain.Goods;
import com.polsri.union.app.service.GoodsService;
import com.polsri.union.app.util.core.UUIDKeyProcessor;

@Service
@Transactional(readOnly = true)
public class GoodsServiceBean implements GoodsService {

	@Autowired
	private GoodsDao goodsDao;

	@Autowired
	private GoodsCategoryDao goodsCategoryDao;

	@Autowired
	private GoodsSupplierDao goodsSupplierDao;

	@Override
	@Transactional(readOnly = false)
	public void insert(Goods goods) {
		// TODO Auto-generated method stub
		goods.setGoodsId(UUIDKeyProcessor.generateUUID());
		goodsDao.insert(goods);
		goodsCategoryDao.insert(goods.getGoodsId(), goods.getCategory());
		goodsSupplierDao.insert(goods.getGoodsId(), goods.getSupplier());
	}

	@Override
	@Transactional(readOnly = false)
	public void update(Goods goods) {
		// TODO Auto-generated method stub
		goodsDao.update(goods);
		goodsCategoryDao.update(goods.getGoodsId(), goods.getCategory());
		goodsSupplierDao.update(goods.getGoodsId(), goods.getSupplier());
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(String id) {
		// TODO Auto-generated method stub
		goodsDao.delete(new Goods(id, null, null, null, 0, null, null, null, null, null, null, null, null));
		goodsCategoryDao.delete(id);
		goodsSupplierDao.delete(id);
	}

	@Override
	public Goods findById(String id) {
		// TODO Auto-generated method stub
		return goodsDao.findById(id);
	}

	@Override
	public List<Goods> findAll(int start, int limit) {
		// TODO Auto-generated method stub
		return goodsDao.findAll(start, limit);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return goodsDao.count();
	}

}
