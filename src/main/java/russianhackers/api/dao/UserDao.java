package russianhackers.api.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import russianhackers.api.model.User;

public interface UserDao {

	int insertUser (UUID id, User user);

	default int insertUser (User user) {
		UUID id = UUID.randomUUID();
		return insertUser (id, user);
	}

	List<User> selectAllPeople();

	Optional<User> selectUserById(UUID id);

	int deleteUserById(UUID id);

	int updateUserById(UUID id, User user);
}
