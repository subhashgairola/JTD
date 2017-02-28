package com.jtd.spring4mvc.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Repository;

import com.jtd.spring4mvc.dao.UserDao;
import com.jtd.spring4mvc.dto.User;

@Repository
public class UserDaoImpl implements UserDao {
	/**
	 * Static list of users
	 */
	private static List<User> users;
	static {
		users = new ArrayList<User>();
		users.add(new User(1, "Sam", 25, "123 Park view Apartments NW"));
		users.add(new User(2, "John", 25, "456 Gaur Green Apartments NW"));
		users.add(new User(3, "Rohan", 25, "56 Hilltop Apartments NW"));
	}

	@Override
	public List<User> listUsers() {
		return users;
	}

	@Override
	public User get(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	@Override
	public boolean delete(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				users.remove(user);
				return true;
			}
		}
		return false;
	}

	@Override
	public User update(User user, int id) {
		for (User userTemp : users) {
			if (userTemp.getId() == id) {
				user.setId(userTemp.getId());
				users.remove(userTemp);
				users.add(user);
				return user;
			}
		}
		return null;
	}

	@Override
	public User create(User user) {
		int id = new Random().nextInt(Integer.MAX_VALUE);
		user.setId(id);
		users.add(user);
		return user;
	}

}
