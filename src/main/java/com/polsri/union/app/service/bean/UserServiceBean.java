package com.polsri.union.app.service.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polsri.union.app.dao.UserDao;
import com.polsri.union.app.domain.User;
import com.polsri.union.app.service.UserService;
import com.polsri.union.app.util.core.UUIDKeyProcessor;

@Service
@Transactional(readOnly = true)
public class UserServiceBean implements UserService {

	@Autowired
	private UserDao dao;

	@Override
	@Transactional(readOnly = false)
	public void insert(User user) {
		// TODO Auto-generated method stub
		user.setUserId(UUIDKeyProcessor.generateUUID());
		dao.insert(user);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(User user) {
		// TODO Auto-generated method stub
		dao.update(user);
	}

	@Override
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUsername(username);
		return dao.findUser(user);
	}

	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return dao.findAllUser();
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(String userId) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserId(userId);
		dao.delete(user);
	}

}
