package com.jtd.spring4mvc.dao;

import java.util.List;

import com.jtd.spring4mvc.dto.User;

public interface UserDao {

	List<User> listUsers();
	User create(User user);
	User get(int id);
	boolean delete(int id);
	User update(User user, int id);
}
