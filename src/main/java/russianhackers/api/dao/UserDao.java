package russianhackers.api.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import russianhackers.api.model.User;

public interface UserDao {

	int insertUser (UUID user_id, User user);

	default int insertUser (User user) {
		UUID user_id = UUID.randomUUID();
		return insertUser (user_id, user);
	}

	List<User> selectAllPeople();

	Optional<User> selectUserById(UUID user_id);

	int deleteUserById(UUID user_id);

	int updateUserById(UUID user_id, User user);
}
