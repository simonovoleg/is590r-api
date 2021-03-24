package russianhackers.api.dao;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import russianhackers.api.auth.ApplicationUser;
import russianhackers.api.model.Journal;
import russianhackers.api.model.User;

@Repository("postgres-journal")
public class JournalDataAccessService implements JournalDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JournalDataAccessService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Journal insertJournal(UUID journal_id, Journal journal, Principal principal) {
		final ApplicationUser applicationUser = jdbcTemplate.queryForObject(
						"SELECT * FROM users WHERE username = ?", new Object[]{principal.getName()},
						(resultSet, i) -> {
							UUID id = UUID.fromString(resultSet.getString("user_id"));
							String name = resultSet.getString("name");
							String username = resultSet.getString("username");
							String password = resultSet.getString("password");
							String email = resultSet.getString("email");
							String role = resultSet.getString("role");
							Boolean isAccountNonExpired = resultSet.getBoolean("isAccountNonExpired");
							Boolean isAccountNonLocked = resultSet.getBoolean("isAccountNonLocked");
							Boolean isCredentialsNonExpired = resultSet.getBoolean("isCredentialsNonExpired");
							Boolean isEnabled = resultSet.getBoolean("isEnabled");
							return new ApplicationUser(id, name, username, password, role, email, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled);
						}
		);

		final String sql = "INSERT INTO journals (journal_id, user_id, journal_name, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?)";
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				sql,
				journal_id, applicationUser.getUserid(), journal.getJournal_name(), timestamp, timestamp
		);
		Journal newJournal = jdbcTemplate.queryForObject(
			"SELECT * FROM journals WHERE journal_id = ?",
			new Object[]{journal_id},
			(resultSet, i) -> {
				UUID journalId = UUID.fromString(resultSet.getString("journal_id"));
				UUID userId = UUID.fromString(resultSet.getString("user_id"));
				String journalName = resultSet.getString("journal_name");
				Timestamp createdAt = resultSet.getTimestamp("createdAt");
				Timestamp updatedAt = resultSet.getTimestamp("updatedAt");
				return new Journal(journalId, userId, journalName, createdAt, updatedAt);
			}
		);
		return newJournal;
	}

	@Override
	public List<Journal> selectAllJournals() {
		final String sql = "SELECT journal_id, user_id, journal_name, createdAt, updatedAt FROM journals";
		return jdbcTemplate.query(sql, (resultSet, i) -> {
			UUID journalId = UUID.fromString(resultSet.getString("journal_id"));
			UUID userId = UUID.fromString(resultSet.getString("user_id"));
			String journalName = resultSet.getString("journal_name");
			Timestamp createdAt = resultSet.getTimestamp("createdAt");
			Timestamp updatedAt = resultSet.getTimestamp("updatedAt");
			return new Journal(journalId, userId, journalName, createdAt, updatedAt);
		});
	}

	@Override
	public Optional<Journal> selectJournalById(UUID journal_id) {
		final String sql = "SELECT journal_id, user_id, journal_name, createdAt, updatedAt FROM journals WHERE journal_id = ?";
		Journal journal = jdbcTemplate.queryForObject(
			sql,
			new Object[]{journal_id},
			(resultSet, i) -> {
				UUID journalId = UUID.fromString(resultSet.getString("journal_id"));
				UUID userId = UUID.fromString(resultSet.getString("user_id"));
				String journalName = resultSet.getString("journal_name");
				Timestamp createdAt = resultSet.getTimestamp("createdAt");
				Timestamp updatedAt = resultSet.getTimestamp("updatedAt");
				return new Journal(journalId, userId, journalName, createdAt, updatedAt);
			}
		);
		return Optional.ofNullable(journal);
	}

	@Override
	public List<Journal> selectUserJournals(Principal principal) {
		final ApplicationUser applicationUser = jdbcTemplate.queryForObject(
				"SELECT * FROM users WHERE username = ?", new Object[]{principal.getName()},
				(resultSet, i) -> {
					UUID id = UUID.fromString(resultSet.getString("user_id"));
					String name = resultSet.getString("name");
					String username = resultSet.getString("username");
					String password = resultSet.getString("password");
					String email = resultSet.getString("email");
					String role = resultSet.getString("role");
					Boolean isAccountNonExpired = resultSet.getBoolean("isAccountNonExpired");
					Boolean isAccountNonLocked = resultSet.getBoolean("isAccountNonLocked");
					Boolean isCredentialsNonExpired = resultSet.getBoolean("isCredentialsNonExpired");
					Boolean isEnabled = resultSet.getBoolean("isEnabled");
					return new ApplicationUser(id, name, username, password, role, email, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled);
				}
		);
		//TODO: check if admin and allow if admin

		final String sql = "SELECT j.journal_id, j.user_id, j.journal_name, j.createdAt, j.updatedAt " +
							"FROM journals j JOIN users u ON j.user_id = u.user_id WHERE u.username = ?";
		return jdbcTemplate.query(
			sql,
			new Object[]{applicationUser.getUsername()},
			(resultSet, i) -> {
				UUID journalId = UUID.fromString(resultSet.getString("journal_id"));
				UUID userId = UUID.fromString(resultSet.getString("user_id"));
				String journalName = resultSet.getString("journal_name");
				Timestamp createdAt = resultSet.getTimestamp("createdAt");
				Timestamp updatedAt = resultSet.getTimestamp("updatedAt");
				return new Journal(journalId, userId, journalName, createdAt, updatedAt);
			}
		);
	}

	@Override
	public int deleteJournalById(UUID journal_id) {
		jdbcTemplate.update("delete from journals where journal_id = ?", journal_id);
		return 0;
	}

	@Override
	public Journal updateJournalById(UUID journal_id, Journal journal) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update("update journals set journal_name = ?, updatedAt = ? where journal_id = ?", journal.getJournal_name(), timestamp, journal_id);
		Journal updatedJournal = jdbcTemplate.queryForObject(
						"SELECT * FROM journals WHERE journal_id = ?",
						new Object[]{journal_id},
						(resultSet, i) -> {
							UUID journalId = UUID.fromString(resultSet.getString("journal_id"));
							UUID userId = UUID.fromString(resultSet.getString("user_id"));
							String journalName = resultSet.getString("journal_name");
							Timestamp createdAt = resultSet.getTimestamp("createdAt");
							Timestamp updatedAt = resultSet.getTimestamp("updatedAt");
							return new Journal(journalId, userId, journalName, createdAt, updatedAt);
						}
		);
		return updatedJournal;
	}
}
