package com.polsri.union.app.service;

import java.util.List;

import com.polsri.union.app.domain.Category;

public interface CategoryService {

	void insert(Category category);

	void update(Category category);

	void delete(String categoryId);

	Category findById(String categoryId);

	List<Category> findAll();

}
