package com.polsri.union.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.polsri.union.app.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
        
	public void insert(User user) {
		jdbcTemplate
				.update(user.generateInsertQuery(),
						new Object[] { user.getUserId(), user.getUsername(), user.getPassword(), user.getActive(),
								user.getCreatedDate(), user.getCreatedBy(), user.getUpdatedDate(),
								user.getUpdatedBy() });
	}

	public void update(User user) {
		jdbcTemplate.update(user.generateUpdateQuery(),
				new Object[] { user.getUserId(), user.getUsername(), user.getPassword(), user.getActive(),
						user.getCreatedDate(), user.getCreatedBy(), user.getUpdatedDate(), user.getUpdatedBy(),
						user.getUserId() });
	}

	public void delete(User user) {
		jdbcTemplate.update(user.generateDeleteQuery(), user.getUserId());
	}

	public User findUser(User user) {
                System.out.println("user : "+user.getUsername());
		return jdbcTemplate.queryForObject(user.generateSelectByQuery(false, "username"),
				new Object[] { user.getUsername() }, User.obtainRowMapper());
	}

	public List<User> findAllUser(int start, int length) {
		return jdbcTemplate.query(new User().generateSelectAllQuery(true), new Object[] { start, length },
				User.obtainRowMapper());
	}

	public Long countTotalUser() {
		return jdbcTemplate.queryForObject(new User().generateCountQuery(), Long.class);
	}


}
