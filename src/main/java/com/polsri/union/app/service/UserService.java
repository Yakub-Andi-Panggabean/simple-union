package com.polsri.union.app.service;

import java.util.List;

import com.polsri.union.app.domain.User;

public interface UserService {

	void insert(User user);

	void update(User user);

	void delete(String userId);

	User findByUserName(String userName);

	List<User> findAllUser(int start, int limit);

	Long countTotalUser();
        
        boolean isValidUser(String principal,String credential);

}
