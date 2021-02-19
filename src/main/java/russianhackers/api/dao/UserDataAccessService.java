package russianhackers.api.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import russianhackers.api.model.User;

@Repository("postgres-user")
public class UserDataAccessService implements UserDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public UserDataAccessService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insertUser(UUID user_id, User user) {
		jdbcTemplate.update(
			"INSERT INTO users (user_id, name, email) VALUES (?, ?, ?)",
				user_id, user.getName(), user.getEmail()
		);
		return 0;
	}

	@Override
	public List<User> selectAllPeople() {
		final String sql = "SELECT user_id, name, email FROM users";
		return jdbcTemplate.query(sql, (resultSet, i) -> {
			UUID user_id = UUID.fromString(resultSet.getString("user_id"));
			String name = resultSet.getString("name");
			String email = resultSet.getString("email");
			return new User(user_id, name, email);
		});
	}

	@Override
	public Optional<User> selectUserById(UUID user_id) {
		final String sql = "SELECT user_id, name, email FROM users WHERE user_id = ?";
		User user = jdbcTemplate.queryForObject(
						sql,
						new Object[]{user_id},
						(resultSet, i) -> {
							UUID userId = UUID.fromString(resultSet.getString("user_id"));
							String name = resultSet.getString("name");
							String email = resultSet.getString("email");
							return new User(userId, name, email);
						}
		);
		//we get 500 status code here if we try to get user by a userid not in the db (has to be valid uuid)
		return Optional.ofNullable(user);
	}

	@Override
	public int deleteUserById(UUID user_id) {
		// I am not sure if we want to catch exceptions
		// or to use 500 status code as a signal of a fail on the front end
		//but for now, here is a try-catch block
		try {
			jdbcTemplate.update("delete from users where user_id = ?", user_id);
		} catch (Exception e) {
			return 0;
		}
		return 0;
	}

	@Override
	public int updateUserById(UUID user_id, User user) {
		try {
			jdbcTemplate.update("update users set name = ?, email = ? where user_id = ?", user.getName(), user.getEmail(), user_id);
		} catch (Exception e) {
			return 0;
		}
		return 0;
	}
}
