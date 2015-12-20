package com.polsri.union.app.service;

import java.util.List;

import com.polsri.union.app.domain.Supplier;

public interface SupplierService {
	void insert(Supplier supplier);

	void update(Supplier supplier);

	void delete(String supplierId);

	Supplier findById(String supplierId);

	List<Supplier> findAllSupplier();
}
