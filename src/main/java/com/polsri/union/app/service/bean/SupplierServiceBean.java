package com.polsri.union.app.service.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polsri.union.app.dao.SupplierDao;
import com.polsri.union.app.domain.Supplier;
import com.polsri.union.app.service.SupplierService;

@Service
@Transactional(readOnly = true)
public class SupplierServiceBean implements SupplierService {

	@Autowired
	private SupplierDao dao;

	@Override
	@Transactional(readOnly = false)
	public void insert(Supplier supplier) {
		// TODO Auto-generated method stub
		dao.insert(supplier);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(Supplier supplier) {
		// TODO Auto-generated method stub
		dao.update(supplier);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(String supplierId) {
		// TODO Auto-generated method stub
		dao.delete(new Supplier(supplierId, null, null, null, 0, null, null, null, null));
	}

	@Override
	public Supplier findById(String supplierId) {
		// TODO Auto-generated method stub
		return dao.findById(supplierId);
	}

	@Override
	public List<Supplier> findAllSupplier() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

}
