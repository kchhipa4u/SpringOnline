package com.onlinelearning.day11.REST.user;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();

	private static int usersCount = 3;

	static {
		users.add(new User(1, "Aanya", new Date()));
		users.add(new User(2, "Prayan", new Date()));
		users.add(new User(3, "Ayaan", new Date()));
	}

	public List<User> findAll() {
		return users;
	}

	public User saveOrUpdate(User user) {
		if (user.getId() == null) {
			user.setId(++usersCount);
			users.add(user);
		} else {
			Iterator<User> iterator = users.iterator();
			int index = 0;
			while (iterator.hasNext()) {
				User usr = iterator.next();
				if (usr.getId() == user.getId()) {
					users.set(index, user);
					break;
				}
				++index;
			}
		}
		return user;
	}

	public User findOne(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}


}