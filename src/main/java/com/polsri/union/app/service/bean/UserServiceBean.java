package com.polsri.union.app.service.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polsri.union.app.dao.UserDao;
import com.polsri.union.app.domain.User;
import com.polsri.union.app.service.UserService;
import com.polsri.union.app.util.core.UUIDKeyProcessor;
import com.polsri.union.app.util.credential.CredentialsInspector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.dao.EmptyResultDataAccessException;

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
		try {
			user.setPassword(CredentialsInspector.generatePassword(user.getPassword()));
		} catch (Exception ex) {

		}
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
	public List<User> findAllUser(int start, int limit) {
		// TODO Auto-generated method stub
		return dao.findAllUser(start, limit);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(String userId) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserId(userId);
		dao.delete(user);
	}

	@Override
	public Long countTotalUser() {
		// TODO Auto-generated method stub
		return dao.countTotalUser();
	}

    @Override
    public boolean isValidUser(String principal, String credential) {
         boolean valid=false; 
         System.out.println(principal+":"+credential);
         try {
             User user=dao.findUser(new User(null, principal, null, 0, null, null, null, null));
             valid=CredentialsInspector.isPasswordMacthes(credential, user.getPassword());
         } catch (Exception ex) {
             ex.printStackTrace();
             if(ex instanceof EmptyResultDataAccessException){
         
             }
         }
         return valid;
    }

}
