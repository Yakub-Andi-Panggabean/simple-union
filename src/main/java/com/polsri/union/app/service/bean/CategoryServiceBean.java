package com.polsri.union.app.service.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polsri.union.app.dao.CategoryDao;
import com.polsri.union.app.domain.Category;
import com.polsri.union.app.service.CategoryService;

@Service
@Transactional(readOnly = true)
public class CategoryServiceBean implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	@Transactional(readOnly = false)
	public void insert(Category category) {
		// TODO Auto-generated method stub
		categoryDao.insert(category);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(Category category) {
		// TODO Auto-generated method stub
		categoryDao.update(category);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(String categoryId) {
		// TODO Auto-generated method stub
		categoryDao.delete(new Category(categoryId, null, 0, null, null, null, null));
	}

	@Override
	public Category findById(String categoryId) {
		// TODO Auto-generated method stub
		return categoryDao.findById(categoryId);
	}

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}

}
