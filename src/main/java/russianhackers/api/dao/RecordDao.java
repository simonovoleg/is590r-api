package russianhackers.api.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import russianhackers.api.model.Journal;
import russianhackers.api.model.Record;

public interface RecordDao {

	int insertRecord (UUID journal_id, UUID record_id, Record record);

	default int insertRecord (UUID journal_id, Record record) {
		UUID record_id = UUID.randomUUID();
		return insertRecord (journal_id, record_id, record);
	}

	Optional<Record> selectRecordById(UUID record_id);

	List<Record> selectRecordsByJournalId(UUID journal_id);

	int deleteRecordById(UUID record_id);

	int updateRecordById(UUID record_id, Record record);
}
