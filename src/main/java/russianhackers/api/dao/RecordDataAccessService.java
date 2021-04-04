package russianhackers.api.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import russianhackers.api.model.Record;

@Repository("postgres-record")
public class RecordDataAccessService implements RecordDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public RecordDataAccessService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Record insertRecord(UUID journal_id, UUID record_id, Record record) {
		final String sql = "INSERT INTO records (record_id, journal_id, record_title, content, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?)";
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				sql,
				record_id, journal_id, record.getRecord_title(), record.getContent(), timestamp, timestamp
		);

		Record newRecord = jdbcTemplate.queryForObject(
		"SELECT * FROM records WHERE record_id = ?",
						new Object[]{record_id},
						(resultSet, i) -> {
							UUID recordId = UUID.fromString(resultSet.getString("record_id"));
							UUID journalId = UUID.fromString(resultSet.getString("journal_id"));
							String recordTitle = resultSet.getString("record_title");
							String recordContent = resultSet.getString("content");
							Timestamp createdAt = resultSet.getTimestamp("createdAt");
							Timestamp updatedAt = resultSet.getTimestamp("updatedAt");
							return new Record(recordId, journalId, recordTitle, recordContent, createdAt, updatedAt);
						});
		return newRecord;
	}

	@Override
	public List<Record> selectRecordsByJournalId(UUID journal_id)  {
		final String sql = "SELECT record_id, journal_id, record_title, content, createdAt, updatedAt FROM records WHERE journal_id = ?";
		return jdbcTemplate.query(
			sql,
			new Object[]{journal_id},
			(resultSet, i) -> {
				UUID recordId = UUID.fromString(resultSet.getString("record_id"));
				UUID journalId = UUID.fromString(resultSet.getString("journal_id"));
				String recordTitle = resultSet.getString("record_title");
				String recordContent = resultSet.getString("content");
				Timestamp createdAt = resultSet.getTimestamp("createdAt");
				Timestamp updatedAt = resultSet.getTimestamp("updatedAt");
				return new Record(recordId, journalId, recordTitle, recordContent, createdAt, updatedAt);
			}
		);
	}

	@Override
	public Optional<Record> selectRecordById(UUID record_id) {
		final String sql = "SELECT record_id, journal_id, record_title, content, createdAt, updatedAt FROM records WHERE record_id = ?";
		Record record = jdbcTemplate.queryForObject(
			sql,
			new Object[]{record_id},
			(resultSet, i) -> {
				UUID recordId = UUID.fromString(resultSet.getString("record_id"));
				UUID journalId = UUID.fromString(resultSet.getString("journal_id"));
				String recordTitle = resultSet.getString("record_title");
				String recordContent = resultSet.getString("content");
				Timestamp createdAt = resultSet.getTimestamp("createdAt");
				Timestamp updatedAt = resultSet.getTimestamp("updatedAt");
				return new Record(recordId, journalId, recordTitle, recordContent, createdAt, updatedAt);
			}
		);
		return Optional.ofNullable(record);
	}

	@Override
	public int deleteRecordById(UUID record_id) {
		jdbcTemplate.update("delete from records where record_id = ?", record_id);
		return 0;
	}

	@Override
	public Record updateRecordById(UUID record_id, Record record) {
		final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		final String sql = "update records set record_title = ?, content = ?, updatedAt = ? where record_id = ?";
		jdbcTemplate.update(sql, record.getRecord_title(), record.getContent(), timestamp, record_id);
		Record newRecord = jdbcTemplate.queryForObject(
						sql,
						new Object[]{record_id},
						(resultSet, i) -> {
							UUID recordId = UUID.fromString(resultSet.getString("record_id"));
							UUID journalId = UUID.fromString(resultSet.getString("journal_id"));
							String recordTitle = resultSet.getString("record_title");
							String recordContent = resultSet.getString("content");
							Timestamp createdAt = resultSet.getTimestamp("createdAt");
							Timestamp updatedAt = resultSet.getTimestamp("updatedAt");
							return new Record(recordId, journalId, recordTitle, recordContent, createdAt, updatedAt);
						}
		);
		return newRecord;
	}
}
