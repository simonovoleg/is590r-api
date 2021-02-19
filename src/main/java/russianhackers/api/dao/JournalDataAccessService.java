package russianhackers.api.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import russianhackers.api.model.Journal;

@Repository("postgres-journal")
public class JournalDataAccessService implements JournalDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JournalDataAccessService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insertJournal(UUID journal_id, Journal journal) {
		final String sql = "INSERT INTO journals (journal_id, user_id, journal_name, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?)";
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				sql,
				journal_id, journal.getUser_id(), journal.getJournal_name(), timestamp, timestamp
		);
		return 0;
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

//	@Override //NEEDS WORK
//	public List<Journal> selectJournalByUserId(UUID user_id) {
//		final String sql = "SELECT journal_id, user_id, journal_name, createdAt, updatedAt FROM journals";
//		return jdbcTemplate.query(sql, new Object[]{user_id}, (resultSet, i) -> {
//			UUID journalId = UUID.fromString(resultSet.getString("journal_id"));
//			UUID userId = UUID.fromString(resultSet.getString("user_id"));
//			String journalName = resultSet.getString("journal_name");
//			Timestamp createdAt = resultSet.getTimestamp("createdAt");
//			Timestamp updatedAt = resultSet.getTimestamp("updatedAt");
//			return new Journal(journalId, userId, journalName, createdAt, updatedAt);
//		});
//	}

	@Override
	public int deleteJournalById(UUID journal_id) {
		jdbcTemplate.update("delete from journals where journal_id = ?", journal_id);
		return 0;
	}

	@Override
	public int updateJournalById(UUID journal_id, Journal journal) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update("update journals set journal_name = ?, updatedAt = ? where journal_id = ?", journal.getJournal_name(), timestamp, journal_id);
		return 0;
	}
}
