package russianhackers.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import russianhackers.api.dao.UserDao;
import russianhackers.api.model.User;

@Service
public class UserService {

	private final UserDao userDao;

	@Autowired
	public UserService (@Qualifier("postgres-user") UserDao userDao) {
		this.userDao = userDao;
	}

	public int addUser(User user) {
		return userDao.insertUser(user);
	}

	public List<User> getAllPeople() {
		return userDao.selectAllPeople();
	}

	public Optional<User> getUserById(UUID user_id) {
		return userDao.selectUserById(user_id);
	}

	public int deleteUser(UUID user_id) {
		return userDao.deleteUserById(user_id);
	}

	public int updateUser(UUID user_id, User newUser) {
		return userDao.updateUserById(user_id, newUser);
	}

}
