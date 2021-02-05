package russianhackers.api.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import russianhackers.api.model.User;

@Repository("postgres")
public class UserDataAccessService implements UserDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public UserDataAccessService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insertUser(UUID id, User user) {
		return 0;
	}

	@Override
	public List<User> selectAllPeople() {
		final String sql = "SELECT id, name, email FROM users";
		return jdbcTemplate.query(sql, (resultSet, i) -> {
			UUID id = UUID.fromString(resultSet.getString("id"));
			String name = resultSet.getString("name");
			String email = resultSet.getString("email");
			return new User(id, name, email);
		});
	}

	@Override
	public Optional<User> selectUserById(UUID id) {
		final String sql = "SELECT id, name, email FROM users WHERE id = ?";
		User user = jdbcTemplate.queryForObject(
						sql,
						new Object[]{id},
						(resultSet, i) -> {
							UUID userId = UUID.fromString(resultSet.getString("id"));
							String name = resultSet.getString("name");
							String email = resultSet.getString("email");
							return new User(userId, name, email);
						}
		);
		return Optional.ofNullable(user);
	}

	@Override
	public int deleteUserById(UUID id) {
		return 0;
	}

	@Override
	public int updateUserById(UUID id, User user) {
		return 0;
	}
}
